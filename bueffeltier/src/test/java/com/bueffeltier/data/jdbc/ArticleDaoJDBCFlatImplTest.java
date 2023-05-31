package com.bueffeltier.data.jdbc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// TODO sveng 10.11.2022: in jdbc test über metadata auch die spaltenzahl prüfen
class ArticleDaoJDBCFlatImplTest
{
	public static Connection conn;

	public static ElementDaoJDBCFlatImpl elementDao;
	public static ArticleDaoJDBCFlatImpl dao;

	public ArticleJDBCFlat article1;
	public ArticleJDBCFlat article2;
	public ArticleJDBCFlat article3;

	public LocalDateTime originalDate;
	public LocalDateTime changedDate;

	@BeforeAll
	static void setUpClass() throws Exception
	{
		elementDao = ElementDaoJDBCFlatImpl
				.getInstance(MaterialTablesDV.ELEMENT_TEST, null);

		dao = ArticleDaoJDBCFlatImpl
				.getInstance(MaterialTablesDV.ARTICLE_TEST, null, elementDao);
	}

	@BeforeEach
	void setupEach() throws Exception
	{
		// todo kein singleton!?
		conn = DBUtils.getConnection();

		DBUtils.executeDDLStatement(MaterialTablesDV.ARTICLE_TEST.getDrop());
		DBUtils.executeDDLStatement(MaterialTablesDV.ARTICLE_TEST.getCreate());

		DBUtils.executeDDLStatement(MaterialTablesDV.ELEMENT_TEST.getDrop());
		DBUtils.executeDDLStatement(MaterialTablesDV.ELEMENT_TEST.getCreate());

		createAndPersistMaterial();
	}

	@AfterEach
	void teardownEach() throws Exception
	{
		conn.close();
	}

	@AfterAll
	static void teardownClass() throws Exception
	{
		DBUtils.executeDDLStatement(MaterialTablesDV.ARTICLE_TEST.getDrop());
		DBUtils.executeDDLStatement(MaterialTablesDV.ELEMENT_TEST.getDrop());
	}

	void createAndPersistMaterial()
	{
		// todo zeit richtig testen!
		String originalDateString = "1977-07-16 00:00:00";
		DateTimeFormatter originalDateFormatter = DateTimeFormatter
				.ofPattern("yyyy-MM-dd HH:mm:ss");
		originalDate = LocalDateTime
				.parse(originalDateString, originalDateFormatter);

		String changedDateString = "2077-07-16 00:00:00";
		DateTimeFormatter changedDateFormatter = DateTimeFormatter
				.ofPattern("yyyy-MM-dd HH:mm:ss");
		changedDate = LocalDateTime
				.parse(originalDateString, originalDateFormatter);

		article1 = new ArticleJDBCFlat();

		article1.setParentId(1L); // 1
		article1.setSorting(1); // 2
		article1.setTitle("title1"); // 3
		article1.setAuthor("author1"); // 4
		article1.setTeaserText("teaserText1"); // 5
		article1.setTeaserImagePath("teaserImagePath1"); // 6
		article1.setShowTeaser(true); // 7
		article1.setLastEditDate(originalDate); // 8
		article1.setPublished(true); // 9
		article1.setKeywords("keywords1"); // 10
		article1.setTeaserCssClass("teaserCssClass1"); // 11
		article1.setTeaserCssId("teaserCssId1"); // 12
		article1.setCssClass("cssClass1"); // 13
		article1.setCssId("cssId1"); // 14
		article1.setContainerTag("containerTag1"); // 15

		article2 = new ArticleJDBCFlat();
		article2.setParentId(1L);
		article2.setTitle("title2");

		article3 = new ArticleJDBCFlat();
		article3.setParentId(1L);
		article3.setTitle("title3");

		assertNotNull(dao);

		dao.write(article1);
		dao.write(article2);
		dao.write(article3);
	}

	@Test
	void defaultSzenario() throws Exception
	{
		assertEquals(dao.readAll().size(), 3);

		// crud

		// rollback
		// close connection
		// commit connection

	}

	@Test
	void testWrite() throws Exception
	{
		assertNotNull(dao);
		assertEquals("title1", dao.read(1L).getTitle());

		// TODO sveng 07.11.2022: testRollback in page- und article-dao
		// SQLIntegrityConstraintViolationException
	}

	@Test
		void testReadMainArticlesByPath() throws Exception
		{
			ArticleJDBCFlat articleResult = dao.read("title1");
	
			assertEquals(1, articleResult.getParentId()); // 1
			assertEquals(1, articleResult.getSorting()); // 2
			assertEquals("title1", articleResult.getTitle()); // 3
			assertEquals("author1", articleResult.getAuthor()); // 4
			assertEquals("teaserText1", articleResult.getTeaserText()); // 5
			assertEquals("teaserImagePath1", articleResult.getTeaserImagePath()); // 6
			assertEquals(true, articleResult.showTeaser()); // 7
			assertEquals(originalDate, articleResult.getLastEditDate());
			assertEquals(true, articleResult.isPublished()); // 9
			assertEquals("keywords1", articleResult.getKeywords()); // 10
			assertEquals("teaserCssClass1", articleResult.getTeaserCssClass()); // 11
			assertEquals("teaserCssId1", articleResult.getTeaserCssId()); // 12
			assertEquals("cssClass1", articleResult.getCssClass()); // 13
			assertEquals("cssId1", articleResult.getCssId()); // 14
			assertEquals("containerTag1", articleResult.getContainerTag());
		}

