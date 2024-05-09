public class Heap<E extends Comparable<E>> {
    ArrayList<E> data;


    public Heap() {
        data = new ArrayList<>();
    }

    protected int leftChildIdx(int idx) { return 2 * idx + 1; }
    protected int rightChildIdx(int idx) { return 2 * idx + 2; }
    protected int parentIdx(int idx) { 
        if (idx == 0) return -1;
        else return (int) Math.floor((idx - 1)/2.0); 
    }

    public boolean checkHeap(int i) {
        if (i < 0 || i >= data.size()) throw new IllegalArgumentException();
        int lidx = leftChildIdx(i), ridx = rightChildIdx(i);
        if (data.get(i).compareTo(data.get(lidx)) < 0 || data.get(i).compareTo(data.get(ridx)) < 0) return false;    // error: children might not exist (see checks below)
        boolean left = (lidx >= data.size()) || checkHeap(lidx);
        boolean right = (ridx >= data.size()) || checkHeap(ridx);
        return left && right;
    }

    public boolean add(E elt) {
        data.add(elt);
        heapifyUp();
        return true;
    }

    public E peek() {
        return data.get(0);
    }

    public E poll() {
        // swap first and last element
        E firstElt = data.get(0);
        data.set(0, data.get(data.size()-1);
        data.set(data.size()-1, firstElt);

        // remove the last element
        data.remove(data.size()-1);

        heapifyDown(0);
    }

    // fix a heap that is correct except for the last element
    protected void heapifyUp() {
        E newElt = data.get(data.size()-1);
        int idx = data.size()-1, pidx = parentIdx(idx);
        while (pidx >= 0 && data.get(pidx).compareTo(newElt) < 0) {        // loop while the parent is smaller than the child
            data.set(idx, data.get(pidx));
            idx = pidx;
            pidx = parentIndex(idx);
        }
        data.set(idx, newElt);
    }

    // fix a heap that is correct except for given index
    protected void heapifyDown(int i) {
        E newElt = data.get(i);
        int idx = i, lidx = leftIndex(i), ridx = rightIndex(i);
        boolean hasBadLeftChild = (lidx < data.size()) && (data.get(lidx).compareTo(newElt) > 0),
            hasBadRightChild = (ridx < data.size()) && (data.get(ridx).compareTo(newElt) > 0);
        while (hasBadLeftChild || hasBadRightChild) {
            if (!hasBadRightChild) {
                data.set(idx, data.get(lidx));
                idx = lidx;
            } else if (!hasBadLeftChild) {
                data.set(idx, data.get(ridx));
                idx = ridx;
            } else {
                if (data.get(lidx).compareTo(data.get(ridx)) > 0) {
                    data.set(idx, data.get(lidx);
                    idx = lidx;
                } else {
                    data.set(idx, data.get(ridx);
                    idx = ridx;
                }
            }
            lidx = leftIndex(idx), ridx = rightIndex(idx);
            hasBadLeftChild = (lidx < data.size()) && (data.get(lidx).compareTo(newElt) > 0),
            hasBadRightChild = (ridx < data.size()) && (data.get(ridx).compareTo(newElt) > 0);
        }
        data.set(idx, newElt);
    }

    // assume data contains elements in arbitrary order, make it into a heap
    // heapifyDown takes log(n) time
    // we call it n/2 times
    // so buildHeap takes n log(n) time
    protected void buildHeap() {
        int i = data.size()/2;
        while (i >= 0) {
            heapifyDown(i);
        }
    }

    
}
