package com.bueffeltier.ui.webapp.content.view;

import static com.bueffeltier.ui.html.molecule.customTag.*;
import static j2html.TagCreator.*;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.bueffeltier.data.jdbc.ElementJDBCFlat;
import com.bueffeltier.data.jdbc.PageJDBCFlat;
import com.bueffeltier.logic.foundation.pagetree.SiteRepository;
import com.bueffeltier.ui.html.Layout;

import j2html.tags.DomContent;

/**
 *
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
public class EditPageView extends AbstractView
{
	private static EditPageView instance;

	private SiteRepository siteRepository = SiteRepository.getInstance();

	private EditPageView()
	{
		super();
	}

	public static EditPageView getInstance()
	{
		if (instance == null)
		{
			instance = new EditPageView();
		}
		return instance;
	}

	@Override
	public DomContent
	    writeHtml(ElementJDBCFlat element, HttpServletRequest request)
	{
		List<String> layouts = Layout.getLayoutNames();

		PageJDBCFlat page = siteRepository.read(1l);

		long id = page.getId();
		String title = page.getHtmlTitle();
		String path = page.getPath();
		int layout = page.getLayout();
		int permission = page.getPermission();
		String forwardTo = page.getForwardTo();
		String author = page.getAuthor();
		String cacheTime = page.getCacheTime();
		boolean createSitemap = page.createSitemap();
		String cssClass = page.getCssClass();
		String description = page.getDescription();
		String pageType = page.getPageType();
		boolean hideInNav = page.isHiddenInNav();
		boolean includeInCache = page.includeCache();
		boolean includeLayout = page.includeLayout();
		boolean isProtected = page.isProtected();
		String keywords = page.getKeywords();
		String language = page.getLanguage();
		String lastVersion = page.getLastVersion();
		boolean isPublished = page.isPublished();
		String sitemapName = page.getSitemapName();
		boolean noFollow = page.noFollow();
		boolean noIndex = page.noIndex();
		String internalName = page.getInternalName();
		String urlAlias = page.getUrlAlias();

		return form(
		    "test", previewSection(), msgSection(""), pageDataSection(page),
		    metaInfoSection(page), accessSection(page)
//				,
//				layoutDataSection(page)
		    , cacheDataSection(page), internalSettingsSection(), finalSection()
			// tastaturNavigationPanel()
		).withClass("mainPanel");
	}

	private DomContent previewSection()
	{
		return section(
		    h2("Preview"), submitButton("preview", "preview", "preview")
		).withClass("panel");
	}

	private DomContent msgSection(String info)
	{
		return section(
		    h2("Message"),
		    iff(
		        info != null,
		        p(info).withStyle("background-color: tomato").withId("msg")
		    )
		).withClass("panel");
	}

	private DomContent pageDataSection(PageJDBCFlat page)
	{
		return section(
		    h2("Internal Data"),
		    ul(
		        li(p("Page-Id?")),
		        li(
		            label("Interner Seitenname").attr("for", "pagename-input"),
		            inputText(
		                "page-name", page.getInternalName(), "pagename-input"
		            )
		        ),
		        li(
		            label("URL-Alias").attr("for", "seitenalias-input"),
		            inputText(
		                "page-alias", page.getUrlAlias(), "seitenalias-input"
		            )
		        ),
		        li(
		            input().withType("radio").withName("page-type")
		                .withValue("page-type").withId("regular-page").attr(
		                    iffElse(
		                        "RESPOND".equals(page.getPageType()), "checked",
		                        "RESPOND"
		                    )
		                ),
		            label("Reguläre Seite").attr("for", "regular-page")
		        ),
		        li(
		            input().withType("radio").withName("page-type")
		                .withValue("page-type").withId("int-fwd").attr(
		                    iffElse(
		                        "INTERNAL_FWD".equals(page.getPageType()),
		                        "checked", ""
		                    )
		                ),
		            label("Interne Weiterleitung").attr("for", "int-fwd")
		        ),
		        li(
		            input().withType("radio").withName("page-type")
		                .withValue("page-type").withId("ext-fwd").attr(
		                    iffElse(
		                        "EXTERNAL_FWD".equals(page.getPageType()),
		                        "checked", ""
		                    )
		                ),
		            label("Externe Weiterleitung").attr("for", "ext-fwd")
		        ),
		        li(
		            input().withType("radio").withName("page-type")
		                .withValue("page-type").withId("403"),
		            label("403 Zugriff verweigert").attr("for", "403")
		        ),
		        li(
		            input().withType("radio").withName("page-type")
		                .withValue("page-type").withId("404"),
		            label("404 Seite nicht gefunden").attr("for", "404")
		        )
		    )
		).withClass("panel");
	}

	private DomContent metaInfoSection(PageJDBCFlat page)
	{
		// todo: https://ahrefs.com/blog/de/meta-robots/
		return section(
		    h2("Meta Info"),
		    ul(
		        li(
		            label("HTML-Title (55 Zeichen)")
		                .attr("for", "page-title-input"),
		            inputText(
		                "page-title", page.getHtmlTitle(), "page-title-input"
		            )
		        ),
		        li(
		            label("Description (160 Zeichen)")
		                .attr("for", "description"),
		            textarea(page.getDescription()).attr("name", "description")
		        ),
		        li(
		            label("keywords").attr("for", "keyword-input"),
		            inputText("keywords", page.getKeywords(), "keywords-input")
		        ),
		        li(
		            input().withType("radio").withName("robots")
		                .withValue("nofollow").withId("nofollow")
		                .attr(iffElse(page.noFollow() == true, "checked", "")),
		            label("index, nofollow").attr("for", "nofollow")
		        ),
		        li(
		            input().withType("radio").withName("robots")
		                .withValue("noindex").withId("noindex")
		                .attr(iffElse(page.noIndex() == true, "checked", "")),
		            label("index, noindex").attr("for", "noindex")
		        )
		    )
		).withClass("panel");

	}

	private DomContent accessSection(PageJDBCFlat page)
	{

		return section(
		    h2("Access"),
		    ul(
		        li(
		            input().withType("radio").withName("permission")
		                .withValue("0").withId("no-log").attr(
		                    iffElse(page.getPermission() == 0, "checked", "")
		                ),
		            label("All Users").attr("for", "no-log")
		        ),
		        li(
		            input().withType("radio").withName("permission")
		                .withValue("1").withId("logged").attr(
		                    iffElse(page.getPermission() == 1, "checked", "")
		                ),
		            label("Eingeloggt").attr("for", "logged")
		        ),
		        li(
		            input().withType("radio").withName("permission")
		                .withValue("2").withId("admin").attr(
		                    iffElse(
		                        page.getPermission() == 2, // todo:
		                                                   // permissions
		                                                   // anhand
		                                                   // request?
		                        "checked", ""
		                    )
		                ),
		            label("Admin").attr("for", "admin")
		        )
		    )
		).withClass("panel");
	}

//	private DomContent layoutDataSection(PageJDBCFlat page)
//	{
//		return section(
//				h2("Layout"),
//				form(
//						"test",
//						select(
//								each(
//										layouts,
//										layout -> option(layout.toString())
//								)
//						).attr("name", "layout")
//						// .attr("size", this.layouts.size())
//				)
//		).withClass("panel");
//	}

	private DomContent cacheDataSection(PageJDBCFlat page)
	{
		return section(
		    h2("Caching"),
		    ul(
		        li(
		            input().withType("checkbox").withName("cache-time")
		                .withValue(page.getCacheTime()).withId("cache-time"),
		            label("Caching-Time").attr("for", "cache-time")
		        )
		    )
		).withClass("panel");
	}

	private DomContent internalSettingsSection()
	{
		return section(
		    h2("Internal Settings"),
		    ul(
		        li(
		            input().withType("checkbox").withName("no-search")
		                .withValue("no-search").withId("no-search"),
		            label("Nicht durchsuchen").attr("for", "no-search")
		        ),
		        li(
		            label("Nicht durchsuchen").attr("for", "css-class"),
		            input().withType("text").withName("css-class")
		                .withValue("css-class").withId("css-class")
		        ),
		        li(
		            input().withType("checkbox").withName("add-to-sitemap")
		                .withValue("add-to-sitemap").withId("add-to-sitemap"),
		            label("In der Sitemap anzeigen")
		                .attr("for", "add-to-sitemap")
		        ),
		        li(
		            input().withType("checkbox").withName("hide-nav")
		                .withValue("hide-nav").withId("hide-nav"),
		            label("In der Seitennavigation verstecken")
		                .attr("for", "hide-nav")
		        ),
		        li(
		            input().withType("checkbox").withName("publish")
		                .withValue("publish").withId("publish"),
		            label("Seite veröffentlichen").attr("for", "publish")
		        )
		    )
		).withClass("panel");
	}

	private DomContent keyboardNavigationSection()
	{
		return section(
		    ul(
		        h2("Keyboard Navigation"),
		        li(
		            input().withType("text").withName("robotTags")
		                .withValue("robotTags").withPlaceholder("robot")
		        ), li()
		    )
		).withClass("panel");
	}

	// todo: save mit AJAX realisieren.
	private DomContent finalSection()
	{
		return section(
		    ul(
		        li(
		            submitButton(
		                "Save and Close", "save-and-close", "save-and-close"
		            ), submitButton("Save", "save", "save"),
		            submitButton("Exit", "exit", "exit")
		        )
		    )
		).withClass("panel");
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
