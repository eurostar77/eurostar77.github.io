//package com.bueffeltier.data.jdbc;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.sql.Connection;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//public class ElementDaoJDBCFlatImplTest
//{
//	public static Connection conn;
//
//	public static ElementDaoJDBCFlatImpl dao;
//
//	public ElementJDBCFlat element1;
//	public ElementJDBCFlat element2;
//	public ElementJDBCFlat element3;
//
//	public LocalDateTime originalDate;
//	public LocalDateTime changedDate;
//
//	@BeforeAll
//	static void setUpClass() throws Exception
//	{
//		dao = ElementDaoJDBCFlatImpl.getInstance();
//	}
//
//	@BeforeEach
//	void setupEach() throws Exception
//	{
//		// todo kein singleton!?
//		conn = DBUtils.getConnection();
//
//		DBUtils.executeDDLStatement(MaterialTablesDV.ELEMENT_TEST.getDrop());
//		DBUtils.executeDDLStatement(MaterialTablesDV.ELEMENT_TEST.getCreate());
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
//		DBUtils.executeDDLStatement(MaterialTablesDV.ELEMENT_TEST.getDrop());
//	}
//
//	void createAndPersistMaterial()
//	{
//		// todo zeit richtig testen!
//		String originalDateString = "1977-07-16 00:00:00";
//		DateTimeFormatter originalDateFormatter = DateTimeFormatter
//		    .ofPattern("yyyy-MM-dd HH:mm:ss");
//		originalDate = LocalDateTime
//		    .parse(originalDateString, originalDateFormatter);
//
//		String changedDateString = "2077-07-16 00:00:00";
//		DateTimeFormatter changedDateFormatter = DateTimeFormatter
//		    .ofPattern("yyyy-MM-dd HH:mm:ss");
//		changedDate = LocalDateTime
//		    .parse(originalDateString, originalDateFormatter);
//
//		element1 = new ElementJDBCFlat();
//		element1.setParentId(1); // 1
//		element1.setSorting(1); // 2
//		element1.setType("type1");
//		element1.setDynamicHtml(true);
//		element1.setHtml("html1");
//		element1.setHeadlineType(1);
//		element1.setHeadlineText("headlineText1");
//		element1.setGuestsOnly(true);
//		element1.setCssId("cssId1");
//		element1.setCssClass("cssClass1");
//		element1.setHidden(true);
//		element1.setShowFrom(originalDate);
//		element1.setShowUntil(originalDate);
//
//		element2 = new ElementJDBCFlat();
//		element2.setParentId(1);
//		element2.setSorting(2); // 2
//		element2.setHtml("html2");
//
//		element3 = new ElementJDBCFlat();
//		element3.setParentId(1);
//		element3.setSorting(3); // 2
//		element3.setHtml("html3");
//
//		assertNotNull(dao);
//
//		dao.write(element1);
//		dao.write(element2);
//		dao.write(element3);
//	}
//
//	@Test
//	void defaultSzenario() throws Exception
//	{
//		assertEquals(dao.readAll().size(), 3);
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
//		assertNotNull(dao);
//
//		ElementJDBCFlat elementResult = dao.read(1);
//
//		assertEquals(1, elementResult.getParentId()); // 1
//		assertEquals(1, elementResult.getSorting()); // 2
//		assertEquals("type1", elementResult.getType()); // 3
//		assertEquals(true, elementResult.isDynamicHtml()); // 4
//		assertEquals("html1", elementResult.getHtml()); // 4
//		assertEquals(1, elementResult.getHeadlineType()); // 5
//		assertEquals("headlineText1", elementResult.getHeadlineText()); // 6
//		assertEquals(true, elementResult.guestsOnly()); // 7
//		assertEquals("cssId1", elementResult.getCssId()); // 8
//		assertEquals("cssClass1", elementResult.getCssClass()); // 9
//		assertEquals(true, elementResult.isHidden()); // 10
//		assertEquals(originalDate, elementResult.getShowFrom()); // 11
//		assertEquals(originalDate, elementResult.getShowUntil()); // 12
//
//		// TODO sveng 07.11.2022: testRollback in page- und element-dao
//		// SQLIntegrityConstraintViolationException
//	}
//
//	@Test
//	void testReadById() throws Exception
//	{
//		ElementJDBCFlat elementResult = dao.read(1);
//
//		assertEquals(1, elementResult.getParentId()); // 1
//		assertEquals(1, elementResult.getSorting()); // 2
//		assertEquals("type1", elementResult.getType()); // 3
//		assertEquals(true, elementResult.isDynamicHtml()); // 4
//		assertEquals("html1", elementResult.getHtml()); // 4
//		assertEquals(1, elementResult.getHeadlineType()); // 5
//		assertEquals("headlineText1", elementResult.getHeadlineText()); // 6
//		assertEquals(true, elementResult.guestsOnly()); // 7
//		assertEquals("cssId1", elementResult.getCssId()); // 8
//		assertEquals("cssClass1", elementResult.getCssClass()); // 9
//		assertEquals(true, elementResult.isHidden()); // 10
//		assertEquals(originalDate, elementResult.getShowFrom()); // 11
//		assertEquals(originalDate, elementResult.getShowUntil()); // 12
//
//		// TODO sveng 07.11.2022: testRollback
//	}
//
//	@Test
//	void testReadByArticle() throws Exception
//	{
//		// TODO sveng 06.11.2022: rückgabe null testen. -auch in pageDao test
//		ArticleJDBCFlat articleWithReturn = new ArticleJDBCFlat();
//		articleWithReturn.setId(2);
//
//		ElementJDBCFlat element4 = new ElementJDBCFlat();
//		element4.setParentId(articleWithReturn.getId());
//		element4.setSorting(2);
//		element4.setHtml("html4");
//
//		ElementJDBCFlat element5 = new ElementJDBCFlat();
//		element5.setParentId(articleWithReturn.getId());
//		element5.setSorting(3);
//		element5.setHtml("html5");
//
//		ElementJDBCFlat element6 = new ElementJDBCFlat();
//		element6.setParentId(articleWithReturn.getId());
//		element6.setSorting(1);
//		element6.setHtml("html6");
//
//		dao.write(element4);
//		dao.write(element5);
//		dao.write(element6);
//
//		List<ElementJDBCFlat> elementResult1 = dao
//		    .read(articleWithReturn, conn);
//		assertEquals(3, elementResult1.size());
//		assertEquals("html6", elementResult1.get(0).getHtml());
//		assertEquals("html4", elementResult1.get(1).getHtml());
//		assertEquals("html5", elementResult1.get(2).getHtml());
//
//		ArticleJDBCFlat articleWithoutReturn = new ArticleJDBCFlat();
//		articleWithReturn.setId(3);
//
//		List<ElementJDBCFlat> elementResult2 = dao
//		    .read(articleWithoutReturn, conn);
//		assertEquals(0, elementResult2.size());
//	}
//
//	@Test
//	void testUpdate() throws Exception
//	{
//		ElementJDBCFlat elementResult1 = dao.read(1);
//		elementResult1.setParentId(2); // 1
//		elementResult1.setSorting(2); // 2
//		elementResult1.setType("typeChanged");
//		elementResult1.setDynamicHtml(false);
//		elementResult1.setHtml("htmlChanged");
//		elementResult1.setHeadlineType(2);
//		elementResult1.setHeadlineText("headlineTextChanged");
//		elementResult1.setGuestsOnly(false);
//		elementResult1.setCssId("cssIdChanged");
//		elementResult1.setCssClass("cssClassChanged");
//		elementResult1.setHidden(false);
//		elementResult1.setShowFrom(changedDate);
//		elementResult1.setShowUntil(changedDate);
//
//		dao.update(elementResult1);
//
//		ElementJDBCFlat elementResult2 = dao.read(1);
//		assertEquals(2, elementResult2.getParentId()); // 1
//		assertEquals(2, elementResult2.getSorting()); // 2
//		assertEquals("typeChanged", elementResult2.getType()); // 3
//		assertEquals(false, elementResult2.isDynamicHtml()); // 4
//		assertEquals("htmlChanged", elementResult2.getHtml()); // 5
//		assertEquals(2, elementResult2.getHeadlineType()); // 6
//		assertEquals("headlineTextChanged", elementResult2.getHeadlineText()); // 7
//		assertEquals(false, elementResult2.guestsOnly()); // 7
//		assertEquals("cssIdChanged", elementResult2.getCssId()); // 7
//		assertEquals("cssClassChanged", elementResult2.getCssClass()); // 7
//		assertEquals(false, elementResult2.isHidden()); // 7
//		assertEquals(changedDate, elementResult2.getShowFrom()); // 8
//		assertEquals(changedDate, elementResult2.getShowUntil()); // 8
//
//		// TODO sveng 07.11.2022: test rollback
//	}
//
//	// delete
//	@Test
//	void testDelete() throws Exception
//	{
//		// TODO sveng 02.11.2022: teste homepage nicht einfach löschen können.
//		assertEquals(3, dao.readAll().size());
//		assertEquals("html1", dao.readAll().get(0).getHtml());
//		assertEquals("html2", dao.readAll().get(1).getHtml());
//		assertEquals("html3", dao.readAll().get(2).getHtml());
//
//		ElementJDBCFlat pageToDelete = dao.read(2);
//		dao.delete(pageToDelete);
//		assertEquals(2, dao.readAll().size());
//		assertEquals("html1", dao.readAll().get(0).getHtml());
//		assertEquals("html3", dao.readAll().get(1).getHtml());
//
//		// TODO sveng 07.11.2022: test rollback
//	}
//
//	@Test
//	void testReadAll() throws Exception
//	{
//		List<ElementJDBCFlat> result = dao.readAll();
//		assertEquals(3, result.size());
//
//		assertEquals(1, result.get(0).getParentId()); // 1
//		assertEquals(1, result.get(0).getSorting()); // 2
//		assertEquals("type1", result.get(0).getType()); // 3
//		assertEquals(true, result.get(0).isDynamicHtml()); // 4
//		assertEquals("html1", result.get(0).getHtml()); // 5
//		assertEquals(1, result.get(0).getHeadlineType()); // 6
//		assertEquals("headlineText1", result.get(0).getHeadlineText()); // 7
//		assertEquals(true, result.get(0).guestsOnly()); // 7
//		assertEquals("cssId1", result.get(0).getCssId()); // 7
//		assertEquals("cssClass1", result.get(0).getCssClass()); // 7
//		assertEquals(true, result.get(0).isHidden()); // 7
//		assertEquals(originalDate, result.get(0).getShowFrom()); // 8
//		assertEquals(originalDate, result.get(0).getShowUntil()); // 8
//
//		assertEquals("html2", dao.readAll().get(1).getHtml());
//
//		assertEquals("html3", dao.readAll().get(2).getHtml());
//
//		// TODO sveng 07.11.2022: test rollback
//	}
//
//	@Test
//	void testMapElementsToPage() throws Exception
//	{
//		// teste nur den part für die artikel, -seite wird nicht getestet.
//
//		// teste threadsafe
//		// db restrictions
//		// 1. ich habe im grundzustand 3 artikel in der tabelle
//		// 2. ich mappe die artikel der seite auf die tabelle
//
//		// #0 Es sind 3 Artikel auf der db.
//
//		// #1 Über das Speichern einer Seite kommt ein neuer Artikel zu den
//		// bereits persistierten dazu, ein alter artikel wird gelöscht.
//		ArticleJDBCFlat article = new ArticleJDBCFlat();
//		article.setId(1);
//
//		List<ElementJDBCFlat> elements = new ArrayList<>();
//
//		ElementJDBCFlat elementNeu = new ElementJDBCFlat();
//		elementNeu.setHtml("HtmlNeu");
//		elementNeu.setParentId(1);
//
//		elements.add(dao.read(1));
//		elements.add(dao.read(2));
//		elements.add(elementNeu);
//
//		article.setElements(elements);
//
//		dao.mapElementsToArticle(article, conn);
//
//		assertEquals(3, dao.readAll().size());
//
//		// TODO sveng 07.11.2022: test rollback
//
//		// #2 Test neuer Artikel dazu, kein artikel gelöscht.
//		// #3 Test kein neuer Artikel dazu, alter artikel gelöscht.
//		// #4 Test kein neuer Artikel dazu, kein alter artikel gelöscht.
//
//		// 3. ich habe einen neuen aritkel und einen artikel weniger zur seite.
//		// 4. ich hab auch andere artikel in der tabelle
//		// 5. nach dem mapping ist der neue artikel und die alten artikel
//		// zur seite gemappt und der gelöschte ist aus der db verschwunden
//		// test mit anschließendem löschen.
//		// test ohne anschließendem löschen.
//		// 6. es gibt keine weiteren mappings zu dieser seite
//		// 7. test insert/update:
//		// neuer artikel auf alte id = Update
//		// neuer artikel auf keine id = Insert
//		// 8. bei insert: wird autoincrement richtig angewendet?
//		// 9. teste rollback
//	}
//}
