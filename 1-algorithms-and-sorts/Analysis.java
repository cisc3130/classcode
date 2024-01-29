public class Analysis {

    // print every element in an array
    public void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++){
            for (int j = 0; j < 3; j++) {
                System.out.println(arr[i] + " ");
            }
        }
        System.out.println();
    }



    // sum up all elements in an array
    
    // linear algorithm ; see hw1.md (a)
    // Linear search 
    public static int linearAlgorithm(int[] arr, int num){
        for (int i = 0; i < arr.length; i++){
            if (arr[i] == num){
                return i;
            }
        }
        return -1;
    }

    // quadratic algorithm ; see hw1.md (b)
    // Sum all elements then multiply by array size
    public static int quadraticAlgorithm(int[] arr){
        int sum = 0;
        for(int i = 0; i < arr.length; i++){
            int arrSum = 0;
            for(int j = 0; j < arr.length; j++){
                arrSum += arr[j];
            }
            sum += arrSum;
        }
        return sum;
    }

    // see if an array contains any duplicates
    public boolean containsDuplicates(int[] arr) {
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = i+1; j < arr.length; j++) {
                if (arr[i] == arr[j]) return true;
            }
        }
        return false;
    }




    public static void main(String[] args) {

        // // Testing 
        // int [] arr = {1,2,3,4,5,6};
        // System.out.println(linearAlgorithm(arr, 10));
        // System.out.println(quadraticAlgorithm(arr));
    }
}