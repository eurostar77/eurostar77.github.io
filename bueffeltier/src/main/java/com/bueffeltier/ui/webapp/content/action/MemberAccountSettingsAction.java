package com.bueffeltier.ui.webapp.content.action;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
public class MemberAccountSettingsAction extends AbstractAction
{

	private static MemberAccountSettingsAction instance;

	private MemberAccountSettingsAction()
	{
		super();
	}

	public static MemberAccountSettingsAction getInstance()
	{
		if (instance == null)
		{
			instance = new MemberAccountSettingsAction();
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
