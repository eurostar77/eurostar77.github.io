package com.bueffeltier.ui.webapp.content.view;

import static j2html.TagCreator.*;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.bueffeltier.data.jdbc.ElementJDBCFlat;
import com.bueffeltier.data.jdbc.InfoBit;
import com.bueffeltier.data.jdbc.InfoBitDaoJDBC;
import com.bueffeltier.ui.html.molecule.SpacingPropertyDV;
import com.bueffeltier.ui.html.molecule.SpacingSidesDV;
import com.bueffeltier.ui.html.molecule.SpacingSizeDV;
import com.bueffeltier.ui.html.organism.ButtonBuilder;
import com.bueffeltier.ui.html.organism.ButtonBuilder.ButtonInputTypeDV;
import com.bueffeltier.ui.html.organism.ButtonBuilder.ColorDV;
import com.bueffeltier.ui.html.organism.ColumnBuilder;
import com.bueffeltier.ui.html.organism.ColumnBuilder.BreakpointDV;
import com.bueffeltier.ui.html.organism.ColumnBuilder.GridWidthDV;
import com.bueffeltier.ui.html.organism.FormControlBuilder;
import com.bueffeltier.ui.html.organism.FormControlBuilder.FormInputTypeDV;
import com.bueffeltier.ui.html.organism.LabelBuilder;
import com.bueffeltier.ui.html.organism.RowBuilder;

import j2html.tags.DomContent;

/**
 *
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
public class CreateFlipcardsView extends AbstractView
{

	private static CreateFlipcardsView instance;

	private InfoBitDaoJDBC infoBitDaoJDBC = InfoBitDaoJDBC.getInstance();

	private CreateFlipcardsView()
	{
		super();
	}

	public static CreateFlipcardsView getInstance()
	{
		if (instance == null)
		{
			instance = new CreateFlipcardsView();
		}
		return instance;
	}

//    private boolean containsCard(Enumeration cards)
//    {
//        boolean containsCard = false;
//        while (cards.hasMoreElements())
//        {
//            if (cards.nextElement().)
//            {
//
//            }
//        }
//    }

	@Override
	public DomContent
	    writeHtml(ElementJDBCFlat element, HttpServletRequest request)
	        throws Exception
	{
		/*
		 * 1. Je nach Permission-Status: Musterstapel laden Letzten Stapel laden
		 * Sinnvollen Stapel laden 3. Stapel werden gespeichert. 4. Lernschicht
		 * überwacht Stapelgröße (Anzahl), 5.
		 * 
		 * 
		 */
