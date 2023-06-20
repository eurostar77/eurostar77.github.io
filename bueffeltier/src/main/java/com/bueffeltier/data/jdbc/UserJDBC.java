package com.bueffeltier.data.jdbc;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
@Entity(name = "UserJDBC")
@Table(name = "user")
public class UserJDBC implements java.io.Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private long id;

	@Column(name = "name")
	private String name;

	@Column(name = "password")
	private String passwordHash;

	@Column(name = "permission")
	private int permission;

	@Column(name = "email ")
	private String email;

	@Column(name = "is_active")
	private boolean isActive;

	@Column(name = "activation_key ")
	private String activationKey;

	@Column(name = "session_token ")
	private String sessionToken;

	@Column(name = "activation_key_experiation_time")
	private LocalDateTime activationKeyExpirationTime;

	@Column(name = "is_anonymous")
	private boolean isAnonymous;

	@Column(name = "last_visit_date")
	private LocalDateTime lastVisitDate;

	@Column(name = "account_creation_date")
	private LocalDateTime accountCreationDate;

	@Column(name = "session_id")
	private String sessionId;

//	@Column(name = "learning_level")
//	private LearningLevel learningLevel;

	public UserJDBC()
	{
		// nix
	}

	public UserJDBC(String userName, String passwordHash)
	{
		this.name = userName;
		this.passwordHash = passwordHash;
		this.isActive = false;
	}

	public long getId()
	{
		return this.id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getName()
	{
		return this.name;
	}

	public void setName(String userName)
	{
		this.name = userName;
	}

	public String getPasswordHash()
	{
		return this.passwordHash;
	}

	public void setPasswordHash(String passwordHash)
	{
		this.passwordHash = passwordHash;
	}

	public int getPermission()
	{
		return this.permission;
	}

	public void setPermission(int permission)
	{
		this.permission = permission;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public boolean isActive()
	{
		return isActive;
	}

	public void setActive(boolean active)
	{
		this.isActive = active;
	}

	public String getActivationKey()
	{
		return activationKey;
	}

	public void setActivationKey(String activationKey)
	{
		this.activationKey = activationKey;
	}

	public LocalDateTime getActivationKeyExpirationTime()
	{
		return activationKeyExpirationTime;
	}

	public void setActivationKeyExpirationTime(
	    LocalDateTime activationKeyExpirationDate
	)
	{
		this.activationKeyExpirationTime = activationKeyExpirationDate;
	}

	public String getSessionToken()
	{
		return sessionToken;
	}

	public void setSessionToken(String sessionToken)
	{
		this.sessionToken = sessionToken;
	}

	public boolean isAnonymous()
	{
		return isAnonymous;
	}

	public void setAnonymous(boolean isAnonymous)
	{
		this.isAnonymous = isAnonymous;
	}

	public LocalDateTime getLastVisitDate()
	{
		return lastVisitDate;
	}

	public void setLastVisitDate(LocalDateTime lastVisitDate)
	{
		this.lastVisitDate = lastVisitDate;
	}

	public LocalDateTime getAccountCreationDate()
	{
		return accountCreationDate;
	}

	public void setAccountCreationDate(LocalDateTime accountCreationDate)
	{
		this.accountCreationDate = accountCreationDate;
	}

	public String getSessionId()
	{
		return sessionId;
	}

	public void setSessionId(String sessionId)
	{
		this.sessionId = sessionId;
	}
}
