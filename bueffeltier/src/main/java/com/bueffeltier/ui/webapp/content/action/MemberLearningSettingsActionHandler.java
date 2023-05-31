package com.bueffeltier.ui.webapp.content.action;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
public class MemberLearningSettingsActionHandler extends AbstractAction
{

	private static MemberLearningSettingsActionHandler instance;

	private MemberLearningSettingsActionHandler()
	{
		super();
	}

	public static MemberLearningSettingsActionHandler getInstance()
	{
		if (instance == null)
		{
			instance = new MemberLearningSettingsActionHandler();
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
