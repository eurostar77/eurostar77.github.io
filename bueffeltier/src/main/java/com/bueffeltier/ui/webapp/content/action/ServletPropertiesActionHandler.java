package com.bueffeltier.ui.webapp.content.action;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
public class ServletPropertiesActionHandler extends AbstractAction
{

	private static ServletPropertiesActionHandler instance;

	private ServletPropertiesActionHandler()
	{
		super();
	}

	public static ServletPropertiesActionHandler getInstance()
	{
		if (instance == null)
		{
			instance = new ServletPropertiesActionHandler();
		}
		return instance;
	}

	@Override
	public void execute(HttpServletRequest request)
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}

	/**
	 *
	 */
	@Override
	public void executeAjax(HttpServletRequest request)
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