	@Test
		void testReadMainArticlesById() throws Exception
		{
			ArticleJDBCFlat articleResult = dao.read(1L);
	
			assertEquals(1, articleResult.getParentId()); // 1
			assertEquals(1, articleResult.getSorting()); // 2
			assertEquals("title1", articleResult.getTitle()); // 3
			assertEquals("author1", articleResult.getAuthor()); // 4
			assertEquals("teaserText1", articleResult.getTeaserText()); // 5
			assertEquals("teaserImagePath1", articleResult.getTeaserImagePath()); // 6
			assertEquals(true, articleResult.showTeaser()); // 7
			assertEquals(originalDate, articleResult.getLastEditDate());
			assertEquals(true, articleResult.isPublished()); // 9
			assertEquals("keywords1", articleResult.getKeywords()); // 10
			assertEquals("teaserCssClass1", articleResult.getTeaserCssClass()); // 11
			assertEquals("teaserCssId1", articleResult.getTeaserCssId()); // 12
			assertEquals("cssClass1", articleResult.getCssClass()); // 13
			assertEquals("cssId1", articleResult.getCssId()); // 14
			assertEquals("containerTag1", articleResult.getContainerTag());
	
			// TODO sveng 07.11.2022: testRolback
		}

	@Test
		void testReadMainArticlesByPage() throws Exception
		{
			// TODO sveng 06.11.2022: rückgabe null testen. -auch in pageDao test
			PageJDBCFlat pageWithReturn = new PageJDBCFlat();
			pageWithReturn.setId(2L);
	
			ArticleJDBCFlat article4 = new ArticleJDBCFlat();
			article4.setParentId(pageWithReturn.getId());
			article4.setSorting(2);
			article4.setTitle("Article4");
	
			ArticleJDBCFlat article5 = new ArticleJDBCFlat();
			article5.setParentId(pageWithReturn.getId());
			article5.setSorting(3);
			article5.setTitle("Article5");
	
			ArticleJDBCFlat article6 = new ArticleJDBCFlat();
			article6.setParentId(pageWithReturn.getId());
			article6.setSorting(1);
			article6.setTitle("Article6");
	
			dao.write(article4);
			dao.write(article5);
			dao.write(article6);
	
			List<ArticleJDBCFlat> articleResult1 = dao.readMainArticles(pageWithReturn, conn);
			assertEquals(3, articleResult1.size());
			assertEquals("Article6", articleResult1.get(0).getTitle());
			assertEquals("Article4", articleResult1.get(1).getTitle());
			assertEquals("Article5", articleResult1.get(2).getTitle());
	
			PageJDBCFlat pageWithoutReturn = new PageJDBCFlat();
			pageWithReturn.setId(3L);
	
			List<ArticleJDBCFlat> articleResult2 = dao
					.readMainArticles(pageWithoutReturn, conn);
			assertEquals(0, articleResult2.size());
		}

	@Test
	void testUpdate() throws Exception
	{
		ArticleJDBCFlat articleResult1 = dao.read(1L);
		articleResult1.setParentId(100); // 1
		articleResult1.setSorting(90); // 2
		articleResult1.setTitle("titleChanged"); // 3
		articleResult1.setAuthor("author2"); // 4
		articleResult1.setTeaserText("teaserText2"); // 5
		articleResult1.setTeaserImagePath("teaserImagePath2"); // 6
		articleResult1.setShowTeaser(false); // 7
		articleResult1.setLastEditDate(changedDate); // 8
		articleResult1.setPublished(false); // 9
		articleResult1.setKeywords("keywords2"); // 10
		articleResult1.setTeaserCssClass("teaserCssClass2"); // 11
		articleResult1.setTeaserCssId("teaserCssId2"); // 12
		articleResult1.setCssClass("cssClass2"); // 13
		articleResult1.setCssId("cssId2"); // 14
		articleResult1.setContainerTag("containerTag2"); // 15
		articleResult1.setCreateTime(changedDate); // 16
		articleResult1.setShowFrom(changedDate); // 17
		articleResult1.setShowUntil(changedDate); // 18

		dao.update(articleResult1);

		ArticleJDBCFlat articleResult2 = dao.read(1L);
		assertEquals(100, articleResult2.getParentId()); // 1
		assertEquals(90, articleResult2.getSorting()); // 2
		assertEquals("titleChanged", articleResult2.getTitle()); // 3
		assertEquals("author2", articleResult2.getAuthor()); // 4
		assertEquals("teaserText2", articleResult2.getTeaserText()); // 5
		assertEquals("teaserImagePath2", articleResult2.getTeaserImagePath()); // 6
		assertEquals(false, articleResult2.showTeaser()); // 7
		assertEquals(originalDate, articleResult2.getLastEditDate()); // 8
		assertEquals(false, articleResult2.isPublished()); // 9
		assertEquals("keywords2", articleResult2.getKeywords()); // 10
		assertEquals("teaserCssClass2", articleResult2.getTeaserCssClass()); // 11
		assertEquals("teaserCssId2", articleResult2.getTeaserCssId()); // 12
		assertEquals("cssClass2", articleResult2.getCssClass()); // 13
		assertEquals("cssId2", articleResult2.getCssId()); // 14
		assertEquals("containerTag2", articleResult2.getContainerTag()); // 15
		assertEquals(changedDate, articleResult2.getCreateTime()); // 16
		assertEquals(changedDate, articleResult2.getShowFrom()); // 17
		assertEquals(changedDate, articleResult2.getShowUntil()); // 18

		// TODO sveng 07.11.2022: test rollback
	}

