package com.bueffeltier.ui.webapp;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.javatuples.Pair;

import com.bueffeltier.crosscutting.AppPropertyService;
import com.bueffeltier.data.jdbc.UserJDBC;
import com.bueffeltier.logic.foundation.AuthPermissionService;
import com.bueffeltier.logic.foundation.EncryptionService;

public class CookieService
{
	private static CookieService instance;

	private AppPropertyService appPropertyService = AppPropertyService
	    .getInstance();

	private AuthPermissionService authPermissionService = AuthPermissionService
	    .getInstance();

	private RedirectDataService redirectDataService = RedirectDataService
	    .getInstance();

	private EncryptionService encryptionService = EncryptionService
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
				if (cookies[i].getName().equals("bueffeltier"))
				{
					sessionToken = readSessionTokenFromCookie(
					    request, cookies[i]
					);
				}

				if (cookies[i].getName().equals("bueffeltier-privacy"))
				{
					request.setAttribute(
					    "hasUserConsentToCookies", cookies[i].getValue()
					);
				}

				if (cookies[i].getName().equals("bueffeltier-redirect"))
				{
					readRedirectDataFromCookie(request, cookies[i]);
				}
			}

		} else
		{
			request.setAttribute("hasBrowserCookiesEnabled", false);
		}

		authPermissionService.authenticate(request, sessionToken);
	}

	private String
	    readSessionTokenFromCookie(HttpServletRequest request, Cookie cookie)
	{
		String sessionToken = cookie.getValue();

		if (sessionToken.equals("session"))
		{
			sessionToken = null;

			request.setAttribute("sessionId", "session");
		}

		return sessionToken;
	}

	private void
	    readRedirectDataFromCookie(HttpServletRequest request, Cookie cookie)
	{
		String cookieValue = cookie.getValue();

		String encryptedCookieValue = null;

		try
		{
			encryptedCookieValue = encryptionService

			    .decryptData(cookieValue);

		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			// TODO sveng 13.06.2023: security - session und request abbrechen?
			// methode aufteilen in try und cathc part
		}

		String[] keyValuePairs = encryptedCookieValue.split(";");

		if (keyValuePairs.length > 0)
		{
			for (String pair : keyValuePairs)
			{
				String[] keyValue = pair.split("=");
				String key = keyValue[0];
				String value = keyValue[1];

				redirectDataService.addData(request, key, value);
			}
		}
	}

	// TODO sveng 12.06.2023: Große Datenmengen auf mehrere Cookies aufteilen
	// TODO sveng 12.06.2023: Exeption, wenn maximale Cookie Zahl erreicht ist.
	protected void
	    writeCookies(HttpServletRequest request, HttpServletResponse response)
	{
		deleteCookiesOpt(request, response);

		setRedirectDataCookie(request, response);

		setSessionCookie(request, response);
	}

	private void deleteCookiesOpt(
	    HttpServletRequest request,
	    HttpServletResponse response
	)
	{
		for (Cookie cookie : request.getCookies())
		{
			if (cookie.getName().equals("bueffeltier-redirect"))
			{
				if (redirectDataService.hasData(request))
				{
					deleteCookie(response, cookie);
				}
			}
		}
	}

	private void deleteCookie(HttpServletResponse response, Cookie cookie)
	{
		Cookie invalidCookie = new Cookie(cookie.getName(), "");

		invalidCookie.setMaxAge(0);

		response.addCookie(invalidCookie);
	}

	private void setRedirectDataCookie(
	    HttpServletRequest request,
	    HttpServletResponse response
	)
	{
		List<Pair<String, Object>> cookieValuePairs = redirectDataService
		    .getDataPairs(request);

		if (redirectDataService.hasData(request))
		{
			StringBuilder cookieValue = new StringBuilder();

			for (Pair<String, Object> pair : cookieValuePairs)
			{
				cookieValue
				    .append(pair.getValue0() + "=" + pair.getValue1() + ";");
			}

			Cookie redirectDataCookie = null;

			try
			{
				redirectDataCookie = new Cookie(
				    "bueffeltier-redirect",
				    encryptionService.encryptData(cookieValue.toString())
				);
			} catch (Exception e)
			{
				// TODO sveng 13.06.2023: security angemessene behandlung
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// TODO sveng 24.01.2023: check length

			// TODO sveng 24.01.2023: für alle cookies in eigener methode
			// setzen:
			// TODO sveng 03.02.2023: denke an localhost.
//			redirectDataCookie.setDomain(appPropertyService.getServletCookieDomain());
			redirectDataCookie
			    .setPath(appPropertyService.getServletCookiePath());
			// TODO sveng 24.01.2023: settings:
			redirectDataCookie.setHttpOnly(false);
			// TODO sveng 24.01.2023: cookie lebenszeit; auch als setting?
			// als: sessionCookie oder als privacyCookie
			// redirectDataCookie.setSecure(appPropertyService.isServletInHttpsMode());
			redirectDataCookie.setMaxAge(60 * 30); // Store cookie for 1 year

			response.addCookie(redirectDataCookie);
		}
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