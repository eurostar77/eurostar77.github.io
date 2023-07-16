package com.bueffeltier.ui.webapp.content.action;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
public class UserAdminActionr extends AbstractAction
{

	private static UserAdminActionr instance;

	private UserAdminActionr()
	{
		super();
	}

	public static UserAdminActionr getInstance()
	{
		if (instance == null)
		{
			instance = new UserAdminActionr();
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
