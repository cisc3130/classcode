import java.util.*;

public class MArrayList<E> {
    E [] data;
    int size;

    public MArrayList() {
        this(10);   
    }

    public MArrayList(int capacity) {
        data = (E[]) new Object[capacity];
        size = 0;
    }

    protected void grow() {
        data = Arrays.copyOf(data, data.length*2);
    }

    public boolean add(E elt) {
        if (size == data.length) grow();
        data[size] = elt;
        size++;
        return true;
    }

    public static void main(String[] args) {
        MArrayList<String> lst = new MArrayList<>(3);
        lst.add("hello");
        lst.add("goodbye");
        lst.add("coffee");
        lst.add("desk");
        lst.add("phone");
        lst.add("computer");
        lst.add("chair");
    }
}