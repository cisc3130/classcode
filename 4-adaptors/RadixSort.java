import java.util.*;

public class RadixSort {

    List<Integer> toSort;
    ArrayList<Queue<Integer>> bins;
    int BASE = 10;
    int numDigits;

    public RadixSort(List<Integer> toSort, int numDigits) {
        this.toSort = toSort;
        bins = new ArrayList<>(BASE);
        for (int i = 0; i < BASE; i++) {
            bins.add(new LinkedList<>());
        }
        this.numDigits = numDigits;
    }

    public void distribute(int d) {
        for (Integer i : toSort) {
            // get the digit in place d
            String numStr = String.valueOf(i);
            int dlen = numStr.length();
            if (dlen <= d) {
                // if there is no such digit, treat it as 0
                bins.get(0).add(i);
                continue;
            }
            int dval = Character.getNumericValue(numStr.charAt(numStr.length() - 1 - d));
            // add i to the bin corresponding to dval
            bins.get(dval).add(i);          // bins is an ArrayList<Queue<Integer>>     
                                            // bins.get(dval) is a Queue<Integer>
                                            // i is an Integer
        }
    }

    public void collect() {
        toSort.clear();
        for (Queue<Integer> b : bins) {
            while (!b.isEmpty()) {
                toSort.add(b.poll());
            }
        }
    }

    public void sort() {
        for (int i = 0; i < numDigits; i++) {
            distribute(i);
            printBins();
            collect();
            printToSort();
        }
    }

    public void printBins() {
        for (int i = 0; i < BASE; i++) {
            System.out.print(i + ": ");
            Queue<Integer> b = bins.get(i);
            for (Integer val : b) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }

    public void printToSort() {
        for (Integer val : toSort) {
            System.out.print(val + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(170, 45, 75, 90, 802, 24, 2, 66);
        RadixSort rs = new RadixSort(new ArrayList<>(nums), 3);
        rs.sort();
    }
}