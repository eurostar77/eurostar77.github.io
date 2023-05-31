package com.bueffeltier.data.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.mariadb.jdbc.Statement;

public class ElementDaoJDBCFlatImpl extends AbstractDAO<ArticleJDBCFlat>
{
	private static ElementDaoJDBCFlatImpl instance = null;

	private ElementDaoJDBCFlatImpl()
	{
		super(MaterialTablesDV.ELEMENT, null);
	}

	public static ElementDaoJDBCFlatImpl getInstance()
	{
		if (instance == null)
		{
			instance = new ElementDaoJDBCFlatImpl();
		}
		return instance;
	}

	public void write(ElementJDBCFlat element)
	{
		String sql = "INSERT INTO " + getTable() + " (" //
				+ "parent_id, " // 1
				+ "sorting, " // 2
				+ "type, " // 3
				+ "is_dynamic, " // 4
				+ "html, " // 5
				+ "headline_type, " // 6
				+ "headline_text, " // 7
				+ "guests_only, " // 8
				+ "css_id, " // 9
				+ "css_class, " // 10
				+ "hide, " // 11
				+ "show_from, " // 12
				+ "show_until" // 13
				//
				+ ") VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try
		{
			conn = DBUtils.getConnection();
			conn.setAutoCommit(false);

			ps = conn.prepareStatement(sql);

			setElementDataToPsExclId(ps, element);

			ps.execute();

			conn.commit();

		} catch (Exception e)
		{
			rollbackTransaction(conn, null);

		} finally
		{
			finalClosings(rs, ps, conn);
		}
	}

	public ElementJDBCFlat read(long id)
	{
		ElementJDBCFlat element = null;

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

			element = new ElementJDBCFlat();

			while (rs.next())
			{
				writeResultSetDataToElement(element, rs);
			}

			conn.commit();

		} catch (Exception e)
		{
			rollbackTransaction(conn, null);

		} finally
		{
			finalClosings(rs, ps, conn);
		}

