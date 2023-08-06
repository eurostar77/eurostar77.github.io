package com.bueffeltier.ui.webapp.content.view;

import static j2html.TagCreator.*;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.bueffeltier.data.jdbc.ArticleJDBCFlat;
import com.bueffeltier.data.jdbc.ElementJDBCFlat;
import com.bueffeltier.data.jdbc.Page;
import com.bueffeltier.data.jdbc.UserJDBC;
import com.bueffeltier.logic.foundation.pagetree.SiteRepository;
import com.bueffeltier.ui.html.organism.Accordion;
import com.bueffeltier.ui.html.organism.ButtonBuilder;
import com.bueffeltier.ui.html.organism.ButtonBuilder.ButtonTypeDV;
import com.bueffeltier.ui.html.organism.ButtonBuilder.ColorDV;
import com.bueffeltier.ui.html.organism.ColumnBuilder;
import com.bueffeltier.ui.html.organism.ColumnBuilder.GridWidthDV;
import com.bueffeltier.ui.html.organism.FooterNavigationBuilder;
import com.bueffeltier.ui.html.organism.FormControlBuilder;
import com.bueffeltier.ui.html.organism.FormControlBuilder.FormInputTypeDV;
import com.bueffeltier.ui.html.organism.LabelBuilder;
import com.bueffeltier.ui.html.organism.RowBuilder;
import com.bueffeltier.ui.html.organism.RowBuilder.RowAlignmentDV;

import j2html.tags.DomContent;

