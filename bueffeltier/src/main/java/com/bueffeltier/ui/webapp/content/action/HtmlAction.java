package com.bueffeltier.ui.webapp.content.action;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
public class HtmlAction extends AbstractAction
{

	private static HtmlAction instance;

	private HtmlAction()
	{
		super();
	}

	public static HtmlAction getInstance()
	{
		if (instance == null)
		{
			instance = new HtmlAction();
		}
		return instance;
	}

	/**
	 *
	 */
	@Override
	public void execute(HttpServletRequest request)
	{
		throw new UnsupportedOperationException("Not supported yet."); // To
																		// change
																		// body
																		// of
																		// generated
																		// methods,
																		// choose
																		// Tools
																		// |
																		// Templates.
	}

	/**
	 *
	 */
	@Override
	public void doAjaxAction(HttpServletRequest request)
	{
		throw new UnsupportedOperationException("Not supported yet."); // To
																		// change
																		// body
																		// of
																		// generated
																		// methods,
																		// choose
																		// Tools
																		// |
																		// Templates.
	}
}
