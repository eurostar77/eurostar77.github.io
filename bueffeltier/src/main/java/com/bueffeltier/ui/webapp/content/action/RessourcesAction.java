package com.bueffeltier.ui.webapp.content.action;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.apache.commons.lang3.StringUtils;

import com.bueffeltier.logic.foundation.FileExplorerService;

/**
 *
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
public class RessourcesAction extends AbstractAction
{

	private static RessourcesAction instance;

	private FileExplorerService fileExplorerService;

	private RessourcesAction(HttpServletRequest request)
	{
		super();

		fileExplorerService = FileExplorerService.getInstance(request);
	}

	public static RessourcesAction getInstance(HttpServletRequest request)
	{
		if (instance == null)
		{
			instance = new RessourcesAction(request);
		}
		return instance;
	}

	@Override
	public void execute(HttpServletRequest request)
	{
//		boolean isMultipart = ServletFileUpload.isMultipartContent(request);

		String currentFolder = request.getParameter("currentFolder");

		String finalFileDestination = request.getParameter("destination");

		String currentFolderAbsolutePath = fileExplorerService
		    .getAbsolutePath(currentFolder);

		File showFolder = fileExplorerService
		    .getFolder(currentFolderAbsolutePath);

		Enumeration<String> parameterNames = request.getParameterNames();

		while (parameterNames.hasMoreElements())
		{
			String actionParameterName = parameterNames.nextElement()
			    .toString();

			switch (actionParameterName) {

			case "up":
				if (StringUtils
				    .isNotBlank(request.getParameter(actionParameterName)))
				{
					showFolder = getShowFolder(
					    request, request.getParameter(actionParameterName)
					);
				}
				break;

			case "openFolder":
				if (StringUtils
				    .isNotBlank(request.getParameter(actionParameterName)))
				{
					showFolder = getShowFolder(
					    request, request.getParameter(actionParameterName)
					);
				}
				break;

			case "openFile":
				if (StringUtils
				    .isNotBlank(request.getParameter(actionParameterName)))
				{
//					fileExplorerService.
				}
				break;

			case "uploadFileInput":

				Part filePart = null;

				try
				{
					filePart = request.getPart("uploadFileInput");

				} catch (Exception e)
				{//

				}

				if (filePart != null)
				{
					String fileName = filePart.getSubmittedFileName();

					Path path = Paths
					    .get(currentFolderAbsolutePath + "\\" + fileName);

					if (filePart != null)
					{
						try (
						    InputStream fileContent = filePart.getInputStream())
						{
							Files.copy(fileContent, path);

						} catch (IOException e)
						{
							// todo handle exception
						}
					}
				}

				break;

			case "newFolderName":
				if (StringUtils
				    .isNotBlank(request.getParameter(actionParameterName)))
				{
					String newFolderName = request
					    .getParameter("newFolderName");

					if (StringUtils.isNotBlank(newFolderName))
					{
						try
						{
							Files.createDirectories(
							    Paths.get(
							        currentFolderAbsolutePath + "\\"
							            + newFolderName
							    )
							);
						} catch (IOException e)
						{

						}
					}

					showFolder = fileExplorerService
					    .getFolder(currentFolderAbsolutePath);
				}
				break;

			case "deleteFile":
				if (StringUtils
				    .isNotBlank(request.getParameter(actionParameterName)))
				{
					String deleteRelativePath = request
					    .getParameter(actionParameterName);
					String deleteAbsolutePath = fileExplorerService
					    .getAbsolutePath(deleteRelativePath);

					fileExplorerService.deleteFile(deleteAbsolutePath);

					showFolder = fileExplorerService
					    .getFolder(currentFolderAbsolutePath);
				}
				break;
			}
		}

		request.setAttribute("fileExplorerShowFolder", showFolder);

		forwardToPage(request, "/ressources");
	}

	private File getShowFolder(HttpServletRequest request, String relativePath)
	{
		return fileExplorerService
		    .getFolder(fileExplorerService.getAbsolutePath(relativePath));
	}

	@Override
	public void doAjaxAction(HttpServletRequest request)
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
