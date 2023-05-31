package com.bueffeltier.ui.webapp.content.action;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.swing.JOptionPane;

/**
 *
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
public class FlipCardAction extends AbstractAction
{

	String question = "Definiere \"Logistik\"";
	String answer = "Betriebswirtschaftliches Teilgebiet, welches sich mit dem Warenfluß (und den dazugehörigen Informationen) innerhalb des Unternehmens beschäftigt.";

	private static FlipCardAction instance;

	private FlipCardAction()
	{
		super();
	}

	public static FlipCardAction getInstance()
	{
		if (instance == null)
		{
			instance = new FlipCardAction();
		}
		return instance;
	}

	@Override
	public void execute(HttpServletRequest request)
	{
		Enumeration parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements())
		{
			String actionParameterName = parameterNames.nextElement()
					.toString();
			switch (actionParameterName) {
			case "right":
				// todo: ergebnis protokollieren.
				JOptionPane.showMessageDialog(null, "right");
				break;
			case "wrong":
				// todo: ergebnis protokollieren.
				JOptionPane.showMessageDialog(null, "wrong");
				break;

			}
		}
		forwardToPage(request, "/lernkarten");
	}

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
