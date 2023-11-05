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

    public boolean removeLastOccurrence(E target) {
        // iterate through the list
        // when you find an instance of the element, keep a reference to it
        // update the reference if you find another instance of the element so it's always pointing to the last one
        // when you reach the end of the list, remove the last instance that you found.

        // iterate through the list: use a tracker node, start at sentinel, end at sentinel, move forward using next
        Node tnd = sentinel.next;       // sentinel.next points to the first node
        Node dnd = null;
        while (tnd != sentinel) {       // end when you reach sentinel. don't enter the loop if the list is empty (sentinel.next == sentinel)
            if (tnd.data.equals(target)) dnd = tnd;
        }
        if (dnd == null) return false;

        dnd.prev.next = dnd.next;
        dnd.next.prev = dnd.prev;       // without the sentinel, you would have to make sure dnd.prev and/or dnd.next exist
        return true;

        // This is inefficient. We should really start at the end of the list (Thank you Abir)
        // Exercise for you offline
    }

    // midterm practice: implement the optimization of starting
    // from the end that the index is closest to. This applies
    // to get, remove, add
}