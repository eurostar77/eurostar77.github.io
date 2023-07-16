package com.bueffeltier.ui.webapp.content.view;

import static com.bueffeltier.ui.html.molecule.customTag.clientSideActionButtonSwitchable;
import static j2html.TagCreator.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.bueffeltier.data.jdbc.ArticleJDBCFlat;
import com.bueffeltier.data.jdbc.ElementJDBCFlat;
import com.bueffeltier.data.jdbc.Page;
import com.bueffeltier.logic.foundation.pagetree.SiteRepository;
import com.bueffeltier.ui.html.molecule.SpacingPropertyDV;
import com.bueffeltier.ui.html.molecule.SpacingSidesDV;
import com.bueffeltier.ui.html.molecule.SpacingSizeDV;
import com.bueffeltier.ui.html.organism.ButtonBuilder;
import com.bueffeltier.ui.html.organism.ButtonBuilder.ButtonTagTypeDV;
import com.bueffeltier.ui.html.organism.ButtonBuilder.ButtonTypeDV;
import com.bueffeltier.ui.html.organism.ButtonBuilder.ColorDV;
import com.bueffeltier.ui.html.organism.ColumnBuilder;
import com.bueffeltier.ui.html.organism.ColumnBuilder.GridWidthDV;
import com.bueffeltier.ui.html.organism.FormControlBuilder;
import com.bueffeltier.ui.html.organism.RowBuilder;
import com.bueffeltier.ui.html.organism.TableBuilder;
import com.bueffeltier.ui.html.organism.TableBuilder.TableColumnTypeDV;
import com.bueffeltier.ui.html.organism.TableBuilder.TableHeadColorDV;
import com.bueffeltier.ui.html.organism.TableRow;

import j2html.tags.DomContent;

