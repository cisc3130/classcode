public class MLinkedList<E> {
    protected class Node {
        E data;
        Node next;
        Node(E data) { this.data = data; }
    }

    Node head;
    int size;

    public MLinkedList() {}

    public boolean isEmpty() { return head == null; }

    public boolean add(E elt) {             // add elt to the end of the list
        // create the new node
        Node nnd = new Node(elt);

        // edge case: if the list is empty
        if (head == null) {
            head = nnd;
        } else {    // most cases: if list is not empty:
            // get to the end of the list
            Node tnd = head;
            while (tnd.next != null) tnd = tnd.next;
            // tnd now is the address of the last node (tnd.next == null)
            
            // link the last node to the new node
            tnd.next = nnd;
        }

        // increment size
        size++;

        return true;
    }

    public E remove(int idx) {
        if (idx < 0 || idx >= size) throw new IndexOutOfBoundsException();

        // edge case: what if the node to be removed is the first one
        if (idx == 0) {
            head = head.next;
        }

        // get to the right place
        Node tnd = head;
        for (int i = 0; i < idx-1; i++) tnd = tnd.next;
        // tnd is now pointing to the node before the one we will remove

        // save the value to be returned
        E toReturn = tnd.next.data;

        // tnd.next is pointing to the node to be removed. Link it instead
        // to the node after that
        tnd.next = tnd.next.next;

        // decrement size
        size--;

        return toReturn;
    }

    public E remove(E valueToRemove) {
        // traverse through the list
        // compare each node to the target value
        // if the value is equal, remove the node

        // edge case: what if the list is empty
        if (head == null) {
            return null;
        }

        // edge case: what if the value to remove is in the first node
        if (head.data.equals(valueToRemove)) {
            head = head.next;
            size--;
            return valueToRemove;
        }

        Node tnd = head;
        while (tnd.next != null && !tnd.next.data.equals(valueToRemove)) {
            tnd = tnd.next;
        }
        // tnd.next.data is now equal to valueToRemove OR tnd.next is null and the value is not in the list
        if (tnd.next == null) {     // valueToRemove is not in list
            return null;
        }
        
        // tnd.next is not null which means that tnd.next.data is equal to valueToRemove
        tnd.next = tnd.next.next;
        
        size--;
        return valueToRemove;
    }

    public static void main(String[] args) {
        String[] strs = { "hello", "goodbye", "cat", "dog", "coffee", "computer"};
        MLinkedList<String> lst = new MLinkedList<>();
        lst.add("hello");
        int x = 72;
        lst.add("goodbye");
        String s = "data structures";
        String t = "operating systems";
        lst.add("cat");
        int y = 89;
        int z = 4;
        lst.add("coffee");
        lst.remove(2);
    }
}