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
    int size;

    public DoublyLinkedList() {
        head = tail = null;
    }

    public int size() {
        // midterm practice: write a method that
        // iterates through the list and counts all nodes
        return size;   // NEED TO IMPLEMENT
    }

    public boolean add(E elt) {
        Node nnd = new Node(elt);
        size++;
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
        size--;

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
        size++;

        // what if index = 0
        if (index == 0) {
            nnd.next = head; // (1)
            if (nnd.next != null) nnd.next.prev = nnd;   // (2)
            else tail = head;
            head = nnd; // (3)
            return true;
        }

        // what if index = size
        if (index == size()) return add(elt);

        Node tnd = head;
        for (int i = 0; i < index-1; i++) tnd = tnd.next;
        // tnd now is a reference to the node before the one
        // currently at index

        nnd.next = tnd.next;
        nnd.next.prev = nnd;
        nnd.prev = tnd;
        nnd.prev.next = nnd;

        return true;
    }

    public E get(int index) {
        if (index < 0 || index >= size()) throw new IndexOutOfBoundsException();
        Node tnd = head;
        for (int i = 0; i < index; i++) tnd = tnd.next;
        // tnd is now pointing to the node at index
        return tnd.data;
    }

    // midterm practice example: write a live insertion sort
    // where new items keep coming in and they need to be 
    // inserted into a list in sorted order
}