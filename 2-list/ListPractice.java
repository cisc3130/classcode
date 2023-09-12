import java.util.*;

public class ListPractice {

    public static <E> void printCollection(Collection<E> c) {
        for (E elt : c) {
            System.out.print(elt + " ");
        }
        System.out.println();
    }
    
    public static <E> int indexOf(List<E> lst, E target) {
        for (int i = 0; i < lst.size(); i++) {
            if (lst.get(i).equals(target)) return i;
        }
        return -1;
    }


    public static void main(String[] args) {
        List<String> lst = new ArrayList<>(); 
        lst.add("hello");
        lst.add("goodbye");
        lst.add("cat");
        lst.add("dog");
        printCollection(lst);

        lst.add(2, "third");
        printCollection(lst);

        for (int i = 0; i < lst.size(); i++) {
            System.out.print(lst.get(i) + " ");
        }   // this can be VERY inefficient depending on the list implementation
        System.out.println();

        System.out.println(String.format("The second element is %s", lst.get(1)));

        lst.remove(0);
        printCollection(lst);

        while (!lst.isEmpty()) {
            lst.remove(0);
            printCollection(lst);
        }
    }
}