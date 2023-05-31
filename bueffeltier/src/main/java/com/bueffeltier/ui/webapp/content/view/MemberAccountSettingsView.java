package com.bueffeltier.ui.webapp.content.view;

import static j2html.TagCreator.*;

import javax.servlet.http.HttpServletRequest;

import com.bueffeltier.data.jdbc.ElementJDBCFlat;
import com.bueffeltier.data.jdbc.UserDaoJDBC;
import com.bueffeltier.ui.html.organism.FormControlBuilder;
import com.bueffeltier.ui.html.organism.FormControlBuilder.FormInputTypeDV;
import com.bueffeltier.ui.html.organism.LabelBuilder;

import j2html.tags.DomContent;

/**
 *
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
public class MemberAccountSettingsView extends AbstractView
{
	private static MemberAccountSettingsView instance;

	private UserDaoJDBC userDaoJDBC = UserDaoJDBC.getInstance();

	private MemberAccountSettingsView()
	{
		super();
	}

	public static MemberAccountSettingsView getInstance()
	{
		if (instance == null)
		{
			instance = new MemberAccountSettingsView();
		}
		return instance;
	}

	@Override
	public DomContent
	    writeHtml(ElementJDBCFlat element, HttpServletRequest request)
	        throws Exception
	{
		return form(
		    element.getType(), //
		    h1("Personal Data"), //
		    LabelBuilder.create().build("Vorname", "vorname"),
		    FormControlBuilder.create().build("vorname"),

		    LabelBuilder.create().build("Nachname", "nachname"),
		    FormControlBuilder.create().build("nachname"),

		    LabelBuilder.create().build("E-Mail", "email"),
		    FormControlBuilder.create().withType(FormInputTypeDV.EMAIL)
		        .build("email"),

		    LabelBuilder.create().build("Passwort", "password"),
		    FormControlBuilder.create().withType(FormInputTypeDV.PASSWORD)
		        .build("password"), //
		    p("Konto löschen.")
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
