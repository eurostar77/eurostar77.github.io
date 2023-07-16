package com.bueffeltier.ui.webapp.content.action;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
public class RegisterSubmittedAction extends AbstractAction
{

	private static RegisterSubmittedAction instance;

	private RegisterSubmittedAction()
	{
		super();
	}

	public static RegisterSubmittedAction getInstance()
	{
		if (instance == null)
		{
			instance = new RegisterSubmittedAction();
		}
		return instance;
	}

	@Override
	public void execute(HttpServletRequest request)
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void executeAjax(HttpServletRequest request)
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
