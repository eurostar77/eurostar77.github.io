//package com.bueffeltier.data.hibernate.entity;
//
//import java.time.LocalDateTime;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
//
//import org.hibernate.annotations.Type;
//
///**
// * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
// */
//@Entity
//@Table(name = "tl_user", catalog = "bueffeltier")
////@Table(name="user_data")
//public class User implements java.io.Serializable
//{
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "tr_id", unique = true, nullable = false, length = 10)
//	private int id;
//
//	@Column(name = "tr_user_name", unique = true, nullable = true, length = 45)
//	private String userName;
//
//	@Column(name = "tr_password", nullable = true, length = 45)
//	private String password;
//
//	@Column(name = "tr_permission", nullable = true, length = 10)
//	private int permission;
//
//	@Column(name = "tr_email", nullable = true, length = 255)
//	private String email;
//
//	@Type(type = "org.hibernate.type.NumericBooleanType")
//	@Column(columnDefinition = "TINYINT", name = "tr_active", nullable = true, length = 1)
//	private boolean active;
//
//	@Column(name = "tr_activation_key", nullable = true, length = 255)
//	private String activationKey;
//
//	@Column(name = "tr_activation_key_expiration_date", nullable = true)
//	private LocalDateTime activationKeyExpirationDate;
//
//	/**
//	 *
//	 */
//	public User()
//	{
//	}
//
//	/**
//	 *
//	 * @param userName
//	 * @param password
//	 */
//	public User(String userName, String password)
//	{
//		this.userName = userName;
//		this.password = password;
//		this.active = false;
//	}
//
//	/**
//	 *
//	 * @return
//	 */
//	public int getID()
//	{
//		return this.id;
//	}
//
//	/**
//	 *
//	 * @param id
//	 */
//	public void setID(int id)
//	{
//		this.id = id;
//	}
//
//	/**
//	 *
//	 * @return
//	 */
//	public String getUserName()
//	{
//		return this.userName;
//	}
//
//	/**
//	 *
//	 * @param userName
//	 */
//	public void setUserName(String userName)
//	{
//		this.userName = userName;
//	}
//
//	/**
//	 *
//	 * @return
//	 */
//	public String getPassword()
//	{
//		return this.password;
//	}
//
//	/**
//	 *
//	 * @param password
//	 */
//	public void setPassword(String password)
//	{
//		this.password = password;
//	}
//
//	/**
//	 *
//	 * @return
//	 */
//	public int getPermission()
//	{
//		return this.permission;
//	}
//
//	/**
//	 *
//	 * @param permission
//	 */
//	public void setPermission(int permission)
//	{
//		this.permission = permission;
//	}
//
//	public String getEmail()
//	{
//		return email;
//	}
//
//	public void setEmail(String email)
//	{
//		this.email = email;
//	}
//
//	/**
//	 *
//	 * @return
//	 */
//	public boolean isActive()
//	{
//		return active;
//	}
//
//	/**
//	 *
//	 * @param active
//	 */
//	public void setActive(boolean active)
//	{
//		this.active = active;
//	}
//
//	public String getActivationKey()
//	{
//		return activationKey;
//	}
//
//	public void setActivationKey(String activationKey)
//	{
//		this.activationKey = activationKey;
//	}
//
//	public LocalDateTime getActivationKeyExpirationDate()
//	{
//		return activationKeyExpirationDate;
//	}
//
//	public void setActivationKeyExpirationDate(
//			LocalDateTime activationKeyExpirationDate
//	)
//	{
//		this.activationKeyExpirationDate = activationKeyExpirationDate;
//	}
//
//	public String getPermissionName()
//	{
//		/*
//		 * Login Status: 0 visitor 1 user 2 member 3 premium 4 admin
//		 */
//		switch (this.permission) {
//		case 0:
//			return "Besucher";
//		case 1:
//			return "Benutzer";
//		case 2:
//			return "Mitglied";
//		case 3:
//			return "Premium-Mitglied";
//		case 4:
//			return "Admin";
//		}
//		return "";
//	}
//
//}
