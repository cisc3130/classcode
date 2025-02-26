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
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    public boolean isEmpty() { return sentinel.next == sentinel; }

    public boolean add(E elt) {
        // create the new node
        Node nnd = new Node(elt);

        // link nnd into the correct location
        // sentinel.prev points to the last node
        sentinel.prev.next = nnd;
        nnd.prev = sentinel.prev;
        sentinel.prev = nnd;
        nnd.next = sentinel;

        size++;
        return true;
    }

    
}