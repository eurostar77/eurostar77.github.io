package com.bueffeltier.ui.webapp.content.view;

import static j2html.TagCreator.*;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.bueffeltier.data.hibernate.Criteria;
import com.bueffeltier.data.hibernate.dao.GenericDAO;
import com.bueffeltier.data.jdbc.ElementJDBCFlat;
import com.bueffeltier.data.jdbc.UserJDBC;
import com.bueffeltier.logic.domain.LearningLevel;
import com.bueffeltier.logic.domain.LearningTask;
import com.bueffeltier.logic.domain.Lesson;
import com.bueffeltier.ui.html.molecule.SpacingPropertyDV;
import com.bueffeltier.ui.html.molecule.SpacingSidesDV;
import com.bueffeltier.ui.html.molecule.SpacingSizeDV;
import com.bueffeltier.ui.html.organism.Accordion;
import com.bueffeltier.ui.html.organism.ButtonBuilder;
import com.bueffeltier.ui.html.organism.ButtonBuilder.ButtonTypeDV;
import com.bueffeltier.ui.html.organism.ButtonBuilder.ColorDV;
import com.bueffeltier.ui.html.organism.ColumnBuilder;
import com.bueffeltier.ui.html.organism.ColumnBuilder.BreakpointDV;
import com.bueffeltier.ui.html.organism.ColumnBuilder.GridWidthDV;
import com.bueffeltier.ui.html.organism.FormControlBuilder;
import com.bueffeltier.ui.html.organism.FormControlBuilder.FormInputTypeDV;
import com.bueffeltier.ui.html.organism.LabelBuilder;
import com.bueffeltier.ui.html.organism.RowBuilder;
import com.bueffeltier.ui.html.organism.RowBuilder.RowAlignmentDV;
import com.bueffeltier.ui.webapp.ViewDataService;

import j2html.tags.DomContent;

/**
 *
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
public class LessonEditorView extends AbstractView
{

	private static LessonEditorView instance;

	private ViewDataService viewDataService = ViewDataService.getInstance();

	private LessonEditorView()
	{
		super();
	}

	public static LessonEditorView getInstance()
	{
		if (instance == null)
		{
			instance = new LessonEditorView();
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
		 */

		// - ich komme von /lernkarten (Aktueller Kartenstapel),
		// dann klicke ich auf Kartenstapel bearbeiten
		// ich muss aus dem lernstand die aktuelle lesson auslesen
		// und in der ...Action d
		// und in der view den richtigen stapel aus der

		// bei kartenstapel wechsel muss das im lernstand vermerkt werden.

		String headline = null;

		Lesson lesson = null;
		String lessonName = null;
		String lessonTheme = null;

		List<LearningTask> learningTasks = null;

		boolean isNewLesson;
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		// JS security tipps beachten
		// hinweise aus cookie sevice hierher kopieren
		// prüfen von welcher action an welche view gesendet wurde.
		// ggf. abbruch
		// code in abstraktion und service auslager, automatik bauen.
		// ggf. erst beim umbau

		// TODO sveng 25.06.2023: in die abstrakte klasse;
		// TODO sveng 25.06.2023: prüfen auf herkunnft und empfänger
		// TODO sveng 25.06.2023: prüfen auf not Null
		// TODO sveng 25.06.2023: gefragte werte direkt aus der abstraktion
		// abfragen boolean.get("isNew") // nach schema auf vollst.
		// prüfen.(meth)
		// daten auch nach key abfragen!

		String isNewLessonString = getViewDataValueOpt(
		    request, "createNewLesson"
		);

		if (isNewLessonString != null)
		{
			isNewLesson = Boolean.parseBoolean(isNewLessonString);

		} else
		{
			isNewLesson = false;
		}

		UserJDBC user = (UserJDBC) request.getAttribute("user");

		if (isNewLesson)
		{
			headline = "Neuen Kartenstapel anlegen";
			lesson = new Lesson(); // TODO sveng 09.06.2023: lesson erst beim
			                       // speichern anlegen?
			lessonName = "";
			lessonTheme = "";
			learningTasks = new ArrayList<LearningTask>();
			learningTasks.add(new LearningTask());

		} else
		{
			Criteria<LearningLevel> criteriaLearningLevel = GenericDAO
			    .createCriteria(LearningLevel.class);

			List<LearningLevel> learningLevelList = criteriaLearningLevel
			    .addEqual("userId", Long.valueOf(user.getId())).getResultList();

			headline = "Kartenstapel bearbeiten";

			Long lessonId = learningLevelList.get(0).getCurrentLessonId();

			Criteria<Lesson> criteriaLesson = GenericDAO
			    .createCriteria(Lesson.class);

			List<Lesson> lessonList = criteriaLesson.addEqual("id", lessonId)
			    .getResultList();

			lesson = lessonList.get(0);
			lessonName = lesson.getName();
			lessonTheme = lesson.getTheme();
			learningTasks = lesson.getLearningTasks();
		}

