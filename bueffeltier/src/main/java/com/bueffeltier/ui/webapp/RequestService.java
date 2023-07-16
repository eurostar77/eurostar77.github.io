
package com.bueffeltier.ui.webapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.bueffeltier.crosscutting.AppPropertyService;
import com.bueffeltier.logic.foundation.pagetree.SiteRepository;
import com.bueffeltier.ui.webapp.content.ActionRegistry;
import com.bueffeltier.ui.webapp.content.PageActionRegistry;
import com.bueffeltier.ui.webapp.content.action.Action;
import com.google.gson.Gson;

/*
 * Service für die Verarbeitung der im Servlet eingehenden Requests.
 */

public class RequestService
{
	private static RequestService instance;

	private ResponseService responseService = ResponseService.getInstance();

	private AppPropertyService appPropertyService = AppPropertyService
	    .getInstance();

	private CookieService cookieService = CookieService.getInstance();

	private ActionRegistry actionRegistry = ActionRegistry.getInstance();

	private PageActionRegistry pageActionRegistry = PageActionRegistry
	    .getInstance();

	private ViewDataService viewDataService = ViewDataService.getInstance();

	ActionService actionService = ActionService.getInstance();

	private SiteRepository siteRepository = SiteRepository.getInstance();

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
		initRequestAttributes(request);

		setRequestCharacterEncoding(request);

		cookieService.readCookies(request);

		// TODO sveng 24.06.2023: hier den path abfragen und weitergeben?
		// Intern mit id arbeiten?
		handleAction(request);

		responseService.doResponse(request);
	}

	private void initRequestAttributes(HttpServletRequest request)
	{
		// TODO sveng 28.01.2023: init aller requestAttribute erforderlich?
		// welche?
		request.setAttribute("permission", 0);
		request.setAttribute("preventRedirect", false);
		request.setAttribute("requestPath", request.getServletPath());
		request.setAttribute("responsePath", request.getServletPath());
		request.setAttribute("isAjaxRequest", false);
	}

	/**
	 * Setzt requestType auf "GET" "POST" oder "AJAX".
	 */
	private String detectRequestType(HttpServletRequest request)
	{
		String requestType = null;

		if (isRequestAJAX(request))
		{
//			request.setAttribute("requestType", "AJAX");
//			requestType = "AJAX";
			request.setAttribute("isAjaxRequest", true);
		}

		if ("POST".equals(request.getMethod()))
		{
//			request.setAttribute("requestType", "POST");
			requestType = "POST";

		} else if (isRequestPageAction(request))
		{
//			request.setAttribute("requestType", "PAGE");
			requestType = "PAGE";
			return requestType;

		} else if ("GET".equals(request.getMethod()))
		{
//			request.setAttribute("requestType", "GET");
			requestType = "GET";

		} else
		{
			// TODO sveng 24.06.2023: Fehlerseite
			// Error für unbehandelte Servlet-Methode.
			// oder Ersatzseite liefern: Anfrage konnte nicht bearbeitet werden.
		}

		return requestType;
	}

	private boolean isRequestAJAX(HttpServletRequest request)
	{
		boolean isAjax = false;

		String xRequestedWithHeader = request.getHeader("X-Requested-With");

		if (xRequestedWithHeader != null)
		{
			isAjax = "XMLHttpRequest".equals(xRequestedWithHeader);
		}

//		String acceptHeader = request.getHeader("Accept");
//
//		if (acceptHeader != null)
//		{
//			isAjax = acceptHeader.contains("application/json");
//		}

		return isAjax;
	}

	private boolean isRequestPageAction(HttpServletRequest request)
	{
		return pageActionRegistry
		    .hasPageAction((String) request.getAttribute("requestedPath"));
	}

	private void setRequestCharacterEncoding(HttpServletRequest request)
	{
		try
		{
			request.setCharacterEncoding(
			    appPropertyService.getServletCharacterEncoding()
			);

		} catch (UnsupportedEncodingException e)
		{
			// TODO sveng 23.06.2023: catch-block
			// TODO sveng 23.06.2023: JSP als Fehlerseite?
			e.printStackTrace();
		}
	}

	private void handleAction(HttpServletRequest request)
	{
//		switch ((String) request.getAttribute("requestType")) {
		switch (detectRequestType(request)) {

//		case "AJAX":
//
//			try
//			{
//				handleAjaxAction(request);
//
//			} catch (IOException e)
//			{
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			break;

		case "POST":

			handlePostAction(request);
			break;

		case "GET":

			handleGetAction(request);
			break;

		case "PAGE":

			handlePageAction(request);
			break;
		}
	}

	private void handleGetAction(HttpServletRequest request)
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

		// TODO sveng 25.06.2023: Alle Actions über den ExeutorService ausführen
		// lassen.
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
				    .getAction("REGISTER_CONFIRM");

				Action action;
				try
				{
					action = (Action) actionClass.getMethod("getInstance")
					    .invoke(null);

					//

					/*
					 * TODO sveng 11.07.2023: Operationen die vor allen Action
					 * Typen ausgeführt werden sollen, Methode
					 * "preActionOperations" einführen. Berücksichtige Verhalten
					 * bei Page-Actions.
					 */
					// Löschen der Action-to-View Daten vor jeder neuen Action.
					viewDataService.clearReceivedData(request);
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

		// TODO sveng 04.07.2023: löschen, da hier nur noch get-Action
		// ausgeführt wird. Prüfen, ob removeTrailingPathSlash() nachfolgend
		// noch angewendet wird.
		// TODO sveng 24.06.2023: trailingSlash beim Auslesen des path
//		String requestPath = (String) request.getAttribute("requestPath");
//		String pathString = removeTrailingPathSlash(requestPath);
	}

	private void handlePostAction(HttpServletRequest request)
	{
		try
		{
			actionService
			    .executeActions(request, request.getParameter("action"));

		} catch (Exception e)
		{
			// TODO sveng 23.06.2023: Fehlerseite laden
			// oder Rückfallseite laden
		}
	}

	private void handleAjaxAction(HttpServletRequest request) throws IOException
	{
		StringBuilder sb = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null)
		{
			sb.append(line);
		}

		String jsonData = sb.toString();
