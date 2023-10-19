import java.lang.Math;

public class Sorter {

    public static <E extends Comparable<E>> void mergeSort(E[] arr) {
        mergeSort(arr, 0, arr.length);
    }

    // when this instance of mergeSort returns,
    // arr[start, end) will be sorted
    public static <E extends Comparable<E>> void mergeSort(E[] arr, int start, int end) {
        System.out.println(String.format("mergeSort [%d, %d))", start, end));
        printArr(arr, start, end);
        // base case
        if (end - start <= 1) {
            System.out.println("Base case");
            return;     // array of size 0 or 1 is trivially sorted
        } 

        // recurse on both halves of the range
        int middle = (int) Math.floor(start + (end - start)/2.0);
        mergeSort(arr, start, middle); // the first half of the range is now sorted
        mergeSort(arr, middle, end);   // the second half of the range is now sorted
        
        // combine the two sorted lists
        int nextFirst = 0, nextSecond = middle, nextWrite = start;
        // copy the first half into scratch space (not inplace!)
        Object[] scratch = new Object[middle - start];
        for (int i = 0; i < (middle - start); i++) scratch[i] = arr[start + i];
        // combine: repeatedly compare the elements at nextFirst and nextSecond
        // write the smaller one into nextWrite and advance nextWrite
        // advance the index of the smaller element (nextFirst or nextSecond)
        while (nextFirst < (middle - start) && nextSecond < end) {
            E nextFirstVal = (E) scratch[nextFirst];
            if (nextFirstVal.compareTo(arr[nextSecond]) <= 0) {
                arr[nextWrite] = nextFirstVal;
                nextFirst++;
            } else {
                arr[nextWrite] = arr[nextSecond];
                nextSecond++;
            }
            nextWrite++;
        }
        while (nextFirst < (middle - start)) arr[nextWrite++] = (E) scratch[nextFirst++];
        while (nextSecond < end) arr[nextWrite++] = arr[nextSecond++];

        // arr[start, end) is now sorted
        printArr(arr, start, end);
    } // end recursive  mergeSort

    public static <E> void printArr(E[] arr) {
        for (E elt : arr) System.out.print(elt + ", ");
        System.out.println();
    }

    public static <E> void printArr(E[] arr, int start, int end) {
        for (int i = start; i < end; i++) {
            System.out.print(arr[i] + ", ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Integer[] arr = { 7, 1, 8, 9, 5, 0, 2, 3 };
        mergeSort(arr);
        printArr(arr);
    }

} // end Sorter