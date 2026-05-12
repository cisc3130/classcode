import java.util.*;

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
        E firstElt = data.set(0, data.get(data.size()-1));
        data.set(data.size()-1, firstElt);

        // remove the last element
        data.remove(data.size()-1);

        heapifyDown(0);

        return firstElt;
    }

    // fix a heap that is correct except for the last element
    protected void heapifyUp() {
       int newIdx = data.size()-1;
       E newElt = data.get(newIdx);
       // loop as long as it has a parent that is smaller than it
       while (newIdx > 0 && newElt.compareTo(data.get(parentIdx(newIdx))) > 0) {
            // move the parent down
            data.set(newIdx, data.get(parentIdx(newIdx)));
            // move newIdx up
            newIdx = parentIdx(newIdx); 
       }
       data.set(newIdx, newElt);
    }

    // fix a heap that is correct except for given index
    protected void heapifyDown(int i) {
       if (i < 0 || i >= data.size()) throw new IndexOutOfBoundsException();
       int lidx = leftChildIdx(i), ridx = rightChildIdx(i);
       E newElt = data.get(i);
       boolean hasGreaterLeftChild = (lidx < data.size()) && (data.get(lidx).compareTo(newElt) > 0),
                hasGreaterRightChild = (ridx < data.size()) && (data.get(ridx).compareTo(newElt) > 0);
       if (!(hasGreaterLeftChild || hasGreaterRightChild)) {
            return;     // heap property is satisfied
       }
       // find which child is greater
       int toIdx;
       if (ridx < data.size() && data.get(ridx).compareTo(data.get(lidx)) > 0) {    // right child exists and is greater
            toIdx = ridx;
       } else {     // right child doesn't exist or left child is larger
            toIdx = lidx;
       }
       // swap the new element into toIdx
       data.set(i, data.get(toIdx));
       data.set(toIdx, newElt);

       // recurse on the new subtree where newElt was moved
       heapifyDown(toIdx);
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

    public static void main(String[] args) {
        Heap<Integer> heap = new Heap<>();
        heap.add(5);
        heap.add(3);
        heap.add(8);
        heap.add(1);
        heap.add(4);
        heap.add(10);
        heap.add(7);
        System.out.println(heap.peek()); // should print 8
        System.out.println(heap.poll()); // should remove and return 8
        System.out.println(heap.peek()); // should print 5
    }

    
}
