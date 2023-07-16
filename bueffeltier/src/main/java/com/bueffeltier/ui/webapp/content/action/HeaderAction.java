package com.bueffeltier.ui.webapp.content.action;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import com.bueffeltier.logic.foundation.AuthPermissionService;

/**
 *
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
public class HeaderAction extends AbstractAction
{
	private static HeaderAction instance;

	private AuthPermissionService authPermissionService = AuthPermissionService
	    .getInstance();

	private HeaderAction(HttpServletRequest request)
	{
		super();
	}

	public static HeaderAction getInstance(HttpServletRequest request)
	{
		if (instance == null)
		{
			instance = new HeaderAction(request);
		}
		return instance;
	}

	@Override
	public void execute(HttpServletRequest request)
	{
		Enumeration<String> parameterNames = request.getParameterNames();

		while (parameterNames.hasMoreElements())
		{
			String actionParameterName = parameterNames.nextElement()
			    .toString();
			switch (actionParameterName) {

			case "logout":
				doLogout(request);
				break;
			}
		}
	}

	private void doLogout(HttpServletRequest request)
	{
		int permission = (int) request.getAttribute("permission");

		if (permission > 0)
		{
			authPermissionService.logout(request);

			forwardToPage(request, "/");
		}
	}

	@Override
	public void executeAjax(HttpServletRequest request)
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
