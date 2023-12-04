
public class Prog1Tester {

	public static void main(String[] args) {
		//
		SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
		//***** BEGIN penultimate() Tests *****//
		
		System.out.println();
		System.out.println("***** BEGIN penultimate() Tests *****");
		System.out.println();
		
		try {
			System.out.println(list.penultimate());
		} catch (IllegalStateException e) {
			System.out.println("Error triggered calling penultimate() on empty list:");
			System.out.println(e.getMessage());
		}
		list.addLast(1);  //list: {1}
		try {
			System.out.println(list.penultimate());
		} catch (IllegalStateException e) {
			System.out.println("Error triggered calling penultimate() on list with 1 element:");
			System.out.println(e.getMessage());
		}
		list.addLast(2);
		System.out.println(list);
		System.out.println("Penultimate: " + list.penultimate());

		for(int i = 3; i<=10; i++) {
			list.addLast(i);
		}
		// list: {1,2,3,4,5,6,7,8,9,10}
		System.out.println(list);
		System.out.println("Penultimate: " + list.penultimate());
		//***** END penultimate Tests *****//
		
		//***** BEGIN reverse() Tests *****//
		System.out.println();
		System.out.println("***** BEGIN reverse() TEST *****");
		System.out.println();
		
		list.reverse();
		// list: {10,9,8,7,6,5,4,3,2,1}
		System.out.println("Reverse the list from previous test:");
		System.out.println(list);
		
		
		//test reverse method on empty list, 1 element, 2 elements, 3 elements.
		list = new SinglyLinkedList<>(); //empty list: {}
		list.reverse();
		System.out.println("Reverse empty list");
		System.out.println(list);
		for(int i = 1; i<4; i++) {
			list.addLast(i);
			list.reverse();
			System.out.println("Reverse list size " + i);
			System.out.println(list);
			list.reverse();
		}
		
		//****** Begin test of addSecond *******//
		System.out.println();
		System.out.println("***** BEGIN addSecond(E e) TEST *****");
		System.out.println();
		
		list = new SinglyLinkedList<>();
		
		try {
			list.addSecond(1);
		} catch (IllegalStateException e) {
			System.out.println("Error triggered calling addSecond(1) on list with 0 elements:");
			System.out.println(e.getMessage());
		}
		
		System.out.println("Adding Second from 5 down to 2 on list (1). Expect: (1,2,3,4,5)");
		list.addFirst(1); //{1}
		for(int i = 5; i > 1; i--) {
			list.addSecond(i);
		}
		//{1,2,3,4,5}
		System.out.println("\nList " + list + "\nSize: " + list.size() );
		
		
		//****** Begin test of add(E e, int i) *******//
		System.out.println();
		System.out.println("***** BEGIN add(E e, int i) TEST *****");
		System.out.println();
		
		list = new SinglyLinkedList<>(); //empty list
		
		try {
			list.add(1,-1);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Error triggered calling add(1,-1) on empty list ");
			System.out.println(e.getMessage());
		}
		
		try {
			list.add(1,1);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Error triggered calling add(1,1) on empty list ");
			System.out.println(e.getMessage());
		}
		
		list.add(5, 0); // {1}
		
		for(int i = 0; i<5; i++) {
			list.add(i, i);
		}
			
		list.add(6,  6); //{0, 1,2,3,4,5,6}
		System.out.println(list);
		
		list = new SinglyLinkedList<>();
		list.add(0, 0);
		list.add(1, 1);
		list.add(2, 2);
		
		System.out.println(list); //{0,1,2}
		
		list = new SinglyLinkedList<>();
		list.add(2, 0);
		list.add(1, 0);
		list.add(0, 0);
		
		System.out.println(list); //{0,1,2}
		
		
		//****** Begin test of remove(int i) *******//
		System.out.println();
		System.out.println("***** BEGIN remove(int i) TEST *****");
		System.out.println();
		
		list = new SinglyLinkedList<>();
		for(int i = 0; i<6; i++) {
			list.add(i+1, i);
		}
		//{1,2,3,4,5,6}	
		
		int n = list.size();
		for(int i = 0; i < n; i++) {
			System.out.println("["+ i + "]:" + list.remove(0) + "\tsize: " + list.size()); //first element each iteration"			
		}
		System.out.println("Empty list: " + list + "\nSize: " + list.size());
		
		for(int i = 0; i < n; i++) {
			list.addLast(i);
		}
		System.out.println("List before removals: " + list + "\nsize: " + list.size());
		System.out.println("Here we show the \"index\" of the element we remove and size of list after removal");
		for(int i = n-1; i >= 0; i--) {
			System.out.println("["+ i + "]:" + list.remove(i) + "\tsize: " + list.size());  //last element each iteration
			System.out.println(list);
		}
		System.out.println("Empty list: " + list + "\nSize: " + list.size());
		

		//****** Begin test of getMiddle() *******//
		System.out.println();
		System.out.println("***** BEGIN getMiddle() TEST *****");
		System.out.println();
		
		list = new SinglyLinkedList<>(); //empty list
		
		try {
			list.getMiddle();
		} catch (IllegalStateException e) {
			System.out.println("Error triggered calling getMiddle() on empty list");
			System.out.println(e.getMessage());
			System.out.println();
		}
		System.out.println("Test sizes up to 10");
		for(int i = 1; i<=10; i++) {
			list.addLast(i);
			System.out.println("List: " + list + "\ngetMiddle(): " + list.getMiddle()+ "\n");
		}
		
		//****** Begin test of FowardIterator Class *******//
		System.out.println();
		System.out.println("***** BEGIN ForwardIterator TEST *****");
		System.out.println();
		
		list = new SinglyLinkedList<>(); //empty list
		System.out.print("Here is test of size 0. (Expect no output).\nOutput:");
		for(int value : list) {
			System.out.println(value);
		}
		System.out.println("\n");
		
		//Test sizes from 1 up to 5
		for(int i = 1; i <=5; i++) {
			System.out.println("Size: " + i);
			list.addLast(i);
			System.out.print("(");
			boolean first = true;
			for(int val : list) {
				if(first) first = false;
				else System.out.print(", ");
				System.out.print(val);
			}
			System.out.println(")\n");
		}
	}
}
