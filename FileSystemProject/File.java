
public class File extends Entry {
	private int size;
	public File(String n, int s) {
		super(n);
		unsetDirectory(); //sets a flag in parent class that this Entry IS NOT a directory
		size = s;
	}
	
	@Override
	public int size() {
		return size;
	}
}
