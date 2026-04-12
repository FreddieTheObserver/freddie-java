# Merge Sort

A divide-and-conquer sorting algorithm that splits the array in half, recursively sorts each half, then merges the sorted halves.

## Complexity

| | Time | Space |
|--|------|-------|
| Best | O(n log n) | O(n) |
| Average | O(n log n) | O(n) |
| Worst | O(n log n) | O(n) |

**Stable:** Yes — equal elements keep their original order.

## How It Works

```
[38, 27, 43, 3, 9, 82, 10]

Split:  [38, 27, 43]     [3, 9, 82, 10]
Split:  [38] [27, 43]    [3, 9] [82, 10]
Split:  [38] [27] [43]   [3] [9] [82] [10]

Merge:  [38] [27, 43]    [3, 9] [10, 82]
Merge:  [27, 38, 43]     [3, 9, 10, 82]
Merge:  [3, 9, 10, 27, 38, 43, 82]
```

## Key Idea

The **merge** step is where the real work happens: two sorted arrays are combined into one sorted array by comparing elements from each side, always picking the smaller one.

## When to Use

- You need a guaranteed O(n log n) sort regardless of input
- Stability matters (preserving order of equal elements)
- You're sorting linked lists (merge sort is ideal — no random access needed)

## Trade-offs

- **vs Quick Sort:** Merge sort is always O(n log n) but uses O(n) extra space. Quick sort is O(n log n) average with O(1) extra space but O(n^2) worst case.
- **vs Insertion Sort:** Merge sort is faster for large arrays but insertion sort wins for small or nearly-sorted arrays due to lower constant factors.
