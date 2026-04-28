package algorithms.sorting.EverySort;

import java.util.Arrays;

public class Sorting {
    public static void main(String[] args) {
        int[][] cases = {
                {4, 2, 7, 1, 3},
                {5, 2, 4, 6, 1, 3},
                {1},
                {},
                {3, 3, 3},
                {-2, 5, 0, -1, 8, -7},
                {170, 45, 75, 90, 802, 24, 2, 66}
        };

        for (int[] input : cases) {
            int[] expected = input.clone();
            Arrays.sort(expected);

            System.out.println("Input:    " + Arrays.toString(input));
            System.out.println("Expected: " + Arrays.toString(expected));

            run("insertion", Sorting.insertionSort(input.clone()), expected);
            run("bubble",    Sorting.bubbleSort(input.clone()),    expected);
            run("selection", Sorting.selectionSort(input.clone()), expected);
            run("merge",     Sorting.mergeSort(input.clone()),     expected);

            int[] qs = input.clone();
            if (qs.length > 0) Sorting.quickSort(qs, 0, qs.length - 1);
            run("quick", qs, expected);

            run("heap",  Sorting.heapSort(input.clone()),  expected);
            run("shell", Sorting.shellSort(input.clone()), expected);

            boolean hasNegative = false;
            for (int v : input) if (v < 0) { hasNegative = true; break; }
            if (hasNegative) {
                System.out.println("  radix:     SKIPPED (negatives not supported)");
            } else {
                run("radix", Sorting.radixSort(input.clone()), expected);
            }

            System.out.println();
        }
    }

    private static void run(String name, int[] actual, int[] expected) {
        boolean ok = Arrays.equals(actual, expected);
        System.out.printf("  %-10s %s %s%n", name + ":", ok ? "PASS" : "FAIL", Arrays.toString(actual));
    }

    public static int[] insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int j = i - 1;
            while (j >= 0 && arr[j + 1] < arr[j]) {
                int temp = arr[j + 1];
                arr[j + 1] = arr[j];
                arr[j] = temp;
                j--;
            }
        }
        return arr;
    }

    public static int[] bubbleSort(int[] arr) {
        int n = arr.length;
        boolean swap = true;

        while (swap) {
            swap = false;

            for (int i = 1; i < n; i++) {
                if (arr[i - 1] > arr[i]) {
                    swap = true;

                    int temp = arr[i - 1];
                    arr[i - 1] = arr[i];
                    arr[i] = temp;
                }
            }
            n--;
        }
        return arr;
    }

    public static int[] selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }
        return arr;
    }

    public static int[] mergeSort(int[] arr) {
        if (arr == null || arr.length == 1) return arr;

        mergeSort(arr, 0, arr.length - 1);
        return arr;
    }

    public static void mergeSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }

        int mid = left + (right - left) / 2;

        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);

        merge(arr, left, mid, right);
    }

    public static void merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int [right - left + 1];

        int l = left;
        int r = mid + 1;
        int i = 0;

        while (l <= mid && r <= right) {
            if (arr[l] < arr[r]) {
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
            arr[x + left] = temp[x];
        }
    }

    public static int[] quickSort(int[] arr, int s, int e) {
        if (e - s + 1 <= 1) return arr;

        int pivot = arr[e];
        int left = s;

        for (int i = s; i < e; i++) {
            if (arr[i] < pivot) {
                int temp = arr[left];
                arr[left] = arr[i];
                arr[i] = temp;
                left++;
            }
        }
        arr[e] = arr[left];
        arr[left] = pivot;

        quickSort(arr, s, left - 1);
        quickSort(arr, left + 1, e);

        return arr;
    }

    public static int[] heapSort(int[] arr) {
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        for (int i = n - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heapify(arr, i, 0);
        }

        return arr;
    }

    public static void heapify(int[] arr, int size, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < size && arr[left] > arr[largest]) {
            largest = left;
        }

        if (right < size && arr[right] > arr[largest]) {
            largest = right;
        }

        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            heapify(arr, size, largest);
        }
    }

    public static int[] shellSort(int[] arr) {
        int n = arr.length;

        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                int temp = arr[i];
                int j = i;

                while (j >= gap && arr[j - gap] > temp) {
                    arr[j] = arr[j - gap];
                    j -= gap;
                }
                arr[j] = temp;
            }
        }
        return arr;
    }

    public static int[] radixSort(int[] arr) {
        if (arr == null || arr.length <= 1) return arr;

        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) max = arr[i];
        }

        for (int d = 1; max / d > 0; d *= 10) {
            countingSortByDigit(arr, d);
        }

        return arr;
    }

    public static void countingSortByDigit(int[] arr, int d) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10];

        for (int i = 0; i < n; i++) {
            int digit = (arr[i] / d) % 10;
            count[digit]++;
        }

        for (int k = 1; k < 10; k++) {
            count[k] += count[k - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            int digit = (arr[i] / d) % 10;
            count[digit]--;
            output[count[digit]] = arr[i];
        }

        for (int i = 0; i < n; i++) {
            arr[i] = output[i];
        }
    }
}

