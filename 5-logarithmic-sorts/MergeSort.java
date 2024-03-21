import java.util.*;

public class MergeSort {




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

    public static <E> String arrToStr(E[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i].toString());
            sb.append(", ");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Integer[] arr = { 2, 5, 7, 1, 6, 3, 4, 8 };
        mergeSort(arr, 0, 8);
        for (int i = 0; i < arr.length; i++) System.out.print(arr[i] + " ");
        System.out.println();
    }
}