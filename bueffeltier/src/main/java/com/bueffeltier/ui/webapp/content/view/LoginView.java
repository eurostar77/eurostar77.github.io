package com.bueffeltier.ui.webapp.content.view;

import static j2html.TagCreator.*;

import javax.servlet.http.HttpServletRequest;

import com.bueffeltier.data.jdbc.ElementJDBCFlat;
import com.bueffeltier.ui.html.organism.ButtonBuilder;
import com.bueffeltier.ui.html.organism.ButtonBuilder.ButtonTypeDV;
import com.bueffeltier.ui.html.organism.FormControlBuilder;
import com.bueffeltier.ui.html.organism.FormControlBuilder.FormInputTypeDV;
import com.bueffeltier.ui.html.organism.LabelBuilder;

import j2html.tags.DomContent;

/**
 *
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
public class LoginView extends AbstractView
{
	private static LoginView instance;

	private LoginView()
	{
		super();
	}

	public static LoginView getInstance()
	{
		if (instance == null)
		{
			instance = new LoginView();
		}
		return instance;
	}

	@Override
	public DomContent
	    writeHtml(ElementJDBCFlat element, HttpServletRequest request)
	{
		return form(

		    element.getType(),
		    h3("Mit eigenem Konto einloggen").withClass("mb-4"),

		    table(
		        tr(
		            td(
		                LabelBuilder.create()
		                    .build("Benutzername:", "user-name-input")
		            ),
		            td(
		                FormControlBuilder.create().withName("username")
		                    .withId("user-name-input").build()
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
		                    .withName("password").withId("password-input")
		                    .build()
		            )
		        ),
		        tr(
		            td(
		                LabelBuilder.create()
		                    .build("Passwort anzeigen", "show-password")
		            ),
		            td(
		                FormControlBuilder.create()
		                    .withType(FormInputTypeDV.CHECKBOX)
		                    .withName("showPw").withValue("showPw")
		                    .withId("show-password").build()
		            )
		        ),
		        tr(
		            td("Passwort vergessen?"),
		            td(
		                ButtonBuilder.create().withText("Senden")
		                    .withButtonType(ButtonTypeDV.SUBMIT)
		                    .withName("login").withValue("login").build()
		            )
		        ),
		        tr(
		            td(),

		            td(
		                a("Mitglied werden!")
		                    .attr("href", "/bueffeltier/register")
		            )
		        )
		    )
		).attr("method", "post");
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
