package com.bueffeltier.ui.webapp.content.action;

import javax.servlet.http.HttpServletRequest;

public class AppSettingsAction extends AbstractAction
{

	private static AppSettingsAction instance;

	private AppSettingsAction()
	{
		super();
	}

	public static AppSettingsAction getInstance()
	{
		if (instance == null)
		{
			instance = new AppSettingsAction();
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
