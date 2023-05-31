package com.bueffeltier.ui.webapp.content.action;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
public class MemberNotesAction extends AbstractAction
{

	private static MemberNotesAction instance;

	private MemberNotesAction()
	{
		super();
	}

	public static MemberNotesAction getInstance()
	{
		if (instance == null)
		{
			instance = new MemberNotesAction();
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
