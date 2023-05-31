package com.bueffeltier.ui.webapp.content.action;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
public class LoginMenuAction extends AbstractAction
{

	private static LoginMenuAction instance;

	private LoginMenuAction()
	{
		super();
	}

	public static LoginMenuAction getInstance()
	{
		if (instance == null)
		{
			instance = new LoginMenuAction();
		}
		return instance;
	}

	@Override
	public void execute(HttpServletRequest request)
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void doAjaxAction(HttpServletRequest request)
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
