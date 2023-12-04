public class Stacks{

    /**
 * A collection of objects that are addeded and removed according to the last-in
 * first-out principle. Although similar in purpose, this interface differs from
 * java.util.Stack.
 *
 * @author Michael T. Goodrich
 * @author Roberto Tamassia
 * @author Michael H. Goldwasser
 * 
 * 
 */

    public interface Stack<E> {
 
        /**
         * Returns the number of elements in the stack.
         * @return number of elements in the stack
         */
        int size();
    
        /**
         * Tests whether the stack is empty.
         * @return true if the stack is empty, false otherwise
         */
        boolean isEmpty();
    
        /**
         * Adds an element at the top of the stack.
         * @param e  the element to be added
         */
        void push(E e);
    
        /**
         * Returns, but does not remove, the element at the top of the stack.
         * @return top element in the stack (or null if empty)
         */
        E top();
    
        /**
         * Removes and returns the top element from the stack.
         * @return element removed (or null if empty)
         */
        E pop();
    }


    public static class ArrayStack<E> implements Stack<E> {
        // instance variables
        public static final int CAPACITY=1000;   // default array capacity
        private E[] data;                        // generic array used for storage
        private int t = -1;                      // index of the top element in stack
    
        // constructors
        public ArrayStack() { this(CAPACITY); }  // constructs stack with default capacity
        public ArrayStack(int capacity) {        // constructs stack with given capacity
            data = (E[]) new Object[capacity];   // safe cast; compiler may give warning
        }
    
        // methods
        /** Returns the number of elements in the stack. */
        public int size() { return (t + 1); }
    
        /** Tests whether the stack is empty. */
        public boolean isEmpty() { return (t == -1); }
    
        /** Adds an element at the top of the stack. */
        public void push(E e) throws IllegalStateException {
            if (size() == data.length) throw new IllegalStateException("Stack is full");
            data[++t] = e;                       // increment t before storing new item
        }
    
        /** Returns, but does not remove, the element at the top of the stack. */
        public E top() {
            if (isEmpty()) return null;
            return data[t];
        }
    
        /** Removes and returns the top element from the stack. */
        public E pop() {
            if (isEmpty()) return null;
            E answer = data[t];
            data[t] = null;                      // dereference to help garbage collection
            t--;
            return answer;
        }

        public void removeAll(E e) {
            //checks for the end of recursion
            if (size() < 1) {
                System.out.println("All elements have been removed.\n");
            }
            
            else {
                //update
                System.out.println("Element \""+pop()+"\" has been removed.\n");
                //recursive call 
                removeAll(top());
            }
        }


    }
    


    public static void main(String[] args) {
        //intialization
        ArrayStack c;
        c = new ArrayStack<>();
        for( int i = 1; i < 5; i++)
            c.push("Element: " + i);

        //initial call of the function
        c.removeAll(c.top());
    }
}