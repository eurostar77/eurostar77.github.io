package com.bueffeltier.ui.webapp;

import javax.servlet.http.HttpServletRequest;

import com.bueffeltier.data.jdbc.Page;
import com.bueffeltier.logic.foundation.pagetree.SiteRepository;

public class PageLoadingService
{
	private static PageLoadingService instance;

	private SiteRepository siteRepository = SiteRepository.getInstance();

	private PageLoadingService()
	{

	}

	public static PageLoadingService getInstance()
	{
		if (instance == null)
		{
			instance = new PageLoadingService();
		}

		return instance;
	}

	public Page detectResponsePage(HttpServletRequest request)
	{
		String path = (String) request.getAttribute("responsePath");

		Page page = siteRepository.read(path);

		if (page == null)
		{
			page = load404PageNotFound(request);

		} else if ((int) request.getAttribute("permission") < page
		    .getPermission())
		{
			page = load403PageForbidden(request);

			// TODO sveng 24.06.2023: Fehlerseite

			// Wenn requestedPage forbidden:
			// "Forbidden"-Meldung an den User senden.
			// Info an Admin, um zu prüfen, ob der Link auf der Seite war.

			// Wenn resultPage forbidden:
			// Passende Fehlermeldung an den User: "Hier ist etwas
			// schiefgelaufen"
			// - entweder zurück an die aufrufende Seite plus Fehlermeldung.
			// - oder Fehlerseite laden.
			// Option in Action Klasse definieren.
		}

		return page;
	}

	private Page load403PageForbidden(HttpServletRequest request)
	{
		request.setAttribute("responseStatus", "SC_FORBIDDEN");

		return siteRepository.read("/http-403");
	}

	private Page load404PageNotFound(HttpServletRequest request)
	{
		request.setAttribute("responseStatus", "SC_NOT_FOUND");

		return siteRepository.read("/http-404");
	}
}
