package com.bueffeltier.ui.webapp.content.view;

import static j2html.TagCreator.*;

import java.util.Enumeration;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import com.bueffeltier.data.hibernate.daoImpl.ContentDaoImpl;
import com.bueffeltier.data.hibernate.entity.Article;
import com.bueffeltier.data.hibernate.entity.Content;
import com.bueffeltier.data.jdbc.ElementJDBCFlat;
import com.bueffeltier.ui.html.molecule.customTag;
import com.bueffeltier.ui.webapp.content.ViewRegistry;

import j2html.tags.DomContent;
import j2html.tags.Tag;

/**
 *
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
public class EditContentElementView extends AbstractView
{

	private final static Logger logger = Logger
	    .getLogger(EditContentElementView.class.getName());

	final String type = "EditContentElement";
	Content content;
	Article article;
	List<String> elementTypes;
	String chosenTypeName;

	private static EditContentElementView instance;

	private EditContentElementView()
	{
		super();
	}

	public static EditContentElementView getInstance()
	{
		if (instance == null)
		{
			instance = new EditContentElementView();
		}
		return instance;
	}

	@Override
	public DomContent
	    writeHtml(ElementJDBCFlat element, HttpServletRequest request)
	{
		try
		{
			this.content = (Content) this.getContentAttributeValue("content1");
			this.article = (Article) this.getContentAttributeValue("article1");
			this.chosenTypeName = (String) this
			    .getContentAttributeValue("type");

		} catch (Exception ex)
		{
		}

		this.elementTypes = ViewRegistry.types();
		/*
		 * Controlls für das anzulegende Content-Element laden:
		 */
		ViewRegistry contentController = new ViewRegistry(
		    this.requestController
		);

		ViewHandler contentTypeHandler = contentController
		    .getTypeHandler(content.getType(), this.contentId, this.threadId); // todo:
		                                                                       // contentId
		                                                                       // noch
		                                                                       // nicht
		                                                                       // vorhanden!
		                                                                       // Controls
		                                                                       // aus
		                                                                       // statischer
		                                                                       // methode
		                                                                       // laden!
//                        contentTypeHandler.restore();

		Tag controlls = contentTypeHandler.writeHtmlControls();

		return form(
		    contentId, threadId, div(
		        // todo: seite zurück und nicht element!
		        customTag.submitButton(
		            "zurück", "back-btn", Integer.toString(content.getId())
		        )
		    ),
		    div(
		        h1("Edit Element"),
		        // todo: Seite der Herkunft angeben.
		        p("id: " + Integer.toString(content.getId())),
		        p(showOptionalHeadline()), div(
		            // Handler für contentType aufrufen und
		            // htmlCOntrolls laden
		            controlls
				// p("Element-Controlls"),
				// p("Element-Controlls"),
				// p("Element-Controlls"),
				// p("Element-Controlls")
		        ),
		        div(
		            label("Element schützen: - nur bestimmte Mitglieder")
		                .attr("for=protected"),
		            input().withType("checkbox").withValue("value")
		                .withName("protected")
		        ).withId("zugriffschutz"),
		        p(
		            label("Nur Gästen anzeigen:").attr("for=guestsOnly"),
		            input().withType("checkbox").withValue("guestsOnly")
		                .withName("guestsOnly")
		        ),
		        p(
		            label("CSS-Id:").attr("for=cssId"),
		            input().withValue("cssId").withName("cssId")
		        ),
		        p(
		            label("CSS-Klasse:").attr("for=cssClass"),
		            input().withValue("cssClass").withName("cssClass")
		        ),
		        p(
		            label("Element unsichtbar:").attr("for=invisible"),
		            input().withType("checkbox").withValue("hideElem")
		                .withName("hideElem")
		        ),
		        p(
		            label("Anzeigen ab:").attr("for=showFrom"),
		            input().withValue("showFrom").withName("showFrom")
		        ),
		        p(
		            label("Anzeigen bis:").attr("for=showUntil"),
		            input().withValue("showUntil").withName("showUntil")
		        ),
		        customTag.submitButton(
		            "speichern", "save", Integer.toString(content.getId())
		        ),
		        customTag.submitButton(
		            "speichern und schließen", "saveAndClose",
		            Integer.toString(content.getId())
		        )
		    )
		);
	}

	private DomContent selectElementType()
	{
		return div(
		    label("Element-Typ wählen:").attr("for", "type"),
		    select(
		        each(
		            elementTypes, element -> option(element).withValue(element)
		        )
		    ).withName("type").attr("onchange=\"this.form.submit()\""),
		    noscript(
		        customTag.submitButton("Typ ändern", "submitForm", "submitForm")
		    )
		);
	}

	private DomContent showOptionalHeadline()
	{
		return div(
		    label("Optionale Überschrift:").attr("for", "headline"),
		    input().withName("headline").withValue("headline"),
		    select(
		        option("H1").withValue("1"), option("H2").withValue("2"),
		        option("H3").withValue("3"), option("H4").withValue("4"),
		        option("H5").withValue("5"), option("H6").withValue("6")
		    )
		);
	}

//    private Tag getElementRow(Content content)
//    {
//        return div(
//                p("............................................................"),
//                p("Content-Id: " + content.getId()),
//                p(content.getType()),
//                /*
//                 * todo: Element-Vorschau.
//                 */
//                customTag.submitButton("bearbeiten", "edit", Integer.toString(content.getId())),
//                customTag.submitButton("verschieben", "move", Integer.toString(content.getId())),
//                customTag.submitButton("löschen", "delete", Integer.toString(content.getId())),
//                customTag.submitButton("veröffentlichen", "publish", Integer.toString(content.getId())),
//                customTag.submitButton("neues Element", "add-behind", Integer.toString(content.getId()))
//        );
//    }
	/**
	 *
	 * @return
	 */
	@Override
	public DomContent writeHtmlControls()
	{
//        log.add(this, "writeHtmlControls()");
		return div().with(p("Test Controls"));
	}

	private void doSave(Content actionContent)
	{
		ContentDaoImpl cdi = new ContentDaoImpl();

		Enumeration<String> attributeNames = this.getContentAttributeNames();
		while (attributeNames.hasMoreElements())
		{
			String attributeName = attributeNames.nextElement();
			// Konzept: Artikel auf seite oder nur eine seite und wechselnde
			// artikel?

			if (attributeName.equals("newOnTop"))
			{

				cdi.save(actionContent);
			}

			if (attributeName.equals(""))
			{

			}

			if (attributeName.equals(""))
			{
			}
		}
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
