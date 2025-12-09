import java.util.*;

public class HeapSortIntArray {

    public static void sort(int[] data) {
        buildHeap(data);
        for (int i = data.length-1; i > 0; i--) {
            // swap the first (largest) and next to last element
            int nextLargest = data[0];
            data[0] = data[i];
            data[i] = nextLargest;
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
    protected static void heapifyUp(int[] data) {
        int newElt = data[data.length-1];
        int idx = data.length-1, pidx = parentIdx(idx);
        while (pidx >= 0 && data[pidx] < newElt) {        // loop while the parent is smaller than the child
            data[idx] = data[pidx];
            idx = pidx;
            pidx = parentIdx(idx);
        }
        data[idx] = newElt;
    }

    // fix a heap that is correct except for given index
    protected static void heapifyDown(int[] data, int i, int end) {
        int newElt = data[i];
        int idx = i, lidx = leftChildIdx(i), ridx = rightChildIdx(i);
        boolean hasBadLeftChild = (lidx < end) && (data[lidx] > newElt),
            hasBadRightChild = (ridx < end) && (data[ridx] > newElt);
        while (hasBadLeftChild || hasBadRightChild) {
            if (!hasBadRightChild) {
                data[idx] = data[lidx];
                idx = lidx;
            } else if (!hasBadLeftChild) {
                data[idx] = data[ridx];
                idx = ridx;
            } else {
                if (data[lidx] > data[ridx]) {
                    data[idx] = data[lidx];
                    idx = lidx;
                } else {
                    data[idx] = data[ridx];
                    idx = ridx;
                }
            }
            lidx = leftChildIdx(idx); ridx = rightChildIdx(idx);
            hasBadLeftChild = (lidx < end) && (data[lidx] > newElt);
            hasBadRightChild = (ridx < end) && (data[ridx] > newElt);
        }
        data[idx] = newElt;
    }

    // assume data contains elements in arbitrary order, make it into a heap
    // heapifyDown takes log(n) time
    // we call it n/2 times
    // so buildHeap takes n log(n) time
    protected static void buildHeap(int[] data) {
        int i = data.length/2 - 1;
        while (i >= 0) {
            heapifyDown(data, i, data.length);
            i--;
        }
    }

    public static void main(String[] args) {
        int[] data = {5,3,8,4,2,7,1,10,6,9};
        System.out.println("Before sorting: " + Arrays.toString(data));
        sort(data);
        System.out.println("After sorting: " + Arrays.toString(data));
    }

    
}
