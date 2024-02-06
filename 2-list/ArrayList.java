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
        size--;
    }

}