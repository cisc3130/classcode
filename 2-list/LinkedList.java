public class LinkedList<E> {
    class Node {
        E data;
        Node next;

        Node(E data) { this.data = data; }
    }

    Node head, tail;

    public boolean isEmpty() {
        return head == null;
    }

    public boolean addFirst(E elt) {
        // 1. allocate the new node
        Node nnd = new Node(elt);
        // 2. save the address of the current first node
        nnd.next = head;        // this works whether or not there is currently a first node
                                // if the list is currently empty, head will be null,
                                // so we are assigning nnd.next = null, which is correct for the
                                // last node
        // 3. link it to the list
        head = nnd;
        // tail bookkeeping 
        if (head.next == null) tail = nnd;
        return true;
    }

    public boolean add(E elt) {
        // 1. allocate the new node
        Node nnd = new Node(elt);
        // edge case: what if the list is empty?
        if (head == null) {
            head = nnd;
            tail = nnd;
            return true;
        }

        /* This is only necessary without a tail reference. With a tail reference we can directly access the last element
        // get to the last node
        // start at head, end when node.next == null, move forward using .next
        // a. create a tracker node, start it at head
        Node tnd = head;        // note that we don't use *new* here. We are using the address
                                // of an existing node, not creating new memory
        while (tnd.next != null) {
            tnd = tnd.next;
        }
        // when we come out of this loop, tnd.next is equal to null
        // which means that tnd is pointing to (holds the address of) the last node in the list.
        // 3. link nnd to the list
        tnd.next = nnd;
        */

        tail.next = nnd;
        tail = nnd;

        return true;
    }

    public E removeFirst() {
        if (head == null) return null;      // edge case: empty list
        if (head == tail) tail = null;      // edge case: list with one element
        E toReturn = head.data;
        head = head.next;
        return toReturn;
        // what if the list is empty?
        // what is the list has only one element?
    }

    public static void main(String[] args) {
        LinkedList<String> lst = new LinkedList<>();
        lst.add("floor");
        lst.addFirst("hello");
        lst.addFirst("goodbye");
        lst.addFirst("coffee");
        lst.addFirst("computer");
        lst.add("desk");
        lst.add("chair");
    }
}