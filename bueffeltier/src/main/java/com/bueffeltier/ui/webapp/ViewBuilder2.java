package com.bueffeltier.ui.webapp;

import static j2html.TagCreator.*;

import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import com.bueffeltier.crosscutting.AppPropertyService;
import com.bueffeltier.data.jdbc.ArticleJDBCFlat;
import com.bueffeltier.data.jdbc.ElementJDBCFlat;
import com.bueffeltier.data.jdbc.PageJDBCFlat;
import com.bueffeltier.ui.webapp.content.ViewRegistry;
import com.bueffeltier.ui.webapp.content.view.ViewHandler;

import j2html.Config;
import j2html.tags.DomContent;
import j2html.tags.specialized.DivTag;
import j2html.tags.specialized.HeadTag;

public class ViewBuilder2
{
	private static ViewBuilder2 instance = null;

	ViewRegistry elementTypeViewHandlerRegister = ViewRegistry.getInstance();

	AppPropertyService appProperties = AppPropertyService.getInstance();

	private ViewBuilder2()
	{
		//
	}

	public static ViewBuilder2 getInstance()
	{
		if (instance == null)
		{
			instance = new ViewBuilder2();
		}

		return instance;
	}

	public String buildView(PageJDBCFlat page, HttpServletRequest request)
	    throws Exception
	{

//		List<DomContent> modals = new ArrayList<>();
//
//		List<ElementJDBCFlat> elements = new ArrayList<>();
//
//		List<ArticleJDBCFlat> allArticles = new ArrayList<>();
//
//		for (ArticleJDBCFlat article : page.getHeaderArticles())
//		{
//			elements.addAll(article.getElements());
//		}
//		
//		for (ArticleJDBCFlat article : page.getMainArticles())
//		{
//			elements.addAll(article.getElements());
//		}
//		
//		for (ArticleJDBCFlat article : page.getFooterArticles())
//		{
//			elements.addAll(article.getElements());
//		}

		// Seitenstringbuilder anlegen
//		StringBuilder viewStringBuilder = null;

//		String documentString;
//		String doctype = "<!DOCTYPE html>\n";

		return document().render() + html(
		    buildHead(page), //
		    buildBody(page, request)
		).withLang("en").renderFormatted();

//		 .renderFormatted();
//		documentString = doctype + j2htmlString;

//		return documentString;
	}

	private HeadTag buildHead(PageJDBCFlat page) throws Exception
	{
		Random random = new Random();
		String randomString;
		int n = random.nextInt(123456);
		randomString = Integer.toString(n);

//        Config.textEscaper.escape("text");
		Config.textEscaper = text -> text;

		return head(
		    meta().withName("charset").withContent("text/html; charset=UTF-8"),
		    meta().withName("http-equiv")
		        // todo: cahe wert aus page laden.
		        // https://www.sistrix.de/frag-sistrix/onpage/meta-tags/http-equiv
		        .withContent("\"cache-control\" content=\"max-age=60"),
		    iff(
		        page.includeCache(),
		        meta().withName("expires").withContent("content=\"0\"")
		    ),
		    // TODO sveng 26.11.2022: meta:
		    // https://stackoverflow.com/questions/4417923/html-meta-tag-for-content-language
		    // TODO sveng 13.11.2022: nicht mehr als 250 Zeichen zulassen.
		    meta().withName("description").withContent(page.getDescription()),
		    meta().withName("keywords").withLang("de")
		        .withContent(page.getKeywords()),
		    meta().withName("viewport").withContent(
		        "width=device-width, initial-scale=1.0, minimum-scale=1.0"
		    ),
		    /*
		     * noindex und nofollow werden bei Bedarf gesetzt: todo:
		     * Random-String in Production unbedingt entfernen.
		     */
		    iff(
		        page.noIndex() == true,
		        meta().withName("robots").withContent("noindex")
		    ),
		    iff(
		        page.noFollow() == true,
		        meta().withName("robots").withContent("nofollow")
		    ),
		    link().withRel("icon").withHref(
		        appProperties.getHostName()
		            + appProperties.getServletContextPath()
		            + appProperties.getFaviconPath()
		    ),

		    // TODO sveng 25.02.2023: Bootstrap aus properties settings
		    // laden.
		    link()//
		        .withRel("stylesheet")//
		        .withHref(
		            "https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
		        )//
		        .attr(
		            "integrity",
		            "sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD"
		        )//
		        .attr("crossorigin", "anonymous")//
			//
		    ,

//				link().withRel("stylesheet")
//						.withHref("bootstrap-5.1.3-dist/css/bootstrap.min.css")
//						,
		    link().withRel("stylesheet").withHref(
		        appProperties.getHostName()
		            + appProperties.getServletContextPath()
		            + "/css/styles.css?v=" + randomString
		    ),
		    // todo: settings
		    link().withRel("stylesheet").withHref(
		        "https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"
		    ),
		    /*
		     * todo: theme-color für mobile-browser einbinden. theme-color wird
		     * aus layout geladen. Farbe kann ggf. aus Seite geladen werden:
		     * appProperties.getMobileBrowserAppLayoutColor()
		     */
		    title(page.getHtmlTitle()),
		    meta().withCharset(appProperties.getHtmlMetaCharset()),
		    title("Title")// todo: was macht
			// titel hier?
		).withLang(appProperties.getHtmlLang());
	}

