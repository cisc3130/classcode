import java.util.*;

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

    public E remove(int idx) {
        if (idx < 0 || idx >= size) throw new IndexOutOfBoundsException();
        Node tnd = head;
        for (int i = 0; i < idx; i++) { 
            tnd = tnd.next;
        }
        // tnd now holds the address of the node we want to remove (the node at idx)
        if (tnd.prev != null) {
            tnd.prev.next = tnd.next;       // link the node before tnd to the one after tnd
        } else {
            head = tnd.next;
        }
        if (tnd.next != null) {
            tnd.next.prev = tnd.prev;       // complete the link in the other direction
        } else {
            tail = tnd.prev;
        }

        size--;

        return tnd.elt;
    }

    public E get(int idx) {
        if (idx < 0 || idx >= size) throw new IndexOutOfBoundsException();
        Node tnd = head;
        for (int i = 0; i < idx; i++) {
            tnd = tnd.next;
        }
        return tnd.elt;
    }


    public E set(int idx, E elt) {
        if (idx < 0 || idx >= size) throw new IndexOutOfBoundsException();
        Node tnd = head;
        for (int i = 0; i < idx; i++) {
            tnd = tnd.next;
        }
        E toReturn = tnd.elt;
        tnd.elt = elt;
        return toReturn;
    }

    public int size() { return size; }

    public static void main(String[] args) {
        List<String> lst = new LinkedList<>();
        lst.add("hello");
        lst.add("goodbye");
        lst.add(0, "coffee");
        lst.add(2, "computer");
        lst.add("desk");
        lst.add("chair");
        lst.add("list");
        lst.remove(3);
        lst.remove(0);
        lst.remove(lst.size()-1);

        // This takes n^2 time!
        for (int i = 0; i < lst.size(); i++) {
            if (lst.get(i).equals("desk")) {
                lst.remove(i);
            }
        }

        // Instead, write it using an iterator
        Iterator<String> it = lst.iterator();
        while (it.hasNext()) {
            if (it.next().equals("desk")) {
                it.remove();
            }
        }
        // ^ This takes linear time



        DoublyLinkedList<Integer> intlist = new DoublyLinkedList<>();
        for (int i = 0; i < 10; i++) intlist.add(i);
        for (int i = 0; i < 10; i++) {
            int currValue = intlist.get(i);
            intlist.set(i, currValue + 1);
        }
        
    }
 
}