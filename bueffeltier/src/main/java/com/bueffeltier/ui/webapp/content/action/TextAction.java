package com.bueffeltier.ui.webapp.content.action;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
public class TextAction extends AbstractAction
{

	private static TextAction instance;

	private TextAction()
	{
		super();
	}

	public static TextAction getInstance()
	{
		if (instance == null)
		{
			instance = new TextAction();
		}
		return instance;
	}

	@Override
	public void execute(HttpServletRequest request)
	{
		//
	}

	@Override
	public void executeAjax(HttpServletRequest request)
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
