import java.lang.*;
import java.util.Arrays;

public class ArrayList<E> {
    Object[] data;
    int size;

    public ArrayList() {
        this(10);
    }

    public ArrayList(int initialCapacity) {
        data = new Object[initialCapacity];
        size = 0;
    }

    protected void grow() {
        Object[] newdata = Arrays.copyOf(data, data.length*2);
        data = newdata;
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.print(data[i] + " ");
        }
        System.out.println();
    }

    public boolean add(E elt) {
        if (size == data.length) grow();
        data[size] = elt;
        size++;
        return true;
    }

    public void add(int index, E elt) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();
        if (size == data.length) grow();
        for (int i = size; i > index; i--)  {
            data[i] = data[i-1];
        }
        data[index] = elt;
        size++;
    }

    public void remove() {
        data[size-1] = null;
        size--;             // this is the important part!
    }

    public E remove(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        E toRemove = (E)data[index];
        for (int i = index; i < size-1; i++) {
            data[i] = data[i+1];
        }
        size--;
        return toRemove;
    }

    public E get(int index) {
        return (E) data[index];
    }

    public E set(int index, E newValue) {
        E oldValue = (E) data[index];
        data[index] = newValue;
        return oldValue;
    }

    public static void main(String[] args) {
        ArrayList<String> strlist = new ArrayList<>();

    }
}