package com.bueffeltier.ui.webapp.content.view;

import static j2html.TagCreator.*;

import javax.servlet.http.HttpServletRequest;

import com.bueffeltier.data.jdbc.ElementJDBCFlat;

import j2html.tags.DomContent;

/**
 *
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
public class PlainView extends AbstractView
{

	final String type = "PAGE_STRUCTURE";

	private boolean enableArtikelAnzeigenButton;
	private boolean enableAblageLeerenButton;
	private boolean enableNeueSeiteButton;

	private static PlainView instance;

	private PlainView()
	{
		super();
	}

	public static PlainView getInstance()
	{
		if (instance == null)
		{
			instance = new PlainView();
		}
		return instance;
	}

	@Override
	public DomContent
	    writeHtml(ElementJDBCFlat element, HttpServletRequest request)
	{
		return div().with(p("Test HTML"));
	}

	@Override
	public DomContent writeHtmlControls()
	{
//        log.add(this, "writeHtmlControls()");
		return div().with(p("Test Controls"));
	}

	@Override
	public String writeCss()
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

//    @Override
//    public String writeJS()
//    {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
}
