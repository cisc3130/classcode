import java.lang.*;

public class ArrayList<E> {
    E[] data;
    int size;

    public ArrayList(int initialCapacity) {
        data = (E[]) new Object[initialCapacity];
        size = 0;
    }

    public ArrayList() {
        this(10);
    }

    private void grow() {
        E[] newdata = (E[]) new Object[data.length*2];
        for (int i = 0; i < size; i++) newdata[i] = data[i];
        data = newdata;
    }

    public boolean add(E elt) {
        if (size == data.length) grow();
        data[size] = elt;       // data[size] is the next available spot
        size++;     // size must equal the number of elements in the list
                    // we just added an element so we need to increment size
        return true;    // Collection::add requires us to return a boolean
    }

    public E removeLast() {
        E last = data[size-1];
        size--;
        return last;
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
        for (int i = idx; i < size-1; i++) {
            data[i] = data[i+1];
        }
        size--;
        return toReturn;
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

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public static void main(String[] args) {
        String[] strs = { "hello", "goodbye", "cat", "dog", "coffee", "computer"};
        ArrayList<String> lst = new ArrayList<>(4);
        lst.add("hello");
        lst.add("goodbye");
        lst.add("cat");
        lst.removeLast();
        lst.add("dog");
        lst.add("computer");
        lst.add(1, "coffee");
        lst.remove(2);
    }

}