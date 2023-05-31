package com.bueffeltier.ui.webapp.content.view;

import static j2html.TagCreator.*;

import javax.servlet.http.HttpServletRequest;

import com.bueffeltier.data.jdbc.ElementJDBCFlat;

import j2html.tags.DomContent;

/**
 *
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
public class RegisterSubmittedView extends AbstractView
{

	private static RegisterSubmittedView instance;

	private RegisterSubmittedView()
	{
		super();
	}

	public static RegisterSubmittedView getInstance()
	{
		if (instance == null)
		{
			instance = new RegisterSubmittedView();
		}
		return instance;
	}

	@Override
	public DomContent
	    writeHtml(ElementJDBCFlat element, HttpServletRequest request)
	        throws Exception
	{
		return div(
		    p(
		        "Die Anmeldedaten wurden erfolgreich gesendet."
		            + "Wir senden Ihnen in Kürze einen Aktivierungs-Link an Ihre E-Mail-Adresse."
		    ), a("Startseite").attr("href", "/bueffeltier/")
		);
	}

	@Override
	public DomContent writeHtmlControls()
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public String writeCss()
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	protected void setCssId(String containerTagClassName)
	{
		// TODO Auto-generated method stub

	}

	@Override
	protected void setContainerTag()
	{
		// TODO Auto-generated method stub

	}
}
