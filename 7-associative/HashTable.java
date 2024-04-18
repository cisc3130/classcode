import java.util.*;

public class HashTable<E> {
    ArrayList<E> data;

    public HashTable() {
        data = new ArrayList<>(10);
    }

    public boolean add(E elt) {
        int hash = elt.hashCode();
        int idx = hash % data.size();
        data.add(idx, elt);
        return true;
    }

}