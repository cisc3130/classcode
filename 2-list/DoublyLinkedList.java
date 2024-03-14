import java.util.*;

public class DoublyLinkedList<E> {
    class Node {
        E data;
        Node prev, next;
        Node(E elt) { data = elt; }
    }

    Node head, tail;
    int size;

    public int size() { return size; }

    public void addFirst(E elt) {
        // 1. allocate the new node
        Node nnd = new Node(elt);
        // 2. save the address of the current first node
        nnd.next = head;        // this works whether or not there is currently a first node
                                // if the list is currently empty, head will be null,
                                // so we are assigning nnd.next = null, which is correct for the
                                // last node
        if (nnd.next != null) {
            nnd.next.prev = nnd;
        } else {
            tail = nnd;
        }
        // 3. link it to the list
        head = nnd;

        size++;
    }

    public E get(int idx) {
        if (idx < 0 || idx >= size) throw new IndexOutOfBoundsException();
        Node tnd = head;
        for (int i = 0; i < idx; i++) tnd = tnd.next;
        return tnd.data;
    }

    public boolean add(E elt) {

        return true;
    }

    public void add(int idx, E elt) {
        if (idx < 0 || idx > size) throw new IndexOutOfBoundsException();
        Node nnd = new Node(elt);           // 1. allocate the new node
        // get to idx. Create a tracker node, start at head, end at idx,
        // move forward using next
        // in order to put nnd at idx, node[idx-1].next = nnd
        // and nnd.next = node[idx]
        if (idx == size) {
            tail.prev.
        }
        Node tnd = head;
        for (int i = 0; i < idx; i++) {
            tnd = tnd.next;
        }   // tnd now equals node[idx]
        if (tnd.prev != null) {
            tnd.prev.next = nnd;
        } else {
            head = nnd;
        }
        nnd.prev = tnd.prev;
        nnd.next = tnd;
        tnd.prev = nnd;
        size++;
    }


    public void addToSortedList(E elt) {
        Node nnd = new Node(elt);
        // find correct location to put elt
        // move the tracker node forward until we reach an element that is larger than elt
        if (head == null) { // edge case #1: what if the list is empty
            head = nnd;
            size++;
            return;
        }
        Node tnd = head;
        while (!tnd.data.compareTo(elt) <= 0) {
            if (tnd.next == null) {
                break;
            }
            tnd = tnd.next;
        }
        // tnd should now equal the first element that is larger than elt
        if (tnd.data.compareTo(elt) <= 0) {     // edge case #3: what if nnd should be inserted at the end of the list
                                                // tnd now points to the last node but it's not larger than nnd
            // insert it after tnd instead of before
            tnd.next = nnd;
            nnd.prev = tnd;
            size++;
            return;
        }
        nnd.next = tnd;
        nnd.prev = tnd.prev;
        tnd.prev = nnd;
        if (nnd.prev == null)   // edge case #2: what if nnd is being inserted at the beginning of the list {
            head = nnd;
        } else {
            nnd.prev.next = nnd;
        }

        size++;
    }


    
}