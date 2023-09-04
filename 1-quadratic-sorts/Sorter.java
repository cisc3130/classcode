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
    }
  }

  public static void printArr(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
      System.out.print(arr[i] + " ");
    }
    System.out.println();
  }
  

  public static void main(String[] args) {
    int arr[] = { 7, 1, 8, 9, 5, 2, 6 };
    Sorter.printArr(arr);
    // Sorter.bubbleSort(arr);
    // Sorter.selectionSort(arr);
    Sorter.insertionSort(arr);
    Sorter.printArr(arr);
    // Sorter.objPrintArr(arr);

    // String strarr[] = {"hello", "goodbye", "cat", "dog"};
    // Sorter.objselectionSort(strarr);
    // Sorter.objPrintArr(strarr);

    // List list = new ArrayList();
    // list.add(7);
    // String s = (String)list.get(0);
  }
}
