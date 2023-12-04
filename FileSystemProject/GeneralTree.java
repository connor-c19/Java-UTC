import java.util.ArrayList;
import java.util.List;


/**
 * This class is a general tree. Each node consists of an element, a link to the parent, 
 * and a LinkedPositionalList of the children nodes. 
 * @author schwabdr
 *
 * @param <E> The types of elements stored by our GeneralTree
 */


public class GeneralTree<E> extends AbstractTree<E> {
	//---------------- nested Node class ----------------
	/** Nested static class for a binary tree node. */
	protected static class Node<E> implements Position<E> {
		private E element;          // an element stored at this node
		private Node<E> parent;     // a reference to the parent node (if any)
		private LinkedPositionalList<Node<E>> children;       // a reference to the List of Children of a node

		/**
		 * Constructs a node with the given element and children.
		 *
		 * @param e  the element to be stored
		 * @param above       reference to a parent node
		 * @param children	LinkedPositinonalList of children, or null
		 */
		public Node(E e, Node<E> above, LinkedPositionalList<Node<E>> ch) {//, Node<E> leftChild, Node<E> rightChild) {
			element = e;
			parent = above;
			if(ch == null) {
				children = new LinkedPositionalList<Node<E>>();
			} else {
				children = ch;
			}
			
		}

		// accessor methods
		public E getElement() { return element; }
		public Node<E> getParent() { return parent; }
		public LinkedPositionalList<Node<E>> getChildren() { return children; } 
		
		// update methods
		public void setElement(E e) { element = e; }
		public void setParent(Node<E> parentNode) { parent = parentNode; }
		public void addChild(Node<E> child) {children.addLast(child);  }
		
	} //----------- end of nested Node class -----------
	/** Factory function to create a new node storing element e. */
	protected Node<E> createNode(E e, Node<E> parent,
			LinkedPositionalList<Node<E>> childs) {
		return new Node<E>(e, parent, childs);
	}



	// GeneralTree instance variables
	/** The root of the binary tree */
	protected Node<E> root = null;     // root of the tree

	/** The number of nodes in the binary tree */
	private int size = 0;              // number of nodes in the tree

	// constructor
	/** Constructs an empty binary tree. */
	public GeneralTree() { }      // constructs an empty binary tree

	// nonpublic utility
	/**
	 * Verifies that a Position belongs to the appropriate class, and is
	 * not one that has been previously removed. Note that our current
	 * implementation does not actually verify that the position belongs
	 * to this particular list instance.
	 *
	 * @param p   a Position (that should belong to this tree)
	 * @return    the underlying Node instance for the position
	 * @throws IllegalArgumentException if an invalid position is detected
	 */
	protected Node<E> validate(Position<E> p) throws IllegalArgumentException {
		if (!(p instanceof Node))
			throw new IllegalArgumentException("Not valid position type");
		Node<E> node = (Node<E>) p;       // safe cast
		if (node.getParent() == node)     // our convention for defunct node
			throw new IllegalArgumentException("p is no longer in the tree");
		return node;
	}
	//complete
	@Override
	public Position<E> root() {
		return root;
	}
	//complete
	@Override
	public int size() { 
		return size; 
	}

	@Override
	public Iterable<Position<E>> children(Position<E> p) {
		List<Position<E>> snapshot = new ArrayList<>();
		Node<E> node = validate(p);
		for(Position<E> x : node.getChildren()) {
			snapshot.add(x);
		}
		return snapshot;
	}


	/**
	 * Node to get the position of the parent of Position p
	 * @return the Position of the Parent of position p 
	 */
	//complete
	@Override
	public Position<E> parent(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
        return node.getParent();
	}
	/**
	 * Returns the position of the ith child of position p
	 *
	 * @param p   position to get children of
	 * @param i 	number of child to get
	 * @return the Position of the ith child
	 * @throws IllegalArgumentException if there isn't an ith child or if p is invalid
	 */
	//COMPLETE
	public Position<E> child(Position<E> p, int i) throws IllegalArgumentException{
		Node<E> node = validate(p);
		if (i < 0 ||  i > numChildren(node)) throw new IllegalArgumentException();
		//create snapshot of current parent
		java.util.Iterator<Position<E>> children = children(node).iterator();
		//iterate through snapshot
		for (int j = 0; j < i-1; j++) children.next();
		//give result
		return children.next();

	}


