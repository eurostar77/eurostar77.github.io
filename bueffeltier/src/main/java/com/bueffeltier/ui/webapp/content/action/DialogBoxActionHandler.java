package com.bueffeltier.ui.webapp.content.action;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
public class DialogBoxActionHandler
{
	private static DialogBoxActionHandler instance;

	private DialogBoxActionHandler()
	{
		super();
	}

	public static DialogBoxActionHandler getInstance(HttpServletRequest request)
	{
		if (instance == null)
		{
			instance = new DialogBoxActionHandler();
		}
		return instance;
	}
}
