package binary_search.solutions.MountainArray;

import java.util.Scanner;

public class MountainArrayMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        int l = 0, r = n - 1;
        while (l < r) {
            int mid = (l + r) / 2;
            if (arr[mid] < arr[mid + 1]) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        System.out.println(l);

        scanner.close();
    }
}
