import java.util.*;

public class ChainedHashSet<E> {
    List[] table;
    int size;
    int targetLoadFactor;

    public HashSet() {
        table = new List[11]; 
        size = 0;
        targetLoadFactor = 0.75;
    }

    protected int index(E elt) {
        int hash = elt.hashCode();
        int idx = hash % table.length;
        System.out.println(String.format("%s hashes to value %d, which is modded with table size %d to get index %d", 
            elt, hash, table.length, idx));
        return idx;
    }

    protected void grow() {
        List[] newTable = new List[table.length * 2];
    }

    public boolean add(E elt) {
        int idx = index(elt);
        // check if it's already in the table
        if (table[idx] == null) table[idx] = new LinkedList<E>();
        else if (table[idx].contains(elt)) return false;     // remember this takes linear time!
        if (size / table.length >= targetLoadFactor) grow();
        table[idx].add(elt);
        size++;
        return true;
    }

    public boolean contains(E elt) {
        int idx = index(elt);
        if (table[idx] == null) return false;
        return table[idx].contains(elt);
    }

    public boolean remove(E elt) {
        int idx = index(elt);
        if (table[idx] == null) return false;
        boolean removed = table[idx].remove(elt);
        if (removed) size--;
        return removed;
    }
}