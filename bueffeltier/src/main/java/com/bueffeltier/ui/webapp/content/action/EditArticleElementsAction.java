//package com.bueffeltier.ui.webapp.content.action;
//
//import java.util.Enumeration;
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//
//import com.bueffeltier.data.hibernate.daoImpl.ContentDaoImpl;
//import com.bueffeltier.data.hibernate.entity.Content;
//import com.bueffeltier.ui.webapp.content.attribute.ContentAttribute;
//import com.bueffeltier.ui.webapp.content.attribute.ContentAttributeBuilder;
//
///**
// *
// * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
// */
//public class EditArticleElementsAction extends AbstractAction
//{
//
//	final String type = "EditArticleElements";
//
//	private static EditArticleElementsAction instance;
//
//	private EditArticleElementsAction()
//	{
//		super();
//	}
//
//	public static EditArticleElementsAction getInstance()
//	{
//		if (instance == null)
//		{
//			instance = new EditArticleElementsAction();
//		}
//		return instance;
//	}
//
//	@Override
//	public void execute(HttpServletRequest request)
//	{
//		Enumeration parameterNames = this.getRequestParameterNames();
//		while (parameterNames.hasMoreElements())
//		{
//			String actionParameterName = parameterNames.nextElement()
//					.toString();
//			switch (actionParameterName) {
//			case "back_btn":
//				this.setForwardingPage("/admin"); // todo: hier id eingeben.
//				break;
//
//			case "clear_tray":
//				this.setForwardingPage("xxx"); // todo: hier id eingeben.
//				break;
//
//			case "edit_article_settings":
//
//				int editArticleId = Integer
//						.parseInt(getRequestParameter("edit_article_settings"));
//
//				ContentAttribute attrArticle = getContentAttribute("article");
//				attrArticle.setAddressedContentId(27);
//				attrArticle.increaseLifetime(1);
//				ContentAttribute attrEditArticleId = getContentAttribute(
//						"editArticleId"
//				);
//				attrEditArticleId.setAddressedContentId(27);
//				attrEditArticleId.increaseLifetime(1);
//
//				if (editArticleId != (Integer) attrEditArticleId.getValue())
//				{
//					throw new Exception("Fehler beim laden des Content!"); // todo:
//																			// eigene
//																			// Exception
//																			// entwickeln.
//				}
//
//				// todo: ContentElemente auch mit deren Class-Name ansprechbar
//				// machen!
//				this.setForwardingPage("/edit-article"); // todo: rename
//															// /edit-article-settings
//															// -- auch in db
//
//				break;
//
//			case "add_elem_on_top":
//
//				ContentAttribute attrArticle1 = getContentAttribute("article");
//				attrArticle1.setAddressedContentId(27);
//				ContentAttribute attrEditArticleId1 = getContentAttribute(
//						"editArticleId"
//				);
//				attrEditArticleId1.setAddressedContentId(27);
//
//				Content content = new Content();
//				content.setOrder(0);
//				ContentAttribute attribute1a = new ContentAttributeBuilder(
//						"content", content, this.contentId, this.threadId
//				).addressedContentId(32)
//						// .lifetime(4)
//						// .persistsWhileSession(true) // todo: false = Standard
//						.buildContentAttribute();
//				ContentAttribute attribute2a = new ContentAttributeBuilder(
//						"article", attrArticle1.getValue(), this.contentId,
//						this.threadId
//				).addressedContentId(32)
//						// .lifetime(4)
//						// .persistsWhileSession(false) // todo: false =
//						// Standard
//						.buildContentAttribute();
//				this.setContentAttribute(attribute1a);
//				this.setContentAttribute(attribute2a);
//				this.setForwardingPage("/choose-content-element"); // todo: hier
//																	// id
//																	// eingeben.
//				break;
//
//			case "edit":
//				ContentDaoImpl cdi = new ContentDaoImpl();
//
//				Content content1 = cdi.findById(
//						Content.class,
//						Integer.parseInt(this.getRequestParameter("edit"))
//				);
//
//				ContentAttribute attribute3 = new ContentAttributeBuilder(
//						"content", content1, this.contentId, this.threadId
//				).addressedContentId(31)
//						// .lifetime(2)
//						// .persistsWhileSession(false) // todo: false =
//						// Standard
//						.buildContentAttribute();
//				this.setContentAttribute(attribute3);
//				this.setForwardingPage("/edit-content-element"); // todo: hier
//																	// id
//																	// eingeben.
//				break;
//			case "move":
//				int moveContentId = Integer
//						.parseInt(this.getRequestParameter("move"));
//				ContentDaoImpl cdi2 = new ContentDaoImpl();
//				List<Content> elements = cdi2.findAllAsc(99999999);
//				this.setForwardingPage("xxx"); // todo: hier id eingeben.
//				break;
//			case "delete":
//				int deleteContentId = Integer
//						.parseInt(this.getRequestParameter("delete"));
//				ContentDaoImpl cdi3 = new ContentDaoImpl();
//				cdi3.delete(cdi3.findById(Content.class, contentId));
//				this.setForwardingPage("xxx"); // todo: hier id eingeben.
//				break;
//			case "publish":
//				this.setForwardingPage("xxx"); // todo: hier id eingeben.
//				break;
//			case "add_behind":
//				this.setForwardingPage("xxx"); // todo: hier id eingeben.
//				break;
//			}
//		}
//		doPageLoad();
//	}
//
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
