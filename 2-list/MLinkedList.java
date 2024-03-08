public class MLinkedList<E> {
    class Node {
        E data;
        Node next;

        Node(E data) { this.data = data; }
    }

    Node head, tail;
    int size;


    public boolean isEmpty() {
        return head == null;
    }

    public int size() { return size; }

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
        size++;
        return true;
    }

    public boolean add(E elt) {
        // 1. allocate the new node
        Node nnd = new Node(elt);
        size++;
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
        size--;
        return toReturn;
        // what if the list is empty?
        // what is the list has only one element?
    }

    public void add(int idx, E elt) {
        if (idx < 0 || idx > size) throw new IndexOutOfBoundsException();
        if (idx == 0) {             // edge case: what if this is the first element
            addFirst(elt);
            return;
        }
        Node nnd = new Node(elt);           // 1. allocate the new node
        // get to idx. Create a tracker node, start at head, end at idx,
        // move forward using next
        // in order to put nnd at idx, node[idx-1].next = nnd
        // and nnd.next = node[idx]
        Node tnd = head;
        for (int i = 0; i < idx-1; i++) {
            tnd = tnd.next;
        }   // tnd now equals node[idx-1]
        nnd.next = tnd.next;                     // nnd now points to the node currently at idx
        tnd.next = nnd;
        if (idx == size) tail = nnd;        // edge case: what if this is the last element
        size++;
    }

    public E remove(int idx) {
        if (idx < 0 || idx >= size) throw new IndexOutOfBoundsException();
        if (idx == 0) return removeFirst();
        Node tnd = head;
        for (int i = 0; i < idx-1; i++) {
            tnd = tnd.next;
        }   // tnd now points to the node before the one at idx
        E toReturn = tnd.next.data;
        tnd.next = tnd.next.next;
        if (idx == (size-1)) tail = tnd;
        size--;
        return toReturn;
    }

    public static void main(String[] args) {
        MLinkedList<String> lst = new MLinkedList<>();
        lst.add("floor");
        lst.add(1, "chair");
        lst.add(1, "coffee");
        lst.remove(0);
        lst.remove(lst.size()-1);
    }
}