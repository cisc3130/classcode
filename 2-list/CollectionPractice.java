import java.util.*;

public class CollectionPractice {

    // this method is both GENERIC and POLYMORPHIC
    public static <E> void printCollection(Collection<E> c) {
        for (E elt : c) {
            System.out.println(elt + " ");
        }
        System.out.println();
    }



    public static void main(String[] args) {
        Collection<String> c = new ArrayList<>();
        c.add("hello");
        c.add("goodbye");
        c.add("hello");
        c.add("cat");
        printCollection(c);

        Collection<String> cset = new TreeSet<>();
        cset.add("hello");
        cset.add("goodbye");
        cset.add("hello");
        cset.add("cat");
        printCollection(cset);

        Collection<String> chashset = new HashSet<>(c);
        printCollection(chashset);
    }
}