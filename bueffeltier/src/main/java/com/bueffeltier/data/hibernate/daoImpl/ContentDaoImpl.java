/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bueffeltier.data.hibernate.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import com.bueffeltier.data.hibernate.dao.ContentDao;
import com.bueffeltier.data.hibernate.entity.Content;

/**
 *
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
public class ContentDaoImpl extends GenericDaoImpl<Content, Integer>
		implements ContentDao
{

	// todo: schnellere suche nicht über entity sondern über dto projections bei
	// Read-Only Operations
	// todo: query an genericDaoImpl.findOne(query)
	/**
	 *
	 * @param id
	 * @return
	 */
	public Content findById(int id)
	{

		Session session = this.getSession();
		session.beginTransaction();

		Content content = null;

		try
		{
			String sql = "SELECT * FROM tl_content WHERE tr_id = :id";
			content = (Content) session.createSQLQuery(sql)
					.addEntity(Content.class).setParameter("id", id)
					.getSingleResult();
			session.getTransaction().commit();
		} catch (HibernateException e)
		{

			//
		} finally
		{
			if (session != null && session.isOpen())
			{
				session.close();
			}
		}
		return content;
	}

	// todo: query an genericDaoImpl.findAll(query)
	/**
	 *
	 * @param parentId
	 * @return
	 */
	public List<Content> findAllByParentIdUnsorted(int parentId)
	{

		Session session = this.getSession();
		session.beginTransaction();

		List<Content> contents = null;

		try
		{

			String sql = "SELECT * FROM tl_content WHERE tr_parent_id = :id ORDER BY tr_order";
			NativeQuery query = session.createSQLQuery(sql)
					.addEntity(Content.class).setParameter("id", parentId);

			contents = query.list();
			session.getTransaction().commit();

		} catch (HibernateException e)
		{

			//
		} finally
		{
			if (session != null && session.isOpen())
			{
				session.close();
			}
		}
		return contents;
	}

	/**
	 * Gibt ein List-Objekt mit allen Content-Elementen zur übergebenen Seite
	 * zurück. Gibt eine leere Liste zurück, wenn keine Content-Elemente
	 * gefunden wurden. todo: schnellere suche nicht über entity sondern über
	 * dto projections bei Read-Only Operations todo: query an
	 * genericDaoImpl.findOne(query)
	 * 
	 * @param articleId
	 * @return
	 */
	public List<Content> findAllAsc(int articleId)
	{
//        JOptionPane.showMessageDialog(null, "drin!");
		Session session = this.getSession();
		session.beginTransaction();

		List<Content> contentElements = new ArrayList<>();
		try
		{
			String sql = "SELECT * FROM tl_content WHERE tr_parent_id = :id ORDER BY tr_order ASC";
			NativeQuery query = session.createSQLQuery(sql)
					.addEntity(Content.class).setParameter("id", articleId); // todo:
																				// parameter
																				// als
																				// array
																				// zuweisen?
			contentElements = query.getResultList();
			session.getTransaction().commit();
		} catch (HibernateException e)
		{
//            JOptionPane.showMessageDialog(null, "HHHHHHa!");
		} finally
		{
			if (session != null && session.isOpen())
			{
				session.close();
			}
		}
		return contentElements;
	}
}
