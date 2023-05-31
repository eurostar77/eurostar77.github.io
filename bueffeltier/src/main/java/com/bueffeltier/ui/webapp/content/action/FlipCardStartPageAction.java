package com.bueffeltier.ui.webapp.content.action;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
public class FlipCardStartPageAction extends AbstractAction
{

	private static FlipCardStartPageAction instance;

	private FlipCardStartPageAction()
	{
		super();
	}

	public static FlipCardStartPageAction getInstance()
	{
		if (instance == null)
		{
			instance = new FlipCardStartPageAction();
		}
		return instance;
	}

	@Override
	public void execute(HttpServletRequest request)
	{
	}

	@Override
	public void doAjaxAction(HttpServletRequest request)
	{
	}
}
