//package com.bueffeltier.data.jdbc;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.sql.Connection;
//import java.util.List;
//
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//class PageDaoJDBCFlatImplTest
//{
//	public static Connection conn;
//
//	public static ElementDaoJDBCFlatImpl elementDao;
//	public static ArticleDaoJDBCFlatImpl articleDao;
//	public static PageDaoJDBCFlatImpl dao;
//
//	public PageJDBCFlat page1;
//	public PageJDBCFlat page2;
//	public PageJDBCFlat page3;
//
//	@BeforeAll
//	static void setUpClass() throws Exception
//	{
//		elementDao = ElementDaoJDBCFlatImpl.getInstance();
//
//		articleDao = ArticleDaoJDBCFlatImpl.getInstance();
//
//		dao = PageDaoJDBCFlatImpl.getInstance();
//	}
//
//	@BeforeEach
//	void setupEach() throws Exception
//	{
//		conn = DBUtils.getConnection();
//
//		DBUtils.executeDDLStatement(MaterialTablesDV.PAGE_TEST.getDrop());
//		DBUtils.executeDDLStatement(MaterialTablesDV.ARTICLE_TEST.getDrop());
//
//		DBUtils.executeDDLStatement(MaterialTablesDV.PAGE_TEST.getCreate());
//		DBUtils.executeDDLStatement(MaterialTablesDV.ARTICLE_TEST.getCreate());
//
//		createAndPersistMaterial();
//	}
//
//	@AfterEach
//	void teardownEach() throws Exception
//	{
//		conn.close();
//	}
//
//	@AfterAll
//	static void teardownClass() throws Exception
//	{
//		DBUtils.executeDDLStatement(MaterialTablesDV.PAGE_TEST.getDrop());
//		DBUtils.executeDDLStatement(MaterialTablesDV.ARTICLE_TEST.getDrop());
//	}
//
//	private void createAndPersistMaterial()
//	{
//		page1 = new PageJDBCFlat();
//
//		page1.setHtmlTitle("title1"); // 1
//		page1.setPath("path1"); // 2
//		page1.setLayout(1); // 3
//		page1.setPermission(2); // 4
//		page1.setForwardTo("forwartTo1"); // 5
//		page1.setAuthor("author1"); // 6
//		page1.setCacheTime("cacheTime1"); // 7
//		page1.setCreateSitemap(true); // 8
//		page1.setCssClass("cssClass1"); // 9
//		page1.setDescription("description1"); // 10
//		page1.setPageType("pageType1"); // 11
//		page1.setHideInNav(true); // 12
//		page1.setIncludeCache(true); // 13
//		page1.setIncludeLayout(true); // 14
//		page1.setProtected(true); // 15
//		page1.setKeywords("keywords1"); // 16
//		page1.setLanguage("language1"); // 17
//		page1.setLastVersion("lastVersion1"); // 18
//		page1.setPublished(true); // 19
//		page1.setSitemapName("sitemapName1"); // 20
//		page1.setNoFollow(true); // 21
//		page1.setNoIndex(true); // 22
//		page1.setInternalName("internalName1"); // 23
//		page1.setUrlAlias("urlAlias1"); // 24
//		page1.setLevel(3); // 25
//
//		page2 = new PageJDBCFlat();
//		page2.setHtmlTitle("title2");
//
//		page3 = new PageJDBCFlat();
//		page3.setHtmlTitle("title3");
//
//		dao.write(page1);
//		dao.write(page2);
//		dao.write(page3);
//	}
//
//	@Test
//	void defaultSzenario() throws Exception
//	{
//		assertNotNull(dao);
//
//		assertEquals(3, dao.readAll().size());
//
//		List<PageJDBCFlat> result = dao.readAll();
//		assertEquals("title1", result.get(0).getHtmlTitle());
//		assertEquals("path1", result.get(0).getPath());
//		assertEquals(1, result.get(0).getLayout());
//		assertEquals(2, result.get(0).getPermission());
//		assertEquals("forwartTo1", result.get(0).getForwardTo());
//		assertEquals("author1", result.get(0).getAuthor());
//		assertEquals("cacheTime1", result.get(0).getCacheTime());
//		assertEquals(true, result.get(0).createSitemap());
//		assertEquals("cssClass1", result.get(0).getCssClass());
//		assertEquals("description1", result.get(0).getDescription());
//		assertEquals("pageType1", result.get(0).getPageType());
//		assertEquals(true, result.get(0).isHiddenInNav());
//		assertEquals(true, result.get(0).includeCache());
//		assertEquals(1, result.get(0).getLayout());
//		assertEquals(true, result.get(0).isProtected());
//		assertEquals("keywords1", result.get(0).getKeywords());
//		assertEquals("language1", result.get(0).getLanguage());
//		assertEquals("lastVersion1", result.get(0).getLastVersion());
//		assertEquals(true, result.get(0).isPublished());
//		assertEquals("sitemapName1", result.get(0).getSitemapName());
//		assertEquals(true, result.get(0).noFollow());
//		assertEquals(true, result.get(0).noIndex());
//		assertEquals("internalName1", result.get(0).getInternalName());
//		assertEquals("urlAlias1", result.get(0).getUrlAlias());
//		assertEquals(3, result.get(0).getLevel());
//
//		// crud
//
//		// rollback
//		// close connection
//		// commit connection
//
//	}
//
//	@Test
//	void testWrite() throws Exception
//	{
//		assertEquals("title1", dao.read(1L).getHtmlTitle());
//
//		PageJDBCFlat pageResult = dao.read("path1");
//
//		assertEquals("title1", pageResult.getHtmlTitle()); // 1
//		assertEquals("path1", pageResult.getPath()); // 2
//		assertEquals(1, pageResult.getLayout()); // 3
//		assertEquals(2, pageResult.getPermission()); // 4
//		assertEquals("forwartTo1", pageResult.getForwardTo()); // 5
//		assertEquals("author1", pageResult.getAuthor()); // 6
//		assertEquals("cacheTime1", pageResult.getCacheTime()); // 7
//		assertEquals(true, pageResult.createSitemap()); // 8
//		assertEquals("cssClass1", pageResult.getCssClass()); // 9
//		assertEquals("description1", pageResult.getDescription()); // 10
//		assertEquals("pageType1", pageResult.getPageType()); // 11
//		assertEquals(true, pageResult.isHiddenInNav()); // 12
//		assertEquals(true, pageResult.includeCache()); // 13
//		assertEquals(true, pageResult.includeLayout()); // 14
//		assertEquals(true, pageResult.isProtected()); // 15
//		assertEquals("keywords1", pageResult.getKeywords()); // 16
//		assertEquals("language1", pageResult.getLanguage()); // 17
//		assertEquals("lastVersion1", pageResult.getLastVersion()); // 18
//		assertEquals(true, pageResult.isPublished()); // 19
//		assertEquals("sitemapName1", pageResult.getSitemapName()); // 20
//		assertEquals(true, pageResult.noFollow()); // 21
//		assertEquals(true, pageResult.noIndex()); // 22
//		assertEquals("internalName1", pageResult.getInternalName()); // 23
//		assertEquals("urlAlias1", pageResult.getUrlAlias()); // 24
//		assertEquals(3, pageResult.getLevel()); // 25
//	}
//
//	@Test
//	void testReadByPath() throws Exception
//	{
//		PageJDBCFlat pageResult = dao.read("path1");
//
//		assertEquals("title1", pageResult.getHtmlTitle()); // 1
//		assertEquals("path1", pageResult.getPath()); // 2
//		assertEquals(1, pageResult.getLayout()); // 3
//		assertEquals(2, pageResult.getPermission()); // 4
//		assertEquals("forwartTo1", pageResult.getForwardTo()); // 5
//		assertEquals("author1", pageResult.getAuthor()); // 6
//		assertEquals("cacheTime1", pageResult.getCacheTime()); // 7
//		assertEquals(true, pageResult.createSitemap()); // 8
//		assertEquals("cssClass1", pageResult.getCssClass()); // 9
//		assertEquals("description1", pageResult.getDescription()); // 10
//		assertEquals("pageType1", pageResult.getPageType()); // 11
//		assertEquals(true, pageResult.isHiddenInNav()); // 12
//		assertEquals(true, pageResult.includeCache()); // 13
//		assertEquals(true, pageResult.includeLayout()); // 14
//		assertEquals(true, pageResult.isProtected()); // 15
//		assertEquals("keywords1", pageResult.getKeywords()); // 16
//		assertEquals("language1", pageResult.getLanguage()); // 17
//		assertEquals("lastVersion1", pageResult.getLastVersion()); // 18
//		assertEquals(true, pageResult.isPublished()); // 19
//		assertEquals("sitemapName1", pageResult.getSitemapName()); // 20
//		assertEquals(true, pageResult.noFollow()); // 21
//		assertEquals(true, pageResult.noIndex()); // 22
//		assertEquals("internalName1", pageResult.getInternalName()); // 23
//		assertEquals("urlAlias1", pageResult.getUrlAlias()); // 24
//		assertEquals(3, pageResult.getLevel()); // 25
//	}
//
//	@Test
//	void testReadById() throws Exception
//	{
//		PageJDBCFlat pageResult = dao.read(1L);
//
//		assertEquals("title1", pageResult.getHtmlTitle()); // 1
//		assertEquals("path1", pageResult.getPath()); // 2
//		assertEquals(1, pageResult.getLayout()); // 3
//		assertEquals(2, pageResult.getPermission()); // 4
//		assertEquals("forwartTo1", pageResult.getForwardTo()); // 5
//		assertEquals("author1", pageResult.getAuthor()); // 6
//		assertEquals("cacheTime1", pageResult.getCacheTime()); // 7
//		assertEquals(true, pageResult.createSitemap()); // 8
//		assertEquals("cssClass1", pageResult.getCssClass()); // 9
//		assertEquals("description1", pageResult.getDescription()); // 10
//		assertEquals("pageType1", pageResult.getPageType()); // 11
//		assertEquals(true, pageResult.isHiddenInNav()); // 12
//		assertEquals(true, pageResult.includeCache()); // 13
//		assertEquals(true, pageResult.includeLayout()); // 14
//		assertEquals(true, pageResult.isProtected()); // 15
//		assertEquals("keywords1", pageResult.getKeywords()); // 16
//		assertEquals("language1", pageResult.getLanguage()); // 17
//		assertEquals("lastVersion1", pageResult.getLastVersion()); // 18
//		assertEquals(true, pageResult.isPublished()); // 19
//		assertEquals("sitemapName1", pageResult.getSitemapName()); // 20
//		assertEquals(true, pageResult.noFollow()); // 21
//		assertEquals(true, pageResult.noIndex()); // 22
//		assertEquals("internalName1", pageResult.getInternalName()); // 23
//		assertEquals("urlAlias1", pageResult.getUrlAlias()); // 24
//		assertEquals(3, pageResult.getLevel()); // 25
//	}
//
//	@Test
//	void testUpdate() throws Exception
//	{
//		PageJDBCFlat pageResult1 = dao.read(1L);
//		pageResult1.setHtmlTitle("title1Changed"); // 1
//		pageResult1.setPath("path1Changed"); // 2
//		pageResult1.setLayout(2); // 3
//		pageResult1.setPermission(3); // 4
//		pageResult1.setForwardTo("forwartTo1Changed"); // 5
//		pageResult1.setAuthor("author1Changed"); // 6
//		pageResult1.setCacheTime("cacheTimeChanged"); // 7
//		pageResult1.setCreateSitemap(false); // 8
//		pageResult1.setCssClass("cssClass1Changed"); // 9
//		pageResult1.setDescription("description1Changed"); // 10
//		pageResult1.setPageType("pageType1Changed"); // 11
//		pageResult1.setHideInNav(false); // 12
//		pageResult1.setIncludeCache(false); // 13
//		pageResult1.setIncludeLayout(false); // 14
//		pageResult1.setProtected(false); // 15
//		pageResult1.setKeywords("keywords1Changed"); // 16
//		pageResult1.setLanguage("language1Changed"); // 17
//		pageResult1.setLastVersion("lastVersion1Changed"); // 18
//		pageResult1.setPublished(false); // 19
//		pageResult1.setSitemapName("sitemapName1Changed"); // 20
//		pageResult1.setNoFollow(false); // 21
//		pageResult1.setNoIndex(false); // 22
//		pageResult1.setInternalName("internalName1Changed"); // 23
//		pageResult1.setUrlAlias("urlAlias1Changed"); // 24
//		pageResult1.setLevel(4); // 25
//		dao.update(pageResult1);
//
//		PageJDBCFlat pageResult2 = dao.read(1L);
//		assertEquals("title1Changed", pageResult2.getHtmlTitle()); // 1
//		assertEquals("path1Changed", pageResult2.getPath()); // 2
//		assertEquals(2, pageResult2.getLayout()); // 3
//		assertEquals(3, pageResult2.getPermission(), 2); // 4
//		assertEquals("forwartTo1Changed", pageResult2.getForwardTo()); // 5
//		assertEquals("author1Changed", pageResult2.getAuthor()); // 6
//		assertEquals("cacheTimeChanged", pageResult2.getCacheTime()); // 7
//		assertEquals(false, pageResult2.createSitemap()); // 8
//		assertEquals("cssClass1Changed", pageResult2.getCssClass()); // 9
//		assertEquals("description1Changed", pageResult2.getDescription()); // 10
//		assertEquals("pageType1Changed", pageResult2.getPageType()); // 11
//		assertEquals(false, pageResult2.isHiddenInNav()); // 12
//		assertEquals(false, pageResult2.includeCache()); // 13
//		assertEquals(false, pageResult2.includeLayout()); // 14
//		assertEquals(false, pageResult2.isProtected()); // 15
//		assertEquals("keywords1Changed", pageResult2.getKeywords()); // 16
//		assertEquals("language1Changed", pageResult2.getLanguage()); // 17
//		assertEquals("lastVersion1Changed", pageResult2.getLastVersion()); // 18
//		assertEquals(false, pageResult2.isPublished()); // 19
//		assertEquals("sitemapName1Changed", pageResult2.getSitemapName()); // 20
//		assertEquals(false, pageResult2.noFollow()); // 21
//		assertEquals(false, pageResult2.noIndex()); // 22
//		assertEquals("internalName1Changed", pageResult2.getInternalName()); // 23
//		assertEquals("urlAlias1Changed", pageResult2.getUrlAlias()); // 24
//		assertEquals(4, pageResult2.getLevel()); // 25
//	}
//
//	@Test
//	void testDelete() throws Exception
//	{
//		assertEquals(3, dao.readAll().size());
//		assertEquals("title1", dao.readAll().get(0).getHtmlTitle());
//		assertEquals("title2", dao.readAll().get(1).getHtmlTitle());
//		assertEquals("title3", dao.readAll().get(2).getHtmlTitle());
//
//		PageJDBCFlat pageToDelete = dao.read(2L);
//		dao.delete(pageToDelete);
//		assertEquals(2, dao.readAll().size());
//		assertEquals("title1", dao.readAll().get(0).getHtmlTitle());
//		assertEquals("title3", dao.readAll().get(1).getHtmlTitle());
//	}
//
//	@Test
//	void testReadAll() throws Exception
//	{
//		List<PageJDBCFlat> result = dao.readAll();
//		assertEquals(3, result.size());
//
//		assertEquals("title1", result.get(0).getHtmlTitle()); // 1
//		assertEquals("path1", result.get(0).getPath()); // 2
//		assertEquals(1, result.get(0).getLayout()); // 3
//		assertEquals(2, result.get(0).getPermission(), 2); // 4
//		assertEquals("forwartTo1", result.get(0).getForwardTo()); // 5
//		assertEquals("author1", result.get(0).getAuthor()); // 6
//		assertEquals("cacheTime1", result.get(0).getCacheTime()); // 7
//		assertEquals(true, result.get(0).createSitemap()); // 8
//		assertEquals("cssClass1", result.get(0).getCssClass()); // 9
//		assertEquals("description1", result.get(0).getDescription()); // 10
//		assertEquals("pageType1", result.get(0).getPageType()); // 11
//		assertEquals(true, result.get(0).isHiddenInNav()); // 12
//		assertEquals(true, result.get(0).includeCache()); // 13
//		assertEquals(true, result.get(0).includeLayout()); // 14
//		assertEquals(true, result.get(0).isProtected()); // 15
//		assertEquals("keywords1", result.get(0).getKeywords()); // 16
//		assertEquals("language1", result.get(0).getLanguage()); // 17
//		assertEquals("lastVersion1", result.get(0).getLastVersion()); // 18
//		assertEquals(true, result.get(0).isPublished()); // 19
//		assertEquals("sitemapName1", result.get(0).getSitemapName()); // 20
//		assertEquals(true, result.get(0).noFollow()); // 21
//		assertEquals(true, result.get(0).noIndex()); // 22
//		assertEquals("internalName1", result.get(0).getInternalName()); // 23
//		assertEquals("urlAlias1", result.get(0).getUrlAlias()); // 24
//		assertEquals(3, result.get(0).getLevel()); // 25
//
//		assertEquals("title2", dao.readAll().get(1).getHtmlTitle());
//
//		assertEquals("title3", dao.readAll().get(2).getHtmlTitle());
//	}
//}
