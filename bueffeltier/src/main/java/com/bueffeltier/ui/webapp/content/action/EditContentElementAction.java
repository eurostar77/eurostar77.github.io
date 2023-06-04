//package com.bueffeltier.ui.webapp.content.action;
//
//import java.util.Enumeration;
//import java.util.List;
//import java.util.logging.Logger;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.swing.JOptionPane;
//
//import com.bueffeltier.data.hibernate.daoImpl.ArticleDaoImpl;
//import com.bueffeltier.data.hibernate.daoImpl.ContentDaoImpl;
//import com.bueffeltier.data.hibernate.entity.Article;
//import com.bueffeltier.data.hibernate.entity.Content;
//import com.bueffeltier.ui.webapp.content.attribute.ContentAttribute;
//import com.bueffeltier.ui.webapp.content.attribute.ContentAttributeBuilder;
//
///**
// *
// * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
// */
//public class EditContentElementAction extends AbstractAction
//{
//
//	private final static Logger logger = Logger
//			.getLogger(EditContentElementAction.class.getName());
//
//	final String type = "EditContentElement";
//	Content content;
//	Article article;
//	List<String> elementTypes;
//	String chosenTypeName;
//
//	private static EditContentElementAction instance;
//
//	private EditContentElementAction()
//	{
//		super();
//	}
//
//	public static EditContentElementAction getInstance()
//	{
//		if (instance == null)
//		{
//			instance = new EditContentElementAction();
//		}
//		return instance;
//	}
//
//	/**
//	 *
//	 */
//	@Override
//	public void execute(HttpServletRequest request)
//	{
//		content = null;
//		try
//		{
////            JOptionPane.showMessageDialog(null, "versuche die seite zu laden!");
//			content = (Content) this.getContentAttributeValue("content1");
//			article = (Article) this.getContentAttributeValue("article1");
////            JOptionPane.showMessageDialog(null, "doPost - Seite geladen!");
//			if (content == null)
//			{
////                JOptionPane.showMessageDialog(null, "seite ist aber null");
//			}
//		} catch (Exception ex)
//		{
//			JOptionPane
//					.showMessageDialog(null, "doPost - Seite nicht geladen!");
////            Logger.getLogger(EditArticleHandler.class.getName()).log(Level.SEVERE, null, ex);
//		}
//
////        log.add(this, "doAction()");
//		Enumeration parameterNames = this.getRequestParameterNames();
//		while (parameterNames.hasMoreElements())
//		{
//			String actionParameterName = parameterNames.nextElement()
//					.toString();
//			switch (actionParameterName) {
//			case "hdl_type":
//
//				this.content.setHeadlineType(
//						Integer.parseInt(this.getRequestParameter("hdl_type"))
//				);
//				break;
//
//			case "headline":
//
//				this.content.setHeadlineText(this.getRequestParameter("type"));
//				break;
//
//			case "guestsOnly":
//
//				if (this.getRequestParameter("guestsOnly").equals("guestsOnly"))
//				{
//					this.content.setGuestsOnly(true);
//				} else
//				{
//					this.content.setGuestsOnly(false);
//				}
//				break;
//			case "cssId":
//
//				this.content.setCssId(this.getRequestParameter("cssId"));
//				break;
//
//			case "cssClass":
//
//				this.content.setCssClass(this.getRequestParameter("cssClass"));
//				break;
//
//			case "hideElem":
//
//				if (this.getRequestParameter("hideElem").equals("hideElem"))
//				{
//					this.content.hide(true);
//				} else
//				{
//					this.content.hide(false);
//				}
//
//				break;
//
////                case "showFrom":
////                    DateTime
////                    LocalDateTime.of(1994, Month.APRIL, 15, 11, 30);
////
////                    this.content.setShowFrom(this.getRequestParameter("showFrom"));
////                    break;
////
////                case "showUntil":
////
////                    this.content.setHeadlineText(this.getRequestParameter("type"));
////                    break;
//			case "type":
//				this.chosenTypeName = this.getRequestParameter("type"); // todo:
//																		// direkt
//																		// in
//																		// content
//																		// speichern!
//				this.content.setType(this.getRequestParameter("type"));
//				// Seite neu laden:
//
//				// Neue Seite bekommt neues Content-Objekt
//				break;
//
//			case "text":
//				content.setHtml(this.getRequestParameter("text"));
//				break;
//
//			case "save":
//				doSave(content);
//				setForwardingPage("/edit-article-elements");
//				break;
//
//			case "saveAndClose":
//
//				doSave(content);
//
//				ArticleDaoImpl adi = new ArticleDaoImpl();
//				// todo: Artikel-Objekt aus vorherigem Handler übernehmen?
//				Article article = adi.findById(Article.class, content.getId());
//				JOptionPane.showMessageDialog(
//						null,
//						"find article: " + Article.class + " " + content.getId()
//				);
//
//				ContentAttribute article1 = new ContentAttributeBuilder(
//						"editArticle", article, this.contentId, this.threadId
//				).addressedContentId(30)
//						// .lifetime(2)
//						// .persistsWhileSession(false) // todo: false =
//						// Standard
//						.buildContentAttribute();
//				this.setContentAttribute(article1);
//
//				setForwardingPage("/edit-article-elements");
//				break;
//			}
//		}
//		doPageLoad();
//	}
//
//	private void doSave(Content actionContent)
//	{
//		ContentDaoImpl cdi = new ContentDaoImpl();
//
//		Enumeration<String> attributeNames = this.getContentAttributeNames(); // todo:
//																				// mit
//																				// obigem
//																				// "content"-Attribute
//																				// vereinen!?
//		while (attributeNames.hasMoreElements())
//		{
//			String attributeName = attributeNames.nextElement();
//			// Konzept: Artikel auf seite oder nur eine seite und wechselnde
//			// artikel?
//
//			if (attributeName.equals("newOnTop"))
//			{
//
//				cdi.save(actionContent);
//			}
//
//			if (attributeName.equals(""))
//			{
//
//			}
//
//			if (attributeName.equals(""))
//			{
//			}
//		}
//	}
//
//	@Override
//	public void doAjaxAction(HttpServletRequest request)
//	{
//		throw new UnsupportedOperationException("Not supported yet.");
//	}
//}
