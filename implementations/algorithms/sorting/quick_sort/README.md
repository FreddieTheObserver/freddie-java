# Quick Sort

A divide-and-conquer sorting algorithm that picks a pivot, partitions the array so everything smaller is on the left and everything larger is on the right, then recursively sorts each side.

## Complexity

| | Time | Space |
|--|------|-------|
| Best | O(n log n) | O(log n) |
| Average | O(n log n) | O(log n) |
| Worst | O(n^2) | O(n) |

**Stable:** No — partitioning can swap equal elements.

## How It Works (Lomuto Partition)

```
[3, 6, 8, 10, 1, 2, 1]   pivot = 1 (last element)

Partition around pivot:
  left pointer scans, swaps elements < pivot to the left side
  After partition: [1, 1, 8, 10, 6, 2, 3]  pivot placed at index 1

Recurse on [left of pivot] and [right of pivot]
```

## Partition Explained

1. Choose the last element as pivot
2. Maintain a `left` pointer starting at the beginning
3. Scan through: if `arr[i] < pivot`, swap `arr[i]` with `arr[left]`, advance `left`
4. After the scan, swap pivot into position `left`
5. Everything before `left` is smaller, everything after is larger

## When to Use

- General-purpose sorting with good average performance
- In-place sorting (O(log n) stack space vs merge sort's O(n) auxiliary array)
- Cache-friendly due to sequential memory access

## Worst Case

Occurs when the pivot consistently picks the smallest or largest element (already sorted arrays with last-element pivot). Mitigations: randomized pivot, median-of-three.
