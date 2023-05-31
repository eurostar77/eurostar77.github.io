package com.bueffeltier.ui.webapp.content.view;

import static com.bueffeltier.ui.html.molecule.customTag.submitButton;
import static j2html.TagCreator.*;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.swing.JOptionPane;

import com.bueffeltier.data.hibernate.entity.Article;
import com.bueffeltier.data.jdbc.ElementJDBCFlat;
import com.bueffeltier.logic.foundation.pagetree.PageTreeControllerOld;
import com.bueffeltier.ui.webapp.content.attribute.ContentAttribute;

import j2html.tags.DomContent;

/**
 *
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
public class EditArticleSettingsView extends AbstractView
{

	private String info;

	private int permission;

	private int layout;

	private boolean showInNav;

	private boolean publish;

	private static EditArticleSettingsView instance;

	private EditArticleSettingsView()
	{
		super();
	}

	public static EditArticleSettingsView getInstance()
	{
		if (instance == null)
		{
			instance = new EditArticleSettingsView();
		}
		return instance;
	}

	private void doSave(Article article)
	{
		PageTreeControllerOld pageTreeController = new PageTreeControllerOld();

		if (getArticleInsertType().getKey().equals("editArticleId"))
		{
			try
			{
				pageTreeController.addNewArticleBehindArticle(
				    article,
				    (int) this.getContentAttributeValue("newInsertedArticle")
				);
			} catch (Exception ex)
			{
				Logger.getLogger(EditArticleSettingsView.class.getName())
				    .log(Level.SEVERE, null, ex);
			}
			return;
		} else if (getArticleInsertType().getKey().equals("newParentPage"))
		{
			article.setPublished(true);
			try
			{
				pageTreeController.addNewArticleIntoPage(
				    article,
				    (int) this.getContentAttributeValue("newParentPage")
				);
			} catch (Exception e)
			{
				JOptionPane.showMessageDialog(null, "fehler beim speichern!");
			}
			return;
		}
//        pageTreeController.saveArticle(article);
		JOptionPane.showMessageDialog(null, "article saved!");
	}

	private ContentAttribute getArticleInsertType()
	{
		JOptionPane.showMessageDialog(null, "getArticleInsertType()");
		try
		{
			return this.getContentAttribute("newInsertedArticle");
		} catch (Exception e)
		{
			// do nothing
		}

		try
		{
			return this.getContentAttribute("newParentPage");
		} catch (Exception e)
		{
			// do nothing
		}
		JOptionPane
		    .showMessageDialog(null, "getArticleInsertType() - return null");
		return null;
	}

	@Override
	public DomContent
	    writeHtml(ElementJDBCFlat element, HttpServletRequest request)
	        throws Exception
	{
		ContentAttribute attrArticle = getContentAttribute("article");
		attrArticle.increaseLifetime(2);
		this.setContentAttribute(attrArticle);

		ContentAttribute editArticle = getContentAttribute("newParentPage");
		editArticle.increaseLifetime(2);
		this.setContentAttribute(editArticle);

		Article article = (Article) this.getContentAttributeValue("article");
		return form(
		    contentId, threadId, infoField(), titleAndAuthorPanel(article),
		    layoutAndSearchPanel(article), artikelTeaserPanel(article),
		    // syndikationPanel(),
		    // templateEinstellungenPanel(),
		    // zugriffschutzPanel(),
		    expertenEinstellungePanel(article), veröffentlichungPanel(article),
		    // tastaturNavigationPanel(),
		    speichernPanel()
		).withClass("mainPanel");
	}

	private DomContent textInput(String name, String value, String id)
	{ // allgemein zur Verfügung stellen!
	  // todo: id vom System generieren und verwalten!

		if (value != null && value != "")
		{
			return input().withType("text").withName(name).withValue(value)
			    .withId(id); // pagename-input
		} else
		{
			return input().withType("text").withName(name).withId(id);
		}
	}

	private DomContent titleAndAuthorPanel(Article article)
	{
		return div(
		    ul(
		        li(
		            label("ID").attr("for", "id"),
		            textInput("id", Integer.toString(article.getId()), "id")
		                .attr("disabled")
		        ),
		        li(
		            label("Titel").attr("for", "title"),
		            textInput("title", article.getTitle(), "title")
		        ),
		        li(
		            // todo: Autor kann einer der hinterlegten User
		            // inlc. Admin sein.
		            label("Autor").attr("for", "author"),
		            textInput("author", article.getAuthor(), "author")
		        )
		    )
		).withClass("panel");
	}

	private DomContent layoutAndSearchPanel(Article article)
	{
		String container;

		try
		{
			container = article.getContainerTag();
		} catch (Exception e)
		{
			container = "";
		}
		return div(
		    ul(
		        // todo: Spalten aus dem Layout anbieten:
		        // li(
		        // input().withType("radio")
		        // .withName("anzeigen-in")
		        // .withValue("kopfzeile")
		        // .withId("kopfzeile"),
		        // label("Kopfzeile").attr("for", "kopfzeile")
		        // ),
		        // li(
		        // input().withType("radio")
		        // .withName("anzeigen-in")
		        // .withValue("linke-spalte")
		        // .withId("linke-spalte"),
		        // label("Linke Spalte").attr("for", "linke-spalte")
		        // ),
		        // li(
		        // input().withType("radio")
		        // .withName("anzeigen-in")
		        // .withValue("rechte-spalte")
		        // .withId("rechte-spalte"),
		        // label("Rechte Spalte").attr("for", "rechte-spalte")
		        // ),
		        // li(
		        // input().withType("radio")
		        // .withName("anzeigen-in")
		        // .withValue("hauptspalte")
		        // .withId("hauptspalte"),
		        // label("Hauptspalte").attr("for", "hauptspalte")
		        // ),
		        li(
		            label("Suchbegriffe").attr("for", "keywords-input"),
		            textInput(
		                "keywords-input", article.getKeywords(), "keywords"
		            ),
		            p(
		                label(
		                    "Hier können Sie eine kommagetrennte Liste von Suchbegriffen eingeben. Suchbegriffe sind jedoch für die meisten Suchmaschinen (inklusive Google) nicht mehr relevant."
		                ).attr("for", "suchbegriffe-input")
		            )
		        ), p("Container:"),
		        li(
		            input().withType("radio").withName("container")
		                .withValue("div").withId("div").attr(
		                    iff(
		                        container != null && container.equals("div"),
		                        "checked"
		                    )
		                ),
		            label("DIV").attr("for", "div")
		        ),
		        li(
		            input().withType("radio").withName("container")
		                .withValue("article").withId("article").attr(
		                    iff(
		                        container != null
		                            && container.equals("article"),
		                        "checked"
		                    )
		                ),
		            label("ARTICLE").attr("for", "article")
		        )
		    )
		).withClass("panel");
	}

	private DomContent artikelTeaserPanel(Article article)
	{
		return div(
		    ul(
		        li(
		            label("Teaser-CSS-Id").attr("for", "teaser_css_id"),
		            textInput(
		                "teaser_css_id", article.getTeaserCssId(),
		                "teaser_css_id"
		            )
		        ),
		        li(
		            label("Teaser-CSS-Class").attr("for", "teaser_css_class"),
		            textInput(
		                "teaser_css_class", article.getTeaserCssClass(),
		                "teaser_css_class"
		            )
		        )
				// todo:
				// ,
				// input().withType("checkbox")
				// .withName("show-teasertext")
				// .withValue("show-teasertext")
				// .withId("show-teasertext")
				// todo:
				// ,
				// label("Automatisch Teaser statt Artikel anzeigen,
				// wenn es mehrere Artikel auf der Seite
				// gibt.").attr("for", "show-teasertext")
				// todo: html editor einbinden.

		    )
		).withClass("panel");
	}

	private DomContent syndikationPanel()
	{
		return div(
		    ul(
		        li(
		            input().withType("checkbox").withName("add-layout")
		                .withValue("add-layout").withId("add-layout"),
		            label("Alle auswählen").attr("for", "add-layout")
		        ),
		        li(
		            input().withType("checkbox").withName("add-layout")
		                .withValue("add-layout").withId("add-layout"),
		            label("Seite drucken").attr("for", "add-layout")
		        ),
		        li(
		            input().withType("checkbox").withName("add-layout")
		                .withValue("add-layout").withId("add-layout"),
		            label("Artikel als PDF").attr("for", "add-layout")
		        ),
		        li(
		            input().withType("checkbox").withName("add-layout")
		                .withValue("add-layout").withId("add-layout"),
		            label("Auf Facebook teilen").attr("for", "add-layout")
		        ),
		        li(
		            input().withType("checkbox").withName("add-layout")
		                .withValue("add-layout").withId("add-layout"),
		            label("Auf Twitter teilen").attr("for", "add-layout")
		        ),
		        li(
		            input().withType("checkbox").withName("add-layout")
		                .withValue("add-layout").withId("add-layout"),
		            label("Ein Layout zuweisen").attr("for", "add-layout")
		        )
		    )
		).withClass("panel");
	}

	private DomContent templateEinstellungenPanel()
	{
		return div(
		    ul(
		        li(
		            input().withType("checkbox").withName("cache-time")
		                .withValue("cache-time").withId("cache-time"),
		            label("Individuelles Template").attr("for", "cache-time")
		        )
		    )
		).withClass("panel");
	}

	private DomContent zugriffschutzPanel()
	{
		return div(
		    ul(
		        li(
		            input().withType("checkbox").withName("protect_article")
		                .withValue("protect_article").withId("protect_article"),
		            label("Artikel für bestimmte Gruppen schützen")
		                .attr("for", "protect_article")
		        )
		    )
		).withClass("panel");
	}

	private DomContent expertenEinstellungePanel(Article article)
	{
		return div(
		    ul(
		        // li(
		        // input().withType("checkbox")
		        // .withName("guest-only")
		        // .withValue("guest-only")
		        // .withId("guest-only"),
		        // label("Nur Gästen anzeigen").attr("for",
		        // "guest-only")
		        // ),
		        li(
		            label("CSS-ID").attr("for", "css_id"),
		            textInput("css_id", article.getCssId(), "css_id")
		        ),
		        li(
		            label("CSS-Klasse").attr("for", "css_class"),
		            textInput("css_class", article.getCssClass(), "css_class")
		        )
		    )
		).withClass("panel");
	}

	private DomContent veröffentlichungPanel(Article article)
	{
		boolean isPublished;
		try
		{
			isPublished = article.isPublished();
		} catch (Exception e)
		{
			isPublished = false;
		}

		return div(
		    ul(
		        li(
		            input().withType("checkbox").withName("publish")
		                .withValue("true").withId("publish")
		                .attr(iff(isPublished, "checked")),
		            label("Artikel veröffentlichen").attr("for", "publish")
		        )
				// ,
				// li(
				// label("Anzeigen ab: ").attr("for", "start_date"),
				// textInput("start_date", "placeholder", "start_date")
				// ),
				// li(
				// label("Anzeigen bis: ").attr("for", "end_date"),
				// textInput("end_date", "placeholder", "end_date")
				// )
		    )
		).withClass("panel");
	}

	private DomContent tastaturNavigationPanel()
	{
		return div(
		    ul(
		        li(
		            input().withType("text").withName("robotTags")
		                .withValue("robotTags").withPlaceholder("robot")
		        ), li()
		    )
		).withClass("panel");
	}

	private DomContent speichernPanel()
	{
		return div(
		    ul(
		        li(
		            submitButton(
		                "Save and Close", "save_and_close", "save_and_close"
		            ), submitButton("Save", "save", "save"),
		            submitButton("Exit", "exit", "exit")
		        )
		    )
		).withClass("panel");
	}

	/**
	 *
	 * @return
	 */
	public DomContent infoField()
	{
		if (this.info != null)
		{
			return p(info).withStyle("background-color: tomato");
		} else
		{
			return p();
		}
	}

	/**
	 *
	 * @return
	 */
	@Override
	public String writeCss()
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}

//    @Override
//    public void restore() {
//
//    }
	/**
	 *
	 * @return
	 */
	@Override
	public DomContent writeHtmlControls()
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
