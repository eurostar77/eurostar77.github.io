package com.bueffeltier.ui.webapp.content.view;

import static j2html.TagCreator.*;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.bueffeltier.data.hibernate.Criteria;
import com.bueffeltier.data.hibernate.dao.GenericDAO;
import com.bueffeltier.data.jdbc.ElementJDBCFlat;
import com.bueffeltier.data.jdbc.UserJDBC;
import com.bueffeltier.logic.domain.Lesson;
import com.bueffeltier.ui.html.molecule.SpacingPropertyDV;
import com.bueffeltier.ui.html.molecule.SpacingSidesDV;
import com.bueffeltier.ui.html.molecule.SpacingSizeDV;
import com.bueffeltier.ui.html.organism.ButtonBuilder;
import com.bueffeltier.ui.html.organism.ButtonBuilder.ButtonTagTypeDV;
import com.bueffeltier.ui.html.organism.ButtonBuilder.ButtonTypeDV;
import com.bueffeltier.ui.html.organism.ButtonBuilder.ColorDV;
import com.bueffeltier.ui.html.organism.CardBuilder;
import com.bueffeltier.ui.html.organism.CardBuilder.HeadlineDV;

import j2html.tags.DomContent;

/**
 *
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
public class CourseEditorView extends AbstractView
{

	private static CourseEditorView instance;

	private CourseEditorView()
	{
		super();
	}

	public static CourseEditorView getInstance()
	{
		if (instance == null)
		{
			instance = new CourseEditorView();
		}
		return instance;
	}

	@Override
	public DomContent
	    writeHtml(ElementJDBCFlat element, HttpServletRequest request)
	        throws Exception
	{

//		Lesson lesson1 = new Lesson();
//		lesson1.setName("Lesson 1");
//		lesson1.setOwner((UserJDBC) request.getAttribute("user"));
//
//		LearningTask task1 = new LearningTask();
//		task1.setLesson(lesson1);
//		task1.setName("Aufgabe 1");
//		lesson1.setLearningTask(task1);
//
//		LearningTask task2 = new LearningTask();
//		task2.setLesson(lesson1);
//		task2.setName("Aufgabe 1");
//		lesson1.setLearningTask(task2);
//
//		GenericDAO.create(lesson1);
//////////////////////////////////////////////////////////////////////////////////

		Criteria<Lesson> criteria = GenericDAO.createCriteria(Lesson.class);

		UserJDBC user = (UserJDBC) request.getAttribute("user");
		Long userId = Long.valueOf(user.getId());

		ArrayList<Lesson> lessons = new ArrayList<>();

		try
		{
			lessons = (ArrayList<Lesson>) criteria//
			    .addEqual("ownerId", userId)//
			    .getResultList();
		} catch (Exception e)
		{
			boolean istrue = false;
			String string = "";
			string = "";
			// nix
		}

		return div(
		    h1("Alle Kartenstapel").withClass("mb-3"), //
		    form(
		        element.getType(), //
		        input()//
		            .withType("hidden")//
		            .withName("newLesson")//
		            .withValue("newLesson"), //

		        ButtonBuilder.create()//
		            .withText("Neuen Kartenstapel anlegen")//

//		            .withHref("/bueffeltier/lernkarten")//
//		            .withColor(ColorDV.SECONDARY)//
		            .withSpacing(
		                SpacingPropertyDV.MARGIN, SpacingSidesDV.BLANK,
		                SpacingSizeDV.THREE
		            )//
		            .withTagType(ButtonTagTypeDV.BUTTON)//
		            .withButtonType(ButtonTypeDV.SUBMIT)//
//		            .withFormAction("new")//
		            .build()
		    ),
//		        ButtonBuilder.create()//
//		            .withText("Lernkarten-Lektion üben")//
//		            .withHref("/bueffeltier/lernkarten")//
//		            .withColor(ColorDV.SECONDARY)//
//		            .withSpacing(
//		                SpacingPropertyDV.MARGIN, SpacingSidesDV.BLANK,
//		                SpacingSizeDV.ONE
//		            )//
//		            .build(),
//		        ButtonBuilder.create()//
//		            .withText("Kartenstapel wechseln")//
//		            .withHref("/lesson-editor")//
//		            .withColor(ColorDV.SECONDARY)//
//		            .withSpacing(
//		                SpacingPropertyDV.MARGIN, SpacingSidesDV.BLANK,
//		                SpacingSizeDV.ONE
//		            )//
//		            .build(),
		    div(
		        //
		        each(lessons, lesson -> //
				div(getLessonEntry(lesson, user)))
		    )
			//
//		    .withClass("container")
		);
	}

	private DomContent getLessonEntry(Lesson lesson, UserJDBC owner)
	{
		return CardBuilder.create()//
		    .withHeader(lesson.getName()).withDomContent(
		        //
		        div(
		            //
		            p("Author: " + owner.getName()),
		            p("Anzahl Aufgaben: " + "9999"), //
		            ButtonBuilder.create()//
		                .withColor(ColorDV.SECONDARY)//
		                .withText("bearbeiten")//
		                .build(), //
		            ButtonBuilder.create()//
		                .withColor(ColorDV.SECONDARY)//
		                .withText("löschen")//
		                .withName("loeschen")//
		                .withButtonType(ButtonTypeDV.SUBMIT)//
		                .build(), //
		            ButtonBuilder.create()//
		                .withText("auswählen")//
		                .build()
				//
		        )
		    )//
		    .withSpacing(
		        SpacingPropertyDV.MARGIN, SpacingSidesDV.BOTTOM,
		        SpacingSizeDV.THREE
		    ).withTitle(HeadlineDV.H2, lesson.getName())//
		    .build();
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
