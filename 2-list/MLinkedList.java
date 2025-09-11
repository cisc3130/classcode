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
    }
   
}