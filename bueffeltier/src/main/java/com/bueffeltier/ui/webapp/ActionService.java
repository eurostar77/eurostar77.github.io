package com.bueffeltier.ui.webapp;

import javax.servlet.http.HttpServletRequest;

import com.bueffeltier.ui.webapp.content.ActionRegistry;
import com.bueffeltier.ui.webapp.content.action.Action;

/*
 * Service zum Aufrufen von Servlet Actions. Greift auf Actions der Domain zu.
 */
public class ActionService
{
	private static ActionService instance;

	ActionRegistry actionRegistry = ActionRegistry.getInstance();

	private ActionService()
	{
		//
	}

	public static ActionService getInstance()
	{
		if (instance == null)
		{
			instance = new ActionService();
		}
		return instance;
	}

	// TODO sveng 24.06.2023: Prüfen, ob hier für jede Action Instanzen gebildet
	// werden.
	public void executeActions(HttpServletRequest request, String actionName)
	    throws Exception
	{
		Class<? extends Action> actionClass = loadRequestedAction(request);

		// TODO sveng 24.06.2023: Endlosschleife vermeiden!
		while (actionClass != null)
		{
			ActionExecutorService.getInstance().execute(request, actionClass);

			// TODO sveng 24.06.2023: nextAction deaktiviert.
//			Class<? extends Action> nextAction = loadAction(
//			    request, (String) request.getAttribute("nextSubActionName")
//			);

//			if (nextAction == null)
//			{
			break;
//			}
		}
	}

	private Class<? extends Action>
	    loadRequestedAction(HttpServletRequest request)
	{
		return actionRegistry.getAction(request.getParameter("action"));
	}

	// TODO sveng 24.06.2023: Einkommentieren, wenn SubActions genutzt werden.
//	private Class<? extends Action>
//	    loadAction(HttpServletRequest request, String actionName)
//	{
//		return actionRegistry.getAction(actionName);
//	}
}
