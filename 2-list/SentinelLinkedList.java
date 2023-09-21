public class SentinelLinkedList<E> {
    class Node {
        E data;
        Node next, prev;
        protected Node(E data) {
            this.data = data;
            next = prev =  null;
        }
    }

    Node sentinel;

    public SentinelLinkedList() {
        sentinel = new Node();
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    public boolean isEmpty() {
        return sentinel.next == sentinel;
    }

    public boolean add(int index, E elt) {
        Node nnd = new Node(elt);

        // what if the list is empty
        if (index < 0 || index > size()) return new IndexOutOfBoundsException();
        size++;

        Node tnd = sentinel;
        for (int i = 0; i < index-1; i++) tnd = tnd.next;
        // tnd now is a reference to the node before the one
        // currently at index

        nnd.next = tnd.next;
        nnd.next.prev = nnd;
        nnd.prev = tnd;
        nnd.prev.next = nnd;

        // what if the list was empty

        

        return true;
    }

    // midterm practice: implement the optimization of starting
    // from the end that the index is closest to. This applies
    // to get, remove, add
}