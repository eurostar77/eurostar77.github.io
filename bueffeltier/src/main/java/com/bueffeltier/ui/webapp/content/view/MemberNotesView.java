package com.bueffeltier.ui.webapp.content.view;

import static j2html.TagCreator.h1;

import javax.servlet.http.HttpServletRequest;

import com.bueffeltier.data.jdbc.ElementJDBCFlat;

import j2html.tags.DomContent;

/**
 *
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
public class MemberNotesView extends AbstractView
{

	private static MemberNotesView instance;

	private MemberNotesView()
	{
		super();
	}

	public static MemberNotesView getInstance()
	{
		if (instance == null)
		{
			instance = new MemberNotesView();
		}
		return instance;
	}

	@Override
	public DomContent
	    writeHtml(ElementJDBCFlat element, HttpServletRequest request)
	        throws Exception
	{
		return form(element.getType(), h1("Notizen"));
		// notizen auflisten, herkunft, link auf seite, zettelkasten.
		// externe links
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
