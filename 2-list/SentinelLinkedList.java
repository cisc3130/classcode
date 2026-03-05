import java.util.*;

public class SentinelLinkedList<E> {
    class Node {
        E data;
        Node prev, next;
        Node(E data) { this.data = data; }
    }

    Node sentinel;
    int size;

    public SentinelLinkedList() {
        sentinel = new Node(null);
        sentinel.next = sentinel;               // sentinel.next points to the first node (or sentinel if the list is empty)
        sentinel.prev = sentinel;               // sentinel.prev points to the last node (or sentinel if the list is empty)
    }

    public boolean isEmpty() { return sentinel.next == sentinel; }

    public boolean add(E elt) {
        // create the new node
        Node nnd = new Node(elt);

        // link nnd into the correct location
        // sentinel.prev points to the last node
        // ....... sentinel.prev <-> sentinel
        // after addition at the end of the list:
        // ....... sentinel.prev <-> nnd <-> sentinel
        sentinel.prev.next = nnd;
        nnd.prev = sentinel.prev;
        sentinel.prev = nnd;
        nnd.next = sentinel;

        // what if list is empty?
        // sentinel <-> sentinel
        // after insertion at the end of the list:
        // sentinel <-> nnd <-> sentinel

        size++;
        return true;
    }

    protected void addAfter(E elt, Node n) {
        Node nnd = new Node(elt);
        nnd.next = n.next;
        nnd.next.prev = nnd;
        nnd.prev = n;
        nnd.prev.next = nnd;
        size++;
    }

    public void add(int idx, E elt) {
        if (idx < 0 || idx > size) throw new IndexOutOfBoundsException();
        // special case: inserting at the end of the list
        if (idx == size) {
            add(elt);
            return;
        }

        Node tnd = sentinel.next;
        for (int i = 0; i < idx; i++) {
            tnd = tnd.next;
        }
        // tnd is now pointing to the node at index idx
        Node nnd = new Node(elt);
        nnd.prev = tnd.prev;
        nnd.prev.next = nnd;
        nnd.next = tnd;
        nnd.next.prev = nnd;

        size++;
    }

    public ListIterator<E> listIterator() {
        LinkedListIterator<E> lit = new LinkedListIterator<>(this, sentinel);
        return lit;
    }

    public ListIterator<E> listIterator(int idx) {
        if (idx < 0 || idx >= size) throw new IndexOutOfBoundsException();
        Node tnd = sentinel;
        for (int i = 0; i < idx; i++) {
            tnd = tnd.next;
        }
        // tnd is now pointing to the node at idx
        LinkedListIterator<E> lit = new LinkedListIterator<>(this, tnd);
        return lit;
    }
}