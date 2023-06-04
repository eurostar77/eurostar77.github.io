//package com.bueffeltier.ui.webapp.content.action;
//
//import java.util.Enumeration;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.swing.JOptionPane;
//
//import com.bueffeltier.data.hibernate.entity.Article;
//import com.bueffeltier.logic.foundation.pagetree.PageTreeControllerOld;
//import com.bueffeltier.ui.webapp.content.attribute.ContentAttribute;
//
///**
// *
// * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
// */
//public class EditArticleSettingsAction extends AbstractAction
//{
//
//	private String info;
//
////    private Article article;
//	private int permission;
//
//	private int layout;
//
//	private boolean showInNav;
//
//	private boolean publish;
//
//	private static EditArticleSettingsAction instance;
//
//	private EditArticleSettingsAction()
//	{
//		super();
//	}
//
//	public static EditArticleSettingsAction getInstance()
//	{
//		if (instance == null)
//		{
//			instance = new EditArticleSettingsAction();
//		}
//		return instance;
//	}
//
//	@Override
//	public void execute(HttpServletRequest request) throws Exception
//	{
//		ContentAttribute attrArticle = getContentAttribute("article");
//		attrArticle.increaseLifetime(2);
//		this.setContentAttribute(attrArticle);
//
//		ContentAttribute editArticle = getContentAttribute("newParentPage");
//		editArticle.increaseLifetime(2);
//		this.setContentAttribute(editArticle);
//
//		Article article = (Article) this.getContentAttributeValue("article");
//		article.setPublished(true);
//
//		Enumeration<String> parameterNames = this.getRequestParameterNames();
//		while (parameterNames.hasMoreElements())
//		{
//			// todo: zweite schleife, die nur save und exit behandelt, um die
//			// anderen daten gänzlich zu speichern.
//			String currentParameterName = parameterNames.nextElement();
//			switch (currentParameterName) {
//			case "id":
////                    article.setId(Integer.parseInt(getRequestParameter("id")));
//				break;
//
//			case "title":
//				article.setTitle(getRequestParameter("title"));
//				break;
//
//			case "author":
//				article.setAuthor(getRequestParameter("author"));
//				break;
//
//			case "container":
//				article.setContainerTag(getRequestParameter("container"));
//				break;
//			// case "anzeigen-in":
//			// actionArticle.setLayoutArea(),
//			// break;
//			case "keywords":
//				article.setKeywords(getRequestParameter("keywords"));
//				break;
//
//			case "teaser_css_id":
//				article.setTeaserCssId(getRequestParameter("teaser_css_id"));
//				break;
//			case "teaser_css_class":
//				article.setTeaserCssClass(
//						getRequestParameter("teaser_css_class")
//				);
//				break;
//
//			case "protect_article":
////                    actionArticle.setProtected(getRequestParameter("author-input"));
//				break;
//
//			case "css_id":
//				article.setCssId(getRequestParameter("css_id"));
//
//				break;
//			case "css_class":
//				article.setCssClass(getRequestParameter("css_class"));
//				break;
//
//			case "publish":
//				if (getRequestParameter("publish").equals("true"))
//				{
//					article.setPublished(true);
//				} else
//				{
//					article.setPublished(false);
//				}
//
//				article.setPublished(
//						Boolean.parseBoolean(getRequestParameter("publish"))
//				);
//				break;
//
//			case "start_date":
//
//				break;
//
//			case "end_date":
//
//				break;
//
//			case "save_and_close":
//
//				doSave(article);
//
//				ContentAttribute attrArticle1 = getContentAttribute("article");
//
//				attrArticle1.setAddressedContentId(30);
//				attrArticle1.increaseLifetime(2);
//
//				ContentAttribute editArticle1 = getContentAttribute(
//						"editArticleId"
//				);
//				editArticle1.setAddressedContentId(30);
//				editArticle1.increaseLifetime(2);
//
//				setForwardingPage("/edit-article-elements");
//				break;
//
//			case "save":
//
//				doSave(article);
//
//				setForwardingPage("/edit-article"); // todo: boolean
//													// funktioniert nicht!
//
//				ContentAttribute attrArticle2 = getContentAttribute("article");
//				attrArticle2.setAddressedContentId(27);
//				attrArticle2.increaseLifetime(1);
//
//				ContentAttribute editArticle2 = getContentAttribute(
//						"newParentPage"
//				);
//				editArticle2.setAddressedContentId(27);
//				editArticle2.increaseLifetime(1);
//
//				this.setForwardingPage("/edit-article");
//				break;
//
//			case "exit":
//				ContentAttribute attrArticle3 = getContentAttribute("article");
//				attrArticle3.setAddressedContentId(30);
//				attrArticle3.incrementLifetime();
//				ContentAttribute editArticle3 = getContentAttribute(
//						"editArticleId"
//				);
//				editArticle3.setAddressedContentId(30);
//				editArticle3.incrementLifetime();
//
//				/*
//				 * todo: Was muss ich machen, damit attribute nach post und get
//				 * wieder zur Verfügung steht? Trennung von action und view hier
//				 * wichtig? Auf Targetpage findet nur ein get statt. muss dann
//				 * verlängert werden? wenn target nicht eingelöst wird,
//				 * attribute lösdchen?
//				 * 
//				 * 
//				 */
//				// ich gehe von 23 nach 30 nach 27
//				setForwardingPage("/edit-article-elements"); // todo: bindung an
//																// die seite
//																// über
//																// contentElement
//																// variable!
//				break;
//			}
//		}
//		this.doPageLoad();
//	}
//
//	private void doSave(Article article)
//	{
//		PageTreeControllerOld pageTreeController = new PageTreeControllerOld();
//
//		if (getArticleInsertType().getKey().equals("editArticleId"))
//		{
//			try
//			{
//				pageTreeController.addNewArticleBehindArticle(
//						article,
//						(int) this
//								.getContentAttributeValue("newInsertedArticle")
//				);
//			} catch (Exception ex)
//			{
//				Logger.getLogger(
//						EditArticleSettingsAction.class.getName()
//				).log(Level.SEVERE, null, ex);
//			}
//			return;
//		} else if (getArticleInsertType().getKey().equals("newParentPage"))
//		{
//			article.setPublished(true);
//			try
//			{
//				pageTreeController.addNewArticleIntoPage(
//						article,
//						(int) this.getContentAttributeValue("newParentPage")
//				);
//			} catch (Exception e)
//			{
//				JOptionPane.showMessageDialog(null, "fehler beim speichern!");
//			}
//			return;
//		}
////        pageTreeController.saveArticle(article);
//		JOptionPane.showMessageDialog(null, "article saved!");
//	}
//
//	private ContentAttribute getArticleInsertType()
//	{
//		JOptionPane.showMessageDialog(null, "getArticleInsertType()");
//		try
//		{
//			return this.getContentAttribute("newInsertedArticle");
//		} catch (Exception e)
//		{
//			// do nothing
//		}
//
//		try
//		{
//			return this.getContentAttribute("newParentPage");
//		} catch (Exception e)
//		{
//			// do nothing
//		}
//		JOptionPane.showMessageDialog(
//				null, "getArticleInsertType() - return null"
//		);
//		return null;
//	}
//
//	@Override
//	public void doAjaxAction(HttpServletRequest request)
//	{
//		throw new UnsupportedOperationException("Not supported yet.");
//	}
//}
