import java.util.*;

class Sorter {

  public static void bubbleSort(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
      boolean swapped = false;
      for (int j = 0; j < arr.length-i-1; j++) {
        if (arr[j] > arr[j+1]) {    // if the two elements are out of order, swap them
          int tmp = arr[j];
          arr[j] = arr[j+1];
          arr[j+1] = tmp;
          swapped = true;
        }
      }  // one element has bubbled to the end   
      if (!swapped) break;
    }  // after iteration k, the k last elements are sorted.
  }

  public static <E extends Comparable<E>> void genericBubbleSort(E[] arr) {
    for (int i = 0; i < arr.length; i++) {
      boolean swapped = false;
      for (int j = 0; j < arr.length-i-1; j++) {
        if (arr[j].compareTo(arr[j+1]) > 0) {    // if the two elements are out of order, swap them
          E tmp = arr[j];
          arr[j] = arr[j+1];
          arr[j+1] = tmp;
          swapped = true;
        }
      }  // one element has bubbled to the end   
      if (!swapped) break;
    }  // after iteration k, the k last elements are sorted.
  }

  public static void selectionSort(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
      int min = arr[i];
      int minIdx = i;
      for (int j = i+1; j < arr.length; j++) {
        if (arr[j] < min) {
          min = arr[j];
          minIdx = j;
        }
      }  // min now holds the minimum element in this range
      // swap the minimum element 
      int tmp = arr[i];
      arr[i] = arr[minIdx];
      arr[minIdx] = tmp;
    } // after each iteration, the next smallest element is sorted
  }

  public static void insertionSort(int[] arr) {
    for (int i = 1; i < arr.length; i++) {
      int j = i;  // declaring it outside the loop so it will still exist when the loop completes
      int toSort = arr[j];
      for (; j > 0; j--) {
        if (toSort < arr[j-1]) {
          arr[j] = arr[j-1];
        } else {
          break;
        }
      }
      arr[j] = toSort;
    } // after each iteration, the next element is sorted (though it may not end up remaining where it is)
  }

  public static <E> void printArr(E[] arr) {
    for (E elt : arr) {
      System.out.print(elt + " ");
    }
    System.out.println();
  }

  public static <T extends Comparable<T>, U, V, W, X, Y, Z> T findMin(T[] arr) {
    T min = arr[0];
    for (int i = 1; i < arr.length; i++) {
      if (arr[i].compareTo(min) < 0) {
        min = arr[i];
      }
    }
    return min;
  }

  public static void stringPrintArr(String[] arr) {
    for (String str : arr) {
      System.out.print(str + " ");
    }
    System.out.println();
  }

  public static void objectPrintArr(Object[] arr) {
    for (Object obj : arr) {
      System.out.print(obj + " ");
    }
    System.out.println();
  }
  

  public static void main(String[] args) {
    Integer arr[] = { 7, 1, 8, 9, 5, 2, 6 };
    Sorter.printArr(arr);
    // Sorter.bubbleSort(arr);
    // Sorter.selectionSort(arr);
    // Sorter.insertionSort(arr);
    Sorter.genericBubbleSort(arr);
    Sorter.printArr(arr);
    // Sorter.objPrintArr(arr);

    Double flarr[]  = {0.25, 3.1415, 2.7901};
    Sorter.genericBubbleSort(flarr);
    Sorter.printArr(flarr);

    String strarr[] = {"hello", "goodbye", "cat", "dog"};
    Sorter.printArr(strarr);
    Sorter.genericBubbleSort(strarr);
    // Sorter.objectBubbleSort(strarr);
    // Sorter.printArr(strarr);
    // Sorter.objselectionSort(strarr);
    // Sorter.objPrintArr(strarr);

    // List<Integer> list = new ArrayList<>();
    // list.add(7);
    // String s = (String)list.get(0);

    System.out.println(findMin(arr));
    System.out.println(findMin(flarr));
    System.out.println(findMin(strarr));
  }
}
