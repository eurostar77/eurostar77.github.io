//package com.bueffeltier.data.hibernate.daoImpl;
//
//import java.util.List;
//
//import org.hibernate.HibernateException;
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//import org.hibernate.query.NativeQuery;
//
//import com.bueffeltier.data.hibernate.OldHibernateUtil;
//import com.bueffeltier.data.hibernate.dao.UserDao;
//import com.bueffeltier.data.hibernate.entity.User;
//
///**
// *
// * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
// */
//public class UserDaoImpl extends GenericDaoImpl<User, Integer>
//		implements UserDao
//{
//
//	/**
//	 *
//	 * @param activationKey
//	 * @return
//	 */
//	public User findByActivationKey(String activationKey)
//	{
//		Session session = OldHibernateUtil.getSession();
//		Transaction transaction = null;
//		User user = null;
//
//		try
//		{
//			transaction = session.beginTransaction();
//			String sql = "SELECT * FROM tl_user WHERE tr_activation_key = :akey";
//			user = (User) session.createSQLQuery(sql).addEntity(User.class)
//					.setParameter("akey", activationKey).getSingleResult();
//			transaction.commit();
//
//		} catch (javax.persistence.NoResultException e)
//		{
//
//		} finally
//		{
//			if (session.isOpen())
//			{
//				session.close();
//			}
//		}
//		return user;
//	}
//
//	// todo: schnellere suche nicht über entity sondern über dto projections bei
//	// Read-Only Operations
//	// todo: Sind nach Methodenaufruf alle Sessions geschlossen?
//	// todo: Sind Transaktionen zu beenden?
//	// todo: Alle "get.singleResult"-Zugriffe absichern!
//	// todo: query an genericDaoImpl.findOne(query)
//	/**
//	 *
//	 * @param userName
//	 * @param password
//	 * @return
//	 */
//	public User findByName(String userName, String password)
//	{
//
//		Session session = OldHibernateUtil.getSession();
//		session.beginTransaction();
//
//		User user = null;
//
//		try
//		{
//			String sql = "SELECT * FROM tl_user WHERE tr_user_name = :uname";
//			user = (User) session.createSQLQuery(sql).addEntity(User.class)
//					.setParameter("uname", userName).getSingleResult();
//		} catch (Exception e)
//		{
//
//		} finally
//		{
//			if (session != null && session.isOpen())
//			{
//				session.close();
//			}
//		}
//		return user;
//	}
//
//	// todo: query an genericDaoImpl.findOne(query)
//	/**
//	 *
//	 * @param userName
//	 * @return
//	 */
//	public User findByName(String userName)
//	{
//		Session session = OldHibernateUtil.getSession();
//		session.beginTransaction();
//		User user = null;
//		try
//		{
//			String sql = "SELECT * FROM tl_user WHERE tr_user_name = :uname";
//			user = (User) session.createSQLQuery(sql).addEntity(User.class)
//					.setParameter("uname", userName).getSingleResult();
//		} catch (Exception e)
//		{
//
//		} finally
//		{
//			if (session != null && session.isOpen())
//			{
//				session.close();
//			}
//		}
//		return user;
//	}
//
//	/*
//	
//	 */
//	public List<User> findAllAsc()
//	{
//		Session session = this.getSession();
//		session.beginTransaction();
//		List<User> users = null;
//		try
//		{
//			String sql = "SELECT * FROM tl_user ORDER BY tr_user_name ASC";
//			NativeQuery query = session.createSQLQuery(sql)
//					.addEntity(User.class); // todo: parameter als array
//											// zuweisen?
//			users = query.getResultList();
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
//		return users;
//	}
//
//	// todo: ist "hasUser" wirklich eine DataAccess-Methode? Oder muss die
//	// Methode in einer anderen Architekturschicht implementiert werden?
//	// todo: query an genericDaoImpl.findOne(query) einbinden??
//	/**
//	 *
//	 * @param userName
//	 * @return
//	 */
//	public boolean hasUser(String userName)
//	{
//		Session session = OldHibernateUtil.getSession();
//		session.beginTransaction();
//		String sql = "SELECT * FROM `tl_user` WHERE `tr_user_name` = :uname";
//		try
//		{
//			User user = (User) session.createSQLQuery(sql).addEntity(User.class)
//					.setParameter("uname", userName).getSingleResult();
//			return true;
//		} catch (javax.persistence.NoResultException e)
//		{
//			return false;
//		} finally
//		{
//			if (session != null && session.isOpen())
//			{
//				session.close();
//			}
//		}
//	}
//
//	/**
//	 *
//	 * @param email
//	 * @return
//	 */
//	public boolean hasEmail(String email)
//	{
//		Session session = OldHibernateUtil.getSession();
//		session.beginTransaction();
//		String sql = "SELECT * FROM `tl_user` WHERE `tr_email` = :email";
//		try
//		{
//			User user = (User) session.createSQLQuery(sql).addEntity(User.class)
//					.setParameter("email", email).getSingleResult();
//			return true;
//		} catch (Exception e)
//		{
//			return false;
//		} finally
//		{
//			if (session != null && session.isOpen())
//			{
//				session.close();
//			}
//		}
//	}
//
//	/**
//	 * Prüft die Logindaten und liefert, wenn User-Status = "active", das ange-
//	 * forderte User-Objekt zurück. todo: hier werden nur zugriff methoden
//	 * geführt. deshalb darf hier auch keine login-Prüfung stattfinden. umnennen
//	 * in getUserBy... todo: query an genericDaoImpl.findOne(query) einbinden???
//	 *
//	 * @param userName todo
//	 * @param passWord todo
//	 * @return todo
//	 */
//	public boolean loginCheck(String userName, String passWord)
//	{
//
//		Session session = OldHibernateUtil.getSession();
//		session.beginTransaction();
//
//		String sql = "SELECT * FROM `tl_user` WHERE `tr_user_name` = :uname";
//		try
//		{
//			User user = (User) session.createSQLQuery(sql).addEntity(User.class)
//					.setParameter("uname", userName).getSingleResult();
//
//			if (user.isActive() && user.getPassword().equals(passWord))
//			{
//				return true;
//
//			} else
//			{
//				return false;
//			}
//
//		} catch (javax.persistence.NoResultException e)
//		{
//			return false;
//		} finally
//		{
//			if (session != null && session.isOpen())
//			{
//				session.close();
//			}
//		}
//	}
//
//	/**
//	 * Prüft die Logindaten und liefert, wenn User-Status = "active", das ange-
//	 * forderte User-Objekt zurück.todo: hier werden nur zugriff methoden
//	 * geführt. deshalb darf hier auch keine login-Prüfung stattfinden. umnennen
//	 * in getUserBy... todo: query an genericDaoImpl.findOne(query) einbinden???
//	 *
//	 * @param userName todo
//	 * @param passWord todo
//	 * @return todo
//	 * @throws java.lang.Exception
//	 */
//	public User requestUser(String userName, String passWord) throws Exception
//	{
//
//		Session session = OldHibernateUtil.getSession();
//		session.beginTransaction();
//
//		String sql = "SELECT * FROM `tl_user` WHERE `tr_user_name` = :uname";
//		try
//		{
//			User user = (User) session.createSQLQuery(sql).addEntity(User.class)
//					.setParameter("uname", userName).getSingleResult();
//
//			if (user.isActive() && user.getPassword().equals(passWord))
//			{
//				return user;
//			}
//
//		} catch (javax.persistence.NoResultException e)
//		{
//			throw new Exception("User request failed!");
//
//		} finally
//		{
//			if (session.isOpen())
//			{
//				session.close();
//			}
//		}
//		throw new Exception("User request failed!");
//	}
//
//	// todo: query an genericDaoImpl.findOne(query)
//	/**
//	 *
//	 * @param userName
//	 * @param password
//	 * @return
//	 */
//	public User getPermission(String userName, String password)
//	{
//
//		Session session = OldHibernateUtil.getSession();
//		session.beginTransaction();
//
//		User user = null;
//
//		try
//		{
//			String sql = "SELECT * FROM tl_user WHERE tr_user_name = :uname";
//			user = (User) session.createSQLQuery(sql).addEntity(User.class)
//					.setParameter("uname", userName).getSingleResult();
//		} catch (HibernateException e)
//		{
//
//		} finally
//		{
//			if (session != null && session.isOpen())
//			{
//				session.close();
//			}
//		}
//		return user;
//	}
//
////    public boolean userRegistration(String userName, String userPassword, String email){
////
////        if(this.hasUser(userName)){
////            return false;
////        }else{
////            User newUser = new User();
////            newUser.setUserName(userName);
////            newUser.setPassword(userPassword);
//////            todo: E-Mail einfügen:
////            userDao.save(newUser); // todo: hier nicht userDAO, sonder UserDaoImpl!
////            return true;
////            // todo: Bestätigungsmail absenden! Confirm
////        }
////    }
//}
