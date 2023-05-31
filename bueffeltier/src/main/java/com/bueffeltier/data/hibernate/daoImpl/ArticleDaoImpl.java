package com.bueffeltier.data.hibernate.daoImpl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import com.bueffeltier.data.hibernate.HibernateUtil;
import com.bueffeltier.data.hibernate.dao.ArticleDao;
import com.bueffeltier.data.hibernate.entity.Article;

/**
 *
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
public class ArticleDaoImpl extends GenericDaoImpl<Article, Integer>
		implements ArticleDao
{

	private static final Logger LOGGER = LogManager
			.getLogger(ArticleDaoImpl.class);

	// todo: schnellere suche nicht über entity sondern über dto projections bei
	// Read-Only Operations
	// todo: query an genericDaoImpl.findOne(query)
	/**
	 * Liefert alle Artikel einer Seite in der richtigen Reihenfolge zurück.
	 * 
	 * @param parentPageId
	 * @return
	 */
	public List<Article> findAllAsc(int parentPageId)
	{
		Session session = this.getSession();
		session.beginTransaction();
		List<Article> articles = null;
		try
		{
			String sql = "SELECT * FROM tl_article WHERE parent_id = :id ORDER BY tr_order ASC";
			NativeQuery query = session.createSQLQuery(sql)
					.addEntity(Article.class).setParameter("id", parentPageId); // todo:
																				// parameter
																				// als
																				// array
																				// zuweisen?
			articles = query.getResultList();
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
		return articles;
	}

	/**
	 *
	 * @param parentId
	 * @param order
	 * @return
	 */
	public Article find(int parentId, int order)
	{
		Session session = HibernateUtil.getSession();
		Transaction transaction = null;
		Article article = null;
		try
		{
			transaction = session.beginTransaction();
			String sql = "SELECT * FROM tl_article WHERE parent_id = :parent AND tr_order = :order";
			article = (Article) session.createSQLQuery(sql)
					.addEntity(Article.class).setParameter("parent", parentId)
					.setParameter("order", order).getSingleResult();

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
		return article;
	}
}
