//package com.bueffeltier.ui.webapp.content.action;
//
//import java.util.Enumeration;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.swing.JOptionPane;
//
//import com.bueffeltier.data.hibernate.entity.Article;
//import com.bueffeltier.data.hibernate.entity.Content;
//import com.bueffeltier.ui.webapp.content.attribute.ContentAttribute;
//import com.bueffeltier.ui.webapp.content.attribute.ContentAttributeBuilder;
//
///**
// *
// * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
// */
//public class ChooseContentTypeAction extends AbstractAction
//{
//
//	Content content;
//	Article article;
//
//	private static ChooseContentTypeAction instance;
//
//	private ChooseContentTypeAction()
//	{
//		super();
//	}
//
//	public static ChooseContentTypeAction getInstance()
//	{
//		if (instance == null)
//		{
//			instance = new ChooseContentTypeAction();
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
//
//		try
//		{
//			JOptionPane.showMessageDialog(
//					null, "ChooseContentType: POST - try Content"
//			);
//			content = (Content) this.getContentAttributeValue("content");
//			JOptionPane.showMessageDialog(
//					null, "ChooseContentType: POST - try Article"
//			);
//			article = (Article) this.getContentAttributeValue("article");
//
////            JOptionPane.showMessageDialog(null, "content at doPostAction: " + content.toString());
//		} catch (Exception ex)
//		{
//			JOptionPane
//					.showMessageDialog(null, "ChooseContentType: POST - catch");
////            JOptionPane.showMessageDialog(null, "catch at postAction");
//		}
//
//		Enumeration<String> parameterNames = this.getRequestParameterNames();
//
//		while (parameterNames.hasMoreElements())
//		{
//			// todo: zweite schleife, die nur save und exit behandelt, um die
//			// anderen daten gänzlich zu speichern.
//			String currentParameterName = parameterNames.nextElement();
////            JOptionPane.showMessageDialog(null, currentParameterName);
//			switch (currentParameterName) {
//			case "type":
////                    JOptionPane.showMessageDialog(null, "type = " + this.getRequestParameter("type"));
//				content.setType(this.getRequestParameter("type"));
//				break;
//
//			case "send_type":
//
//				ContentAttribute attribute1 = new ContentAttributeBuilder(
//						"content1", content, this.contentId, this.threadId
//				).addressedContentId(2)
//						// .lifetime(2)
//						.persistsWhileSession(true) // todo: false = Standard
//						.buildContentAttribute();
//				this.setContentAttribute(attribute1);
//
//				ContentAttribute attribute2 = new ContentAttributeBuilder(
//						"article1", article, this.contentId, this.threadId
//				).addressedContentId(2)
//						// .lifetime(2)
//						.persistsWhileSession(true) // todo: false = Standard
//						.buildContentAttribute();
//				this.setContentAttribute(attribute2);
//
//				this.setForwardingPage("/edit-content-element");
//				break;
//			}
//		}
//		this.doPageLoad();
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
//
//}
