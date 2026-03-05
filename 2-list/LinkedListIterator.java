import java.util.NoSuchElementException;
import java.util.*;

public class LinkedListIterator<E> implements ListIterator<E> {

    SentinelLinkedList<E> list;
    SentinelLinkedList<E>.Node prev, lastReturned;


    protected LinkedListIterator(SentinelLinkedList<E> list, SentinelLinkedList<E>.Node prev) {
        this.list = list;
        this.prev = prev;
        this.lastReturned = null;
    }

    public boolean hasNext() {
        return prev.next != list.sentinel;
    }

    public boolean hasPrevious() {
        return prev != list.sentinel;
    }

    public E next() {
        if (!hasNext()) throw new NoSuchElementException();
        // [prev] ->| [prev.next]
        prev = prev.next;
        // [former prev] -> [new prev] ->| [new prev.next]
        E toReturn = prev.data;
        lastReturned = prev;
        return toReturn;
    }

    public E previous() {
        if (!hasPrevious()) throw new NoSuchElementException();
        E toReturn = prev.data;
        lastReturned = prev;
        prev = prev.prev;
        return toReturn;
    }

    public void set(E newValue) {
        if (lastReturned == null) throw new IllegalStateException();
        lastReturned.data = newValue;
    }

    public void remove() {
       if (lastReturned == null) throw new IllegalStateException();
       lastReturned.prev.next = lastReturned.next;
       lastReturned.next.prev = lastReturned.prev;
       list.size--;
       if (lastReturned == prev) prev = prev.prev;
       lastReturned = null;
    }


    public int previousIndex() { throw new UnsupportedOperationException(); }

    public int nextIndex() { throw new UnsupportedOperationException(); }

    public void add(E elt) {
       list.addAfter(elt, prev);
    }

    public static void main(String[] args) {
        SentinelLinkedList<String> lst = new SentinelLinkedList<>();
        String[] arr = {"hello", "goodbye", "coffee", "desk", "computer"};
        for (String s : arr) 
            lst.add(s);

        List<String> alist = new ArrayList<>();
        for (String s : arr) {
            alist.add(s);
        }
        ListIterator<String> lit = alist.listIterator();
        while (lit.hasNext()) {
            String s = lit.next();
            if (s.equals("coffee")) {
                lit.remove();
            }
            else if (s.equals("desk")) {
                lit.add("anotherDesk");
            }
            else if (s.equals("computer")) {
                lit.set("pc");
            }
        }
    }

}