
public class Directory extends Entry {
	public Directory(String n) {
		super(n);
		setDirectory(); //sets a flag in parent class that this Entry IS a directory
	}
	/**
	 * @return size of all directories is 4.
	 */
	@Override
	public int size() {
		return 4;
	}
	
}