//		request.getAttribute("");
//
//		if (lesson.size() == 0)
//		{
//			InfoBit infoBit = new InfoBit();
//			// TODO sveng 03.03.2023: default werte
//		}

		return

		div(
		    div(
		        form(
		            element.getType(),

		            ButtonBuilder.create()//
		                .withText("Kartenstapel lernen")//
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

		            h1(headline).withClass("my-5"),

		            RowBuilder.create()//
		                .withDomContent(

		                    ColumnBuilder.create()//
		                        .withDomContent(
		                            LabelBuilder.create()//
		                                .build("Name des Stapels", "inputName")
									//
		                        )//
		                        .withGridWidth(GridWidthDV.THREE)//
		                        .withBreakpoint(BreakpointDV.MD).build(),

		                    ColumnBuilder.create()//
		                        .withDomContent(
		                            FormControlBuilder.create()//
		                                .withValue(lessonName)//
		                                .withName("inputName")//
		                                .withId("inputName")//
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
		                                    "Thema des Stapels", "inputTheme"
		                                )
		                        )//
		                        .withGridWidth(GridWidthDV.THREE)//
		                        .withBreakpoint(BreakpointDV.MD).build(),
		                    ColumnBuilder.create()//
		                        .withDomContent(
		                            FormControlBuilder.create()//
		                                .withText(lessonTheme)//
		                                .withValue(lessonTheme)//
		                                .withName("inputTheme")//
		                                .withType(FormInputTypeDV.TEXTAREA)//
		                                .withRows(2)//
		                                .withId("inputTheme")//

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
		                                .withClass("addCardBtn")//
		                                .withButtonType(ButtonTypeDV.BUTTON)//
//		                                .withOncLick(null)//
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

		            div(
		                each(
		                    learningTasks,
		                    (index, learningTask) -> writeLearningTaskEntry(
		                        learningTask, index + 1
		                    )
		                )
		            ).withId("cards"), br(), //

		            /*
		             * Untere Buttons nur nach if anzeigen.
		             */

		            ButtonBuilder.create()//
		                .withButtonType(ButtonTypeDV.BUTTON)//
		                .withText("Neue Karte erstellen")//
		                .withColor(ColorDV.PRIMARY)//
		                .withClass("addCardBtn")//
//                    .withOncLick(null)//
		                .build(), //
		            br(), //

		            ButtonBuilder.create()//
		                .withButtonType(ButtonTypeDV.SUBMIT)//
		                .withText("Kartenstapel speichern")//
		                .withId("btnSave")//
		                .withColor(ColorDV.PRIMARY)//
		                .withSpacing(
		                    SpacingPropertyDV.MARGIN, SpacingSidesDV.BLANK,
		                    SpacingSizeDV.TWO
		                )//
		                .build(), //

		            div().withClass("autosaveMessage")

//		            ButtonBuilder.create()//
//		                .withColor(ColorDV.SECONDARY)//
//		                .withText("Abbrechen")//
//		                .withSpacing(
//		                    SpacingPropertyDV.MARGIN, SpacingSidesDV.BLANK,
//		                    SpacingSizeDV.TWO
//		                )//
//		                .withId("")//
//		                .withInputType(ButtonInputTypeDV.BUTTON)//
//		                .build() //
		        )//

//		        .withClass("card-body")
		    )
		)//
		    .withClass("container")//
		    .withData("autosave-url", "http://localhost:8080/")//
		    .withData("action", "CREATE_FLASHCARDS")//
		;//
//		.withClass("card");
	}

	private DomContent writeLearningTaskEntry(LearningTask task, int taskCount)
	{
		Accordion accordion = new Accordion("task" + taskCount);

		String question = task.getName();
		if (StringUtils.isBlank(question))
		{
			question = "";
		}

		return div(

		    accordion.buildAccordionContainer(

		        accordion.buildAccordionItem(

		            accordion.buildHeaderH2("Aufgabe " + taskCount),

		            accordion.buildBody(

		                // h3("Aufgabe " + taskCount).withClass("card-header")
//		                    .withId("ch" + taskCount),

		                div(
		                    div(
		                        h4("Frage").withClass("card-header"),
		                        div(
		                            textarea(question)//
		                                .withClass("form-control")
		                                .attr("rows", "2")
		                                .withId("q" + taskCount)
		                                .attr("name", "q" + task.getName())
		                        ).withClass("card-body")
		                    ).withClass("g-col-4"),
		                    div(
		                        h4("Antwort").withClass("card-header"), //
		                        div(
		                            // textarea(task.getQuestion()).withClass("form-control")
		                            textarea("").//
		                                withClass("form-control")
		                                .attr("rows", "2")
		                                .withId("a" + taskCount)
//		                    .attr("name", "a" + task.getInfoBitId())
		                                .attr("name", "a" + "ID")
		                        ).withClass("card-body")
		                    ).withClass(".g-col-4")
		                ).withClass("grid").attr("class", "grid"),

//		    ButtonBuilder.create().withText("Bearbeiten").build(),

		                RowBuilder.create()//
		                    .withAlignment(RowAlignmentDV.END)
		                    .withDomContent(
		                        ColumnBuilder.create()//
		                            .withGridWidth(GridWidthDV.AUTO)
		                            .withDomContent(
		                                ButtonBuilder.create()//
		                                    .withColor(ColorDV.DANGER)//
		                                    .withClass("btnDelete")//
		                                    .withName("delete")//
		                                    .withText("Löschen")//
		                                    .build()
		                            )//
		                            .build()
		                    )//
		                    .withDomContent(
		                        ColumnBuilder.create()//
		                            .withGridWidth(GridWidthDV.AUTO)
		                            .withDomContent(
		                                ButtonBuilder.create()//
		                                    .withColor(ColorDV.WARNING)//
		                                    .withClass("btnRemove")//
		                                    .withName("remove")//
		                                    .withText("Entfernen")//
		                                    .build()
		                            )//
		                            .build()
		                    )//
		                    .build()
		            )
		        )
		    )
		).withClass("card my-5 bg-light").withId("card" + taskCount);
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
