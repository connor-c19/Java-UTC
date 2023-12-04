import java.util.Iterator;
import java.util.Scanner;

public class FileSystemTester {

	public static void main(String[] args) {
		FileSystem fs = new FileSystem();
		Scanner in = new Scanner(System.in);
		
		
		boolean done = false;
		while(!done) {
			printMenu();
			System.out.print("Your choice: ");
			String input = in.nextLine();
			String name = "";
			switch(input.charAt(0)) {
				case '1':
					fs.pwd();
					break;
				case '2':
					fs.ls();
					break;
				case '3':
					fs.cd();
					break;
				case '4':
					name = input.substring(2, input.length());
					fs.cd(name);
					break;
				case '5': //mkdir
					name = input.substring(2, input.length()); 
					System.out.println("Making directory " + name);
					fs.mkdir(name);
					break;
				case '6':
					name = input.substring(2, input.length());
					int space = name.lastIndexOf(" ");
					//System.out.println("lastIndexOf: " + space);
					String size = name.substring(space+1);
					int sz = Integer.parseInt(size);
					name = name.substring(0, space);
					System.out.println("name: " + name + "; size: " + size);
					fs.mkfile(name,sz);
					break;
				case '7':
					sz = fs.du(fs.getRoot());
					System.out.println("Root Filesystem Size: " + sz);
					break;
				case '8':
					sz = fs.du(fs.getCur());
					System.out.println("Filesystem Size from Current: " + sz);
					break;
				case 'q': 
					done = true;
					break;
				default:
					System.out.println("invalid entry, try again");
			}
		}
		
		
		
	}
	public static void printMenu() {
		System.out.println("****************************************************");
		System.out.println("Menu Options:");
		System.out.println("1: pwd - print Current Directory");
		System.out.println("2: ls - print current directory contents");
		System.out.println("3: cd .. - move up one directory");
		System.out.println("4: cd name - move to directory called 'name' ");
		System.out.println("5: mkdir name - make a new directory here with given name");
		System.out.println("6: mkfile name size - make a file here with given name and size");
		System.out.println("7: du - recursively find the size of the file system from the root (whole file system)");
		System.out.println("8: du - recursively find the size of the file system from current directory");
		System.out.println("q: quit - file system will be lost");
		System.out.println("****************************************************");
	}

}
