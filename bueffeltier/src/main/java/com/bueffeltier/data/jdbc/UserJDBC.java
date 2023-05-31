package com.bueffeltier.data.jdbc;

import java.time.LocalDateTime;

/**
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
public class UserJDBC implements java.io.Serializable
{
	private long id;
	private String name;
	private String passwordHash;
	private int permission;
	private String email;
	private boolean isActive;
	private String activationKey;
	private String sessionToken;
	private LocalDateTime activationKeyExpirationTime;
	private boolean isAnonymous;
	private LocalDateTime lastVisitDate;
	private LocalDateTime accountCreationDate;
	private String sessionId;

	public UserJDBC()
	{
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