	// delete
	@Test
	void testDelete() throws Exception
	{
		// TODO sveng 02.11.2022: teste homepage nicht einfach löschen können.
		assertEquals(3, dao.readAll().size());
		assertEquals("title1", dao.readAll().get(0).getTitle());
		assertEquals("title2", dao.readAll().get(1).getTitle());
		assertEquals("title3", dao.readAll().get(2).getTitle());

		ArticleJDBCFlat pageToDelete = dao.read(2L);
		dao.delete(pageToDelete);
		assertEquals(2, dao.readAll().size());
		assertEquals("title1", dao.readAll().get(0).getTitle());
		assertEquals("title3", dao.readAll().get(1).getTitle());

		// TODO sveng 07.11.2022: test rollback
	}

	@Test
		void testReadMainArticlesAll() throws Exception
		{
			List<ArticleJDBCFlat> result = dao.readAll();
			assertEquals(3, result.size());
	
			assertEquals(1L, result.get(0).getParentId()); // 1
			assertEquals(1, result.get(0).getSorting()); // 2
			assertEquals("title1", result.get(0).getTitle()); // 3
			assertEquals("author1", result.get(0).getAuthor()); // 4
			assertEquals("teaserText1", result.get(0).getTeaserText()); // 5
			assertEquals("teaserImagePath1", result.get(0).getTeaserImagePath()); // 6
			assertEquals(true, result.get(0).showTeaser()); // 7
			assertEquals(originalDate, result.get(0).getLastEditDate()); // 8
			assertEquals(true, result.get(0).isPublished()); // 9
			assertEquals("keywords1", result.get(0).getKeywords()); // 10
			assertEquals("teaserCssClass1", result.get(0).getTeaserCssClass()); // 11
			assertEquals("teaserCssId1", result.get(0).getTeaserCssId()); // 12
			assertEquals("cssClass1", result.get(0).getCssClass()); // 13
			assertEquals("cssId1", result.get(0).getCssId()); // 14
			assertEquals("containerTag1", result.get(0).getContainerTag());
	
			assertEquals("title2", dao.readAll().get(1).getTitle());
	
			assertEquals("title3", dao.readAll().get(2).getTitle());
	
			// TODO sveng 07.11.2022: test rollback
		}

	@Test
	void testMapArticlesToPage() throws Exception
	{
		// teste nur den part für die artikel, -seite wird nicht getestet.

		// teste threadsafe
		// db restrictions
		// 1. ich habe im grundzustand 3 artikel in der tabelle
		// 2. ich mappe die artikel der seite auf die tabelle

		// #0 Es sind 3 Artikel auf der db.

		// #1 Über das Speichern einer Seite kommt ein neuer Artikel zu den
		// bereits persistierten dazu, ein alter artikel wird gelöscht.
		PageJDBCFlat page = new PageJDBCFlat();
		page.setId(1L);

		List<ArticleJDBCFlat> articles = new ArrayList<>();

		ArticleJDBCFlat articleNeu = new ArticleJDBCFlat();
		articleNeu.setTitle("TitleNeu");
		articleNeu.setParentId(1L);

		articles.add(dao.read(1L));
		articles.add(dao.read(2L));
		articles.add(articleNeu);

		page.setMainArticles(articles);

		dao.mapArticlesToPage(page, conn);

		assertEquals(3, dao.readAll().size());

		// TODO sveng 07.11.2022: test rollback

		// #2 Test neuer Artikel dazu, kein artikel gelöscht.
		// #3 Test kein neuer Artikel dazu, alter artikel gelöscht.
		// #4 Test kein neuer Artikel dazu, kein alter artikel gelöscht.

		// 3. ich habe einen neuen aritkel und einen artikel weniger zur seite.
		// 4. ich hab auch andere artikel in der tabelle
		// 5. nach dem mapping ist der neue artikel und die alten artikel
		// zur seite gemappt und der gelöschte ist aus der db verschwunden
		// test mit anschließendem löschen.
		// test ohne anschließendem löschen.
		// 6. es gibt keine weiteren mappings zu dieser seite
		// 7. test insert/update:
		// neuer artikel auf alte id = Update
		// neuer artikel auf keine id = Insert
		// 8. bei insert: wird autoincrement richtig angewendet?
		// 9. teste rollback
	}
}