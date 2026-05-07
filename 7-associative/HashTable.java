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
        size = 0;
    }

    private void grow() {
        int newCapacity = data.length * 2 + 1;
        E[] oldData = data;
        this.data = (E[]) new Object[newCapacity];
        for (E elt : oldData) {
            if (elt != null && !elt.equals(DELETED)) {
                int idx = probe(elt);
                this.data[idx] = elt;
            }
        }
    }

    private int probe(E elt) {
        // elt --> hashCode --> idx
        int hashCode = elt.hashCode();
        int idx = hashCode % this.data.length;
        int firstDeletedIdx = -1;
        while (data[idx] != null && !data[idx].equals(elt)) {
            if (data[idx] == DELETED && firstDeletedIdx == -1) {
                firstDeletedIdx = idx;
            }
            idx = (idx + 1) % this.data.length;
        }
        if (firstDeletedIdx != -1 && this.data[idx].equals(elt)) {
            this.data[firstDeletedIdx] = elt;
            this.data[idx] = DELETED;
        }
        if (firstDeletedIdx != -1) return firstDeletedIdx;
        return idx;
    }

    public boolean add(E elt) {
        if (size/data.length >= targetLoadFactor) grow();
        int idx = probe(elt);
        if (this.data[idx] != null && this.data[idx] != DELETED) return false;
        this.data[idx] = elt;
        this.size++;
        return true;
    }

    public boolean contains(E elt) {
        int idx = probe(elt);
        return this.data[idx] != null && this.data[idx] != DELETED;
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