import java.util.NoSuchElementException;

public class LinkedListIterator<E> {            // implements ListIterator<E>

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

    public E set(E newElt) {
        if (lastReturned == null) throw new IllegalStateException();
        E oldElt = lastReturned.data; 
        lastReturned.data = newElt;
        return oldElt;
    }

    public E remove() {
        if (lastReturned == null) throw new IllegalStateException();
        lastReturned.prev.next = lastReturned.next;
        lastReturned.next.prev = lastReturned.prev;
        list.size--;
        E toReturn = lastReturned.data;
        lastReturned = null;
        return toReturn;
    }

}