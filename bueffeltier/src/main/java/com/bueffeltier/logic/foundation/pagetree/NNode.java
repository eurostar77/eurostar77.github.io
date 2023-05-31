package com.bueffeltier.logic.foundation.pagetree;
//package com.mycompany.bubbles.servlet.pagetree;
//
//import com.mycompany.bubbles.servlet.Settings;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// *
// * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
// * @param <T>
// */
//public abstract class NNode<T> {
//
//    private final   T   item    = null;
//    private final   int level   = 0;
//    static boolean isExpanded;
//
//    private         NNode<T>         rootNode;              // Eine T - Typ Variable des Wurzel-Elements der Liste (Zugriff auf die Wurzel)
//    private         NNode<T>         parentNode;              // Eine T - Typ Variable des Eltern-Elements der Liste
//    private final   List<NNode<T>>   childNodes;   // a T type list to store the getChildren of the list
//
//
//    // default constructor
//    public NNode(T item){
//        this.parentNode = null;
//        this.rootNode = null;
//        this.item = item;
//    }
//
//    // Constructor overloading to set the parent node
//    public NNode(NNode<T> parent){
//        this.parentNode = parent;
//    }
//
////    // Constructor overloading to set parent and child node
////    public NNode(NNode<T> parent, NNode<T> child){
////        this(parent);
////        this.childNodes.add(child);
////    }
//
////    private void buildRoot(T item){
////        rootNode = new NNode(item);
////        rootNode.setLevel(0);
////    }
//
//
//    // todo: nur einmalig bei rootNode möglich? singleton pattern?
//    /**
//     * Erstellt einen Seitenbaum aus der Node.
//     * @param node
//     */
//    public void expandNode() {
////        level++;
//        childNodes = this.getChildNodes();
////        if (tree.get(node) == null) {
////            System.out.println(node);
////            return;
////        }
////        childNodes müssen vorher gesetzt worden sein.
//        for(int i=0;i<childPages.size();i++){
//                Node<Page> node = new Node(parentNode);
//                node.setItem(childPages.get(i));
//                node.setLevel(level);
//                node.getItem().setLevel(level);
//                parentNode.addChild(node);
//                expandRootNode(node);
//            }
//        for(NNode<T> n : tree.get(node)) {
//            System.out.println(node);
//            expandNode(n);
//        }
//    }
//
//    public abstract boolean hasChilds(NNode){
//
//    }
//
//    abstract List<NNode<T>> getChildNodes(){
//
//    }
//
//    private void getHtmlNavigation(
//            NNode<Page> parent,
//            StringBuilder navString,
//            int startLevel,
//            int stopLevel,
//            int passedLevel,
//            String passedPath){
//
//        // Alle children des übergebenen parents bearbeiten:
//        NNode<Page>[] children = parent.getChildren();
//
//        if(children.length>0){
//
//            // Am Beginn einer NNode der gewählten Levels html-Liste öffnen:
//            if(passedLevel>=startLevel && passedLevel<=stopLevel){
//                navString.append("\n<ul>\n");
//            }
//
//            // Für alle children:
//            for(int i=0; i<children.length; i++){
//
//                // todo: if-Abfrage hier weglassen, da oben bereits geprüft?
//                if(passedLevel>=startLevel&&passedLevel<=stopLevel){
//                    navString.append("<li><a href=\"")
//                            .append(Settings.getContextPath());
//                    navString.append("/");
//
//                    // vorherige Levels einfügen:
//                    navString.append(passedPath).append("/");
//
//                    navString.append(children[i].getItem().getHtmlTitle());
//                    navString.append("\">");
//                    navString.append(children[i].getItem().getHtmlTitle())
//                            .append("</a>");
//                }
//
//                // Ans nächste Level zu übergebende Daten:
//                int passLevel = passedLevel+1;
//                String passPath = passedPath + children[i].getItem().getHtmlTitle();
//                this.getHtmlNavigation(
//                        children[i], navString, startLevel, stopLevel, passLevel, passPath);
//
//                if(passedLevel>=startLevel&&passedLevel<=stopLevel){
//                    navString.append("</li>\n");
//                }
//            }
//
//            // Ende des Levels:
//            if(passedLevel>=startLevel&&passedLevel<=stopLevel){
//                navString.append("</ul>\n");
//            }
//        }
//    }
//
//    public String getNav(int startLevel, int stopLevel){
//
//        // todo: wenn eingaben level 0 bis 0 sind, dann exception
//        StringBuilder html = new StringBuilder();
//        String pathString = rootNode.getItem().getHtmlTitle();
//
//        html.append("<nav>");
//
//        // todo: wenn root existiert!
//        this.getHtmlNavigation(rootNode, html, startLevel, stopLevel, 1, pathString);
//
////        html.append("</nav>\n");
//        html.append("</div></nav>\n");
//
//        return html.toString();
//    }
//
//    // todo: diese methode und get nav vereinen oder verketten, mit und ohne html, stufe 1 stufe 2 ,...
//    public void getPageTree(NNode<Page> root, ArrayList<Page> tree){
////        ebene einfügen
////                nicht mehr page zurückgeben, sondern list objekt?
////                artikel mit laden!
//        NNode<Page>[] children = root.getChildren();
//        if(children.length>0){
//            for(int i=0; i<children.length; i++){
//                // todo: direkteren weg, auf die pages zuzugreifen? aus dao heraus?
//                    tree.add(children[i].getItem());
//                    getPageTree(children[i], tree);
//            }
//        }
//    }
//
////    private void nodeIterator (){
////
////    }
//
//    public NNode<Page> getRootNode() {
//        return rootNode;
//    }
//
//    public void setRootNode(NNode<Page> rootNode) {
//        this.rootNode = rootNode;
//    }
//}
//
///**
//
//    Create a class called Tree that acts as a container for nodes.
//    The tree class should support the following methods.
//
//    public void add(Node parent, Node child){}
//    -- Adds a new child node to the parent node
//
//    public void removeChild(Nodeparent, Node child){}
//    -- Removes a child node from a parent.
//
//    public Node getRootNode(){}
//    -- Returns the root of the tree
//
//    public void setRoot(Node root){}
//    -- Sets the root node of the tree
//
//    public boolean contains(T data){}
//    -- Searches the tree for a given type
//
//    public void dfs(Node child){}
//    -- Performs a depth-first-search of the tree and outputs each node (indented)
//
//    public void bfs(Node child){}
//    -- Performs a breadth-first-search of the tree and outputs each node (indented)
//
//    The tree class should be parameterized to handle a generic type T,
//    allowing trees of strings, files etc... to be created, e.g.
//    Tree<String> tree = new Tree<String>()
//    The tree class should implement the tree structure using an adjacency list
//    and be defined in the following way:
//    Map<Node<T>, List<Node<T>>> tree = new HashMap<Node<T>, List<Node<T>>();
//    https://www.programiz.com/dsa/graph-adjacency-list
