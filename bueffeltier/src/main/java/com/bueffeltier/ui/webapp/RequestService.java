
package com.bueffeltier.ui.webapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bueffeltier.crosscutting.AppPropertyService;
import com.bueffeltier.data.jdbc.ArticleJDBCFlat;
import com.bueffeltier.data.jdbc.LocalDateTimeSerializerForGson;
import com.bueffeltier.data.jdbc.PageJDBCFlat;
import com.bueffeltier.logic.foundation.AuthPermissionService;
import com.bueffeltier.logic.foundation.pagetree.SiteRepository;
import com.bueffeltier.ui.webapp.content.ActionRegistry;
import com.bueffeltier.ui.webapp.content.action.Action;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class RequestService
{
	private static RequestService instance;

	private SiteRepository siteRepository = SiteRepository.getInstance();

	private ViewBuilder viewBuilder = ViewBuilder.getInstance();

	private ResponseService responseService = ResponseService.getInstance();

	private AppPropertyService appPropertyService = AppPropertyService
	    .getInstance();

	private AuthPermissionService authPermissionService = AuthPermissionService
	    .getInstance();

	private CookieService cookieService = CookieService.getInstance();

	ActionRegistry actionRegistry = ActionRegistry.getInstance();

	private RequestService()
	{
		//
	}

	public static RequestService getInstance()
	{
		if (instance == null)
		{
			instance = new RequestService();
		}
		return instance;
	}

	public void processRequest(HttpServletRequest request)
	{
		// TODO sveng 28.01.2023: init aller requestAttribute erforderlich?
		request.setAttribute("permission", 0);
		request.setAttribute("preventRedirect", false);
		request.setAttribute("requestPath", request.getServletPath());

		try
		{
			// todo: nötig?
			request.setCharacterEncoding(
			    appPropertyService.getServletCharacterEncoding()
			);

		} catch (UnsupportedEncodingException e)
		{
			// TODO Auto-generated catch block
			// ggf. jsp zurückgeben? als fehlerseite?
			e.printStackTrace();
		}

		cookieService.readCookies(request);

		// TODO sveng 24.05.2023: löschen
//		authPermissionService.authenticate(request);

		detectRequestType(request);

		handleRequestByType(request);

	}

	private void handleRequestByType(HttpServletRequest request)
	{
		switch ((String) request.getAttribute("requestType")) {

		case "AJAX":

			handleAjax(request);
			break;

		case "POST":

			handlePost(request);
			break;

		case "GET":

			handleGet(request);
			break;
		}
	}

	private void handleGet(HttpServletRequest request)
	{
		/*
		 * todo: bei url-rewriting hier auch parameter auslesen, um ggf. actions
		 * ausführen zu können.
		 */

		/*
		 * TODO: Page-Actions / Request-Actions werden vor dem Aufruf bestimmter
		 * Seiten per GET ausgeführt. Müsste evtl. in AbstractView geprüft
		 * werden!
		 */
		Map<String, String[]> requestParameterNames = request.getParameterMap();

		if (requestParameterNames != null)
		{
			/*
			 * Account Activation (Seitenaufruf-Action)
			 */
			if (requestParameterNames.containsKey("activate"))
			{
				// todo: neues register für getActions

				// todo: action in der bd suchen? actions von view lösen!

				// todo: null pointer behandeln!
				Class<? extends Action> actionClass = actionRegistry
				    .getActionClass("REGISTER_CONFIRM");

				Action action;
				try
				{
					action = (Action) actionClass.getMethod("getInstance")
					    .invoke(null);

					action.execute(request);

				} catch (IllegalAccessException | InvocationTargetException
				    | NoSuchMethodException | SecurityException e1)
				{
					// TODO Auto-generated catch block

					// todo: andere Seite laden. Fehlerseite, etc...

					e1.printStackTrace();
				}
			}
		}

		// todo: eigentlich schon Teil von responseService:

		String requestPath = (String) request.getAttribute("requestPath");

		String pathString = removeTrailingPathSlash(requestPath);

		buildView(pathString, request);

		responseService.doResponse(request);
	}

	// todo: mehrere posts nach einander
	private void handlePost(HttpServletRequest request)
	{
		Class<? extends Action> actionClass = actionRegistry
		    .getActionClass(request.getParameter("action"));

		Action action;
		try
		{
			action = (Action) actionClass
			    .getMethod("getInstance", HttpServletRequest.class)
			    .invoke(actionClass, request);

			action.execute(request);

		} catch (IllegalAccessException | InvocationTargetException
		    | NoSuchMethodException | SecurityException e1)
		{
			// TODO Auto-generated catch block

			// todo: andere Seite laden. Fehlerseite, etc...

			e1.printStackTrace();
		}

		// TODO sveng 11.02.2023: hier kann auch null rauskommen!
		buildView((String) request.getAttribute("responsePath"), request);

		responseService.doResponse(request);
	}

	// todo: wird das in oder durch cookieService erledigt?
	// authentify
	private void handleAjax(HttpServletRequest request)
	{
		// TODO sveng 10.02.2023: testlogik hier auflösen.
		String json = null;

		String requestedPath = (String) request.getAttribute("requestPath");

		if (requestedPath.equals("/api"))
		{
			long id = Long.parseLong(request.getParameter("id"));
			List<ArticleJDBCFlat> articles = siteRepository.readArticles(id);

			Gson gson = new GsonBuilder().registerTypeAdapter(
			    LocalDateTime.class, new LocalDateTimeSerializerForGson()
			).create();

			json = gson.toJson(articles);

		} else

		{
			// Handle regular request
		}

		AsyncContext asyncContext = (AsyncContext) request
		    .getAttribute("asyncContext");

		HttpServletResponse response = (HttpServletResponse) asyncContext
		    .getResponse();

		cookieService.writeCookies(request, response);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

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

//			out.write(json);
			out.print(json);

		} catch (IOException ex)
		{
			// todo: fehlerseite

		} finally
		{
			out.close();
		}
	}

	// todo: in Response Service verschieben:
	// todo: auch view von id abrufbar.
	// TODO sveng 31.12.2022: fehler zurückgeben, wenn seite nicht geladen.
	private void buildView(String path, HttpServletRequest request)
	{
		PageJDBCFlat accessiblePage = getAccessiblePage(request, path);

		// TODO sveng 31.12.2022: db-Spalte response_type nennen.
		// TODO sveng 31.12.2022: db-Spalte hält int-Wert.
		request.setAttribute("responsePath", accessiblePage.getPath());

		String pageType = accessiblePage.getPageType();

		if (path.equals(accessiblePage.getPath()))
		{
			request.setAttribute("responseType", pageType);
		} else
		{
			request.setAttribute("responseType", "REDIRECT");
		}

		if (pageType != null && pageType.equals("RESPOND"))
		{
			String view = "";
			try
			{
				view = viewBuilder.buildView(accessiblePage, request);

				request.setAttribute("responseView", view);

			} catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();

				// todo: Fehlerseite laden, wenn auch fail, dann Fehlerseite aus
				// dem Speicher oder Rückfallseite, wenn vorhanden.
			}

		} else if (pageType.equals("FORWARD")) // todo: fehlerseite, wenn page
		                                       // nicht exists.
		{
			request.setAttribute("responsePath", accessiblePage.getPath());

		} else if (pageType.equals("REDIRECT"))
		{
			//
		}
	};

	/**
	 * Setzt requestType auf "GET" "POST" oder "AJAX".
	 */
	private void detectRequestType(HttpServletRequest request)
	{
		String requestedWith = request.getHeader("X-Requested-With");

		boolean isAjax = requestedWith != null
		    && "XMLHttpRequest".equals(requestedWith);

		if (isAjax)
		{
			request.setAttribute("requestType", "AJAX");

		} else if ("POST".equals(request.getMethod()))
		{
			request.setAttribute("requestType", "POST");

		} else if ("GET".equals(request.getMethod()))
		{
			request.setAttribute("requestType", "GET");

		} else
		{
			// todo: Error für unbehandelte Servlet-Methode.
			// oder Ersatzseite liefern: Anfrage konnte nicht bearbeitet werden.
		}
	}

	private PageJDBCFlat
	    getAccessiblePage(HttpServletRequest request, String path)
	{
		PageJDBCFlat page = siteRepository.read(path);

		if ((int) request.getAttribute("permission") < page.getPermission())
		{
			page = load403ForbiddenPage(request);
		}

		return page;
	}

	// TODO sveng 28.04.2023: Methode in Service auslagern?
	private PageJDBCFlat load403ForbiddenPage(HttpServletRequest request)
	{
		request.setAttribute("responseStatus", "SC_FORBIDDEN");
		return siteRepository.read("/http-403");
	}

	// todo: noch nötig?
	private String removeTrailingPathSlash(String string)
	{
		// todo: Hier wird geprüft, ob die Startseite "/" gerufen wird.
		// Dieser Aufruf widerspricht der Logik, dass Urls bei mir immer
		// ohne "/" enden.
		if (string.length() == 1 && string.equals("/"))
		{
			return string;
		}
		if (!string.substring(string.length() - 1, string.length()).equals("/"))
		{
			return string;
		} else
		{
			return string.substring(0, string.length() - 1);
		}
	}
}
