//package com.bueffeltier.data.hibernate.daoImpl;
//
//import java.util.Iterator;
//import java.util.List;
//
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//import org.hibernate.query.NativeQuery;
//
//import com.bueffeltier.data.hibernate.OldHibernateUtil;
//import com.bueffeltier.data.hibernate.dao.PageDao;
//import com.bueffeltier.data.hibernate.entity.Page;
//import com.bueffeltier.logic.foundation.pagetree.Node;
//import com.bueffeltier.logic.foundation.pagetree.TreeOfPages;
//
///**
// * Bietet Datenbank-Zugriff auf die Page-Entities
// * 
// * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
// */
//public class PageDaoImpl extends GenericDaoImpl<Page, Integer>
//		implements PageDao
//{
//
//	/**
//	 * Liefert die angefrage Page incl. ihrer Artikel und deren ELemente zurück.
//	 * todo: Sicherstellen, dass die index-Seite immer die ID 0 oder 1 behält.
//	 * todo: query an genericDaoImpl.findOne(query)
//	 * 
//	 * @param servletPath
//	 * @return
//	 * @throws java.lang.Exception
//	 */
//	public Page findWithChilds(String servletPath) throws Exception
//	{
//		return findByPageTitle(
//				ensureTrailingSlash(extractPageName(servletPath))
//		);
//	}
//
//	private String extractPageName(String path)
//	{
//		return path.substring(path.lastIndexOf("/") + 1, path.length());
//	}
//
//	private String ensureTrailingSlash(String path)
//	{
//		if (!path.substring(path.length() - 1).equals('/'))
//		{
//			path += "/";
//		}
//		return path;
//	}
//
//	/**
//	 * Liefert die angefrage Page zurück. todo: Sicherstellen, dass die
//	 * index-Seite immer die ID 0 oder 1 behält. todo: query an
//	 * genericDaoImpl.findOne(query)
//	 * 
//	 * @param servletPath
//	 * @return
//	 * @throws java.lang.Exception
//	 */
//	public Page findByServletPath(String servletPath) throws Exception
//	{
//		String title = servletPath.substring(
//				servletPath.lastIndexOf("/") + 1, servletPath.length()
//		);
//		return findByPageTitle(title);
//	}
//
//	/**
//	 * Liefert eine Page anhand des übergebenen titles zurück. Wirft Exception,
//	 * wenn Page nicht gefunden wird. todo: query an
//	 * genericDaoImpl.findOne(query)
//	 * 
//	 * @param title
//	 * @return
//	 * @throws java.lang.Exception
//	 */
////	public Page findWithChilds(String title) throws Exception
////	{
////		Session session = HibernateUtil.getSession();
////		Transaction transaction = null;
////		
////		Page page = null;
////
////		try
////		{
////			transaction = session.beginTransaction();
////
////			String sql = "SELECT * FROM page WHERE tr_title = :title";
////			page = (Page) session.createSQLQuery(sql).addEntity(Page.class)
////					.setParameter("title", title).getSingleResult();
////			transaction.commit();
////
////		} catch (Exception e)
////		{
////			if (transaction != null)
////			{
////				transaction.rollback();
////			}
//////            log.add(this, "findByPageTitle(String title)", "- catch Exception: PageNotFound, throw e");
////			throw new Exception("PageNotFound!", e);
////
////		} finally
////		{
////			session.close();
////
////		}
////		return page;
////	}
//
//	/**
//	 * Liefert eine Page anhand des übergebenen titles zurück. Wirft Exception,
//	 * wenn Page nicht gefunden wird. todo: query an
//	 * genericDaoImpl.findOne(query)
//	 * 
//	 * @param title
//	 * @return
//	 * @throws java.lang.Exception
//	 */
//	public Page findByPageTitle(String title) throws Exception
//	{
//
//		Session session = OldHibernateUtil.getSession();
//		Transaction transaction = null;
//		Page page = null;
//
//		try
//		{
//			transaction = session.beginTransaction();
//
//			String sql = "SELECT * FROM page WHERE tr_title = :title";
//			page = (Page) session.createSQLQuery(sql).addEntity(Page.class)
//					.setParameter("title", title).getSingleResult();
//			transaction.commit();
//
//		} catch (Exception e)
//		{
//			if (transaction != null)
//			{
//				transaction.rollback();
//			}
////            log.add(this, "findByPageTitle(String title)", "- catch Exception: PageNotFound, throw e");
//			throw new Exception("PageNotFound!", e);
//
//		} finally
//		{
//			session.close();
//
//		}
//		return page;
//	}
//
//	/**
//	 * Gibt ein Seiten-Objekt anhand der Id zurück. todo: query an
//	 * genericDaoImpl.findOne(query)
//	 * 
//	 * @param id
//	 * @return
//	 */
//	public Page findByPageId(int id)
//	{
//		Session session = OldHibernateUtil.getSession();
//
////		Transaction tx = session.beginTransaction();
////		
////		CriteriaBuilder crBuilder = session.getCriteriaBuilder();
////		CriteriaQuery<Item> cr = crBuilder.createQuery(Item.class);
////		Root<Page> root = cr.from(Page.class);
////		cr.multiselect(root).where(crBuilder.is(root.get("id"), 1000));
////
////		Query<Item> query = session.createQuery(cr);
////		List<Item> results = query.getResultList();
//
////		CriteriaBuilder builder = session.getCriteriaBuilder();
////		
////		CriteriaQuery<Page> criteria = builder.createQuery(Page.class);
////		Root<Page> root = criteria.from(Page.class);
////		criteria.select(root.get(Page.id));
////		criteria.where(builder.equal(root.get(Page.id), id));
////		
////		List<String> nickNames = session.createQuery(criteria).getResultList();
//
//		Transaction transaction = null;
//		Page page = null;
//
//		try
//		{
//			transaction = session.beginTransaction();
//
//			String sql = "SELECT * FROM page WHERE id = :id";
//
//			page = (Page) session.createSQLQuery(sql).addEntity(Page.class)
//					.setParameter("id", id).getSingleResult();
//
//			transaction.commit();
//
//		} catch (Exception e)
//		{
//
//			if (transaction != null)
//			{
//				transaction.rollback();
//			}
//
//			throw e;
//
//		} finally
//		{
//			session.close();
//		}
//
//		return page;
//	}
//
//	/**
//	 * Baut auf Grundlage der pageId einen ServletPath. Achtung, Seite muss
//	 * bereits in der Datenbank vorhanden sein. todo: query an
//	 * genericDaoImpl.findOne(query)
//	 * 
//	 * @param pageId Page-Id der Seite.
//	 * @return String Servlet-Path
//	 */
//	public String buildServletPath(int pageId)
//	{
////        log.add(this, "getPath(int pageId)", "");
//		int currentPageId = pageId;
//		int rootId = findRootId();
//		Page currentPage;
//
//		StringBuilder path = new StringBuilder();
//
//		// todo: alles in eine Transaction!
//		do
//		{
////            log.add(this, "getPath(int pageId)", ": do while: " + currentPageId + " != " + rootId);
//			// todo: seiten über den neuen pageTree aufrufen.
//			// -horizontalIterator down!
//			// todo: page id 0 oder 1?
//			// todo: die index-seite kann nicht verschoben werden und
//			// heißt immer wie das projekt. -das wird über den pageTree
//			// geblockt.
//			currentPage = this.findById(Page.class, currentPageId);
//
//			// todo: if ist völlig überflüssig, da rootPath eh durch getParentId
//			// erreicht wird. teste das!
//			if (currentPageId == findRootId())
//			{
////                log.add(this, "getPath(int pageId)", "do: currentPageId == findRootId()");
//				path.insert(0, getRootPath());
//			} else
//			{
////                log.add(this, "getPath(int pageId)", " do: currentPageId != findRootId()");
//				path.insert(0, currentPage.getHtmlTitle());
//				path.insert(0, "/");
//			}
//			currentPageId = currentPage.getParentId();
//
//		} // todo: while(currentPage.getId != findRootId()
//			// oder: hat kleinste parentId
//		while (currentPage.getId() != rootId);
//
//		return path.toString(); // todo: statt servletPath contextPath?
//	}
//
//	// todo: query an genericDaoImpl.findOne(query)
//	// todo: funktioniert nicht richtig!!!
//	/**
//	 *
//	 * @param start
//	 * @param stop
//	 * @return
//	 * @deprecated
//	 */
//	@Deprecated
//	public List<Page> getSortedTree(int start, int stop)
//	{
//		Session session = OldHibernateUtil.getSession();
//		Transaction transaction = null;
//		List<Page> pages = null;
//		try
//		{
//			transaction = session.beginTransaction();
//			String sql = "SELECT * FROM page WHERE id BETWEEN :start AND :stop ORDER BY id ASC, tr_parent_id ASC";
//			NativeQuery query = session.createSQLQuery(sql)
//					.addEntity(Page.class).setParameter("start", start)
//					.setParameter("stop", stop); // todo: parameter als array
//													// zuweisen?
//
//			pages = query.getResultList();
//			transaction.commit();
//		} catch (Exception e)
//		{
//			if (transaction != null)
//			{
//				transaction.rollback();
//			}
//			throw e;
//		} finally
//		{
//			session.close();
//		}
//		return pages;
//	}
//
//// todo: query an genericDaoImpl.findOne(query)
//// todo: funktioniert nicht richtig!!!
//	/**
//	 *
//	 * @param parentId
//	 * @return
//	 */
//	public List<Page> getChildPages(int parentId)
//	{
////        Page page = this.findByPageId(parentId);
////        int currentParentId = page.getParentId();
//		List<Page> pages = null;
//		Session session = OldHibernateUtil.getSession();
//		Transaction transaction = null;
//		try
//		{
//			transaction = session.beginTransaction();
//			pages = session.createSQLQuery(
//					// "SELECT * FROM page WHERE tr_parent_id = (SELECT
//					// MIN(tr_parent_id) FROM (SELECT * FROM page WHERE
//					// tr_parent_id > :id) page)")
//					"SELECT * FROM page WHERE tr_parent_id = :id"
//			).addEntity(Page.class).setParameter("id", parentId).list();
////            String sql = "SELECT * FROM page WHERE tr_parent_id = 0";
////
////            NativeQuery query =  (NativeQuery) session.createSQLQuery(sql)
////                .addEntity(Page.class).getResultList();
//////                     .setParameter("id", currentId);
//////                    .
//////                getSingleResult();
////            pages = query.getResultList();
//			transaction.commit();
//		} catch (Exception e)
//		{
//			if (transaction != null)
//			{
//				transaction.rollback();
//			}
//			throw e;
//		} finally
//		{
//			session.close();
//		}
//		return pages;
//	}
//
//	// todo: query an genericDaoImpl.findOne(query)
//	// todo: funktioniert nicht richtig!!!
//	/**
//	 * Gibt eine Seiten-Liste der Unterseiten zur übergebenen Seiten-Id zurück.
//	 * 
//	 * @param parentId
//	 * @return List
//	 */
//	public List<Page> getChildPagesAsc(int parentId)
//	{
////        Page page = this.findByPageId(parentId);
////        int currentParentId = page.getParentId();
//		List<Page> pages = null;
//		Session session = OldHibernateUtil.getSession();
//		Transaction transaction = null;
//		try
//		{
//			transaction = session.beginTransaction();
////            String sql = "SELECT * FROM page WHERE tr_parent_id = :id ORDER BY tr_order ASC";
////            NativeQuery query = session.createSQLQuery(sql)
////                    .addEntity(Page.class)
////                    .setParameter("id", parentId); // todo: parameter als array zuweisen?
////
////            pages = query.getResultList();
//			pages = session.createSQLQuery(
//					"SELECT * FROM page WHERE tr_parent_id = :id ORDER BY tr_order ASC"
//			).addEntity(Page.class).setParameter("id", parentId).list();
//			transaction.commit();
//		} catch (Exception e)
//		{
//			if (transaction != null)
//			{
//				transaction.rollback();
//			}
//			throw e;
//		} finally
//		{
//			session.close();
//		}
//		return pages;
//	}
//
//	// todo: query an genericDaoImpl.findOne(query)
//	/**
//	 *
//	 * @param parentId
//	 * @return
//	 */
//	public int getNextLevel(int parentId)
//	{
//
////        Page page = this.findByPageId(parentId);
////        int currentParentId = page.getParentId();
////        List<Page> pages = null;
//		int nextLevel = 0;
//		Session session = OldHibernateUtil.getSession();
//		Transaction transaction = null;
//		try
//		{
//			transaction = session.beginTransaction();
//			nextLevel = session.createSQLQuery(
//					// "SELECT * FROM page WHERE tr_parent_id = (SELECT
//					// MIN(tr_parent_id) FROM (SELECT * FROM page WHERE
//					// tr_parent_id > :id) page)")
//					"SELECT MIN(tr_parent_id) FROM (SELECT * FROM page WHERE tr_parent_id > :id) page"
//			).addEntity(Page.class).setParameter("id", parentId)
//					.getFirstResult();
////            String sql = "SELECT * FROM page WHERE tr_parent_id = 0";
////
////            NativeQuery query =  (NativeQuery) session.createSQLQuery(sql)
////                .addEntity(Page.class).getResultList();
//////                     .setParameter("id", currentId);
//////                    .
//////                getSingleResult();
////            pages = query.getResultList();
//			transaction.commit();
//		} catch (Exception e)
//		{
//			if (transaction != null)
//			{
//				transaction.rollback();
//			}
//			throw e;
//		} finally
//		{
//			session.close();
//		}
//		return nextLevel;
//	}
//
//	// todo: query an genericDaoImpl.findOne(query)
//	/**
//	 *
//	 * @param pageId
//	 * @return
//	 */
//	public boolean hasChildPages(int pageId)
//	{
////        Page page = this.findByPageId(parentId);
////        int currentParentId = page.getParentId();
////        List<Page> pages = null;
//		String bool = null;
//		Session session = OldHibernateUtil.getSession();
//		Transaction transaction = null;
//		try
//		{
//			transaction = session.beginTransaction();
//			bool = (String) session.createSQLQuery(
//					"SELECT CASE WHEN EXISTS ( SELECT 1 FROM page WHERE tr_parent_id = :id )THEN 'true' ELSE 'false' END"
//			)
//					// .addEntity( Page.class )
//					.setParameter("id", pageId).uniqueResult();
//
////            bool = (String) session.createSQLQuery(
////                    "SELECT CASE WHEN EXISTS ( SELECT 1 FROM page WHERE tr_parent_id = :id )THEN 'true' ELSE 'false' END")
////                    .setParameter("id", pageId)
////                    .uniqueResult();
////            JOptionPane.showMessageDialog(null, "abgefragt: "+ bool);
////            String sql = "SELECT * FROM `page` WHERE `tr_parent_id` = 0";
////
////            NativeQuery query =  (NativeQuery) session.createSQLQuery(sql)
////                .addEntity(Page.class).getResultList();
//////                     .setParameter("id", currentId);
//////                    .
//////                getSingleResult();
////            pages = query.getResultList();
//			transaction.commit();
//		} catch (Exception e)
//		{
//			if (transaction != null)
//			{
//				transaction.rollback();
//			}
//			throw e;
//		} finally
//		{
//			session.close();
//		}
//
//		if (bool.equals("true"))
//		{
//			return true;
//		} else
//		{
//			return false;
//		}
//	}
//
//	// todo: Metode wird überflüssig, wenn über den PageTree direkt auf die
//	// RootPage zugegriffen werden kann. PageDaoImpl soll nur noch den Zugriff
//	// auf die Datenbank herstellen, -beim initialisieren des Seitenstammes in
//	// der Servlet init()-Mathode und beim HInzufügen, Löschen und Verschieben
//	// von Seiten.
//	/**
//	 *
//	 * @return
//	 */
//	public Page findRootPage()
//	{
//		return this.findById(Page.class, this.findRootId());
//	}
//
//	/**
//	 *
//	 * @return
//	 */
//	public String getRootPath()
//	{
//		return "/" + findRootPage().getHtmlTitle();
//	}
//
//	/**
//	 * Findet die Root-ID in der Datenbank, der Index-Seite der Website, dessen
//	 * Parent-Id 0 ist. todo: query an genericDaoImpl.findOne(query)
//	 * 
//	 * @return
//	 */
//	public int findRootId()
//	{
//		int root = 0;
//		Session session = OldHibernateUtil.getSession();
//		Transaction transaction = null;
//		try
//		{
//			transaction = session.beginTransaction();
//			root = (Integer) session.createSQLQuery(
//					"SELECT id FROM page where tr_parent_id = 0"
//			).uniqueResult();
//			transaction.commit();
//		} catch (Exception e)
//		{
//			if (transaction != null)
//			{
//				transaction.rollback();
//			}
//			throw e;
//		} finally
//		{
//			session.close();
//		}
//		return root;
//	}
//
//	/**
//	 * Löscht eine einzelne Seite. Parent-Ids der Child-Pages werden
//	 * aktualisiert. Pfade der Child-Pages werden angepasst.
//	 * 
//	 * @param page
//	 * @return
//	 * @throws java.lang.Exception
//	 */
//	public boolean deletePage(Page page) throws Exception
//	{
//		// Geschützte Seiten nicht löschen:
//		if (page.isProtected())
//		{
//			return false; // todo: eigene exception werfen?
//		}
//		// RootNode nicht löschen:
//		if (page.getParentId() == 0)
//		// todo: bei der rootPage dürfen niemals die Id und die Parent-Id
//		// geändert werden. Sowieso ist die ID einzigartig.
//		// todo: im rahmen des contentManagements muss es später aber doch
//		// möglich sein, die rootPage zu löschen, damit eine neue erstellt
//		// werden kann.
//		{
//			return false; // todo: eigene exception werfen?
//		}
//
//		Session session = OldHibernateUtil.getSession();
//		Transaction transaction = null;
//		try
//		{
//			transaction = session.beginTransaction();
//
//			// Seitenbaum von Page mit Kindern und Kindeskindern laden:
//			// todo: treeOfPages mit parent von page beginnen!
//			TreeOfPages treeOfPages = new TreeOfPages();
//			treeOfPages.buildTree(page.getId());
//
//			// Die Geschwister der zu löschenden Seite ermitteln:
//			// (Hier incl. der zu löschenden Seite!) todo: hier nur die
//			// Geschwister abrufen!
//			List<Page> siblings = this.getChildPagesAsc(page.getParentId());
//
//			// Zählt die Order der direkten Kinder von Page, um sich selbst und
//			// die Order der alten Geschwister von Page später zu erhöhen.
//			int siblingsInsertOrder = page.getOrder();
//
//			/*
//			 * Den Kindern und allen Kindeskindern einen neuen Pfad schreiben:
//			 */
//			// Neuen Pfad vorbereiten (= Pfad der ParentPage):
//			String newPathSnipped;
//			newPathSnipped = // alten Pfad der zu löschenden Seite...
//					page.getPath().substring(
//							// ...aufteilen in Substring...
//							0, // ...von 0 bis...
//								// altePfad-Länge minus alten Title
//							(page.getPath().length()
//									- page.getHtmlTitle().length() - 1)
//					);
//			Iterator<Node<Page>> iterator = treeOfPages.verticalIterator();
//			while (iterator.hasNext())
//			{
//				Node<Page> currentNode = iterator.next();
//				Page currentPage = currentNode.getItem();
//
//				if (currentNode.getLevel() == 0)
//				{
//					/*
//					 * In Level 1 nichts machen, da es um die Kinder geht. Page
//					 * wird gelöscht!
//					 */
//
//				} else
//				{
//					// Pfad auf Level 1:
//					if (currentNode.getLevel() == 1)
//					{
//						currentPage.setPath(
//								newPathSnipped + "/"
//										+ currentPage.getHtmlTitle()
//						);
//						currentPage.setParentId(page.getParentId());
//						currentPage.setOrder(siblingsInsertOrder);
//						siblingsInsertOrder++;
//					}
//					// Pfade ab Level 2:
//					if (currentNode.getLevel() > 1)
//					{
//						currentPage.setPath(
//								currentNode.getParent().getItem().getPath()
//										.concat("/")
//										.concat(currentPage.getHtmlTitle())
//						);
//					}
//				}
//				this.save(currentPage);
//			}
//
//			/*
//			 * Order der Geschwister-Seiten neu schreiben. Nur wenn Page keine
//			 * Kinder hatte, denn sonst rutschen die Kinder einfach nach.
//			 */
//			if (treeOfPages.size() <= 1)
//			{
//				int shift = 0;
//				for (int i = 0; i < siblings.size(); i++)
//				{
//					// Wenn zu löschende Seite erreicht, nichts tun:
//					if (siblings.get(i).getId() == page.getId())
//					{
//						// Seite bekommt keine neue Order, da sie gelöscht wird.
//						shift--;
//					} else
//					{
//						siblings.get(i).setOrder(i + shift);
//						this.save(siblings.get(i));
//					}
//				}
//			} else
//			{
//				// Kinder rutschen nach und orderCount erhöht die Order der
//				// nachfolgenden neuen Geschwister:
//				int shift = 0;
//				boolean insertShift = false;
//				siblingsInsertOrder--;
//				for (int i = 0; i < siblings.size(); i++)
//				{
////                    order der geschwister ab alter Position um orderCount erhöhen.
//					// Wenn zu löschende Seite erreicht nichts tun:
//					if (siblings.get(i).getId() == page.getId())
//					{
//						// Seite bekommt keine neue Order, da sie gelöscht wird.
//						shift--;
//						insertShift = true;
//					} else
//					{
//						if (insertShift)
//						{
//							siblings.get(i)
//									.setOrder(i + shift + siblingsInsertOrder);
//						} else
//						{
//							siblings.get(i).setOrder(i + shift);
//						}
//						this.save(siblings.get(i));
//					}
//				}
//
//			}
////            JOptionPane.showMessageDialog(null, "jetzt löschen?");
//			// Die zu löschende Seite kann jetzt gelöscht werden.
//
//			this.delete(page);
//			// todo: Prüfen, ob seite und unterseiten bereits veröffentlicht
//			// sind. (SEO)
//			// todo: Weitere Anpassungen an den ChildPages: Navigation, Links,..
//			//
////            teste:
////            wie führt man rollback herbei? how to provoke rolback?
////            funktioniert rollback? suche beispiel seite und ids heraus!
////            warum zeigt siteStructureHandler nicht sofort an
//			transaction.commit();
//			return true;
////            transaction.setRollbackOnly();
//		} catch (Exception e) // ehemals headless exception
//		{
////            JOptionPane.showMessageDialog(null, "catch");
//			if (transaction != null)
//			{
//				transaction.rollback();
//			}
//			throw e;
//		} finally
//		{
//			session.close();
//		}
//	}
//
////    // unbrauchbar! Es gibt keine rootPage!
////    public Page findRootPage(){
////        Page rootPage = null;
////        Session session = this.getSession();
////        session.beginTransaction();
////        try{
////            rootPage = (Page) session.createSQLQuery(
////                    "SELECT * FROM page WHERE MIN(tr_parent_id)")
////            .addEntity(Page.class)
////            .uniqueResult();
////        } catch (Exception e) {
////        } finally {
////            if (session != null && session.isOpen()) {
////                session.close();
////            }
////        }
////        return rootPage;
////    }
//}
