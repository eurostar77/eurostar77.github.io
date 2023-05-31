package com.bueffeltier.logic.foundation;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.bueffeltier.crosscutting.AppPropertyService;

public class FileExplorerService
{
	private static FileExplorerService instance;

	private AppPropertyService appPropertyService = AppPropertyService
	    .getInstance();

	private File rootFolder;

	private FileExplorerService(HttpServletRequest request)
	{
		createRootFolder(request);
	}

	public static FileExplorerService getInstance(HttpServletRequest request)
	{
		if (instance == null)
		{
			instance = new FileExplorerService(request);
		}
		return instance;
	}

	private void createRootFolder(HttpServletRequest request)
	{
		rootFolder = new File(appPropertyService.getRessourcesRootFolder());
	}

	public File getParentFolder(String currentFolderAbsolutePath)
	{
		File resultFolder;

		if (isChildOfRoot(currentFolderAbsolutePath))
		{
			resultFolder = new File(currentFolderAbsolutePath);

		} else
		{
			resultFolder = rootFolder;
		}

		return resultFolder;
	}

	public String getPathToBase(File subfolder)
	{

		Path basePath = Paths.get(rootFolder.getPath()).normalize()
		    .toAbsolutePath();
		Path subPath = Paths.get(subfolder.getPath()).normalize()
		    .toAbsolutePath();

		if (!subPath.startsWith(basePath))
		{
			throw new IllegalArgumentException(
			    "The sub folder is not a subdirectory of the base folder."
			);
		}

		String baseFolderName = basePath.getFileName().toString();
		Path relativePath = basePath.relativize(subPath);

		if (relativePath.toString().contains(".."))
		{
			throw new IllegalArgumentException(
			    "The sub folder must not reference parent directories with '..'"
			);
		}

		return Paths.get("/" + baseFolderName).resolve(relativePath).toString();
	}

	private boolean isChildOfRoot(String path)
	{
		Path rootPath = Paths.get(rootFolder.getPath());

		Path filePath = Paths.get(path);

		Path relativePath = filePath.relativize(rootPath);

		return !relativePath.isAbsolute() && !relativePath.startsWith("..");
	}

	public boolean isChildOfRoot(File folder)
	{
		File parent = folder.getParentFile();

		while (parent != null)
		{
			if (parent.getPath().equals(rootFolder.getPath()))
			{
				return true;
			}
			parent = parent.getParentFile();
		}
		return false;
	}

	public boolean isRoot(File folder)
	{
		if (folder.getPath().equals(rootFolder.getPath()))
		{
			return true;

		} else
		{
			return false;
		}
	}

	public File getRootFolder()
	{
		return rootFolder;
	}

	public File getFolder(String absoluteFolderPath)
	{
		File folderToOpen = new File(absoluteFolderPath);

		if (!folderToOpen.isDirectory())
		{
			folderToOpen = null;
		}

		return folderToOpen;
	}

	public String getRelativePath(String fullPath)
	{
		String rootFolderName = rootFolder.getPath()
		    .substring(rootFolder.getPath().lastIndexOf("\\") + 1);

		int rootFolderIndex = fullPath.indexOf(rootFolderName);

		if (rootFolderIndex < 0)
		{
			return null; // root folder not found in path
		}
		String rootPath = fullPath.substring(rootFolderIndex);

		return "\\" + rootPath;
	}

	public String getAbsolutePath(String relativePath)
	{
		int lastIndex = rootFolder.getPath().lastIndexOf("\\");

		String rootFolderBasePath = rootFolder.getPath()
		    .substring(0, lastIndex);

		return rootFolderBasePath + relativePath;
	}

	public String getFileType(File file)
	{
		String fileType = "";

		if (file.isDirectory())
		{
			fileType = "Ordner";
		} else
		{
			int lastDotIndex = file.getName().lastIndexOf(".");

			if (lastDotIndex != -1
			    && lastDotIndex < file.getName().length() - 1)
			{
				fileType = file.getName().substring(lastDotIndex + 1)
				    + "-Datei";
			}
		}

		return fileType;
	}

	public List<File> getFilesFromFolder(File folder)
	{
		List<File> filesFromFolder = null;

		File[] files = folder.listFiles();

		if (files != null)
		{
			filesFromFolder = sortFilesInFolder(Arrays.asList(files));
		}

		return filesFromFolder;
	}

