import java.util.NoSuchElementException;
import java.util.*;

public class LinkedListIterator<E> implements ListIterator<E> {

    SentinelLinkedList<E> list;
    SentinelLinkedList<E>.Node prev, lastReturned;


    public LinkedListIterator(SentinelLinkedList<E> list, SentinelLinkedList<E>.Node prev) {
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
        prev = prev.next;
        lastReturned = prev;
        return lastReturned.data;
    }

    public E previous() {
        if (!hasPrevious()) throw new NoSuchElementException();
        prev = prev.prev;
        lastReturned = prev.next;
        return lastReturned.data;
    }

    public void set(E newElt) {
        if (lastReturned == null) throw new IllegalStateException();
        lastReturned.data = newElt;
    }

    public void remove() {
        if (lastReturned == null) throw new IllegalStateException();
        lastReturned.prev.next = lastReturned.next;
        lastReturned.next.prev = lastReturned.prev;
        list.size--;
        lastReturned = null;
    }

    public int previousIndex() { throw new UnsupportedOperationException(); }

    public int nextIndex() { throw new UnsupportedOperationException(); }

    public void add(E elt) {
        SentinelLinkedList<E>.Node nnd = list.new Node(elt);
        nnd.next = prev.next;
        nnd.next.prev = nnd;
        nnd.prev = prev;
        nnd.prev.next = nnd;
        list.size++;
        lastReturned = null;
    }

    public static void main(String[] args) {
        SentinelLinkedList<String> lst = new SentinelLinkedList<>();
        String[] arr = {"hello", "goodbye", "coffee", "desk", "computer"};
        for (String s : arr) 
            lst.add(s);

        

    }

}