//		List<FrageAntwort> cardStack = frageAntwortDaoImpl.findAll();
		List<InfoBit> cardStack = infoBitDaoJDBC.readAll();

		if (cardStack.size() == 0)
		{
			InfoBit infoBit = new InfoBit();
			// TODO sveng 03.03.2023: default werte
		}

		return div(
		    div(
		        form(
		            element.getType(),

		            ButtonBuilder.create()//
		                .withText("Kartenstapel anwenden")//
		                .withHref("/bueffeltier/lernkarten")//
		                .withColor(ColorDV.SECONDARY)//
		                .withSpacing(
		                    SpacingPropertyDV.MARGIN, SpacingSidesDV.BLANK,
		                    SpacingSizeDV.ONE
		                )//
		                .build(),
		            ButtonBuilder.create()//
		                .withText("Kartenstapel wechseln")//
		                .withHref("/bueffeltier/subject-admin")//
		                .withColor(ColorDV.SECONDARY)//
		                .withSpacing(
		                    SpacingPropertyDV.MARGIN, SpacingSidesDV.BLANK,
		                    SpacingSizeDV.ONE
		                )//
		                .build(), //

		            h1("Lernkarten erstellen").withClass("my-5"),

		            RowBuilder.create()//
		                .withDomContent(
		                    ColumnBuilder.create()//
		                        .withDomContent(
		                            LabelBuilder.create()//
		                                .build(
		                                    "Name des Stapels", "stapel-name"
		                                )
									//
		                        )//
		                        .withGridWidth(GridWidthDV.THREE)//
		                        .withBreakpoint(BreakpointDV.MD).build(),
		                    ColumnBuilder.create()//
		                        .withDomContent(
		                            FormControlBuilder.create()//
		                                .withValue("stackName")//
		                                .withName("stackName")//
		                                .withId("stapel-name")//
		                                .build()//

									//
		                        )//
		                        .withGridWidth(GridWidthDV.NINE)//
		                        .withBreakpoint(BreakpointDV.MD).build()

							//
		                )//
		                .build(),
		            RowBuilder.create()//
		                .withDomContent(
		                    ColumnBuilder.create()//
		                        .withDomContent(
		                            LabelBuilder.create()//
		                                .build(
		                                    "Thema des Stapels", "stapel-thema"
		                                )
		                        )//
		                        .withGridWidth(GridWidthDV.THREE)//
		                        .withBreakpoint(BreakpointDV.MD).build(),
		                    ColumnBuilder.create()//
		                        .withDomContent(
		                            FormControlBuilder.create()//
		                                .withValue("stapel-thema")//
		                                .withName("stapel-thema")//
		                                .withType(FormInputTypeDV.TEXTAREA)//
		                                .withRows(2)//
		                                .withId("stapel-thema")//
		                                .withSpacing(
		                                    SpacingPropertyDV.MARGIN,
		                                    SpacingSidesDV.BOTTOM,
		                                    SpacingSizeDV.FOUR
		                                )//
		                                .build()//
		                        )//
		                        .withGridWidth(GridWidthDV.NINE)//
		                        .withBreakpoint(BreakpointDV.MD).build()
		                )//
		                .build(),
		            RowBuilder.create()//
		                .withDomContent(
		                    ColumnBuilder.create()//
		                        .withDomContent(
		                            ButtonBuilder.create()//
		                                .withText("Neue Karte erstellen")//
		                                .withId("addBtn")//
		                                .withInputType(ButtonInputTypeDV.BUTTON)//
		                                .withOncLick(null)//
		                                .withColor(ColorDV.PRIMARY)//
		                                .build()
		                        )//
		                        .withGridWidth(GridWidthDV.AUTO)//
		                        .withBreakpoint(BreakpointDV.MD).build(),
		                    ColumnBuilder.create()//
		                        .withDomContent(
		                            ButtonBuilder.create()//
		                                .withText("Lernkarten importieren")//
		                                .withColor(ColorDV.SECONDARY)//
//		                                .withSpacing(
//		                                    SpacingPropertyDV.MARGIN,
//		                                    SpacingSidesDV.BLANK,
//		                                    SpacingSizeDV.ONE
//		                                )//
		                                .build()
		                        )//
		                        .withGridWidth(GridWidthDV.AUTO)//
		                        .withBreakpoint(BreakpointDV.MD).build()
		                )//
		                .build(),

		            div(each(cardStack, card -> writeCard(card)))
		                .withId("cards"),
		            br(), //

		            /*
		             * Untere Buttons nur nach if anzeigen.
		             */
		            ButtonBuilder.create()//
		                .withText("Neue Karte erstellen")//
		                .withInputType(ButtonInputTypeDV.BUTTON)//
		                .withColor(ColorDV.PRIMARY)//
		                .withSpacing(
		                    SpacingPropertyDV.MARGIN, SpacingSidesDV.BLANK,
		                    SpacingSizeDV.TWO
		                )//
		                .withId("addBtn")//
		                .withInputType(ButtonInputTypeDV.BUTTON)//
		                .build(), //
		            br(), //
		            ButtonBuilder.create()//
		                .withText("Kartenstapel speichern")//
		                .withColor(ColorDV.PRIMARY)//
		                .withSpacing(
		                    SpacingPropertyDV.MARGIN, SpacingSidesDV.BLANK,
		                    SpacingSizeDV.TWO
		                )//
		                .withId("")//
		                .withInputType(ButtonInputTypeDV.SUBMIT)//
		                .build(), //
		            ButtonBuilder.create()//
		                .withText("Abbrechen")//
		                .withColor(ColorDV.SECONDARY)//
		                .withSpacing(
		                    SpacingPropertyDV.MARGIN, SpacingSidesDV.BLANK,
		                    SpacingSizeDV.TWO
		                )//
		                .withId("")//
		                .withInputType(ButtonInputTypeDV.BUTTON)//
		                .build() //
		        )//
//		        .withClass("card-body")
		    )
		);//
//		.withClass("card");
	}

	private DomContent writeCard(InfoBit card)
	{
		int cardCount = 0;

		cardCount++;
		return div(
		    h3("Karte " + cardCount).withClass("card-header")
		        .withId("ch" + cardCount),
		    div(
		        div(
		            h4("Aufgabe").withClass("card-header"),
		            div(
		                textarea(card.getInfoBitQuestion())
		                    .withClass("form-control").attr("rows", "2")
		                    .withId("q" + cardCount)
		                    .attr("name", "q" + card.getInfoBitId())
		            ).withClass("card-body")
		        ).withClass(".g-col-4"),
		        div(
		            h4("Lösung").withClass("card-header"),
		            div(
		                textarea(card.getInfoBitQuestion())
		                    .withClass("form-control").attr("rows", "2")
		                    .withId("a" + cardCount)
		                    .attr("name", "a" + card.getInfoBitId())
		            ).withClass("card-body")
		        ).withClass(".g-col-4")
		    ).withClass("grid").attr("class", "grid")
		).withClass("card my-5 bg-light").withId("card" + cardCount);
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
