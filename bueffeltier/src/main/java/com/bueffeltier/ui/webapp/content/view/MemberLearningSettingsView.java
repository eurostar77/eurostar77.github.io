package com.bueffeltier.ui.webapp.content.view;

import static j2html.TagCreator.h1;

import javax.servlet.http.HttpServletRequest;

import com.bueffeltier.data.jdbc.ElementJDBCFlat;

import j2html.tags.DomContent;

/**
 *
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
public class MemberLearningSettingsView extends AbstractView
{

	private static MemberLearningSettingsView instance;

	private MemberLearningSettingsView()
	{
		super();
	}

	public static MemberLearningSettingsView getInstance()
	{
		if (instance == null)
		{
			instance = new MemberLearningSettingsView();
		}
		return instance;
	}

	@Override
	public DomContent
	    writeHtml(ElementJDBCFlat element, HttpServletRequest request)
	        throws Exception
	{

		return form(element.getType(), h1("Learning Settings"));
		// Welchen Kurs biete ich an?
		// Wie ist der Lehrplan?
		// Was will ich erreichen?
		// In welcher Zeit?
		// Wie ist mein Lernstand?
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
