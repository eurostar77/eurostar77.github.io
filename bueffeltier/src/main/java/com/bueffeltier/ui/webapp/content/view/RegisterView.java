package com.bueffeltier.ui.webapp.content.view;

import static j2html.TagCreator.*;

import javax.servlet.http.HttpServletRequest;

import com.bueffeltier.data.jdbc.ElementJDBCFlat;
import com.bueffeltier.ui.html.organism.ButtonBuilder;
import com.bueffeltier.ui.html.organism.ButtonBuilder.ButtonInputTypeDV;
import com.bueffeltier.ui.html.organism.FormControlBuilder;
import com.bueffeltier.ui.html.organism.FormControlBuilder.FormInputTypeDV;
import com.bueffeltier.ui.html.organism.LabelBuilder;

import j2html.tags.DomContent;

/**
 *
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
public class RegisterView extends AbstractView
{
	private static RegisterView instance;

	private RegisterView()
	{
		super();
	}

	public static RegisterView getInstance()
	{
		if (instance == null)
		{
			instance = new RegisterView();
		}
		return instance;
	}

	@Override
	public DomContent
	    writeHtml(ElementJDBCFlat element, HttpServletRequest request)
	{
		return form(

		    element.getType(), h3("Neues Konto erstellen").withClass("mb-4"),

		    table(
		        tr(
		            td(
		                LabelBuilder.create()
		                    .build("Benutzername:", "user-name-input")
		            ),
		            td(
		                FormControlBuilder.create()//
		                    .withId("user-name-input")//
		                    .build()
		            )
		        ),
		        tr(
		            td(
		                LabelBuilder.create()
		                    .build("Passwort:", "password-input")
		            ),
		            td(
		                FormControlBuilder.create()
		                    .withType(FormInputTypeDV.PASSWORD)
		                    .withId("password-input").build()
		            )
		        ),
		        tr(
		            td(LabelBuilder.create().build("E-Mail:", "email-input")),
		            td(
		                FormControlBuilder.create()
		                    .withType(FormInputTypeDV.EMAIL)
		                    .withId("email-input").build()
		            )
		        ), tr(td(

				), td(
				    ButtonBuilder.create().withText("Senden").withName("senden")
				        .withName("login")
				        .withInputType(ButtonInputTypeDV.SUBMIT)
				        .withValue("login").build()
				))

		    )
		);
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

	/**
	 *
	 * @return
	 */
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
