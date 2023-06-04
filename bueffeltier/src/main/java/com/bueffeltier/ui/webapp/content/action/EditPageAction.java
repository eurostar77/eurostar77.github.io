//package com.bueffeltier.ui.webapp.content.action;
//
//import java.util.ArrayList;
//import java.util.Enumeration;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//import javax.servlet.http.HttpServletRequest;
//
//import com.bueffeltier.data.hibernate.entity.Page;
//import com.bueffeltier.logic.foundation.pagetree.PageTreeControllerOld;
//import com.bueffeltier.ui.webapp.content.view.HeaderView;
//
///**
// *
// * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
// */
//public class EditPageAction extends AbstractAction
//{
//
//	private String info;
//	private boolean noFollow;
//	private boolean noIndex;
//	private String seitenName;
//	private String seitenAlias;
//	private String seitenTyp;
//	private ArrayList<String> layouts;
//	private String seitenTitel;
//	private String indexFollow;
//
//	private String description;
//	private String keywords;
//	private String pageType;
//	private int permission;
//	private String cacheTime;
//	private int layoutNo;
//
//	private boolean showInNav;
//
//	private boolean publish;
//
//	private static HeaderView instance;
//
//	private EditPageAction()
//	{
//		super();
//	}
//
//	public static EditPageAction getInstance()
//	{
//		if (instance == null)
//		{
//			instance = new EditPageAction();
//		}
//		return instance;
//	}
//
//	@Override
//	public void execute(HttpServletRequest request)
//	{
//		Page actionPage = null;
//
//		/*
//		 * Das zu bearbeitende Page-Objekt aus den Session-Attributen beziehen:
//		 */
//		try
//		{
//			actionPage = (Page) this.getContentAttributeValue("page");
//
////            log.add(this, "doPostAction()", "Page-Objekt aus ContentAttribute geladen.");
//			if (actionPage == null)
//			{
////                log.add(this, "doPostAction()", "Geladenes Page-Objekt ist null.");
//			}
//		} catch (Exception ex)
//		{
////            log.add(this, "doPostAction()", "try - Page-Objekt aus ContentAttribute konnte nicht geladen werden.");
//			Logger.getLogger(EditPageAction.class.getName())
//					.log(Level.SEVERE, null, ex);
//
//		}
//
//		Enumeration parameterNames = this.getRequestParameterNames();
//
//		// todo: <- ggf. hier alle parameter loggen.
//		//
//		while (parameterNames.hasMoreElements())
//		{
//
//			// todo: zweite schleife, die nur save und exit behandelt,
//			// um die anderen daten gänzlich zu speichern.
//			switch (parameterNames.nextElement().toString()) {
//			case "preview":
//				// todo:
//				this.setForwardingPage("/preview");
//				// todo: Seite "preview" anlegen.
//				// rename method "setForwardingPage" = "doForward"
//				// "forwardToPage"
//				break;
//
//			case "page-name":
//				actionPage.setInternalName(getRequestParameter("page-name"));
//				break;
//
//			case "page-alias":
//				actionPage.setUrlAlias(getRequestParameter("page-alias"));
//				break;
//
//			case "page-type":
//				String pageTypeParam = getRequestParameter("page-type");
//				switch (pageTypeParam) {
//				case "regular-page":
//					actionPage.setPageType("RESPOND");
//					break;
//				case "int-fwd":
//					actionPage.setPageType("int-fwd");
//					break;
//				case "403":
//					actionPage.setPageType("403");
//					break;
//				case "404":
//					actionPage.setPageType("404");
//					break;
//				}
//				break;
//
//			case "page-title":
//				actionPage.setHtmlTitle(getRequestParameter("page-title"));
//				/*
//				 * Der Servlet-Path der Seite wird bei Update (wenn edit) oder
//				 * beim Einfügen (newPage) in den Seitenbaum über
//				 * PageTreeController geschrieben.
//				 */
//				break;
//
//			case "description":
//				actionPage.setDescription(getRequestParameter("description"));
//				break;
//
//			case "keywords":
//				actionPage.setKeywords(getRequestParameter("keywords"));
//				break;
//
//			case "robots":
//				actionPage.setNoFollow(
//						Boolean.parseBoolean(getRequestParameter("robots"))
//				);
//				// todo: methoden in actionPage überarbeiten.
//				// formular überarbeiten: mehrere Optionen!
//				break;
//
//			case "permission":
//				actionPage.setPermission(
//						Integer.parseInt(getRequestParameter("permission"))
//				);
//				break;
//
//			case "layout":
//				actionPage.setLayout(1); // todo: Layouts anbieten!
//				break;
//
//			case "cache-time": // todo:
//
//				break;
//
//			case "no-search": // todo: genauer definieren: no-search vs.
//								// hide-in-nav
//				actionPage.setHideInNav(
//						Boolean.parseBoolean(getRequestParameter("no-search"))
//				);
//				break;
//
//			case "css-class":
//				// todo:
//				break;
//
//			case "add-to-sitemap": // todo: hier boolean-wert abfragen?
//				actionPage
//						.setSitemapName(getRequestParameter("add-to-sitemap"));
//				break;
//
//			case "hide-in-nav": // todo: genauer definieren: no-search vs.
//								// hide-in-nav
//				actionPage.setHideInNav(
//						Boolean.parseBoolean(getRequestParameter("hide-in-nav"))
//				);
//				break;
//
//			case "publish":
//				actionPage.setPublished(
//						Boolean.parseBoolean(getRequestParameter("publish"))
//				);
//				break;
//
//			case "forward-to": // todo:
////                    actionPage.setForwardDestination("forwardDestination");
//				break;
//
//			case "save-and-close":
////                    JOptionPane.showMessageDialog(null, actionPage.toString());
//				doSave(actionPage);
//				setForwardingPage("/admin");
//				break;
//
//			case "save":
//				doSave(actionPage);
//				setForwardingPage("/edit-page"); // todo: boolean funktioniert
//													// nicht!
//				break;
//
//			case "exit":
//				setForwardingPage("/admin"); // todo: bindung an die seite über
//												// contentElement variable!
//				break;
//			}
//		}
//		this.doPageLoad();
//	}
//
//	/**
//	 * Speichert die Seite. 1. Als Erste Unterseite 2. Als neue Elternseite 3.
//	 * Als bearbeitete Seite
//	 */
//	private void doSave(Page actionPage)
//	{
////        log.add(this, "doSave(Page actionPage)", "Seite speichern!");
//		PageTreeControllerOld pageTreeController = new PageTreeControllerOld();
//
//		Enumeration<String> attributeNames = this.getContentAttributeNames(); // todo:
//																				// mit
//																				// obigem
//																				// "page"-Attribute
//																				// vereinen!?
//
//		while (attributeNames.hasMoreElements())
//		{
//			String attributeName = attributeNames.nextElement();
//
//			// Einfügen NACH einer Seite:
//			if (attributeName.equals("newLeadingPage"))
//			{
////                JOptionPane.showMessageDialog(null, "attr. = newLeadingPage");
//				try
//				{
////                    JOptionPane.showMessageDialog(null, (int) this.getContentAttributeValue("newLeadingPage"));
//					pageTreeController.addNewPageBehind(
//							actionPage,
//							(int) this
//									.getContentAttributeValue("newLeadingPage")
//					);
//				} catch (Exception ex)
//				{
////                    JOptionPane.showMessageDialog(null, "attr. = newLeadingPage - CATCH!");
//					Logger.getLogger(EditPageAction.class.getName())
//							.log(Level.SEVERE, null, ex);
//				}
//			}
//
//			// Einfügen IN eine Seite:
//			if (attributeName.equals("newParentPage"))
//			{
////                log.add(
////                        this,
////                        "private void doSave(Page actionPage)",
////                        "Einfügen in eine Seite."
////                );
//				try
//				{
//					pageTreeController.addNewPageInto(
//							actionPage,
//							(int) this.getContentAttributeValue("newParentPage")
//					);
//				} catch (Exception ex)
//				{
//					Logger.getLogger(EditPageAction.class.getName())
//							.log(Level.SEVERE, null, ex);
//				}
//			}
//
//			if (attributeName.equals("editPage"))
//			{
////                JOptionPane.showMessageDialog(null, "habe: editPage");
////                JOptionPane.showMessageDialog(null, "versuche die seite zu speichern: " + actionPage.getId() + " " + actionPage.getInternalName());
//
//				pageTreeController.updatePage(actionPage);
//			}
//		}
//	}
//
//	/**
//	 *
//	 */
//	@Override
//	public void doAjaxAction(HttpServletRequest request)
//	{
//		throw new UnsupportedOperationException("Not supported yet."); // To
//																		// change
//																		// body
//																		// of
//																		// generated
//																		// methods,
//																		// choose
//																		// Tools
//																		// |
//																		// Templates.
//	}
//}
