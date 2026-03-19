/*
4, 9, 8, 2, 7, 1, 6, 3
4, 2, 1, 3, 6, 8, 9, 7
            ^        ^ 
3 1 4 2 6 9 8 7
3 1 4 2              9 8 7
1 2 3 4
1   3 4
*/


public class QuickSortInt {
    public void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length);
    }

    protected void quickSort(int[] arr, int begin, int end) {
        // base case
        if (end - begin < 2) return;

        // base case to shorten tracing:
        // if there are two elements, just swap them if they're out of order
        if (end - begin == 2) {
            if (arr[begin] > arr[begin+1]) {
                int tmp = arr[begin];
                arr[begin] = arr[begin+1];
                arr[begin+1] = tmp;
            }
            return;
        }

        int pivot = partition(arr, begin, end);
        quickSort(arr, begin, pivot);
        quickSort(arr, pivot + 1, end);
    }

    protected int partition(int[] arr, int begin, int end) {
        int pidx = choosePivot(arr, begin, end);

        // swap the pivot to the end of the range
        int pivot = arr[pidx];
        arr[pidx] = arr[end-1];
        arr[end-1] = pivot;

        int nextAvailableIdx = begin, readIdx = begin;
        while (readIdx < pidx) {
            if (arr[readIdx] < pivot) {
                // swap arr[readIdx] into the next available slot
                int tmp = arr[nextAvailableIdx];
                arr[nextAvailableIdx] = arr[readIdx];
                arr[readIdx] = tmp;
                nextAvailableIdx++;
            }
            readIdx++;

        }

        // swap the pivot into its final position
        int tmp = arr[nextAvailableIdx];
        arr[nextAvailableIdx] = arr[end-1];
        arr[end-1] = tmp;

        return nextAvailableIdx;
    }

    protected int choosePivot(int[] arr, int begin, int end) {
        return end-1;
    }

    public static void main(String[] args) {
        int [] arr = {4, 9, 8, 2, 7, 1, 6, 3};
        QuickSortInt q = new QuickSortInt();
        q.quickSort(arr);
    }
}

