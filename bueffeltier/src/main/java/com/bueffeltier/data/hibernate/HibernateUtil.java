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
		createEntityManagerFactory();
	}

	public static HibernateUtil getInstance()
	{
		if (instance == null)
		{
			instance = new HibernateUtil();
		}

		return instance;
	}

	private static void createEntityManagerFactory()
	{
		if (entityManagerFactory == null)
		{
			entityManagerFactory = Persistence
			    .createEntityManagerFactory("persistence");
		}
	}

	public static EntityManager getEntityManager()
	{
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
