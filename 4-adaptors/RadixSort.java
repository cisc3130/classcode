import java.util.*;

public class RadixSort {

    List<Integer> toSort;
    MArrayList<Queue<Integer>> bins;
    int numDigits;

    public RadixSort(List<Integer> toSort, int numDigits) {
        this.toSort = toSort;
        this.numDigits = numDigits;
        bins = new MArrayList<>();                   // instantiate the bins data structure
        for (int i = 0; i < 10; i++) {
            bins.add(new CLinkedList<Integer>());    // instantiate an empty queue (implemented as a LL) for each bin
        }
    }

    public void distribute(int d) {
        for (Integer i : toSort) {
            // get the digit in place d
            String numStr = String.valueOf(i);
            int dval = Character.getNumericValue(numStr.charAt(numStr.length() - 1 - d));
            // add i to the bin corresponding to dval
            bins.get(dval).add(i);          // bins is an ArrayList<Queue<Integer>>     
                                            // bins.get(dval) is a Queue<Integer>
                                            // i is an Integer
        }
    }

    public void collect() {
        ListIterator<Integer> it = toSort.listIterator();
        for (Queue<Integer> b : bins) {
            while (!b.isEmpty()) {
                it.next();
                it.set(b.poll());
            }
        }
    }

    public void sort() {
        for (int i = 0; i < numDigits; i++) {
            distribute(i);
            collect();
        }
    }
}