package com.bueffeltier.data.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 *
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
public class OldHibernateUtil
{

	private static final StandardServiceRegistry REGISTRY;
	private static final SessionFactory SESSION_FACTORY;

	static
	{
//        try {
		// 1. Build the StandardServiceRegistry
		REGISTRY = new StandardServiceRegistryBuilder()
		    // //When hibernate.config is loaded from outside the package:
		    // File f = new
		    // File("C:\eclipse-workspace\bueffeltier\src\main\resources\\hibernate.cfg.xml");
		    // .configure(f) //configures settings from configurationFile
		    .configure() // configures settings from hibernate.cfg.xml

//		    .applySetting(
//		        "hibernate.connection.username",
//		        AppPropertyService.getDbUsername()
//		    )
//		    .applySetting(
//		        "hibernate.connection.password",
//		        AppPropertyService.getDbPassword()
//		    )
//		    .applySetting(
//		        "hibernate.connection.url", AppPropertyService.getDbUrl()
//		    )
		    .build();

		// 2. Build the MetadataSources
		MetadataSources sources = new MetadataSources(REGISTRY);

		// Create Metadata
		Metadata metadata = sources.getMetadataBuilder().build();

		// 3. Use these two to build the SessionFactory
		SESSION_FACTORY = metadata.getSessionFactoryBuilder().build();

//        } catch (Exception e) {
//            if (null != REGISTRY) {
//                StandardServiceRegistryBuilder.destroy(REGISTRY);
//            }
//        }
	}

	/**
	 *
	 * @return
	 */
	public static SessionFactory getSessionFactory()
	{
		return SESSION_FACTORY;
	}

	/**
	 *
	 * @return
	 */
	public static Session beginTransaction()
	{
		Session session = OldHibernateUtil.getSession();
		session.beginTransaction();
		return session;
	}

	/**
	 *
	 */
	public static void commitTransaction()
	{
		OldHibernateUtil.getSession().getTransaction().commit();
	}

	/**
	 *
	 */
	public static void rollbackTransaction()
	{
		OldHibernateUtil.getSession().getTransaction().rollback();
	}

	/**
	 *
	 */
	public static void closeSession()
	{
		OldHibernateUtil.getSession().close();
	}

	/**
	 *
	 * @return
	 */
	public static Session getSession()
	{
		Session session = SESSION_FACTORY.openSession(); // Ursprünglicher
		                                                 // COde:
		// ...SESSION_FACTORY.getCurrentSession();
		return session;
	}

	/**
	 *
	 */
	public static void shutdown()
	{
		if (REGISTRY != null)
		{
			StandardServiceRegistryBuilder.destroy(REGISTRY);
		}
	}
}
