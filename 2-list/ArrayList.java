import java.lang.*;

public class ArrayList<E> {
    Object[] data;
    int size;

    public ArrayList() {
        ArrayList(10);
    }

    public ArrayList(int initialCapacity) {
        data = new Object[initialCapacity];
        size = 0;
    }

    protected void grow() {
        Object[] newdata = Arrays.copyOf(data, data.length*2);
        data = newdata;
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
}