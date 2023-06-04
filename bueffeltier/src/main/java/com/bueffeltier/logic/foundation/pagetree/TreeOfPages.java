//package com.bueffeltier.logic.foundation.pagetree;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//
//import com.bueffeltier.crosscutting.AppPropertyService;
//import com.bueffeltier.data.hibernate.daoImpl.PageDaoImpl;
//import com.bueffeltier.data.hibernate.entity.Page;
//import com.bueffeltier.ui.webapp.RequestController;
//
///**
// * PageTree stellt Ausschnitte aus der Seiten-Struktur bereit, definiert durch
// * Start-und End-Level. Wird zur Erstellung aller Navigationen verwendet. Steht
// * in Beziehung zu pageTreeController. todo: threadsafe! todo: verschiedene
// * Methoden (expandStartNode, getPageTree, getNav) vereinen. LevelIterator
// * bereitstellen (von, bis). todo: konstruktoren (von, bis), (von), () todo:
// * expandStartNode(): es wird bisher immer von nur einem Startknoten
// * ausgegeangen. Für Navigationen müssen somit mehrere TreeOfNodes erzeugt
// * werden. todo: Collection "TreeOfPages" um Feld levels erweitern. todo:
// * Abstrakte Klasse TreeOfPages enthält abstrakte Methoden um an die Daten der
// * Childs zu kommen.
// * 
// * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
// */
//public class TreeOfPages
//{
//
//	PageDaoImpl pdi;
//	private Node<Page> startNode;
//	private int size;
//	HashMap<Integer, Node<Page>> horizontalNodes;
//	HashMap<Integer, Node<Page>> verticalNodes;
//	HashMap<Integer, ArrayList<Node<Page>>> levelNodes;
//
////    public static LogFrame log = LogFrame.getInstance();
//	private RequestController requestController;
//
//	/**
//	 * Konstruktor
//	 */
//	public TreeOfPages()
//	{
//		pdi = new PageDaoImpl(); // todo: sollte pdi nur einmal in der App
//									// vorkommen?
//		this.horizontalNodes = new HashMap<>();
//		this.verticalNodes = new HashMap<>();
//		this.levelNodes = new HashMap<>();
//	}
//
//	/**
//	 *
//	 * @param startId
//	 */
//	public void buildTree(int startId)
//	{
//		this.buildStartNode(startId);
//		this.expandStartNode(startNode);
//		this.setVerticalIndices();
//	}
//
//	/**
//	 * Liest die Start-Page aus der Datenbank und bildet daraus die Start-Node.
//	 * Das Level der StartNode wird auf 0 gesetzt. Der Begriff RootNode wird
//	 * hier nicht mehr verwendet, da ein Vergleich zur RootPage aus der
//	 * Datenbank gezogen werden könnte.
//	 */
//	private void buildStartNode(int startId)
//	{
//		// Node erschaffen und Item setzen:
//		// Level der Node setzen:
//		this.startNode = new Node(pdi.findByPageId(startId));
//		this.startNode.setLevel(0);
//		startNode.getItem().setLevel(0);
//		ArrayList<Node<Page>> arrayList;
//		if (this.levelNodes.containsKey(0))
//		{
//			arrayList = this.levelNodes.get(0);
//		} else
//		{
//			arrayList = new ArrayList<>();
//			this.levelNodes.put(0, arrayList);
//		}
//		arrayList.add(startNode);
//		// Setze Horizontalen Index der Node:
//		this.startNode.setHorizontalIndex(0);
//		// Node in die Collection aufnehmen:
//		this.horizontalNodes.put(0, startNode);
//
//		// Size der Collection setzen:
//		this.size = 1;
//	}
//
//	/**
//	 * Hilfsmethode zur Nav-Generierung.
//	 * 
//	 * @param passedNode
//	 * @param navString
//	 * @param startLevel
//	 * @param stopLevel
//	 * @param passedLevel
//	 * @param passedPath
//	 * @deprecated
//	 */
//	private void getHtmlNavigationBody(
//			Node<Page> passedNode,
//			StringBuilder navString,
//			int startLevel,
//			int stopLevel,
//			int passedLevel,
//			String passedPath
//	)
//	{
//		// Level und Title immer mitgeben, jedoch nur schreiben, wenn Level ok:
//		// HTML-Liste für übergebene Node öffnen und neuen Link angeben:
//		// ...Nur wenn Level gewünscht ist wird geschrieben:
//		if (passedLevel >= startLevel && passedLevel <= stopLevel)
//		{
//			navString.append("\n<ul>\n") // ul wird eröffnet
//					.append("<li><a href=\"")
//					// .append("/") // todo: in die vorläufer-methode
//					// verschieben. servletPath mitgeben!
//					.append(passedPath); // Der übergegebene Pfad ist der
//											// aktuelle Pfad
//
//			if (requestController.request.getSession(false) != null
//					&& requestController.doUrlRewriting)
//			{
//				navString.append(";jsessionid=").append(
//						requestController.request.getSession(false).getId()
//				);
//			}
//
//			navString.append("\">").append(passedNode.getItem().getHtmlTitle()) // Der
//																				// Titel
//																				// wird
//																				// nicht
//																				// übergeben/-nommen
//																				// sondern
//																				// aus
//																				// der
//																				// passedNode
//																				// bezogen.
//					.append("</a>");
//		}
//		// Der Pfad jedoch wird immer fortgeschrieben: (todo: in den nodes immer
//		// den Pfad speichern!!!!)
//
//		// Kind-Liste zur aktuellen Node erstellen:
//		Node<Page>[] children = passedNode.getChildren();
//
//		// Wenn der aktuelle Knoten Kinder hat wird diese Methode für jedes Kind
//		// erneut aufgerufen und der bisherige Pfad mitgegeben:
//		if (children.length > 0)
//		{
//			for (int i = 0; i < children.length; i++)
//			{
//
//				// todo: if-Abfrage hier weglassen, da oben bereits geprüft?
//				// oder muss ich bei der prüfung bereits hier das nächste level
//				// prüfen?
//				// fehler: ich prüfe hier jedes Kind auf dem selben level auf
//				// das level!!!
//				if (passedLevel >= startLevel && passedLevel <= stopLevel)
//				{ // todo: ausprobieren, ob weggelassen werden kann!
////                            .append(passedTitle) // hier wird der pfad übernommen...
////                            .append("/")
////                            .append(children[i].getItem().getHtmlTitle()) // ... hier weitergeführt.
//////                            .append("/")
////                            .append("\">")
////                            .append(children[i].getItem().getHtmlTitle())
////                            .append("</a>");
//				}
//
//				// Ans nächste Level zu übergebende Daten:
//				// todo: passedLevel als eckpunkt für horizontalIterator nutzen.
//				// von dort aus dann navigationsdaten oder seitendaten abrufen.
//				int passLevel = passedLevel + 1;
//
//				String passPath = passedPath + "/"
//						+ children[i].getItem().getHtmlTitle(); // passPath ist
//																// sozusagen der
//																// "nextPath"
//
//				this.getHtmlNavigationBody(
//						children[i], navString, startLevel, stopLevel,
//						passLevel, passPath
//				);
//
//				// todo: Welches list-item wird hier geschlossen? Jedes?
//				if (passedLevel >= startLevel && passedLevel <= stopLevel)
//				{
//					navString.append("</li>\n");
//				}
//			}
//		}
//		// Ende des Levels:
//		if (passedLevel >= startLevel && passedLevel <= stopLevel)
//		{
//			navString.append("</ul>\n"); // (jede) ul wird geschlossen
//		}
//	}
//
//	/**
//	 * Liefert HTML-Code für ein Navigationselement.
//	 * 
//	 * @param startLevel
//	 * @param stopLevel
//	 * @param requestController
//	 * @return
//	 * @deprecated
//	 */
//	public String getNav(
//			int startLevel,
//			int stopLevel,
//			RequestController requestController
//	)
//	{
//		this.buildStartNode(1);
//		this.expandStartNode(startNode);
//		this.requestController = requestController;
//		// testweise können hier die Levels manipuliert werden:
//		startLevel = 0;
//		stopLevel = 10;
//		// todo: wenn eingaben level 0 bis 0 sind, dann exception
//		StringBuilder navString = new StringBuilder();
//		String passPath = AppPropertyService.getHostName()
//				+ AppPropertyService.getServletPath() + "/"
//				+ startNode.getItem().getHtmlTitle();
//		int passLevel = 0; // ist immer null, da der Pfad in der nächsten
//							// Methode immer von der Root beginnend geschrieben
//							// werden muss.
//		navString.append("<nav>");
//		// todo: wenn root existiert!
//		this.getHtmlNavigationBody(
//				startNode, navString, startLevel, stopLevel, passLevel, passPath
//		);
//		navString.append("</div></nav>\n");
//		return navString.toString();
//	}
//
//	/**
//	 * Gibt den Angeforderten Page-Tree als Array zurück.
//	 * 
//	 * @param startId   Index beginnt bei 0
//	 * @param maxLevels
//	 * @return
//	 */
//	public ArrayList<Page> getPageTree(int startId, int maxLevels)
//	{
//		return getPageTreeInternal(startId, maxLevels, false, 4, false);
//	}
//
//	/**
//	 * Gibt den Angeforderten Page-Tree als Array zurück.Es werden nur Seiten
//	 * zurück gegeben, die in der Seiten-Navigation angezeigt werden.
//	 * 
//	 * @param startId    Index beginnt bei 0
//	 * @param maxLevels
//	 * @param navOnly
//	 * @param permission
//	 * @return
//	 */
//	public ArrayList<Page> getPageTree(
//			int startId,
//			int maxLevels,
//			boolean navOnly,
//			int permission
//	)
//	{
//		return getPageTreeInternal(
//				startId, maxLevels, navOnly, permission, false
//		);
//	}
//
//	/**
//	 * Gibt den Angeforderten Page-Tree als Array zurück. Gebaut wird dieser in
//	 * der privaten Methode buildPageTree. todo: diese methode und get nav
//	 * vereinen oder verketten, mit und ohne html, stufe 1 stufe 2 ,... todo:
//	 * Ermöglichen, nur einzelne Spalten anzufordern. todo: Können Artikel ggf.
//	 * mit geladen werden? todo: Methode überladen und levels durch open-end zu
//	 * ersetzten. todo: maxLevels (levels) benutzen. todo: methode überladen für
//	 * published, noNav, hide, etc...
//	 * 
//	 * @param startId Index beginnt bei 0
//	 * @param levels
//	 * @param navOnly
//	 * @return ArrayList
//	 */
//	private ArrayList<Page> getPageTreeInternal(
//			int startId,
//			int maxLevels,
//			boolean navOnly,
//			int permission,
//			boolean publishedOnly
//	)
//	{
//		// todo: wenn eingaben level 0 bis 0 sind, dann exception?
//
//		// Gesprerrte Seiten nicht anzeigen (Nav, Permission, Published) ->
//		// Anforderung TreeOfPages!
//		this.buildStartNode(startId);
//		this.expandStartNode(startNode); // todo: sollte ExpandStartNode()
//		// bereits Teil dieser Methode sein? (doppelter Code!)
//		ArrayList<Page> returnPageTree = new ArrayList<>();
//		writePageTree(
//				this.startNode, maxLevels, 0, returnPageTree, navOnly,
//				permission, publishedOnly
//		);
////        for (int i = 0; i <= returnPageTree.size(); i++)
////        {
////            JOptionPane.showMessageDialog(null, returnPageTree.get(i).getHtmlTitle());
////        }
//		return returnPageTree;
//	}
//
//	/**
//	 * Füllt das übergebene Page-Tree-Array mit den angeforderten Page-Objekten.
//	 * todo: diese methode und get nav vereinen oder verketten, mit und ohne
//	 * html, stufe 1 stufe 2 ,... todo: Ermöglichen, nur einzelne Spalten
//	 * anzufordern. todo: Können Artikel ggf. mit geladen werden?
//	 */
//	private void writePageTree(
//			Node<Page> startNode,
//			int maxLevels,
//			int passedLevel,
//			ArrayList<Page> pageTree,
//			boolean navOnly,
//			int permission,
//			boolean publishedOnly
//	)
//	{
//		if (passedLevel > maxLevels)
//		{
//			return;
//		}
//		// Nur die erste Node wird einzeln hinzugefügt:
//		if (passedLevel == 0)
//		{
//			// Seitenausschluss prüfen:
//			if (excludePage(
//					startNode.getItem(), navOnly, permission, publishedOnly
//			))
//			{
//				return;
//			}
//			pageTree.add(startNode.getItem());
//		}
//
//		Node<Page>[] children = startNode.getChildren();
//		passedLevel++;
//		if (children.length > 0)
//		{
//			for (int i = 0; i < children.length; i++)
//			{
//				// todo: direkteren weg, auf die pages zuzugreifen? aus dao
//				// heraus?
//				// wenn Navigation true:
//				// Seitenausschluss prüfen:
//				if (excludePage(
//						children[i].getItem(), navOnly, permission,
//						publishedOnly
//				))
//				{
//					continue;
//				}
//				pageTree.add(children[i].getItem());
//				writePageTree(
//						children[i], maxLevels, passedLevel, pageTree, navOnly,
//						permission, publishedOnly
//				);
//			}
//		}
//	}
//
//	/**
//	 * Erstellt rekursiv den Seitenbaum aus der StartNode. todo: Bereits hier
//	 * ungewünschte Seiten ausschließen ? (Nav, published, etc.) todo: rename
//	 * parent=currentNode
//	 * 
//	 * @param parent
//	 * @param parentsVerticalIndex Bei Root = 0
//	 * @param parentsSiblingsSize  Bei Root = 0
//	 */
//	private void expandStartNode(Node<Page> parent)
//	{
//		List<Page> siblings = pdi.getChildPagesAsc(parent.getItem().getId());
//
////        if (pdi.hasChildPages(parent.getItem().getId()))
//		if (siblings.size() > 0)
//		{
//			for (int i = 0; i < siblings.size(); i++)
//			{
//				Node<Page> node = new Node(parent);
//				// Size der Collection erhöhen:
//				this.size++;
//				// Item und Level der Node setzen:
//				node.setItem(siblings.get(i));
//				int level = parent.getLevel() + 1;
//				node.setLevel(level);
//				// Vertikalen Index für Node errechnen:
//				// in weiterer Methode aus levelNodes berechnen.
//				//
//				// Ggf. benötigte Level-ArrayList in levelNodes anlegen:
//				// Jede Node wird der richtigen Level-Array-List zugeordnet.
//				ArrayList<Node<Page>> arrayList;
//				if (this.levelNodes.containsKey(level))
//				{
//					arrayList = this.levelNodes.get(level);
//				} else
//				{
//					arrayList = new ArrayList<>();
//					this.levelNodes.put(level, arrayList);
//				}
//				arrayList.add(node);
//				// Setze Horizontalen Index der Node:
//				node.setHorizontalIndex(this.size - 1);
//				// Node in die Collection aufnehmen:
//				this.horizontalNodes.put(this.size - 1, node);
//				// todo: Item (Page) bekommt Level, horozontalIndex und
//				// verticalIndex
//				node.getItem().setLevel(parent.getLevel() + 1);
//				// Node der Eltern-Node als Kind zuweisen:
//				parent.addChild(node);
//				// Node weiter expandieren:
//				expandStartNode(node);
//			}
//		}
//	}
//
//	/**
//	 * Durchläuft die horizontalNodes nach dem Expandieren, setzt die vertikalen
//	 * Indizes und fügt die Nodes in "verticalNodes" ein.
//	 */
//	private void setVerticalIndices()
//	{
//		int currentVerticalIndex = 0;
//		// LevelNodes beginnend von Level 0 durchlaufen:
////        JOptionPane.showMessageDialog(null, "levelnodes size: " + levelNodes.size());
//		for (int i = 0; i < levelNodes.size(); i++)
//		{
//			ArrayList<Node<Page>> levelList = levelNodes.get(i);
//
//			for (int j = 0; j < levelList.size(); j++)
//			{
//				Node<Page> node = levelList.get(j);
//				node.setVerticalIndex(currentVerticalIndex);
//				verticalNodes.put(currentVerticalIndex, node);
////                JOptionPane.showMessageDialog(null, "verticalIndex: " + node.getVerticalIndex() + " ist id: " + node.getItem().getId());
//				currentVerticalIndex++;
//			}
//		}
//	}
//
//	/**
//	 * Prüft, ob Seite aus dem Seitenbaum ausgeschlossen wird. Ist eine Seite
//	 * ausgeschlossen, so sind auch deren Unterseiten ausgeschlossen.
//	 * 
//	 * @param navOnly
//	 * @param permission
//	 * @param published
//	 * @return
//	 */
//	private boolean excludePage(
//			Page page,
//			boolean navOnly,
//			int requestPermission,
//			boolean publishedOnly
//	)
//	{
////        JOptionPane.showMessageDialog(null, "navOnly = " + navOnly);
////        JOptionPane.showMessageDialog(null, "page is hidden = " + page.isHiddenInNav());
//		if (requestPermission < page.getPermission())
//		{
////            JOptionPane.showMessageDialog(null, page.getHtmlTitle() + " ist gesperrt wegen fehlender permission.");
//			return true;
//		}
//		if (navOnly && page.isHideInNav())
//		{
////            JOptionPane.showMessageDialog(null, page.getHtmlTitle() + " ist in navigation gesperrt.");
//			return true;
//		}
//		if (publishedOnly & page.isPublished() == false)
//		{
////            JOptionPane.showMessageDialog(null, page.getHtmlTitle() + " ist nicht veröffentlicht.");
//			return true;
//		}
////        JOptionPane.showMessageDialog(null, page.getHtmlTitle() + " nichts ist ausgeschlossen, return exclude = false.");
//		return false;
//	}
//
//	/**
//	 *
//	 * @return
//	 */
//	public int size()
//	{
//		return this.size;
//	}
//
//	/**
//	 * Gibt einen Horizontalen Iterator des PageTrees zurück.
//	 * 
//	 * @return
//	 */
//	public Iterator<Node<Page>> horizontalIterator()
//	{
//		Iterator<Node<Page>> iterator = new Iterator<Node<Page>>()
//		{
//			int indexPointer = 0;
//
//			@Override
//			public boolean hasNext()
//			{
//				if (indexPointer < size)
//				{
//					return true;
//				} else
//				{
//					indexPointer = 0;
//					return false;
//				}
//			}
//
//			@Override
//			public Node<Page> next()
//			{
//				int currentIndex = indexPointer;
//				indexPointer++;
//				return horizontalNodes.get(currentIndex);
//			}
//
////            @Override
////            public void remove()
////            {
////
////            }
//		};
//		return iterator;
//	}
//
//	/**
//	 * Gibt einen Vertikalen Iterator des PageTrees zurück.
//	 *
//	 * @return
//	 */
//	public Iterator<Node<Page>> verticalIterator()
//	{
//		Iterator<Node<Page>> iterator = new Iterator<Node<Page>>()
//		{
//			int indexPointer = 0;
//
//			@Override
//			public boolean hasNext()
//			{
//				if (indexPointer < size)
//				{
//					return true;
//				} else
//				{
//					indexPointer = 0;
//					return false;
//				}
//			}
//
//			@Override
//			public Node<Page> next()
//			{
//				int currentIndex = indexPointer;
//				indexPointer++;
//				return verticalNodes.get(currentIndex);
//			}
//
////            @Override
////            public void remove()
////            {
////
////            }
//		};
//		return iterator;
//	}
//}
