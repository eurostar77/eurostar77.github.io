package com.bueffeltier.ui.webapp.content.pageaction;

import javax.servlet.http.HttpServletRequest;

import com.bueffeltier.data.hibernate.dao.GenericDAO;
import com.bueffeltier.data.jdbc.UserJDBC;
import com.bueffeltier.logic.domain.LearningLevel;
import com.bueffeltier.ui.webapp.content.action.AbstractAction;

public class LessonPageAction extends AbstractAction
{

	@Override
	public void execute(HttpServletRequest request)
	{
		// Ermitteln, ob user schon lesson und karten hat.
		// 1. wenn current_lesson hinterlegt ist,
		// und current lesson aufgaben hat, dann wird angezeigt:
		// a) die current lesson
		// b) und die aktuelle/nicht beantwortete aufgabe der lesson
		// = lernkarten (umbenennen: lesson, learning, learningdesk)
		// 2. wenn current lesson hinterlegt ist und keine karten hat, dann
		// muss lernkarten bearbeiten angezeigt werden ???
		// = lesson-editor
		// 3. wenn keine current lesson hinterlegt ist, dann
		// kartenstapel verwalten wählen
		// = subject-admin

		UserJDBC user = (UserJDBC) request.getAttribute("user");
//				Long userId = Long.valueOf(user.getId());

		LearningLevel learningLevel = GenericDAO
		    .read(LearningLevel.class, user.getId());

		Long currentLessonId = learningLevel.getCurrentLessonId();

		if (currentLessonId == null)
		{
			forwardToPage(request, "/subject-admin");

		} else
		{
			forwardToPage(request, "/learning");
		}

	}

	@Override
	public void executeAjax(HttpServletRequest request)
	{
		// TODO Auto-generated method stub

	}

}
