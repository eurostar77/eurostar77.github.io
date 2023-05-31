package com.bueffeltier.ui.webapp.content.view;

import static j2html.TagCreator.*;

import javax.servlet.http.HttpServletRequest;

import com.bueffeltier.data.jdbc.ElementJDBCFlat;

import j2html.tags.DomContent;

/**
 *
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
public class LoginMenuView extends AbstractView
{
	private static LoginMenuView instance;

	private LoginMenuView()
	{
		super();
	}

	public static LoginMenuView getInstance()
	{
		if (instance == null)
		{
			instance = new LoginMenuView();
		}
		return instance;
	}

	@Override
	public DomContent
	    writeHtml(ElementJDBCFlat element, HttpServletRequest request)
	        throws Exception
	{
		return form(element.getType(), showSettings());
	}

	/**
	 *
	 * @return
	 */
	public DomContent showSettings()
	{
		return html(
		    button("Einstellungen").attr("type", "button"),
		    button("Logout").attr("type", "button")
		).attr("class", "btn-group");
	}

	/**
	 *
	 * @return
	 */
	@Override
	public DomContent writeHtmlControls()
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}

	/**
	 *
	 * @return
	 */
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
