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
        if (end - begin <= 1) return;       // base case: if there is just 1 or 0 elements the range is sorted

        int middle = begin + (int) Math.ceil((end - begin)/2.0);
        mergeSort(arr, begin, middle);
        mergeSort(arr, middle, end);

        // combine
        E[] scratchArr = Arrays.copyOfRange(arr, begin, middle);
        int toIdx = begin, leftIdx = 0, rightIdx = middle;
        while (leftIdx < scratchArr.length && rightIdx < end) {
            if (scratchArr[leftIdx].compareTo(arr[rightIdx]) <= 0) {
                arr[toIdx] = scratchArr[leftIdx];
                leftIdx++;
            } else {
                arr[toIdx] = arr[rightIdx];
                rightIdx++;
            }
            toIdx++;
        }
        while (leftIdx < scratchArr.length) {
            arr[toIdx++] = scratchArr[leftIdx++];
        }
        while (rightIdx < end) {
            arr[toIdx++] = arr[rightIdx++];
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