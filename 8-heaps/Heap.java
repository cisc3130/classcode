import java.util.*;

public class Heap<E extends Comparable<E>> {
    ArrayList data;


    public Heap() {
        data = new ArrayList();
    }

    protected int leftChild(int idx) {
        int left = idx * 2 + 1;
        if (left > data.size() - 1) return -1;
        return left;
    }

    protected int rightChild(int idx) {
        int right = idx * 2 + 2;
        if (right > data.size() - 1) return -1;
        return right;
    }

    protected int parent(int idx) {
        if (idx == 0) return -1;
        int parent = (int) Math.floor((idx-1)/2.0);
        return parent;
    }

    // precondition: you have an array that is a heap
    // except for the last element
    protected void heapifyUp() {
        // start from the last element
        // compare it to its parent
        // if it's larger than its parent, swap them
        // continue until it is smaller than its parent.
        if (data.size() == 1) return;
        int idx = data.size() - 1, pidx = parent(idx);
        E elt = (E) data.get(idx), pelt = (E) data.get(pidx);
        while (elt.compareTo(pelt) > 0) {
            data.set(pidx, elt);
            data.set(idx, pelt);
            idx = pidx; pidx = parent(idx);
            // elt stays the same
            if (pidx < 0) break;
            pelt = (E) data.get(pidx);
        }

    }

    // precondition: you have an array that is a heap
    // except for the root
    protected void heapifyDown() {
        // compare the element to its two children
        // if it is smaller than either of its children,
        // swap it with that children
        // if it's smaller than both children,
        int idx = 0, lidx = leftChild(idx), ridx = rightChild(lidx);
        E elt = (E) data.get(idx),
          lelt = lidx > 0 ? (E) data.get(lidx) : null,
          relt = ridx > 0 ? (E) data.get(ridx) : null;
        boolean hasGreaterLeftChild = lelt != null && elt.compareTo(lelt) < 0,
            hasGreaterRightChild = relt != null && elt.compareTo(relt) < 0;
        while (hasGreaterLeftChild || hasGreaterRightChild) {
            System.out.println(data);

            int swapTo; E swapElt;
            if (!hasGreaterRightChild) {
                swapTo = lidx; 
                swapElt = lelt;
            } else if (!hasGreaterLeftChild) {
                swapTo = ridx;
                swapElt = relt;
            } else {
                int c = lelt.compareTo(relt);
                swapTo = c > 0 ? lidx : ridx;
                swapElt = c > 0 ? lelt : relt;
            }
            data.set(swapTo, elt);
            data.set(idx, swapElt);
            idx = swapTo;
            lidx = leftChild(idx); ridx = rightChild(idx);
            lelt = lidx > 0 ? (E) data.get(lidx) : null;
            relt = ridx > 0 ? (E) data.get(ridx) : null; 
            hasGreaterLeftChild = lelt != null && elt.compareTo(lelt) < 0;
            hasGreaterRightChild = relt != null && elt.compareTo(relt) < 0;
        }
    }

    public boolean add(E elt) {
        System.out.println("Adding " + elt);
        data.add(elt);
        System.out.println(data);
        heapifyUp();
        System.out.println(data);
        return true;
    }

    public E removeMax() {
        // swap the first and last elements
        E max = (E) data.set(0, data.get(data.size()-1));
        // remove the last element
        data.remove(data.size()-1);
        // heapifyDown
        heapifyDown();
        return max;
    }

    public String toString() {
        return data.toString();
    }

    public E maxElt() {
        return (E) data.get(0);
    }

    public static void main(String[] args) {
        Heap<Integer> intHeap = new Heap<>();
        Integer[] ints = { 10, 28, 14, 19, 7, 8, 2, 30, 11};
        for (Integer i : ints) {
            intHeap.add(i);
        }

        Integer max = intHeap.removeMax();
        
    }

}