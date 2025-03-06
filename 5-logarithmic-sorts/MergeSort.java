public class MergeSort<E extends Comparable<E>> {
    public void mergeSort(E[] arr) {
        mergeSort(arr, 0, arr.length);
    }

    protected void mergeSort(E[] arr, int begin, int end) {
        // base case
        if (end - begin <= 1) return;     // an array of size 1 or 0 is trivially sorted

        // find the middle
        int middle = begin + (end - begin)/2;

        // recurse left
        mergeSort(arr, begin, middle);
        // recurse right
        mergeSort(arr, middle, end);
        
        // merge
        // create scratch space
        E[] scratch = (E[]) new Object[middle - begin];
        // copy first half of the range over into the scratch space
        for (int i = 0; i < (middle - begin); i++) {
            scratch[i] = arr[begin + i];
        }
        // two pointer algorithm for merge
        int nextFirst = 0, nextSecond = middle, nextWrite = begin;
        while (nextFirst < (middle - begin) && nextSecond < end) {
            // compare scratch[nextFirst] to arr[nextSecond]
            // write whichever is smaller into arr[nextWrite]
            // advance whichever pointer was smaller
            arr[nextWrite++] = scratch[nextFirst].compareTo(arr[nextSecond]) < 0 ? arr[nextFirst++] : arr[nextSecond++];
        }
        while (nextFirst < (middle - begin)) {
            arr[nextWrite++] = scratch[nextFirst++];
        }
    }

    public static void main(String[] args) {
        Integer [] arr = {4, 9, 8, 2, 7, 1, 6, 3};
        MergeSort<Integer> m = new MergeSort<>();
        m.mergeSort(arr);
    }
}