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
        // 1. allocate memory
        Node nnd = new Node(elt);

        if (head == null) {         // special case: if list is empty
            head = nnd;
        } else {        // 2. links
            tail.next = nnd;
            nnd.prev = tail;
        }

        tail = nnd;

        // 3. increment size
        size++;

        // 4. check edge cases

        return true;
    }


    public void add(int idx, E elt) {
        if (idx < 0 || idx > size) throw new IndexOutOfBoundsException();

        // 1. allocate memory
        Node nnd = new Node(elt);
        // use a tracker node to find index idx
        Node tnd = head;
        for (int i = 0; i < idx-1; i++) {
            tnd = tnd.next;
        }
        // tnd now points to the element before the target index
        // we will insert nnd after tnd

        // tnd <-> tnd.next
        // after insertion:
        // tnd <-> nnd <-> tnd.next

        if (idx == 0) {         // edge case: inserting at the beginning
            nnd.next = head;
            if (head != null) {         // edge case: make sure the list is not empty
                head.prev = nnd;
            }
            head = nnd;
        } else {
            nnd.next = tnd.next;
            if (tnd.next != null) {
                tnd.next.prev = nnd;
            } else {                    // edge case: inserting at the end
                tail = nnd;
            }
            tnd.next = nnd;
            nnd.prev = tnd;
        }

        // 3. size
        size++;

        // 4. check edge cases
    }

    public E remove(int idx) {
        if (idx < 0 || idx >= size) throw new IndexOutOfBoundsException();
        Node tnd = head;
        for (int i = 0; i < idx; i++) { 
            tnd = tnd.next;
        }
        // tnd now holds the address of the node we want to remove (the node at idx)
        // tnd.prev <-> tnd <-> tnd.next
        // after removal:
        // tnd.prev <-> tnd.next
        if (tnd.prev != null) {
            tnd.prev.next = tnd.next;
        } else {            // edge case: tnd is first node
            head = tnd.next;
        }
        if (tnd.next != null) {
            tnd.next.prev = tnd.prev;
        } else {            // edge case: tnd is last node
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
        DoublyLinkedList<String> lst = new DoublyLinkedList<>();
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