import java.util.*;

public class HeapSort<E extends Comparable<E>> {

    public static void sort(ArrayList<E> data) {
        buildHeap(data);
        for (int i = data.size()-1; i > 0; i--) {
            // swap the first (largest) and next to last element
            E nextLargest = data.get(0);
            data.set(0, data.get(i));
            data.set(i, nextLargest);
            heapifyDown(data, 0, i);
        }
    }

    protected static int leftChildIdx(int idx) { return 2 * idx + 1; }
    protected static int rightChildIdx(int idx) { return 2 * idx + 2; }
    protected static int parentIdx(int idx) { 
        if (idx == 0) return -1;
        else return (int) Math.floor((idx - 1)/2.0); 
    }
  
    // fix a heap that is correct except for the last element
    protected static void heapifyUp(ArrayList<E> data) {
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
    protected static void heapifyDown(ArrayList<E> data, int i, int end) {
        E newElt = data.get(i);
        int idx = i, lidx = leftIndex(i), ridx = rightIndex(i);
        boolean hasBadLeftChild = (lidx < end) && (data.get(lidx).compareTo(newElt) > 0),
            hasBadRightChild = (ridx < end) && (data.get(ridx).compareTo(newElt) > 0);
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
            hasBadLeftChild = (lidx < end) && (data.get(lidx).compareTo(newElt) > 0),
            hasBadRightChild = (ridx < end) && (data.get(ridx).compareTo(newElt) > 0);
        }
        data.set(idx, newElt);
    }

    // assume data contains elements in arbitrary order, make it into a heap
    // heapifyDown takes log(n) time
    // we call it n/2 times
    // so buildHeap takes n log(n) time
    protected static void buildHeap(ArrayList<E> data) {
        int i = data.size()/2;
        while (i >= 0) {
            heapifyDown(data, i, data.size());
        }
    }

    
}
