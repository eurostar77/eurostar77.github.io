package com.bueffeltier.logic.foundation.pagetree;

//package com.mycompany.bubbles.servlet.pagetree;
//
//import com.mycompany.bubbles.database.daoImpl.PageDaoImpl;
//import com.mycompany.bubbles.database.entity.Page;
//import com.mycompany.bubbles.servlet.LogFrame;
//import com.mycompany.bubbles.servlet.RequestController;
//import com.mycompany.bubbles.servlet.Settings;
//import java.io.PrintWriter;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//import javax.swing.JOptionPane;
//
///**
// * Repräsentiert den Seitenbaum und enthält alle Page-Objekte, die zur Website
// * gehören.todo: Ich schreibe zuerst einen Baum Speziell für Page-Nodes.
// *
// * todo: Plan der Umstellung auf NewPageTree:
// *       Zuerst nur durch Pfade auf die Seiten zugreifen.
// *       Später auch Suche über die ID ermöglichen. Was geht schneller?
// *
// *       Klasse parametrisieren und somit generalisieren.
// *       Node-Funktionen kommen in die Mutterklasse
// *       Page-Funktionen kommen in die abgeleitete Klasse.
// *
// todo: Klasse PageTree parametrisieren, für das nodeItem
// todo: nur page-verweise verwalten, nicht alle objekte mit deren
// inhalten, sondern nur verwendete seiten zwischenspeichern.
// todo: zugriff durch mehrere nutzer möglich machen. -> thread-safe!
// *
// * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
// */
//public class NewPageTree implements Iterable<Node<Page>>
//{
//
//    private boolean storePagesInMemory = true;
//
//    HashMap<String, Node<Page>> stringNodes = new HashMap();
//    HashMap<Integer, Node<Page>> integerNodes = new HashMap();
//
//    private Node<Page> rootNode;
//    private Node<Page> head; // todo: für iterator nutzen.
//    PageDaoImpl pageDaoImpl;
//    public static LogFrame log = LogFrame.getInstance();
//    private RequestController requestController;
//
//    /**
//     *  Konstruktor
//     */
//    public NewPageTree()
//    {
//        // klasse ableiten, damit auf felder der elternklasse nicht zugegriffen werden
//        // kann.
//        // ...
//    }
//
//    public void buildPageTree()
//    {
//        /*
//         * todo: Unterscheide Laden durch User und Laden durch db.
//         * todo: Ausnahmebehandlung wenn noch keine RootId in DB!
//         */
//        // todo: achtung! unterscheide laden aus db und erstellen durch User !
//
//    }
//
//    /*
//     * Legt die root-page im Page-Tree an.
//     */
//    public void buildRoot()
//    {
//        Page rootPage = pageDaoImpl.findByPageId(findRootId());
//        rootNode = new Node(rootPage);
//        rootNode.setLevel(0);
//        rootNode.getItem().setLevel(0);
//        rootNode.setRoot(rootNode); // todo: kann rootNode sich selbst als rootNode haben?
//        addNode(rootNode); // todo: neue methode sorgt für korrekte initialisierung der Page.
//
//        // todo: welche initialisierungsparameter muss ich noch setzen?
//    }
//
//    /*
//     * Fügt eine Node beiden HashMaps hinzu.
//     * - Pages müssen voll initialisiert sein!
//     */
//    private void addNode(Node<Page> node)
//    {
//        // todo:? Node darauf prüfen, ob es ein item enthält und ob die items
//        //       richtig mit parent, root und childPages initialisiert ist.?
//        stringNodes.put(node.getItem().getSitePath(), rootNode);
//        integerNodes.put(node.getItem().getId(), rootNode);
//    }
//
//    /**
//     * Erstellt den kompletten Seitenbaum aus der RootNode.
//     * todo: Aus dieser Methode den Iterator entwickeln.
//     * todo: Wie tread-safe machen?
//     * todo: Ersetzen durch Methode expandNode() // private iterateNode
//     * @param parentNode
//     */
//    public void expandRootNode(Node<Page> parentNode)
//    {
//        // Es wird die parentNode übergeben. Das kann auch aus PageDaoImpl geschehen.
//        int level = parentNode.getLevel() + 1;
//
//        if (pageDaoImpl.hasChildPages(parentNode.getItem().getId()))
//        {
//
//            List<Page> childPages
//                    = pageDaoImpl.getChildPagesAsc(parentNode.getItem().getId());
//
//            for (int i = 0; i < childPages.size(); i++)
//            {
//
//                Node<Page> childNode
//                        = new Node(
//                                this.rootNode, parentNode, childPages.get(i));
//                childNode.setLevel(level);
//                childNode.getItem().setLevel(level);
//
//                parentNode.addChild(childNode);
//
//                stringNodes.put(childNode.getItem().getSitePath(), childNode);
//                integerNodes.put(childNode.getItem().getId(), childNode);
//
//                expandRootNode(childNode);
//            }
//        }
//    }
//
//    public Page get(int pageId, int requestPermission) throws Exception
//    {
//        log.add(this, "getPage( " + pageId + " )");
//
//        Page requestedPage = integerNodes.get(pageId).getItem();
//
//        if (requestPermission >= requestedPage.getPermission())
//        {
//            log.add(this, "getPage() = succeeded");
//            return requestedPage;
//
//        } else
//        {
//            log.add(this, "getPage - failed");
//
//            throw new Exception("Page not found!");
//        }
//    }
//
//    /*
//     * Liefert das angeforderte Page-Objekt aus dem PageTree
//     */
//    public Page get(
//            String pagePath, int requestPermission) throws Exception
//    {
//        log.add(this, "getPage( " + pagePath + " ) todo: rename to servletPath?");
//
//        Page requestedPage = stringNodes.get(pagePath).getItem();
//
//        if (requestPermission >= requestedPage.getPermission())
//        {
//            log.add(this, "getPage() = succeeded");
//            return requestedPage;
//
//        } else
//        {
//            log.add(this, "getPage - failed");
//
//            throw new Exception("Page not found!");
//        }
//    }
//
//    /*
//     * Es gibt gar keinen Bedarf für diese Funktion, denn Seiten sollen immer
//     * über die Klasse PageTree erstellt und geändert werden.
//     * Jedoch gibt er auch die Funktion getPage(), -braucht man diese wirklich?
//     */
////    public void savePage(Page page) throws Exception
////    {
////        String key = page.getSitePath();
////
////        if (pages.containsKey(key))
////        {
////            // Wenn die Seite bereits vorhanden ist:
////            // todo: Genauer überprüfen, dass Seitenobjekt einzigartig ist!
////
////            // Datenbank aktualisieren:
////            pageDaoImpl.save(page);
////
////            // Erst nach erfolgreicher Speicherung in db die Node ändern!
////            // Ansonsten macht db rollback und die Seite kann nicht gespeichert
////            // werden.
////            pages.get(key).setItem(page);
////
////            /* Mein Node-Objekt soll erkennen, ob daten am item geändert werden.
////             * Deshalb wird mein Node-Objekt von Node abgeleitet und fügt
////             * Methoden wie zB. saveChangedDataToDB hinzu.
////             */
////        } else
////        {
////
////        }
////        pages.pages.put(page.getSitePath(), head);
////        /*
////        * bei jeder Änderung an den Daten von "pages" müssen die
////        * betroffenen Page-Objekte in der Datenbank gespeichert werden
////        * auf zu speichernde seiten darf temporär nicht zugegriffen werden
////         */
////        .
////        pageDaoImpl.save(page);
////    }
//    /*
//     * Liefert die index-Seite der Webanwendung.
//     */
//    public Page getIndexPage()
//    {
//        return rootNode.getItem();
//    }
//
//    private boolean add(Page newPage, int parentPageId, int order)
//    {
//        // todo: denke dir eine sichere schreib- lese-strategie aus.
//        // Operation läuft zuerst über PageDaoImpl:
//        newPage.setOrder(order);
//        newPage.setParentId(parentPageId);
//        pageDaoImpl.add(newPage, parentPageId, order);
//        // Operation soll komplett in einer transactioin durchgeführt werden.
//        //
//        // Zuerst werden die Daten in der Datenbank geändert.
//        //// Methode durchläuft zuerst alle geänderten Seiten in DB.
//        //////
//        //////
//        //////
//        //// Methode durchläuft dann alle geänderten Seiten im Speicher.
//        ////// Erstelle neue Node und füge die neue Seite ein. - Beachte mögliche Konstruktoren!
//        ////// beziehe ParentPage und ihre Node.
//        ////// speichere die neue Node in den Children der Parent Node.
//        ////// denke an die Reihenfolge der Children!
//        ////// Setze Level in neuer Node und Seite.
//        ////// Setze Pfad in neuer Seite. -> Wirklich pfad in Seite setzen?
//        ////// Setze RootNode zur neuen Node.
//        ////// Setze ParentNode zur neuen Node.
//        //// Ggf. Rollback in DB und im Speicher.
//        //// rückgabe true/false
//
//        int orderShift = 0;
//        for (int i = 0; i < childPages.size(); i++)
//        {
//            if (childPages.get(i).getId() == newLeadingPage.getId())
//            {
//                int newLeadingPageOrder = newLeadingPage.getOrder();
//                newLeadingPage.setOrder(newLeadingPageOrder + 1);
//                orderShift = 1;
//                pageDaoImpl.save(childPages.get(i));
//            } else
//            {
//                childPages.get(i).setOrder(i + orderShift);
//                pageDaoImpl.save(childPages.get(i));
//            }
//        }
//        newPage.setOrder(newLeadingPage.getOrder() + 1);
//        newPage.setParentId(newLeadingPage.getParentId());
//        pageDaoImpl.save(newPage);
//        update(); // todo: update einführen! updateSitemap
//        return true;
//    }
//
//    /**
//     * Fügt eine Seite in eine andere Seite ein. Die eingefügte Seite wird zur
//     * Kind-Seite der Eltern-Seite.
//     * @param newPage
//     * @param parentPageId
//     * @return
//     */
//    public boolean addNewPageInto(Page newPage, int parentPageId)
//    {
//        // Operation läuft zuerst über PageDaoImpl:
//        // Operation soll komplett in einer transactioin durchgeführt werden.
//        //
//        // Zuerst werden die Daten in der Datenbank geändert.
//        //// Methode durchläuft zuerst alle geänderten Seiten in DB.
//        //////
//        //////
//        //////
//        //// Methode durchläuft dann alle geänderten Seiten im Speicher.
//        ////// Erstelle neue Node und füge die neue Seite ein. - Beachte mögliche Konstruktoren!
//        ////// beziehe ParentPage und ihre Node.
//        ////// speichere die neue Node in den Children der Parent Node.
//        ////// denke an die Reihenfolge der Children!
//        ////// Setze Level in neuer Node und Seite.
//        ////// Setze Pfad in neuer Seite. -> Wirklich pfad in Seite setzen?
//        ////// Setze RootNode zur neuen Node.
//        ////// Setze ParentNode zur neuen Node.
//        //// Ggf. Rollback in DB und im Speicher.
//        //// rückgabe true/false
//        List<Page> childPages = pageDaoImpl.getChildPagesAsc(parentPageId); // in reihenfolge laden!
//        for (int i = 0; i < childPages.size(); i++)
//        {
//            int order = childPages.get(i).getOrder();
//            childPages.get(i).setOrder(order + 1);
//            pageDaoImpl.save(childPages.get(i));
//        }
//        newPage.setOrder(0);
//        newPage.setParentId(parentPageId);
//        pageDaoImpl.save(newPage);
//        update(); // todo: update einführen! updateSitemap
//        return true;
//    }
//
//    /**
//     * Fügt eine Seite in eine andere Seite ein. Die eingefügte Seite wird zur
//     * Kind-Seite der Eltern-Seite.
//     * @param newPage
//     * @param parentPagePath
//     * @return
//     */
//    public boolean addNewPageInto(Page newPage, String parentPagePath)
//    {
//        // 1. Datenbank-Operationen durchführen:
//        // 2. Wenn db=ok, -Node-Operationen durchführen.
//        // 3. Thead-Safety!
//        // 4. Rollback! -Teilbaum zum Ende einfügen. Transaktion erst dann
//        //               schließen. Schreibschutz auf den gesamten Unterbaum?
//        List<Page> childPages = pageDaoImpl.getChildPagesAsc(parentPagePath);
//        for (int i = 0; i < childPages.size(); i++)
//        {
//            int order = childPages.get(i).getOrder();
//            childPages.get(i).setOrder(order + 1);
//            pageDaoImpl.save(childPages.get(i));
//        }
//        newPage.setOrder(0);
//        newPage.setParentId(parentPagePath);
//        pageDaoImpl.save(newPage);
//        update(); // todo: update einführen! updateSitemap
//        return true;
//    }
//
//    public boolean movePageIntoPage(int pageToMoveId, int newParentPageId)
//    {
//        // Zeitmessung:
//        final long timeStart = System.currentTimeMillis();
////                // Zeitmessung:
////                final long timeEnd = System.currentTimeMillis();
////                JOptionPane.showMessageDialog(null, "Zeitmessung: " + (timeEnd - timeStart) + " Millisek.");
//
//        Page pageToMove = pageDaoImpl.findByPageId(pageToMoveId);
//        Page newParentPage = pageDaoImpl.findByPageId(newParentPageId);
//
//        // Todo: Die Root-Page anders definieren! Oder einmal fest setzen!
//        if (pageToMoveId == 0 || pageToMoveId == newParentPageId)
//        {
//            log.add(this, "ERROR - newParentPageId = pageToMove oder pageToMove ist Seite 1");
//            // todo: bisher ist das problem seiteId 0 und 1 als mutterelement nicht gelöst!
//            // vielleicht bekommt die root page einen Status-Root? dieser verhindert verschieben, etc.
//            return false;
//
//        } else
//        {
//            List<Page> childPages = pageDaoImpl.getChildPagesAsc(newParentPage.getId());
//            int shift = 1;
//            for (int i = 0; i < childPages.size(); i++)
//            {
////                falsch! ich suche nach der neuen ordnung
//                if (childPages.get(i).getId() == pageToMoveId)
//                {
//                    shift = 0;
//                } else
//                {
//                    childPages.get(i).setOrder(i + shift);
//                    pageDaoImpl.save(childPages.get(i));
//                }
//            }
//            pageToMove.setOrder(0);
//            pageToMove.setParentId(newParentPage.getId());
//            pageDaoImpl.save(pageToMove);
//            // todo: achtung: google pageranking - alte seite im system belassen und redirect auf neue seite setzen.
//            // werden alles Links und die Navigationen angepasst?
//            // die sitmap muss neu geschrieben werden. (feld last version in page)
//            update(); //todo: update einführen! updateSitemap
////            // Zeitmessung:
////                final long timeStart = System.currentTimeMillis();
//            // Zeitmessung:
//            final long timeEnd = System.currentTimeMillis();
//            JOptionPane.showMessageDialog(null, "Zeitmessung: " + (timeEnd - timeStart) + " Millisek.");
//            return true;
//        }
//    }
//
//    // todo: brauche ich je eine delete funktion für id und name?
//    // todo: wenn ja, dann sollen beide funktionen eine unterfunktion zum
//    //       löschen ansprechen!
//    public boolean deletePage(int pageId)
//    {
//        Page page = pageDaoImpl.findById(Page.class, pageId);
//        deletePage(page);
//        return true;
//    }
//
//    public boolean deletePage(Page page)
//    {
//        // todo: seo beachten!
//        // das muss alles in eine transaction!
//
//        // todo: funktion, die einen ganzen Seiten-Baum-Zweig löscht.
//        // wenn zu löschende seite unterseiten hat, -parent page in den childs ändern.
//        List<Page> childPages = pageDaoImpl.getChildPagesAsc(page.getId());
//        if (childPages == null || childPages.size() == 0)
//        {
//            for (int i = 0; i < childPages.size(); i++)
//            {
//                childPages.get(i).setParentId(page.getParentId());
//            }
//        }
////         childPages in
////
////
////        pageDaoImpl.delete(page);
////
////        wenn page childs hat...hat
////
////                löschen zuletzt, rollback!
//        return true;
//    }
//
//    // todo: auch in anderen methoden nutzen!
//    private void reorderChildPages(int parentPageId)
//    {
//        List<Page> childPages = pageDaoImpl.getChildPagesAsc(parentPageId);
//        int order = 0;
//        for (int i = 0; i < childPages.size(); i++)
//        {
//            childPages.get(i).setOrder(order);
//            order++;
//        }
//    }
//
//    public void writeSitemapXML()
//    {
//        try (PrintWriter out
//                = new PrintWriter("C:\eclipse-workspace\bueffeltier\src\main\webapp\\sitemap.txt"))
//        {
//            out.println("test");
//
//            // beachte Struktur von Spiegel online
//            // keine leeren verweise hinterlassen!
//        } catch (java.io.FileNotFoundException e)
//        {
//
//        }
//    }
//
//    // todo: diese methode und get nav vereinen oder verketten, mit und ohne html, stufe 1 stufe 2 ,...
//    public void getPageTree(Node<Page> root, ArrayList<Page> tree)
//    {
////        ebene einfügen
////                nicht mehr page zurückgeben, sondern list objekt?
////                artikel mit laden!
//        Node<Page>[] children = root.getChildren();
//        if (children.length > 0)
//        {
//            for (int i = 0; i < children.length; i++)
//            {
//                // todo: direkteren weg, auf die pages zuzugreifen? aus dao heraus?
//                tree.add(children[i].getItem());
//                getPageTree(children[i], tree);
//            }
//        }
//    }
//
////    private void nodeIterator (){
////
////    }
//    public Node<Page> getRootNode()
//    {
//        return rootNode;
//    }
//
//    public void setRootNode(Node<Page> rootNode)
//    {
//        this.rootNode = rootNode;
//    }
//
////    @Override
////    public Iterator iterator()
////    {
////        return new NewPageTreeIterator();
////    }
////
////    @Override
////    public void forEach(Consumer action)
////    {
////        Iterable.super.forEach(action); //To change body of generated methods, choose Tools | Templates.
////    }
////
////    @Override
////    public Spliterator spliterator()
////    {
////        return Iterable.super.spliterator(); //To change body of generated methods, choose Tools | Templates.
////    }
////
////    private class NewPageTreeIterator implements Iterator<Page>
////    {
////
////        Node<Page> node = head;
////
////        @Override
////        public boolean hasNext()
////        {
////            return node != null;
////        }
////
////        @Override
////        public Page next()
////        {
////            if (!hasNext())
////            {
////                throw new NoSuchElementException();
////            }
////            Node<Page> prevNode = node;
////            node = node.next;
////            return prevNode.data;
////        }
////
////        @Override
////        public void remove()
////        {
////            throw new UnsupportedOperationException("Removal logic not implemented.");
////        }
////    }
//    /**
//     * Update muss nach jeder Änderung an der Seitenstruktur aufgerufen werden.
//     * todo: ...umsetzen!
//     */
//    private void update()
//    {
////        writeSitemapXML();
//    }
//
//    /*
//********************************************************************************
//     */
//    private void getHtmlNavigationBody(
//            Node<Page> passedNode,
//            StringBuilder navString,
//            int startLevel,
//            int stopLevel,
//            int passedLevel,
//            String passedPath)
//    {
//
//// todo:
//// Navigation zeigt an:		permitted pages
////Navigation zeigt nicht an:	Systemseiten (ausblendbar: hide in nav.)
////Stammdaten werden berücksichtigt.
//// achtung: wenn ich auf einem bestimmten level beginne, dann hat die navigation viele bäume.
//        // Level und Title immer mitgeben, jedoch nur schreiben, wenn Level ok:
//        // HTML-Liste für übergebene Node öffnen und neuen Link angeben:
//        // ...Nur wenn Level gewünscht ist wird geschrieben:
//        if (passedLevel >= startLevel && passedLevel <= stopLevel)
//        {
//            navString
//                    .append("\n<ul>\n") // ul wird eröffnet
//                    .append("<li><a href=\"")
//                    //                .append("/") // todo: in die vorläufer-methode verschieben. servletPath mitgeben!
//                    .append(passedPath); // Der übergegebene Pfad ist der aktuelle Pfad
//
//            if (requestController.request.getSession(false) != null
//                    && requestController.doUrlRewriting)
//            {
//                navString
//                        .append(";jsessionid=")
//                        .append(requestController.request
//                                .getSession(false).getId());
//            }
//
//            navString
//                    .append("\">")
//                    .append(passedNode.getItem().getHtmlTitle()) // Der Titel wird nicht übergeben/-nommen sondern aus der passedNode bezogen.
//                    .append("</a>");
//        }
//        // Der Pfad jedoch wird immer fortgeschrieben: (todo: in den nodes immer den Pfad speichern!!!!)
//
//        // Kind-Liste zur aktuellen Node erstellen:
//        Node<Page>[] children = passedNode.getChildren();
//
//        // Wenn der aktuelle Knoten Kinder hat wird diese Methode für jedes Kind
//        // erneut aufgerufen und der bisherige Pfad mitgegeben:
//        if (children.length > 0)
//        {
//            for (int i = 0; i < children.length; i++)
//            {
//
//                // todo: if-Abfrage hier weglassen, da oben bereits geprüft?
//                // oder muss ich bei der prüfung bereits hier das nächste level prüfen?
//                // fehler: ich prüfe hier jedes Kind auf dem selben level auf das level!!!
//                if (passedLevel >= startLevel && passedLevel <= stopLevel)
//                { // todo: ausprobieren, ob weggelassen werden kann!
////                            .append(passedTitle) // hier wird der pfad übernommen...
////                            .append("/")
////                            .append(children[i].getItem().getHtmlTitle()) // ... hier weitergeführt.
//////                            .append("/")
////                            .append("\">")
////                            .append(children[i].getItem().getHtmlTitle())
////                            .append("</a>");
//                }
//
//                // Ans nächste Level zu übergebende Daten:
//                // todo: passedLevel als eckpunkt für iterator nutzen.
//                // von dort aus dann navigationsdaten oder seitendaten abrufen.
//                int passLevel = passedLevel + 1;
//
//                String passPath = passedPath + "/" + children[i].getItem().getHtmlTitle(); // passPath ist sozusagen der "nextPath"
//
//                this.getHtmlNavigationBody(
//                        children[i], navString, startLevel, stopLevel, passLevel, passPath);
//
//                // todo: Welches list-item wird hier geschlossen? Jedes?
//                if (passedLevel >= startLevel && passedLevel <= stopLevel)
//                {
//                    navString.append("</li>\n");
//                }
//            }
//        }
//        // Ende des Levels:
//        if (passedLevel >= startLevel && passedLevel <= stopLevel)
//        {
//            navString.append("</ul>\n"); // (jede) ul wird geschlossen
//        }
//    }
//
//    // todo: später boolean doUrlRewriting statt session mitgeben.
//    public String getNav(
//            int startLevel, int stopLevel, RequestController requestController)
//    {
//        this.requestController = requestController;
//
//        // testweise können hier die Levels manipuliert werden:
//        startLevel = 0;
//        stopLevel = 10;
//
//        // todo: wenn eingaben level 0 bis 0 sind, dann exception
//        StringBuilder navString = new StringBuilder();
//        String passPath
//                = Settings.getHostName()
//                + Settings.getContextPath()
//                + "/"
//                + rootNode.getItem().getHtmlTitle();
//        int passLevel = 0; // ist immer null, da der Pfad in der nächsten Methode immer von der Root beginnend geschrieben werden muss.
//
//        navString.append("<nav>");
//
//        // todo: wenn root existiert!
//        this.getHtmlNavigationBody(rootNode, navString, startLevel, stopLevel, passLevel, passPath);
//
////        html.append("</nav>\n");
//        navString.append("</div></nav>\n");
//
//        return navString.toString();
//    }
//
////    parent zu newpagetree schaffen namens TreeOfNodes
////
////    .
////
////    rootExpander(item)
//    @Override
//    public Iterator iterator()
//    {
//        Iterator<Node<Page>> iterator = new Iterator<Node<Page>>()
//        {
//
//            @Override
//            public boolean hasNext()
//            {
//                //
//            }
//
//            @Override
//            public Node<Page> next()
//            {
//                // Interner Iterator:
//                int level = parentNode.getLevel() + 1;
//
//                if (pageDaoImpl.hasChildPages(parentNode.getItem().getId()))
//                {
//
//                    List<Page> childPages
//                            = pageDaoImpl.getChildPagesAsc(parentNode.getItem().getId());
//
//                    for (int i = 0; i < childPages.size(); i++)
//                    {
//
//                        Node<Page> childNode
//                                = new Node(
//                                        this.rootNode, parentNode, childPages.get(i));
//                        childNode.setLevel(level);
//                        childNode.getItem().setLevel(level);
//
//                        parentNode.addChild(childNode);
//
//                        stringNodes.put(childNode.getItem().getSitePath(), childNode);
//                        integerNodes.put(childNode.getItem().getId(), childNode);
//
//                        expandRootNode(childNode);
//                    }
//                }
//            }
//
//            @Override
//            public void remove()
//            {
//                // todo:
//            }
//        };
//        return iterator;
//    }
//
//    /**
//     * Iteriert die übergebene Methode.
//     * @param parentNode
//     */
//    private void iterateNode(Node<Page> parentNode)
//    {
//        int level = parentNode.getLevel() + 1;
//
//        if (pageDaoImpl.hasChildPages(parentNode.getItem().getId()))
//        {
//
//            List<Page> childPages
//                    = pageDaoImpl.getChildPagesAsc(parentNode.getItem().getId());
//
//            for (int i = 0; i < childPages.size(); i++)
//            {
//
//                Node<Page> childNode
//                        = new Node(
//                                this.rootNode, parentNode, childPages.get(i));
//                childNode.setLevel(level);
//                childNode.getItem().setLevel(level);
//
//                parentNode.addChild(childNode);
//
//                stringNodes.put(childNode.getItem().getSitePath(), childNode);
//                integerNodes.put(childNode.getItem().getId(), childNode);
//
//                expandRootNode(childNode);
//            }
//        }
//    }
//
//}
