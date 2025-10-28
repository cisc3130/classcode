public class QuickSortInt {
    public void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length);
    }

    protected void quickSort(int[] arr, int begin, int end) {
        // base case
        if (end - begin < 2) return;

        int pivot = partition(arr, begin, end);
        quickSort(arr, begin, pivot);
        quickSort(arr, pivot + 1, end);
    }

    protected int partition(int[] arr, int begin, int end) {
        int pidx = choosePivot(arr, begin, end);

        // swap pivot to the end
        int tmp = arr[pidx];
        arr[pidx] = arr[end-1];
        arr[end-1] = tmp;

        int nextAvailableIdx = begin, nextCompareIdx = begin;
        while (nextCompareIdx < end-1) {
            if (arr[nextCompareIdx] < arr[pidx]) {
                // swap the element we compared into the nextAvailableIndex
                tmp = arr[nextAvailableIdx];
                arr[nextAvailableIdx] = arr[nextCompareIdx];
                arr[nextCompareIdx] = tmp;
                nextAvailableIdx++;
            }
            nextCompareIdx++;   // either way, move nextCompareIdx forward
        }

        // swap pivot into the nextAvailableIdx
        tmp = arr[nextAvailableIdx];
        arr[nextAvailableIdx] = arr[pidx];
        arr[pidx] = tmp;

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

