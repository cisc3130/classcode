public class MLinkedList<E> {

    class Node {
        E elt;
        Node next;
        Node(E elt) {
            this.elt = elt;
        }
    }

    Node head;
    int size;

    public MLinkedList() {
        head = null;
        size = 0;
    }

    public boolean add(E elt) {
        // special case: what if the list is empty
        if (head == null) {
            head = new Node(elt);
            size++;
            return true;
        }

        // get to the end of the list
        Node tnd = head;
        while (tnd.next != null) {
            tnd = tnd.next;
        }
        // tnd.next is null := tnd is the last nd := this is the node we need to insert after
        // allocate memory
        Node nnd = new Node(elt);
        // link it up
        tnd.next = nnd;
        // increment size
        size++;
        return true;
    }

    public E remove(int idx) {
        if (idx < 0 || idx >= size) throw new IndexOutOfBoundsException();
        // special case: idx == 0
        if (idx == 0) {
            E toReturn = head.elt;
            head = head.next;
            size--;
            return toReturn;
        }
        // move to the element before the one we want to remove
        Node tnd = head;
        for (int i = 0; i < idx-1; i++) {
            tnd = tnd.next;
        }
        // tnd is now pointing to the node at index idx-1
        // save the value to return
        E toReturn = tnd.next.elt;
        tnd.next = tnd.next.next;   // link tnd to the node after the one after it
        size--;

        return toReturn;
    }


    public static void main(String[] args) {
        MLinkedList<String> lst = new MLinkedList<>();
        lst.add("hello");
        lst.add("goodbye");
        lst.add("desk");
        lst.add("computer");
        lst.remove(2);
    }
   
}