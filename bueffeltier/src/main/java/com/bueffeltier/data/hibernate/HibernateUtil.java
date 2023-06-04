package com.bueffeltier.data.hibernate;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class HibernateUtil
{
	private static HibernateUtil instance;

	private static EntityManagerFactory entityManagerFactory;

	private HibernateUtil()
	{
		// Privater Konstruktor, um die Instanziierung zu verhindern
	}

	public static HibernateUtil getInstance()
	{
		if (instance == null)
		{
			instance = new HibernateUtil();
		}

		return instance;
	}

	public static EntityManager getEntityManager()
	{
		if (entityManagerFactory == null)
		{
			entityManagerFactory = Persistence
			    .createEntityManagerFactory("persistence");
		}

		return entityManagerFactory.createEntityManager();
	}

	public static void closeEntityManagerFactory()
	{
		if (entityManagerFactory != null && entityManagerFactory.isOpen())
		{
			entityManagerFactory.close();
		}
	}
}
