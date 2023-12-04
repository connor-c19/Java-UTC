/*This is our main class - (not a main method!).
 * The point of this class it to keep a tree which represents our file system
 * We need to have a current directory (a position in the tree)
 */

public class FileSystem {
	private GeneralTree<Entry> sys;
	private Position<Entry> root;
	private Position<Entry> cur;
	/**
	 * Constructor - creates a new FileSystem with a root node.
	 */
	
	public FileSystem() {
		sys = new GeneralTree<>();
		sys.addRoot(new Directory("root"));
		cur = sys.root();
		root = sys.root();
	}
	
	//****** COMPLETE ALL METHODS BELOW HERE ******//
	/**
	 * Accessor
	 * @return the root of the file system as a Position
	 */
	public Position<Entry> getRoot() {
		return root;
	}
	/**
	 * Accessor
	 * @return current Position (which is the current Directory).
	 */
	public Position<Entry> getCur() {
		return cur;
	}
	/**
	 * Adds a directory as the child of the current directory
	 * @param n name of the new directory
	 * @return returns the position of the new directory
	 */
	public Position<Entry> mkdir(String n) {
		Directory newD = new Directory(n);
		return sys.addChild(cur, newD);
	}
	/**
	 * Adds a new file to the file system with the given name and size.
	 * @param n name of the new file
	 * @param s size of the new file
	 * @return the Position of the newly added file.
	 */
	public Position<Entry> mkfile(String n, int s) {
		File newF = new File(n, s);
		return sys.addChild(cur, newF);
	}
	/**
	 * Print the name of the current directory, followed by the contents of the current directory.
	 */
	public void ls() {
		
		System.out.println("Current Directory: "+cur.getElement().getName()+"\nContents:\n");
		Iterable<Position<Entry>> content = sys.children(cur);
		String files = "";
		for (Position<Entry> x : content) {
			if (x.getElement().isDirectory() == true) {
				System.out.print("Directory: "+x.getElement().getName()+"\n");
			}
			else {
				files += ("File: "+x.getElement().getName()+"\n");
			}
		}
		System.out.print(files);
		
		
	}
	/**
	 * Print the name of the current directory. See sample output for how to format this.
	 */
	public void pwd() {
		System.out.println("Current Directory: "+cur.getElement().getName());
	}
	
	/**
	 * Method to change directory to one of the children of current.
	 * @param n name of the directory to change the current to.  If n does not match the name of any of the
	 * children, return cur unchanged.  (no error).
	 * @return the new current directory (position), or the old one if the parameter could not be found.
	 */
	public Position<Entry> cd(String n) {
		Iterable<Position<Entry>> content = sys.children(cur);
		for (Position<Entry> x : content) {
			if (x.getElement().isDirectory() == true) {
				if ((x.getElement().getName()).equals(n)) cur = x;
			}
		}
		return cur;
	}
	/**
	 * A method to move up one folder.  If cur is already the root, just return the root.  (no error).
	 * @return the new current position in the tree.
	 */
	public Position<Entry> cd() { //overloaded method
		if (cur == root) return root;
		else return sys.parent(cur);
	}
	
	/**
	 * A method to calculate the size of all the files and folders in the subtree rooted at start.
	 * This is a recursive method.
	 * @param start the position to start the calculation
	 * @return the size calculated
	 */
	
	public int du(Position<Entry> start) {
		int size = 4;
		Iterable<Position<Entry>> content = sys.children(start);
		for (Position<Entry> x : content) {
			if (x.getElement().isDirectory() == true) {
				size += du(x);
			}
			else {
				size += x.getElement().size();
			}
		}
		return size;
	}
	
}
