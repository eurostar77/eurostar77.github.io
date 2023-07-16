package com.bueffeltier.ui.webapp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bueffeltier.crosscutting.AppPropertyService;
import com.bueffeltier.data.jdbc.Page;
import com.google.gson.Gson;

// todo rename responseService
public class ResponseService
{
	private static ResponseService instance;

	private static CookieService cookieService;

	private AppPropertyService appPropertyService = AppPropertyService
	    .getInstance();

	private PageLoadingService pageLoadingService = PageLoadingService
	    .getInstance();

	private ViewDataService viewDataService = ViewDataService.getInstance();

	private ViewBuilder viewBuilder = ViewBuilder.getInstance();

	private ResponseService()
	{
		cookieService = CookieService.getInstance();
	}

	public static ResponseService getInstance()
	{
		if (instance == null)
		{
			instance = new ResponseService();
		}
		return instance;
	}

	/**
	 * Schließt das request mit einer der folgenden Antworten ab: forward,
	 * redirect oder response Setzt die Cookies. Speichert ConentAttributeSet.
	 */
	public void doResponse(HttpServletRequest request)
	{
		boolean isAjaxRequest = (boolean) request.getAttribute("isAjaxRequest");

		String responseType = "RESPOND";
		request.setAttribute("responseType", "RESPOND");

		if (!isAjaxRequest)
		{
			Page accessiblePage = pageLoadingService
			    .detectResponsePage(request);

			responseType = detectResponseType(request, accessiblePage);

			try
			{
				buildResponseViewOpt(request, responseType, accessiblePage);

			} catch (Exception e)
			{
				// Bauen der View schlug fehl.
				// TODO sveng 12.07.2023: fehlerseite laden, schleife mit 3
				// maligem durchlauf, dann jsp laden, oder html

			}
		}

		switch (responseType) {
		// todo: exceeption werfen, für den fall, dass in der db kein
		// respond-type
		// angegeben ist, Ausnahmebehandlung anbieten.

		case "RESPOND":
			sendResponse(request);
			break;

		case "REDIRECT":
			sendRedirect(request);
			break;

		case "FORWARD":
			doForward(request);
			break;
		}
	}

	public String detectResponseType(
	    HttpServletRequest request,
	    Page responsePage
	)
	{
		String responseType = null;

		String requestedPath = (String) request.getAttribute("requestPath");
		String responsePath = responsePage.getPath();

		String reponsePageType = responsePage.getPageType();

		boolean preventRedirect = (boolean) request
		    .getAttribute("preventRedirect");

		// Zuerst wird nur geprüft, ob mit der vom Client angeforderten Seite
		// geantwortet wird, um "RESPOND" und "FORWARD" von "REDIRECT" zu
		// unterscheiden:
		if (responsePath.equals(requestedPath))
		{
			// Wenn angeforderte Seite geladen wurde, dann wird auch dessen
			// ResponseType verwendet:
			// REDIRECT, FORWARD oder RESPOND
			responseType = reponsePageType;

		} else
		{
			// ... sonst gehen wir von einem "REDIRECT" aus:
			responseType = "REDIRECT";

			if (preventRedirect)
			{
				responseType = reponsePageType;
			}
		}

		// TODO sveng 25.06.2023: Wenn hier response-type == null:
		// Fehlerseite oder sinnvolle Antwort.

		request.setAttribute("responseType", responseType);

		return responseType;
	}

	// TODO sveng 25.06.2023: jsp view als startpunkt hier mit einbauen.
	private void buildResponseViewOpt(
	    HttpServletRequest request,
	    String responseType,
	    Page page
	)
	{
		if (responseType.equals("RESPOND"))
		{
			String view = null;

			// while(viewNotBuilt){}
			try
			{
				view = viewBuilder.buildView(request, page);

			} catch (Exception e)
			{
				// TODO sveng 25.06.2023: View erneut bauen, sonst in einer
				// Schleife
				// Ausweichseiten versuchen zu laden. Erst x, dann y, dann z...
			}

			request.setAttribute("responseView", view);

			// TODO sveng 25.06.2023: Gilt für "RESPOND" und "FORWARD"
			// (JSP-View)
			// Löschen der Action-to-View Daten nach Bauen der View,
			// damit sie nicht mit Cookie gesendet werden.
			viewDataService.clearForwardViewData(request);
		}
	}

	/*
	 * Baut einen Response-String und sendet das Response.
	 */
	private void sendResponse(HttpServletRequest request)
	{
		AsyncContext asyncContext = (AsyncContext) request
		    .getAttribute("asyncContext");

		HttpServletResponse response = (HttpServletResponse) asyncContext
		    .getResponse();

		// TODO sveng 28.06.2023: Wann müssen die Status Codes gesetzt werden?
		setStatus(request, response);

		cookieService.writeCookies(request, response);

		String htmlString = "";

		boolean isAjaxRequest = (boolean) request.getAttribute("isAjaxRequest");

		if (!isAjaxRequest)
		{
			response.setContentType("text/htmlcharset=UTF-8");
			response.setContentType("text/html");

			try
			{
				htmlString = (String) request.getAttribute("responseView");

			} catch (Exception ex)
			{
				// TODO sveng 28.06.2023: Fehlerseite, Fehlermeldung, Statuscode
			}

		} else
		{
			response.setCharacterEncoding("UTF-8");

			response.setContentType("application/json");
		}

		PrintWriter out = null;

		try
		{
			out = response.getWriter();

			if (isAjaxRequest)
			{
				Gson gson = (Gson) request.getAttribute("ajaxResponseData");

				out.print(gson);

			} else
			{
				out.print(htmlString);
			}

		} catch (IOException ex)
		{
			// TODO sveng 28.06.2023: Fehlerseite, Fehlermeldung, Statuscode

		} finally
		{
			out.close();
		}
	}

