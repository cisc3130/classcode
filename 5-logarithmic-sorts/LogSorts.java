import java.util.*;

public class LogSorts {

    public static <E extends Comparable<E>> void quickSort(E[] arr, int begin, int end) {
        if (end - begin <= 1) return;       // base case: if there is just 1 or 0 elements the range is sorted
        if (end - begin == 2) {
            if (arr[begin].compareTo(arr[begin+1]) > 0) {
                E tmp = arr[begin];
                arr[begin] = arr[begin+1];
                arr[begin+1] = tmp;
            }
            return;
        }       // optional base case to prevent a lot of unnecessary recursion when the range size gets down to 2

        int pivotIdx = choosePivot(arr, begin, end);
        int nextAvailableIdx = begin, nextCompareIdx = begin;
        
        // swap pivot to the last element
        E gtmp = arr[pivotIdx];
        arr[pivotIdx] = arr[end-1];
        arr[end-1] = gtmp;
        pivotIdx = end-1;

        while (nextCompareIdx < pivotIdx) {
            if (arr[nextCompareIdx].compareTo(arr[pivotIdx]) < 0) {     
                // the element we're up to is smaller than the pivot
                // swap it into the next available spot
                E tmp = arr[nextAvailableIdx];
                arr[nextAvailableIdx] = arr[nextCompareIdx];
                arr[nextCompareIdx] = tmp;
                nextAvailableIdx++;
            } 
            nextCompareIdx++;
        }

        // swap pivot to nextAvailableIdx
        gtmp = arr[pivotIdx];
        arr[pivotIdx] = arr[nextAvailableIdx];
        arr[nextAvailableIdx] = gtmp;
        pivotIdx = nextAvailableIdx;

        quickSort(arr, begin, pivotIdx);
        quickSort(arr, pivotIdx+1, end);
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
        mergeSort(arr, 0, 8);
        for (int i = 0; i < arr.length; i++) System.out.print(arr[i] + " ");
        System.out.println();
    }
}