import java.util.*;

public class HashTable<E> {
    E[] data;
    float targetLoadFactor;
    int size;
    final E DELETED;

    public HashTable() {
        this(16, 0.75f);
    }

    public HashTable(int initialCapacity) {
        this(initialCapacity, 0.75f);
    }

    public HashTable(int initialCapacity, float loadFactor) {
        if (loadFactor > 1 || loadFactor <= 0) throw new IllegalArgumentException();
        this.data = (E[]) new Object[initialCapacity];
        this.targetLoadFactor = loadFactor;
        DELETED = (E) new Object();
    }

    private void grow() {
        int newCapacity = data.length * 2 + 1;
        E[] newData = (E[]) new Object[newCapacity];
        for (E elt : data) {
            if (elt != null && !elt.equals(DELETED)) {
                int idx = probe(elt);
                newData[idx] = elt;
            }
        }
        data = newData;
    }

    private int probe(E elt) {
        int hash = elt.hashCode();
        int start_idx = hash % data.length;
        int idx = start_idx;
        int first_deleted_idx = -1;
        while (data[idx] != null && !data[idx].equals(elt) ) {
            if (data[idx].equals(DELETED) && first_deleted_idx < 0) first_deleted_idx = idx;
            idx = (idx + 1) % data.length;
            // if (idx == start_idx) return -1;             // this should never happen
        }
        if (first_deleted_idx >= 0) {
            if (data[idx] != null) {
                data[first_deleted_idx] = data[idx];
                data[idx] = DELETED;
            }
            idx = first_deleted_idx;
        }
        return idx;
    }

    public boolean add(E elt) {
        if ((size / data.length) >= targetLoadFactor) grow();
        int idx = probe(elt);
        if (data[idx] == null || data[idx].equals(DELETED)) {
            data[idx] = elt;
            size++;
            return true;
        } else {
            return false;
        }
    }

    public boolean contains(E elt) {
        int idx = probe(elt);
        return (data[idx] != null) && !data[idx].equals(DELETED);
    }

    public boolean remove(E elt) {
        int idx = probe(elt);
        if (data[idx] == null || data[idx].equals(DELETED)) return false;
        else {
            data[idx] = DELETED;
            size--;
            return true;
        }
    }

    public static void main(String[] args) {
        HashTable<Integer> t = new HashTable<>(5);
        t.add(72);
        t.add(96);
        t.add(11);
        t.remove(96);
        t.add(11);
        t.remove(72);
        t.add(87);
    }

}