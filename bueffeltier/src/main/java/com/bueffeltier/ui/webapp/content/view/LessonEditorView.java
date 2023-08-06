package com.bueffeltier.ui.webapp.content.view;

import static j2html.TagCreator.*;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.bueffeltier.data.hibernate.Criteria;
import com.bueffeltier.data.hibernate.dao.GenericDAO;
import com.bueffeltier.data.jdbc.ElementJDBCFlat;
import com.bueffeltier.logic.domain.LearningTask;
import com.bueffeltier.logic.domain.LearningUnit;
import com.bueffeltier.logic.domain.Lesson;
import com.bueffeltier.ui.html.molecule.SpacingPropertyDV;
import com.bueffeltier.ui.html.molecule.SpacingSidesDV;
import com.bueffeltier.ui.html.molecule.SpacingSizeDV;
import com.bueffeltier.ui.html.organism.ButtonBuilder;
import com.bueffeltier.ui.html.organism.ButtonBuilder.ColorDV;
import com.bueffeltier.ui.html.organism.ColumnBuilder;
import com.bueffeltier.ui.html.organism.ColumnBuilder.BreakpointDV;
import com.bueffeltier.ui.html.organism.ColumnBuilder.GridWidthDV;
import com.bueffeltier.ui.html.organism.DynamicCardBuilder;
import com.bueffeltier.ui.html.organism.FooterNavigationBuilder;
import com.bueffeltier.ui.html.organism.FormBuilder;
import com.bueffeltier.ui.html.organism.FormControlBuilder;
import com.bueffeltier.ui.html.organism.FormControlBuilder.FormInputTypeDV;
import com.bueffeltier.ui.html.organism.LabelBuilder;
import com.bueffeltier.ui.html.organism.RowBuilder;
import com.bueffeltier.ui.html.organism.RowBuilder.RowAlignmentDV;

import j2html.tags.DomContent;
import j2html.tags.specialized.DivTag;
import j2html.tags.specialized.FormTag;

