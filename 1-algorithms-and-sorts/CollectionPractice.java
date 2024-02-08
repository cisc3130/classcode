import java.util.*;

public class CollectionPractice {

    public static <E> void addElementsFromArray(Collection<E> c, E[] elts) {
        for (int i = 0; i < elts.length; i++) {
            c.add(elts[i]);
        }
    }

    public static <E> void printCollection(Collection<E> c) {
        for (E elt : c) {
            System.out.print(elt + " ");
        }
        System.out.println();
    }


    public static void main(String[] args) {
        String[] strarr = {"hello", "goodbye", "cat", "dog", "computer", "desk"};
        List<String> lst = new ArrayList<>();
        addElementsFromArray(lst, strarr);
        Set<String> set = new TreeSet<>(lst);
        Deque<String> dq = new ArrayDeque<>();
        dq.addAll(set);
        printCollection(lst);
        printCollection(set);
        printCollection(dq);
    }
}