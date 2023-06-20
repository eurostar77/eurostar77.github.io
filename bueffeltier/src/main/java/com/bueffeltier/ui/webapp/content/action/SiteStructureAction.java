package com.bueffeltier.ui.webapp.content.action;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import com.bueffeltier.logic.foundation.pagetree.SiteRepository;

/**
 *
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
public class SiteStructureAction extends AbstractAction
{
	private boolean showMsgBox;

	private static SiteStructureAction instance;

	private SiteRepository siteRepository = SiteRepository.getInstance();

	private SiteStructureAction()
	{
		super();
	}

	public static SiteStructureAction getInstance(HttpServletRequest request)
	{
		if (instance == null)
		{
			instance = new SiteStructureAction();
		}
		return instance;
	}

	@Override
	public void execute(HttpServletRequest request)
	{
		String testString = "test";

		Enumeration<String> parameterNames = request.getParameterNames();

		while (parameterNames.hasMoreElements())
		{
			String actionParameterName = parameterNames.nextElement()
			    .toString();

			switch (actionParameterName) {

			case "editPage":

				String pageEditIdString = request.getParameter("editPage");

				Long pageEditId = Long.parseLong(pageEditIdString);

				forwardViewData(request, "pageId", pageEditId);

				forwardToPage(request, "/edit-page");
				break;

//			case "expand":
//
//				ArrayList<Integer> expandedArticlePages;
//				try
//				{
//					expandedArticlePages = (ArrayList) this
//							.getContentAttributeValue("expandedArticlePages");
//				} catch (Exception ex)
//				{
//					expandedArticlePages = new ArrayList<>();
//
//					attrExpandedArticlePages = new ContentAttributeBuilder(
//							"expandedArticlePages", expandedArticlePages,
//							this.contentId, this.threadId
//					).persistsWhileSession(true).buildContentAttribute();
//				}
//
//				int expand = Integer.parseInt(getRequestParameter("expand"));
//
//				expandedArticlePages.add(expand);
//
//				// Buttons anpassen:
//				if (expandedArticlePages.size() > 0
//						&& expandedArticlePages.size() < pageTree.size())
//				{
//					disableExpandAllArticlesButton = false;
//					disableCollapseAllArticlesButton = false;
//				} else if (expandedArticlePages.size() == pageTree.size())
//				{
//					disableExpandAllArticlesButton = true;
//					disableCollapseAllArticlesButton = false;
//				} else if (expandedArticlePages.isEmpty())
//				{
//					disableExpandAllArticlesButton = false;
//					disableCollapseAllArticlesButton = true;
//				}
//
//				this.setContentAttribute(attrExpandedArticlePages);
//
//				this.setForwardingPage("/site-structure");
//				break;
//
//			case "collapse":
//
//				ArrayList<Integer> expandedArticlePages1 = new ArrayList<>();
//				try
//				{
//					expandedArticlePages1 = (ArrayList) this
//							.getContentAttributeValue("expandedArticlePages");
//				} catch (Exception ex)
//				{
//					Logger.getLogger(SiteStructureAction.class.getName())
//							.log(Level.SEVERE, null, ex);
//				}
//
//				int collapse = Integer
//						.parseInt(getRequestParameter("collapse"));
//
//				// Ausgewählte Seite kollabieren:
//				for (int i = 0; i < expandedArticlePages1.size(); i++)
//				{
//					if (expandedArticlePages1.get(i) == collapse)
//					{
//						expandedArticlePages1.remove(i);
//					}
//				}
//
//				// Buttons anpassen:
//				if (expandedArticlePages1.size() > 0
//						&& expandedArticlePages1.size() < pageTree.size())
//				{
//					disableExpandAllArticlesButton = false;
//					disableCollapseAllArticlesButton = false;
//				} else if (expandedArticlePages1.isEmpty())
//				{
//					disableExpandAllArticlesButton = false;
//					disableCollapseAllArticlesButton = true;
//				} else if (expandedArticlePages1.size() == pageTree.size())
//				{
//					disableExpandAllArticlesButton = true;
//					disableCollapseAllArticlesButton = false;
//				}
//
//				ContentAttribute attrExpandedArticlePages1 = new ContentAttributeBuilder(
//						"expandedArticlePages", expandedArticlePages1,
//						this.contentId, this.threadId
//				).persistsWhileSession(true).buildContentAttribute();
//				this.setContentAttribute(attrExpandedArticlePages1);
//
//				this.setForwardingPage("/site-structure");
//				break;
//
//			case "expandAllArticles":
//
//				// todo: später könnte es eine maximale Anzahl von aufklappbaren
//				// Artikeln geben, denn
//				// ich plane eine Artikel- oder Seitenansicht einzubauen.
//				// ArrayList "expandedArticlePages aus Session laden:
//				ArrayList<Integer> expandedArticlePages2;
//				try
//				{
//					expandedArticlePages2 = (ArrayList) this
//							.getContentAttributeValue("expandedArticlePages");
//				} catch (Exception ex)
//				{
//					expandedArticlePages2 = new ArrayList<>();
//
//				}
//
//				// Ausgewählte Seiten expandieren:
//				expandedArticlePages2.clear();
//				for (int i = 0; i < pageTree.size(); i++)
//				{
//					expandedArticlePages2.add(pageTree.get(i).getId());
//				}
//
//				// Buttons anpassen:
//				disableExpandAllArticlesButton = true;
//				disableCollapseAllArticlesButton = false;
//
//				ContentAttribute attrExpandedArticlePages2 = new ContentAttributeBuilder(
//						"expandedArticlePages", expandedArticlePages2,
//						this.contentId, this.threadId
//				).persistsWhileSession(true).buildContentAttribute();
//				this.setContentAttribute(attrExpandedArticlePages2);
//
//				this.setForwardingPage("/site-structure");
//				break;
//
//			case "collapseAllArticles":
//
//				// ArrayList "expandedArticlePages aus Session laden:
//				ArrayList<Integer> expandedArticlePages3 = new ArrayList<>();
//				try
//				{
//					expandedArticlePages3 = (ArrayList) this
//							.getContentAttributeValue("expandedArticlePages");
//				} catch (Exception ex)
//				{
//					// todo: schreibe expadedPages neu, als leere liste
//					// passe die buttons entsprechend an.
//					Logger.getLogger(SiteStructureAction.class.getName())
//							.log(Level.SEVERE, null, ex);
//				}
//
//				// Ausgewählte Seiten kollabieren, danach Buttons anpassen:
//				if (expandedArticlePages3.size() > 0
//						&& expandedArticlePages3.size() < pageTree.size())
//				{
//					expandedArticlePages3.clear();
//					disableExpandAllArticlesButton = false;
//					disableCollapseAllArticlesButton = true;
//				} else if (expandedArticlePages3.size() == pageTree.size())
//				{
//					expandedArticlePages3.clear();
//					disableExpandAllArticlesButton = false;
//					disableCollapseAllArticlesButton = true;
//				} else if (expandedArticlePages3.isEmpty())
//				{
//					for (int loopVar = 0; loopVar < pageTree.size(); loopVar++)
//					{
//						expandedArticlePages3
//								.add(pageTree.get(loopVar).getId());
//					}
//					disableExpandAllArticlesButton = false;
//					disableCollapseAllArticlesButton = true;
//				}
//
//				ContentAttribute attrExpandedArticlePages3 = new ContentAttributeBuilder(
//						"expandedArticlePages", expandedArticlePages3,
//						this.contentId, this.threadId
//				).persistsWhileSession(true).buildContentAttribute();
//				this.setContentAttribute(attrExpandedArticlePages3);
//
//				this.setForwardingPage("/site-structure");
//				break;
//
//			case "clearTray":
//
//				// todo: alle nicht benötigten, zu einer aktion gehörigen,
//				// atttribute löschen.
//				// todo: was passiert mit attributen in der zwischenablage
//				// (sessin)?
//				// kann man alle attribute, die aus einer id kommen oder auf
//				// eine
//				// id verweisen löschen?
//				ContentAttribute attrInfo4 = new ContentAttributeBuilder(
//						"info", "", this.contentId, this.threadId
//				).addressedContentId(23).buildContentAttribute();
//				this.setContentAttribute(attrInfo4);
//
//				setForwardingPage("/site-structure");
//				break;
//
//			case "newPage":
//
//				ContentAttribute attrMovePage = new ContentAttributeBuilder(
//						"movePage", "new", this.contentId, this.threadId
//				).addressedContentId(23).lifetime(2).buildContentAttribute();
//				this.setContentAttribute(attrMovePage);
//
//				ContentAttribute attrInfo3 = new ContentAttributeBuilder(
//						"info", "Neue Seite an gewünschter Stelle einfügen!",
//						this.contentId, this.threadId
//				).addressedContentId(23).lifetime(2).buildContentAttribute();
//				this.setContentAttribute(attrInfo3);
//
//				disableEmptyTrayButton = false;
//				disableNewPageButton = true;
//				disableNewArticleButton = true;
//				disableExpandAllArticlesButton = false;
//
//				disableEditPageButton = true;
//				disableMovePageButton = true;
//				disableDeletePageButton = true;
//				disablePublishPageButton = true;
//				disableInsertBehindPageButton = false;
//				disableInsertIntoPageButton = false;
//
//				// todo: hier könnte direkt eine view angesprochen werden:
//				// z.B.:
//				// this.view.setInsertButtonText.setName("insertNewPageBehind")
//				this.actionNameInsertBehindPageButton = "insertNewPageBehind";
//				this.actionNameInsertIntoPageButton = "insertNewPageInto";
//
//				setForwardingPage("/site-structure");
//				break;
//
//			case "movePage":
//				int pageToMove = Integer
//						.parseInt(getRequestParameter("movePage"));
//
//				ContentAttribute attrInsertPage = new ContentAttributeBuilder(
//						"insertPage", pageToMove, this.contentId, this.threadId
//				).addressedContentId(23).buildContentAttribute();
//				this.setContentAttribute(attrInsertPage);
////                    setContentAttribute("insertPage", pageToMove,2);
//
//				ContentAttribute attrInfo2 = new ContentAttributeBuilder(
//						"info",
//						"Seite-Id: " + pageToMove
//								+ " an gewünschter Stelle einfügen!",
//						this.contentId, this.threadId
//				).lifetime(1).buildContentAttribute();
//				this.setContentAttribute(attrInfo2);
//
//				disableEmptyTrayButton = false;
//				disableNewPageButton = true;
//
//				disableEditPageButton = true;
//				disableMovePageButton = true;
//				disableDeletePageButton = true;
//				disablePublishPageButton = true;
//				// todo: Zu verschiebende Seite komplett deaktivieren!
//				disableInsertBehindPageButton = false;
//				disableInsertIntoPageButton = false;
//
//				setForwardingPage("/site-structure");
//				break;
//
//			case "deletePage":
//				// todo: Abfrage ob löschen?
//				// message box für alle seiten gleich
//				// msgbox ruft finallydelete auf!
////                    x HTML MessageBox
//
//				break;
//
//			case "finalyDeletePage":
//				JOptionPane.showMessageDialog(
//						null, "ACHTUNG - SEITE WIRD ENDGÜLTIG GELÖSCHT!"
//				);
//				int deletePageId = Integer
//						.parseInt(getRequestParameter("finalyDeletePage"));
////                    JOptionPane.showMessageDialog(null, deletePageId);
//				this.pageTreeController.deletePage(deletePageId);
//
//				this.setForwardingPage("/site-structure");
//
//			case "publishPage":
//
//				try
//				{
//					// todo: db status setzen und anzeige ändern.
//					pageTreeController.getPageUnchecked(
//							(int) this.getContentAttributeValue("publishPage")
//					);
//				} catch (Exception ex)
//				{
//					Logger.getLogger(SiteStructureAction.class.getName())
//							.log(Level.SEVERE, null, ex);
//				}
////                    if is if not... todo:
//				break;
//			case "insertPageBehind":
//				int pageToInsertBehindAnother = 0; // das mit der Null gefällt
//													// mir nicht!
//				try
//				{
//					pageToInsertBehindAnother = (Integer) (getContentAttributeValue(
//							"insertPage"
//					));
//				} catch (Exception ex)
//				{
//
//				}
//				int newLeadingPageId = Integer
//						.parseInt(getRequestParameter("insertPageBehind"));
//
//				LOGGER.debug(
//						"Füge Seite " + pageToInsertBehindAnother
//								+ " nach Seite "
//								+ getRequestParameter("insertPageBehind")
//								+ " ein"
//				);
//				if (pageToInsertBehindAnother != newLeadingPageId)
//				{
//					if (pageTreeController.movePageBehindPage(
//							pageToInsertBehindAnother, newLeadingPageId
//					))
//					{
//						// Ansicht der aktualisierten Seite erstellen.
//						// ...entfällt, da die ansicht wieder standard ist!
//						pageTree = pageTreeController.getPageTree(1, 99);
//					} else
//					{
//						disableEmptyTrayButton = true;
//						disableNewPageButton = false;
//
//						disableEditPageButton = false;
//						disableEditPageButton = false;
//						disableMovePageButton = false;
//						disableDeletePageButton = false;
//						disablePublishPageButton = false;
//						disableInsertBehindPageButton = true;
//						disableInsertIntoPageButton = true;
//					}
//				}
//				this.setForwardingPage("/site-structure");
//				break;
//
//			case "insertPageInto":
//
//				int pageToInsertIntoAnother = 0; // das mit der Null gefällt mir
//													// nicht!
//				try
//				{
//					pageToInsertIntoAnother = (Integer) getContentAttributeValue(
//							"insertPage"
//					);
//				} catch (Exception ex)
//				{
//
//				}
//				int newParentPageId = Integer
//						.parseInt(getRequestParameter("insertPageInto"));
//
//				LOGGER.debug(
//						"Füge Seite " + pageToInsertIntoAnother + " in Seite "
//								+ getRequestParameter("insertPageInto") + " ein"
//				);
//				if (pageToInsertIntoAnother != newParentPageId)
//				{
//					if (pageTreeController.movePageIntoPage(
//							pageToInsertIntoAnother, newParentPageId
//					))
//					{
//						// Normale Ansicht wird normal geladen.
//						pageTree = pageTreeController.getPageTree(1, 99);
//					} else
//					{
//						disableEmptyTrayButton = true;
//						disableNewPageButton = false;
//						disableEditPageButton = false;
//						disableEditPageButton = false;
//						disableMovePageButton = false;
//						disableDeletePageButton = false;
//						disablePublishPageButton = false;
//						disableInsertBehindPageButton = true;
//						disableInsertIntoPageButton = true;
//					}
//				}
//				this.setForwardingPage("/site-structure");
//				break;
//
//			case "insertNewPageBehind":
//				int newPagesNewLeadingPageId = Integer
//						.parseInt(getRequestParameter("insertNewPageBehind"));
//
//				ContentAttribute attrNewLeadingPage = new ContentAttributeBuilder(
//						"newLeadingPage", newPagesNewLeadingPageId,
//						this.contentId, this.threadId
//				).addressedContentId(26).buildContentAttribute();
//				this.setContentAttribute(attrNewLeadingPage);
//
//				Page newPageBehind = new Page("");
//
//				ContentAttribute attrPage = new ContentAttributeBuilder(
//						"page", newPageBehind, this.contentId, this.threadId
//				).addressedContentId(26)
//						// .lifetime(2)
//						.buildContentAttribute();
//				this.setContentAttribute(attrPage);
//
//				this.setForwardingPage("/edit-page");
//				break;
//
//			case "insertNewPageInto":
//				int newPagesNewParentPageId = Integer
//						.parseInt(getRequestParameter("insertNewPageInto"));
//
//				ContentAttribute attr = new ContentAttributeBuilder(
//						"newParentPage", newPagesNewParentPageId,
//						this.contentId, this.threadId
//				).addressedContentId(26).lifetime(2).buildContentAttribute();
//				this.setContentAttribute(attr);
//
//				Page newPageInto = new Page("");
//
//				ContentAttribute attrPage1 = new ContentAttributeBuilder(
//						"page", newPageInto, this.contentId, this.threadId
//				).addressedContentId(26).lifetime(2).buildContentAttribute();
//				this.setContentAttribute(attrPage1);
//
//				this.setForwardingPage("/edit-page");
//				break;

//			case "addArticle":
//				// ?todo: Infotext muss in Attribute gespeichert werden!
//				// ?todo: Status "einfügen" bleibt bestehen bis:
//				// 1. Das ContentElement verlassen wird.
//				// 2. Die Zwischenablage geleert wird.
//				// 3. Der neue Artikel eingefügt wurde.
//				// Der Sinn dahinter: Oft muss man zuerst die Artikel
//				// zur ausgewählten Seite ausklappen.
//				// allgemein sollte ich mir notieren, wo die gestzten attribute
//				// später wieder aufgerufen werden. -> Checkliste
//				// todo: auch die Seitenansicht sollte dann so lange als
//				// "einfügen" dargestellt werden.
////                    ContentAttribute attrAddArticle = new AttributeBuilder()
////                            .key("moveArticle")                                    //    rename? "movePage" ?
////                            .value("new")
////                            .originContentId(23)                                //  nach classname auswählen ???
////                            .addressedContentId(23)
////                            .lifetime(2) // todo: wenn lifetime = null nicht löschen!
////                            .buildContentAttribute();
////                    this.setContentAttribute(attrAddArticle);
//				String info = "Neuen Artikel an gewünschter Stelle einfügen!";
//				ContentAttribute attrInfo = new ContentAttributeBuilder(
//						"info", info, this.contentId, this.threadId
//				).addressedContentId(23).buildContentAttribute();
//				this.setContentAttribute(attrInfo);
//
//				disableEmptyTrayButton = false;
//				disableNewPageButton = true;
//				disableNewArticleButton = true;
//				disableExpandAllArticlesButton = false;
//
//				disableEditPageButton = true;
//				disableMovePageButton = true;
//				disableDeletePageButton = true;
//				disablePublishPageButton = true;
//				disableInsertBehindPageButton = true;
//				disableInsertIntoPageButton = false;
//
//				disableEditArticleButton = true;
//				disableMoveArticleButton = true;
//				disableDeleteArticleButton = true;
//				disablePublishArticleButton = true;
//				disableInsertBehindArticleButton = false;
//
////                    this.actionNameInsertBehindPageButton = "insertNewArticleBehind"; // todo: rename Button!
//				this.actionNameInsertIntoPageButton = "insertNewArticleIntoPage";
//				this.actionNameInsertBehindArticleButton = "insertNewArticleBehindArticle";
//
//				setForwardingPage("/site-structure");
//
//				break;
//
//			case "insertNewArticleIntoPage":
//
//				int pageToInsertNewArticleInto = Integer.parseInt(
//						getRequestParameter("insertNewArticleIntoPage")
//				);
//
//				Article articleInto = new Article();
//				articleInto.setTitle("attrTitle"); // todo: zeile löschen!
//				articleInto.setParentId(pageToInsertNewArticleInto);
//
//				ContentAttribute attrArticle = new ContentAttributeBuilder(
//						"article", articleInto, this.contentId, this.threadId
//				).addressedContentId(27).buildContentAttribute();
//				this.setContentAttribute(attrArticle);
//
//				ContentAttribute attrNewParentPage = new ContentAttributeBuilder(
//						"newParentPage", pageToInsertNewArticleInto,
//						this.contentId, this.threadId
//				).addressedContentId(27).buildContentAttribute();
//				this.setContentAttribute(attrNewParentPage);
//
//				this.setForwardingPage("/edit-article"); // todo: forwaring
//															// element? auch
//															// abfrage
//															// element=seite
//															// automatisch
//				break;
//
//			case "insertNewArticleBehindArticle":
//				int newArticlesNewLeadingArticleId = Integer.parseInt(
//						getRequestParameter("insertNewArticleBehindArticle")
//				);
////                    int articleToInsertNewArticleBehind = Integer.parseInt(
////                            getRequestParameter("insertNewArticleBehind"));
//				Article newArticle = new Article();
//
//				int newArticlesOrder = this.pageTreeController
//						.getArticle(newArticlesNewLeadingArticleId).getOrder()
//						+ 1;
//				newArticle.setOrder(newArticlesOrder);
//
//				ContentAttribute attrArticle1 = new ContentAttributeBuilder(
//						"article", newArticle, this.contentId, this.threadId
//				).addressedContentId(27).buildContentAttribute();
//				this.setContentAttribute(attrArticle1);
//
//				ContentAttribute attrNewLeadingArticle = new ContentAttributeBuilder(
//						"newInsertedArticle", newArticlesNewLeadingArticleId,
//						this.contentId, this.threadId
//				).addressedContentId(27).buildContentAttribute();
//				this.setContentAttribute(attrNewLeadingArticle);
//
//				this.setForwardingPage("/edit-article"); // todo: forwarding
//															// element? auch
//															// abfrage
//															// element=seite
//															// automatisch
//
//				break;
//
//			case "editArticle":
//				/*
//				 * Jede ausgelieferte Seite bekommt eine einzigartige ID. kommt
//				 * ein request von einer ID, die bereits eingelöst wurde, so
//				 * wird die anfrage verworfen. direkte eingeabe der url fragt
//				 * ohne id an, d.h. es wird eine neue id erzeugt.
//				 * 
//				 */
////                    todo: thread wird nicht weitergegeben, wegen redirect! beachte das bei allen parametern.
////                füge eine redirect speicherschicht hinzu
////                !
//
////                    rückfallseite wenn action nicht funktioniert, -dann wird rückfall geladen. try catch und throws
////
////                        zugriff auf contentAttribute aus dem handler ist nur möglich,
////                        wenn thread passt, die contentId oder die addressedContentId
////
////                    lifetime um 1 höher initialisieren
////                    ?
////                    lifetime expanded ArticlePages = permanent.
////
////                    todo: contentElement als id oder generell als handler "xy" ansprechbar.
////
////                    !
//				int editArticleId = Integer
//						.parseInt(getRequestParameter("editArticle"));
//
//				ContentAttribute attrEditArticleId = new ContentAttributeBuilder(
//						"editArticleId", editArticleId, this.contentId,
//						this.threadId
//				).addressedContentId(30).buildContentAttribute();
//				attrEditArticleId.increaseLifetime(1);
//
//				this.setContentAttribute(attrEditArticleId);
//
//				Article article;
//				try
//				{
//					article = pageTreeController.getArticle(editArticleId);
//
//					ContentAttribute attrArticle2 = new ContentAttributeBuilder(
//							"article", article, this.contentId, this.threadId
//					).addressedContentId(30).buildContentAttribute();
//					attrArticle2.increaseLifetime(1);
//
//					this.setContentAttribute(attrArticle2); // todo:
//															// ContentElemente
//															// auch mit deren
//															// Class-Name
//															// ansprechbar
//															// machen!
//
//					this.setForwardingPage("/edit-article-elements");
//
//				} catch (Exception ex)
//				{
//					Logger.getLogger(SiteStructureAction.class.getName())
//							.log(Level.SEVERE, null, ex);
//				}
//
//				break;
//
//			case "finalyDeleteArticle":
//
//				int deleteArticleId = Integer
//						.parseInt(getRequestParameter("finalyDeleteArticle"));
//
////                    ContentAttribute attrEditArticle = new AttributeBuilder()
////                            .key("editArticle")
////                            .value(editArticleId)
////                            .originContentId(23)
////                            .addressedContentId(27)
////                            .buildContentAttribute();
////                        this.setContentAttribute(attrEditArticle);
////                    this.showMsgBox = true;
//				this.pageTreeController.deleteArticle(deleteArticleId);
//
//				this.setForwardingPage("/site-structure");
//
//				break;
//
//			case "moveArticle":
//
////                    zu verschiebenden artikel nicht als einfügeoption!
//				int articleToMove = Integer
//						.parseInt(getRequestParameter("moveArticle"));
//
//				ContentAttribute attrInsertArticle = new ContentAttributeBuilder(
//						"insertArticle", articleToMove, this.contentId,
//						this.threadId
//				).addressedContentId(23).lifetime(2).buildContentAttribute();
//				this.setContentAttribute(attrInsertArticle);
//
//				ContentAttribute attrInfo1 = new ContentAttributeBuilder(
//						"info", "Artikel an gewünschter Stelle einfügen!",
//						this.contentId, this.threadId
//				).addressedContentId(23).lifetime(2).buildContentAttribute();
//				this.setContentAttribute(attrInfo1);
//
//				this.disableEmptyTrayButton = false;
//				this.disableNewPageButton = true;
//				this.disableNewArticleButton = true;
//
//				this.disableEditPageButton = true;
//				this.disableMovePageButton = true;
//				this.disableDeletePageButton = true;
//				this.disablePublishPageButton = true;
//				this.disableInsertBehindPageButton = true;
//				this.disableInsertIntoPageButton = false;
//
//				this.disableEditArticleButton = true;
//				this.disableDeleteArticleButton = true;
//				this.disableMoveArticleButton = true;
//				this.disablePublishArticleButton = true;
//				this.disableInsertBehindArticleButton = false;
//
////                    this.actionNameInsertBehindPageButton = "insertArticleIntoPage";
//				this.actionNameInsertIntoPageButton = "insertArticleIntoPage";
//
//				setForwardingPage("/site-structure");
//				break;
//
//			case "publishArticle":
//
//				break;
//
//			case "insertBehindArticle":
//
//				int insertBehindArticle = Integer
//						.parseInt(getRequestParameter("insertBehindArticle"));
//
//				int articleToInsert = 0; // das mit der Null gefällt mir nicht!
//				try
//				{
//					articleToInsert = (Integer) (getContentAttributeValue(
//							"insertArticle"
//					));
//				} catch (Exception ex)
//				{
//
//				}
//
//				this.pageTreeController.moveArticleBehindArticle(
//						articleToInsert, insertBehindArticle
//				);
//
//				this.disableEmptyTrayButton = true;
//				this.disableNewPageButton = false;
//				this.disableNewArticleButton = false;
//
//				this.disableEditPageButton = false;
//				this.disableMovePageButton = false;
//				this.disableDeletePageButton = false;
//				this.disablePublishPageButton = false;
//				this.disableInsertBehindPageButton = true;
//				this.disableInsertIntoPageButton = true;
//
//				this.disableEditArticleButton = false;
//				this.disableDeleteArticleButton = false;
//				this.disableMoveArticleButton = false;
//				this.disablePublishArticleButton = false;
//				this.disableInsertBehindArticleButton = true;
//
//				ContentAttribute attrInfo5 = new ContentAttributeBuilder(
//						"info", "", this.contentId, this.threadId
//				).addressedContentId(23).buildContentAttribute();
//				this.setContentAttribute(attrInfo5);
//
//				setForwardingPage("/site-structure");
//
//				break;
//
//			case "insertArticleIntoPage":
//				int insertIntoPage = Integer
//						.parseInt(getRequestParameter("insertArticleIntoPage"));
//
//				int articleToInsert1 = 0; // das mit der Null gefällt mir nicht!
//				try
//				{
//					articleToInsert1 = (Integer) (getContentAttributeValue(
//							"insertArticle"
//					));
//				} catch (Exception ex)
//				{
//
//				}
//
//				this.pageTreeController
//						.moveArticleIntoPage(articleToInsert1, insertIntoPage);
//
//				this.disableEmptyTrayButton = true;
//				this.disableNewPageButton = false;
//				this.disableNewArticleButton = false;
//
//				this.disableEditPageButton = false;
//				this.disableMovePageButton = false;
//				this.disableDeletePageButton = false;
//				this.disablePublishPageButton = false;
//				this.disableInsertBehindPageButton = true;
//				this.disableInsertIntoPageButton = true;
//
//				this.disableEditArticleButton = false;
//				this.disableDeleteArticleButton = false;
//				this.disableMoveArticleButton = false;
//				this.disablePublishArticleButton = false;
//				this.disableInsertBehindArticleButton = true;
//
//				ContentAttribute attrInfo6 = new ContentAttributeBuilder(
//						"info", "", this.contentId, this.threadId
//				).addressedContentId(23).buildContentAttribute();
//				this.setContentAttribute(attrInfo6);
//
//				setForwardingPage("/site-structure");
//
//				break;
			}
		}
	}

	// ist etwas in der Zwischenablage?
	// was muss angezeigt werden?
	// wird die zwischenablage nach seitenwechsel automatisch gelöscht?
	// gekoppelte Sitzungsdaten lesen
	// Klasse für Navigation schreiben? z.B. Buttons und zustände.
	// schleifen-buttons benennen und gemeinsam freischalten oder einzeln.
//        HTMLDocument htmlDocument = new HTMLDocument();
//        htmlDocument.i
//        HTML html = new HTML();
	@Override
	public void doAjaxAction(HttpServletRequest request)
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public String pageContextStyle(int pageLevel)
	{
		StringBuilder style = new StringBuilder();
		style.append("padding-left:").append(Integer.toString(pageLevel * 20))
		    .append("px");
		return style.toString();
	}

}