	private DomContent buildBody(PageJDBCFlat page, HttpServletRequest request)
	    throws Exception
	{
		/*
		 * TODO: Modal Unbenutzte Tags und Attribute nullen.....................
		 * Rückgabe-Werte ok, abbrechen mit JS abfangen? Grundmethode dafür?
		 * Immer Bestandteil der Seite? Wie von View-Handler ansprechbar?......
		 * Statischer Hintergrund einbauen.....................................
		 * Scrollable..........................................................
		 * vertically centered.................................................
		 * verschiedene dom content anbieten mit Grid-System...................
		 * Wenn mehrere Modals anzuzeigen sind, dann kann man diese Toggeln,
		 * d.h. sie können nacheinander abgerufen werden? Oder hin und her? WIe
		 * bei Cookies?........................................................
		 * von handlern injizieren
		 */

		/*
		 * TODO Umbau - 2 Varianten:
		 * 
		 * 1. Alle Seitendaten, Artikel Elemente, Views laden. Dann erst bauen.
		 * 
		 * 2. Modale Dialoge anbieten
		 * 
		 * 
		 * 
		 * 
		 * 
		 */

////		hier alle elemente abrufen, für den modal
//		List<DomContent> modals;
//
//		List<ArticleJDBCFlat> articles = page.getMainArticles();
//
//		for (ArticleJDBCFlat article : articles)
//		{
//			List<ElementJDBCFlat> elements = article.getElements();
//
//			for (ElementJDBCFlat element : elements)
//			{
//				modals.add(element.get)
//			}
//		}

//		
//		for(ElementJDBC element:elements) {
//				modals.addAll(element.getModalsOpt());
//				if(element.hasModalOpt()).setId();
//		}

		return body(

		    // Modaler Dialog:
		    button("Launch demo Modal").withType("button")
		        .withClass("btn btn-primary").attr("data-bs-toggle", "modal")
		        .attr("data-bs-target", "#exampleModal"),
//		    ModalBuilder.create().build(
//		        "Modaler Dialog", "Bitte sofort lesen!", "exampleModal", "id",
//		        null
//		    ),

		    div(

		        // showAlert(request),
		        showCookieBanner(request)

		        , div(
		            // noScriptMsg(),
		            buildHeader(page.getHeaderArticles(), request), //
		            buildMainLayoutArea(page.getMainArticles(), request), //
		            buildFooter(page.getFooterArticles(), request)
		        ).withClass("page-content")
		    ).withClass("page"), buildScriptImports(request)
		).withClass("body");
	}

