public class DoublyLinkedList<E> {
    class Node {
        E data;
        Node next, prev;
        Node(E data) { this.data = data; }
    }

    Node head, tail;
    int size;

    public boolean add(E elt) {
        // create the new node
        Node nnd = new Node(elt);

        // edge case: what if the list is empty
        if (head == null) {
            tail = nnd;
            head = nnd;
        }
        else {
            // link it to the list
            tail.next = nnd;
            tail = tail.next;
        }

        size++;
        return true;
    }

    public void add(int idx, E elt) {
        // validate the index
        if (idx < 0 || idx > size) throw new IndexOutOfBoundsException();

        // if idx equals size, delegate to the default add method
        if (idx == size) {
            add(elt);
            return;
        }

        // create the new node
        Node nnd = new Node(elt);

        // get to the correct spot through traversal
        Node tnd = head;
        for (int i = 0; i < idx; i++) {
            tnd = tnd.next;
        }
        // tnd is now pointing to the index where nnd should be inserted
        // nnd should be inserted before tnd
        // 4 links - ensure correct order
        // edge case: what if we're inserting at the beginning of the list:
        if (idx == 0) {
            head = nnd;
        }
        else {
            tnd.prev.next = nnd;
        }
        nnd.prev = tnd.prev;
        nnd.next = tnd;
        tnd.prev = nnd;

        size++;
    }

    public E remove(int idx) {
        if (idx < 0 || idx >= size) throw new IndexOutOfBoundsException();

        // traverse to the correct index
        Node tnd = head;
        for (int i = 0; i < idx; i++) {
            tnd = tnd.next;
        }
        // tnd is now pointing to the node to be removed

        // 2 links:
        // edge case: what if we're removing the first node
        if (idx == 0) {
            head = head.next;
        } else {
            tnd.prev.next = tnd.next;
        }
        if (idx == size-1) {
            tail = tail.prev;
        } else{
            tnd.next.prev = tnd.prev;
        }

        size--;
        return tnd.data;
    }

}