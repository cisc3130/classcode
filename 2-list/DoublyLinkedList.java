public class DoublyLinkedList<E> {

    class Node {
        E elt;
        Node prev, next;
        Node(E elt) { this.elt = elt; }
    }

    Node head, tail;
    int size;

    public DoublyLinkedList() {
        head = tail = null;
        size = 0;
    }

    public boolean add(E elt) {
        Node nnd = new Node(elt);
        nnd.prev = tail;
        tail = nnd;

        // special case: list is empty
        if (head == null) {
            head = nnd;
        } else {
            nnd.prev.next = nnd;
        }
        
        size++;
        return true;
    }

    public void add(int idx, E elt) {
        if (idx < 0 || idx > size) throw new IndexOutOfBoundsException();
        // special case: inserting at the end of the list
        if (idx == size) {
            add(elt);
            return;
        }

        Node tnd = head;
        for (int i = 0; i < idx; i++) {
            tnd = tnd.next;
        }
        // tnd is now pointing to the node at index idx
        Node nnd = new Node(elt);
        nnd.prev = tnd.prev;
        if (nnd.prev != null) {
            nnd.prev.next = nnd;
        } else {
            head = nnd;
        }
        nnd.next = tnd;
        nnd.next.prev = nnd;

        size++;
    }

    public static void main(String[] args) {
        DoublyLinkedList<String> lst = new DoublyLinkedList<>();
        lst.add("hello");
        lst.add("goodbye");
        lst.add(0, "coffee");
        lst.add(2, "computer");
    }
 
}