
public class Array {

    /** A simplified version of the java.util.List interface. */
    public interface List<E> {
        /** Returns the number of elements in this list. */
        int size();

    /** Returns whether the list is empty. */
        boolean isEmpty();

        /** Returns (but does not remove) the element at index i. */
        E get(int i) throws IndexOutOfBoundsException;

        /** Replaces the element at index i with e, and returns the replaced element. */
        E set(int i, E e) throws IndexOutOfBoundsException;

        /** Inserts element e to be at index i, shifting all subsequent elements later. */
        void add(int i, E e) throws IndexOutOfBoundsException;

        /** Removes/returns the element at index i, shifting subsequent elements earlier. */
        E remove(int i) throws IndexOutOfBoundsException;
    }


    public static class ArrayList<E> implements List<E> {
        // instance variables
        public static final int CAPACITY=3;     // default array capacity
        private E[] data;                        // generic array used for storage
        private int size = 0;                    // current number of elements
    
        // constructors
        public ArrayList() { this(CAPACITY); }   // constructs list with default capacity
        public ArrayList(int capacity) {         // constructs list with given capacity
            data = (E[]) new Object[capacity];   // safe cast; compiler may give warning
        }
        
        // protected utility method
        /** Checks whether the given index is in the range [0, n-1]. */
        protected void checkIndex(int i, int n) throws IndexOutOfBoundsException {
            if (i < 0 || i >= n)
                throw new IndexOutOfBoundsException("Illegal index: " + i);
        }
    
        // public methods
        /** Returns the number of elements in the array list. */
        public int size() { return size; }
    
        /** Returns whether the array list is empty. */
        public boolean isEmpty() { return size == 0; }
    
        /** Returns (but does not remove) the element at index i. */
        public E get(int i) throws IndexOutOfBoundsException {
            checkIndex(i, size);
            return data[i];
        }
    
        /** Replaces the element at index i with e, and returns the replaced element. */
        public E set(int i, E e) throws IndexOutOfBoundsException {
            checkIndex(i, size);
            E temp = data[i];
            data[i] = e;
            return temp;
        }

    
        /** Inserts element e to be at index i, shifting all subsequent elements later. */
        public void add(int i, E e) throws IndexOutOfBoundsException,IllegalStateException {
            checkIndex(i, size + 1);
            if (size == data.length) {
                //---CHANGES MADE HERE---
                E[] temp = (E[]) new Object[2 * data.length]; // safe cast; compiler may give warning
                for (int k=0; k < size+1; k++) {
                    if (k == i) {
                        temp[k] = e;
                    }
                    else if (k > i) {
                        temp[k] = data[k-1];
                    }
                    else {
                        temp[k] = data[k];
                    }
                }
                data = temp;  
                size *= 2;
                //---CHANGES MADE HERE---
            }
            //runs for normal add operations
            else {
                for (int k=size-1; k >= i; k--) {      // start by shifting rightmost
                    data[k+1] = data[k];
                }
                data[i] = e;                      // ready to place the new element
                size++;
            }
        }
    
        /** Removes/returns the element at index i, shifting subsequent elements earlier. */
        public E remove(int i) throws IndexOutOfBoundsException {
            checkIndex(i, size);
            E temp = data[i];
            for (int k=i; k < size-1; k++)        // shift elements to fill hole
                data[k] = data[k+1];
            data[size-1] = null;                  // help garbage collection
            size--;
            return temp;
        }

        public void printArray() {
            for (int i = 0; i < size; i ++) {
            System.out.print("["+get(i)+"]");
            }
            System.out.println("\n");
        }
    }



    public static void main(String[] args) {
        //initialization
        ArrayList c;
        c = new ArrayList<>();

        //using array size limit of 3 to make it easier to see how method works
        for(int i = 0; i<3; i++) {
            c.add(i, i+1);
        }
        //list is already at 3, so adding another element will cause it to resize 
        c.add(1, 4);
        //when this is run, it shoudl display [1][4][2][3] null null to indicate that the list:
        //-resized to 6 
        //-added the next element
        c.printArray();
    }
        




            
}

