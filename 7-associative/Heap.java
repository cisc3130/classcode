public class Heap<E extends Comparable<E>> {
    ArrayList<E> data;


    public Heap() {
        data = new ArrayList<>();
    }

    protected int leftChildIdx(int idx) { return 2 * idx + 1; }
    protected int rightChildIdx(int idx) { return 2 * idx + 2; }
    protected int parentIdx(int idx) { (int) Math.floor((idx - 1)/2.0); }

    public boolean checkHeap(int i) {
        if (i < 0 || i >= data.size()) throw new IllegalArgumentException();
        int lidx = leftChildIdx(i), ridx = rightChildIdx(i);
        if (data.get(i).compareTo(data.get(lidx)) < 0 || data.get(i).compareTo(data.get(ridx)) < 0) return false;
        boolean left = (lidx >= data.size()) || checkHeap(lidx);
        boolean right = (ridx >= data.size()) || checkHeap(ridx);
        return left && right;
    }
}
