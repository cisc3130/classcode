public class DoublyLinkedList<E> {
    class Node {
        E data;
        Node prev, next;
        Node(E elt) { data = elt; }
    }

    Node head, tail;
    int size;

    public void addFirst(E elt) {

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
}