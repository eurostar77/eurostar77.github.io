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

public class ArticleDaoJDBCFlatImpl extends AbstractDAO<ArticleJDBCFlat>
{
	private static ArticleDaoJDBCFlatImpl instance = null;

	private ElementDaoJDBCFlatImpl elementDao;

	private ArticleDaoJDBCFlatImpl()
	{
		super(MaterialTablesDV.ARTICLE, null);

		this.elementDao = ElementDaoJDBCFlatImpl.getInstance();
	}

	public static ArticleDaoJDBCFlatImpl getInstance()
	{
		if (instance == null)
		{
			instance = new ArticleDaoJDBCFlatImpl();
		}
		return instance;
	}

	public void write(ArticleJDBCFlat article)
	{
		String sql = "INSERT INTO " + getTable() + " (" //
				+ "parent_id, " // 1
				+ "sorting, " // 2
				+ "title, " // 3 UNIQUE
				+ "author, " // 4
				+ "teaser_text, " // 5
				+ "teaser_image_path, " // 6
				+ "show_teaser, " // 7
				+ "last_edit_date, " // 8
				+ "is_published, " // 9
				+ "keywords, " // 10
				+ "teaser_css_class, " // 11
				+ "teaser_css_id, " // 12
				+ "css_class, " // 13
				+ "css_id, " // 14
				+ "container_tag, " // 15
				+ "create_time, " // 16
				+ "show_from, " // 17
				+ "show_until, " // 18
				+ "layout_column," // 19
				+ "page_path" // 20

				//
				+ ") VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		Connection conn = null;
		PreparedStatement ps = null;

		try
		{
			conn = DBUtils.getConnection();
			conn.setAutoCommit(false);

			ps = conn.prepareStatement(sql);
			setArticleDataToPsExclId(ps, article);
			ps.execute();

			elementDao.mapElementsToArticle(article, conn);

			conn.commit();

		} catch (Exception e)
		{
			rollbackTransaction(conn, null);

		} finally
		{
			finalClosings(ps, conn);
		}
	}

	public ArticleJDBCFlat read(String title)
	{
		ArticleJDBCFlat article = null;

		String sql = "SELECT * FROM " + getTable() + " WHERE title=?";

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try
		{
			conn = DBUtils.getConnection();
			conn.setAutoCommit(false);

			ps = conn.prepareStatement(sql);
			ps.setString(1, title);

			rs = ps.executeQuery();

			article = new ArticleJDBCFlat();

			while (rs.next())
			{
				writeResultSetDataToArticle(article, rs);
			}

			article.setElements(elementDao.read(article, conn));

			conn.commit();

		} catch (Exception e)
		{
			rollbackTransaction(conn, null);

		} finally
		{
			finalClosings(rs, ps, conn);
		}

		return article;
	}

	public ArticleJDBCFlat read(long id)
	{
		ArticleJDBCFlat article = null;

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

			article = new ArticleJDBCFlat();

			while (rs.next())
			{
				writeResultSetDataToArticle(article, rs);
			}

			article.setElements(elementDao.read(article, conn));

			conn.commit();

		} catch (Exception e)
		{
			rollbackTransaction(conn, null);

		} finally
		{
			finalClosings(rs, ps, conn);
		}