/**
 *
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
public class PageEditorView extends AbstractView
{
	private static PageEditorView instance;

	private SiteRepository siteRepository = SiteRepository.getInstance();

	private PageEditorView()
	{
		super();
	}

	public static PageEditorView getInstance()
	{
		if (instance == null)
		{
			instance = new PageEditorView();
		}
		return instance;
	}

	@Override
	public DomContent
	    writeHtml(ElementJDBCFlat element, HttpServletRequest request)
	{
		Long pageId = null;

		Page page = null;

		String headline;

		String pageIdString = getViewDataValueOpt(request, "pageId");

		// TODO sveng 04.07.2023: UUID umwandeln

		if (pageIdString != null)
		{
			pageId = Long.parseLong(pageIdString);

		} else
		{

		}

		if (pageId != null)
		{
			page = siteRepository.read(pageId);

			headline = "Edit Page-ID " + pageId;

		} else
		{
			// TODO sveng 21.06.2023: default Werte für neue Seite sinnvoll
			// setzen! Ggf. create Methode bauen.
			page = new Page();

			pageIdString = "NEW";

			headline = "Edit New Page-ID";
		}

		Accordion accordion = new Accordion("editPage");

		return form(

		    element.getType(), //

		    input()//
		        .withType("hidden")//
		        .withName("pRef")//
		        .withValue(Long.toString(page.getId())), //

		    h1(headline).withClass("h1 mb-3"),

		    accordion.buildAccordionContainer(
		        // previewSection(), //
		        articlesSection(page, accordion), //
		        pageDataSection(page, pageIdString, request, accordion), //
		        metaInfoSection(page, accordion), //
		        accessSection(page, accordion),
		        // layoutDataSection(page, accordion),//
		        cacheDataSection(page, accordion), //
		        internalSettingsSection(page, accordion), //
		        footerSection()
			// tastaturNavigationPanel()
		    )

		).withClass("mainPanel accordion");
	}

	private DomContent previewSection()
	{
		return RowBuilder.create()//
		    .withAlignment(RowAlignmentDV.END)//
		    .withDomContent(
		        //
		        ButtonBuilder.create()//
		            .withName("Vorschau")//
		            .build()//
		    )//
		    .build();
	}

	private DomContent articlesSection(Page page, Accordion accordion)
	{
		List<ArticleJDBCFlat> articles = page.getMainArticles();

		if (articles == null || articles.isEmpty())
		{
			return null;

		} else
		{
			return accordion.buildAccordionItem(
			    //
			    accordion.buildItemHeaderH2("Articles"), //

			    accordion.buildItemBody(
			        //
			        each(
			            //
			            articles, a -> //
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
						                    .withText(a.getTitle())//
						                    .withName("article")//
						                    .withValue(Long.toString(a.getId()))//
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

	private DomContent pageDataSection(
	    Page page,
	    String pageIdString,
	    HttpServletRequest request,
	    Accordion accordion
	)
	{
		String authorString;

		if (page == null)
		{
			authorString = ((UserJDBC) request.getAttribute("user")).getName();

		} else
		{
			authorString = page.getAuthor();
		}

		return accordion.buildAccordionItem(

		    accordion.buildItemHeaderH2("Internal Data"), //

		    accordion.buildItemBody(

		        p("Page-Id: " + pageIdString), //

		        input()//
		            .withName("author")//
		            .withValue(authorString)//
		            .isHidden(),

		        p("Author: " + authorString), //

		        //
		        LabelBuilder.create().//
		            build("Interner Seitenname", "internalTitleInput"), //

		        FormControlBuilder.create()//
		            .withName("internalTitle")//
		            .withValue(page.getInternalName())//
		            .withId("internalTitleInput")//
		            .build(),

//	    			            //
//	    			            LabelBuilder.create().//
//	    			                build("URL-Alias", "seitenaliasInput"), //
				//
//	    			            FormControlBuilder.create()//
//	    			                .withName("seitenalias")//
//	    			                .withValue(page.getUrlAlias())//
//	    			                .withId("seitenaliasInput")//
//	    			                .build()
//	    			        ,
		        //
		        LabelBuilder.create().//
		            build("Relative Path", "pagePathInput"), //

		        FormControlBuilder.create()//
		            .withName("pagePath")//
		            .withValue(page.getPath())//
		            .withId("pagePathInput")//
		            .build(),

		        ul(

		            label(
		                input()//
		                    .withType("radio")//
		                    .withName("pageType")//
		                    .withValue("RESPOND")//
		                    .withId("pageTypeInput1") //
		                    .withClass("form-check-input me-1") //
		                    .withCondChecked(
		                        "RESPOND".equals(page.getPageType())
		                    ) //
		            )//
		                .withText("Reguläre Seite")//
		                .withFor("pageTypeInput1")//
		                .withClass("list-group-item"), //

		            label(
		                input()//
		                    .withType("radio")//
		                    .withName("pageType")//
		                    .withValue("INTERNAL_FWD")//
		                    .withId("pageTypeInput2") //
		                    .withClass("form-check-input me-1") //
		                    .withCondChecked(
		                        "INTERNAL_FWD".equals(page.getPageType())
		                    ), //

		                div(
		                    //
		                    LabelBuilder.create().//
		                        build(
		                            "Interner Weiterleitungs-Pfad",
		                            "intFwdPathInput"
		                        ), //

		                    FormControlBuilder.create()//
		                        .withName("intFwdPath")//
		                        .withOnClick("showFwdPathInput('option1')")//
		                        .withType(FormInputTypeDV.TEXT)//
		                        .withId("intFwdPathInput")//
		                        .build()

		                ).withStyle("display: none;")
		                    .withId("intFwdPathInputCont")
		            )//
		                .withText("Interne Weiterleitung")//
		                .withFor("pageTypeInput2")//
		                .withClass("list-group-item"), //

		            label(
		                input()//
		                    .withType("radio")//
		                    .withName("pageType")//
		                    .withValue("EXTERNAL_FWD")//
		                    .withId("pageTypeInput3") //
		                    .withClass("form-check-input me-1") //
		                    .withCondChecked(
		                        "EXTERNAL_FWD".equals(page.getPageType())
		                    ), //

		                div(
		                    //
		                    LabelBuilder.create().//
		                        build(
		                            "Externe Weiterleitungs-URL:",
		                            "extFwdPathInput"
		                        ), //

		                    FormControlBuilder.create()//
		                        .withName("extFwdPath")//
		                        .withOnClick("showFwdPathInput('option2')")//
		                        .withType(FormInputTypeDV.TEXT)//
		                        .withId("extFwdPathInput")//
		                        .build()

		                ).withStyle("display: none;")
		                    .withId("extFwdPathInputCont")
		            )//
		                .withText("Externe Weiterleitung")//
		                .withFor("pageTypeInput3")//
		                .withClass("list-group-item"), //

		            label(
		                input()//
		                    .withType("radio")//
		                    .withName("pageType")//
		                    .withValue("403")//
		                    .withId("pageTypeInput4") //
		                    .withClass("form-check-input me-1") //
		                    .withCondChecked("403".equals(page.getPageType())) //
		            )//
		                .withText("403 Zugriff verweigert")//
		                .withFor("pageTypeInput4")//
		                .withClass("list-group-item"), //

		            label(
		                input()//
		                    .withType("radio")//
		                    .withName("pageType")//
		                    .withValue("404")//
		                    .withId("pageTypeInput5") //
		                    .withClass("form-check-input me-1") //
		                    .withCondChecked("404".equals(page.getPageType())) //
		            )//
		                .withText("404 Seite nicht gefunden")//
		                .withFor("pageTypeInput5")//
		                .withClass("list-group-item") //

		        ).withClass("list-group list-group-flush")

		    )//
		);
	}

	private DomContent metaInfoSection(Page page, Accordion accordion)
	{
		// todo: https://ahrefs.com/blog/de/meta-robots/

		return accordion.buildAccordionItem(

		    accordion.buildItemHeaderH2("Meta Info"), //

		    accordion.buildItemBody(

		        LabelBuilder.create().//
		            build("HTML-Title (55 Zeichen)", "htmlTitleInput"), //

		        FormControlBuilder.create()//
		            .withName("htmlTitle")//
		            .withValue(page.getHtmlTitle())//
		            .withId("htmlTitleInput")//
		            .build(), //

		        LabelBuilder.create().//
		            build("Description (160 Zeichen)", "descriptionInput"), //

		        FormControlBuilder.create()//
		            .withType(FormInputTypeDV.TEXTAREA)//
		            .withRows(3)//
		            .withText(page.getDescription())//
		            .withName("description")//
		            .withValue(page.getDescription())//
		            .withId("descriptionInput")//
		            .build(), //

		        LabelBuilder.create().//
		            build("keywords", "keywordsInput"), //

		        FormControlBuilder.create()//
		            .withName("keywords")//
		            .withValue(page.getKeywords())//
		            .withId("keywordsInput")//
		            .build(),

		        ul(
		            label(
		                input()//
		                    .withType("checkbox")//
		                    .withName("noindex")//
		                    .withValue("true")//
		                    .withId("noindexCb") //
		                    .withClass("form-check-input me-1") //
		                    .withCondChecked(page.noIndex()) //
		            )//
		                .withText("noindex")//
		                .withFor("noindexCb")//
		                .withClass("list-group-item"), //

		            label(
		                input()//
		                    .withType("checkbox")//
		                    .withName("nofollow")//
		                    .withValue("true")//
		                    .withId("nofollowCB") //
		                    .withClass("form-check-input me-1") //
		                    .withCondChecked(page.noFollow()) //
		            )//
		                .withText("nofollow")//
		                .withFor("nofollowCB")//
		                .withClass("list-group-item") //

		        ).withClass("list-group list-group-flush")
		    )
		);
	}

	private DomContent accessSection(Page page, Accordion accordion)
	{
		int permission = page.getPermission();

		if (permission < 0 || permission > 5)
		{
			permission = 5;
		}

		return accordion.buildAccordionItem(

		    accordion.buildItemHeaderH2("Access"), //

		    accordion.buildItemBody(

		        ul(
		            label(
		                input()//
		                    .withType("radio")//
		                    .withName("permission")//
		                    .withValue("0")//
		                    .withId("permissionRb0") //
		                    .withClass("form-check-input me-1") //
		                    .withCondChecked(page.getPermission() == 0) //
		            )//
		                .withText("Anonymous User (not logged in)")//
		                .withFor("permissionRb0")//
		                .withClass("list-group-item"), //

		            label(
		                input()//
		                    .withType("radio")//
		                    .withName("permission")//
		                    .withValue("1")//
		                    .withId("permissionRb1") //
		                    .withClass("form-check-input me-1") //
		                    .withCondChecked(page.getPermission() == 1) //
		            )//
		                .withText("User")//
		                .withFor("permissionRb1")//
		                .withClass("list-group-item"), //

		            label(
		                input()//
		                    .withType("radio")//
		                    .withName("permission")//
		                    .withValue("2")//
		                    .withId("permissionRb2") //
		                    .withClass("form-check-input me-1") //
		                    .withCondChecked(page.getPermission() == 2) //
		            )//
		                .withText("Member")//
		                .withFor("permissionRb2")//
		                .withClass("list-group-item"), //

		            label(
		                input()//
		                    .withType("radio")//
		                    .withName("permission")//
		                    .withValue("3")//
		                    .withId("permissionRb3") //
		                    .withClass("form-check-input me-1") //
		                    .withCondChecked(page.getPermission() == 3) //
		            )//
		                .withText("Premium Member")//
		                .withFor("permissionRb3")//
		                .withClass("list-group-item"), //

		            label(
		                input()//
		                    .withType("radio")//
		                    .withName("permission")//
		                    .withValue("4")//
		                    .withId("permissionRb4") //
		                    .withClass("form-check-input me-1") //
		                    .withCondChecked(page.getPermission() == 4) //
		            )//
		                .withText("Author")//
		                .withFor("permissionRb4")//
		                .withClass("list-group-item"), //

		            label(
		                input()//
		                    .withType("radio")//
		                    .withName("permission")//
		                    .withValue("5")//
		                    .withId("permissionRb5") //
		                    .withClass("form-check-input me-1") //
		                    .withCondChecked(page.getPermission() == 5) //
		            )//
		                .withText("Admin")//
		                .withFor("permissionRb5")//
		                .withClass("list-group-item") //

		        ).withClass("list-group list-group-flush")

		    )
		);
	}

//	private DomContent layoutDataSection(PageJDBCFlat page, Accordion accordion)
//	{
//
//		return accordion.buildAccordionItem(
//
//		    accordion.buildHeaderH2("Layout"), //
//
//		    accordion.buildBody(
//
//		        form(
//		            "test",
//		            select(each(layouts, layout -> option(layout.toString())))
//		                .attr("name", "layout")
//					// .attr("size", this.layouts.size())
//		        )
//		    )
//		);
//	}

	private DomContent cacheDataSection(Page page, Accordion accordion)
	{
		String cachingTimeString = page.getCacheTime();

		if (StringUtils.isBlank(cachingTimeString))
		{
			cachingTimeString = "0";
		}

		return accordion.buildAccordionItem(

		    accordion.buildItemHeaderH2("Caching"), //

		    accordion.buildItemBody(

		        // max-age:
		        LabelBuilder.create().//
		            build("Caching-Time in Sekunden", "cacheTimeInput"), //

		        input()//
		            .withType("number")//
		            .withName("cacheTime")//
		            .withValue(cachingTimeString)//
		            .withClass("form-control")//
		            .withId("cacheTimeInput")//
		            .withStep("1")//

			// TODO sveng 08.07.2023: public einführen.
			// public (Steuert caching dann auch auf Proxy Server)

		    )
		);
	}

	private DomContent internalSettingsSection(Page page, Accordion accordion)
	{
		return accordion.buildAccordionItem(

		    accordion.buildItemHeaderH2("Internal Settings"), //

		    accordion.buildItemBody(

		        ul(
		            label(
		                input()//
		                    .withType("checkbox")//
		                    .withName("noSearch")//
		                    .withValue("true")//
		                    .withId("noSearchCb") //
		                    .withClass("form-check-input me-1") //
//		                .condAttr(page.hideInSearch(), "checked", "",)
		            )//
		                .withText("Nicht in Suche aufnehmen")//
		                .withFor("noSearchCb")//
		                .withClass("list-group-item"), //

		            label(
		                input()//
		                    .withType("checkbox")//
		                    .withName("addToSitemap")//
		                    .withValue("true")//
		                    .withId("addToSitemapCb")//
		                    .withClass("form-check-input me-1")//
		                    .condAttr(page.createSitemap(), "checked", "")
		            )//
		                .withText("In der Sitemap anzeigen")//
		                .withFor("addToSitemapCb")//
		                .withClass("list-group-item"), //

		            label(
		                input()//
		                    .withType("checkbox")//
		                    .withName("hideInNav")//
		                    .withValue("true")//
		                    .withId("hideNavCb") //
		                    .withClass("form-check-input me-1")//
		                    .condAttr(page.isHiddenInNav(), "checked", "")
		            )//
		                .withText("In der Seitennavigation verstecken")//
		                .withFor("hideNavCb")//
		                .withClass("list-group-item"), //

		            label(
		                input()//
		                    .withType("checkbox")//
		                    .withName("publish")//
		                    .withValue("true")//
		                    .withId("publishCb")//
		                    .withClass("form-check-input me-1")//
		                    .condAttr(page.isPublished(), "checked", "")
		            )//
		                .withText("Seite veröffentlichen")//
		                .withFor("publishCb")//
		                .withClass("list-group-item")//

		        ).withClass("list-group list-group-flush")

		    )
		);
	}

	private DomContent keyboardNavigationSection(Accordion accordion)
	{
		return accordion.buildAccordionItem(

		    accordion.buildItemHeaderH2("Keyboard Navigation"), //

		    accordion.buildItemBody(

		        //
		        input().//
		            withType("text")//
		            .withName("robotTags")//
		            .withValue("robotTags")//
		            .withPlaceholder("robot")
		    )
		);
	}

	private DomContent footerSection()
	{
		return section(
		    FooterNavigationBuilder.create()//
		        .withSaveAndCloseOption()//
		        .withSaveOption("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx")//
		        .withReturnOption()//
		        .withAlignment(RowAlignmentDV.END)//
		        .build()
		).withClass("container mt-3");
	}

	@Override
	public String writeCss()
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public DomContent writeHtmlControls()
	{
		return null;
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
