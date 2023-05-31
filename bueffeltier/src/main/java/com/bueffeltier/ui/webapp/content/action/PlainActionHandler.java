package com.bueffeltier.ui.webapp.content.action;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
public class PlainActionHandler extends AbstractAction
{

	final String type = "PAGE_STRUCTURE";

	private boolean enableArtikelAnzeigenButton;
	private boolean enableAblageLeerenButton;
	private boolean enableNeueSeiteButton;

	private static PlainActionHandler instance;

	private PlainActionHandler()
	{
		super();
	}

	public static PlainActionHandler getInstance()
	{
		if (instance == null)
		{
			instance = new PlainActionHandler();
		}
		return instance;
	}

	@Override
	public void execute(HttpServletRequest request)
	{
//        log.add(this, "doAction()");
		Enumeration parameterNames = this.getRequestParameterNames();
		while (parameterNames.hasMoreElements())
		{
			String actionParameterName = parameterNames.nextElement()
					.toString();
			switch (actionParameterName) {
			case "test":
				this.setForwardingPage("xxx"); // todo: hier id eingeben.
				break;
			// todo: final / default ?
			}
		}
		doPageLoad();
	}

	@Override
	public void doAjaxAction(HttpServletRequest request)
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
