package com.bueffeltier.data.hibernate.daoImpl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.bueffeltier.data.hibernate.OldHibernateUtil;
import com.bueffeltier.data.hibernate.dao.GenericDao;

import jakarta.persistence.Query;

/**
 *
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 * @param <T>  x
 * @param <ID> x
 */
public abstract class GenericDaoImpl<T, ID extends Serializable>
    implements GenericDao<T, ID>
{

	// todo: hibernateUtil als feld der klasse genericDaoImpl einfügen, damit
	// rollback, etc. möglich werden.
	// alle methoden, die session erfordern mit "optional" statt mit überprüfung
	// des null-wertes absichern!
	// https://www.baeldung.com/hibernate-save-persist-update-merge-saveorupdate
	/**
	 *
	 * @return
	 */
	protected Session getSession()
	{
		return OldHibernateUtil.getSession();
	}

	/**
	 * Das übergebene Entity-Objekt wird gespeichert (INSERT) oder aktualisiert
	 * (UPDATE).
	 * 
	 * @param entity dddd
	 */
	@Override
	public void save(T entity)
	{
		Session session = OldHibernateUtil.getSession();
		Transaction transaction = null;

		try
		{
			transaction = session.beginTransaction();
			session.saveOrUpdate(entity);
			transaction.commit();
		} catch (Exception e)
		{
			if (transaction != null)
			{
				transaction.rollback();
			}
			throw e;
		} finally
		{
			session.close();
		}
//        HibernateUtil.commitTransaction();
//        HibernateUtil.closeSession();

//        session.getTransaction().commit();
//        session.close();
	}

	/**
	 * "merge" (Zusammenführen" erstellt eine neue Instanz Ihrer Entität,
	 * kopiert den Status von der bereitgestellten Entität und verwaltet die
	 * neue Kopie. Die Instanz, die Sie übergeben, wird nicht verwaltet (alle
	 * Änderungen, die Sie vornehmen, sind nicht Teil der Transaktion - es sei
	 * denn, Sie rufen Merge erneut auf).
	 *
	 * @param entity ffff
	 */
	@Override
	public void merge(T entity)
	{
		Session session = this.getSession();
		session.beginTransaction();

		session.merge(entity);

		session.getTransaction().commit();
		session.close();
	}

	/**
	 *
	 * @param entity dddd
	 */
	@Override
	public void delete(T entity)
	{
		Session session = this.getSession();
		session.beginTransaction();

		session.delete(entity);

		session.getTransaction().commit();
		session.close();
	}

	/**
	 *
	 * @param query dddd
	 * @return dddd
	 */
	public List<T> findMany(Query query)
	{
		throw new UnsupportedOperationException("Not supported yet.");
//        Session session = this.getSession();
//        session.beginTransaction();
//        List<T> t;
//        t = (List<T>) query.list();
//        return t;
//        session.getTransaction().commit();
//        session.close();
	}

	/**
	 *
	 * @param query ddd
	 * @return ddd
	 */
	public T findOne(Query query)
	{
		throw new UnsupportedOperationException("Not supported yet.");
//        Session session = this.getSession();
//        session.beginTransaction();
//        T t;
//        t = (T) query.uniqueResult();
//        return t;
//        session.getTransaction().commit();
//        session.close();
	}

	/**
	 *
	 * @param clazz ddd
	 * @param id    ddd
	 * @return ddd
	 */
	@Override
	public T findById(Class clazz, int id)
	{
		Session session = OldHibernateUtil.getSession();
		Transaction transaction = null;
		T t = null;
		try
		{
			session = this.getSession();
			session.beginTransaction();
			t = (T) session.get(clazz, id);
			Hibernate.initialize(t);
			session.getTransaction().commit();
		} catch (HibernateException e)
		{
			throw e;
		} finally
		{
			if (session != null && session.isOpen())
			{
				session.close();
			}
		}
		return t;
	}

	/**
	 *
	 * @param clazz        ddd
	 * @param sqlTableName ddd
	 * @return ddd
	 */
	@Override
	public List<T> findAll(Class clazz, String sqlTableName)
	{
		List<T> objects = null;
		Session session = null;
		try
		{
			session = this.getSession();
			session.beginTransaction();
			objects = session
			    .createNativeQuery("SELECT * FROM " + sqlTableName, clazz)
			    .getResultList();
			session.getTransaction().commit();
		} catch (HibernateException e)
		{

		} finally
		{
			if (session != null && session.isOpen())
			{
				session.close();
			}
		}
		return objects;
	}
}