		return article;
	}

	public List<ArticleJDBCFlat> readArticles(long id)
	{
		List<ArticleJDBCFlat> articles = new ArrayList<>();

		String sql = "SELECT * FROM " + getTable()
				+ " WHERE parent_id = ? ORDER BY sorting ASC";

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

			ArticleJDBCFlat article;

			while (rs.next())
			{
				article = new ArticleJDBCFlat();

				writeResultSetDataToArticle(article, rs);

				article.setElements(elementDao.read(article, conn));

				articles.add(article);
			}

			conn.commit();

		} catch (Exception e)
		{
			rollbackTransaction(conn, null);

		} finally
		{
			finalClosings(rs, ps, conn);
		}

		return articles;
	}

	public List<ArticleJDBCFlat>
			readMainArticles(PageJDBCFlat page, Connection conn)
					throws SQLException
	{
		List<ArticleJDBCFlat> articles = new ArrayList<>();

		String sql = "SELECT * FROM " + getTable()
				+ " WHERE parent_id = ? ORDER BY sorting ASC";

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setLong(1, page.getId());

		ResultSet rs = ps.executeQuery();

		ArticleJDBCFlat article;

		while (rs.next())
		{
			article = new ArticleJDBCFlat();

			writeResultSetDataToArticle(article, rs);

			article.setElements(elementDao.read(article, conn));

			articles.add(article);
		}

		return articles;
	}

	public void update(ArticleJDBCFlat article)
	{
		String sql = "UPDATE " + getTable() + " SET "//
				+ "parent_id=?, " // 1
				+ "sorting=?, " // 2
				+ "title=?, " // 3 UNIQUE
				+ "author=?, " // 4
				+ "teaser_text=?, " // 5
				+ "teaser_image_path=?, " // 6
				+ "show_teaser=?, " // 7
				+ "last_edit_date=?, " // 8
				+ "is_published=?, " // 9
				+ "keywords=?, " // 10
				+ "teaser_css_class=?, " // 11
				+ "teaser_css_id=?, " // 12
				+ "css_class=?, " // 13
				+ "css_id=?, " // 14
				+ "container_tag=?, " // 15
				+ "create_time=?, " // 16
				+ "show_from=?, " // 17
				+ "show_until=?, " // 18
				+ "layout_column=?, " // 19
				+ "page_path=?" // 20

				+ " where id=?;";

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try
		{
			conn = DBUtils.getConnection();
			conn.setAutoCommit(false);

			ps = conn.prepareStatement(sql);

			setArticleDataToPsExclId(ps, article);

			ps.setLong(21, article.getId()); // 21 /////////////////////////////

			ps.executeUpdate();

			elementDao.mapElementsToArticle(article, conn);

			conn.commit();

		} catch (Exception e)
		{
			rollbackTransaction(conn, null);

		} finally
		{
			finalClosings(rs, ps, conn);
		}
	}

	public void delete(ArticleJDBCFlat article)
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
			ps.setLong(1, article.getId());

			ps.executeUpdate();

			elementDao.unmapElementsFromArticle(article, conn);

			conn.commit();

		} catch (Exception e)
		{
			rollbackTransaction(conn, null);

		} finally
		{
			finalClosings(rs, ps, conn);
		}
	}

	public List<ArticleJDBCFlat> readAll()
	{
		List<ArticleJDBCFlat> articles = new ArrayList<>();

		String sql = "SELECT * FROM " + getTable() + " ORDER BY title ASC";

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
				ArticleJDBCFlat article = new ArticleJDBCFlat();

				writeResultSetDataToArticle(article, rs);

				article.setElements(elementDao.read(article, conn));

				articles.add(article);
			}

			conn.commit();

		} catch (Exception e)
		{
			rollbackTransaction(conn, null);

		} finally
		{
			finalClosings(rs, ps, conn);
		}

		return articles;
	}

	private PreparedStatement setArticleDataToPsExclId(
			PreparedStatement ps,
			ArticleJDBCFlat article
	) throws SQLException
	{
		ps.setLong(1, article.getParentId()); // 1
		ps.setInt(2, article.getSorting()); // 2
		ps.setString(3, article.getTitle()); // 3
		ps.setString(4, article.getAuthor()); // 4
		ps.setString(5, article.getTeaserText()); // 5
		ps.setString(6, article.getTeaserImagePath()); // 6
		ps.setBoolean(7, article.showTeaser()); // 7

		if (article.getLastEditDate() == null)
			ps.setNull(8, Types.TIMESTAMP);
		else
		{
			ps.setTimestamp(8, Timestamp.valueOf(article.getLastEditDate())); // 8
		}

		ps.setBoolean(9, article.isPublished()); // 9
		ps.setString(10, article.getKeywords()); // 10
		ps.setString(11, article.getTeaserCssClass()); // 11
		ps.setString(12, article.getTeaserCssId()); // 12
		ps.setString(13, article.getCssClass()); // 13
		ps.setString(14, article.getCssId()); // 14
		ps.setString(15, article.getContainerTag()); // 15

		if (article.getCreateTime() == null)
			ps.setNull(16, Types.TIMESTAMP);
		else
		{
			ps.setTimestamp(16, Timestamp.valueOf(article.getCreateTime())); // 16
		}

		if (article.getShowFrom() == null)
			ps.setNull(17, Types.TIMESTAMP);
		else
		{
			ps.setTimestamp(17, Timestamp.valueOf(article.getShowFrom())); // 17
		}

		if (article.getShowUntil() == null)
			ps.setNull(18, Types.TIMESTAMP);
		else
		{
			ps.setTimestamp(18, Timestamp.valueOf(article.getShowUntil())); // 18
		}

		ps.setString(19, article.getLayoutColumn()); // 19
		ps.setString(20, article.getPagePath()); // 20

		return ps;
	}

	private PreparedStatement setArticleDataToPsInclId(
			PreparedStatement ps,
			ArticleJDBCFlat article
	) throws SQLException
	{
		ps.setLong(1, article.getId()); // 1
		ps.setLong(2, article.getParentId()); // 2
		ps.setInt(3, article.getSorting()); // 3
		ps.setString(4, article.getTitle()); // 4
		ps.setString(5, article.getAuthor()); // 5
		ps.setString(6, article.getTeaserText()); // 6
		ps.setString(7, article.getTeaserImagePath()); // 7
		ps.setBoolean(8, article.showTeaser()); // 8
		ps.setObject(9, article.getLastEditDate()); // 9
		ps.setBoolean(10, article.isPublished()); // 10
		ps.setString(11, article.getKeywords()); // 11
		ps.setString(12, article.getTeaserCssClass()); // 12
		ps.setString(13, article.getTeaserCssId()); // 13
		ps.setString(14, article.getCssClass()); // 14
		ps.setString(15, article.getCssId()); // 15
		ps.setString(16, article.getContainerTag()); // 16

		if (article.getCreateTime() == null)
		{
			ps.setNull(17, Types.TIMESTAMP);

		} else
		{
			ps.setTimestamp(17, Timestamp.valueOf(article.getCreateTime())); // 17
		}

		if (article.getShowFrom() == null)
		{
			ps.setNull(18, Types.TIMESTAMP);

		} else
		{
			ps.setTimestamp(18, Timestamp.valueOf(article.getShowFrom())); // 18
		}

		if (article.getShowUntil() == null)
		{
			ps.setNull(19, Types.TIMESTAMP);

		} else
		{
			ps.setTimestamp(19, Timestamp.valueOf(article.getShowUntil())); // 19
		}

		ps.setString(20, article.getLayoutColumn()); // 20
		ps.setString(21, article.getPagePath()); // 21

		return ps;
	}

	private ArticleJDBCFlat
			writeResultSetDataToArticle(ArticleJDBCFlat article, ResultSet rs)
					throws SQLException
	{
		article.setId(rs.getLong("id"));
		article.setParentId(rs.getLong("parent_id")); // 1
		article.setSorting(rs.getInt("sorting")); // 2
		article.setTitle(rs.getString("title")); // 3
		article.setAuthor(rs.getString("author")); // 4
		article.setTeaserText(rs.getString("teaser_text")); // 5
		article.setTeaserImagePath(rs.getString("teaser_image_path")); // 6
		article.setShowTeaser(rs.getBoolean("show_teaser")); // 7

		if (rs.getTimestamp("last_edit_date") == null)
		{
			article.setLastEditDate(null);

		} else
		{
			article.setLastEditDate(
					rs.getTimestamp("last_edit_date").toLocalDateTime()
			); // 8
		}

		article.setPublished(rs.getBoolean("is_published")); // 9
		article.setKeywords(rs.getString("keywords")); // 10
		article.setTeaserCssClass(rs.getString("teaser_css_class")); // 11
		article.setTeaserCssId(rs.getString("teaser_css_id")); // 12
		article.setCssClass(rs.getString("css_class")); // 13
		article.setCssId(rs.getString("css_id")); // 14
		article.setContainerTag(rs.getString("container_tag")); // 15

		if (rs.getTimestamp("create_time") == null)
		{
			article.setCreateTime(null);

		} else
		{
			article.setCreateTime(
					rs.getTimestamp("create_time").toLocalDateTime()
			); // 16
		}

		if (rs.getTimestamp("show_from") == null)
		{
			article.setShowFrom(null);

		} else
		{
			article.setShowFrom(rs.getTimestamp("show_from").toLocalDateTime()); // 17
		}

		if (rs.getTimestamp("show_until") == null)
		{
			article.setShowFrom(null);

		} else
		{
			article.setShowUntil(
					rs.getTimestamp("show_until").toLocalDateTime()
			); // 18
		}

		article.setLayoutColumn(rs.getString("layout_column")); // 19
		article.setPagePath(rs.getString("page_path")); // 20

		return article;
	}

	public List<ArticleJDBCFlat>
			mapArticlesToPage(PageJDBCFlat page, Connection conn)
					throws SQLException
	{
		List<ArticleJDBCFlat> articles = new ArrayList<>();

		articles.addAll(page.getMainArticles());

		if (articles == null || articles.isEmpty())
		{
			return null;
		}

		PreparedStatement psInsertUpdate;
		String sqlInsertUpdate = "INSERT INTO " + getTable() + " ("//
				+ "id, " // 1
				+ "parent_id, " // 2
				+ "sorting, " // 3
				+ "title, " // 4
				+ "author, " // 5
				+ "teaser_text, " // 6
				+ "teaser_image_path, " // 7
				+ "show_teaser, " // 8
				+ "last_edit_date, " // 9
				+ "is_published, " // 10
				+ "keywords, " // 11
				+ "teaser_css_class, " // 12
				+ "teaser_css_id, " // 13
				+ "css_class, " // 14
				+ "css_id, " // 15
				+ "container_tag, " // 16
				+ "create_time, " // 17
				+ "show_from, " // 18
				+ "show_until, " // 19
				+ "layout_column, " // 20
				+ "page_path" // 21
				//
				+ ") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) " //
				+ "ON DUPLICATE KEY UPDATE " //
				+ "parent_id = VALUES(parent_id), " // 1
				+ "sorting = VALUES(sorting), " // 2
				+ "title = VALUES(title), " // 3
				+ "author = VALUES(author), " // 4
				+ "teaser_text = VALUES(teaser_text), " // 5
				+ "teaser_image_path = VALUES(teaser_image_path), " // 6
				+ "show_teaser = VALUES(show_teaser), " // 7
				+ "last_edit_date = VALUES(last_edit_date), " // 8
				+ "is_published = VALUES(is_published), " // 9
				+ "keywords = VALUES(keywords), " // 10
				+ "teaser_css_class = VALUES(teaser_css_class), " // 11
				+ "teaser_css_id = VALUES(teaser_css_id), " // 12
				+ "css_class = VALUES(css_class), " // 13
				+ "css_id = VALUES(css_id), " // 14
				+ "container_tag = VALUES(container_tag), " // 15
				+ "create_time = VALUES(create_time), " // 16
				+ "show_from = VALUES(show_from), " // 17
				+ "show_until = VALUES(show_until), " // 18
				+ "layout_column = VALUES(layout_column, )" // 19
				+ "page_path = VALUES(page_path)" // 20

				+ ";";

		psInsertUpdate = conn.prepareStatement(
				sqlInsertUpdate, Statement.RETURN_GENERATED_KEYS
		);

		StringBuilder articlesNoDelete = new StringBuilder();

		for (int i = 0; i < articles.size(); i++)
		{
			setArticleDataToPsInclId(psInsertUpdate, articles.get(i));

			int affectedRows = psInsertUpdate.executeUpdate();
			// TODO sveng 05.11.2022: affected rows nutzen?

			ResultSet rs = psInsertUpdate.getGeneratedKeys();
			while (rs.next())
			{
				articles.get(i).setId(rs.getLong(1));
			}

			elementDao.mapElementsToArticle(articles.get(i), conn);

			articlesNoDelete.append(articles.get(i).getId());
			if (i < articles.size() - 1)
			{
				articlesNoDelete.append(", ");
			}
		}

		PreparedStatement psDelete;
		String sqlDeleteString = //
				"DELETE FROM " + getTable() + " "
						+ "WHERE parent_id=?  AND id NOT IN ("
						+ articlesNoDelete.toString() + ")";
		psDelete = conn.prepareStatement(sqlDeleteString);
		psDelete.setLong(1, page.getId());

		psDelete.execute();

		return articles;
	}

	public void unmapArticlesFromPage(PageJDBCFlat page, Connection conn)
			throws SQLException
	{
		String sql = "delete from " + getTable() + " where parent_id=?";

		conn.setAutoCommit(false);

		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setLong(1, page.getId());

		ps.executeUpdate();

		List<ArticleJDBCFlat> articles = page.getMainArticles();

		for (ArticleJDBCFlat article : articles)
		{
			elementDao.unmapElementsFromArticle(article, conn);
		}
	}

	public List<ArticleJDBCFlat> readHeaderArticles(Connection conn)
			throws SQLException
	{
		List<ArticleJDBCFlat> articles = new ArrayList<>();

		String sql = "SELECT * FROM " + getTable()
				+ " WHERE parent_id = 40 ORDER BY sorting ASC";

		PreparedStatement ps = conn.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();

		ArticleJDBCFlat article;

		while (rs.next())
		{
			article = new ArticleJDBCFlat();

			writeResultSetDataToArticle(article, rs);

			article.setElements(elementDao.read(article, conn));

			articles.add(article);
		}

		return articles;
	}

	public List<ArticleJDBCFlat> readFooterArticles(Connection conn)
			throws SQLException
	{
		List<ArticleJDBCFlat> articles = new ArrayList<>();

		String sql = "SELECT * FROM " + getTable()
				+ " WHERE parent_id = 46 ORDER BY sorting ASC";

		PreparedStatement ps = conn.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();

		ArticleJDBCFlat article;

		while (rs.next())
		{
			article = new ArticleJDBCFlat();

			writeResultSetDataToArticle(article, rs);

			article.setElements(elementDao.read(article, conn));

			articles.add(article);
		}

		return articles;
	}
}
