package com.bueffeltier.ui.webapp.content.view;

import javax.servlet.http.HttpServletRequest;

import com.bueffeltier.data.jdbc.ElementJDBCFlat;

import j2html.tags.DomContent;

/**
 *
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
public interface ViewHandler
{
	public DomContent
	    writeHtml(ElementJDBCFlat element, HttpServletRequest request)
	        throws Exception;

	public DomContent writeHtmlControls();

	public DomContent
	    writeInternalHtml(ElementJDBCFlat element, HttpServletRequest request)
	        throws Exception;

	public DomContent writeInternalHtmlControlls();

	public String writeCss();

	public long getContentId(ElementJDBCFlat element);
}
