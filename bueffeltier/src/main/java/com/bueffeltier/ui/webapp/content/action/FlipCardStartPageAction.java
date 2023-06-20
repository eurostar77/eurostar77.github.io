package com.bueffeltier.ui.webapp.content.action;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import com.bueffeltier.logic.domain.Lesson;
import com.bueffeltier.ui.webapp.RedirectDataService;

/**
 *
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
public class FlipCardStartPageAction extends AbstractAction
{

	private static FlipCardStartPageAction instance;

	private RedirectDataService redirectDataService = RedirectDataService
	    .getInstance();

	private FlipCardStartPageAction()
	{
		super();
	}

	public static FlipCardStartPageAction
	    getInstance(HttpServletRequest request)
	{
		if (instance == null)
		{
			instance = new FlipCardStartPageAction();
		}
		return instance;
	}

	@Override
	public void execute(HttpServletRequest request)
	{
		// Actions:
		// Karte erstellen: JS
		// - Aufgabenliste aktualisieren
		// Karte löschen oder Karte entfernen:
		// - Aufgabenliste aktualisieren
		// Kartenstapel speichern: SUBMIT
		// Abbrechen: ?
		// Lernkarten Importieren: ?

		// FRAGEN:
		// - Wie ordne ich Karten hinzu?
		// - Wie werden dann Später Lektionen mit anderen Fragetypen erstellt?

		// TODO:
		// Fragen existieren unabhängig von Aufgaben
		// Numerierung der Karten 1,1,2,3 ???
		// JS: Neue Karte erst, wenn alle Karten ausgefüllt sind.
		// JS: Eine Karte muss immer da sein. (JS-Abstracte Basisklasse?

		Enumeration<String> parameterNames = request.getParameterNames();

		while (parameterNames.hasMoreElements())
		{
			String actionParameterName = parameterNames.nextElement()
			    .toString();

			switch (actionParameterName) {

			case "bearbeiten":
				// request.getAttribute("bearbeiten") // die lesson-id
				// lektion in lernkarten-bearbeiten-View öffnen
				//
				break;

			case "löschen":
				// abfrage per js, ob gelöscht werden soll?
				// request.getAttribute("bearbeiten") // die lesson-id
				// lektion löschen

				break;

			case "auswählen":
				//

				break;

			case "new":

//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
				// abfragen, von welcher seite das request kommt?
				// Umbau auf Generische Typen

				redirectDataService
				    .addData(request, "createNewLesson", Boolean.TRUE);

				// todo: mit redirectDataService vereinen.
//				request.setAttribute("createNewLesson", true);
				this.forwardToPage(request, "/subject-editor");
				break;

			case "create":
				Lesson lesson = new Lesson();
				lesson.setName(actionParameterName);
				lesson.setOwnerId(0);
//				Lernstand
				this.forwardToPage(request, "/subject-editor");
				break;
			}
		}
	}

	@Override
	public void doAjaxAction(HttpServletRequest request)
	{
	}
}
