package com.bueffeltier.ui.webapp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bueffeltier.crosscutting.AppPropertyService;

// todo rename responseService
public class ResponseService
{
	private static ResponseService instance;

	private static CookieService cookieService;

	private AppPropertyService appPropertyService = AppPropertyService
	    .getInstance();

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
		String responseType = detectResponseType(request);

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

	private String detectResponseType(HttpServletRequest request)
	{
		String responseType = (String) request.getAttribute("responseType");

		boolean preventRedirect = (boolean) request
		    .getAttribute("preventRedirect");

		// Vergleich des ursprünglich angefragten Pfades mit dem tatsächlich
		// ausgelieferten Pfades:
		boolean doRedirect = !request.getServletPath()
		    .equals(request.getAttribute("responsePath"));

		if (responseType.equals("REDIRECT"))
		{
			if (preventRedirect)
			{
				request.setAttribute("responseType", "RESPOND");
			}
		}

		if (responseType.equals("RESPOND"))
		{
			if (doRedirect && !preventRedirect)
			{
				request.setAttribute("responseType", "REDIRECT");
				responseType = "REDIRECT";
			}
		}

		return responseType;
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

		setStatus(request, response);

		cookieService.writeCookies(request, response);

		response.setContentType("text/htmlcharset=UTF-8");
//		response.setContentType("text/html");

		response.setContentType("text/html"); // todo: wo wird das festgelegt?

		PrintWriter out = null;

		String htmlString = "";

		try
		{
			htmlString = (String) request.getAttribute("responseView");

		} catch (Exception ex)
		{
			// todo: fehlerseite
		}

		try
		{
			out = response.getWriter();

			out.print(htmlString);

		} catch (IOException ex)
		{
			// todo: fehlerseite

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
