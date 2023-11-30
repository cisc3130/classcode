public class HashSet<E> {
    Object[] table;
    int size;
    final Object DELETED;

    public HashSet() {
        table = new Object[11]; 
        size = 0;
        DELETED = new Object();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < table.length; i++) {
            sb.append(i + ") ");
            if (table[i] == null) sb.append("NULL");
            else if (table[i].equals(DELETED)) sb.append("DELETED");
            else sb.append(table[i]);
            sb.append("\n");
        }
        return sb.toString();
    }

    protected int index(E elt) {
        int hash = elt.hashCode();
        int idx = hash % table.length;
        System.out.println(String.format("%s hashes to value %d, which is modded with table size %d to get index %d", 
            elt, hash, table.length, idx));
        return idx;
    }

    protected boolean slotIsEmpty(int index) {
        return table[index] == null || table[index].equals(DELETED);
    }

    protected int probe(int index, E elt) {
        int firstDeletedIdx = -1;
        int i = index;
        while (table[i] != null && !table[i].equals(elt)) {
            if (table[i].equals(DELETED) && firstDeletedIdx < 0) {
                firstDeletedIdx = i;
            }
            i = (i + 1) % table.length;
            if (i == index) break;
        }

        if (firstDeletedIdx >= 0) {
            if (table[i].equals(elt)) {
                table[firstDeletedIdx] = elt;
                table[i] = DELETED;
            }
            return firstDeletedIdx;             // this is where it should go
        }

        return i;
    }

    public boolean add(E elt) {
        int idx = index(elt);   // this is where elt belongs, but the space may be taken due to a collision
        idx = probe(idx, elt);  // advance idx until you find elt or an empty spot
        if (slotIsEmpty(idx)) {
            table[idx] = elt;
            size++;
            return true;
        }
        return false;
    }

    public boolean contains(E elt) {
        int idx = index(elt);
        idx = probe(idx, elt);
        return slotIsEmpty(idx);
    }

    public boolean remove(E elt) {
        int idx = index(elt);
        idx = probe(idx, elt);
        if (!slotIsEmpty(idx)) {
            table[idx] = DELETED;
            size--;
            return true;
        }
        return false;

    }

    public static void main(String[] args) {
        String test1 = "hello this is data structures",
            test2 = "hello goodbye coffee dog cat data structures",
            test3 = "hello this is data structure";
        System.out.println(test1.hashCode());
        System.out.println(test2.hashCode());
        System.out.println(test3.hashCode());

        HashSet<String> hs = new HashSet<>();
        hs.add(test1);
        System.out.println(hs);
        hs.add(test2);
        System.out.println(hs);
        hs.add(test3);
        System.out.println(hs);
        System.out.println(hs.contains(test1));
        hs.remove(test2);
        System.out.println(hs);
        System.out.println(hs.contains(test2));
    }


}