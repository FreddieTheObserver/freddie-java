package algorithms.sorting.bubble_sort;

public class BubbleSortMain {
    public static int[] bubbleSortV1(int[] arr) {
        int n = arr.length;
        boolean swapped = true;

        while (swapped) {
            swapped = false;

            for (int i = 1; i < n; i++) {
                if (arr[i - 1] > arr[i]) {
                    swapped = true;

                    int temp = arr[i - 1];
                    arr[i - 1] = arr[i];
                    arr[i] = temp;
                }
            }
            n--;
        }
        return arr;
    }

    public static int[] bubbleSortV2(int[] arr) {
        int start = 0;
        boolean swapped = true;

        while (swapped) {
            swapped = false;

            for (int i = arr.length - 1; i > start; i--) {
                if (arr[i - 1] > arr[i]) {
                    swapped = true;

                    int temp = arr[i - 1];
                    arr[i - 1] = arr[i];
                    arr[i] = temp;
                }
            }
            start++;
        }
        return arr;
    }
}
