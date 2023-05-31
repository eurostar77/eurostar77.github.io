package com.bueffeltier.data.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InfoBitDaoJDBC extends AbstractDAO<ArticleJDBCFlat>
{
	private static InfoBitDaoJDBC instance = null;

	private InfoBitDaoJDBC()
	{
		super(MaterialTablesDV.INFO_BIT, null);
	}

	public static InfoBitDaoJDBC getInstance()
	{
		if (instance == null)
		{
			instance = new InfoBitDaoJDBC();
		}
		return instance;
	}

	public void write(InfoBit infoBit)
	{
		String sql = "INSERT INTO " + getTable() + " (" //
		    + "info_bit_id" // 1
		    + "info_bit_question, " // 2
		    + "info_bit_answer, " // 3
		    + "info_bit_right_answer_count, " // 4
		    + "info_bit_wrong_answer_count, " // 5
		    + "task_id, " // 6
		    + "task_name, " // 7
		    + "lection_id, " // 8
		    + "lection_name, " // 9
		    + "user_id" // 10
			//
		    + ") VALUES(?,?,?,?,?,?,?,?,?,?)";

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try
		{
			conn = DBUtils.getConnection();
			conn.setAutoCommit(false);

			ps = conn.prepareStatement(sql);

			setInfoBitDataToPsExclId(ps, infoBit);

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

	public InfoBit read(long id)
	{
		InfoBit infoBit = null;

		String sql = "SELECT * FROM " + getTable() + " WHERE info_bit_id = ?";

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

			infoBit = new InfoBit();

			while (rs.next())
			{
				writeResultSetDataToInfoBit(infoBit, rs);
			}

			conn.commit();

		} catch (Exception e)
		{
			rollbackTransaction(conn, null);

		} finally
		{
			finalClosings(rs, ps, conn);
		}

		return infoBit;
	}

//	public List<InfoBit> read(ArticleJDBCFlat article, Connection conn)
//	    throws SQLException
//	{
//		List<InfoBit> infoBits = new ArrayList<>();
//
//		String sql = "SELECT * FROM " + getTable()
//		    + " WHERE parent_id = ? ORDER BY sorting ASC";
//
//		PreparedStatement ps = conn.prepareStatement(sql);
//
//		ps.setLong(1, article.getId());
//
//		ResultSet rs = ps.executeQuery();
//
//		InfoBit infoBit;
//
//		while (rs.next())
//		{
//			infoBit = new InfoBit();
//			writeResultSetDataToInfoBit(infoBit, rs);
//			infoBits.add(infoBit);
//		}
//
//		return infoBits;
//	}

	public void update(InfoBit infoBit)
	{
		String sql = "UPDATE " + getTable() + " SET "//
		    + "info_bit_question=?, " // 1
		    + "info_bit_answer=?, " // 2
		    + "info_bit_right_answer_count=?, " // 3
		    + "info_bit_wrong_answer_count=?, " // 4
		    + "task_id=?, " // 5
		    + "task_name=?, " // 6
		    + "lection_id=?, " // 7
		    + "lection_name=?, " // 8
		    + "user_id=?" // 9

		    + " where info_bit_id=?;";

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try
		{
			conn = DBUtils.getConnection();
			conn.setAutoCommit(false);

			ps = conn.prepareStatement(sql);

			setInfoBitDataToPsExclId(ps, infoBit);

			ps.setLong(10, infoBit.getInfoBitId()); // 10 //////////////////////

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

	public void delete(InfoBit infoBit)
	{
		String sql = "delete from " + getTable() + " where info_bit_id=?";

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try
		{
			conn = DBUtils.getConnection();
			conn.setAutoCommit(false);

			ps = conn.prepareStatement(sql);

			ps.setLong(1, infoBit.getInfoBitId());

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

	public List<InfoBit> readAll()
	{
		List<InfoBit> infoBits = new ArrayList<>();

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
				InfoBit infoBit = new InfoBit();

				writeResultSetDataToInfoBit(infoBit, rs);

				infoBits.add(infoBit);
			}

			conn.commit();

		} catch (Exception e)
		{
			rollbackTransaction(conn, null);

		} finally
		{
			finalClosings(rs, ps, conn);
		}

		return infoBits;
	}

	private PreparedStatement
	    setInfoBitDataToPsExclId(PreparedStatement ps, InfoBit infoBit)
	        throws SQLException
	{
		ps.setString(2, infoBit.getInfoBitQuestion()); // 1
		ps.setString(3, infoBit.getInfoBitQuestion()); // 2
		ps.setInt(4, infoBit.getInfoBitRightAnswerCount()); // 3
		ps.setInt(5, infoBit.getInfoBitWrongAnswerCount()); // 4
		ps.setLong(6, infoBit.getTaskId()); // 5
		ps.setString(7, infoBit.getTaskName()); // 6
		ps.setLong(8, infoBit.getLectionId()); // 7
		ps.setString(9, infoBit.getLectionName()); // 8
		ps.setLong(10, infoBit.getUserId()); // 9

		return ps;
	}

//	private PreparedStatement
//	    setInfoBitDataToPsInclId(PreparedStatement ps, InfoBit infoBit)
//	        throws SQLException
//	{
//		ps.setLong(1, infoBit.getInfoBitId()); // 1
//		ps.setString(2, infoBit.getInfoBitQuestion()); // 2
//		ps.setString(3, infoBit.getInfoBitQuestion()); // 3
//		ps.setInt(4, infoBit.getInfoBitRightAnswerCount()); // 4
//		ps.setInt(5, infoBit.getInfoBitWrongAnswerCount()); // 5
//		ps.setLong(6, infoBit.getTaskId()); // 6
//		ps.setString(7, infoBit.getTaskName()); // 7
//		ps.setLong(8, infoBit.getLectionId()); // 8
//		ps.setString(9, infoBit.getLectionName()); // 9
//		ps.setLong(10, infoBit.getUserId()); // 10

//		if (infoBit.getShowFrom() == null)
//		{
//			ps.setNull(13, Types.TIMESTAMP);
//
//		} else
//		{
//			ps.setTimestamp(13, Timestamp.valueOf(infoBit.getShowFrom())); // 12
//		}
//
//	return ps;
//
//	}

	private InfoBit writeResultSetDataToInfoBit(InfoBit infoBit, ResultSet rs)
	    throws SQLException
	{
		infoBit.setInfoBitId(rs.getLong("info_bit_id")); // 0
		infoBit.setInfoBitQuestion(rs.getString("info_bit_question")); // 1
		infoBit.setInfoBitAnswer(rs.getString("info_bit_answer")); // 2
		infoBit.setInfoBitRightAnswerCount(
		    rs.getInt("info_bit_right_answer_count")
		); // 3
		infoBit.setInfoBitWrongAnswerCount(
		    rs.getInt("info_bit_wrong_answer_count")
		); // 4
		infoBit.setTaskId(rs.getLong("task_id")); // 5
		infoBit.setTaskName(rs.getString("task_name")); // 6
		infoBit.setLectionId(rs.getLong("lection_id")); // 7
		infoBit.setLectionName(rs.getString("lection_name")); // 8
		infoBit.setUserId(rs.getLong("user_id")); // 9

//		if (rs.getTimestamp("show_from") == null)
//		{
//			infoBit.setShowFrom(null);
//
//		} else
//		{
//			infoBit.setShowFrom(rs.getTimestamp("show_from").toLocalDateTime()); // 12
//		}

		return infoBit;
	}

//	public List<InfoBit>
//	    mapinfoBitsToArticle(ArticleJDBCFlat article, Connection conn)
//	        throws SQLException
//	{
//		List<InfoBit> infoBits = article.getinfoBits();
//
//		if (infoBits == null)
//		{
//			return null;
//		}
//
//		if (infoBits.isEmpty())
//		{
//			return null;
//		}
//
//		PreparedStatement psInsertUpdate;
//		String sqlInsertUpdate = "INSERT INTO " + getTable() + " ("//
//		    + "id, " // 1
//		    + "parent_id, " // 2
//		    + "sorting, " // 3
//		    + "type, " // 4
//		    + "is_dynamic, " // 5
//		    + "html, " // 6
//		    + "headline_type, " // 7
//		    + "headline_text, " // 8
//		    + "guests_only, " // 9
//		    + "css_id, " // 10
//		    + "css_class, " // 11
//		    + "hide, " // 12
//		    + "show_from, " // 13
//		    + "show_until" // 14
//			//
//		    + ") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?) " //
//		    + "ON DUPLICATE KEY UPDATE " //
//		    + "parent_id = VALUES(parent_id), " // 1
//		    + "sorting = VALUES(sorting), " // 2
//		    + "type = VALUES(type), " // 3
//		    + "is_dynamic = VALUES(is_dynamic), " // 4
//		    + "html = VALUES(html), " // 5
//		    + "headline_type = VALUES(headline_type), " // 6
//		    + "headline_text = VALUES(headline_text), " // 7
//		    + "guests_only = VALUES(guests_only), " // 8
//		    + "css_id = VALUES(css_id), " // 9
//		    + "css_class = VALUES(css_class), " // 10
//		    + "hide = VALUES(hide), " // 11
//		    + "show_from = VALUES(show_from), " // 12
//		    + "show_until = VALUES(show_until)" // 13
//		    + ";";
//
//		psInsertUpdate = conn
//		    .prepareStatement(sqlInsertUpdate, Statement.RETURN_GENERATED_KEYS);
//
//		StringBuilder infoBitsNoDelete = new StringBuilder();
//
//		for (int i = 0; i < infoBits.size(); i++)
//		{
//			setInfoBitDataToPsInclId(psInsertUpdate, infoBits.get(i));
//
//			int affectedRows = psInsertUpdate.executeUpdate();
//			// TODO sveng 05.11.2022: affected rows nutzen?
//
//			ResultSet rs = psInsertUpdate.getGeneratedKeys();
//
//			while (rs.next())
//			{
//				infoBits.get(i).setId(rs.getLong(1));
//			}
//
//			infoBitsNoDelete.append(infoBits.get(i).getId());
//
//			if (i < infoBits.size() - 1)
//			{
//				infoBitsNoDelete.append(", ");
//			}
//		}
//
//		PreparedStatement psDelete;
//
//		String sqlDeleteString = //
//		    "DELETE FROM " + getTable() + " "
//		        + "WHERE parent_id=?  AND id NOT IN ("
//		        + infoBitsNoDelete.toString() + ")";
//
//		psDelete = conn.prepareStatement(sqlDeleteString);
//		psDelete.setLong(1, article.getId());
//
//		psDelete.execute();
//
//		return infoBits;
//	}

//	public void
//	    unmapinfoBitsFromArticle(ArticleJDBCFlat article, Connection conn)
//	        throws SQLException
//	{
//		String sql = "delete from " + getTable() + " where parent_id=?";
//
//		conn.setAutoCommit(false);
//
//		PreparedStatement ps = conn.prepareStatement(sql);
//
//		ps.setLong(1, article.getId());
//
//		ps.executeUpdate();
//	}
}
