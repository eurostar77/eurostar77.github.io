//package com.bueffeltier.ui.webapp.content.view;
//
//import static com.bueffeltier.ui.html.molecule.customTag.*;
//import static j2html.TagCreator.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//
//import com.bueffeltier.data.hibernate.daoImpl.ArticleDaoImpl;
//import com.bueffeltier.data.hibernate.entity.Article;
//import com.bueffeltier.data.hibernate.entity.Page;
//import com.bueffeltier.data.jdbc.ElementJDBCFlat;
//import com.bueffeltier.logic.foundation.pagetree.PageTreeControllerOld;
//
//import j2html.tags.DomContent;
//
///**
// *
// * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
// */
//public class SiteStructureViewHandler extends AbstractView
//{
//
////    final String type = "PAGE_STRUCTURE";
//	// Daten für die aktuelle Seitenansicht.
////    private String  showAllArticlesButtonText = "Expand all Articles";
////    private String  collapseAllArticlesButtonText = ""
//	private boolean disableExpandAllArticlesButton;
//	private boolean disableCollapseAllArticlesButton;
//	private boolean disableEmptyTrayButton = true;
//	private boolean disableNewPageButton = false;
//	private boolean disableNewArticleButton = false;
//
//	private boolean disableEditPageButton = false;
//	private boolean disablePublishPageButton = false;
//	private boolean disableDeletePageButton = false;
//	private boolean disableMovePageButton = false;
//	private String actionNameInsertBehindPageButton = "insertPageBehind";
//	private boolean disableInsertBehindPageButton = true;
//	private String actionNameInsertIntoPageButton = "insertPageInto";
//	private boolean disableInsertIntoPageButton = true;
//
//	private boolean disableEditArticleButton = false;
//	private boolean disableMoveArticleButton = false;
//	private boolean disableDeleteArticleButton = false;
//	private boolean disablePublishArticleButton = false;
//
//	private String actionNameInsertBehindArticleButton = "insertBehindArticle";
//	private boolean disableInsertBehindArticleButton = true;
//
//	StringBuilder htmlString = new StringBuilder(); // todo: j2html braucht
//	                                                // keinen string builder!
//
//	PageTreeControllerOld pageTreeController = new PageTreeControllerOld();
//	ArrayList<Page> pageTree;
//
//	private boolean showMsgBox;
//
//	private static SiteStructureViewHandler instance;
//
//	private SiteStructureViewHandler()
//	{
//		super();
//	}
//
//	public static SiteStructureViewHandler getInstance()
//	{
//		if (instance == null)
//		{
//			instance = new SiteStructureViewHandler();
//		}
//		return instance;
//	}
//
//	// ist etwas in der Zwischenablage?
//	// was muss angezeigt werden?
//	// wird die zwischenablage nach seitenwechsel automatisch gelöscht?
//	// gekoppelte Sitzungsdaten lesen
//	// Klasse für Navigation schreiben? z.B. Buttons und zustände.
//	// schleifen-buttons benennen und gemeinsam freischalten oder einzeln.
////        HTMLDocument htmlDocument = new HTMLDocument();
////        htmlDocument.i
////        HTML html = new HTML();
//
//	@Override
//	public DomContent
//	    writeHtml(ElementJDBCFlat element, HttpServletRequest request)
//	        throws Exception // todo: throw exception!
//	{
//
//		this.requestController.contentAttributeSet
//		    .logCurrentAttributes("current attributes:");
//
//		ArrayList<Integer> expandedArticlePages;
//		try
//		{
//			expandedArticlePages = (ArrayList) this
//			    .getContentAttributeValue("expandedArticlePages");
//		} catch (Exception ex)
//		{
//			expandedArticlePages = new ArrayList<>();
//		}
//
//		// Sind alle Artikel expandiert oder collapased?
//		if (expandedArticlePages.isEmpty())
//		{
//			disableExpandAllArticlesButton = false;
//			disableCollapseAllArticlesButton = true;
//		} else if (expandedArticlePages.size() == pageTree.size())
//		{
//			disableExpandAllArticlesButton = true;
//			disableCollapseAllArticlesButton = false;
//		} else
//		{
//			disableExpandAllArticlesButton = false;
//			disableCollapseAllArticlesButton = false;
//		}
//		return form(
//		    this.contentId, this.threadId, controllGroup(),
//		    structureGroup(expandedArticlePages)
//		);
//		// todo: index seite nicht kopierbar machen!
//	}
//
//	/**
//	 *
//	 * @return
//	 */
//	public DomContent controllGroup()
//	{
//		return div().withClass("controlls").with(
//		    infoField(),
//		    submitButtonSwitchable(
//		        "Expand all Articles Button2", "expandAllArticles",
//		        "expandAllArticles", this.disableExpandAllArticlesButton
//		    ),
//		    submitButtonSwitchable(
//		        "Collapse all Articles Button", "collapseAllArticles",
//		        "collapseAllArticles", this.disableCollapseAllArticlesButton
//		    ),
//		    submitButtonSwitchable(
//		        "Clear Tray", "clearTray", "clearTray",
//		        this.disableEmptyTrayButton
//		    ),
//		    submitButtonSwitchable(
//		        "Add new Page", "newPage", "newPage", this.disableNewPageButton
//		    ),
//		    submitButtonSwitchable(
//		        "Add new Article", "addArticle", "addArticle",
//		        disableNewArticleButton
//		    )
//		);
//	}
//
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
//
//	/**
//	 *
//	 * @param expandedArticlePages
//	 * @return
//	 */
//	public DomContent structureGroup(ArrayList<Integer> expandedArticlePages)
//	{
//		return div(
//		    // todo: id für einrückung
//		    attrs(".structure"),
//		    each(
//		        pageTree,
//		        page -> div(
//		            attrs(".page-context"), pageRow(page, expandedArticlePages),
//		            // each page
//		            articleRows(page.getId(), expandedArticlePages)
//		        ).withStyle(pageContextStyle(page.getLevel()))
//		    )
//		);
//	}
//
//	/**
//	 *
//	 * @param page
//	 * @param expandedArticlePages
//	 * @return
//	 */
//	public DomContent
//	    pageRow(Page page, ArrayList<Integer> expandedArticlePages)
//	{
//		return div().withClass("page-row").with(
//		    pageTitle(page.getId(), page.getHtmlTitle()),
//		    pageButtons(page, expandedArticlePages)
//		);
//	}
//
//	/**
//	 *
//	 * @param pageId
//	 * @param pageTitle
//	 * @return
//	 */
//	public DomContent pageTitle(int pageId, String pageTitle)
//	{
//		return p("ID: " + Integer.toString(pageId) + " - " + pageTitle)
//		    .withClass("page-title");
//	}
//
//	/**
//	 *
//	 * @param page
//	 * @param expandedArticlePages
//	 * @return
//	 */
//	public DomContent
//	    pageButtons(Page page, ArrayList<Integer> expandedArticlePages)
//	{
//		String pageIdString = Integer.toString(page.getId());
//		return div().with(
//		    expandButton(page.getId(), expandedArticlePages),
//		    submitButtonSwitchable(
//		        "Edit Page", "editPage", pageIdString, disableEditPageButton
//		    ),
//		    submitButtonSwitchable(
//		        "Publish Page", "publishPage", pageIdString,
//		        disablePublishPageButton
//		    ),
//		    // todo: HTML-Elemente als Bibliothek aufbauen!
//		    // Seiten-Dialog für verscheidene Messages und auch ohne JS
//		    // aufbauen.
//		    // submitButtonSwitchable("Delete Page", "deletePage",
//		    // pageIdString, disableDeletePageButton),
//		    // clientSideActionButtonSwitchable("Delete Page",
//		    // "finalyDeletePage", pageIdString, disableDeletePageButton,
//		    // page.getId(), "showDeletePageDialog"),
//		    submitButtonSwitchable(
//		        "Delete Page", "finalyDeletePage", pageIdString,
//		        disableDeletePageButton
//		    ), // todo: hier wieder client side dialog einsetzen.
//		    submitButtonSwitchable(
//		        "Move Page", "movePage", pageIdString, disableMovePageButton
//		    ),
//		    submitButtonSwitchable(
//		        "Insert Behind Page", actionNameInsertBehindPageButton,
//		        pageIdString, disableInsertBehindPageButton
//		    ),
//		    submitButtonSwitchable(
//		        "Insert Into Page", actionNameInsertIntoPageButton,
//		        pageIdString, disableInsertIntoPageButton
//		    ),
//		    submitButtonSwitchable(
//		        "Insert Article", "insertNewArticleIntoPage", pageIdString,
//		        false
//		    )
//		).withClass("page-buttons");
//	}
//
//	/**
//	 *
//	 * @param pageId
//	 * @param expandedArticlePages
//	 * @return
//	 */
//	public DomContent
//	    expandButton(int pageId, ArrayList<Integer> expandedArticlePages)
//	{
//
//		if (expandedArticlePages.contains(pageId))
//		{
//			return submitButton("-", "collapse", Integer.toString(pageId));
//		} else
//		{
//			return submitButton("+", "expand", Integer.toString(pageId));
//		}
//	}
//
//	/**
//	 *
//	 * @param pageId
//	 * @param expandedArticlePages
//	 * @return
//	 */
//	public DomContent
//	    articleRows(int pageId, ArrayList<Integer> expandedArticlePages)
//	{
//		// todo: hier zeit messen!
//		ArticleDaoImpl articleDaoImpl = new ArticleDaoImpl(); // todo: global
//		                                                      // definieren
//		                                                      // oder zugriff
//		                                                      // über
//		                                                      // pageTreeController.
//		List<Article> articles = articleDaoImpl.findAllAsc(pageId);
//
//		if (expandedArticlePages.contains(pageId))
//		{
//			return // todo: iff einführen
//			each(
//			    articles,
//			    article -> div(
//			        articleTitle(article.getTitle()),
//			        articleButtonGroup(article.getId())
//			    ).withClass("article-row")
//			);
//		} else
//		{
//			return null;
//		}
//	}
//
//	/**
//	 *
//	 * @param articleTitle
//	 * @return
//	 */
//	public DomContent articleTitle(String articleTitle)
//	{
//		return p(articleTitle).withClass("article-title");
//	}
//
//	/**
//	 *
//	 * @param articleId
//	 * @return
//	 */
//	public DomContent articleButtonGroup(int articleId)
//	{
//		String articleIdString = Integer.toString(articleId);
//		return div().withClass("article-buttons").with(
//		    submitButtonSwitchable(
//		        "Edit Article", "editArticle", articleIdString,
//		        disableEditArticleButton
//		    ),
//		    // todo: Wenn kein JS-dann muss die Message Box als extra Seite
//		    // oder als Info-Feld direkt bei dem Button.
//		    // Anker auf der Seite helfen, dabei, dass die INfo auch gelesen
//		    // wird.
//		    // Vielleicht wird die Seite dann auch entsprechend css
//		    // formatiert geladen, so dasss die info im vordergrund steht.
//		    // submitButtonSwitchable("Delete Article", "deleteArticle",
//		    // articleIdString, disableDeleteArticleButton),
//		    clientSideActionButtonSwitchable(
//		        "Delete Article", "deleteArticle", articleIdString,
//		        disableDeleteArticleButton, articleId, "showDialog"
//		    ),
//		    submitButtonSwitchable(
//		        "Move Article", "moveArticle", articleIdString,
//		        disableMoveArticleButton
//		    ),
//		    submitButtonSwitchable(
//		        "Publish Article", "publishArticle", articleIdString,
//		        disablePublishArticleButton
//		    ),
//		    submitButtonSwitchable(
//		        "Insert Behind Article", actionNameInsertBehindArticleButton,
//		        articleIdString, disableInsertBehindArticleButton
//		    ), dlgBackground(), dlgBox(articleId)
//		);
//	}
//
//	/**
//	 *
//	 * @return
//	 */
//	public DomContent dlgBackground()
//	{
////        if(showMsgBox==true){
//		return div().attr("id=\"white-background\"");
////        }else{
////            return div(
////                script("dlgHide()")
////            );
////        }
//	}
//
//	/**
//	 *
//	 * @param articleId
//	 * @return
//	 */
//	public DomContent dlgBox(int articleId)
//	{
////        if(showMsgBox==true){
//		return div(
//		    div("Please confirm!").attr("id=\"dlg-header\""),
//		    div("Artikel unwiderruflich löschen?").attr("id=\"dlg-body\""),
//		    div(
//		        button().withText("ok").withName("finalyDeleteArticle")
//		            .withValue(Integer.toString(articleId)).withType("submit"), // .attr("onclick=\"dlgHide()\""),
//		        // submitButton("ok","finalyDeleteArticle",Integer.toString(articleId)),
//		        button("cancel").attr(
//		            "type = \"button\" onclick=\"dlgCancel(" + articleId + ")\""
//		        )
//				// button("cancel").attr("type = \"button\"
//				// onclick=\"dlgCancel(" + articleId + ")\"")
//		    ).attr("id=\"dlg-footer\"")
//		).attr("id=\"dlgbox-" + articleId + "\"");
////        }else{
////            return div();
////        }
//	}
//
//	/**
//	 *
//	 * @param pageLevel
//	 * @return
//	 */
//	public String pageContextStyle(int pageLevel)
//	{
//		StringBuilder style = new StringBuilder();
//		style.append("padding-left:").append(Integer.toString(pageLevel * 20))
//		    .append("px");
//		return style.toString();
//	}
//
//	/**
//	 *
//	 * @return
//	 */
//	@Override
//	public String writeCss()
//	{
//		StringBuilder css = new StringBuilder();
//		css.append(".page{padding-left:20px}");
//		css.append("\n");
//		css.append("");
//		css.append("\n");
//		css.append("");
//		css.append("\n");
//		css.append("");
//		css.append("\n");
//		css.append("");
//		return css.toString();
//
//	}
//
////        private static String generateHTML() {
////        return html(generateBody()).renderFormatted();
////    }
////
////    private static ContainerTag generateForm() {
////        return form().withMethod("post").withAction("/yourServlet").with(generateUserField(), generatePasswordField());
////    }
////
////    private static Tag generateUserField() {
////        return input().withType("text").withName("user").withId("user");
////    }
////
////    private static ContainerTag generetaH1Title() {
////        return h1("Hello World - Body!");
////    }
////
////    private static Tag generatePasswordField() {
////        return input().withType("password").withName("password").withId("password");
////    }
//	/**
//	 *
//	 * @return
//	 */
//	@Override
//	public DomContent writeHtmlControls()
//	{
//		return null;
//	}
//
//	@Override
//	protected void setCssId(String containerTagClassName)
//	{
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	protected void setContainerTag()
//	{
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public DomContent writeModalsOpt()
//	{
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public boolean hasModalOpt()
//	{
//		// TODO Auto-generated method stub
//		return false;
//	}
//}
