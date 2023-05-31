package com.bueffeltier.logic.foundation.pagetree;

import java.util.ArrayList;
import java.util.List;

/**
 * todo: Konstruktoren überarbeiten. Klasse dokumentieren.
 * 
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 * @param <T>
 */
public final class Node<T>
{

	private T item;
	private int level;
	private int horizontalIndex;
	private int verticalIndex;

	private Node<T> root; // Eine T - Typ Variable des Wurzel-Elements der Liste
							// (Zugriff auf die Wurzel)
	private Node<T> parent; // Eine T - Typ Variable des Eltern-Elements der
							// Liste

	private final List<Node<T>> children = new ArrayList<>(); // a T type list
																// to store the
																// getChildren
																// of the list

	/**
	 * Standard-Konstruktor.Setzt sich selbst als RootNode und Parent als Null.
	 * 
	 * @param t
	 */
	public Node(T item)
	{
		setParent(null);
		setRoot(null);
		setItem(item);
	}

	/**
	 * Konstruktor, - setzt die Parent-Node
	 * 
	 * @param node
	 */
	public Node(Node<T> parent)
	{
		this.setParent(parent);
		// this.addChild(parent);
	}

	// constructor overloading to set the parent of the list
	/**
	 *
	 * @param parent
	 * @param child
	 */
	public Node(Node<T> parent, Node<T> child)
	{
		this(parent);
		this.children.add(child);
	}

	/**
	 * Add a child node.
	 *
	 * @param child
	 **/
	public void addChild(Node<T> child)
	{
		child.root = this.getRoot();
		child.setParent((Node<T>) this);
		this.children.add(child); // add this item to the list
	}

	/**
	 *
	 * @param child
	 */
	public void removeChild(Node<T> child)
	{
		this.children.remove(child); // remove this item from the list
	}

	/**
	 *
	 * @return
	 */
	public Node<T> getRoot()
	{
		return root;
	}

	/**
	 *
	 * @return
	 */
	public boolean isRoot()
	{
		// check to see if the root is null if yes then return true else return
		// false
		return this.root != null;
	}

	/**
	 *
	 * @param root
	 */
	public void setRoot(Node<T> root)
	{
		this.root = root;
	}

	/**
	 *
	 * @return
	 */
	public Node<T> getParent()
	{
		return parent;
	}

	/**
	 *
	 * @param parent
	 */
	public void setParent(Node<T> parent)
	{
		this.parent = parent;
	}

	/**
	 *
	 * @return
	 */
	public T getItem()
	{
		return item;
	}

	/**
	 *
	 * @param child
	 */
	public void setItem(T child)
	{
		this.item = child;
	}

	/**
	 *
	 * @return
	 */
	public boolean hasChildren()
	{
		return this.children.size() > 0;
	}

	/**
	 *
	 * @return
	 */
	public Node<T>[] getChildren()
	{
		return (Node<T>[]) children.toArray(new Node[children.size()]);
	}

	/**
	 *
	 * @return
	 */
	public List<Node<T>> getChildrenList()
	{
		return this.children;
	}

	/**
	 *
	 * @return
	 */
	public int getLevel()
	{
		return level;
	}

	/**
	 *
	 * @param level
	 */
	public void setLevel(int level)
	{
		this.level = level;
	}

	/**
	 *
	 * @return
	 */
	public int getHorizontalIndex()
	{
		return horizontalIndex;
	}

	/**
	 *
	 * @param horizontalIndex
	 */
	public void setHorizontalIndex(int horizontalIndex)
	{
		this.horizontalIndex = horizontalIndex;
	}

	/**
	 *
	 * @return
	 */
	public int getVerticalIndex()
	{
		return verticalIndex;
	}

	/**
	 *
	 * @param verticalIndex
	 */
	public void setVerticalIndex(int verticalIndex)
	{
		this.verticalIndex = verticalIndex;
	}
//    @SuppressWarnings({ "unchecked"})
//    public Node<T>[] getSiblings()
//    {
//        if(this.isRoot()!=false && parent==null)
//        {
//            System.out.println("this is root or there are no siblings");
//            return null;
//        }
//        else{
//            List<Node1<T>> siblings = new ArrayList<Node1<T>>((Collection<? extends Node<T>>) Arrays.asList(new Node[this.parent.getChildren.size()]));
//            Collections.copy(siblings, this.parent.getChildren);
//            siblings.remove(this);
//            return siblings.toArray(new Node[siblings.size()]);
//        }
//    }
}
