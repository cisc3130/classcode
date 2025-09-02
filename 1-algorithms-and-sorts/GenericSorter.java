import java.util.*;

class Desk implements Comparable<Desk> {
    int i;

    public int compareTo(Desk other) {
        return this.i - other.i;
    }
}

public class GenericSorter<E> {

    public String[] generateStringArray() {
        String[] arr = new String[10];
        for (int i = 0; i < arr.length; i++) arr[i] = "abc";
        return arr;
    }


    public static <G> void printArr(G[] arr) {
        System.out.print("{ ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("}");
    }


    public void insertionSort(E[] arr) {
        for (int i = 1; i < arr.length; i++) {
            E toSort = arr[i];
            int j = i;
            while (j > 0 && toSort.compareTo(arr[j-1]) < 0) {
                arr[j] = arr[j-1];
                j--;
            }
            arr[j] = toSort;
        }
    }
    


    public static void main(String[] args) {
        String[] arr = { "hello", "goodbye", "cookie", "desk", "abc", "computer" };
    
        GenericSorter<String> sorter = new GenericSorter<>(); 
        printArr(arr);
        sorter.insertionSort(arr);
        printArr(arr);

        Integer[] intarr = { 3, 4, 2, 6, 8, 5, 3 };
        GenericSorter<Integer> intSorter = new GenericSorter<>();
        printArr(intarr);

        Desk[] deskarr = { new Desk(), new Desk() };
        printArr(deskarr);
        GenericSorter<Desk> deskSorter = new GenericSorter<>();

        // GenericSorter<char> basicSorter = new GenericSorter<>();

    }
}