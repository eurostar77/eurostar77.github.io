package com.bueffeltier.ui.webapp.content.action;

import javax.servlet.http.HttpServletRequest;

import com.bueffeltier.logic.domain.Lesson;

/**
 *
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
public class CourseEditorAction extends AbstractAction
{
	private static CourseEditorAction instance;

	private CourseEditorAction()
	{
		super();
	}

	public static CourseEditorAction getInstance(HttpServletRequest request)
	{
		if (instance == null)
		{
			instance = new CourseEditorAction();
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

		String bearbeiten = request.getParameter("bearbeiten");
		if (bearbeiten != null)
		{
			// lektion in lernkarten-bearbeiten-View öffnen
		}

		String loeschen = request.getParameter("loeschen");
		if (loeschen != null)
		{
			// abfrage per js, ob gelöscht werden soll?
			// request.getAttribute("bearbeiten") // die lesson-id
			// lektion löschen

			this.forwardToPage(request, "/lesson-editor");
		}

		String auswählen = request.getParameter("auswählen");
		if (auswählen != null)
		{
			//
		}

		String newLesson = request.getParameter("newLesson");
		if (newLesson != null)
		{
			forwardViewData(request, "createNewLesson", "true");

			// request.setAttribute("createNewLesson", true);
			this.forwardToPage(request, "/lesson-editor");
		}

		String create = request.getParameter("create");
		if (create != null)
		{
			Lesson lesson = new Lesson();
//			lesson.setName(actionParameterName);
			lesson.setOwnerId(0);
			// Lernstand
			this.forwardToPage(request, "/lesson-editor");
		}
	}

	@Override
	public void executeAjax(HttpServletRequest request)
	{

	}
}
