package com.bueffeltier.data.hibernate.dao;

import com.bueffeltier.data.hibernate.Criteria;
import com.bueffeltier.data.hibernate.HibernateUtil;

import jakarta.persistence.EntityManager;

public class GenericDAO<T>
{
	private GenericDAO()
	{
		// Private constructor to prevent instantiation
	}

	public static <T> T create(T t)
	{
		EntityManager entityManager = HibernateUtil.getEntityManager();

		entityManager.getTransaction().begin();
		entityManager.persist(t);
		entityManager.flush();
		entityManager.getTransaction().commit();
		entityManager.close();

		return t;
	}

	public static <T> T read(Class<T> classType, Long id)
	{
		EntityManager entityManager = HibernateUtil.getEntityManager();

		T t = entityManager.find(classType, id);

		if (t == null)
		{
			entityManager.close();
			return null;
		} else
		{
			entityManager.refresh(t);
			entityManager.close();
			return t;
		}
	}

	public static <T> T update(T t)
	{
		EntityManager entityManager = HibernateUtil.getEntityManager();

		entityManager.getTransaction().begin();
		entityManager.merge(t);
		entityManager.getTransaction().commit();
		entityManager.close();

		return t;
	}

	public static <T> boolean delete(Class<T> classType, Long id)
	{
		EntityManager entityManager = HibernateUtil.getEntityManager();

		entityManager.getTransaction().begin();

		T t = entityManager.getReference(classType, id);

		if (t == null)
		{
			// exception
			return false;
		}

		entityManager.remove(t);
		entityManager.getTransaction().commit();
		entityManager.close();
		return true;
	}

	public static <T> Criteria<T> createCriteria(Class<T> entityClass)
	{
		EntityManager entityManager = HibernateUtil.getEntityManager();

		return new Criteria<T>(entityClass, entityManager);
	}
}