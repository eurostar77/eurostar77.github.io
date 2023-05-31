package com.bueffeltier.ui.webapp.content.view;

import static j2html.TagCreator.*;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.bueffeltier.crosscutting.AppPropertyService;
import com.bueffeltier.data.jdbc.ElementJDBCFlat;
import com.bueffeltier.ui.html.molecule.Bootstrap;
import com.bueffeltier.ui.html.molecule.SpacingPropertyDV;
import com.bueffeltier.ui.html.molecule.SpacingSidesDV;
import com.bueffeltier.ui.html.molecule.SpacingSizeDV;
import com.bueffeltier.ui.html.organism.ButtonBuilder;
import com.bueffeltier.ui.html.organism.ButtonBuilder.ButtonInputTypeDV;
import com.bueffeltier.ui.html.organism.ButtonBuilder.ButtonTagTypeDV;
import com.bueffeltier.ui.html.organism.ButtonBuilder.ColorDV;

import j2html.tags.DomContent;

/**
 *
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
public class FlipCardView extends AbstractView
{
	private final String question = "Definiere \"Logistik\"";
	private final String answer = "Betriebswirtschaftliches Teilgebiet, welches sich mit dem Warenfluß (und den dazugehörigen Informationen) innerhalb des Unternehmens beschäftigt.";

	private static FlipCardView instance;

	private AppPropertyService appPropertyService;

	private FlipCardView()
	{
		super();

		// TODO sveng 27.11.2022: injizieren?
		appPropertyService = AppPropertyService.getInstance();
	}

	public static FlipCardView getInstance()
	{
		if (instance == null)
		{
			instance = new FlipCardView();
		}
		return instance;
	}

	@Override
	public DomContent
	    writeHtml(ElementJDBCFlat element, HttpServletRequest request)
	        throws Exception
	{

		/*
		 * todo: 0. Einstellungen: Antwort auf der selben Seite wie die Frage
		 * anzeigen. 0. https://de.wikipedia.org/wiki/Lernkartei 1. Design
		 * auswählbar: Papierkarte mit Linien. 2. Exakte Lernpunkte definieren.
		 * 3. Bei mehreren Punkten pro Karte ggf. nur Vermittlungspunkte
		 * speichern. 4. Antwortkarte mit verschiedenen Punkteverteilungen. Es
		 * sollen alle Lernpunkte abgedeckt werden. 5. Beim Erstellen der Karten
		 * kann man auch den Lernstoff in der DB feiner granulieren und
		 * Unterpunkte hinzufügen. 6. Bilder, Skizzen, Sounds und Videos
		 * einfügbar. 7. Typen: Vokabeln Definitionen Formeln Daten/Kennzahlen
		 * Diagramme Frage- und Antwortsätze Gliederungen Zusammenfassungen
		 * Multiple-Choice-Fragen 8. Handschrift ermöglichen 9. Notizen
		 * hinterlegbar zu jedem Aufgabentypen 10. Übersichtskarten 11. Warnung
		 * bei zu viel Inhalt, oder Beschränkung von Grund auf. 12. Beispiele
		 * angeben, aber dann andere Bewertung des Ergebnisses. 13. Zuordnung zu
		 * einem Bestimmten Thema in der DB, Tags, Links, 14. Karten sind ein
		 * Teil eines Karteikastens (Lüning) 15. Ist die Karte zu beidseitigem
		 * Lernen geeignet? 16. Klassisches Lernen: Gelöste Karten Problemkarten
		 * Wiederholung des Stoffes 17. Karten nach Schwierigkeit einteilen. 18.
		 * Erstes Fach: Täglich Zweites Fach: Jeden zweiten oder dritten Tag
		 * Drittes Fach: Wöchentlich Viertes Fach: Monatlich oder seltener 19.
		 * Druckfunktion (Rückseite gedreht) 20. ,Kategorisiere Deine
		 * Karteikarten nach Thema, Veranstaltung oder Datum. Verwende dafür
		 * beispielsweise Kürzel wie: „VL Nr. 1 Makro“. 21. Nutze verschiedene
		 * Karteikästen für verschiedene Themen, 22. Quelle mit Seitenanzahl auf
		 * der Karteikarte vermerken 23. vorlese funktion 24. erklärung zum
		 * lernen lehren. 25. Art der Auswertung: Richtig - Falsch Punkte für
		 * Teilantworten 26. Tabellenfelder:
		 */
		ArrayList<DomContent> btnList = new ArrayList<>();

		btnList.add(
		    ButtonBuilder.create()//
		        .withText("Antwort ist richtig")//
		        .withInputType(ButtonInputTypeDV.SUBMIT)//
		        .withColor(ColorDV.SUCCESS)//
		        .withValue("right")//
		        .withName("right")//
		        .withId("btnAnswerRight")//
		        .isToggled(false)//
		        //
		        .build()
		);
		btnList.add(
		    ButtonBuilder.create()//
		        .withText("Antwort ist falsch")//
		        .withInputType(ButtonInputTypeDV.SUBMIT)//
		        .withColor(ColorDV.DANGER)//
		        .withValue("wrong")//
		        .withName("wrong")//
		        .withId("btnAnswerWrong")//
		        .isToggled(false)//
		        //
		        .build()
		);

		return div(
		    div(
		        ButtonBuilder.create()//
		            .withText("Lernkarten bearbeiten")//
		            .withColor(ColorDV.SECONDARY)//
		            .withSpacing(
		                SpacingPropertyDV.MARGIN, SpacingSidesDV.BLANK,
		                SpacingSizeDV.ONE
		            )
		            .withHref(
		                appPropertyService.getHostName()
		                    + appPropertyService.getServletContextPath()
		                    + "/subject-editor"
		            )//
		            .withTagType(ButtonTagTypeDV.A)//
		            .build(), //

		        ButtonBuilder.create()//
		            .withText("Kartenstapel wechseln")//
		            .withColor(ColorDV.SECONDARY)//
		            .withSpacing(
		                SpacingPropertyDV.MARGIN, SpacingSidesDV.BLANK,
		                SpacingSizeDV.ONE
		            )
		            .withHref(
		                appPropertyService.getHostName()
		                    + appPropertyService.getServletContextPath()
		                    + "/subject-admin"
		            )//
		            .build() //
		    ).withClasses("btn.group ").attr("role", "group"),
		    h1("Aktueller Kartenstapel").withClass("m-3"), div(
		        // todo: ggf. Anleitung zur Übung

		        // a("Button")
		        // .withHref("javascript:void(0);")
		        // .withClass("btn btn-primary")
		        // .withId("flipBtn"),
		        p(
		            // ButtonBuilder.create()//
//		                .withText("Karte drehen")//
//		                .withColor(ColorDV.PRIMARY)//
//		                .withSpacing(
//		                    SpacingPropertyDV.MARGIN, SpacingSidesDV.BLANK,
//		                    SpacingSizeDV.ONE
//		                )//
//		                .withId("flipBtn")//
//		                .withInputType(ButtonInputTypeDV.SUBMIT).build()
		        ),
		        // script(
		        // "function test() {alert(\"how are you\");}"
		        // ).withType("text/javascript"),
		        div(
		            //
		            div(
		                //
		                div(
		                    //
		                    section(
		                        div(
		                            // Frage:
		                            div(
		                                h3("Aufgabe:").withClass("card-header"),
		                                p(question).withClass("card-body")
		                                    .withId("front")
		                            ).withId("front"),
		                            //
		                            // Antwort:
		                            div(
		                                h3("Lösung:").withClass("card-header"),
		                                p(answer).withClass("card-body"),
		                                form(
		                                    element.getType(),
		                                    Bootstrap.bsButtonGroup(
		                                        btnList, "d-grid gap-2"
		                                    ),
		                                    ButtonBuilder.create()//
		                                        .withSpacing(
		                                            SpacingPropertyDV.MARGIN,
		                                            SpacingSidesDV.BLANK,
		                                            SpacingSizeDV.ONE
		                                        )//
		                                        .withText("Senden").build()

		                                )//
//		                                .withClass("card-body")
		                            ).withId("back").withClass("hidden")
		                        ).withId("card")
		                    ).withClass("d-flex flex-column")
		                        .withStyle("height: 400px;")
						//
		                ).withClass("col")
		            ).withClass("row")
		        ).withClass("container"), ButtonBuilder.create()//
		            .withText("Karte drehen")//
		            .withColor(ColorDV.PRIMARY)//
		            .withSpacing(
		                SpacingPropertyDV.MARGIN, SpacingSidesDV.BLANK,
		                SpacingSizeDV.ONE
		            )//
		            .withId("flipBtn")//
		            .withInputType(ButtonInputTypeDV.SUBMIT).build(),
		        section(
		            // p(button("Flip Card").withId("flip"))
		        ).withId("options")
		    )//
//		    .withClasses("card gap-2")
		);//
//		    .withClasses("card", "m-3");

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
