import java.lang.*;

public class DoublyLinkedList<E> {
    class Node {
        E data;
        Node next, prev;
        protected Node(E data) {
            this.data = data;
            next = prev =  null;
        }
    }

    Node head, tail;

    public DoublyLinkedList() {
        head = tail = null;
    }

    public int size() {
        return -1;   // NEED TO IMPLEMENT
    }

    public boolean add(E elt) {
        Node nnd = new Node(elt);
        // what if the list is empty
        if (head == null) {
            head = tail = nnd;
            return true;
        }
        tail.next = nnd;
        nnd.prev = tail;
        tail = nnd;
        return true;
    }

    public E remove() {     // default remove: remove the last node
        // what if the list is empty
        if (head == null) return null;

        E delVal = tail.data;

        // what if there is just one node
        if (head == tail) {
            head = tail = null;
            return delVal;
        }

        tail = tail.prev;
        tail.next = null;
        return delVal;
    }

    public boolean add(int index, E elt) {
        Node nnd = new Node(elt);

        // what if the list is empty
        if (index < 0 || index > size()) return new IndexOutOfBoundsException();

        // what if index = 0
        if (index == 0) {
            nnd.next = head;
            nnd.next.prev = nnd;        // can this be null? UP TO THIS 9/19
            head = nnd;
        }

        Node tnd = head;
        for (int i = 0; i < index-1; i++) tnd = tnd.next;
        // tnd now is a reference to the node before the one
        // currently at index

        nnd.next = tnd.next;
        nnd.next.prev = nnd;
        nnd.prev = tnd;
        nnd.prev.next = nnd;
    }
}