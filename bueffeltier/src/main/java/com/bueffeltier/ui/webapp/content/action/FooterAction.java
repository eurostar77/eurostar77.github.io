package com.bueffeltier.ui.webapp.content.action;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
public class FooterAction extends AbstractAction
{

	private static FooterAction instance;

	private FooterAction()
	{
		super();
	}

	public static FooterAction getInstance()
	{
		if (instance == null)
		{
			instance = new FooterAction();
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
