package com.bueffeltier.ui.webapp.content.action;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
public class ArticleFeederAction extends AbstractAction
{

	private String lorem = "Lorem ipsum dolor sit amet, consetetur"
			+ " sadipscing elitr, sed diam nonumy "
			+ "eirmod tempor invidunt ut labore et "
			+ "dolore magna aliquyam erat, sed diam "
			+ "voluptua. At vero eos et accusam et "
			+ "justo duo dolores et ea rebum. "
			+ "Stet clita kasd gubergren, Lorem "
			+ "ipsum dolor sit amet, consetetur " + "sadipscing elitr.";

	private static ArticleFeederAction instance;

	private ArticleFeederAction()
	{
		super();
	}

	public static ArticleFeederAction getInstance()
	{
		if (instance == null)
		{
			instance = new ArticleFeederAction();
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
	public void executeAjax(HttpServletRequest request)
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
