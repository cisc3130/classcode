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

    public static void printArr(int[] arr) {
        System.out.print("{ ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("}");
    }

    /*********** End of pre-written code ***********/


    public static void main(String[] args) {
        int[] arr = generateIntArray();
        printArr(arr);
        // bubbleSort(arr);
        printArr(arr);
    }
}