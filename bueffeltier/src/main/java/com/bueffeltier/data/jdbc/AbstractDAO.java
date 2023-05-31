package com.bueffeltier.data.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Superklasse für Dao-Implementations. Implementiert das DAO-Pattern, die
 * SQL-Exceptionbehandlung und das Schließen der DB-Connections.
 */
public abstract class AbstractDAO<T> // TODO sveng 01.11.2022: hier generics?
{
	private MaterialTablesDV table;
	private MaterialTablesDV mappingTableOpt;

	protected AbstractDAO(
			final MaterialTablesDV table, final MaterialTablesDV mappingTableOpt
	)
	{
		this.table = table;
		this.mappingTableOpt = mappingTableOpt;
	}

	public MaterialTablesDV getTable()
	{
		return this.table;
	}

	public MaterialTablesDV getMappingTableOpt()
	{
		assert mappingTableOpt != null
				: "Postcondition violated! mappingTableOpt != null"; //$NON-NLS-1$

		return mappingTableOpt;
	}

	public String getTableName()
	{
		return table.getName();
	}

	public String getMappingTableOptName()
	{
		return mappingTableOpt != null ? mappingTableOpt.getName() : "";
	}

	protected void
			rollbackTransaction(final Connection conn, final SQLException e)
	{
		// todo: logging
//        LogUtil.log(Level.SEVERE, e.getMessage());
		if (conn != null)
		{
			try
			{
				conn.rollback();

				conn.close();

			} catch (SQLException ex)
			{
//                LogUtil.log(Level.SEVERE, "Rollback fehlgeschlagen! Application neu starten!");
//                LogUtil.log(Level.SEVERE, ex.getMessage());
				closeConnection(conn);
			}
		}
	}

	protected void closeConnection(final Connection conn)
	{
		try
		{
			conn.close();
		} catch (SQLException e)
		{
//            LogUtil.log(Level.SEVERE, "Das Schließen der JDBC-Connection ist fehlgeschlagen! Application neu starten!");
//            LogUtil.log(Level.SEVERE, e.getMessage());
		}
	}

	protected void finalClosings(PreparedStatement ps, Connection conn)
	{
		finalClosings(null, ps, conn);
	}

	protected void
			finalClosings(ResultSet rs, PreparedStatement ps, Connection conn)
	{
		if (rs != null)
		{
			try
			{
				rs.close();
			} catch (SQLException e)
			{
				// TODO sveng 11.11.2022: logger.error(e.getMessage(),
				// e.fillInStackTrace());
			}
		}

		if (ps != null)
		{
			try
			{
				ps.close();
			} catch (SQLException e)
			{
				// TODO sveng 11.11.2022: logger.error(e.getMessage(),
				// e.fillInStackTrace());
			}
		}

		try
		{
			if (conn != null && !conn.isClosed())
			{
				conn.close();
			}
		} catch (SQLException sqle)
		{
			// TODO sveng 11.11.2022: logger.error(sqle.getMessage(),
			// sqle.fillInStackTrace());
		}
	}
}
