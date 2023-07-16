package com.bueffeltier.data.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// TODO sveng 12.11.2022: url alias löschen?, css_id einfügen oder löschen? 
// todo createdate, author

public class PageDaoJDBCFlatImpl extends AbstractDAO<Page>
{
	private static PageDaoJDBCFlatImpl instance = null;

	private ArticleDaoJDBCFlatImpl articleDao;

	private PageDaoJDBCFlatImpl()
	{
		super(MaterialTablesDV.PAGE, null);
		this.articleDao = ArticleDaoJDBCFlatImpl.getInstance();
	}

	public static PageDaoJDBCFlatImpl getInstance()
	{
		if (instance == null)
		{
			instance = new PageDaoJDBCFlatImpl();
		}

		return instance;
	}

	public void write(Page page)
	{
		String sql = "INSERT INTO " + getTable() + " (" //
		    + "title, " // 1 UNIQUE
		    + "path, " // 2
		    + "layout, " // 3
		    + "permission, " // 4
		    + "forward_to, " // 5
		    + "author, " // 6
		    + "cache_time, " // 7
		    + "create_sitemap, " // 8
		    + "css_class, " // 9
		    + "description, " // 10
		    + "page_type, " // 11
		    + "hide_in_nav, " // 12
		    + "include_cache, " // 13
		    + "include_layout, " // 14
		    + "is_protected, " // 15
		    + "keywords, " // 16
		    + "language, " // 17
		    + "last_version, " // 18
		    + "published, " // 19
		    + "sitemap_name, " // 20
		    + "no_follow, " // 21
		    + "no_index, " // 22
		    + "internal_name, " // 23
		    + "url_alias" // 24
			//
		    + ") VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		Connection conn = null;
		PreparedStatement ps = null;

		try
		{
			conn = DBUtils.getConnection();
			conn.setAutoCommit(false);

			ps = conn.prepareStatement(sql);

			setPageDataToPs(ps, page);

			ps.execute();

			articleDao.mapArticlesToPage(page, conn);

			conn.commit();

		} catch (Exception e)
		{
			rollbackTransaction(conn, null);

		} finally
		{
			finalClosings(ps, conn);
		}
	}

	public Page read(String path)
	{
		Page page = null;

		String sql = "SELECT * FROM " + getTable() + " WHERE path=?";

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try
		{
			conn = DBUtils.getConnection();
			conn.setAutoCommit(false);

			ps = conn.prepareStatement(sql);
			ps.setString(1, path);

			rs = ps.executeQuery();

			if (rs.next())
			{
				page = new Page();

				writeResultSetDataToPage(page, rs);

				setArticlesToPage(page, conn);

				conn.commit();
			}

		} catch (Exception e)
		{
			rollbackTransaction(conn, null);

		} finally
		{
			finalClosings(rs, ps, conn);
		}

		return page;
	}

	public Page read(long id)
	{
		Page page = null;

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

			page = new Page();

			while (rs.next())
			{
				writeResultSetDataToPage(page, rs);
			}

			setArticlesToPage(page, conn);

			conn.commit();

		} catch (Exception e)
		{
			rollbackTransaction(conn, null);

		} finally
		{
			finalClosings(rs, ps, conn);
		}