	public void
	    addFile(File sourceFile, String destinationFolderPath, String fileName)
	{

		File destinationFolder = new File(destinationFolderPath);

		try
		{
			boolean success = sourceFile
			    .renameTo(new File(destinationFolder, fileName));

			if (success)
			{
				System.out.println("File moved successfully.");
			} else
			{
				System.out.println("File move failed.");
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
//		Files.move(source, target, REPLACE_EXISTING);
//		
//		
//		File addFile = new File(folder, fileName);
//		try
//		{
//			boolean success = file.renameTo(new File(absolutePath));
//			if (success)
//			{
//				System.out.println(
//				    "Datei erfolgreich hochgeladen nach " + absolutePath
//				);
//			} else
//			{
//				System.out.println(
//				    "Fehler beim Hochladen der Datei nach " + absolutePath
//				);
//			}
//		} catch (Exception e)
//		{
//			e.printStackTrace();
//		}
	}

	public List<File> getFilesForBreadcrumb(File currentFolder)
	{
		List<File> breadcrumb = new ArrayList<>();

		breadcrumb.add(currentFolder);

		File parentFolder = currentFolder.getParentFile();

		while (parentFolder != null
		    && (isChildOfRoot(parentFolder) || isRoot(parentFolder)))
		{
			breadcrumb.add(parentFolder);

			parentFolder = parentFolder.getParentFile();
		}
		Collections.reverse(breadcrumb);

		return breadcrumb;
	}

	public List<File> getFilesForBreadcrumb2(File currentFolder)
	{
		List<File> parentFolderList = new ArrayList<>();

		File parentFolder = currentFolder;

		while (parentFolder != null && !parentFolder.equals(rootFolder))
		{
			parentFolderList.add(parentFolder);
			parentFolder = parentFolder.getParentFile();
		}

		if (parentFolder != null && parentFolder.equals(rootFolder))
		{
			parentFolderList.add(parentFolder);
		}

		List<File> reversedList = new ArrayList<>(parentFolderList);
		java.util.Collections.reverse(reversedList);

		return reversedList;
	}

	private List<File> sortFilesInFolder(List<File> files)
	{
		Comparator<File> fileComparator = new Comparator<File>()
		{
			@Override
			public int compare(File f1, File f2)
			{
				if (f1.isDirectory() && !f2.isDirectory())
				{
					return -1; // f1 is a folder, f2 is a file, f1 comes first
				} else if (!f1.isDirectory() && f2.isDirectory())
				{
					return 1; // f1 is a file, f2 is a folder, f2 comes first
				} else
				{
					// Both are either folders or files, sort by name
					return f1.getName().compareTo(f2.getName());
				}
			}
		};

		files.sort(fileComparator);

		return files;
	}

	public void deleteFile(String abloluteFilePath)
	{
		File folderToDelete = new File(abloluteFilePath);

		if (folderToDelete.exists())
		{
			if (folderToDelete.isDirectory())
			{
				File[] files = folderToDelete.listFiles();

				if (files != null)
				{
					for (File file : files)
					{
						if (file.isDirectory())
						{
							deleteFile(file.getAbsolutePath());
						} else
						{
							file.delete();
						}
					}
				}
			}
			if (folderToDelete.delete())
			{
				System.out.println(
				    "Folder on path \"" + abloluteFilePath
				        + "\" has been deleted."
				);

			} else
			{
				System.out.println(
				    "Error deleting folder on path \"" + abloluteFilePath + "\""
				);
			}

		} else
		{
			File fileToDelete = new File(abloluteFilePath);

			if (fileToDelete.exists() && fileToDelete.isFile())
			{
				if (fileToDelete.delete())
				{
					System.out.println(
					    "File on path \"" + abloluteFilePath
					        + "\" has been deleted."
					);

				} else
				{
					System.out.println(
					    "Error deleting file on path \"" + abloluteFilePath
					        + "\""
					);
				}

			} else
			{
				System.out.println(
				    "Path \"" + abloluteFilePath
				        + "\" does not exist or is not a valid file or folder."
				);
			}
		}
	}
}