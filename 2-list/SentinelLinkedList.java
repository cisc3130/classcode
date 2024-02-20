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

    
}