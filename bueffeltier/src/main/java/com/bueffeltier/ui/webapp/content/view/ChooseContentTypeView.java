package com.bueffeltier.ui.webapp.content.view;

import static j2html.TagCreator.*;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.bueffeltier.data.jdbc.ElementJDBCFlat;
import com.bueffeltier.ui.html.molecule.customTag;
import com.bueffeltier.ui.webapp.content.ViewRegistry;

import j2html.tags.DomContent;

/**
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
public class ChooseContentTypeView extends AbstractView
{
	private static ChooseContentTypeView instance;

	private ChooseContentTypeView()
	{
		super();
	}

	public static ChooseContentTypeView getInstance()
	{
		if (instance == null)
		{
			instance = new ChooseContentTypeView();
		}
		return instance;
	}

	@Override
	public DomContent
	    writeHtml(ElementJDBCFlat element, HttpServletRequest request)
	{
		List<String> elementTypes = ViewRegistry.types();
		return form(
		    element.getType(), h1("Element-Typ wählen:"),
		    label("Element-Typ wählen:").attr("for", "type"),
		    select(each(elementTypes, e -> option(e).withValue(e))
			// ).withName("type").attr("onchange=\"this.form.submit()\""),
		    ).withName("type"),
		    // noscript(customTag.submitButton("Typ ändern", "submitForm",
		    // "submitForm")),
		    customTag.submitButton("auswählen", "send_type", "send_type")
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
