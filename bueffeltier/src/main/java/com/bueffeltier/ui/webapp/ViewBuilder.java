package com.bueffeltier.ui.webapp;

import static j2html.TagCreator.*;

import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import com.bueffeltier.crosscutting.AppPropertyService;
import com.bueffeltier.data.jdbc.ArticleJDBCFlat;
import com.bueffeltier.data.jdbc.ElementJDBCFlat;
import com.bueffeltier.data.jdbc.Page;
import com.bueffeltier.ui.html.organism.ModalBuilder;
import com.bueffeltier.ui.webapp.content.ViewRegistry;
import com.bueffeltier.ui.webapp.content.view.ViewHandler;

import j2html.tags.DomContent;
import j2html.tags.specialized.DivTag;
import j2html.tags.specialized.HeadTag;

public class ViewBuilder
{
	private static ViewBuilder instance = null;

	ViewRegistry elementTypeViewHandlerRegister = ViewRegistry.getInstance();
	AppPropertyService appProperties = AppPropertyService.getInstance();

	private ViewBuilder()
	{
		//
	}

	public static ViewBuilder getInstance()
	{
		if (instance == null)
		{
			instance = new ViewBuilder();
		}
		return instance;
	}

	public String buildView(HttpServletRequest request, Page page)
	    throws Exception
	{
		return document().render() + "\n" + html(
		    buildHead(page), //
		    buildBody(page, request)
		).withLang(appProperties.getHtmlLang())//
		    // TODO sveng 03.08.2023: Rendern in Production ausschalten!
		    // Production-Mode
		    .renderFormatted();
	}

	private HeadTag buildHead(Page page) throws Exception
	{
		// TODO sveng 02.05.2023: random als optional
		Random random = new Random();
		String randomString;
		int n = random.nextInt(123456);
		randomString = Integer.toString(n);

		return head(
		    meta()//
		        .withCharset(appProperties.getHtmlMetaCharset()), //
		    meta()//
		        .attr("http-equiv", "cache-control")//
		        .attr("content", "max-age=60"), //
		    meta()//
		        .withName("description")//
		        // TODO sveng 13.11.2022: nicht mehr als 250 Zeichen zulassen.
		        .withContent(page.getDescription()),
		    meta()//
		        .withName("keywords")//
		        .withContent(page.getKeywords()),
		    meta()//
		        .withName("viewport")//
		        .withContent(
		            "width=device-width, initial-scale=1.0, minimum-scale=1.0"
		        ),
		    ////////////////////////////////////
		    iff(
		        page.includeCache(),
		        meta().withName("expires").withContent("content=\"0\"")
		    ),
		    // TODO sveng 26.11.2022: meta:
		    // https://stackoverflow.com/questions/4417923/html-meta-tag-for-content-language

		    // noindex und nofollow werden bei Bedarf gesetzt:
		    // TODO sveng 03.08.2023: Random-String in Production unbedingt
		    // entfernen.
		    iff(
		        page.noIndex() == true,
		        meta().withName("robots").withContent("noindex")
		    ),
		    iff(
		        page.noFollow() == true,
		        meta().withName("robots").withContent("nofollow")
		    ),
		    link()//
		        .withRel("icon")//
		        .withHref(
		            appProperties.getHostName()
		                + appProperties.getServletContextPath()
		                + appProperties.getFaviconPath()
		        )//
		        .withType("image/png"),

		    // TODO sveng 25.02.2023: Bootstrap aus properties settings
		    // laden.

		    /*
		     * 1. styles.css + random 2. bootstrap + 3. fontawesome + 4.
		     * sonstige
		     */

		    // each(styleSheetTags, tag -> rawHtml(tag)),

		    link()//
		        .withRel("stylesheet")//
		        .withHref(
		            "https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
		        )//
		        .attr(
		            "integrity",
		            "sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
		        )//
		        .attr("crossorigin", "anonymous")//
			//

		    , link()//
		        .withRel("stylesheet")//
		        .withHref(
		            "https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css"
		        )//
		    , //
		      //

		    link().withRel("stylesheet").withHref(
		        appProperties.getHostName()
		            + appProperties.getServletContextPath()
		            + "/css/styles.css?v=" + randomString
		    ),
		    /*
		     * todo: theme-color für mobile-browser einbinden. theme-color wird
		     * aus layout geladen. Farbe kann ggf. aus Seite geladen werden:
		     * appProperties.getMobileBrowserAppLayoutColor()
		     */
		    title(page.getHtmlTitle())
		);
	}

