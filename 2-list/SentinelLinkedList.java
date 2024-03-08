import java.util.ListIterator;

public class SentinelLinkedList<E> {
    class Node {
        E data;
        Node prev, next;
        Node(E elt) { data = elt; }
    }

    Node sentinel;
    int size;

    public SentinelLinkedList() {
        sentinel = new Node(null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    public boolean isEmpty() { return sentinel.next == sentinel; }

    public void addFirst(E elt) {
        // 1. allocate the new node
        Node nnd = new Node(elt);
        // 2. save the address of the current first node
        nnd.next = sentinel.next;        // this works whether or not there is currently a first node
                                // if the list is currently empty, head will be null,
                                // so we are assigning nnd.next = null, which is correct for the
                                // last node
        nnd.next.prev = nnd;

        // 3. link it to the list
        sentinel.next = nnd;
        nnd.prev = sentinel;

        size++;
    }

    public void add(int idx, E elt) {
        if (idx < 0 || idx > size) throw new IndexOutOfBoundsException();
        Node nnd = new Node(elt);
        Node tnd;
        if (idx <= size/2) {
            tnd = sentinel.next;
            for (int i = 0; i < idx; i++) tnd = tnd.next;
        } else {
            tnd = sentinel;
            for (int i = size-1; i > idx; i--) tnd = tnd.prev;
        }
        // tnd is now pointing to the element at idx
        tnd.prev.next = nnd;
        nnd.prev = tnd.prev;
        tnd.prev = nnd;
        nnd.next = tnd;
        size++;
    }

    public boolean add(E elt) {
        this.add(this.size, elt);
        return true;
    }
    
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException();
    }

    
}