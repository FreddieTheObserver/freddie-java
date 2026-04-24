package algorithms.sorting.merge_sort;

public class MergeSortMain {
    public static int[] mergeSort(int[] arr) {
        if (arr == null || arr.length <= 1) return arr;

        mergeSort(arr, 0, arr.length - 1);
        return arr;
    }

    private static void mergeSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }

        int mid = left + (right - left) / 2;

        mergeSort(arr, left, mid);
        mergeSort(arr,mid + 1, right);

        merge(arr, left, mid, right);
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int [right - left + 1];

        int l = left;
        int r = mid + 1;
        int i = 0;

        while (l <= mid && r <= right) {
            if (arr[l] <= arr[r]) {
                temp[i] = arr[l];
                l++;
            } else {
                temp[i] = arr[r];
                r++;
            }
            i++;
        }

        while (l <= mid) {
            temp[i] = arr[l];
            l++;
            i++;
        }

        while (r <= right) {
            temp[i] = arr[r];
            r++;
            i++;
        }

        for (int x = 0; x < temp.length; x++) {
            arr[left + x] = temp[x];
        }
    }
}