	private DomContent buildBody(Page page, HttpServletRequest request)
	    throws Exception
	{

		return body(
		    div(

		        // showAlert(request),
		        showCookieBanner(request)

		        , div(
		            // noScriptMsg(),
		            buildHeader(page.getHeaderArticles(), request), //
		            buildMainLayoutArea(page.getMainArticles(), request), //
		            buildFooter(page.getFooterArticles(), request), //
		            buildModal(request)
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
		String containerTag = article.getContainerTag();

		if (containerTag != null)
		{
			switch (containerTag) {

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
		} else
		{
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
		// TODO sveng 02.05.2023: random als optional über settings
		Random random = new Random();
		String randomString;
		int n = random.nextInt(123456);
		randomString = Integer.toString(n);

		// TODO sveng 25.02.2023: bootstrap js from settings
		appProperties.getHtmlScriptPaths();

		return div(
		    buildInternalScript(request),
		    each(
		        appProperties.getHtmlScriptPaths(),
		        scriptPath -> script()
		            .withSrc(scriptPath + "?v=" + randomString) // todo: random
		            .withType("text/javascript")
		    )
		).with(
		    script()//
		        .withSrc(
		            // TODO sveng 15.03.2023: settings
		            "https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
		        )//
		        .attr(
		            "integrity",
		            "sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
		        )//
		        .attr("crossorigin", "anonymus")
		).with(
		    script()//
		        .withSrc(
		            "http://localhost:8080/bueffeltier/js/lesson-editor.js"
		        )//
		        .withType("text/javascript")
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

	private DomContent buildModal(HttpServletRequest request)
	{
		// TODO sveng 15.07.2023: oben unter dem body element anzeigen
		// TODO sveng 15.07.2023: bei fehlendem js message anzeigen
//		return ModalBuilder.create()//
//		    .withMessage("Test Message")//
//		    .build("Info", "pageModal", null, null, "showPageModal()");

//		return div(
//		    //
//		    div(
//		        //
//		        div(
//		            //
//		            div(
//		                //
//		                h5(
//		                    //
//		                    text("Modal Title")
//		                )//
//		                    .withClass("modal-title fs-5")//
//		                    .withId("modalTitle"),
//		                button(
//		                    //
//		                    span("&times;").attr("aria-hidden", "true")
//		                )//
//		                    .withType("button")//
//		                    .withClass("close")//
//		                    .withData("bs-dismiss", "modal")//
//		                    .attr("aria-label", "Close")//
//		            )//
//		                .withClass("modal-header"),
//
//		            div(
//		                //
//		                text("Modal Body Text")
//		            )//
//		                .withClass("modal-body")//
//		                .withId("modalBody"),
//		            div(
//		                //
//		                button("Schließen")//
//		                    .withType("button")//
//		                    .withClass("btn btn-secondary")//
//		                    .withData("bs-dismiss", "modal")
//		            )//
//		                .withClass("modal-footer")//
//		        )//
//		            .withClass("modal-content")
//		    )//
//		        .withClass("modal-dialog")//
//		        .attr("role", "document")
//		)//
//		    .withClass("modal fade")//
//		    .withId("pageModal")//
//		    .withTabindex(-1)//
//		    .attr("role", "dialog")//
//		    .attr("aria-labelledby", "modalTitle")//
//		    .attr("aria-hidden", "true");

		// Delete File Modal
		return ModalBuilder.create()//
		    .withoutOpenScript()//
		    .withAbortButton("OK")//
		    .build(
		        "Datei löschen", "pageModal", "pageModal", null,
		        "showPageModal()"
		    );
	}
}
