package com.bueffeltier.ui.webapp.content.action;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
public class Http404Action extends AbstractAction
{

	private static Http404Action instance;

	private Http404Action()
	{
		super();
	}

	public static Http404Action getInstance()
	{
		if (instance == null)
		{
			instance = new Http404Action();
		}
		return instance;
	}

	@Override
	public void execute(HttpServletRequest request) throws Exception
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void doAjaxAction(HttpServletRequest request)
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
