package com.bueffeltier.ui.webapp.content.action;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
public class MemberPersonalDataAction extends AbstractAction
{

	private static MemberPersonalDataAction instance;

	private MemberPersonalDataAction()
	{
		super();
	}

	public static MemberPersonalDataAction getInstance()
	{
		if (instance == null)
		{
			instance = new MemberPersonalDataAction();
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
