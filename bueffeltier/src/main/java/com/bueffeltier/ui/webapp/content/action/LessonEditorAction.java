package com.bueffeltier.ui.webapp.content.action;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.bueffeltier.logic.domain.LearningTask;
import com.google.gson.Gson;

/**
 *
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
public class LessonEditorAction extends AbstractAction
{
	int cardCount = 0;

	private static LessonEditorAction instance;

	private LessonEditorAction(HttpServletRequest request)
	{
		super();
	}

	public static LessonEditorAction getInstance(HttpServletRequest request)
	{
		if (instance == null)
		{
			instance = new LessonEditorAction(request);
		}
		return instance;
	}

	@Override
	public void execute(HttpServletRequest request)
	{
		Enumeration<String> pNamesEnumeration = request.getParameterNames();

		// Hier wird eine Lesson gespeichert
		// Lesson.name darf nur einmal existieren.
		// Aktion sollte in Ajax abgewickelt werden.

		// 1. Alle Daten auslesen
		// 2. Überprüfung auf Vollständigkeit der Daten
		// 3. Überprüfen auf Plausibilität und Integrität der Daten
		// ___Parameter-Überprüfung
		// ___Lassen sich Objekte so anlegen/speichern/updaten?
		// 4.
		// 5.
		// 6.

		String inputName = request.getParameter("inputName");
		if (inputName != null)
		{

		}

		String stackTheme = request.getParameter("stackTheme");
		if (stackTheme != null)
		{

		}

		String stackName = request.getParameter("stackName");
		if (stackName != null)
		{

		}

		String json = request.getParameter("json");
		if (json != null)
		{
//			StringBuilder sb = new StringBuilder();
//			BufferedReader reader = request.getParameter("ajax");
//			String line;
//			while ((line = reader.readLine()) != null)
//			{
//				sb.append(line); 
//			}

//			String jsonData = sb.toString();
			// TODO sveng 27.06.2023: Gson-Servie, da gson threadsafe
			// ist.
			// JSON-Parsing mit Gson
			String jsonData = request.getParameter("json");
			Gson gson = new Gson();
			LessonMapper mappedLesson = gson
			    .fromJson(jsonData, LessonMapper.class);
		}
	}

	@Override
	public void executeAjax(HttpServletRequest request)
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public class LessonMapper
	{
		private String crs;
		private String lid;
		private String lna;
		private String ldc;
		private List<LearningTask> tks;

		private String name;

		private String theme;

		public String getName()
		{
			return name;
		}

		public void setName(String name)
		{
			this.name = name;
		}

		public int getAge()
		{
			return 0;
		}

		public void setAge(int age)
		{
			//
		}
	}
}
