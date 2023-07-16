package com.bueffeltier.ui.webapp.content.view;

import javax.servlet.http.HttpServletRequest;

import com.bueffeltier.data.jdbc.ElementJDBCFlat;
import com.bueffeltier.ui.html.organism.ButtonBuilder;
import com.bueffeltier.ui.html.organism.ButtonBuilder.ButtonTypeDV;

import j2html.tags.DomContent;

public class TestView extends AbstractView
{
	private static TestView instance;

	private TestView(HttpServletRequest request)
	{

	}

	public static TestView getInstance(HttpServletRequest request)
	{
		if (instance == null)
		{
			instance = new TestView(request);
		}
		return instance;
	}

	@Override
	public DomContent
	    writeHtml(ElementJDBCFlat element, HttpServletRequest request)
	        throws Exception
	{
		return ButtonBuilder.create()//
		    .withText("TestButton")//
		    .withClass("testButton")//
		    .withButtonType(ButtonTypeDV.BUTTON)//
		    .build();
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

	@Override
	public DomContent writeHtmlControls()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String writeCss()
	{
		// TODO Auto-generated method stub
		return null;
	}
}
