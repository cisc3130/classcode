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
        return prev.data;
    }

}