package com.bueffeltier.ui.webapp.content.view;

import static com.bueffeltier.ui.html.molecule.Bootstrap.*;
import static j2html.TagCreator.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.bueffeltier.data.jdbc.ElementJDBCFlat;
import com.bueffeltier.ui.html.molecule.Bootstrap;
import com.bueffeltier.ui.html.organism.ButtonBuilder;
import com.bueffeltier.ui.html.organism.ButtonBuilder.ButtonInputTypeDV;
import com.bueffeltier.ui.html.organism.ButtonBuilder.ColorDV;

import j2html.tags.DomContent;

/**
 *
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
public class ContactView extends AbstractView
{

	private static ContactView instance;

	private ContactView()
	{
		super();
	}

	public static ContactView getInstance()
	{
		if (instance == null)
		{
			instance = new ContactView();
		}
		return instance;
	}

	@Override
	public DomContent
	    writeHtml(ElementJDBCFlat element, HttpServletRequest request)
	        throws Exception
	{
		List<String> tHead = new ArrayList<>();
		tHead.add("Id");
		tHead.add("Frage");
		tHead.add("Antwort");
		tHead.add("Punkte");

		List<List<String>> tBodyItems = Arrays.asList(
		    Arrays.asList(
		        "1", "Wofür steht die Abkürzung KGaA?",
		        "Kommanditgesellschaft auf Aktien", "1"
		    ),
		    Arrays.asList(
		        "2", "Wie hoch ist die Mehrwertsteuer in Deutschland?",
		        "19 Prozent", "1"
		    ),
		    Arrays.asList(
		        "3",
		        "In welcher Einheit wird der elektrische Widerstand gemessen?",
		        "Ohm", "1"
		    ),
		    Arrays.asList(
		        "4", "Wofür steht das „L“ im Sender RTL?", "Luxembourg", "1"
		    )
		);

		List<DomContent> list = new ArrayList<>();
		list.add(
		    ButtonBuilder.create()//
		        .withText("Button 1")//
		        .withColor(ColorDV.SECONDARY)//
		        .withClass(" me-md-2")//
		        .withId("")//
		        .isToggled(true)//
		        .withInputType(ButtonInputTypeDV.BUTTON)//
		        .build()//

		);
		list.add(
		    ButtonBuilder.create().withText("Button 2")
		        .withColor(ColorDV.PRIMARY).build()
		);
		return form(
		    element.getType(),
		    div(
		        label("Vorname:").attr("for", "vorname"),
		        input().attr("type", "text").attr("id", "vorname")
		            .attr("name", "vorname").attr("autocomplete", "given-name"),
		        br(),
		        label("Nachname:").attr("for", "nachname")
		            .attr("autocomplete", "given-name"),
		        input().attr("type", "text").attr("id", "nachname")
		            .attr("name", "nacfamily-name"),
		        br(), label("E-Mail-Adresse:").attr("for", "email"),
		        input().attr("type", "text").attr("id", "email")
		            .attr("email", "email").attr("autocomplete", "email"),
		        br(), label("Nachricht:").attr("for", "nachricht"),
		        input().attr("type", "textarea").attr("id", "nachricht")
		            .attr("nachricht", "nachricht"),
		        br(), input().attr("type", "submit").attr("value", "Submit"),
		        br(), br(), br(),
		        Bootstrap.bsTable(
		            tHead, tBodyItems, true,
		            "table table-sm table-info table-striped table-hover table-responsive",
		            "Tabellenüberschrift"
		        ),
		        ButtonBuilder.create().withText("Button 2")
		            .withColor(ColorDV.PRIMARY).build(),
		        div(
		            ButtonBuilder.create().withText("Button 2")
		                .withColor(ColorDV.PRIMARY).build(),
		            ButtonBuilder.create().withText("Button 2")
		                .withColor(ColorDV.PRIMARY).build()
		        ).withClass("d-grid gap-2"),
		        bsButtonGroup(
		            list, "d-grid gap-2 d-md-flex justify-content-md-end"
		        ), bsForm()
				// bsAlert("Alert!", "alert-primary")
		    )
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
