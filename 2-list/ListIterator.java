import java.lang.*;

public class ListIterator<E> {
    SentinelLinkedList<E> lst;
    Node previous, next, lastReturned;

    protected ListIterator(Node previous) {
        this.previous = previous;
        this.next = previous.next;
        this.lastReturned = null;
    }

    public boolean hasNext() {
        return next != lst.sentinel;
    }

    public boolean hasPrevious() {
        return previous != lst.sentinel;
    }

    public E next() {
        if (!this.hasNext()) throw new NoSuchElementException();
        E toReturn = next.data;
        lastReturned = next;
        previous = next;
        next = next.next;
        return toReturn;
    }

    public void remove() {
        if (lastReturned == null) throw new IllegalStateException();
        lastReturned.previous.next = lastReturned.next;
        lastReturned.next.previous = lastReturned.previous;
        lst.size--;
        if (lastReturned == previous) {
            previous = lastReturned.previous;
        } else {
            next = lastReturned.next;
        }
        lastReturned = null;
    }
}