	private DomContent showCookieBanner(HttpServletRequest request)
	{

		boolean browserCookiesAreEnabled = true;

		if (browserCookiesAreEnabled)
		{
			// todo: texte in settings
			String description = """
			    Diese Website verwendet Cookies und ähnliche Technologien
			    und verarbeitet personenbezogene Daten über dich.
			    Du kannst dich hier über die Verarbeitung deiner Daten informieren
			    und auswählen, welche Einwilligungen du uns geben geben möchtest.
			    Du kannst deine Einwilligung später jederzeit ändern oder widerrufen.

			    Weitere Informationen zum Thema Datenschutz findest du unter folgenden Links:
			    """;

			String descriptionEssential = """
			    Diese Website verwendet die für den Betrieb ihrer wesentlichen
			    Funktionen erforderlichen, technisch notwendigen Cookies.

			    Weitere Informationen zum Thema Datenschutz findest du unter folgenden Links:
			    """;

			return div(
			    div(
			        div(
			            div(h4("Datenschutzeinstellungen"))
			                .withClass("cookie-consent-banner__header"),
			            div(descriptionEssential)
			                .withClass("cookie-consent-banner__description"),
			            div(
			                a("Datenschutz")
			                    .withHref("/bueffeltier/datenschutzerklaerung"),
			                a("Impressum").withHref("/bueffeltier/impressum")
			            )
			        ).withClass("cookie-consent-banner__copy"),
			        div(
			            a("OK").withClass("cookie-consent-banner__cta")
			                .withId("cookie-consent-banner_btn_accept")
//									a("Akzeptieren").withHref("www.google.de")
//											.withClass(
//													"cookie-consent-banner__cta"
//											),
//									a("Ablehnen").withHref(
//											"www.google.de"
//									).withClass(
//											"cookie-consent-banner__cta cookie-consent-banner__cta--secondary"
//									)
			        ).withClass("cookie-consent-banner__actions")
			    ).withClass("cookie-consent-banner__inner")
			).withClass("cookie-consent-banner")
			    .withId("cookie-consent-banner");
		}
		return null;
	}

	// TODO sveng 18.01.2023:aus effizienzgründen immer in den queslltext
	// einbauen?
//	private DomContent showAlert(HttpServletRequest request)
//	{
//		if (true)
//		{
//			// todo: alerts aus BootstrapTags nehmen.
//			div(appProperties.getAlertBrowserCookiesDisabledMessage())
//					.withClasses("alert", "alert-primary")
//					.attr("role", "alert");
//		}
//	}

	private DomContent buildHeader(
	    List<ArticleJDBCFlat> headerArticles,
	    HttpServletRequest request
	) throws Exception
	{
		if (headerArticles == null || headerArticles.isEmpty())
		{
			return header();
		}
		try
		{
			return header(
			    each(
			        headerArticles,
			        article -> getArticleInContainerTag(article, request)
			    ), hr().withClass("header_line")
			).withClass("header");

		} catch (Exception e)
		{
			throw e;

		}
	}

	private DomContent buildMainLayoutArea(
	    List<ArticleJDBCFlat> mainArticles,
	    HttpServletRequest request
	) throws Exception
	{
		if (mainArticles == null || mainArticles.isEmpty())
		{
			return main();
		}
		try
		{
			return main(
			    each(
			        mainArticles,
			        article -> getArticleInContainerTag(article, request)
			    )
			).withClass("main");
		} catch (Exception e)
		{
			// todo fehlerseite oder fehlertext?
			throw e;
		}
	}

	private DomContent buildFooter(
	    List<ArticleJDBCFlat> footerArticles,
	    HttpServletRequest request
	) throws Exception
	{
		if (footerArticles == null || footerArticles.isEmpty())
		{
			return footer();
		}
		try
		{
			return footer(
			    each(
			        footerArticles,
			        article -> getArticleInContainerTag(article, request)
			    )
			).withClass("footer");
		} catch (Exception e)
		{
			throw e;
		}
	}

	private DomContent getArticleInContainerTag(
	    ArticleJDBCFlat article,
	    HttpServletRequest request
	)
	{
		switch (article.getContainerTag()) {

		case "div":
			return div(loadContentElements(article, request))
			    .withClass(article.getCssClass());
		// case "section":
		case "article":
			return article(loadContentElements(article, request))
			    .withClass(article.getCssClass());
		default:
			return html(loadContentElements(article, request))
			    .withClass(article.getCssClass());
		}
	}

