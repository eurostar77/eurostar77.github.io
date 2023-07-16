package com.bueffeltier.logic.foundation.pagetree;

import java.util.List;

import com.bueffeltier.data.jdbc.ArticleDaoJDBCFlatImpl;
import com.bueffeltier.data.jdbc.ArticleJDBCFlat;
import com.bueffeltier.data.jdbc.ElementDaoJDBCFlatImpl;
import com.bueffeltier.data.jdbc.ElementJDBCFlat;
import com.bueffeltier.data.jdbc.PageDaoJDBCFlatImpl;
import com.bueffeltier.data.jdbc.Page;

// todo headlines einführen.

/*
 * Root-Page hat immer id 1 und pid 0/null.
 */
public class SiteRepository
{
	private static SiteRepository instance = null;

	PageDaoJDBCFlatImpl pageDao;
	ArticleDaoJDBCFlatImpl articleDao;
	ElementDaoJDBCFlatImpl elementDao;

	private SiteRepository()
	{
		pageDao = PageDaoJDBCFlatImpl.getInstance();
		articleDao = ArticleDaoJDBCFlatImpl.getInstance();
		elementDao = ElementDaoJDBCFlatImpl.getInstance();
	}

	public static SiteRepository getInstance()
	{
		if (instance == null)
		{
			instance = new SiteRepository();
		}
		return instance;
	}

	/*
	 * Pages:
	 */

	public Page createPage()
	{
		// page richtig initialisieren.

		return null;
	}

	public void write(Page page)
	{
		pageDao.write(page);
		// todo: mit return values arbeiten, richtige seite, oder meldung
		// zurückgeben.
	}

	public Page read(String path)
	{
		return pageDao.read(path);
	}

	public Page read(long id)
	{
		return pageDao.read(id);
	}

	public List<Page> readAll()
	{
		return pageDao.readAll();
	}

	public void update(Page page)
	{
		pageDao.update(page);
	}

	public void delete(Page page)
	{
		pageDao.delete(page);
	}

	/*
	 * Articles:
	 */

	public ArticleJDBCFlat readArticle(long id)
	{
		return articleDao.read(id);
	}

	public List<ArticleJDBCFlat> readArticles(long id)
	{
		return articleDao.readArticles(id);
	}

	public ElementJDBCFlat readElement(long id)
	{
		return elementDao.read(id);
	}

// Sitemap:

	private void writeSitemap()
	{
		// todo: sitemap und page links müssen die selben pages enthalten.
		// todo: jede seite muss über max. 3 klicks erreichbar sein, sonst
		// warning.

		// xml anlegen
		// foundation-pages anlegen
		// artikelpages anlegen
	}

	public Page getHomePage()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
