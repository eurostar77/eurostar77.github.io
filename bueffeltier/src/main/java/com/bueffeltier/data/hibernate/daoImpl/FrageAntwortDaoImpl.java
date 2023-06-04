//package com.bueffeltier.data.hibernate.daoImpl;
//
//import java.util.List;
//
//import org.hibernate.HibernateException;
//import org.hibernate.Session;
//import org.hibernate.query.NativeQuery;
//
//import com.bueffeltier.data.hibernate.dao.FrageAntwortDao;
//import com.bueffeltier.data.hibernate.entity.FrageAntwort;
//
///**
// *
// * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
// */
//public class FrageAntwortDaoImpl extends GenericDaoImpl<FrageAntwort, Integer>
//    implements FrageAntwortDao
//{
//
//	// todo: schnellere suche nicht über entity sondern über dto projections bei
//	// Read-Only Operations
//	/**
//	 *
//	 * @return
//	 */
//	public int getSize()
//	{
//		throw new UnsupportedOperationException("Not supported yet."); // To
//		                                                               // change
//		                                                               // body
//		                                                               // of
//		                                                               // generated
//		                                                               // methods,
//		                                                               // choose
//		                                                               // Tools
//		                                                               // |
//		                                                               // Templates.
//	}
//
//	// todo: query an genericDaoImpl.findOne(query)
//	/**
//	 *
//	 * @param fromId
//	 * @return
//	 */
//	public FrageAntwort findNextById(int fromId)
//	{
////        Session session = this.getSession();
////        session.beginTransaction();
////
////         String sql = "SELECT * FROM `user_data` WHERE `user_name` = :uname";
////        try {
////            FrageAntwort fran = (FrageAntwort) session.createSQLQuery(sql)
////                    .addEntity(User.class).setParameter("fran", userName).getSingleResult();
////
////
////
////
////
////
////
////        FrageAntwort fran;
////        try {
////
////            fran =  (FrageAntwort)session.get(FrageAntwort.class, fromId);
////            Hibernate.initialize(t);
////        } catch (HibernateException e) {
////
////        } finally {
////            if (session != null && session.isOpen()) {
////                session.close();
////            }
////        }
//		return null;
//
//	}
//
//	public List<FrageAntwort> findAllAsc(int parentPageId)
//	{
//		Session session = this.getSession();
//		session.beginTransaction();
//		List<FrageAntwort> frageAntwortList = null;
//		try
//		{
//			String sql = "SELECT * FROM tbl_exercise WHERE tr_parent_id = :id ORDER BY tr_order ASC";
//			NativeQuery query = session.createSQLQuery(sql)
//			    .addEntity(FrageAntwort.class).setParameter("id", parentPageId); // todo:
//			                                                                     // parameter
//			                                                                     // als
//			                                                                     // array
//			                                                                     // zuweisen?
//			frageAntwortList = query.getResultList();
//			session.getTransaction().commit();
//		} catch (HibernateException e)
//		{
//			//
//		} finally
//		{
//			if (session != null && session.isOpen())
//			{
//				session.close();
//			}
//		}
//		return frageAntwortList;
//	}
//
//	public List<FrageAntwort> findAll()
//	{
//		Session session = this.getSession();
//		session.beginTransaction();
//		List<FrageAntwort> frageAntwortList = null;
//
//		try
//		{
//			String sql = "SELECT * FROM `tbl_exercise`";
//			NativeQuery query = session.createSQLQuery(sql)
//			    .addEntity(FrageAntwort.class);
////            JOptionPane.showMessageDialog(null, "hier");
//			frageAntwortList = query.getResultList();
//
//			session.getTransaction().commit();
//
//		} catch (HibernateException e)
//		{
//			//
//		} finally
//		{
//			if (session != null && session.isOpen())
//			{
//				session.close();
//			}
//		}
//		return frageAntwortList;
//	}
//
//}