/**
 *
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
public class LessonEditorView extends AbstractView
{
	private static LessonEditorView instance;

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

//	https://www.tutorialspoint.com/online_json_editor.htm
//
//		<input name="crs" ...>
//		<input name="lid" ...>
//		<input name="lna" ...>
//		<input name="ldc" ...>
//
//		<input name="tks[].tid" ...>
//		<input name="tks[].lus[].lui" ...>
//		<input name="tks[].lus[].kut.kui" ...>
//		<input name="tks[].lus[].kut.ktx" ...>
//		<input name="tks[].lus[].qtn.qni" ...>
//		<input name="tks[].lus[].qtn.qtx" ...>
//
//		{
//		    "crs": "user.getUuid()",					crs	data
//		    "lid": "lesson.getUuid()",					lid	data
//		    "lna": "lesson.getName()",					lna	data
//		    "ldc": "lesson.getTheme()",					ldc	data
//		    "tks": [									
//		        {									
//		            "tid": "task.getUuid()",				tsk[].tid		hidden
//		            "lus": [								
//		                {
//		                    "lui": "unit.getUuid()",			tsk[].lut[].lui		hidden
//		                    "kut": {
//		                        "kui": "knowledge.getUuid()",		tsk[].lut[].kut.kui	hidden
//		                        "ktx": "knowledge.getText()"		tsk[].lut[].kut.ktx	visible
//		                    },
//		                    "qtn": {							
//		                        "qni": "question.getUuid()",		tsk[].lut[].qtn.qni	hidden
//		                        "qtx": "question.getText()"		tsk[].lut[].qtn.qtx	visible
//		                    }
//		                }
//		            ]
//		        }
//		    ]
//		}
//
//	getLearningTaskUuidInputHidden(LearningTask task)
//	getLearningUnitUuidInputHidden(LearningUnit unit)
//	getQuestionUuidInputHidden(Question question)
//	getQuestionTextInputVisible(Question question)
//	getKnowledgeUuidInputHidden(Knowledge knowledge)
//	getKnowledgeTextInputVisble(Knowledge knowledge)

	@Override
	public DomContent
	    writeHtml(ElementJDBCFlat element, HttpServletRequest request)
	        throws Exception
	{
		Lesson lesson = getLesson(request);

		String dataFormId = "lesson-form-data";
		String dataAutoSaveUrl = "http://localhost:8080/";
		String dataAction = "CREATE_FLASHCARDS";

		return div()//
		    .with(buildElementHeaderNav().withClass("mb-4"))//
		    .with(buildHeadlineDiv(lesson).withClass("mb-4"))//
		    .with(buildFormTag(lesson, element, dataFormId))//
		    .with(getFooterNavigation(dataFormId));
	}

	private Lesson getLesson(HttpServletRequest request) throws Exception
	{
		/*
		 * 1. Je nach Permission-Status: Musterstapel laden Letzten Stapel laden
		 * Sinnvollen Stapel laden 3. Stapel werden gespeichert. 4. Lernschicht
		 * überwacht Stapelgröße (Anzahl), 5.
		 */

		Lesson lesson = null;

		String lessonUuid = getViewDataValueOpt(request, "lessonEditor");
		if (lessonUuid == null)
		{
			lessonUuid = "new";
		}

		if (lessonUuid.equals("new"))
		{
			lesson = new Lesson();
			lesson.setName("");
			lesson.setTheme("");
			lesson.setUuid("new");

		} else
		{
			try
			{
				Criteria<Lesson> criteriaLesson = GenericDAO
				    .createCriteria(Lesson.class);

				List<Lesson> lessonList = criteriaLesson
				    .addEqual("uuid", lessonUuid).getResultList();

			} catch (Exception e)
			{
				throw new Exception(
				    "View konnte nicht erzeugt werden, da Lesson nicht gefunden wurde."
				);
			}
		}

		return lesson;
	}

	private String getHeadline(boolean isNew)
	{
		String headline;

		if (isNew)
		{
			headline = "Neuen Kartenstapel anlegen";

		} else
		{

			headline = "Kartenstapel bearbeiten";
		}

		return headline;
	}

	private FormTag
	    buildFormTag(Lesson lesson, ElementJDBCFlat element, String formId)
	{
		return FormBuilder.create()//
		    .withDomContent(
		        //
		        buildFormContent(lesson)
		    )//
//		    .withData("crs", lesson.getCourse().getUuid())// User bis Kurse aktiv
		    .withData("crs", Long.toString(lesson.getOwnerId()))//
		    .withData("lid", lesson.getUuid())//
		    .withId(formId)//
		    .build(element.getType());
	}

	private DivTag buildHeadlineDiv(Lesson lesson)
	{
		return div(
		    //
		    h1(getHeadline(lesson.getId() == null)).withClass("h1")
		);
	}

	private DivTag buildElementHeaderNav()
	{
		return div(

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
		        .build()
		);
	}

	private DivTag buildFormContent(Lesson lesson)
	{
		return div(

		    lessonDataContainer(lesson),

		    getLearningTaskContainer(lesson),

		    div().withClass("autosaveMessage")

		)//
		    .withData("autosave-url", "http://localhost:8080/")//
		    .withData("action", "CREATE_FLASHCARDS");
	}

	private DomContent getFooterNavigation(String saveContainerId)
	{
		return FooterNavigationBuilder.create()//
		    .withSaveAndCloseOption()//
		    .withSaveOption(saveContainerId)//
		    .withAbortOption()//
		    .withAlignment(RowAlignmentDV.END)//
		    .build();
	}

	private DivTag lessonDataContainer(Lesson lesson)
	{
		return div(

		    RowBuilder.create()//
		        .withDomContent(

		            ColumnBuilder.create()//
		                .withDomContent(
		                    LabelBuilder.create()//
		                        .build("Name des Stapels", "txt-lsn-name")
							//
		                )//
		                .withGridWidth(GridWidthDV.THREE)//
		                .withBreakpoint(BreakpointDV.MD)//
		                .build(),

		            ColumnBuilder.create()//
		                .withDomContent(
		                    FormControlBuilder.create()//
		                        .withValue(lesson.getName())//
		                        .withName("lna")//
		                        .withId("txt-lsn-name")//
		                        .build()//

							//
		                )//
		                .withGridWidth(GridWidthDV.NINE)//
		                .withBreakpoint(BreakpointDV.MD)//
		                .build()

					//
		        )//
		        .build(),

		    RowBuilder.create()//
		        .withDomContent(
		            ColumnBuilder.create()//
		                .withDomContent(
		                    LabelBuilder.create()//
		                        .build("Thema des Stapels", "inputTheme")
		                )//
		                .withGridWidth(GridWidthDV.THREE)//
		                .withBreakpoint(BreakpointDV.MD).build(),
		            ColumnBuilder.create()//
		                .withDomContent(
		                    FormControlBuilder.create()//
		                        .withText(lesson.getTheme())//
		                        .withName("ldc")//
		                        .withId("inputTheme")//
		                        .withType(FormInputTypeDV.TEXTAREA)//
		                        .withRows(2)//
		                        .withSpacing(
		                            SpacingPropertyDV.MARGIN,
		                            SpacingSidesDV.BOTTOM, SpacingSizeDV.FOUR
		                        )//
		                        .build()//
		                )//
		                .withGridWidth(GridWidthDV.NINE)//
		                .withBreakpoint(BreakpointDV.MD).build()
		        )//
		        .build()

		);
	}

	private DomContent getLearningTaskContainer(Lesson lesson)
	{
		return DynamicCardBuilder.create()//
		    .withHeadline("Aufgabe")//
		    .withPrototypeCard(
		        //
		        writeLearningTaskEntry(null)
		    )//
		    .withCards(
		        //
		        writeLearningTaskEntries(lesson.getLearningTasks(), null)
		    )//
		    .withAddBtnText("Hinzufügen")//
		    .withMaxLength(7)//
		    .expandAll()//
		    .build("lessonCards").withId("cards");
	}

	private List<DivTag> writeLearningTaskEntries(
	    List<LearningTask> learningTasks,
	    LearningTask task
	)
	{
		List<DivTag> learningTaskEntries = new ArrayList<>();

		for (LearningTask t : learningTasks)
		{
			learningTaskEntries.add(writeLearningTaskEntry(t));
		}

		return learningTaskEntries;
	}

	private DivTag writeLearningTaskEntry(LearningTask task)
	{
		return //
		div(
		    div(
		        getLearningTaskUuidInputHidden(task),

		        getLearningUnitEntries(task)
		    )//
		        .withClass("grid")//
		        .attr("class", "grid")
		)//
		    .withClass("card my-1 bg-light dynamic-id");
	}

	private DomContent getLearningUnitEntries(LearningTask task)
	{
		List<LearningUnit> units = null;

		if (task != null)
		{
			units = task.getLearningUnits();
		}

		if (units == null)
		{
			units = new ArrayList<>();
			units.add(new LearningUnit());
		}

		return div(

		    each(units, unit ->

			div(
			    getLearningUnitUuidInputHidden(unit),

			    getQuestionEntry(unit),

			    getKnowledgeEntry(unit)
			))
		);
	}

	private DomContent getQuestionEntry(LearningUnit lu)
	{
		boolean hasQuestionUuid = lu != null && lu.hasQuestion();
		boolean hasQuestionString = lu != null && lu.hasQuestion();

		String questionUuid = hasQuestionUuid ? lu.getQuestion().getUuid() : "";
		String qString = hasQuestionString ? lu.getQuestion().getText() : "";

		return div(

		    h4("Frage").withClass("card-header"), //

		    getQuestionUuidInputHidden(questionUuid),

		    getQuestionTextInputVisible(qString)

		).withClass("g-col-4");
	}

	private DomContent getKnowledgeEntry(LearningUnit lu)
	{
		boolean hasKnowledgeUuid = lu != null && lu.hasKnowledge();
		boolean hasKnowledgeString = lu != null && lu.hasKnowledge();

		String kUuid = hasKnowledgeUuid ? lu.getKnowledge().getUuid() : "";
		String kString = hasKnowledgeString ? lu.getKnowledge().getText() : "";

		return div(

		    h4("Antwort").withClass("card-header"), //

		    getKnowledgeUuidInputHidden(kUuid),

		    getKnowledgeTextInputVisble(kString)

		).withClass(".g-col-4");
	}

	private DomContent getLearningTaskUuidInputHidden(LearningTask task)
	{
		String taskUuid = task != null ? task.getUuid() : "new";

		return input()//
		    .withName("tsk[].tid")//
		    .withValue(taskUuid)//
		    .isHidden();
	}

	private DomContent getLearningUnitUuidInputHidden(LearningUnit unit)
	{
		String unitUuid = unit != null ? unit.getUuid() : "new";

		return input()//
		    .withName("tsk[].lut[].lui")//
		    .withValue(unitUuid)//
		    .isHidden();
	}

	private DomContent getQuestionUuidInputHidden(String uuid)
	{
		return input()//
		    .withName("tsk[].lut[].qtn[].qni")//
		    .withValue(uuid)//
		    .isHidden();
	}

	private DomContent getQuestionTextInputVisible(String questionString)
	{
		return div(
		    textarea(questionString)//
		        .withName("tsk[].lut[].qtn[].qtx")//
		        .withClass("form-control dynamic-id")//
		        .attr("rows", "2")//
		).withClass("card-body");
	}

	private DomContent getKnowledgeUuidInputHidden(String uuid)
	{
		return input()//
		    .withName("tsk[].lut[].kut[].kui")//
		    .withValue(uuid)//
		    .isHidden();
	}

	private DomContent getKnowledgeTextInputVisble(String knowledgeString)
	{
		return div(
		    textarea(knowledgeString)//
		        .withName("tsk[].lut[].kut[].ktx")//
		        .withClass("form-control dynamic-id")//
		        .attr("rows", "2")//
		).withClass("card-body");
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