		return element;
	}

	public List<ElementJDBCFlat> read(ArticleJDBCFlat article, Connection conn)
			throws SQLException
	{
		List<ElementJDBCFlat> elements = new ArrayList<>();

		String sql = "SELECT * FROM " + getTable()
				+ " WHERE parent_id = ? ORDER BY sorting ASC";

		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setLong(1, article.getId());

		ResultSet rs = ps.executeQuery();

		ElementJDBCFlat element;

		while (rs.next())
		{
			element = new ElementJDBCFlat();
			writeResultSetDataToElement(element, rs);
			elements.add(element);
		}

		return elements;
	}

	public void update(ElementJDBCFlat element)
	{
		String sql = "UPDATE " + getTable() + " SET "//
				+ "parent_id=?, " // 1
				+ "sorting=?, " // 2
				+ "type=?, " // 3
				+ "is_dynamic=?, " // 4
				+ "html=?, " // 5
				+ "headline_type=?, " // 6
				+ "headline_text=?, " // 7
				+ "guests_only=?, " // 8
				+ "css_id=?, " // 9
				+ "css_Class=?, " // 10
				+ "hide=?, " // 11
				+ "show_from=?, " // 12
				+ "show_until=?" // 13

				+ " where id=?;";

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try
		{
			conn = DBUtils.getConnection();
			conn.setAutoCommit(false);

			ps = conn.prepareStatement(sql);

			setElementDataToPsExclId(ps, element);

			ps.setLong(14, element.getId()); // 14 /////////////////////////////

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

	public void delete(ElementJDBCFlat element)
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

			ps.setLong(1, element.getId());

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

	public List<ElementJDBCFlat> readAll()
	{
		List<ElementJDBCFlat> elements = new ArrayList<>();

		String sql = "SELECT * FROM " + getTable();

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
				ElementJDBCFlat element = new ElementJDBCFlat();

				writeResultSetDataToElement(element, rs);

				elements.add(element);
			}

			conn.commit();

		} catch (Exception e)
		{
			rollbackTransaction(conn, null);

		} finally
		{
			finalClosings(rs, ps, conn);
		}

		return elements;
	}

	private PreparedStatement setElementDataToPsExclId(
			PreparedStatement ps,
			ElementJDBCFlat element
	) throws SQLException
	{
		ps.setLong(1, element.getParentId()); // 1
		ps.setInt(2, element.getSorting()); // 2
		ps.setString(3, element.getType()); // 3
		ps.setBoolean(4, element.isDynamicHtml()); // 4
		ps.setString(5, element.getHtml()); // 5
		ps.setInt(6, element.getHeadlineType()); // 6
		ps.setString(7, element.getHeadlineText()); // 7
		ps.setBoolean(8, element.guestsOnly()); // 8
		ps.setString(9, element.getCssId()); // 9
		ps.setString(10, element.getCssClass()); // 10
		ps.setBoolean(11, element.isHidden()); // 11
		ps.setObject(12, element.getShowFrom()); // 12
		ps.setObject(13, element.getShowUntil()); // 13

		return ps;
	}

	private PreparedStatement setElementDataToPsInclId(
			PreparedStatement ps,
			ElementJDBCFlat element
	) throws SQLException
	{
		ps.setLong(1, element.getId()); // 0
		ps.setLong(2, element.getParentId()); // 1
		ps.setInt(3, element.getSorting()); // 2
		ps.setString(4, element.getType()); // 3
		ps.setBoolean(5, element.isDynamicHtml()); // 4
		ps.setString(6, element.getHtml()); // 5
		ps.setInt(7, element.getHeadlineType()); // 6
		ps.setString(8, element.getHeadlineText()); // 7
		ps.setBoolean(9, element.guestsOnly()); // 8
		ps.setString(10, element.getCssId()); // 9
		ps.setString(11, element.getCssClass()); // 10
		ps.setBoolean(12, element.isHidden()); // 11

		if (element.getShowFrom() == null)
		{
			ps.setNull(13, Types.TIMESTAMP);

		} else
		{
			ps.setTimestamp(13, Timestamp.valueOf(element.getShowFrom())); // 12
		}

		if (element.getShowUntil() == null)
		{
			ps.setNull(14, Types.TIMESTAMP);

		} else
		{
			ps.setTimestamp(14, Timestamp.valueOf(element.getShowUntil())); // 13
		}

		return ps;
	}

	private ElementJDBCFlat
			writeResultSetDataToElement(ElementJDBCFlat element, ResultSet rs)
					throws SQLException
	{
		element.setId(rs.getLong("id")); // 0
		element.setParentId(rs.getLong("parent_id")); // 1
		element.setSorting(rs.getInt("sorting")); // 2
		element.setType(rs.getString("type")); // 3
		element.setDynamicHtml(rs.getBoolean("is_dynamic")); // 4
		element.setHtml(rs.getString("html")); // 5
		element.setHeadlineType(rs.getInt("headline_type")); // 6
		element.setHeadlineText(rs.getString("headline_text")); // 7
		element.setGuestsOnly(rs.getBoolean("guests_only")); // 8
		element.setCssId(rs.getString("css_id")); // 9
		element.setCssClass(rs.getString("css_class")); // 10
		element.setHidden(rs.getBoolean("hide")); // 11

		if (rs.getTimestamp("show_from") == null)
		{
			element.setShowFrom(null);

		} else
		{
			element.setShowFrom(rs.getTimestamp("show_from").toLocalDateTime()); // 12
		}

		if (rs.getTimestamp("show_from") == null)
		{
			element.setShowUntil(null);

		} else
		{
			element.setShowUntil(
					rs.getTimestamp("show_until").toLocalDateTime()
			); // 13
		}

		return element;
	}

	public List<ElementJDBCFlat>
			mapElementsToArticle(ArticleJDBCFlat article, Connection conn)
					throws SQLException
	{
		List<ElementJDBCFlat> elements = article.getElements();

		if (elements == null)
		{
			return null;
		}

		if (elements.isEmpty())
		{
			return null;
		}

		PreparedStatement psInsertUpdate;
		String sqlInsertUpdate = "INSERT INTO " + getTable() + " ("//
				+ "id, " // 1
				+ "parent_id, " // 2
				+ "sorting, " // 3
				+ "type, " // 4
				+ "is_dynamic, " // 5
				+ "html, " // 6
				+ "headline_type, " // 7
				+ "headline_text, " // 8
				+ "guests_only, " // 9
				+ "css_id, " // 10
				+ "css_class, " // 11
				+ "hide, " // 12
				+ "show_from, " // 13
				+ "show_until" // 14
				//
				+ ") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?) " //
				+ "ON DUPLICATE KEY UPDATE " //
				+ "parent_id = VALUES(parent_id), " // 1
				+ "sorting = VALUES(sorting), " // 2
				+ "type = VALUES(type), " // 3
				+ "is_dynamic = VALUES(is_dynamic), " // 4
				+ "html = VALUES(html), " // 5
				+ "headline_type = VALUES(headline_type), " // 6
				+ "headline_text = VALUES(headline_text), " // 7
				+ "guests_only = VALUES(guests_only), " // 8
				+ "css_id = VALUES(css_id), " // 9
				+ "css_class = VALUES(css_class), " // 10
				+ "hide = VALUES(hide), " // 11
				+ "show_from = VALUES(show_from), " // 12
				+ "show_until = VALUES(show_until)" // 13
				+ ";";

		psInsertUpdate = conn.prepareStatement(
				sqlInsertUpdate, Statement.RETURN_GENERATED_KEYS
		);

		StringBuilder elementsNoDelete = new StringBuilder();

		for (int i = 0; i < elements.size(); i++)
		{
			setElementDataToPsInclId(psInsertUpdate, elements.get(i));

			int affectedRows = psInsertUpdate.executeUpdate();
			// TODO sveng 05.11.2022: affected rows nutzen?

			ResultSet rs = psInsertUpdate.getGeneratedKeys();

			while (rs.next())
			{
				elements.get(i).setId(rs.getLong(1));
			}

			elementsNoDelete.append(elements.get(i).getId());

			if (i < elements.size() - 1)
			{
				elementsNoDelete.append(", ");
			}
		}

		PreparedStatement psDelete;

		String sqlDeleteString = //
				"DELETE FROM " + getTable() + " "
						+ "WHERE parent_id=?  AND id NOT IN ("
						+ elementsNoDelete.toString() + ")";

		psDelete = conn.prepareStatement(sqlDeleteString);
		psDelete.setLong(1, article.getId());

		psDelete.execute();

		return elements;
	}

	public void
			unmapElementsFromArticle(ArticleJDBCFlat article, Connection conn)
					throws SQLException
	{
		String sql = "delete from " + getTable() + " where parent_id=?";

		conn.setAutoCommit(false);

		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setLong(1, article.getId());

		ps.executeUpdate();
	}
}
