package com.bueffeltier.data.hibernate;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class Criteria<T>
{
	private final Class<T> entityClass;
	private final EntityManager entityManager;
	private final CriteriaBuilder criteriaBuilder;
	private final CriteriaQuery<T> criteriaQuery;
	private final Root<T> root;

	public Criteria(Class<T> entityClass, EntityManager entityManager)
	{
		this.entityClass = entityClass;
		this.entityManager = entityManager;
		this.criteriaBuilder = entityManager.getCriteriaBuilder();
		this.criteriaQuery = criteriaBuilder.createQuery(entityClass);
		this.root = criteriaQuery.from(entityClass);
	}

	// Fügen Sie hier die gewünschten Filter- und Suchmethoden hinzu, z.B.:

	public Criteria<T> addEqual(String attributeName, Object value)
	{
		criteriaQuery
		    .where(criteriaBuilder.equal(root.get(attributeName), value));
		return this;
	}

	public Criteria<T> addLike(String attributeName, String value)
	{
		criteriaQuery
		    .where(criteriaBuilder.like(root.get(attributeName), value));
		return this;
	}

	public List<T> getResultList()
	{
		return entityManager.createQuery(criteriaQuery).getResultList();
	}

	public T getSingleResult()
	{
		return entityManager.createQuery(criteriaQuery).getSingleResult();
	}
}
