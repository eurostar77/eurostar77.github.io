package com.bueffeltier.ui.webapp.content.action;

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

	public static DialogBoxActionHandler getInstance()
	{
		if (instance == null)
		{
			instance = new DialogBoxActionHandler();
		}
		return instance;
	}
}
