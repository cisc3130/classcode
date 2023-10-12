import java.util.*;

public class MidtermPractice {

    public static void aggregatePractice() {
        Collection<Integer> c = new LinkedList<>();
        c.stream();

        int[] arr = {3, 4, 1, 2, 5, 3, 2};
        Arrays.stream(arr);
    }

    public static <T> void genericPractice(T param) {
        // Why are generics better than objects and casting?
        List lst = new LinkedList();        // list is a raw type - not generic. Everything in it is an object
        lst.add("Hello");
        Integer i = (Integer) lst.get(0);   // This compiles but throws an exception at runtime "String cannot be cast to Integer"
        // Generics provide compile time checking that all types match up.

        // What can't be done with a generic type?
        T x = new T();                         // can't use generic type with new
                                                // "required : class. found: type parameter T"
        T[] genericArray = new T[10];       // error: generic array creation
    }

    public static void main(String[] args) {
    }
    

}