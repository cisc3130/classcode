import java.util.*;

public class ListPractice {

    public static void collectionPractice() {
        Collection<String> c = new ArrayList<>();
        String [] strarr = {"hello", "goodbye", "coffee"};
        System.out.println(c.isEmpty());
        for (String s : strarr) {
            c.add(s);
            System.out.println(c.size());
        }
        System.out.println(c.isEmpty());

        for (String s : c) {
            System.out.println(s);
        }

    }

    public static void listPractice() {
        List<String> c = new ArrayList<>();
        String [] strarr = {"hello", "goodbye", "coffee"};
        System.out.println(c.isEmpty());
        for (String s : strarr) {
            c.add(s);
            System.out.println(c.size());
        }
        System.out.println(c.isEmpty());
        c.add(0, "desk");

        for (String s : c) {
            System.out.println(s);
        }

        c.set(1, "goodbye");

        for (String s : c) {
            System.out.println(s);
        }

        System.out.println("The element at index 2 is " + c.get(2));

        System.out.println("Removing the last element. The last element was " + c.remove(c.size()-1) 
        + " The size is now " + c.size());

        List<String> newList = new ArrayList<>(c);
    }
    public static void main(String[] args) {
        listPractice();
    }
}