	// update methods supported by this class
	/**
	 * Places element e at the root of an empty tree and returns its new Position.
	 *
	 * @param e   the new element
	 * @return the Position of the new element
	 * @throws IllegalStateException if the tree is not empty
	 */
	public Position<E> addRoot(E e) throws IllegalStateException {
		if (!isEmpty()) throw new IllegalStateException("Tree is not empty");
		root = createNode(e, null, null);
		size = 1;
		return root;
	}

	/**
	 * Adds a child of Position p storing element e and returns the new Position.
	 *
	 * @param p   the Position to which a child with the new element is inserted
	 * @param e   the new element
	 * @return the Position of the new element
	 * @throws IllegalArgumentException if p is not a valid Position for this tree
	 * 
	 */
	//COMPLETE
	public Position<E> addChild(Position<E> p, E e) {
		Node<E> parent = validate(p);
		Node<E> child = createNode(e, parent, null);
		parent.addChild(child);
		size++;
		return child;
	}



	/**
	 * Replaces the element at Position p with element e and returns the replaced element.
	 *
	 * @param p   the relevant Position
	 * @param e   the new element
	 * @return the replaced element
	 * @throws IllegalArgumentException if p is not a valid Position for this tree.
	 */
	public E set(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> node = validate(p);
		E temp = node.getElement();
		node.setElement(e);
		return temp;
	}

	/**
	 * Attaches trees t1 and t2, respectively, as children of 
	 * leaf Position p. As a side effect, t1 and t2 are set to empty trees.
	 *
	 * @param p   a leaf of the tree
	 * @param t1  an independent tree whose structure becomes a child of p
	 * @param t2  an independent tree whose structure becomes a child of p
	 * @throws IllegalArgumentException if p is not a valid Position for this tree
	 * @throws IllegalArgumentException if p is not a leaf
	 */
	public void attach(Position<E> p, GeneralTree<E> t1,
			GeneralTree<E> t2) throws IllegalArgumentException {
		Node<E> node = validate(p);
		if (isInternal(p)) throw new IllegalArgumentException("p must be a leaf");
		size += t1.size() + t2.size();
		if (!t1.isEmpty()) {                  // attach t1 as left subtree of node
			t1.root.setParent(node);
			node.addChild(t1.root);
			t1.root = null;
			t1.size = 0;
		}
		if (!t2.isEmpty()) {                  // attach t2 as right subtree of node
			t2.root.setParent(node);
			node.addChild(t2.root);
			t2.root = null;
			t2.size = 0;
		}
	}

	/**
	 * Removes the node at Position p and replaces it with its child, if any.
	 * NOT IMPELEMENTED - not needed for this project.
	 * @param p   the relevant Position
	 * @return element that was removed
	 * @throws IllegalArgumentException if p is not a valid Position for this tree.
	 * @throws IllegalArgumentException if p has > 1 children.
	 */
	//	  public E remove(Position<E> p) throws IllegalArgumentException {
	//	    Node<E> node = validate(p);
	//	    if (numChildren(p) != 1)
	//	      throw new IllegalArgumentException("p must have exactly 1 child");
	//	    //Node<E> child = (node.getLeft() != null ? node.getLeft() : node.getRight() );
	//	    Node<E> child = (Node<E>)node.getChildren().first();
	//	    if (child != null)
	//	      child.setParent(node.getParent());  // child's grandparent becomes its parent
	//	    if (node == root)
	//	      root = child;                       // child becomes root
	//	    else {
	//	      Node<E> parent = node.getParent();
	//	      if (node == parent.getLeft())
	//	        parent.setLeft(child);
	//	      else
	//	        parent.setRight(child);
	//	    }
	//	    size--;
	//	    E temp = node.getElement();
	//	    node.setElement(null);                // help garbage collection
	//	    node.setLeft(null);
	//	    node.setRight(null);
	//	    node.setParent(node);                 // our convention for defunct node
	//	    return temp;
	//	  }


}
