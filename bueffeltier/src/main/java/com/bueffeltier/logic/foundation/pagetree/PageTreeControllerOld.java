//package com.bueffeltier.logic.foundation.pagetree;
//
//import java.io.PrintWriter;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//import com.bueffeltier.crosscutting.AppPropertyService;
//import com.bueffeltier.data.hibernate.daoImpl.ArticleDaoImpl;
//import com.bueffeltier.data.hibernate.daoImpl.PageDaoImpl;
//import com.bueffeltier.data.hibernate.entity.Article;
//import com.bueffeltier.data.hibernate.entity.Page;
//import com.bueffeltier.ui.webapp.RequestController;
//
///**
// * Der Seitenbaum ermöglicht den Lese- und Schreibzugriff auf sensible
// * Seiten-Daten, insbesondere alle strukturgebenden Aktionen wie, erstellen,
// * einfügen, verschieben und löschen von Seiten. Schreibt die Sitemap (beim
// * Erstellen oder bei Änderungen).
// *
// * todo: protected Pages (schreibgeschützte Seiten) beachten! todo: public
// * methoden ggf. private machen // Kapselung pdi->pageTree-> pageObj todo: an
// * was kann ich die identität einer seite nun langfristig fesmachen? an der id?
// * am servletPath? am namen? todo: wird eine seite umbenannt oder verschoben, so
// * muss an Stelle der alten seite der Seitentyp Redirect gesetzt werden. die
// * bearbeitete Seite wird dann genaus genommen neu erstellt. todo: Rollback und
// * boolean-Rückgabewert für jede Methode! todo: alte seite im system belassen
// * und redirect auf neue seite setzen. todo: links, url, -möglichst viel auf den
// * Seiten wird hartCodiert. todo: redo von Aktionen hinzufügen. todo:
// * sql-Schnellzugriff implementieren. todo: erbt diese klasse von pagedaoimpl?
// * todo: gib SEO-Warnung bei Verschieben und Löschen! todo: Warnung nicht, wenn
// * noch nicht veröffentlicht. todo: Beim Unveröffentlichen - SEO-Warnung!
// * 
// * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
// */
//public class PageTreeControllerOld
//{
//
//	private final PageDaoImpl pageDaoImpl;
//	private AppPropertyService appPropertyService = AppPropertyService
//			.getInstance();
//
//	/**
//	 * Konstruktor. todo: nur einmal in der gesamten app instantiieren.
//	 * (singleton) klasse muss threadsafe sein. -macht beides sinn?
//	 */
//	public PageTreeControllerOld()
//	{
//		pageDaoImpl = new PageDaoImpl();
//	}
//
//	/**
//	 * Seite wird in der Datenbank aktualisiert. Neue Seiten immer mit
//	 * insertNewPage() hinzufügen. Es dürfen keine Strukturveränderungen am
//	 * Seitenbaum gemacht worden sein! Der Pfad wird aktualisiert. todo: Sitemap
//	 * schreiben!
//	 * 
//	 * @param page
//	 */
//	public void updatePage(Page page)
//	{
//		page.setPath(
//				this.buildServletPath(
//						page, pageDaoImpl.findByPageId(page.getParentId())
//				)
//		);
//		pageDaoImpl.save(page);
//	}
//
//	/**
//	 * Fügt eine neue Seite an einer bestimmten Stelle in eine Seite ein. Pfad
//	 * der neuen Seite wird geschrieben. todo: alles muss in eine Transaction!?
//	 * todo: Sitemap schreiben. todo: boolean Rückgabewert einfügen.
//	 */
//	private void insertnewPage(Page newPage, int order, int parentPageId)
//	{
////        JOptionPane.showMessageDialog(null, "order: " + order);
////        JOptionPane.showMessageDialog(null, "parent: " + parentPageId);
//		List<Page> newSiblings = pageDaoImpl.getChildPagesAsc(parentPageId);
//		/**
//		 * todo: gemeinsamkeiten von insertNewPage und movePage in selber
//		 * methode zusammenfassen.
//		 */
//		// Einschränkung: Man kann nicht hinter id 1 einfügen:
//		if (parentPageId == 0)
//		{
////            log.add(
////                    this,
////                    "movePage(int pageToMoveId, int newOrder, int newParentPageId)",
////                    "Es kann nicht hinter RootPage (ID 1) eingefügt werden!");
//			return;
//		}
//
//		int shift = 0;
//		for (int i = 0; i < newSiblings.size(); i++)
//		{
//			// Wenn in die siblings eingefügt werden soll ( und nicht hinten
//			// angehängt):
//			if (order < newSiblings.size())
//			{
//				// Wenn neue Position erreicht:
//				if (newSiblings.get(i).getOrder() == order)
//				{
//					// Platzhalter für die neue Seite:
//					shift = 1;
//
//					// ALte Seite auf dieser Position bearbeiten:
//					newSiblings.get(i).setOrder(i + shift);
//					// update(); // todo: update einführen! updateSitemap;
//					// vielleicht baum von bis?
//				} else
//				{
//					newSiblings.get(i).setOrder(i + shift);
//				}
//			}
//		}
//		// NeueSeite Pfad schreiben:
//		newPage.setPath(
//				this.buildServletPath(
//						newPage, pageDaoImpl.findByPageId(parentPageId)
//				)
//		);
//		// NeueSeite Parent-Id und Order schreiben:
//		newPage.setParentId(parentPageId);
//		newPage.setOrder(order);
//
//		// NeueSeite einfügen:
//		newSiblings.add(order, newPage);
//		// Alle siblings speichern:
//		for (int i = 0; i < newSiblings.size(); i++)
//		{
//			pageDaoImpl.save(newSiblings.get(i));
//		}
//	}
//
//	/**
//	 * Fügt eine neue Seite IN eine bestimmte Seite in den Seitenbaum ein. Die
//	 * Sitemap wird aktualisiert. Der Servlet-Path des Seiten-Objekts wird
//	 * geschrieben. todo: Sitemap schreiben.
//	 * 
//	 * @param newPage
//	 * @param newParentPageId
//	 * @return
//	 */
//	public boolean addNewPageInto(Page newPage, int newParentPageId)
//	{
//		this.insertnewPage(newPage, 0, newParentPageId);
//		return true; // todo:
//	}
//
//	/**
//	 * Fügt eine neue Seite HINTER einer bestimmten Seite in den Seitenbaum ein.
//	 * Die Sitemap wird aktualisiert. Der Servlet-Path des Seiten-Objekts wird
//	 * geschrieben.
//	 * 
//	 * @param newPage
//	 * @param newLeadingPageId
//	 * @return
//	 */
//	public boolean addNewPageBehind(Page newPage, int newLeadingPageId)
//	{
//		Page newLeadingPage = this.pageDaoImpl.findByPageId(newLeadingPageId);
//		this.insertnewPage(
//				newPage, newLeadingPage.getOrder() + 1,
//				newLeadingPage.getParentId()
//		);
//		return true;
//	}
//
//	/**
//	 * Verscheibt eine Seite innerhalb derselben, oder in eine andere Seite.
//	 * Child-Pages werden beim Verschieben mitgenommen. Setzt den Path aller
//	 * betroffenen Seiten neu. todo: Sitemap schreiben! todo: Alles in eine
//	 * Transaction! // todo: achtung: google pageranking - alte seite im system
//	 * belassen und redirect auf neue seite setzen. // werden alles Links und
//	 * die Navigationen angepasst? // die sitmap muss neu geschrieben werden.
//	 * (feld last version in page)
//	 */
//	private boolean
//			movePage(int pageToMoveId, int newOrder, int newParentPageId)
//	{
//		Page pageToMove = this.pageDaoImpl.findByPageId(pageToMoveId);
//		Page newParentPage = pageDaoImpl.findByPageId(newParentPageId);
//
//		// Seite in eigenen Unterbaum verschieben ist nicht möglich:
//		// todo: pdi methode boolean isDescendantOf()
//		// oder mit iteratoren-hierarchie lösen!
//		// Seite hinter sich selbst verschieben ist nicht möglich!
//		if (pageToMove.getParentId() == newParentPageId
//				& pageToMove.getOrder() + 1 == newOrder)
//
//		{
////            log.add(
////                    this,
////                    "movePage(int pageToMoveId, int newOrder, int newParentPageId)",
////                    "Seite hinter sich selbst verschieben ist nicht möglich!");
//			return false;
//		}
//
//		// Seite wird gar nicht verschoben:
//		if (pageToMove.getParentId() == newParentPageId
//				& pageToMove.getOrder() == newOrder)
//		{
////            log.add(
////                    this,
////                    "movePage(int pageToMoveId, int newOrder, int newParentPageId)",
////                    "Herkunft und Ziel sind gleich!"
////            );
//			return true;
//		}
//
//		// Seite in sich selbst verschieben ist nicht möglich!
//		if (pageToMoveId == newParentPageId)
//		{
////            log.add(
////                    this,
////                    "movePage(int pageToMoveId, int newOrder, int newParentPageId)",
////                    "Seite in sich selbst verschieben ist nicht möglich!");
//			return false;
//		}
//
//		// Page-ID 0 existiert nie:
//		if (pageToMoveId == 0)
//		{
////            log.add(
////                    this,
////                    "movePage(int pageToMoveId, int newOrder, int newParentPageId)",
////                    "Seite 0 existiert nie!");
//			return false;
//		}
//
//		// Page-ID 0 existiert nie:
//		if (pageToMoveId == 1)
//		{
////            log.add(
////                    this,
////                    "movePage(int pageToMoveId, int newOrder, int newParentPageId)",
////                    "Root-Page kann nicht verschoben werden!");
//			return false;
//		}
//
//		// NewParentPage kann nicht 0 sein, da Root-Page eine Ebene allein
//		// einnimmt:
//		if (pageToMoveId == 1)
//		{
////            log.add(
////                    this,
////                    "movePage(int pageToMoveId, int newOrder, int newParentPageId)",
////                    "NewParentPage kann nicht 0 sein, da Root-Page eine Ebene allein einnimmt!");
//			return false;
//		}
//
//		/*
//		 * Pfade des verschobenen Unterbaumes neu schreiben (incl. PageToMove):
//		 * Weitere Daten der PageToMove werden angepasst.
//		 */
//		StringBuilder newPath = new StringBuilder();
//		newPath.append(newParentPage.getPath()).append("/");
//
//		TreeOfPages childPages = new TreeOfPages();
//		childPages.buildTree(pageToMove.getId());
//		Iterator<Node<Page>> iterator = childPages.verticalIterator();
////        boolean isLevelOne = true;
//		while (iterator.hasNext())
//		{
//			Node<Page> currentNode = iterator.next();
////            JOptionPane.showMessageDialog(null, currentNode.getItem().getHtmlTitle());
////            JOptionPane.showMessageDialog(null, "node level: " + currentNode.getLevel());
//			Page currentPage = currentNode.getItem();
//			if (currentNode.getLevel() == 0)
//			{
//				/*
//				 * PageToMove an neuer Stelle einfügen Pfad zu PageToMove und
//				 * Geschwister-Pages schreiben: (Unterseiten behalten ihre
//				 * Parent-ID und werden so mit verschoben:
//				 */
//				currentPage.setParentId(newParentPageId);
//				currentPage.setOrder(newOrder);
//				currentPage.setPath(
//						newPath.append(currentPage.getHtmlTitle()).toString()
//				);
//				this.pageDaoImpl.save(currentPage);
//			} else
//			{
//				currentPage.setPath(
//						currentNode.getParent().getItem().getPath().concat("/")
//								.concat(currentPage.getHtmlTitle())
//				);
//				this.pageDaoImpl.save(currentPage);
//			}
//
//		}
//
//		// Neue Geschwister sortieren und speichern:
//		List<Page> newSiblings = pageDaoImpl
//				.getChildPagesAsc(newParentPage.getId());
//		int newShift = 0;
//		for (int i = 0; i < newSiblings.size(); i++)
//		{
//			if (newSiblings.get(i).getOrder() == newOrder)
//			{
//				// Wenn neue Position erreicht:
//				newShift++;
//				newSiblings.get(i).setOrder(i + newShift);
//				this.pageDaoImpl.save(newSiblings.get(i));
//			} else
//			{
//				// Neue Geschwister sortieren:
//				newSiblings.get(i).setOrder(i + newShift);
//				this.pageDaoImpl.save(newSiblings.get(i));
//			}
//		}
//
//		/*
//		 * Seite wurde aus einer verschiedenen Parent-Page heraus verschoben:
//		 * (Erst durchführen, wenn PageToMove bereits herausgelöst!)
//		 */
//		if (pageToMove.getParentId() != newParentPageId)
//		{
//			// Alte Geschwister neu sortieren: (Der Pfad bleibt gleich!)
//			List<Page> oldSiblings = pageDaoImpl
//					.getChildPagesAsc(newParentPage.getId());
//			for (int i = 0; i < oldSiblings.size(); i++)
//			{
//				oldSiblings.get(i).setOrder(i);
//				this.pageDaoImpl.save(oldSiblings.get(i));
//			}
//		}
//		return true;
//	}
//
//	private boolean
//			newMovePage(int pageToMoveId, int newOrder, int newParentPageId)
//	{
////        JOptionPane.showMessageDialog(null, "pageToMoveId: " + pageToMoveId);
////        JOptionPane.showMessageDialog(null, "newOrder: " + newOrder);
////        JOptionPane.showMessageDialog(null, "newParentPageId: " + newParentPageId);
//		/*
//		 * Benötigte als auch zu bearbeitende Seiten aus DB laden:
//		 */
//		// Referenz-Seiten (werden nicht wieder gespeichert):
//		Page pageToMove = this.pageDaoImpl.findByPageId(pageToMoveId);
//		Page oldParentPage = pageDaoImpl.findByPageId(pageToMove.getParentId());
//		Page newParentPage = pageDaoImpl.findByPageId(newParentPageId);
//
//		// Zu verschiebender Seitenbaum:
//		TreeOfPages movingTree = new TreeOfPages();
//		movingTree.buildTree(pageToMoveId);
//
//		// Alte Geschwister:
//		List<Page> oldSiblings = pageDaoImpl
//				.getChildPagesAsc(pageToMove.getParentId());
//
//		// Neue Geschwister in eventuell anderer Eltern-Seite:
//		// -->> wird weiter unten ermittelt.
//		/**
//		 * Nicht mögliche und nicht erlaubte Aktionen abfangen und false zurück
//		 * geben.
//		 */
//		// hinter sich selbst einfügen nicht möglich!
//		// Seite in eigenen Unterbaum verschieben ist nicht möglich:
//		// todo: pdi methode boolean isDescendantOf()
//		// oder mit iteratoren-hierarchie lösen!
//		//
//		// Seite hinter sich selbst verschieben ist nicht möglich!
//		if (pageToMove.getParentId() == newParentPageId
//				& pageToMove.getOrder() + 1 == newOrder)
//
//		{
////            log.add(
////                    this,
////                    "movePage(int pageToMoveId, int newOrder, int newParentPageId)",
////                    "Seite hinter sich selbst verschieben ist nicht möglich!");
//			return false;
//		}
//
//		// Seite wird gar nicht verschoben:
//		if (pageToMove.getParentId() == newParentPageId
//				& pageToMove.getOrder() == newOrder)
//		{
////            log.add(
////                    this,
////                    "movePage(int pageToMoveId, int newOrder, int newParentPageId)",
////                    "Herkunft und Ziel sind gleich!"
////            );
//			return true;
//		}
//
//		// Seite in sich selbst verschieben ist nicht möglich!
//		if (pageToMoveId == newParentPageId)
//		{
////            log.add(
////                    this,
////                    "movePage(int pageToMoveId, int newOrder, int newParentPageId)",
////                    "Seite in sich selbst verschieben ist nicht möglich!");
//			return false;
//		}
//
//		// Page-ID 0 existiert nie:
//		if (pageToMoveId == 0)
//		{
////            log.add(
////                    this,
////                    "movePage(int pageToMoveId, int newOrder, int newParentPageId)",
////                    "Seite 0 existiert nie!");
//			return false;
//		}
//
//		// todo: wenn in selber seite verschoben wird, und order gleich, dann
//		// kein verschieben!
////        if (pageToMove.getOrder() < newOrder)
//		// Page-ID 0 existiert nie:
//		if (pageToMoveId == 1)
//		{
////            log.add(
////                    this,
////                    "movePage(int pageToMoveId, int newOrder, int newParentPageId)",
////                    "Root-Page kann nicht verschoben werden!");
//			return false;
//		}
//
//		// NewParentPage kann nicht 0 sein, da Root-Page eine Ebene allein
//		// einnimmt:
//		if (pageToMoveId == 1)
//		{
////            log.add(
////                    this,
////                    "movePage(int pageToMoveId, int newOrder, int newParentPageId)",
////                    "NewParentPage kann nicht 0 sein, da Root-Page eine Ebene allein einnimmt!");
//			return false;
//		}
////        JOptionPane.showMessageDialog(null, "Keine Einwände.");
//		/*
//		 * Move-Type ermitteln: 0 = vorwärts innerhalb der selben Eltern-Seite 1
//		 * = rückwärts innerhalb der selben Eltern-Seite 2 = in andere Seite
//		 * verschieben
//		 *
//		 * NewOrder bei Überschreiten des möglichen Index anpassen:
//		 */
//		int moveType = 0;
//		if (pageToMove.getParentId() == newParentPageId)
//		{
//			// Innerhalb einer Seite verschieben:
//
//			// NewOrder anpassen:
//			if (newOrder > oldSiblings.size() + 1)
//			{
//				newOrder = oldSiblings.size() + 1;
//			}
//			// Move-Type setzen:
//			if (pageToMove.getOrder() < newOrder)
//			{
//				moveType = 0;
//			} else if (pageToMove.getOrder() > newOrder)
//			{
//				moveType = 1;
//			}
//		} else
//		{
//			// In eine Andere Seite verschieben:
//			moveType = 2;
//		}
////        JOptionPane.showMessageDialog(null, "moveType: " + moveType);
//
//		/*
//		 * Sortiere ggf. die neuen Geschwister neu: Es werden hier nur die
//		 * Geschwister nicht die zu verschiebende Seite verändert!
//		 */
//		if (moveType == 2)
//		{
//			List<Page> newSiblings = pageDaoImpl
//					.getChildPagesAsc(newParentPageId);
//			int newShift = 0;
//			for (int i = 0; i < newSiblings.size(); i++)
//			{
//				if (newSiblings.get(i).getOrder() == newOrder)
//				{
//					// Wenn neue Position erreicht:
//					newShift++;
//					newSiblings.get(i).setOrder(i + newShift);
//				} else
//				{
//					// Neue Geschwister sortieren:
//					newSiblings.get(i).setOrder(i + newShift);
//				}
//				this.pageDaoImpl.save(newSiblings.get(i));
//			}
//		}
//
//		/*
//		 * Verarbeite den verschobenen Seitenbaum: - Parent-Id der verschobenen
//		 * Seite auf Level 1 anpassen. - Order der verschobenen Seite auf Level
//		 * 1 anpassen. - Pfade aller Level anpassen.
//		 */
//		Node<Page> currentNode;
//		Page currentPage;
//
//		StringBuilder newPathBasis = new StringBuilder();
//		newPathBasis.append(newParentPage.getPath());
//
//		Iterator<Node<Page>> iterator = movingTree.verticalIterator();
//		while (iterator.hasNext())
//		{
//			currentNode = iterator.next();
//			currentPage = currentNode.getItem();
//
//			if (currentNode.getLevel() == 0)
//			{
//				newPathBasis.append("/").append(currentPage.getHtmlTitle());
//				currentPage.setPath(newPathBasis.toString());
//				currentPage.setParentId(newParentPageId);
//				currentPage.setOrder(newOrder);
//			} else
//			{
//				// todo: Effizienz-Steigerung durch StringBuilder:
//				currentPage.setPath(
//						currentNode.getParent().getItem().getPath().concat("/")
//								.concat(currentPage.getHtmlTitle())
//				);
//			}
////            JOptionPane.showMessageDialog(null, "movingTree " + currentNode.getVerticalIndex() + ", save page: " + currentPage.getId());
//			this.pageDaoImpl.save(currentPage);
//		}
//
//		/*
//		 * Ordne die alten Geschwister neu: Es werden hier nur die Geschwister
//		 * nicht die zu verschiebende Seite verändert!
//		 */
//		int shift = 0;
//		for (int i = 0; i < oldSiblings.size(); i++)
//		{
//			// Wenn pageToMove erreicht (Ausschneide-Position):
//			if (oldSiblings.get(i).getId() == pageToMoveId)
//			{
//				if (moveType == 0 || moveType == 2)
//				{
//					shift--;
//				}
//				// pageToMove nicht verändern todo: ?
//
//				if (moveType == 1)
//				{
//					shift--;
//				}
//			} else if (oldSiblings.get(i).getOrder() == newOrder)
//			// Wenn Einfüge-Position erreicht:
//			{
//				if (moveType == 1)
//				{
//					shift++;
//				}
//				// Bisherige Seite auf der Einfüge-Position schieben:
//				oldSiblings.get(i).setOrder(i + shift);
//				if (moveType == 0)
//				{
//					shift++;
//				}
////                JOptionPane.showMessageDialog(null, "oldSiblings save: " + oldSiblings.get(i).getId());
//				this.pageDaoImpl.save(oldSiblings.get(i));
//			} else
//			{
//				// Alle nicht direkt betroffenen Positionen werden geschoben:
//				oldSiblings.get(i).setOrder(i + shift);
////                JOptionPane.showMessageDialog(null, "oldSiblings save: " + oldSiblings.get(i).getId());
//				this.pageDaoImpl.save(oldSiblings.get(i));
//			}
//		}
//		return true;
//	}
//
//	/**
//	 * Verschiebt eine Seite hinter eine andere Seite. Funktion wird an
//	 * movePage() weiter gereicht.
//	 * 
//	 * @param pageToMoveId
//	 * @param newLeadingPageId
//	 * @return
//	 */
//	public boolean movePageBehindPage(int pageToMoveId, int newLeadingPageId)
//	{
//		Page newLeadingPage = this.pageDaoImpl.findByPageId(newLeadingPageId);
//		return this.newMovePage(
//				pageToMoveId, newLeadingPage.getOrder() + 1,
//				newLeadingPage.getParentId()
//		);
//	}
//
//	/**
//	 * Verschiebt eine Seite in eine andere Seite. Funktion wird an movePage()
//	 * weiter gereicht.
//	 * 
//	 * @param pageToMoveId
//	 * @param newParentPageId
//	 * @return
//	 */
//	public boolean movePageIntoPage(int pageToMoveId, int newParentPageId)
//	{
//		return this.newMovePage(pageToMoveId, 0, newParentPageId);
//	}
//
//	/**
//	 * Benennt die Seite neu.Pfade werden angepasst, auch in Unterseiten. todo:
//	 * SEO redirect anlegen.-
//	 *
//	 * @return
//	 */
//	public boolean renamePage()
//	{
//		return true;
//	}
//
//	/**
//	 * Liefert die Angefragte Seite, oder eine Ausweich-Seite zurück. Für
//	 * ungeprüfte Zugriffe auf Seiten "getPageUnchecked()" verwenden.
//	 * 
//	 * @param pageId
//	 * @param requestPermission
//	 * @return
//	 * @throws Exception
//	 */
//	public Page requestPage(int pageId, int requestPermission)
//	{
//		try
//		{
//			Page page = pageDaoImpl.findByPageId(pageId);
//
//			// Schließlich die Permission prüfen:
//			if (requestPermission >= page.getPermission())
//			{
//				// Zugriff erlaubt:
////                log.add(this, "getPageByPermission ok");
//				return page;
//
//			} else
//			{
//				// 403 - No Permission!
////                log.add(this, "getPageByPermission failed");
//
//				return get403Page();
//
//			}
//		} catch (Exception e) // Seite nicht gefunden:
//		{
//			// 404 - Page Not Found!
//			return get404Page(); // todo: Bei Exception wird z.Zt. null
//									// zurückgegeben.
//		}
//	}
//
//	/**
//	 * Liefert die Angefragte Seite, oder eine Ausweich-Seite zurück. Für
//	 * ungeprüfte Zugriffe auf Seiten "getPageUnchecked()" verwenden. todo:
//	 * zugriffe erfordern grundsätzlich permission! todo: rename: getPage()
//	 * 
//	 * @param servletPath
//	 * @param requestPermission
//	 * @return
//	 */
//	public Page requestPage(String servletPath, int requestPermission)
//	{
//		try
//		{
//			Page page;
//
//			if (servletPath.equals(appPropertyService.getRootPagePath()))
//			{
//				page = pageDaoImpl.findByPageId(1); // da über id angefragt
//													// wird, spielt bei der root
//													// page auch der title der
//													// seite keine rolle.
//			} else
//			{
//				page = pageDaoImpl.findByServletPath(servletPath);
//			}
//
//			/*
//			 * Permission der gefundenen Seite prüfen:
//			 */
//			if (requestPermission >= page.getPermission())
//			{
//				// Zugriff erlaubt:
////                log.add(this, "getPageByPermission ok");
//				return page;
//
//			} else
//			{
//				// 403 - No Permission!
////                log.add(this, "getPageByPermission failed");
//
//				page = pageDaoImpl.findByServletPath(
//						appPropertyService.getLoginPageServletPath()
//				);
//
//				return page;
////                return get403Page();
//			}
//
//		} catch (Exception e) // Seite nicht gefunden:
//		{
////            log.add(this,
////                    "Page requestPage(String servletPath, int requestPermission)",
////                    "Angefragte Seite nicht gefunden, rufe get404Page() auf."
////            );
//			// 404 - Page Not Found!
//			return get404Page(); // todo: Bei Exception wird z.Zt. null
//									// zurückgegeben.
//		}
//	}
//
//	/**
//	 * Gibt die angeforderte Seite ungeprüft auf Zugriffsrechte zurück. Es wird
//	 * keine Ausweich- oder Fehlerseite geliefert. todo: prüfe in jeder Nutzung,
//	 * ob permission relevant ist. todo: Bei Nichtexistenz Exception werfen?
//	 * oder boolean? todo: beachte sicherheit bei verwendung dieser methode, -
//	 * wem wird was angezeigt, bzw.ausgeliefert?
//	 * 
//	 * @param pageId
//	 * @return
//	 */
//	public Page getPageUnchecked(int pageId)
//	{
//		return pageDaoImpl.findByPageId(pageId);
//	}
//
//	/**
//	 * Gibt die RootPage zurück. Es findet keine Prüfung der Permission statt.
//	 * RootPage ist immer die Seite mit der ID 1. todo: alle seiten sollten mit
//	 * permission geladen werden. Daher muss auch diese Anfrage durch
//	 * getPageByPermission() laufen!
//	 * 
//	 * @return
//	 */
//	private Page getRootPageUnchecked()
//	{
//		// todo: sicherstellen, dass index nicht gelöscht werden kann und
//		// dass index immer id 1 ist.
//		// todo: warum index nicht = 0 ???
//		Page page1 = pageDaoImpl.findByPageId(1);
//		return page1;
//	}
//
//	/**
//	 *
//	 * @param pageTitle
//	 * @return
//	 * @throws Exception
//	 * @deprecated
//	 */
//	@Deprecated
//	public int getPageIdByPageTitle(String pageTitle) throws Exception
//	{
//		return this.pageDaoImpl.findByPageTitle(pageTitle).getId();
//	}
//
//	/**
//	 * Gibt eine Page-ArrayList mit dem angeforderten Seitenbaum-Abschnitt
//	 * zurück.todo: auch als Array abrufbar?
//	 * 
//	 * @param startId Index beginnt bei 0
//	 * @param levels
//	 * @return ArrayList
//	 * @deprecated Künftig direkt einen TreeOfPages anfordern!?
//	 */
//	@Deprecated
//	public ArrayList<Page> getPageTree(int startId, int levels)
//	{
//		TreeOfPages top = new TreeOfPages(); // todo: static?
//		return top.getPageTree(startId, levels);
//	}
//
//	/**
//	 *
//	 * @param start
//	 * @param stop
//	 * @param requestController
//	 * @return
//	 */
//	public String
//			getNavMenu(int start, int stop, RequestController requestController)
//	{
//		TreeOfPages pageTree = new TreeOfPages();
//		String nav = pageTree.getNav(start, stop, requestController);
//		// todo: Zwischenablage heraus nehmen!
////        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(
////                new StringSelection(nav), null);
//		return nav;
//	}
//
//	/**
//	 * 403 - error-no-member
//	 * 
//	 * @return
//	 * @throws Exception
//	 */
//	private Page get403Page() throws Exception
//	{
//		try
//		{
////            log.add(this, "get403Page() - try");
//			// todo: fehlerseiten aus page laden!
//			return pageDaoImpl.findByServletPath("/bubbles/http-403");
////            return pageDaoImpl.findByServletPath("/bubbles/handelsfachwirt/fuehrung-personalmanagement-kommunikation-kooperation/fuehrungsmethoden/aufbauorganisation");
//		} catch (Exception e)
//		{
////            log.add(this, "get403Page() - catch - throw e");
//			throw e;
//		}
//	}
//
//	/**
//	 * 404 - error
//	 * 
//	 * @return
//	 * @throws Exception
//	 */
//	private Page get404Page()
//	{
//		try
//		{
////            log.add(this, "get404Page() - try");
//			// todo: fehlerseiten aus page laden!
//			// oder besser noch: fehlerseiten bei servletstart bereits laden!
////            return pageDaoImpl.findByServletPath("/bubbles/error");
//			return pageDaoImpl.findByServletPath("/bubbles/http-404");
//		} catch (Exception e)
//		{
////            log.add(this, "get404Page() - catch - return null");
//			return null;
//		}
//	}
//
//	/**
//	 * Baut einen Servlet-Pfad auf Grundlage des zur jeweiligen Page-Id
//	 * gehörigen htmlTitles.Achtung, die Seite muss bereits in der Datenbank
//	 * vorhanden sein! Soll der ServletPath einer Seite lediglich abgefragt
//	 * werden, sollte das über das jeweilige Page-Objekt geschehen. todo:
//	 * contextPath als Grundlage verwenden?! todo: methode schreibt den neuen
//	 * Pfad direkt in die Datenbank und liefert boolean true zurück.
//	 * 
//	 * @param pageId
//	 * @return String Servlet-Path
//	 * @deprecated Methode birgt zu viele Risiken in der Anwendung, da direkt in
//	 *             DB geschrieben wird.
//	 */
//	@Deprecated
//	public String buildServletPath(int pageId)
//	{
//		return pageDaoImpl.buildServletPath(pageId);
//	}
//
//	/**
//	 * Baut einen Servlet-Pfad auf Grundlage der zum jeweiligen Page-Objektes
//	 * gehörigen Parent-Page.Achtung, beide Seiten müssen anschließend auch so
//	 * in der Datenbank gespeichert werden! Soll der ServletPath einer Seite
//	 * lediglich abgefragt werden, sollte das über das jeweilige Page-Objekt
//	 * geschehen.todo: contextPath als Grundlage verwenden?!
//	 * 
//	 * @param page
//	 * @param parentPage
//	 * @return String Servlet-Path
//	 */
//	public String buildServletPath(Page page, Page parentPage)
//	{
//		// todo: funktioniert die methode?
//		return parentPage.getPath() + "/" + page.getHtmlTitle();
//	}
//
//	/**
//	 * Baut einen URL aus Host-Name und Servlet-Path der zur jeweiligen Page-Id
//	 * gehörigen Seite. Soll der URl lediglich abgefragt werden, so sollte das
//	 * über das jeweilige Page-Objekt geschehen. todo: URL-Encoding mit
//	 * einbeziehen?
//	 * 
//	 * @param pageId
//	 * @return
//	 * @deprecated URL kann an jeder Stelle aus Host-Name und Servlet-Path
//	 *             gebaut werden! URL wir in Page-Objekt nicht gespeichert.
//	 */
//	@Deprecated
//	public String buildUrl(int pageId)
//	{
//		/*
//		 * todo: https nutzen!
//		 */
//		return AppPropertyService.getHostName()
//				+ pageDaoImpl.buildServletPath(pageId);
//
//	}
//
//	/**
//	 * Baut einen URL der Seite aus Host-Name und Servlet-Path. Soll der URl
//	 * lediglich abgefragt werden, so sollte das über das jeweilige Page-Objekt
//	 * geschehen. todo: URL-Encoding mit einbeziehen?
//	 * 
//	 * @param servletPath
//	 * @return servletPath
//	 * @deprecated URL kann an jeder Stelle aus Host-Name und Servlet-Path
//	 *             gebaut werden! URL wir in Page-Objekt nicht gespeichert.
//	 */
//	@Deprecated
//	public String buildUrl(String servletPath)
//	{
//		/*
//		 * todo: https nutzen!
//		 */
//		return AppPropertyService.getHostName() + servletPath;
//	}
//
//	/**
//	 * Löscht eine Seite.Löschauftrag wird über deletePage(Page page) an
//	 * PageDaoImpl weitergegeben.Parent-Ids der Child-Pages werden aktualisiert.
//	 * Pfade der Child-Pages werden angepasst. todo: Sitemap schreiben! todo:
//	 * Reihenfolge umkehren, da PageDaoImpl auf Basis von Ids arbeitet? todo:
//	 * direkt in PageDaoImpl aufrufen? todo: pageTree und PageDaoImpl vereinen.
//	 * 
//	 * @param pageId
//	 * @return
//	 */
//	public boolean deletePage(int pageId)
//	{
//		Page page = pageDaoImpl.findById(Page.class, pageId);
//		boolean deletePage = this.deletePage(page);
//		return deletePage;
//	}
//
//	/**
//	 * Löscht eine Seite. Löschauftrag wird an PageDaoImpl weiter- gegeben.
//	 * Parent-Ids der Child-Pages werden aktualisiert. Pfade der Child-Pages
//	 * werden angepasst. todo: Sitemap schreiben! todo: direkt in PageDaoImpl
//	 * aufrufen? todo: ausnahmebehandlungen in der gesamten klasse durchdenken.
//	 * 
//	 * @param page
//	 * @return
//	 */
//	public boolean deletePage(Page page)
//	{
//		try
//		{
//			pageDaoImpl.deletePage(page);
//			return true;
//		} catch (Exception ex)
//		{
//			Logger.getLogger(PageTreeControllerOld.class.getName())
//					.log(Level.SEVERE, null, ex);
//
//			return false;
//		}
//	}
//
//	/**
//	 * todo: methode löschen ? weil es ein zugriff auf seitendaten ist? besser
//	 * einen pageController einführen, der nur einzelne page daten wie id,
//	 * title, etc abruft und kein ganzes objekt?
//	 * 
//	 * @param servletPath todo
//	 * @return todo
//	 * @throws java.lang.Exception
//	 * @deprecated
//	 */
//	@Deprecated
//	public int getPageIdByServletPath(String servletPath) throws Exception
//	{
//		return this.pageDaoImpl.findByServletPath(servletPath).getId();
//	}
//
//	/**
//	 * Aktualisiert die Sitemap, wenn Seiten angelegt, umbenannt, verschoben,
//	 * oder gelöscht werden. todo: welcher Teil wird umgeschrieben? todo: wo
//	 * wird die methode genutzt?
//	 */
//	public void rewriteSitemap()
//	{
//		try (PrintWriter out = new PrintWriter(
//				"C:\\eclipse-workspace\\bueffeltier\\src\\main\\webapp\\sitemap.txt"
//		))
//		{
//			out.println("test");
//
//			// beachte Struktur von Spiegel online
//			// keine leeren verweise hinterlassen!
//		} catch (java.io.FileNotFoundException e)
//		{
//
//		}
//	}
//
//	/**
//	 *
//	 * @param id
//	 * @return
//	 */
//	public Article getArticle(int id)
//	{
//		ArticleDaoImpl articleDaoImpl = new ArticleDaoImpl();
//		Article article = articleDaoImpl.findById(Article.class, id);
//		return article;
//	}
//
//	/**
//	 *
//	 * @param article
//	 */
//	public void saveArticle(Article article)
//	{
//		ArticleDaoImpl articleDaoImpl = new ArticleDaoImpl();
//		articleDaoImpl.save(article);
//	}
//
//	/**
//	 *
//	 * @param newArticle
//	 * @param newLeadingArticleId
//	 * @return
//	 */
//	public boolean addNewArticleBehindArticle(
//			Article newArticle,
//			int newLeadingArticleId
//	)
//	{
//
//		ArticleDaoImpl articleDaoImpl = new ArticleDaoImpl(); // todo:
//																// pageDaoImpl
//																// und
//																// articleDaoImol
//																// werden doch
//																// im gesamten
//																// Servlet immer
//																// wieder
//																// benötigt.
//																// SOllten die
//																// evtl. in der
//																// Init-Methode
//																// des Servlets
//																// geladen
//																// werden? -
//																// Nein, da
//																// nicht immer
//																// ein Admin
//																// eingeloggt
//																// ist?
//
//		int parentPageId = articleDaoImpl
//				.findById(newArticle.getClass(), newLeadingArticleId)
//				.getParentId();
//
//		List<Article> childArticles = articleDaoImpl.findAllAsc(parentPageId);
//		Article leadingArticle = articleDaoImpl
//				.findById(newArticle.getClass(), newLeadingArticleId);
//
//		int orderShift = 0;
//		for (int i = 0; i < childArticles.size(); i++)
//		{
//			if (childArticles.get(i).getId() == newLeadingArticleId)
//			{
////                int newLeadingArticleOrderValue = leadingArticle.getOrder();
////                leadingArticle.setOrder(newLeadingArticleOrderValue + 1);
//				orderShift = 1;
////                pageDaoImpl.save(childArticles.get(i));
//			} else
//			{
//				childArticles.get(i).setOrder(i + orderShift);
//				articleDaoImpl.save(childArticles.get(i));
//			}
//		}
//		newArticle.setOrder(leadingArticle.getOrder() + 1);
//		newArticle.setParentId(parentPageId);
//		articleDaoImpl.save(newArticle);
////        update(); // todo: update einführen! updateSitemap
//		return true;
//
//////////////////////////////////////////////////////////////////////////////////
////        List<Article> articles = articleDaoImpl.findAllAsc(newParentPageId);
////        for(int i=0; i<articles.size(); i++){
////            int order = articles.get(i).getOrder();
////            articles.get(i).setOrder(order+1);
////            articleDaoImpl.save(articles.get(i));
////        }
////        newArticle.setOrder(0);
////        newArticle.setParentId(newParentPageId);
////
////        articleDaoImpl.save(newArticle);
//////        update();
////        return true;
//	}
//
//	/**
//	 * Erstellt einen neuen Artikel als ersten Artikel einer Seite und schreibt
//	 * für alle anderen Geschwister-Artikel die Order neu.
//	 * 
//	 * @param newArticle
//	 * @param newParentPageId @ param newParentPageId
//	 * @return
//	 */
//	public boolean
//			addNewArticleIntoPage(Article newArticle, int newParentPageId)
//	{
//		ArticleDaoImpl articleDaoImpl = new ArticleDaoImpl();
//
//		articleDaoImpl.save(newArticle);
//		return true;
//
////        ArticleDaoImpl articleDaoImpl = new ArticleDaoImpl();
////        List<Article> articles = articleDaoImpl.findAllAsc(newParentPageId);
////
////        if (articles.size() == 0)
////        {
////            newArticle.setOrder(0);
////            newArticle.setParentId(newParentPageId);
////            articleDaoImpl.save(newArticle);
////            // update();
////        } else
////        {
////            for (int i = 0; i < articles.size(); i++)
////            {
////                int order = articles.get(i).getOrder();
////                articles.get(i).setOrder(order + 1);
////                articleDaoImpl.save(articles.get(i));
////            }
////            newArticle.setOrder(0);
////            newArticle.setParentId(newParentPageId);
////
////            articleDaoImpl.save(newArticle);
////            // update();
////        }
////        return true;
//	}
//
//	/**
//	 *
//	 * @param moveArticleId
//	 * @param behindArticleId
//	 * @return
//	 */
//	public boolean
//			moveArticleBehindArticle(int moveArticleId, int behindArticleId)
//	{
//
////        // Zeitmessung:
////        final long timeStart = System.currentTimeMillis();
////        final long timeEnd = System.currentTimeMillis();
////        // Zeitmessung:
////        JOptionPane.showMessageDialog(null, "Zeitmessung: " + (timeEnd - timeStart) + " Millisek.");
//		ArticleDaoImpl articleDaoImpl = new ArticleDaoImpl();
//
//		Article articleToMove = articleDaoImpl
//				.findById(Article.class, moveArticleId);
//		Article articleToMoveBehind = articleDaoImpl
//				.findById(Article.class, behindArticleId);
//		int idToMoveBehind = articleToMoveBehind.getId();
//		boolean insertOnSamePage = articleToMove
//				.getParentId() == articleToMoveBehind.getParentId();
//
//		List<Article> originPageArticles = articleDaoImpl
//				.findAllAsc(articleToMove.getParentId());
//
//		// Zu verschiebenden Artikel ausschneiden:
//		// todo: private Methode daraus machen!
//		// todo: parametrisierte Klasse daraus machen!
//		// todo: name: ...?
//		originPageArticles.remove(articleToMove.getOrder());
//
//		// Zu verschiebenden Artikel in selber Seite einfügen:
//		if (insertOnSamePage)
//		{
//			for (int i = 0; i < originPageArticles.size(); i++)
//			{
//				if (originPageArticles.get(i).getId() == idToMoveBehind)
//				{
//					int insertIndex = originPageArticles.get(i).getOrder() + 1;
//
//					if (insertIndex > originPageArticles.size())
//					{
//						originPageArticles.add(articleToMove);
//
//					} else
//					{
//						originPageArticles.add(insertIndex, articleToMove);
//					}
//				}
//			}
//			// Wenn ich auf einer anderen Seite einfüge:
//		} else
//		{
//			articleToMove.setParentId(articleToMoveBehind.getParentId());
//			List<Article> destinationPageArticles = articleDaoImpl
//					.findAllAsc(articleToMoveBehind.getParentId());
//
//			// Zu verschiebenden Artikel einfügen:
//			for (int i = 0; i < destinationPageArticles.size(); i++)
//			{
//				if (destinationPageArticles.get(i).getId() == idToMoveBehind)
//				{
//					int insertIndex = destinationPageArticles.get(i).getOrder()
//							+ 1;
//
//					if (insertIndex > destinationPageArticles.size())
//					{
//						destinationPageArticles.add(articleToMove);
//					} else
//					{
//						destinationPageArticles.add(insertIndex, articleToMove);
//					}
//				}
//			}
//			// Artikel-Order der Zielseite aktualisieren:
//			for (int i = 0; i < destinationPageArticles.size(); i++)
//			{
//				destinationPageArticles.get(i).setOrder(i);
//				articleDaoImpl.save(destinationPageArticles.get(i));
//			}
//		}
//
//		// Artikel-Order der Ursprungsseite aktualisieren:
//		for (int i = 0; i < originPageArticles.size(); i++)
//		{
//			originPageArticles.get(i).setOrder(i);
//			articleDaoImpl.save(originPageArticles.get(i));
//		}
//		return true;
//	}
//
//	/**
//	 *
//	 * @param moveArticleId
//	 * @param pageId
//	 * @return
//	 */
//	public boolean moveArticleIntoPage(int moveArticleId, int pageId)
//	{
//		// Zeitmessung:
//		final long timeStart = System.currentTimeMillis();
////                // Zeitmessung:
////                final long timeEnd = System.currentTimeMillis();
////                JOptionPane.showMessageDialog(null, "Zeitmessung: " + (timeEnd - timeStart) + " Millisek.");
//
//		ArticleDaoImpl articleDaoImpl = new ArticleDaoImpl();
//		Article articleToMove = articleDaoImpl
//				.findById(Article.class, moveArticleId);
//		Page pageToMoveInto = pageDaoImpl.findById(Page.class, pageId);
//
//		List<Article> originPageArticles = articleDaoImpl
//				.findAllAsc(articleToMove.getParentId());
//
//		int shift;
//		if (articleToMove.getParentId() == pageToMoveInto.getId())
//		{
//			shift = 1;
//		} else
//		{
//			shift = 0;
//		}
//
//		for (int i = 0; i < originPageArticles.size(); i++)
//		{
//			if (originPageArticles.get(i).getId() == articleToMove.getId())
//			{
//				originPageArticles.get(i).setOrder(0);
//
//				if (articleToMove.getParentId() != pageToMoveInto.getId())
//				{
//					List<Article> alternativePageArticles = articleDaoImpl
//							.findAllAsc(pageId);
//					for (int j = 0; j < alternativePageArticles.size(); j++)
//					{
//						alternativePageArticles.get(j).setOrder(j + 1);
//						articleDaoImpl.save(alternativePageArticles.get(j));
//					}
//					originPageArticles.get(i).setParentId(pageId);
//				}
//				shift--;
//			} else
//			{
//				originPageArticles.get(i).setOrder(i + shift);
//			}
//			articleDaoImpl.save(originPageArticles.get(i));
//		}
//		// // Zeitmessung:
//		// final long timeStart = System.currentTimeMillis();
//		// Zeitmessung:
//		final long timeEnd = System.currentTimeMillis();
////        JOptionPane.showMessageDialog(null, "Zeitmessung: " + (timeEnd - timeStart) + " Millisek.");
//		return true;
//	}
//
//	/**
//	 * Löscht den Artikel.
//	 * 
//	 * @param articleId
//	 * @return
//	 */
//	public boolean deleteArticle(int articleId)
//	{
//		// todo: seo beachten!
//		// das muss alles in eine transaction!
//		// todo: reorderArticles();
//		// todo: artikelzugriff nur über pageTree? oder artikeldaoimpl?
//		// sowie seitenzugriff nur über pagedaoimpl?
//
//		ArticleDaoImpl articleDaoImpl = new ArticleDaoImpl();
//
//		Article article = articleDaoImpl.findById(Article.class, articleId);
//		articleDaoImpl.delete(article);
//
//		List<Article> childArticles = articleDaoImpl
//				.findAllAsc(article.getParentId());
//		// todo: die childs müssen zwingend bearbeitet werden, sonst rollback!
//		if (childArticles != null && childArticles.size() > 0)
//		{
//			for (int i = 0; i < childArticles.size(); i++)
//			{
//				Article article1 = childArticles.get(i);
//				article1.setOrder(i);
//				articleDaoImpl.save(article1);
//			}
//		}
//		return true;
//	}
//}
