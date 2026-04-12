# Insertion Sort

A simple sorting algorithm that grows a sorted portion from left to right by inserting each new element into its correct position among the elements that are already sorted.

## Complexity

| | Time | Space |
|--|------|-------|
| Best | O(n) | O(1) |
| Average | O(n^2) | O(1) |
| Worst | O(n^2) | O(1) |

**Stable:** Yes - equal elements keep their original order.

## How It Works

```text
[2, 3, 4, 1, 6]

Start: [2] is sorted
i=1:   insert 3 into [2]       -> [2, 3, 4, 1, 6]
i=2:   insert 4 into [2, 3]    -> [2, 3, 4, 1, 6]
i=3:   insert 1 into [2, 3, 4] -> [1, 2, 3, 4, 6]
i=4:   insert 6 into [1, 2, 3, 4] -> no change
```

## Key Idea

Treat the first element as a sorted subarray of size 1. For each index `i`, move the current element left until it reaches the correct spot in the sorted portion `[0...i-1]`.

Typical pointer view:

- `i` points to the element being inserted
- `j` starts at `i - 1`
- while the current element is smaller than the element to its left, shift or swap it left

That is why the algorithm is called insertion sort: each new element is inserted into an already-sorted prefix.

## Java Implementation

```java
public static int[] insertionSort(int[] arr) {
    for (int i = 1; i < arr.length; i++) {
        int j = i - 1;
        while (j >= 0 && arr[j + 1] < arr[j]) {
            int tmp = arr[j + 1];
            arr[j + 1] = arr[j];
            arr[j] = tmp;
            j--;
        }
    }
    return arr;
}
```

## Why It Is Stable

Insertion sort only moves an element left while it is strictly smaller than the element before it. Equal values do not pass each other, so their original relative order is preserved.

Example:

```text
[7, 3, 7]
```

After sorting, the first `7` still appears before the second `7`.

## When to Use

- Small arrays where simplicity matters
- Nearly sorted data, where it can approach O(n)
- As a base case inside more advanced sorts for small subarrays
- Situations where stability is required

## Trade-offs

- **Best case:** O(n) when the array is already sorted, because the inner loop barely runs
- **Average case:** O(n^2)
- **Worst case:** O(n^2), usually when the array is in reverse order
- **Space:** O(1), because it sorts in place without using another array

Compared with merge sort or quick sort, insertion sort is much slower on large random inputs, but it has lower overhead and is often competitive on very small or nearly sorted arrays.
