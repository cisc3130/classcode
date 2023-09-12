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
        newdata[size] = elt;
        size++;
        return true;
    }
}