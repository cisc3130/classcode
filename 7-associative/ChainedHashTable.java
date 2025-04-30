import java.util.*;

public class ChainedHashTable<E> {
    List<E>[] data;
    float targetLoadFactor;
    int size;

    public ChainedHashTable() {
        this(11, 0.75f);
    }

    public ChainedHashTable(int initialCapacity) {
        this(initialCapacity, 0.75f);
    }

    public ChainedHashTable(int initialCapacity, float loadFactor) {
        if (loadFactor < 0) throw new IllegalArgumentException();
        this.data = (List<E>[]) new List[initialCapacity];
        this.targetLoadFactor = loadFactor;
    }

    private void grow() {

    }

    private ListIterator<E> probe(E elt) {
        int hash = elt.hashCode();
        int idx = hash % data.length;
        if (data[idx] == null)  {                           // lazy strategy: create a new list only when needed
            data[idx] = (LinkedList<E>) new LinkedList();
            return data[idx].listIterator();
        }
        ListIterator<E> it = data[idx].listIterator();
        while (it.hasNext()) {
            E nextElt = it.next();
            if (nextElt.equals(elt)) {
                it.previous();    // move iterator back so it's before the element we're looking for
                return it;
            }
        }
        return it;
    }

    public boolean add(E elt) {
        if ((size / data.length) >= targetLoadFactor) grow();
        ListIterator<E> it = probe(elt);
        if (it.hasNext()) return false;     // if the iterator has a next element, it means the element is already in the list
        it.add(elt);
        size++;
        return true;
    }

    public E get(E elt) {
        Iterator<E> it = probe(elt);
        if (!it.hasNext()) return null;
        return it.next();
    }

    public boolean contains(E elt) {
        Iterator<E> it = probe(elt);
        if (!it.hasNext()) return false;
        return true;
    }

    public boolean remove(E elt) {
        ListIterator<E> it = probe(elt);
        if (!it.hasNext()) return false;    // it does not have next which means the element is not in the list
        it.remove();
        size--;
        return true;
    }

    public static void main(String[] args) {
        ChainedHashTable<Integer> t = new ChainedHashTable<>(5);
        t.add(72);
        t.add(96);
        t.add(11);
        t.remove(96);
        t.add(11);
        t.remove(72);
        t.add(87);
    }

}
