package com.bueffeltier.data.hibernate.dao;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.NamedQuery;

/**
 *
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 * @param <T>  test
 * @param <ID> test
 */
public interface GenericDao<T, ID extends Serializable>
{

	/**
	 *
	 * @param entity
	 */
	public void save(T entity);

	/**
	 *
	 * @param entity
	 */
	public void merge(T entity);

	/**
	 *
	 * @param entity
	 */
	public void delete(T entity);

	/**
	 *
	 * @param query
	 * @return
	 */
	public List<T> findMany(NamedQuery query);

	/**
	 *
	 * @param query
	 * @return
	 */
	public T findOne(NamedQuery query);

	// todo: methode wurde von mir definiert. gehört die hier rein?
	/**
	 *
	 * @param clazz
	 * @param id
	 * @return
	 */
	public T findById(Class<T> clazz, int id);

	/**
	 *
	 * @param clazz
	 * @param sqlTableName
	 * @return
	 */
	public List<T> findAll(Class<T> clazz, String sqlTableName);
	// todo: Mistake 6: Perform all Logic in Your Business Code
	// imlementierende methode sollte Query als Parameter übergeben:
//Query q = em.createQuery("SELECT a, size(a.books) FROM Author a GROUP BY a.id");
//List<Object[]> results = q.getResultList();
//
//oder datenbankspezifisch über JPA:
//
//TypedQuery<Book> q = em.createQuery(
//             "SELECT b FROM Book b WHERE b.id = function('calculate', 1, 2)",
//             Book.class);
//Book b = q.getSingleResult();

	// https://thoughts-on-java.org/common-hibernate-mistakes-cripple-performance/
}