		return page;
	}

	public void update(Page page)
	{
		String sql = "UPDATE " + getTable() + " SET "//
		    + "title=?, " // 1
		    + "path=?, " // 2
		    + "layout=?, " // 3
		    + "permission=?, " // 4
		    + "forward_to=?, " // 5
		    + "author=?, " // 6
		    + "cache_time=?, " // 7
		    + "create_sitemap=?, " // 8
		    + "css_class=?, " // 9
		    + "description=?, " // 10
		    + "page_type=?, " // 11
		    + "hide_in_nav=?, " // 12
		    + "include_cache=?, " // 13
		    + "include_layout=?, " // 14
		    + "is_protected=?, " // 15
		    + "keywords=?, " // 16
		    + "language=?, " // 17
		    + "last_version=?, " // 18
		    + "published=?, " // 19
		    + "sitemap_name=?, " // 20
		    + "no_follow=?, " // 21
		    + "no_index=?, " // 22
		    + "internal_name=?, " // 23
		    + "url_alias=?" // 24
		    + " where id=?;";

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try
		{
			conn = DBUtils.getConnection();
			conn.setAutoCommit(false);

			ps = conn.prepareStatement(sql);

			setPageDataToPs(ps, page);

			ps.setLong(25, page.getId()); // 25 ////////////////////////////////

			ps.executeUpdate();

			articleDao.mapArticlesToPage(page, conn);

			conn.commit();

		} catch (Exception e)
		{
			rollbackTransaction(conn, null);

		} finally
		{
			finalClosings(rs, ps, conn);
		}
	}

	public void delete(Page page)
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

			ps.setLong(1, page.getId());

			ps.executeUpdate();

			articleDao.unmapArticlesFromPage(page, conn);

			conn.commit();

		} catch (Exception e)
		{
			rollbackTransaction(conn, null);

		} finally
		{
			finalClosings(rs, ps, conn);
		}
	}

	public List<Page> readAll()
	{
		List<Page> pages = new ArrayList<>();

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
				Page page = new Page();

				writeResultSetDataToPage(page, rs);

				setArticlesToPage(page, conn);

				pages.add(page);
			}

			conn.commit();

		} catch (Exception e)
		{
			rollbackTransaction(conn, null);

		} finally
		{
			finalClosings(rs, ps, conn);
		}

		return pages;
	}

	private Page
	    writeResultSetDataToPage(Page page, ResultSet rs)
	        throws SQLException
	{
		page.setId(rs.getLong("id"));
		page.setHtmlTitle(rs.getString("title")); // 1
		page.setPath(rs.getString("path")); // 2
		page.setLayout(rs.getInt("layout")); // 3
		page.setPermission(rs.getInt("permission")); // 4
		page.setForwardTo(rs.getString("forward_to")); // 5
		page.setAuthor(rs.getString("author")); // 6
		page.setCacheTime(rs.getString("cache_time")); // 7
		page.createSitemap(rs.getBoolean("create_sitemap")); // 8
		page.setCssClass(rs.getString("css_class")); // 9
		page.setDescription(rs.getString("description")); // 10
		page.setPageType(rs.getString("page_type")); // 11
		page.setHideInNav(rs.getBoolean("hide_in_nav")); // 12
		page.setIncludeCache(rs.getBoolean("include_cache")); // 13
		page.setIncludeLayout(rs.getBoolean("include_layout")); // 14
		page.setProtected(rs.getBoolean("is_protected")); // 15
		page.setKeywords(rs.getString("keywords")); // 16
		page.setLanguage(rs.getString("language")); // 17
		page.setLastVersion(rs.getString("last_version")); // 18
		page.setPublished(rs.getBoolean("published")); // 19
		page.setSitemapName(rs.getString("sitemap_name")); // 20
		page.setNoFollow(rs.getBoolean("no_follow")); // 21
		page.setNoIndex(rs.getBoolean("no_index")); // 22
		page.setInternalName(rs.getString("internal_name")); // 23
		page.setUrlAlias(rs.getString("url_alias")); // 24

		return page;
	}

	private PreparedStatement
	    setPageDataToPs(PreparedStatement ps, Page page)
	        throws SQLException
	{
		ps.setString(1, page.getHtmlTitle()); // 1
		ps.setString(2, page.getPath()); // 2
		ps.setInt(3, page.getLayout()); // 3
		ps.setInt(4, page.getPermission()); // 4
		ps.setString(5, page.getForwardTo()); // 5
		ps.setString(6, page.getAuthor()); // 6
		ps.setString(7, page.getCacheTime()); // 7
		ps.setBoolean(8, page.createSitemap()); // 8
		ps.setString(9, page.getCssClass()); // 9
		ps.setString(10, page.getDescription()); // 10
		ps.setString(11, page.getPageType()); // 11
		ps.setBoolean(12, page.isHiddenInNav()); // 12
		ps.setBoolean(13, page.includeCache()); // 13
		ps.setBoolean(14, page.includeLayout()); // 14
		ps.setBoolean(15, page.isProtected()); // 15
		ps.setString(16, page.getKeywords()); // 16
		ps.setString(17, page.getLanguage()); // 17
		ps.setString(18, page.getLastVersion()); // 18
		ps.setBoolean(19, page.isPublished()); // 19
		ps.setString(20, page.getSitemapName()); // 20
		ps.setBoolean(21, page.noFollow()); // 21
		ps.setBoolean(22, page.noIndex()); // 22
		ps.setString(23, page.getInternalName()); // 23
		ps.setString(24, page.getUrlAlias()); // 24

		return ps;
	}

	private Page setArticlesToPage(Page page, Connection conn)
	    throws SQLException
	{
		List<ArticleJDBCFlat> headerArticles = new ArrayList<>();
		List<ArticleJDBCFlat> mainArticles = new ArrayList<>();
		List<ArticleJDBCFlat> footerArticles = new ArrayList<>();

		headerArticles.addAll(articleDao.readHeaderArticles(conn));
		mainArticles.addAll(articleDao.readMainArticles(page, conn));
		footerArticles.addAll(articleDao.readFooterArticles(conn));

		page.setHeaderArticles(headerArticles);
		page.setMainArticles(mainArticles);
		page.setFooterArticles(footerArticles);

		return page;
	}

}