/**
 *
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
public class SiteStructureView extends AbstractView
{
	SiteRepository siteRepository = SiteRepository.getInstance();

	private static SiteStructureView instance;

	private SiteStructureView()
	{
		super();
	}

	public static SiteStructureView getInstance()
	{
		if (instance == null)
		{
			instance = new SiteStructureView();
		}
		return instance;
	}

	@Override
	public DomContent
	    writeHtml(ElementJDBCFlat element, HttpServletRequest request)
	{
		ArrayList<Page> pages = (ArrayList<Page>) siteRepository
		    .readAll();

		List<TableRow> rows = new ArrayList<>();

		for (Page page : pages)
		{
			DomContent btnExpand = ButtonBuilder.create()//
			    .withButtonType(ButtonTypeDV.SUBMIT)//
			    .asOutline()//
			    .withText("+")//
//			    .withId(Long.toString(page.getId()))//
			    .withTagType(ButtonTagTypeDV.BUTTON)//
			    .withOncLick(
			        "loadArticles(" + Long.toString(page.getId()) + ")"
			    ).build();

			String txtId = Long.toString(page.getId());
			String txtTitle = page.getHtmlTitle();

			DomContent btnEdit = ButtonBuilder.create()//
			    .withText("Edit")//
			    .withName("editPage")//
			    .withValue(txtId)//
			    .withButtonType(ButtonTypeDV.SUBMIT)//
			    .asOutline()//
			    .build();

			DomContent btnPublish = ButtonBuilder.create()//
			    .withText("Publish")//
			    .withColor(ColorDV.SECONDARY)//
			    .asOutline()//
			    .build();

			DomContent btnDelete = ButtonBuilder.create()//
			    .withText("Delete")//
			    .withColor(ColorDV.DANGER)//
			    .asOutline()//
			    .asOutline()//
			    .build();

			DomContent btnAddArticle = ButtonBuilder.create()//
			    .withText("Add Article")//
			    .build();

			List<Object> row = Arrays.asList(
			    btnExpand, txtId, txtTitle, btnEdit, btnPublish, btnDelete,
			    btnAddArticle
			);

			rows.add(new TableRow(row));
		}

		return form(

		    element.getType(),

		    RowBuilder.create()//
		        .withSpacing(
		            SpacingPropertyDV.MARGIN, SpacingSidesDV.BOTTOM,
		            SpacingSizeDV.FOUR
		        ).withDomContent(
		            div(),

		            ColumnBuilder.create().withGridWidth(GridWidthDV.ONE)
		                .withClass("text-start").withDomContent(div()).build(),

		            ColumnBuilder.create().withGridWidth(GridWidthDV.FOUR)
		                .withClass("text-start")
		                .withDomContent(
		                    FormControlBuilder.create()
		                        .withId("filterSearchInput").build()
		                ).build(),

		            ColumnBuilder.create().//
		                withGridWidth(GridWidthDV.TWO)//
		                .withClass("text-start")//
		                .withDomContent(
		                    ButtonBuilder.create()//
		                        .withText("Suchen")//
		                        .build()
		                ).build(),

		            ColumnBuilder.create().//
		                withGridWidth(GridWidthDV.FOUR)//
		                .withClass("text-end")//
		                .withDomContent(
		                    ButtonBuilder.create()//
		                        .withButtonType(ButtonTypeDV.SUBMIT)//
		                        .withText("Neue Seite")//
		                        .withName("newPage")//
		                        .withValue("newPage")//
		                        .build()
		                ).build(),
		            ColumnBuilder.create().withGridWidth(GridWidthDV.ONE)
		                .withClass("text-end").withDomContent(div()).build()
		        ).build(),

		    TableBuilder//
		        .create()//
		        .withColumn("", TableColumnTypeDV.BUTTON, GridWidthDV.ONE)//
		        .withColumn("", TableColumnTypeDV.TEXT, GridWidthDV.ONE)
		        .withColumn("", TableColumnTypeDV.TEXT, GridWidthDV.SEVEN)
		        .withColumn("", TableColumnTypeDV.BUTTON, GridWidthDV.ONE)
		        .withColumn("", TableColumnTypeDV.BUTTON, GridWidthDV.ONE)
		        .withColumn("", TableColumnTypeDV.BUTTON, GridWidthDV.ONE)
		        .withHoverableRows().withRows(rows)//
		        .withHeadColor(TableHeadColorDV.LIGHT)//

		        .build()//
		);
		// todo: index seite nicht kopierbar machen!
	}

//	/**
//	 *
//	 * @return
//	 */
//	public DomContent infoField()
//	{
//
//		String info = "";
//		try
//		{
//			info = (String) getContentAttributeValue("info");
//		} catch (Exception ex)
//		{
//
//		}
//
//		if (info != null)
//		{
//			return p(info).withStyle("background-color: tomato");
//		} else
//		{
//			return p();
//		}
//	}

	public DomContent pageTitle(long l, String pageTitle)
	{
		return p("ID: " + Long.toString(l) + " - " + pageTitle)
		    .withClass("page-title");
	}

	/**
	 *
	 * @param pageId
	 * @param expandedArticlePages
	 * @return
	 */
	public DomContent articleRows(long pageId, List<ArticleJDBCFlat> articles)
	{
		return // todo: iff einführen
		each(
		    articles,
		    article -> div(
		        articleTitle(article.getTitle()),
		        articleButtonGroup(article.getId())
		    ).withClass("article-row")
		);
	}

	/**
	 *
	 * @param articleTitle
	 * @return
	 */
	public DomContent articleTitle(String articleTitle)
	{
		return p(articleTitle).withClass("article-title");
	}

	/**
	 *
	 * @param articleId
	 * @return
	 */
	public DomContent articleButtonGroup(long articleId)
	{
		String articleIdString = Long.toString(articleId);
		return div().withClass("article-buttons").with(

		    ButtonBuilder.create()//
		        .withText("Edit Article")//
		        .withName("editArticle")//
		        .withValue(articleIdString)//
		        .build(),
		    // todo: Wenn kein JS-dann muss die Message Box als extra Seite
		    // oder als Info-Feld direkt bei dem Button.
		    // Anker auf der Seite helfen, dabei, dass die INfo auch gelesen
		    // wird.
		    // Vielleicht wird die Seite dann auch entsprechend css
		    // formatiert geladen, so dasss die info im vordergrund steht.
		    // submitButtonSwitchable("Delete Article", "deleteArticle",
		    // articleIdString, disableDeleteArticleButton),
		    clientSideActionButtonSwitchable(
		        "Delete Article", "deleteArticle", articleIdString, true,
		        articleId, "showDialog"
		    ),
		    ButtonBuilder.create()//
		        .withText("Move Article")//
		        .withName("moveArticle")//
		        .withValue(articleIdString)//
		        .build(),
		    ButtonBuilder.create()//
		        .withText("Publish Article")//
		        .withName("publishArticle")//
		        .withValue(articleIdString)//
		        .build(),
		    ButtonBuilder.create()//
		        .withText("Insert Behind Article")//
		        .withName("test todo")//
		        .withValue(articleIdString)//
		        .build(),
		    dlgBackground(), dlgBox(articleId)
		);
	}

	/**
	 *
	 * @return
	 */
	public DomContent dlgBackground()
	{
//        if(showMsgBox==true){
		return div().attr("id=\"white-background\"");
//        }else{
//            return div(
//                script("dlgHide()")
//            );
//        }
	}

	/**
	 *
	 * @param articleId
	 * @return
	 */
	public DomContent dlgBox(long articleId)
	{
//        if(showMsgBox==true){
		return div(
		    div("Please confirm!").attr("id=\"dlg-header\""),
		    div("Artikel unwiderruflich löschen?").attr("id=\"dlg-body\""),
		    div(
		        button().withText("ok").withName("finalyDeleteArticle")
		            .withValue(Long.toString(articleId)).withType("submit"), // .attr("onclick=\"dlgHide()\""),
		        // submitButton("ok","finalyDeleteArticle",Integer.toString(articleId)),
		        button("cancel").attr(
		            "type = \"button\" onclick=\"dlgCancel(" + articleId + ")\""
		        )
				// button("cancel").attr("type = \"button\"
				// onclick=\"dlgCancel(" + articleId + ")\"")
		    ).attr("id=\"dlg-footer\"")
		).attr("id=\"dlgbox-" + articleId + "\"");
//        }else{
//            return div();
//        }
	}

	/**
	 *
	 * @param pageLevel
	 * @return
	 */
	public String pageContextStyle(int pageLevel)
	{
		StringBuilder style = new StringBuilder();
		style.append("padding-left:").append(Integer.toString(pageLevel * 20))
		    .append("px");
		return style.toString();
	}

	/**
	 *
	 * @return
	 */
	@Override
	public String writeCss()
	{
		StringBuilder css = new StringBuilder();
		css.append(".page{padding-left:20px}");
		css.append("\n");
		css.append("");
		css.append("\n");
		css.append("");
		css.append("\n");
		css.append("");
		css.append("\n");
		css.append("");
		return css.toString();

	}

//        private static String generateHTML() {
//        return html(generateBody()).renderFormatted();
//    }
//
//    private static ContainerTag generateForm() {
//        return form().withMethod("post").withAction("/yourServlet").with(generateUserField(), generatePasswordField());
//    }
//
//    private static Tag generateUserField() {
//        return input().withType("text").withName("user").withId("user");
//    }
//
//    private static ContainerTag generetaH1Title() {
//        return h1("Hello World - Body!");
//    }
//
//    private static Tag generatePasswordField() {
//        return input().withType("password").withName("password").withId("password");
//    }
	/**
	 *
	 * @return
	 */
	@Override
	public DomContent writeHtmlControls()
	{
		return null;
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
