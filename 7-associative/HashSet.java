public class HashSet<E> {
    Object[] table;
    int size;

    public HashSet() {
        table = new Object[11]; 
        size = 0;
    }

    protected int index(E elt) {
        int hash = elt.hashCode();
        int idx = hash % table.length;
        return idx;
    }

    protected int probe(int index, E elt) {
        while (table[index] != null) {
            if (table[index].equals(elt)) return index;
            index = (index + 1) % table.length;
        }
        return index;
    }

    public boolean add(E elt) {
        int idx = index(elt);

    }

    public static void main(String[] args) {
        String test1 = "hello this is data structures",
            test2 = "hello goodbye coffee dog cat data structures",
            test3 = "hello this is data structure";
        System.out.println(test1.hashCode());
        System.out.println(test2.hashCode());
        System.out.println(test3.hashCode());

        HashSet<String> hs = new HashSet<>();
        System.out.println(String.format("%d, %d, %d", hs.index(test1), hs.index(test2), hs.index(test3)));
    }


}