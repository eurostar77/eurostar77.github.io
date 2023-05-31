package com.bueffeltier.logic.foundation;

import com.bueffeltier.data.jdbc.UserDaoJDBC;
import com.bueffeltier.data.jdbc.UserJDBC;

public class UserRepository
{
	private static UserRepository instance = null;

	UserDaoJDBC userDao;

	private UserRepository()
	{
		userDao = UserDaoJDBC.getInstance();
	}

	public static UserRepository getInstance()
	{
		if (instance == null)
		{
			instance = new UserRepository();
		}
		return instance;
	}

	public UserJDBC createUser()
	{
		// page richtig initialisieren.

		return null;
	}

	public long write(UserJDBC user)
	{
		return userDao.write(user);
		// todo: mit return values arbeiten, richtige seite, oder meldung
		// zurückgeben.

	}

	public UserJDBC read(String name)
	{
		return userDao.read(name);
	}

	public UserJDBC readByActivationKey(String activationKey)
	{
		return userDao.readByActivationKey(activationKey);
	}

	public UserJDBC readBySessionToken(String sessionToken)
	{
		return userDao.readBySessionToken(sessionToken);
	}

	public UserJDBC read(long id)
	{
		return userDao.read(id);
	}

	public void update(UserJDBC user)
	{
		userDao.update(user);
	}

	public void delete(UserJDBC user)
	{
		userDao.delete(user);
	}

	public boolean nameExists(String name)
	{
		return userDao.nameExists(name);
	}

	public boolean emailExists(String email)
	{
		return userDao.emailExists(email);
	}

	public void deleteExpiredActivations()
	{
		userDao.deleteExpiredActivations();
	};
}
