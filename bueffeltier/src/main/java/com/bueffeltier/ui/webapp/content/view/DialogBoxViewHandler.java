package com.bueffeltier.ui.webapp.content.view;

/**
 *
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
public class DialogBoxViewHandler
{
	private static DialogBoxViewHandler instance;

	private DialogBoxViewHandler()
	{
		super();
	}

	public static DialogBoxViewHandler getInstance()
	{
		if (instance == null)
		{
			instance = new DialogBoxViewHandler();
		}
		return instance;
	}
}
