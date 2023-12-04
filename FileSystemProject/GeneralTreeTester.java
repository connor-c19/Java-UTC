import java.util.Iterator;

public class GeneralTreeTester {

	public static void main(String[] args) {
		GeneralTree<Integer> t = new GeneralTree<>();
		//I will build the following tree
		/*
					35
			20				63
		9		27		46		75
			  23  31
		 */
		t.addRoot(35); //simply provide the element to store at root.
		//p will save the position as we build down the tree.
		Position<Integer> p = t.addChild(t.root(), 20);
		p = t.addChild(p, 9);
		p = t.parent(p);
		p = t.addChild(p,  27);
		t.addChild(p, 23);
		p = t.parent(p);
		t.addChild(p,  31);
		t.addChild(t.root(), 63);
		p = t.child(t.root(), 1);
		t.addChild(p, 46);
		t.addChild(p, 75);
	
		System.out.println("Size: " + t.size());

		//*** EXAMPLE 1
		boolean first = true;
		System.out.println("Using the FOREACH loop:");
		//because Trees are Iterable, we can do this
		for(Integer x : t) {
			if(first) {
				System.out.print("tree: {" + x);
				first = false;
			} else {
				System.out.print(", " + x );
			}
		}
		System.out.println("}");
		//*** EXAMPLE 2
		first = true;
		System.out.println("Using the equivalent t.iterator():");
		Iterator<Integer> intIter = t.iterator();
		while (intIter.hasNext()) {
			int x = intIter.next();
			if(first) {
				System.out.print("tree: {" + x);
				first = false;
			} else {
				System.out.print(", " + x );
			}
		}
		System.out.println("}");
		
		//*** EXAMPLE 3
		first = true;
		//t.positions() returns an Iterable Object -> means it has iterator() method
		//we call that iterator() method to get an Iterator object of the positions in 
		//our Tree t
		Iterator<Position<Integer>> iter = t.positions().iterator();
		System.out.println("Using the Iterator from t.positions().iterator()");
		while(iter.hasNext()) {
			//iter.next() gives us a position, we then 
			//must call the getElement() method to get the 
			//element stored at each position
			int x = iter.next().getElement();
			if(first) {
				System.out.print("tree: {" + x);
				first = false;
			} else {
				System.out.print(", " + x );
			}
		}
		System.out.println("}");
	

	}

}
