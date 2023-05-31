package com.bueffeltier.ui.webapp.content.view;

import static j2html.TagCreator.*;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import com.bueffeltier.data.jdbc.ElementJDBCFlat;

import j2html.tags.DomContent;

/**
 *
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
public class MottoView extends AbstractView
{

	private static MottoView instance;

	private MottoView()
	{
		super();
	}

	public static MottoView getInstance()
	{
		if (instance == null)
		{
			instance = new MottoView();
		}
		return instance;
	}

	@Override
	public DomContent
	    writeHtml(ElementJDBCFlat element, HttpServletRequest request)
	        throws Exception
	{
		return div(randomMotto(1, 2));
	}

	private DomContent randomMotto(int min, int max)
	{
		Random random = new Random();
		int randomInt = random.nextInt(max - min + 1) + min;
		switch (randomInt) {
		case 1:
			return p("Leben heißt Lernen!");
		case 2:
			return p("Gas geben!");
		default:
			return p("Leben heißt Lernen!");

		}
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
