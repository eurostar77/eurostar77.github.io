package com.bueffeltier.ui.webapp.content.view;

import static j2html.TagCreator.*;

import javax.servlet.http.HttpServletRequest;

import com.bueffeltier.data.jdbc.ElementJDBCFlat;
import com.bueffeltier.ui.html.molecule.SpacingPropertyDV;
import com.bueffeltier.ui.html.molecule.SpacingSidesDV;
import com.bueffeltier.ui.html.molecule.SpacingSizeDV;
import com.bueffeltier.ui.html.organism.ButtonBuilder;
import com.bueffeltier.ui.html.organism.ButtonBuilder.ColorDV;

import j2html.tags.DomContent;

/**
 *
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
public class FlipCardStartPageView extends AbstractView
{

	private static FlipCardStartPageView instance;

	private FlipCardStartPageView()
	{
		super();
	}

	public static FlipCardStartPageView getInstance()
	{
		if (instance == null)
		{
			instance = new FlipCardStartPageView();
		}
		return instance;
	}

	@Override
	public DomContent
	    writeHtml(ElementJDBCFlat element, HttpServletRequest request)
	        throws Exception
	{
//		Session session = HibernateUtil.getSessionFactory().openSession();
//		session.beginTransaction();

//		// Add new Employee object
//		Lesson lesson1 = new Lesson();
//		lesson1.setName("Lesson 1");
//
//		Lesson lesson2 = new Lesson();
//		lesson2.setName("Lesson 2");
//
//		Lesson lesson3 = new Lesson();
//		lesson3.setName("Lesson 3");
//
//		Lesson lesson4 = new Lesson();
//		lesson4.setName("Lesson 4");
//
//		Lesson lesson5 = new Lesson();
//		lesson5.setName("Lesson 5");

//		session.persist(lesson1);
//		session.persist(lesson2);
//		session.persist(lesson3);
//		session.persist(lesson4);
//		session.persist(lesson5);

//		session.getTransaction().commit();
//		HibernateUtil.shutdown();

		return div(
		    form(
		        element.getType(),

		        ButtonBuilder.create()//
		            .withText("Lernkarten-Lektion üben")//
		            .withHref("/bueffeltier/lernkarten")//
		            .withColor(ColorDV.SECONDARY)//
		            .withSpacing(
		                SpacingPropertyDV.MARGIN, SpacingSidesDV.BLANK,
		                SpacingSizeDV.ONE
		            )//
		            .build(),
		        ButtonBuilder.create()//
		            .withText("Kartenstapel wechseln")//
		            .withHref("/bueffeltier/subject-editor")//
		            .withColor(ColorDV.SECONDARY)//
		            .withSpacing(
		                SpacingPropertyDV.MARGIN, SpacingSidesDV.BLANK,
		                SpacingSizeDV.ONE
		            )//
		            .build(),
		        h1("Kartenstapel verwalten").withClass("mt-4"), div(each())
		    )//
//		    .withClass("container")
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
