package com.bueffeltier.ui.webapp.content;

import java.util.HashMap;
import java.util.Map;

import com.bueffeltier.ui.webapp.content.action.Action;
import com.bueffeltier.ui.webapp.content.pageaction.LessonPageAction;

/*
 * Über diese Klasse erfolgt der Zugriff auf die PageAction Objekte.
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 **/
public class PageActionRegistry
{
	private static PageActionRegistry instance = null;

	private Map<String, Class<? extends Action>> actions;

	private PageActionRegistry()
	{
		buildActionMap();
	}

	public static PageActionRegistry getInstance()
	{
		if (instance == null)
		{
			instance = new PageActionRegistry();
		}
		return instance;
	}

	public void
	    registerAction(String actionName, Class<? extends Action> actionClass)
	{
		actions.put(actionName, actionClass);
	}

	public Class<? extends Action> getActionClass(String actionName)
	{
		return actions.get(actionName);
	}

//		request.getParameter("content"); // TODO sveng 01.12.2022: content
	// verschlüsselt und auch als enum?
//	        default: throw new Exception("Content-Type does not exist!");
	private void buildActionMap()
	{
		actions = new HashMap<>();

		actions.put("lesson", LessonPageAction.class);
	}

	public boolean hasPageAction(String path)
	{
		return actions.containsKey(path);
	}
}
