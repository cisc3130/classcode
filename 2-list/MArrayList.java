import java.util.*;

public class MArrayList<E> extends AbstractList<E> {
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

    public void add(int idx, E elt) {
        if (idx < 0 || idx > size) throw new IndexOutOfBoundsException();
        if (size == data.length) grow();
        for (int i = size; i > idx; i--) {
            data[i] = data[i-1];
        }
        data[idx] = elt;
        size++;
    }

    public E remove(int idx) {
        if (idx < 0 || idx >= size) throw new IndexOutOfBoundsException();
        E toReturn = data[idx];
        for (int i = idx; i < size - 1; i++) {
            data[i] = data[i+1];
        }
        size--;
        return toReturn;
    }

    public int size() {
        return size;
    }

    public E get(int idx) {
        if (idx < 0 || idx >= size) throw new IndexOutOfBoundsException();
        return data[idx];
    }

    public E set(int idx, E elt) {
        if (idx < 0 || idx >= size) throw new IndexOutOfBoundsException();
        E toReturn = data[idx];
        data[idx] = elt;
        return toReturn;
    }

    public static void main(String[] args) {
        MArrayList<String> lst = new MArrayList<>(3);
        lst.add("hello");
        lst.add("goodbye");
        lst.add("coffee");
        lst.add(0, "desk");
        lst.add(1, "phone");
        lst.add("computer");
        lst.add("chair");
        lst.remove(lst.size()-1);
        lst.remove(1);
    }
}