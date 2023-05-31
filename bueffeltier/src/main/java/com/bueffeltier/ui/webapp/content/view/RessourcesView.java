package com.bueffeltier.ui.webapp.content.view;

import static j2html.TagCreator.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;

import com.bueffeltier.data.jdbc.ElementJDBCFlat;
import com.bueffeltier.logic.foundation.FileExplorerService;
import com.bueffeltier.ui.html.molecule.SpacingPropertyDV;
import com.bueffeltier.ui.html.molecule.SpacingSidesDV;
import com.bueffeltier.ui.html.molecule.SpacingSizeDV;
import com.bueffeltier.ui.html.organism.ButtonBuilder;
import com.bueffeltier.ui.html.organism.ButtonBuilder.ButtonInputTypeDV;
import com.bueffeltier.ui.html.organism.ButtonBuilder.ColorDV;
import com.bueffeltier.ui.html.organism.FormControlBuilder;
import com.bueffeltier.ui.html.organism.IconBuilder;
import com.bueffeltier.ui.html.organism.IconBuilder.IconTypeDV;
import com.bueffeltier.ui.html.organism.LabelBuilder;
import com.bueffeltier.ui.html.organism.ModalBuilder;
import com.bueffeltier.ui.html.organism.TableBuilder;
import com.bueffeltier.ui.html.organism.TableBuilder.TableColumnTypeDV;
import com.bueffeltier.ui.html.organism.TableRow;

import j2html.tags.DomContent;
import j2html.tags.specialized.ITag;
import j2html.tags.specialized.LiTag;

