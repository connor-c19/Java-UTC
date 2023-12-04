
public abstract class Entry {
	private String name; //name of Directory or File
	private boolean isDirectory = false; //default not a directory
	public Entry(String n) {
		this.name = n;
	}
	public void setName(String n) {
		name = n;
	}
	public String getName() {
		return name;
	}
	
	public boolean isDirectory() { return isDirectory; }
	public void setDirectory() {isDirectory = true; }
	public void unsetDirectory() {isDirectory = false; }
	
	/*
	 * returns the size of this Entry
	 * 
	 */
	public int size() {
		return 0;
	}
}
