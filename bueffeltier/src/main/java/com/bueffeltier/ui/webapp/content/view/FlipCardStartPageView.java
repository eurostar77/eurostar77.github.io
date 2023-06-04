package com.bueffeltier.ui.webapp.content.view;

import static j2html.TagCreator.*;

import javax.servlet.http.HttpServletRequest;

import com.bueffeltier.data.hibernate.HibernateUtil;
import com.bueffeltier.data.jdbc.ElementJDBCFlat;
import com.bueffeltier.logic.domain.Lesson;
import com.bueffeltier.ui.html.molecule.SpacingPropertyDV;
import com.bueffeltier.ui.html.molecule.SpacingSidesDV;
import com.bueffeltier.ui.html.molecule.SpacingSizeDV;
import com.bueffeltier.ui.html.organism.ButtonBuilder;
import com.bueffeltier.ui.html.organism.ButtonBuilder.ColorDV;

import j2html.tags.DomContent;
import jakarta.persistence.EntityManager;

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
//		Session session = OldHibernateUtil.getSessionFactory().openSession();
//		session.beginTransaction();
//
//		Lesson lesson = new Lesson();
//		lesson.setName("Lesson 7");
//
//		session.persist(lesson);
//
//		session.getTransaction().commit();
//		OldHibernateUtil.shutdown();

		/*
		 *******************************************************************************
		 */

		HibernateUtil.getInstance();
		EntityManager em = HibernateUtil.getEntityManager();
		em.getTransaction().begin();
		Lesson lesson = new Lesson();
		lesson.setName("Lesson 8");
		em.persist(lesson);
		em.flush();
		em.getTransaction().commit();
		em.close();

//		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

		// Erstellen Sie ein CriteriaQuery-Objekt mit dem gewünschten
		// Ergebnistyp
//		CriteriaQuery<Lesson> criteriaQuery = criteriaBuilder
//		    .createQuery(Lesson.class);

		// Definieren Sie die Root-Tabelle für die Abfrage
//		Root<Lesson> root = criteriaQuery.from(Lesson.class);

		// Fügen Sie die gewünschten Abfragekriterien hinzu
//		criteriaQuery.select(root)
//		    .where(criteriaBuilder.equal(root.get("name"), "lesson4"));

		// Führen Sie die Abfrage über den EntityManager aus
//		List<Lesson> resultList = em.createQuery(criteriaQuery).getResultList();

		// Beenden Sie die Transaktion
//		em.getTransaction().commit();

		// Schließen Sie den EntityManager
//		em.close();

		/*
		 *******************************************************************************
		 */

//		Session session = OldHibernateUtil.getSessionFactory().openSession();
//		EntityManager entityManager = session.getEntityManagerFactory()
//		    .createEntityManager();
//
//		Lesson lesson = new Lesson();
//		lesson.setName("test");
//		EntityManagerFactory entityManagerFactory = Persistence
//		    .createEntityManagerFactory("persistence");
//		EntityManager entityManager = entityManagerFactory
//		    .createEntityManager();
//		entityManager.getTransaction().begin();
//		entityManager.persist(lesson);
//		entityManager.getTransaction().commit();
//		entityManager.close();
//		entityManagerFactory.close();

//		entityManager.close();
//		session.close();

//		// Konfiguration erstellen
//		org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
//		// Füge die Konfigurationseinstellungen hinzu
//		// ...
//
//		// Erstelle den StandardServiceRegistry
//		StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder()
//		    .applySettings(configuration.getProperties()).build();
//
//		// Erstelle das MetadataSources-Objekt
//		MetadataSources metadataSources = new MetadataSources(
//		    standardServiceRegistry
//		);
//
//		// Füge die Klasse/Package hinzu, die deine Entities enthält
//		metadataSources.addAnnotatedClass(Lesson.class);
//		// ...
//
//		// Erstelle die SessionFactory
//		SessionFactory sessionFactory = metadataSources.buildMetadata()
//		    .buildSessionFactory();
//
//		// Erstelle die EntityManagerFactory
//		EntityManagerFactory entityManagerFactory = Persistence
//		    .createEntityManagerFactory("yourPersistenceUnitName");
//
//		// Verwende die EntityManagerFactory
//		EntityManager em = entityManagerFactory.createEntityManager();
//
//		em.getTransaction().begin();
//		em.persist(lesson);
//		em.getTransaction().commit();
//		em.close();
//		entityManagerFactory.close();

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
