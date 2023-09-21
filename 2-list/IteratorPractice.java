import java.util.*;

public class IteratorPractice {
    public static void main(String[] args) {
        Collection<String> c = new ArrayList<>();
        c.add("hello");
        c.add("goodbye");
        c.add("cat");
        c.add("dog");

        Iterator<String> it = c.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}