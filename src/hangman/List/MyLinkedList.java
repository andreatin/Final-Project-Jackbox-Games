package hangman.List;

//this is the main class defining the MyLinkedList type and its methods and attributes 
public class MyLinkedList implements ListInterface {
    protected int size;
    protected Node head;
    protected Node tail;

    protected class Node {
        Object item;
        Node next;

        Node(Object o) {
            item = o;
            next = null;
        }
    }

    //function that gets the input from the user 
    public Object get(int index) {

        if (index >= 0 && index < size) {
            Node current = head;
            int i = 0;
            while (i < index) {
                current = current.next;
                i++;
            }
            return current.item;
        }
        else
            throw new ListIndexOutOfBoundsException("Index " + index + " out of bounds in get()");
    }

    //returns the object as a string when called
    public String toString() {
        String s = "(";
        Node current = head;
        while (current != null) {
            s = s + current.item;
            if (current.next != null)
                s = s + ", ";
            current = current.next;
        }
        s = s + ")";
        return s;
    }

    //returns the size of the list
    public int size() {
        return size;
    }

    //checks if the list is empty
    public boolean isEmpty() {
        if (size == 0)
            return true;
        else
            return false;
    }

    //adds an object to the list 
    public void add(int index, Object o) {
        if (index >=0 && index <= size) {
            if (index == 0) {
                // Adding to the front of the list
                Node newNode = new Node(o);
                newNode.next = head;
                head = newNode;
                if (tail == null)
                    tail = newNode;
            }
            else if (index == size) {
                // Adding to the end of the list
                Node newNode = new Node(o);
                tail.next = newNode;
                tail = newNode;
            }
            else {
                // Adding in the middle of the list
                int i = 0;
                Node current = head;
                while(i < index - 1) {
                    current = current.next;
                    i++;
                }
                Node newNode = new Node(o);
                newNode.next = current.next;
                current.next = newNode;
            }
            size++;
        }
        else {
            throw new ListIndexOutOfBoundsException("Index " + index + " out of bounds in add()");
        }

    }

    //removes elements from MyLinkedList
    public void remove(int index) {
        if (index >= 0 && index < size) {
            if (index == 0) {
                // Removing an element from the front of the list
                head = head.next;
                if (head == null)
                    tail = null;
            }
            else if (index == size - 1) {
                // Removing the last element of the list
                Node current = head;
                for(int i = 0; i < size - 2; i++) {
                    current = current.next;
                }
                tail = current;
                tail.next = null;
            }
            else {
                // Removing an element from the middle of the list
                Node prev = head;
                for(int i = 1; i < index; i++) {
                    prev = prev.next;
                }
                prev.next = prev.next.next;
            }
            size--;
        }
        else {
            throw new ListIndexOutOfBoundsException("Index " + index + " out of bounds in remove()");
        }
    }

    //empties the list 
    public void removeAll() {
        size = 0;
        head = null;
        tail = null;
    }

    //algorithm to search for an element of the list
    public int find(Object o) {
        Node current = head;
        int i = -1;
        while(current != null) {
            i++;
            if (current.item.equals(o))
                return i;
            else
                current = current.next;
        }
        return -1;
    }

    public MyLinkedList() {
        size = 0;
        head = null;
        tail = null;
    }
}
