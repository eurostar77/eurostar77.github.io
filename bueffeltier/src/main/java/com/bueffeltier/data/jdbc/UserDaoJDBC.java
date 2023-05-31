package com.bueffeltier.data.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBC extends AbstractDAO<UserJDBC>
{
	private static UserDaoJDBC instance = null;

	private UserDaoJDBC()
	{
		super(MaterialTablesDV.USER, null);
	}

	public static UserDaoJDBC getInstance()
	{
		if (instance == null)
		{
			instance = new UserDaoJDBC();
		}
		return instance;
	}

	public long write(UserJDBC user)
	{
		String sql = "INSERT INTO " + getTable() + " (" //
		    + "name, " // 1
		    + "password, " // 2
		    + "permission, " // 3
		    + "email, " // 4
		    + "is_active, " // 5
		    + "activation_key, " // 6
		    + "session_token, " // 7
		    + "activation_key_experiation_time," // 8
		    + "is_anonymous," // 9
		    + "last_visit_date," // 10
		    + "account_creation_date," // 11
		    + "session_id" // 12

			//
		    + ") VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet generatedKeys = null;
		long generatedId = -1;

		try
		{
			conn = DBUtils.getConnection();
			conn.setAutoCommit(false);

			ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			setUserDataToPsExclId(ps, user);
			ps.execute();

			generatedKeys = ps.getGeneratedKeys();
			if (generatedKeys.next())
			{
				generatedId = generatedKeys.getLong(1); // Assuming the ID
				                                        // column is the first
				                                        // column
			}

			conn.commit();

		} catch (Exception e)
		{
			rollbackTransaction(conn, null);

		} finally
		{
			finalClosings(ps, conn);
		}

		return generatedId;
	}

	public UserJDBC read(String name)
	{
		UserJDBC user = null;

		String sql = "SELECT * FROM " + getTable() + " WHERE name=?";

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try
		{
			conn = DBUtils.getConnection();
			conn.setAutoCommit(false);

			ps = conn.prepareStatement(sql);
			ps.setString(1, name);

			rs = ps.executeQuery();

			user = new UserJDBC();

			while (rs.next())
			{
				writeResultSetDataToUser(user, rs);
			}

			conn.commit();

		} catch (Exception e)
		{
			rollbackTransaction(conn, null);

		} finally
		{
			finalClosings(rs, ps, conn);
		}

		return user;
	}

	public UserJDBC readByActivationKey(String activationKey)
	{
		UserJDBC user = null;

		String sql = "SELECT * FROM " + getTable() + " WHERE activation_key =?";

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try
		{
			conn = DBUtils.getConnection();
			conn.setAutoCommit(false);

			ps = conn.prepareStatement(sql);
			ps.setString(1, activationKey);

			rs = ps.executeQuery();

			user = new UserJDBC();

			while (rs.next())
			{
				writeResultSetDataToUser(user, rs);
			}

			conn.commit();

		} catch (Exception e)
		{
			rollbackTransaction(conn, null);

		} finally
		{
			finalClosings(rs, ps, conn);
		}

		return user;
	}

	public UserJDBC readBySessionToken(String sessionToken)
	{
		UserJDBC user = null;

		String sql = "SELECT * FROM " + getTable() + " WHERE session_token =?";

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try
		{
			conn = DBUtils.getConnection();
			conn.setAutoCommit(false);

			ps = conn.prepareStatement(sql);
			ps.setString(1, sessionToken);

			rs = ps.executeQuery();

			user = new UserJDBC();

			while (rs.next())
			{
				writeResultSetDataToUser(user, rs);
			}

			conn.commit();

		} catch (Exception e)
		{
			rollbackTransaction(conn, null);

		} finally
		{
			finalClosings(rs, ps, conn);
		}

		return user;
	}

	public UserJDBC readBySessionId(String sessionId)
	{
		UserJDBC user = null;

		String sql = "SELECT * FROM " + getTable() + " WHERE session_id =?";

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try
		{
			conn = DBUtils.getConnection();
			conn.setAutoCommit(false);

			ps = conn.prepareStatement(sql);
			ps.setString(1, sessionId);

			rs = ps.executeQuery();

			user = new UserJDBC();

			while (rs.next())
			{
				writeResultSetDataToUser(user, rs);
			}

			conn.commit();

		} catch (Exception e)
		{
			rollbackTransaction(conn, null);

		} finally
		{
			finalClosings(rs, ps, conn);
		}

		return user;
	}

	public UserJDBC read(long id)
	{
		UserJDBC user = null;

		String sql = "SELECT * FROM " + getTable() + " WHERE id = ?";

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try
		{
			conn = DBUtils.getConnection();
			conn.setAutoCommit(false);

			ps = conn.prepareStatement(sql);
			ps.setLong(1, id);

			rs = ps.executeQuery();

			user = new UserJDBC();

			while (rs.next())
			{
				writeResultSetDataToUser(user, rs);
			}

			conn.commit();

		} catch (Exception e)
		{
			rollbackTransaction(conn, null);

		} finally
		{
			finalClosings(rs, ps, conn);
		}

		return user;
	}

	public void update(UserJDBC user)
	{
		String sql = "UPDATE " + getTable() + " SET "//
		    + "name=?, " // 1 UNIQUE
		    + "password=?, " // 2
		    + "permission=?, " // 3
		    + "email=?, " // 4
		    + "is_active=?, " // 5
		    + "activation_key=?, " // 6
		    + "session_token=?, " // 7
		    + "activation_key_experiation_time=?," // 8
		    + "is_anonymous=?," // 9
		    + "last_visit_date=?," // 10
		    + "account_creation_date=?," // 11
		    + "session_id=?" // 12

		    + " where id=?;";

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try
		{
			conn = DBUtils.getConnection();
			conn.setAutoCommit(false);

			ps = conn.prepareStatement(sql);

			setUserDataToPsExclId(ps, user);

			ps.setLong(13, user.getId()); // 13 ////////////////////////////////

			ps.executeUpdate();

			conn.commit();

		} catch (Exception e)
		{
			rollbackTransaction(conn, null);

		} finally
		{
			finalClosings(rs, ps, conn);
		}
	}

	public void delete(UserJDBC user)
	{
		String sql = "delete from " + getTable() + " where id=?";

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try
		{
			conn = DBUtils.getConnection();
			conn.setAutoCommit(false);

			ps = conn.prepareStatement(sql);
			ps.setLong(1, user.getId());

			ps.executeUpdate();

			conn.commit();

		} catch (Exception e)
		{
			rollbackTransaction(conn, null);

		} finally
		{
			finalClosings(rs, ps, conn);
		}
	}

	public List<UserJDBC> readAll()
	{
		List<UserJDBC> users = new ArrayList<>();

		String sql = "SELECT * FROM " + getTable() + " ORDER BY name ASC";

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try
		{
			conn = DBUtils.getConnection();
			conn.setAutoCommit(false);

			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();

			while (rs.next())
			{
				UserJDBC user = new UserJDBC();

				writeResultSetDataToUser(user, rs);

				users.add(user);
			}

			conn.commit();

		} catch (Exception e)
		{
			rollbackTransaction(conn, null);

		} finally
		{
			finalClosings(rs, ps, conn);
		}

		return users;
	}

	private PreparedStatement
	    setUserDataToPsExclId(PreparedStatement ps, UserJDBC user)
	        throws SQLException
	{
		ps.setString(1, user.getName()); // 1
		ps.setString(2, user.getPasswordHash()); // 2
		ps.setInt(3, user.getPermission()); // 3
		ps.setString(4, user.getEmail()); // 4
		ps.setBoolean(5, user.isActive()); // 5
		ps.setString(6, user.getActivationKey()); // 6
		ps.setString(7, user.getSessionToken()); // 7

		if (user.getActivationKeyExpirationTime() == null)
		{
			ps.setNull(8, Types.TIMESTAMP); // 8

		} else
		{
			ps.setTimestamp(
			    8, Timestamp.valueOf(user.getActivationKeyExpirationTime())
			); // 8
		}

		ps.setBoolean(9, user.isAnonymous()); // 9

		if (user.getLastVisitDate() == null)
		{
			ps.setNull(10, Types.TIMESTAMP); // 10

		} else
		{
			ps.setTimestamp(10, Timestamp.valueOf(user.getLastVisitDate())); // 10
		}

		if (user.getAccountCreationDate() == null)
		{
			ps.setNull(11, Types.TIMESTAMP); // 11

		} else
		{
			ps.setTimestamp(
			    11, Timestamp.valueOf(user.getAccountCreationDate())
			); // 11
		}

		ps.setString(12, user.getSessionId()); // 12

		return ps;
	}

	private PreparedStatement
	    setUserDataToPsInclId(PreparedStatement ps, UserJDBC user)
	        throws SQLException
	{
		ps.setLong(1, user.getId()); // 1
		ps.setString(2, user.getName()); // 2
		ps.setString(3, user.getPasswordHash()); // 3
		ps.setInt(4, user.getPermission()); // 4
		ps.setString(5, user.getEmail()); // 5
		ps.setBoolean(6, user.isActive()); // 6
		ps.setString(7, user.getActivationKey()); // 7
		ps.setString(8, user.getSessionToken()); // 8

		if (user.getActivationKeyExpirationTime() == null)
		{
			ps.setNull(9, Types.TIMESTAMP);

		} else
		{
			ps.setTimestamp(
			    9, Timestamp.valueOf(user.getActivationKeyExpirationTime())
			); // 9
		}

		ps.setBoolean(10, user.isAnonymous()); // 10

		if (user.getLastVisitDate() == null)
		{
			ps.setNull(11, Types.TIMESTAMP); // 11

		} else
		{
			ps.setTimestamp(11, Timestamp.valueOf(user.getLastVisitDate())); // 11
		}

		if (user.getAccountCreationDate() == null)
		{
			ps.setNull(12, Types.TIMESTAMP); // 12

		} else
		{
			ps.setTimestamp(
			    12, Timestamp.valueOf(user.getAccountCreationDate())
			); // 12
		}

		ps.setString(7, user.getSessionId()); // 13

		return ps;
	}

	private UserJDBC writeResultSetDataToUser(UserJDBC user, ResultSet rs)
	    throws SQLException
	{
		user.setId(rs.getLong("id")); // 1
		user.setName(rs.getString("name")); // 2
		user.setPasswordHash(rs.getString("password")); // 3
		user.setPermission(rs.getInt("permission")); // 4
		user.setEmail(rs.getString("email")); // 5
		user.setActive(rs.getBoolean("is_active")); // 6
		user.setActivationKey(rs.getString("activation_key")); // 7
		user.setSessionToken(rs.getString("session_token")); // 8

		if (rs.getTimestamp("activation_key_experiation_time") == null)
		{
			user.setActivationKeyExpirationTime(null); // 9

		} else
		{
			user.setActivationKeyExpirationTime(
			    rs.getTimestamp("activation_key_experiation_time")
			        .toLocalDateTime()
			); // 9
		}

		user.setAnonymous(rs.getBoolean("is_anonymous")); // 10

		if (rs.getTimestamp("last_visit_date") == null)
		{
			user.setLastVisitDate(null); // 11

		} else
		{
			user.setLastVisitDate(

			    rs.getTimestamp("last_visit_date").toLocalDateTime()
			); // 11
		}

		if (rs.getTimestamp("account_creation_date") == null)
		{
			user.setAccountCreationDate(null); // 12

		} else
		{
			user.setAccountCreationDate(

			    rs.getTimestamp("account_creation_date").toLocalDateTime()
			); // 12
		}

		user.setSessionId(rs.getString("session_id")); // 13

		return user;
	}

	public boolean nameExists(String name)
	{
		boolean nameExists = false;

		String sql = "SELECT * FROM " + getTable() + " WHERE name=?";

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try
		{
			conn = DBUtils.getConnection();
			conn.setAutoCommit(false);

			ps = conn.prepareStatement(sql);
			ps.setString(1, name);

			rs = ps.executeQuery();

			if (rs.next())
			{
				nameExists = true;
			}

			conn.commit();

		} catch (Exception e)
		{
			rollbackTransaction(conn, null);

		} finally
		{
			finalClosings(rs, ps, conn);
		}

		return nameExists;
	}

	public boolean emailExists(String email)
	{
		boolean emailExists = false;

		String sql = "SELECT * FROM " + getTable() + " WHERE email=?";

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try
		{
			conn = DBUtils.getConnection();
			conn.setAutoCommit(false);

			ps = conn.prepareStatement(sql);
			ps.setString(1, email);

			rs = ps.executeQuery();

			if (rs.next())
			{
				emailExists = true;
			}

			conn.commit();

		} catch (Exception e)
		{
			rollbackTransaction(conn, null);

		} finally
		{
			finalClosings(rs, ps, conn);
		}

		return emailExists;
	}

	public void deleteExpiredActivations()
	{
		// TODO sveng 28.01.2023: return value?
		String sql = "DELETE FROM " + getTable()
		    + " WHERE activation_key_experiation_time <=? AND activation_key IS NOT NULL AND activation_key <> '')";

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try
		{
			conn = DBUtils.getConnection();
			conn.setAutoCommit(false);

			ps = conn.prepareStatement(sql);
			ps.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));

			ps.executeUpdate();

			conn.commit();

		} catch (Exception e)
		{
			rollbackTransaction(conn, null);

		} finally
		{
			finalClosings(rs, ps, conn);
		}
	}
}