	/**
	 * Redirect durchführen. Der Browser wird angewiesen einen bestimmten
	 * anderen URL anzufordern: (Angeforderter URL existiert nicht, oder Zugriff
	 * ist nicht erlaubt.)
	 */
	private void sendRedirect(HttpServletRequest request)
	{
		String encodedUrl;

//		if (this.doUrlRewriting)
//		{
//			encodedUrl = response.encodeRedirectURL(
//					AppPropertyService.getHostName()
//							+ AppPropertyService.servlet.getServletContext()
//									.getContextPath()
//							+ this.accessiblePageContent.getPage().getPath()
//			);
//		} else
//		{
		encodedUrl = appPropertyService.getHostName()
		    + appPropertyService.getServletContextPath()
		    + (String) request.getAttribute("responsePath");
//		}

		AsyncContext asyncContext = (AsyncContext) request
		    .getAttribute("asyncContext");

		HttpServletResponse response = (HttpServletResponse) asyncContext
		    .getResponse();

		setStatus(request, response);

		cookieService.writeCookies(request, response);
		// Löschen der Action-to-View Daten nach dem Setzen der Cookies aller
		// Response-Types.
//		viewDataService.clear(request);

		try
		{
			response.sendRedirect(encodedUrl);

		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Forward an eine JSP durchführen. Hier findet nie URL-Rewriting statt:
	 */
	private void doForward(HttpServletRequest request)
	{
		/*
		 * todo: Welche Daten mitgeben? Daten aus AccessiblePageContent abrufen?
		 */

		// JSP Content bereitstellen:

		String jspPath = (String) request.getAttribute("responsePath");
		request.setAttribute("content", jspPath);

		AsyncContext asyncContext = (AsyncContext) request
		    .getAttribute("asyncContext");

		HttpServletResponse response = (HttpServletResponse) asyncContext
		    .getResponse();

		setStatus(request, response);

		cookieService.writeCookies(request, response);
		// Löschen der Action-to-View Daten nach dem Setzen der Cookies aller
		// Response-Types.
//		viewDataService.clear(request);

		// Forward durchführen:
		// todo: url aus page laden!
		try
		{
			request.getRequestDispatcher("/WEB-INF/jsp/page.jsp")
			    .forward(request, response);
		} catch (ServletException | IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void
	    setStatus(HttpServletRequest request, HttpServletResponse response)
	{
		String status = (String) request.getAttribute("responseStatus");

		if (status != null && status.equals("SC_FORBIDDEN"))
		{
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		}
	}
	/**
	 * Ermittelt, ob die Session per URL-Rewriting geführt werden muss. Methode
	 * darf erst zum Einsatz kommen, wenn die zu ladende Seite fest steht.
	 */
//	private boolean useCookieOrUrlRewriting(HttpServletRequest request)
//	{
//		boolean doUrlRewriting = false;
//
//		if (request.getSession(false) != null)
//		{
//			// Wenn eine Session besteht:
//
//			if (request.getSession(false).isNew())
//			{
//				// Wenn Session neu ist:
//				if ((Boolean) request
//						.getAttribute("hasBrowserCookiesEnabled") == true)
//				{
//					doUrlRewriting = false;
//					// Browser akzeptiert Cookies.- Kein URL-Rewriting
//					// durchführen.
//				} else
//				{
//					doUrlRewriting = true;
//					// Browser akzeptiert keine Cookies. - URL-Rewriting
//					// durchführen.
//				}
//			} else if (request.isRequestedSessionIdFromCookie() == true)
//			{
//				// Session stammt aus Cookie.\nKein URL-Rewriting durchführen."
//				doUrlRewriting = false;
//
//			} else if (request.isRequestedSessionIdFromURL() == true)
//			{
//				// Wenn Session aus URL stammt:
//				// Session-Id stammt aus URL."
//				if ((Boolean) request
//						.getAttribute("hasBrowserCookiesEnabled") == true)
//				{
//					doUrlRewriting = false;
//					// Browser akzeptiert Cookies. - Kein URL-Rewriting
//					// durchführen."
//				} else
//				{
//					doUrlRewriting = true;
//					// Browser akzeptiert keine Cookies. - URL-Rewriting
//					// durchführen."
//				}
//			}
//		} else
//		{
//			// Es besteht gar keine Session:
//			doUrlRewriting = false;
//			// Es besteht keine Session. - Kein URL-Rewriting durchführen."
//		}
//	}

//    private void doRewritingReport()
//    {
//        String sessionText = "";
//        if (request.getSession(false) != null)
//        {
//            sessionText = "Session is New: " + request.getSession(false).isNew() + "\n";
//        }
//        log.add(this,
//                "\n\n Rewriting-Info: \n"
//                + "ForwardVsRedirect: " + preferForwardToRedirect + "\n"
//                + "hasBrowserCookiesEnabled: " + hasBrowserCookiesEnabled + "\n"
//                + "DoUrlRewriting: " + doUrlRewriting + "\n"
//                + "SessionIdFromCookie: " + request.isRequestedSessionIdFromCookie() + "\n"
//                + "SessionIdFromURL: " + request.isRequestedSessionIdFromURL() + "\n"
//                + "Gesetztes Cookie: " + gesetzterCookieTyp + "\n"
//                + sessionText
//        );
//    }

}
