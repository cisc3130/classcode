public class LinkedList<E> {
    protected class Node {
        E data;
        Node next;

        Node(E data) {
            this.data = data;
            this.next = null;
        }
    }

    Node head, tail;

    public LinkedList() {
        head = tail = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    /*
    public boolean add(E elt) {
        Node nnd = new Node(elt);       // 1. allocate memory
        
        // for(
        //     Node pnd = head;            // start at head
        //     pnd != null;                // end at null
        //     pnd = pnd.next;             // move forward using next ptr
        // )
        

        // 3. edge case - what if the list is empty
        if (head == null) {
            head = nnd;         // this takes care of 4. make sure head is correct
            return true;
        }

        Node pnd = head;
        while (pnd.next != null) pnd = pnd.next;
        // now we are at the end of the list. insert nnd here.
        // 2. links
        pnd.next = nnd;
        return true;
    }   
    */

    // add method taking advantage of tail
    public boolean add(E elt) {
        Node nnd = new Node(elt);

        if (tail == null) {
            // list is empty
            head = tail = nnd;
            return true;
        }
        tail.next = nnd;
        tail = nnd;
        return true;
    }

    public E remove() {     // remove the last node
        // what if list is empty
        if (head == null) return null;

        E delVal = tail.data;

        // what if list has just one element
        if (head == tail) {
            head = tail = null;
            return delVal;
        }

        // iterate to the second to last node
        Node tnode = head;
        while (tnode.next != tail) {
            tnode = tnode.next;
        }  
        // when we exit the loop, tnode.next == tail
        tail = tnode;
        tnode.next = null;
        return delVal;
    }
}