/**
 *
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
public class RessourcesView extends AbstractView
{
	private static RessourcesView instance;

	private FileExplorerService fileExplorerService;

	private RessourcesView(HttpServletRequest request)
	{
		super();

		fileExplorerService = FileExplorerService.getInstance(request);
	}

	public static RessourcesView getInstance(HttpServletRequest request)
	{
		if (instance == null)
		{
			instance = new RessourcesView(request);
		}
		return instance;
	}

	@Override
	public DomContent
	    writeHtml(ElementJDBCFlat element, HttpServletRequest request)
	        throws Exception
	{
		String originPagePath = (String) request.getAttribute("originPagePath");

		boolean isInSelectionMode = false;

		if (!originPagePath.equals("/ressources"))
		{
			isInSelectionMode = true;
		}

		// - PagePath in die View schreiben und bei Aktualisieren mitnehmen,
		// dass Aktualisieren nicht die origin überschreibt
		// - Dateiauswahl nur ermöglichen, wenn Dateimanager nicht direkt
		// aufgerufen wurde.
		// - Man darf keine Dateien einschleusen können. In Ressoures? In

		File currentFolder = (File) request
		    .getAttribute("fileExplorerShowFolder");

		if (currentFolder == null)
		{
			currentFolder = fileExplorerService.getRootFolder();
		}

		List<File> files = fileExplorerService
		    .getFilesFromFolder(currentFolder);

		List<TableRow> tableRows = new ArrayList<>();

		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");

		if (files != null)
		{
			for (File file : files)
			{
				String fileType = null;
				String openCmd = "";
				IconTypeDV iconType = IconTypeDV.OPEN_FOLDER;

				if (file.isDirectory())
				{
					iconType = IconTypeDV.FOLDER;
					openCmd = "openFolder";
					fileType = "folder";

				} else if (file.isFile())
				{
					iconType = IconTypeDV.FILE;
					openCmd = "openFile";
					fileType = "file";
				}

//				String icon = iconClassString;
				String name = file.getName();
				String size = Long.toString(file.getTotalSpace());
				String type = fileExplorerService.getFileType(file);
				String changeDate = sdf.format(file.lastModified());
				String createDate = "XX" + sdf.format(file.lastModified());

				ITag icon = IconBuilder.create()//
				    .withIcon(iconType)//
				    .withFontSize(24)//
//				    .withColor()//
				    .build();

				DomContent btnDelete = ButtonBuilder.create()//
				    .withClass(fileType)//
				    .withClass("btnDelete")//
				    .withColor(ColorDV.SECONDARY)//
				    .asOutline().withIcon(IconTypeDV.DELETE, 24)//
//				    .withId(fileExplorerService.getRelativePath(file.getPath()))
				    .withOncLick(
				        "openDeleteFileModal('" + fileExplorerService
				            .getRelativePath(file.getPath())
				            .replace("\\", "\\\\") + "')"

				    )//
				    .withName("deleteFile")//
				    .withValue("")//
//				    .withInputType(ButtonInputTypeDV.SUBMIT)//
				    .build();

				DomContent btnEdit = ButtonBuilder.create()//
				    .withClass("")//
				    .withColor(ColorDV.SECONDARY)//
				    .asOutline().withIcon(IconTypeDV.EDIT, 24)//
//				    .withText("Bearbeiten")//
				    .withName("")//
				    .withValue("")//
				    .withInputType(ButtonInputTypeDV.SUBMIT)//
				    .build();

				DomContent btnOpen = null;

				if (file.isDirectory())
				{
					btnOpen = ButtonBuilder.create()//
					    .withClass("")//
//					    .withText("Öffnen")//
					    .withIcon(IconTypeDV.OPEN_FOLDER, 24)//
					    .withName(openCmd)//
					    .withValue(
					        fileExplorerService.getRelativePath(file.getPath())
					    )//
					    .withInputType(ButtonInputTypeDV.SUBMIT)//
					    .build();
				} else
				{
					btnOpen = ButtonBuilder.create()//
					    .withClass("")//
//					    .withText("Öffnen")//
					    .withIcon(IconTypeDV.FILE_CHECK, 24)//
					    .withName(openCmd)//
					    .withValue(
					        fileExplorerService.getRelativePath(file.getPath())
					    )//
					    .withInputType(ButtonInputTypeDV.SUBMIT)//
					    .build();
				}

				List<Object> row = Arrays.asList(
				    icon, name, size, type, changeDate, createDate, btnDelete,
				    btnEdit, btnOpen
				);

				tableRows.add(new TableRow(row));
			}
		}

		return form(
		    element.getType(), div(
		        //
		        h1("Dateimanager"), //

		        // Current Shown Folder
		        input()//
		            .withType("hidden")//
		            .withName("currentFolder")//
		            .withValue(
		                fileExplorerService
		                    .getRelativePath(currentFolder.getPath())
		            )//
		            .withId("currentFolderInput"),

		        // Datei-Upload
		        ButtonBuilder.create()//
		            .withText("Datei hochladen")//
		            .withInputType(ButtonInputTypeDV.BUTTON)//
		            .withOncLick("uploadFiles()")//
		            .withId("uploadFileButton")//
		            .build(),

		        input()//
		            .withStyle("display: none;")//
		            .withName("uploadFileInput")//
		            .withId("uploadFileInput")//
		            .withType("file"),

		        script(
		            """
		                function uploadFiles()
		                {
		                	var uploadFileInput = document.getElementById('uploadFileInput');
		                    uploadFileInput.click();
		                   	uploadFileInput.addEventListener('change', () =>
		                   	{
		                   	    var form = document.getElementById('fileExplorer');
		                   	    form.enctype = 'multipart/form-data';

		                   	    var uploadFileParam = document.createElement("input");
		                        uploadFileParam.setAttribute("name", "uploadFileInput");
		                        form.appendChild(uploadFileParam);

		                        form.submit();
		                        form.enctype = null;
		                    });
		                }
		                """
		        ),

		        // Neuer-Ordner-Button
		        ButtonBuilder.create()//
		            .withText("Neuer Ordner")//
		            .withInputType(ButtonInputTypeDV.BUTTON)//
		            .withOncLick("openNewFolderModal()")//
		            .build()

		    ).withClass("container mt-4"),

		    div(
		        //
		        nav(
		            //
		            ol(
		                //
		                each(
		                    getBreadcrumbItems(currentFolder), bcItem -> bcItem
		                )
		            ).withClass("breadcrumb")//
		        ).attr("aria-label", "breadcrumb")//
		    ).withClass("container mt-4"),

		    TableBuilder.create()//
		        .withColumn("", TableColumnTypeDV.ICON)//
		        .withColumn("Name", TableColumnTypeDV.TEXT)//
		        .withColumn("Größe", TableColumnTypeDV.TEXT)//
		        .withColumn("Typ", TableColumnTypeDV.TEXT)//
		        .withColumn("Geändert am", TableColumnTypeDV.TEXT)//
		        .withColumn("Erstellt am", TableColumnTypeDV.TEXT)//
		        .withColumn("", TableColumnTypeDV.BUTTON)//
		        .withColumn("", TableColumnTypeDV.BUTTON)//
		        .withColumn("", TableColumnTypeDV.BUTTON)//
		        .withRows(tableRows)//
		        .withGroupDivider()//
		        .withHoverableRows()//
		        .build(),

		    // Neuer Ordner Modal
		    ModalBuilder.create()//
		        .withDomContent(
		            LabelBuilder.create()//
		                .build(
		                    "Bitte geben Sie einen Namen für den Ordner ein.",
		                    "folderNameInput"
		                ), //
		            FormControlBuilder.create()//
		                .withId("folderNameInput")//
		                .withName("newFolderName")//
//		                .withValue("")//
		                .build()//
		        )//
		        .withApproveButton("OK")//
		        .withApproveFunctionName("newFolderModalApprove()")//
		        .withApproveFunctionScript(
		            """
		                function newFolderModalApprove(){
		                  var form = document.getElementById('fileExplorer')
		                  var newFolderName = document.getElementById('folderNameInput').value;
		                  if(newFolderName.length>0){
		                    form.submit();
		                  }
		                }
		                """
		        )//
		        .withAbortButton()
		        .withAbortFunctionName("newFolderModalAbort()")//
		        .withAbortFunctionScript("""
		            function newFolderModalAbort(){
		              document.getElementById('folderNameInput').value = '';
		            }
		            """)//
		        .build(
		            "Neuen Ordner anlegen", "newFolderModal", "newFolderModal",
		            null, "openNewFolderModal()"
		        ),

		    // Delete File Modal
		    ModalBuilder.create()//
		        .withDomContent(
		            LabelBuilder.create()//
		                .build("Wollen Sie diese Datei wirklich löschen?", null)
		        )//
		        .withOpenFunctionParameter("fileDeletePath")//
		        .withApproveButton("Löschen")//
		        .withApproveFunctionName("approveDeleteFileModal()")//
		        .withApproveFunctionScript(
		            """
		                           function approveDeleteFileModal(){
		                             var filepathInput = document.createElement("input");
		                             filepathInput.setAttribute("type", "hidden");
		                             filepathInput.setAttribute("name", "deleteFile");
		                             filepathInput.setAttribute("value", fileDeletePath);

		                			 // todo: form global in page setzen?
		                			 var form = document.getElementById("fileExplorer");
		                			 form.appendChild(filepathInput);
		                			 form.submit();

		                // TODO: modal bekommt auch ein script zugriff auf elemente?
		                //
		                //		                  let buttons = document.querySelectorAll('.btnDelete');
		                //		                  buttons.forEach(function(button) {
		                //		                      button.onclick = function() {
		                //		                      openModal(button.name);
		                //		                    }
		                //		                  });

		                             // var newFolderName = document.getElementById('folderNameInput').value;
		                             // if(newFolderName.length>0){
		                             //  form.submit();
		                             // }
		                           }
		                           """
		        )//
		        .withAbortButton()//
		        .withAbortFunctionName("abortDeleteFileModal()")//
		        .withAbortFunctionScript(
		            """
		                function abortDeleteFileModal(){
		                  // var button = document.getElementById('folderNameInput').value = '';
		                  // value von button löschen
		                  //
		                  //document.getElementById('newFolderInput').value = '';
		                }
		                """
		        )//
		        .build(
		            "Datei löschen", "deleteFileModal", "deleteFileModal", null,
		            "openDeleteFileModal()"
		        )

		).withId("fileExplorer");
	}

	private List<LiTag> getBreadcrumbItems(File currentFolder)
	{
		List<LiTag> bcLiItems = new ArrayList<>();

		File parentFile = currentFolder.getParentFile();

		if (!fileExplorerService.isChildOfRoot(currentFolder))
		{
			parentFile = fileExplorerService.getRootFolder();
		}

		// todo: keine action auf currentFolder:
		LiTag upItem = new LiTag().with(
		    ButtonBuilder.create()//
		        .withInputType(ButtonInputTypeDV.SUBMIT)//
		        .withName("up")//
		        .withValue(
		            fileExplorerService.getRelativePath(parentFile.getPath())
		        )//
		        .withInputType(ButtonInputTypeDV.SUBMIT)//
		        .withIcon(IconTypeDV.LEVEL_UP, 0)//
		        .build()
		).withClass("breadcrumb-item");

		bcLiItems.add(upItem);

		List<File> breadcrumbs = fileExplorerService
		    .getFilesForBreadcrumb(currentFolder);

		// todo: keine action auf currentFolder:
		LiTag homeItem = new LiTag().with(
		    ButtonBuilder.create()//
		        .withText(breadcrumbs.get(0).getName())//
		        .withName("openFolder")//
		        .withValue(
		            fileExplorerService
		                .getRelativePath(breadcrumbs.get(0).getPath())
		        ).withColor(ColorDV.LINK)//
		        .withSpacing(
		            SpacingPropertyDV.MARGIN, SpacingSidesDV.BLANK,
		            SpacingSizeDV.NULL
		        )//
		        .withInputType(ButtonInputTypeDV.SUBMIT)//
		        .withIcon(IconTypeDV.HOME, 0)//
		        .withStyle("vertical-align: middle;")//
		        .build()
		).withClass("breadcrumb-item");

		bcLiItems.add(homeItem);

		for (int i = 1; i < breadcrumbs.size(); i++)
		{
			// todo: keine action auf currentFolder
			LiTag folderItem = new LiTag().with(
			    ButtonBuilder.create()//
			        .withText(breadcrumbs.get(i).getName())//
			        .withName("openFolder")//
			        .withValue(
			            fileExplorerService
			                .getRelativePath(breadcrumbs.get(i).getPath())
			        ).withColor(ColorDV.LINK)//
			        .withSpacing(
			            SpacingPropertyDV.MARGIN, SpacingSidesDV.BLANK,
			            SpacingSizeDV.NULL
			        )//
			        .withInputType(ButtonInputTypeDV.SUBMIT)//
			        .withIcon(IconTypeDV.FOLDER, 0)//
			        .withStyle("vertical-align: middle;")//
			        .build()
			).withClass("breadcrumb-item");
			;
			bcLiItems.add(folderItem);
		}

		return bcLiItems;
	}

	public Set<String> listFilesUsingFilesList(String dir) throws IOException
	{
		try (Stream<Path> stream = Files.list(Paths.get(dir)))
		{
			return stream.filter(file -> !Files.isDirectory(file))
			    .map(Path::getFileName).map(Path::toString)
			    .collect(Collectors.toSet());
		}
	}

	public List<File> getFileList(final File folder)
	{
		List<File> files = new ArrayList<>();

		for (final File fileEntry : folder.listFiles())
		{
			files.add(fileEntry);
		}

		return files;
	}

	@Override
	public DomContent writeHtmlControls()
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public String writeCss()
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	protected void setCssId(String containerTagClassName)
	{
		// TODO Auto-generated method stub

	}

	@Override
	protected void setContainerTag()
	{
		// TODO Auto-generated method stub

	}
}
