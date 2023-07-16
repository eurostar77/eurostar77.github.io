package com.bueffeltier.ui.webapp.content.view;

import static j2html.TagCreator.*;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.bueffeltier.data.jdbc.ArticleJDBCFlat;
import com.bueffeltier.data.jdbc.ElementJDBCFlat;
import com.bueffeltier.logic.foundation.pagetree.SiteRepository;
import com.bueffeltier.ui.html.organism.Accordion;
import com.bueffeltier.ui.html.organism.ButtonBuilder;
import com.bueffeltier.ui.html.organism.ButtonBuilder.ButtonTypeDV;
import com.bueffeltier.ui.html.organism.ButtonBuilder.ColorDV;
import com.bueffeltier.ui.html.organism.ColumnBuilder;
import com.bueffeltier.ui.html.organism.ColumnBuilder.GridWidthDV;
import com.bueffeltier.ui.html.organism.FooterNavigationBuilder;
import com.bueffeltier.ui.html.organism.FormControlBuilder;
import com.bueffeltier.ui.html.organism.LabelBuilder;
import com.bueffeltier.ui.html.organism.RowBuilder;
import com.bueffeltier.ui.html.organism.RowBuilder.RowAlignmentDV;

import j2html.tags.DomContent;

/**
 *
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
public class ArticleEditorView extends AbstractView
{
	private static ArticleEditorView instance;

	private SiteRepository siteRepository = SiteRepository.getInstance();

	private ArticleEditorView()
	{
		super();
	}

	public static ArticleEditorView getInstance()
	{
		if (instance == null)
		{
			instance = new ArticleEditorView();
		}
		return instance;
	}

	@Override
	public DomContent
	    writeHtml(ElementJDBCFlat element, HttpServletRequest request)
	{

		Long articleId = null;

		String articleIdString = getViewDataValueOpt(request, "editArticle");

		// TODO sveng 04.07.2023: UUID umwandeln

		if (articleIdString != null)
		{
			articleId = Long.parseLong(articleIdString);
		}

		ArticleJDBCFlat article = null;

		if (articleId != null)
		{
			article = siteRepository.readArticle(articleId);

		} else
		{
			// TODO sveng 21.06.2023: default Werte für neue Seite sinnvoll
			// setzen! Ggf. create Methode bauen.
			article = new ArticleJDBCFlat();

		}

		Accordion accordion = new Accordion("editArticle");

		return form(

		    element.getType(), //

		    input()//
		        .withType("hidden")//
		        .withName("pRef")//
		        .withValue(Long.toString(article.getId())), //

		    h1("Article Editor").withClass("mb-3"),

		    accordion.buildAccordionContainer(

		        titleAndAuthorPanel(article, accordion, articleIdString), //

		        elementsSection(article, accordion), //
		        layoutAndSearchPanel(article, accordion), //
		        artikelTeaserPanel(article, accordion), //
		        syndikationPanel(article, accordion), //
		        templateEinstellungenPanel(article, accordion), //
		        zugriffschutzPanel(article, accordion), //
		        expertenEinstellungePanel(article, accordion), //
		        veröffentlichungPanel(article, accordion),
		        tastaturNavigationPanel(article, accordion), //
		        footerSection()
		    )
		).withClass("mainPanel accordion");
	}

//	private DomContent textInput(String name, String value, String id)
//	{
//		if (value != null && value != "")
//		{
//			return input().withType("text").withName(name).withValue(value)
//			    .withId(id); // pagename-input
//		} else
//		{
//			return input().withType("text").withName(name).withId(id);
//		}
//	}

	private DomContent
	    elementsSection(ArticleJDBCFlat article, Accordion accordion)
	{
		List<ElementJDBCFlat> elements = article.getElements();

		if (elements == null || elements.isEmpty())
		{
			return null;

		} else
		{
			return accordion.buildAccordionItem(
			    //
			    accordion.buildHeaderH2("Content Elements"), //

			    accordion.buildBody(
			        //
			        each(
			            //
			            elements, e -> //
						RowBuilder.create()//
						    .withDomContent(
						        //
						        ColumnBuilder.create()//
						            .withGridWidth(GridWidthDV.FOUR)
						            .withDomContent(
						                //
						                ButtonBuilder.create()//
						                    .withColor(ColorDV.SECONDARY)//
						                    .asOutline()//
						                    .withText(e.getType())//
						                    .withName("article")//
						                    .withValue(Long.toString(e.getId()))//
						                    .withButtonType(ButtonTypeDV.SUBMIT)//
						                    .build()
						            )//
						            .build()
						    )//
						    .build()
			        )
			    )
			);
		}
	}

	private DomContent titleAndAuthorPanel(
	    ArticleJDBCFlat article,
	    Accordion accordion,
	    String articleIdString
	)
	{
		return accordion.buildAccordionItem(
		    //
		    accordion.buildHeaderH2("Title & Author"), //

		    accordion.buildBody(
		        //
		        LabelBuilder.create().//
		            build("ID", "idInput"), //

		        p("Page-Id: " + articleIdString),

		        LabelBuilder.create().//
		            build("Titel", "titleInput"), //

		        FormControlBuilder.create()//
		            .withName("title")//
		            .withValue(article.getTitle())//
		            .withId("titleInput")//
		            .build(),

		        LabelBuilder.create().//
		            build("Autor", "authorInput"), //

		        // name val id
		        FormControlBuilder.create()//
		            .withName("author")//
		            .withValue(article.getAuthor())//
		            .withId("authorInput")//
		            .build()
		    )
		);
	}

	private DomContent
	    layoutAndSearchPanel(ArticleJDBCFlat article, Accordion accordion)
	{
		String container;

		try
		{
			container = article.getContainerTag();
		} catch (Exception e)
		{
			container = "";
		}
		return accordion.buildAccordionItem(
		    //
		    accordion.buildHeaderH2("Layout & Search"), //

		    accordion.buildBody(
		        //
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
		            li(LabelBuilder.create().//
		                build("Suchbegriffe", "keywordsInput"), //

		                FormControlBuilder.create()//
		                    .withName("keywords")//
		                    .withValue(article.getKeywords())//
		                    .withId("keywordsInput")//
		                    .build(),

		                p(LabelBuilder.create().//
		                    build(
		                        """
		                            Hier können Sie eine kommagetrennte Liste von Suchbegriffen eingeben.
		                            Suchbegriffe sind jedoch für die meisten Suchmaschinen (inklusive Google) nicht mehr relevant.
		                            """,
		                        "suchbegriffe-input"
		                    ) //
		                )
		            ), //

		            p("Container:"),

		            input().withType("radio").withName("container")
		                .withValue("div").withId("div").attr(
		                    iff(
		                        container != null && container.equals("div"),
		                        "checked"
		                    ),

		                    LabelBuilder.create().//
		                        build("DIV", "div") //
		                ),

		            input().withType("radio").withName("container")
		                .withValue("article").withId("article").attr(
		                    iff(
		                        container != null
		                            && container.equals("article"),
		                        "checked"
		                    ),

		                    LabelBuilder.create().//
		                        build("ARTICLE", "article")
		                )
		        )
		    )
		);
	}

	private DomContent
	    artikelTeaserPanel(ArticleJDBCFlat article, Accordion accordion)
	{
		return accordion.buildAccordionItem(
		    //
		    accordion.buildHeaderH2("Article Teaser"), //

		    accordion.buildBody(
		        //
		        ul(
		            //
		            li(
		                //
		                LabelBuilder.create().//
		                    build("Teaser CSS-Id", "teaserCssIdInput"), //

		                FormControlBuilder.create()//
		                    .withName("teaserCssId")//
		                    .withValue("article.getTeaserCssId()")//
		                    .withId("teaserCssIdInput")//
		                    .build()
		            )
		        ),
		        li(
		            //
		            LabelBuilder.create().//
		                build("Teaser-CSS-Class", "teaserCssClassInput"), //

		            // name val id
		            FormControlBuilder.create()//
		                .withName("teaserCssClass")//
		                .withValue(article.getTeaserCssClass())//
		                .withId("teaserCssClassInput")//
		                .build()
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
		);
	}

	private DomContent
	    syndikationPanel(ArticleJDBCFlat article, Accordion accordion)
	{
		return accordion.buildAccordionItem(
		    //
		    accordion.buildHeaderH2("Syndication"), //

		    accordion.buildBody(
		        //
		        input().withType("checkbox").withName("add-layout")
		            .withValue("add-layout").withId("add-layout"),

		        LabelBuilder.create().//
		            build("Alle auswählen", "add-layout"),

		        input().withType("checkbox").withName("add-layout")
		            .withValue("add-layout").withId("add-layout"),

		        LabelBuilder.create().//
		            build("Seite drucken", "add-layout"),

		        input().withType("checkbox").withName("add-layout")
		            .withValue("add-layout").withId("add-layout"),

		        LabelBuilder.create().//
		            build("Artikel als PDF", "add-layout"),

		        input().withType("checkbox").withName("add-layout")
		            .withValue("add-layout").withId("add-layout"),

		        LabelBuilder.create().//
		            build("Auf Facebook teilen", "add-layout"),

		        input().withType("checkbox").withName("add-layout")
		            .withValue("add-layout").withId("add-layout"),

		        LabelBuilder.create().//
		            build("Auf Twitter teilen", "add-layout"),

		        input().withType("checkbox").withName("add-layout")
		            .withValue("add-layout").withId("add-layout"),

		        LabelBuilder.create().//
		            build("Ein Layout zuweisen", "add-layout")
		    )
		);
	}

	private DomContent
	    templateEinstellungenPanel(ArticleJDBCFlat article, Accordion accordion)
	{
		return accordion.buildAccordionItem(
		    //
		    accordion.buildHeaderH2("Template"), //

		    accordion.buildBody(
		        //
		        input().withType("checkbox").withName("cache-time")
		            .withValue("cache-time").withId("cache-time"),

		        LabelBuilder.create().//
		            build("Individuelles Template", "cache-time")
		    )
		);
	}

	private DomContent
	    zugriffschutzPanel(ArticleJDBCFlat article, Accordion accordion)
	{
		return accordion.buildAccordionItem(
		    //
		    accordion.buildHeaderH2("Access"), //

		    accordion.buildBody(
		        //
		        ul(
		            li(
		                input().withType("checkbox").withName("protect_article")
		                    .withValue("protect_article")
		                    .withId("protect_article"),

		                LabelBuilder.create().//
		                    build(
		                        "Artikel für bestimmte Gruppen schützen",
		                        "protect_article"
		                    )
		            )
		        )
		    )
		);
	}

	private DomContent
	    expertenEinstellungePanel(ArticleJDBCFlat article, Accordion accordion)
	{
		return accordion.buildAccordionItem(
		    //
		    accordion.buildHeaderH2("Experts"), //

		    accordion.buildBody(
		        //
		        // input().withType("checkbox")
		        // .withName("guest-only")
		        // .withValue("guest-only")
		        // .withId("guest-only"),
		        // label("Nur Gästen anzeigen").attr("for",
		        // "guest-only")
		        // ),
		        //
		        LabelBuilder.create().//
		            build("CSS-ID", "cssIdInput"), //

		        // name val id
		        FormControlBuilder.create()//
		            .withName("cssId")//
		            .withValue(article.getCssId())//
		            .withId("cssIdInput")//
		            .build(),

		        //
		        LabelBuilder.create().//
		            build("CSS-Klasse", "cssClassInput"), //

		        // name val id
		        FormControlBuilder.create()//
		            .withName("cssClass")//
		            .withValue(article.getCssClass())//
		            .withId("cssClassInput")//
		            .build()
		    )
		);
	}

	private DomContent
	    veröffentlichungPanel(ArticleJDBCFlat article, Accordion accordion)
	{
		boolean isPublished;
		try
		{
			isPublished = article.isPublished();

		} catch (Exception e)
		{
			isPublished = false;
		}

		return accordion.buildAccordionItem(
		    //
		    accordion.buildHeaderH2("Publish"), //

		    accordion.buildBody(
		        //
		        input().withType("checkbox").withName("publish")
		            .withValue("true").withId("publish")
		            .attr(iff(isPublished, "checked")),

		        LabelBuilder.create().//
		            build("Artikel veröffentlichen", "publish")
			// ,
			// label("Anzeigen ab: ").attr("for", "start_date"),
			// textInput("start_date", "placeholder", "start_date")
			// ),
			// label("Anzeigen bis: ").attr("for", "end_date"),
			// textInput("end_date", "placeholder", "end_date")
			// ),
		    )
		);
	}

	private DomContent
	    tastaturNavigationPanel(ArticleJDBCFlat article, Accordion accordion)
	{
		return accordion.buildAccordionItem(
		    //
		    accordion.buildHeaderH2("Keyboard Navigation"), //

		    accordion.buildBody(
		        //
		        ul(
		            li(
		                input().withType("text").withName("robotTags")
		                    .withValue("robotTags").withPlaceholder("robot")
		            ), li()
		        )
		    )
		);
	}

	private DomContent footerSection()
	{
		return section(
		    FooterNavigationBuilder.create()//
		        .withSaveAndCloseOption()//
		        .withSaveOption()//
		        .withReturnOption()//
		        .withAlignment(RowAlignmentDV.END)//
		        .build()
		).withClass("container mt-3");
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
