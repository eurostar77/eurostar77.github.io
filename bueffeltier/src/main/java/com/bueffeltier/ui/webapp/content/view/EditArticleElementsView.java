//package com.bueffeltier.ui.webapp.content.view;
//
//import static j2html.TagCreator.*;
//
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//
//import com.bueffeltier.data.hibernate.daoImpl.ContentDaoImpl;
//import com.bueffeltier.data.hibernate.entity.Content;
//import com.bueffeltier.data.jdbc.ElementJDBCFlat;
//import com.bueffeltier.ui.html.molecule.customTag;
//
//import j2html.tags.DomContent;
//
///**
// *
// * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
// */
//public class EditArticleElementsView extends AbstractView
//{
//
//	final String type = "EditArticleElements";
//
//	private static EditArticleElementsView instance;
//
//	private EditArticleElementsView()
//	{
//		super();
//	}
//
//	public static EditArticleElementsView getInstance()
//	{
//		if (instance == null)
//		{
//			instance = new EditArticleElementsView();
//		}
//		return instance;
//	}
//
//	@Override
//	public DomContent
//	    writeHtml(ElementJDBCFlat element, HttpServletRequest request)
//	        throws Exception
//	{
//		ContentDaoImpl cdi = new ContentDaoImpl();
//		List<Content> elements = cdi.findAllAsc(article.getId());
//
//		String showTeaser;
//		if (article.showTeaser() == true)
//		{
//			showTeaser = "ja";
//		} else
//		{
//			showTeaser = "nein";
//		}
//		return form(
//		    element.getType(),
//		    customTag.submitButton(
//		        "zurück", "back_btn", Integer.toString(article.getId())
//		    ),
//		    customTag.submitButton(
//		        "Ablage leeren", "clear_tray", Integer.toString(article.getId())
//		    ),
//		    div(
//		        h1("Edit Article Elements"), p("ID: " + article.getId()),
//		        p("Titel: " + article.getTitle()),
//		        p("Autor: " + article.getAuthor()),
//		        // todo:
//		        // p("Änderungsdatum: " + article.getLastEditDate()),
//		        p("Änderungsdatum: " + "Noch programmieren."),
//		        p("Teasertext anzeigen: " + showTeaser),
//		        p("Artikel veröffentlicht: " + article.isPublished()),
//		        customTag.submitButton(
//		            "Artikel Einstellungen bearbeiten", "edit_article_settings",
//		            Integer.toString(article.getId())
//		        ),
//		        customTag.submitButton(
//		            "Element einfügen", "add_elem_on_top", "add_elem_on_top"
//		        ), showContentElements(elements)
//		    )
//		);
//	}
//
//	private DomContent showContentElements(List<Content> elements)
//	{
//		return div(
//		    // todo: id für einrückung
//		    attrs(".structure"),
//		    each(
//		        elements,
//		        element -> div(attrs(".elements"), getElementRow(element))
//		    )
//		);
//	}
//
//	private DomContent getElementRow(Content content)
//	{
//		return div(
//		    p("............................................................"),
//		    p("Content-Id: " + content.getId()), p(content.getType()),
//		    p(content.getHtml()),
//		    /*
//		     * todo: Element-Vorschau.
//		     */
//		    customTag.submitButton(
//		        "bearbeiten", "edit", Integer.toString(content.getId())
//		    ),
//		    customTag.submitButton(
//		        "verschieben", "move", Integer.toString(content.getId())
//		    ),
//		    customTag.submitButton(
//		        "löschen", "delete", Integer.toString(content.getId())
//		    ),
//		    customTag.submitButton(
//		        "veröffentlichen", "publish", Integer.toString(content.getId())
//		    ),
//		    customTag.submitButton(
//		        "neues Element", "add_behind", Integer.toString(content.getId())
//		    ),
//		    customTag.submitButton(
//		        "einfügen danach", "insert-behind",
//		        Integer.toString(content.getId())
//		    )
//		);
//	}
//
//	@Override
//	public DomContent writeHtmlControls()
//	{
//		return div().with(p("Test Controls"));
//	}
//
//	@Override
//	public String writeCss()
//	{
//		throw new UnsupportedOperationException("Not supported yet.");
//	}
//
//	@Override
//	protected void setCssId(String containerTagClassName)
//	{
//		// TODO Auto-generated method stub
//	}
//
//	@Override
//	protected void setContainerTag()
//	{
//		// TODO Auto-generated method stub
//	}
//}
