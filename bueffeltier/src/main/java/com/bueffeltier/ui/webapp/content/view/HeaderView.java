package com.bueffeltier.ui.webapp.content.view;

import static j2html.TagCreator.*;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.bueffeltier.crosscutting.AppPropertyService;
import com.bueffeltier.data.jdbc.ElementJDBCFlat;
import com.bueffeltier.data.jdbc.UserJDBC;
import com.bueffeltier.ui.html.molecule.SpacingPropertyDV;
import com.bueffeltier.ui.html.molecule.SpacingSidesDV;
import com.bueffeltier.ui.html.molecule.SpacingSizeDV;
import com.bueffeltier.ui.html.organism.ButtonBuilder;
import com.bueffeltier.ui.html.organism.ButtonBuilder.ButtonInputTypeDV;
import com.bueffeltier.ui.html.organism.ButtonBuilder.ButtonTagTypeDV;
import com.bueffeltier.ui.html.organism.ButtonBuilder.ColorDV;

import j2html.tags.DomContent;
import j2html.tags.specialized.ATag;

/**
 *
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
public class HeaderView extends AbstractView
{
	private static HeaderView instance;

	private AppPropertyService appPropertyService = AppPropertyService
	    .getInstance();

	private HeaderView()
	{
		super();
	}

	public static HeaderView
	    getInstance(ElementJDBCFlat element, HttpServletRequest request)
	{
		if (instance == null)
		{
			instance = new HeaderView();
		}

		return instance;
	}

	@Override
	public DomContent
	    writeHtml(ElementJDBCFlat element, HttpServletRequest request)
	        throws Exception
	{
		String headerImagePath = appPropertyService.getHeaderImagePath();

		ATag aTag = a()
		    // ... Weitere Attribute und Inhalte des <a>-Elements
		    .withClass("navbar-brand").withHref("/bueffeltier/");

		if (StringUtils.isNotBlank(headerImagePath))
		{
			aTag.with(
			    img().withSrc(appPropertyService.getHeaderImagePath())//
			        .withAlt(appPropertyService.getHeaderImageAltName())//
			        .withClass("headerBrand")//
			        .withStyle("height: 50px;")
			);
		} else
		{
			aTag.withText(appPropertyService.getHeaderImageAltName());
		}

		return nav(
		    div(
		        aTag,
		        // todo referenz aufidbasis einfügen.

		        button(span().attr("class", "navbar-toggler-icon"))
		            .attr("class", "navbar-toggler").attr("type", "button")
		            .attr("data-bs-toggle", "collapse")
		            .attr("data-bs-target", "#navbarTogglerDemo02")
		            .attr("aria-controls", "navbarTogglerDemo02")
		            .attr("aria-expanded", "false")
		            .attr("aria-label", "Toggle navigation"),
		        div(
		            ul(
		                // li(homeLink(request)), //
		                li(registerLink(request)), //
		                li(loginLink(request)), //
		                li(settingsLink(request)), //
		                li(logoutLink(element, request))
		            ).attr("class", "navbar-nav me-auto mb-2 mb-lg-0")
		        ).attr("class", "collapse navbar-collapse")
		            .attr("id", "navbarTogglerDemo02")
		    ).attr("class", "container-fluid")
		).attr("class", "navbar navbar-expand-md navbar-light bg-light");

		//
		//
		//
//        return html(
		// div(
		// a(
		// new ContainerTag<>("picture").with(
		// source().withMedia("(min-width: 992px)")
		// .withSrcset("/bubbles/images/logo/logoa.svg 1x"),
		// source().withMedia("(min-width: 575px)")
		// .withSrcset("/bubbles/images/logo/logob.svg 1x"),
		// // a(
		// img().withClass("logoc")
		// .withSrc("/bubbles/images/logo/logoc.svg")
		// .withAlt("Büffeltier")
		// .withHeight("50px"))
		// ).withHref("/bubbles/").withClass("logoc")// todo: alt aus settings
		// ).withClass("logo") // ,
		// // a(
		// // img().withClass("logoa")
		// // .withSrc("/bubbles/images/logo/logoa.svg")
		// // .withAlt("Büffeltier"), // todo: alt aus settings
		// // img().withClass("logob")
		// // .withSrc("/bubbles/images/logo/logob.svg")
		// // .withAlt("Büffeltier")
		// // ).withHref("/bueffeltier/").withClass("logo")// todo: alt aus
		// settings
		// // div(
		// // input().withType("checkbox").withId("hamburg"),
		// // label(
		// // span("").withClass("line"),
		// // span("").withClass("line"),
		// // span("").withClass("line")
		// // ).attr("for=\"hamburg\"").withClass("hamburg")
		// // ).withClass("row")
		// ,
		// div(
		// // startLink(),
		// loginLink(),
		// // registerLink(),
		// logoutLink(),
		// settingsLink()
		// ).attr("class", "header-right")
		// );
	}

	private DomContent homeLink(HttpServletRequest request)
	{
		DomContent homeLink = null;

		// TODO sveng 15.02.2023: link in settings oder aus repo laden.
		String requestPath = (String) request.getAttribute("requestPath");

		if (!requestPath.equals("/"))
		{
			// TODO sveng 15.02.2023: link in settings oder aus repo laden.
			homeLink = ButtonBuilder.create().withTagType(ButtonTagTypeDV.A)
			    .asOutline().withText("Startseite").withColor(ColorDV.PRIMARY)
			    .withClass("m-1").withHref("/bueffeltier").build();
		}

		return homeLink;
	}

	private DomContent registerLink(HttpServletRequest request)
	{
		DomContent registerLink = null;

		if ((Integer) request.getAttribute("permission") == 0)
		{
			// TODO sveng 15.02.2023: link in settings oder aus repo laden.
			registerLink = ButtonBuilder.create().withTagType(ButtonTagTypeDV.A)
			    .withText("Registrieren").withColor(ColorDV.SECONDARY)
			    .withHref("/bueffeltier/register").withClass("m-1").asOutline()
			    .withClass("nav-item").build();
		}

		return registerLink;
	}

	private DomContent loginLink(HttpServletRequest request)
	{
		DomContent loginLink = null;

		// TODO sveng 15.02.2023: Wenn ich auf Login-Seite bin, Login-Link oder
		// Header nicht anzeigen.
		if ((Integer) request.getAttribute("permission") == 0)
		{
			// TODO sveng 15.02.2023: link in settings oder aus repo laden.
			loginLink = ButtonBuilder.create().withTagType(ButtonTagTypeDV.A)
			    .withText("Einloggen").withColor(ColorDV.PRIMARY)
			    .withHref("/bueffeltier/login").withClass("m-1")
			    .withClass("nav-item").build();
		}

		return loginLink;
	}

	private DomContent
	    logoutLink(ElementJDBCFlat element, HttpServletRequest request)
	{
		DomContent logoutLink = null;

		boolean isLoggedIn = (Integer) request.getAttribute("permission") > 0;

		boolean isRegisteredUser = true;

		UserJDBC user = (UserJDBC) request.getAttribute("user");

		if (user != null)
		{
			if (user.isAnonymous())
			{
				isRegisteredUser = false;
			}
		}

		if (isLoggedIn && isRegisteredUser)
		{

			logoutLink = form(
			    element.getType(),

			    ButtonBuilder.create().withText("Ausloggen")
			        .withInputType(ButtonInputTypeDV.SUBMIT).withValue("logout")
			        .withName("logout").withColor(ColorDV.PRIMARY)
			        .withSpacing(
			            SpacingPropertyDV.MARGIN, SpacingSidesDV.BLANK,
			            SpacingSizeDV.ONE
			        ).withClass("nav-item").withTagType(ButtonTagTypeDV.BUTTON)
			        .withClass("header-right").build()
			);
			// .withClass("header-right");
		}

		return logoutLink;
	}

	private DomContent settingsLink(HttpServletRequest request)
	{
		if ((Integer) request.getAttribute("permission") > 0)
		{
			String responsePathString = (String) request
			    .getAttribute("responsePath");
			if (!responsePathString.equals("/member-area")) // todo:
			                                                // aus
			                                                // settings
			                                                // laden.
			{
				return html(
				    a("Mitglieder").withHref("/bueffeltier/member-area")

				);
			}
		}
		return null;
	}

	@Override
	public DomContent writeHtmlControls()
	{
		throw new UnsupportedOperationException("Not supported yet.");
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
