class GenericSorter<T extends Comparable<T>> {
  private T[] arr;

  public GenericSorter(T[] a) {
    this.arr = a;
  }

  public void bubbleSort() {
    boolean swapped;
    for (int i = 0; i < arr.length; i++) {
      swapped = false;
      for (int j = 0; j < arr.length-i-1; j++) {
        if (arr[j].compareTo(arr[j+1]) > 0) {
          T tmp = arr[j];
          arr[j] = arr[j+1];
          arr[j+1] = tmp;
          swapped = true;
        }
      }
      if (!swapped) break;
    } // at the end of the ith iteration, the 
      // last i elements are sorted
  }

  public void printArr() {
    for (T e : arr) {
      System.out.print(e + ", ");
    }
    System.out.println();
  }

  public static <V extends Comparable<V>> void insertionSort(V arr[]) {
    for (int i = 1; i < arr.length; i++) {
      int k = i - 1;
      V curr = arr[i];
      while (k >= 0 && curr.compareTo(arr[k]) > 0) {
        arr[k+1] = arr[k];
        k--;
      }
      arr[k+1] = curr;
    }   // arr[0..i] is sorted
  }

  public static void main(String[] args) {
    Integer arr[] = {3, 2, 22, 7, 6, 99, 33, 56, 0 };
    String strarr[] = "hello goodbye banana cat dog zoo".split(" ");

    GenericSorter<Integer> intgs = new GenericSorter<>(arr);
    GenericSorter<String> strgs = new GenericSorter<>(strarr);
    GenericSorter rawgs = new GenericSorter(arr);

    intgs.bubbleSort();
    intgs.printArr();

    strgs.bubbleSort();
    strgs.printArr();


    // GenericSorter.insertionSort<String>(strarr);
  }
}
