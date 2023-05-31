package com.bueffeltier.ui.webapp;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.bueffeltier.crosscutting.AppPropertyService;
import com.bueffeltier.data.jdbc.UserJDBC;
import com.bueffeltier.logic.foundation.AuthPermissionService;

public class CookieService
{
	private static CookieService instance;

	private AppPropertyService appPropertyService = AppPropertyService
	    .getInstance();

	private AuthPermissionService authPermissionService = AuthPermissionService
	    .getInstance();

	private CookieService()
	{

	}

	public static CookieService getInstance()
	{
		if (instance == null)
		{
			instance = new CookieService();
		}
		return instance;
	}

	public void readCookies(HttpServletRequest request)
	{
		String sessionToken = null;

		Cookie[] cookies = request.getCookies();

		if (cookies != null)
		{
			request.setAttribute("hasBrowserCookiesEnabled", true);

			request.setAttribute("hasUserConsentToCookies", false);

			for (int i = 0; i < cookies.length; i++)
			{
				if (cookies[i].getName().equals("bueffeltier-privacy"))
				{
					request.setAttribute(
					    "hasUserConsentToCookies", cookies[i].getValue()
					);
				}

				if (cookies[i].getName().equals("bueffeltier"))
				{
					sessionToken = cookies[i].getValue();

					if (sessionToken.equals("session"))
					{
						sessionToken = null;

						request.setAttribute("sessionId", "session");
					}

					// TODO sveng 25.05.2023: löschen
//						request.setAttribute(
//						    "sessionToken", cookies[i].getValue()
//						);
				}
			}
		} else
		{
			request.setAttribute("hasBrowserCookiesEnabled", false);
		}

		authPermissionService.authenticate(request, sessionToken);
	}

	protected void
	    writeCookies(HttpServletRequest request, HttpServletResponse response)
	{
//		for (Cookie cookie : request.getCookies())
//		{
//			if (cookie.getName().equals(""))
//			{
//				// löschen?
//			}
//		}

		setSessionCookie(request, response);
	}

	private void setSessionCookie(
	    HttpServletRequest request,
	    HttpServletResponse response
	)
	{
		String cookieValue;

		UserJDBC user = (UserJDBC) request.getAttribute("user");

		String userName = null;

		String sessionToken = null;

		if (user != null)
		{
			userName = user.getName();

			if (StringUtils
			    .isNotBlank((String) request.getAttribute("sessionId")))
			{
				sessionToken = authPermissionService
				    .createWebtoken(request, userName);
			}
		}

		// TODO sveng 25.05.2023: löschen
//		String sessionToken = (String) request.getAttribute("sessionToken");

		if (StringUtils.isBlank(sessionToken))
		{
			cookieValue = "session";

		} else
		{
			cookieValue = sessionToken;
		}

		Cookie sessionCookie = new Cookie("bueffeltier", cookieValue);

		// TODO sveng 24.01.2023: check length

		// TODO sveng 24.01.2023: für alle cookies in eigener methode setzen:
		// TODO sveng 03.02.2023: denke an localhost.
//		sessionCookie.setDomain(appPropertyService.getServletCookieDomain());
		sessionCookie.setPath(appPropertyService.getServletCookiePath());
		// TODO sveng 24.01.2023: settings:
		sessionCookie.setHttpOnly(false);
		// TODO sveng 24.01.2023: cookie lebenszeit; auch als setting?
		// als: sessionCookie oder als privacyCookie
		// mainCookie.setSecure(appPropertyService.isServletInHttpsMode());
		sessionCookie.setMaxAge(60 * 30); // Store cookie for 1 year

		response.addCookie(sessionCookie);
	}

	private void getKbSize(String cookie)
	{
		// TODO sveng 25.01.2023: cookie darf max. 4kb haben.
		// https://stackoverflow.com/questions/4385623/bytes-of-a-string-in-java
	}
}