import java.util.*;

public class RadixSort {

    static int NDIGITS = 3;
    static int BASE = 10;

    public static void radixSort(List<Integer> lst) {
        // find the number of digits
        // specified as NDIGITS

        // create bins
        ArrayList<Queue<Integer>> bins = new ArrayList<Queue<Integer>>(BASE);
        for (int i = 0; i < BASE; i++) bins.add(new LinkedList<Integer>());

        for (int d = 0; d < NDIGITS; d++) {
            ListIterator<Integer> it = lst.listIterator();
            // distribute
            while (it.hasNext()) {
                int val = it.next();
                // find the digit in the dth place
                String valString = String.valueOf(val);
                int dval = Character.digit(valString.charAt(valString.length()-d), BASE);
                // put the number in the bin corresponding to that digit
                bins.get(dval).add(val);
            }
            // collect
            it = lst.listIterator();
            for (int i = 0; i < BASE; i++) {
                while (!bins.get(i).isEmpty()) {
                    Integer val = bins.get(i).remove();
                    it.next();
                    it.set(val);
                }
            } // lst is now sorted by the last d+1 digits
        }

    }


    public static void main(String[] args) {
        List<Integer> intlist = new LinkedList<>();
        for (int i = 0; i < 20; i++) {
            intlist.add((int) Math.floor(Math.random() * Math.pow(BASE, NDIGITS)));
        }
        radixSort(intlist);
        System.out.println(intlist);
    }
}