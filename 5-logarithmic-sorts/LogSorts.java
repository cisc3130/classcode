import java.util.*;

public class LogSorts {

    public static <E extends Comparable<E>> void quickSort(E[] arr, int begin, int end) {
        // base case: only 1 or 0 elements: subarray is trivially sorted
        if (begin - end <= 1) return;

        // choose pivot
        int pivotIdx = choosePivot(arr, begin, end);
        E pivot = arr[pivotIdx];

        // partitioning step
        // move pivot to end
        E tmp = arr[end-1];
        arr[end-1] = pivot;
        arr[pivotIdx] = tmp;

        int writeIdx = begin, readIdx = begin;
        while (readIdx != end-1) {
            if (arr[readIdx].compareTo(pivot) < 0)  {  // the value we just read is smaller than the pivot  
                // swap it into arr[writeIdx]
                tmp = arr[writeIdx];
                arr[writeIdx] = arr[readIdx];
                arr[readIdx] = tmp;
                // move writeIdx forward - this index is no longer available to write to
                writeIdx++;
            }
            readIdx++;
        }
        // swap the pivot into writeIdx. Everything to the left of writeIdx is smaller than the pivot
        // and everything to the right of it is larger, so this is the correct spot for the pivot
        arr[end-1] = arr[writeIdx];
        arr[writeIdx] = pivot;

        // recurse on both sides of the pivot
        quickSort(arr, begin, writeIdx);
        quickSort(arr, writeIdx+1, end);
    }

    public static <E extends Comparable<E>> int choosePivot(E[] arr, int begin, int end) {
        return end-1;
    }


    public static <E extends Comparable<E>> void mergeSort(E[] arr, int begin, int end) {
        // base case: only 1 or 0 elements: subarray is trivially sorted
        if (begin - end <= 1) {
            return;     
        }

        // recurse on the two halves of the subarray
        int middle = (end - begin) / 2 + begin;
        mergeSort(arr, begin, middle);
        mergeSort(arr, middle, end);

        // the two halves of the subarray are now sorted
        // now we can combine in linear time
        E[] scratch = Arrays.copyOfRange(arr, begin, middle);    // copy the left half into scratch space
        int writeIdx = begin, leftIdx = 0, rightIdx = middle,
            leftEnd = scratch.length, rightEnd = end;
        while (leftIdx < leftEnd && rightIdx < rightEnd) {
            if (scratch[leftIdx].compareTo(arr[rightIdx]) <= 0) {   // the element in the left half is smaller or equal to the element in the right half
                arr[writeIdx] = scratch[leftIdx];
                leftIdx++;
            } else {
                arr[writeIdx] = arr[rightIdx];
                rightIdx++;
            }
            writeIdx++;
        }
        // cleanup after one of the idxs reaches the end. Only one of these while loops will run
        while (leftIdx < leftEnd) {
            arr[writeIdx++] = scratch[leftIdx++];
        }
        while (rightIdx < rightEnd) {        // this while loop isn't actually necessary
            arr[writeIdx++] = arr[rightIdx++];  // the rightmost elements are already in the right place
        }

    }

    public static <E> String arrToStr(E[] arr, int begin, int end) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i].toString());
            sb.append(", ");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Integer[] arr = { 2, 5, 7, 1, 6, 3, 8, 4 };
        quickSort(arr, 0, 8);
        for (int i = 0; i < arr.length; i++) System.out.print(arr[i] + " ");
        System.out.println();
    }
}