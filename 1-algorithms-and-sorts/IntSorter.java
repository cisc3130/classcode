import java.util.*;

public class IntSorter {

    /*********** Start of pre-written code ***********/

    public static int[] generateIntArray(int n) {
        // ints should be between 1 and 100
        int[] arr = new int[n];
        Random r = new Random();
        for (int i = 0; i < n; i++) {
            arr[i] = r.nextInt(100);
        }
        return arr;
    }

    public static int[] generateIntArray() { return generateIntArray(8); }

    public static void printArr(Object[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static void printArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    /*********** End of pre-written code ***********/

    public static void bubbleSort(int[] arr) {
        boolean swapped;
        for (int i = 0; i < arr.length; i++) {
            swapped = false;
            for (int k = 0; k < arr.length-i-1; k++) {
                if (arr[k] > arr[k+1]) {    // if the two elements are out of order
                    int temp = arr[k];
                    arr[k] = arr[k+1];
                    arr[k+1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    /*
     we need to sort n elements
     on the first pass, we need to look at all n elements 
     (make n-1 comparisons)
     on the second pass, one element is sorted, so we need to look at n-1 elements
     on the third pass, two elements are sorted, so we need to look at n-2 elements

     n*n = n^2
     (n-1) + (n-2) + (n-3) + ... + 1 + 0
     the sum of an arithmetic series is equal to n(the first term - last term) / 2
     n(n-1)/2 = (n^2 - n)/2 = n^2/2 - n/2
     when there are two terms to an equation, we don't care about the smaller term
     n^2/2
     we don't care about constant multipliers (= divisors)
     n^2
     */

     /*
      what if the array is sorted?
      1 2 3 4 5

      what if there are ties in the array?
      27a 40 12 27b 63
      27a 12 27b 40 63
      12 27a 27b 40 63

      stable = when there's a tie, leave the two equivalent elements in the same order
      */


    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int minIdx = i;
            for (int j = minIdx + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            if (minIdx != i) {
                int temp = arr[minIdx];
                arr[minIdx] = arr[i];
                arr[i] = temp;
            }
        }
    }

    /*
     is selection sort stable?
     27a 27b 5 2 43
     2 27b 5 27a 43
     2 5 27b 27a 43 
     */

    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int j = i;
            int toSort = arr[j];
            while (j > 0 && toSort < arr[j-1]) {
                arr[j] = arr[j-1];
                j--;
            }
            // either j == 0 or arr[j-1] <= toSort
            arr[j] = toSort;
        } // after i iterations, the first i elements are sorted relative to each other
          // (they may still be moved down)
    }


    public static void main(String[] args) {
        int[] arr = generateIntArray();
        printArr(arr);
        // bubbleSort(arr);
        selectionSort(arr);
        // insertionSort(arr);
        printArr(arr);

        // Object[] objArr = {17, 22, "hello", "goodbye", 85};
        // bubbleSort(objArr);
    }
}
