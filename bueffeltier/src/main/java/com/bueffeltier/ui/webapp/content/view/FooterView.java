package com.bueffeltier.ui.webapp.content.view;

import static j2html.TagCreator.*;

import javax.servlet.http.HttpServletRequest;

import com.bueffeltier.crosscutting.AppPropertyService;
import com.bueffeltier.data.jdbc.ElementJDBCFlat;

import j2html.tags.DomContent;

/**
 *
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
public class FooterView extends AbstractView
{
	private static FooterView instance;

	private AppPropertyService appProperties;

	private FooterView()
	{
		super();
		appProperties = AppPropertyService.getInstance();
	}

	public static FooterView getInstance()
	{
		if (instance == null)
		{
			instance = new FooterView();
		}
		return instance;
	}

	@Override
	public DomContent
	    writeHtml(ElementJDBCFlat element, HttpServletRequest request)
	{
		return footer(
		    div().withClass("footer-right"),
		    a("Kontakt")
		        .withHref(appProperties.getServletContextPath() + "/kontakt"),
		    a("Impressum")
		        .withHref(appProperties.getServletContextPath() + "/impressum"),
		    a("AGB").withHref(appProperties.getServletContextPath() + "/agb"),
		    a("Datenschutzerklärung").withHref(
		        appProperties.getServletContextPath() + "/datenschutzerklaerung"
		    )
		).withClass("footer");
	}

	@Override
	public String writeCss()
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public DomContent writeHtmlControls()
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