	private DomContent
	    loadContentElements(ArticleJDBCFlat article, HttpServletRequest request)
	{
		List<ElementJDBCFlat> elements = article.getElements();
		return each(elements, element -> getContentTag(element, request));

//        return each(
//                filter(contentElements, content -> content != null), content
//                -> getHtmlOrTagContentFromElement(content)
//        );
	}

	private DomContent
	    getContentTag(ElementJDBCFlat element, HttpServletRequest request) // throws
	                                                                       // Exception
	{
		DomContent tag = new DivTag();

		try
		{
			tag = getHtmlOrInsertTagFromElement(element, request);

		} catch (Exception e)
		{
			String x = e.getMessage();
		}
		return tag;
	}

	private DomContent getHtmlOrInsertTagFromElement(
	    ElementJDBCFlat element,
	    HttpServletRequest request
	) throws Exception
	{
		if (element.isDynamicHtml())
		{
			ViewHandler handler = getContentHandler(element, request);

			return handler.writeInternalHtml(element, request);

		} else
		{
			// TODO sveng 13.11.2022: Insert-Tags einfügen/ersetzen
//          return this.replaceContentTags(content.getHtml());
			return div(element.getHtml());
		}
	}

	private ViewHandler
	    getContentHandler(ElementJDBCFlat element, HttpServletRequest request)
	        throws Exception
	{
		ViewHandler contentTypeHandler = elementTypeViewHandlerRegister
		    .getViewHandler(element, request);

		return contentTypeHandler;
		// todo: throw exception!
	}

	private DomContent buildScriptImports(HttpServletRequest request)
	{
		// TODO sveng 25.02.2023: bootstrap js from settings
		appProperties.getHtmlScriptPaths();

		return div(
		    buildInternalScript(request),
		    each(
		        appProperties.getHtmlScriptPaths(),
		        scriptPath -> script().withSrc(scriptPath)
		            .withType("text/javascript")
		    )
		).with(
		    script()//
		        .withSrc(
		            // TODO sveng 15.03.2023: settings
		            "https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
		        )//
		        .attr(
		            "integrity",
		            "sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
		        )//
		        .attr("crossorigin", "anonymus")
		);
	}

	private DomContent buildInternalScript(HttpServletRequest request)
	{
		DomContent internalScript = null;

		String scriptString = (String) request.getAttribute("internalScript");

		if (scriptString != null)
		{
			internalScript = script(scriptString);
		}

		return internalScript;
	}

//  private String processContentTag(String key, String value)
//  {
//      switch (key)
//      {
////              case "link":
////                  // todo: ContentHandler (LinkHandler) programmieren?
////                  // Muss die Permission für den Link geprüft werden?
////                  // Wie wird der link dann angezeigt?
//////                  log.add(this, "tagKey: = " + tagKey);
////
////                  PageTreeController pageTreeController
////                          = new PageTreeController();
////
////                  if (tagValue.matches("\\d+"))
////                  {
////                      // Wenn tagKey einen Integer-Wert enthält:
////                      try
////                      {
////                          Page urlPage = pageTreeController.getPageUnchecked(
////                                  Integer.parseInt(tagValue)
////                          );
////
////                          contentString = matcher.replaceFirst(
////                                  "<a href=\""
////                                  + pageTreeController.buildUrl(
////                                          Integer.parseInt(tagValue))
////                                  + "\">"
////                                  + urlPage.getHtmlTitle()
////                                  + "</a>");
////
////                      } catch (Exception e)
////                      {
////                          contentString = "";
////                      }
////
////                      // Link wird eingefügt:
////                  } else
////                  {
////                      // Wenn tagKey keinen Integer-Wert enthält:
////
////                      /*
////                       * todo: Soll ich den eingegebenen String darstellen,
////                       * oder nichts?
////                       */
////                      contentString = "";
////                  }
////
////                  break;
//
//          case "content":
//              int contentId = Integer.parseInt(value);
//
//              ContentTypeHandler contentTypeHandler
//                      = this.getContentTypeHandler(contentId);
//
//              return  = contentTypeHandler.writeInternalHtml();
//
//      }
//  }
}