// TODO sveng 27.06.2023: Gson-Servie, da gson threadsafe ist.
		// JSON-Parsing mit Gson
		Gson gson = new Gson();
		John john = gson.fromJson(jsonData, John.class);

		// Hier kannst du auf die JSON-Daten zugreifen
		// data enthält die geparsten JSON-Daten als Instanz von YourDataClass
////////////////////////////////////////////////////////////////////////////////		
//		// TODO sveng 10.02.2023: testlogik hier auflösen.
//		String json = null;
//
//		String requestedPath = (String) request.getAttribute("requestPath");
//
//		if (requestedPath.equals("/api"))
//		{
		// Löschen der Action-to-View Daten vor jeder neuen Action.
		viewDataService.clearReceivedData(request);
//
//			long id = Long.parseLong(request.getParameter("id"));
//			List<ArticleJDBCFlat> articles = siteRepository.readArticles(id);
//
//			Gson gson = new GsonBuilder().registerTypeAdapter(
//			    LocalDateTime.class, new LocalDateTimeSerializerForGson()
//			).create();
//
//			json = gson.toJson(articles);
//
//		} else
//
//		{
//			// Handle regular request
//		}
//
//		javax.servlet.AsyncContext asyncContext = (javax.servlet.AsyncContext) request
//		    .getAttribute("asyncContext");
//
//		javax.servlet.http.HttpServletResponse response = (javax.servlet.http.HttpServletResponse) asyncContext
//		    .getResponse();
//
//		cookieService.writeCookies(request, response);
//		// FALSCH?
//		// Löschen der Action-to-View Daten nach dem Setzen der Cookies aller
//		// Response-Types.
//		viewDataService.clear(request);
//
//		response.setContentType("application/json");
//		response.setCharacterEncoding("UTF-8");
//
//		PrintWriter out = null;
//
//		String htmlString = "";
//
//		try
//		{
//			htmlString = (String) request.getAttribute("responseView");
//
//		} catch (Exception ex)
//		{
//			// todo: fehlerseite
//		}
//
//		try
//		{
//			out = response.getWriter();
//
////			out.write(json);
//			out.print(gson);
//
//		} catch (IOException ex)
//		{
//			// todo: fehlerseite
//
//		} finally
//		{
//			out.close();
//	}

	}

	private void handlePageAction(HttpServletRequest request)
	{
		Class<? extends Action> actionClass = pageActionRegistry
		    .getActionClass(request.getParameter("action"));

		Action action;
		try
		{
			action = (Action) actionClass
			    .getMethod("getInstance", HttpServletRequest.class)
			    .invoke(actionClass, request);

			// TODO sveng 12.07.2023: Hinterfragen, ob viewData bei Page Actions
			// gelöscht werden muss, ob pageActions im Einzelfall darüber eint-
			// scheiden müssen.

			// Löschen der Action-to-View Daten vor jeder neuen Action.
			viewDataService.clearReceivedData(request);
			action.execute(request);

		} catch (IllegalAccessException | InvocationTargetException
		    | NoSuchMethodException | SecurityException e1)
		{
			// TODO Auto-generated catch block

			// todo: andere Seite laden. Fehlerseite, etc...

			e1.printStackTrace();
		}
	}

	// TODO sveng 24.06.2023: löschen?
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

	public class John
	{
		private String name;

		private int age;

		public String getName()
		{
			return name;
		}

		public void setName(String name)
		{
			this.name = name;
		}

		public int getAge()
		{
			return age;
		}

		public void setAge(int age)
		{
			this.age = age;
		}
	}
}
