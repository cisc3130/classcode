public class MLinkedList<E> {

    protected class Node {
        E item;
        Node next;
        Node(E item) {
            this.item = item;
        }
    }

    Node head;
    int size;

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return size;
    }

    protected void addFirst(E elt) {
        // 1. allocate memory
        Node nnd = new Node(elt);
        // 2. links
        nnd.next = head;
        head = nnd;
        // 3. size
        size++;
        // 4. check edge cases
    }

    public boolean add(E elt) {
        if (head == null) {
            addFirst(elt);
            return true;
        }
        // 1. allocate memory
        Node nnd = new Node(elt);
        // use a tracker node to find the end of the list
        Node tnd = head;
        while (tnd.next != null) {
            tnd = tnd.next;
        }
        // when we exit the loop, tnd.next equals null
        // 2. links
        tnd.next = nnd;
        // 3. size
        size++;
        // 4. check edge cases
        return true;
    }

    public void add(int idx, E elt) {
        if (idx < 0 || idx > size) throw new IndexOutOfBoundsException();

        if (idx == 0) {
            addFirst(elt);
            return;
        }

        // 1. allocate memory
        Node nnd = new Node(elt);
        // use a tracker node to find index idx
        Node tnd = head;
        for (int i = 0; i < idx-1; i++) {
            tnd = tnd.next;
        }
        // tnd now points to the element before the target index
        // we will insert nnd after tnd
        // 2. links
        nnd.next = tnd.next;
        tnd.next = nnd;

        // 3. size
        size++;

        // 4. check edge cases
        

    }

    public E remove(int idx) {
        if (idx < 0 || idx >= size) throw new IndexOutOfBoundsException();

        E toReturn;

        if (idx == 0) {
            toReturn = head.item;
            head = head.next;
            return toReturn;
        } 
        else {
            // use tracker node to get to idx-1
            Node tnd = head;
            for (int i = 0; i < idx-1; i++) {
                tnd = tnd.next;
            }
            // tnd now points to the node before idx
            toReturn = tnd.next.item;

            // links
            tnd.next = tnd.next.next;
        }
        
        // size
        size--;

        // edge cases

        return toReturn;

    }


    


    public static void main(String[] args) {
        MLinkedList<String> lst = new MLinkedList<>();
        lst.add("hello");
        lst.add("goodbye");
        lst.add("desk");
        lst.addFirst("computer");
        lst.add(2, "chair");
        // lst.remove(2);
    }
   
}