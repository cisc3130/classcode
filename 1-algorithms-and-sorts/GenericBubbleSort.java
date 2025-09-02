public class GenericBubbleSort<T extends Comparable<T>> {

    T[] arr;
    int defaultSortNumber;

    public static <G> void printArr(G[] arr) {
        for (int i = 0; i < arr.length; i++) {
            G x = arr[i];
            System.out.print(x + " ");
        }
    }

    public void setArr(T[] arr) {
        this.arr = arr;
    }

    public void bubbleSort() {
        boolean swapped;
        for (int i = 0; i < arr.length; i++) {
            swapped = false;
            for (int k = 0; k < arr.length-i-1; k++) {
                if (arr[k].compareTo(arr[k+1]) > 0) {    // if the two elements are out of order
                    T temp = arr[k];
                    arr[k] = arr[k+1];
                    arr[k+1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }


    public static void main(String[] args) {
        String[] strArr = {"hello", "goodbye", "computer", "desk", "water"};

        printArr(strArr);

        GenericBubbleSort<String> stringSorter = new GenericBubbleSort<>();

    }
}