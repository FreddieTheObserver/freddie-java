# Heap

A complete binary tree stored in an array where each parent satisfies the heap property relative to its children.

- **Min-heap:** parent ≤ children (root is minimum)
- **Max-heap:** parent ≥ children (root is maximum)

## Array Indexing (0-based)

For node at index `i`:

| Relation | Index |
|----------|-------|
| Parent | `(i - 1) / 2` |
| Left child | `2 * i + 1` |
| Right child | `2 * i + 2` |

## Structure (min-heap example)

```
        6
       / \
     10   8
    /  \   \
   15  12  20
```

Array representation: `[6, 10, 8, 15, 12, 20]` — index 0 is the minimum.

## Operations Implemented

| Method | Time | Description |
|--------|------|-------------|
| `insert(val)` | O(log n) | Add element, bubble up (`heapifyUp`) |
| `extractMin()` / `extractMax()` | O(log n) | Remove root, bubble down (`heapifyDown`) |
| `delete(index)` | O(log n) | Remove arbitrary element, re-heapify |
| `peek()` | O(1) | View root without removing |
| `buildHeap(arr)` | O(n) | Bottom-up heapify from `n/2 - 1` down to `0` |
| `heapify(arr, size, i)` | O(log n) | Restore max-heap property at index `i` (static, in-place) |
| `heapSort(arr)` | O(n log n) | Build max-heap, repeatedly swap root with end |

## Heap Sort (in-place)

1. **Build max-heap** — heapify from last non-leaf (`n/2 - 1`) to root
2. **Extract repeatedly** — swap root with last element, shrink heap, heapify root

## Priority Queue

`PriorityQueue` wraps `MinHeap` with standard queue naming:

| Method | Heap equivalent |
|--------|-----------------|
| `offer(val)` | `insert` |
| `poll()` | `extractMin` |
| `peek()` | `peek` |

Lower value = higher priority (min-heap). Use `MaxHeap` if larger values should dequeue first.

## When to Use

- **Top-K / Kth largest/smallest** — heap of size K
- **Merge K sorted lists** — min-heap of heads
- **Dijkstra's algorithm** — priority queue over distances
- **Heap sort** — O(n log n) in-place sorting with O(1) extra space (aside from recursion stack in recursive heapify)

## Trade-offs

| | Heap | BST (balanced) |
|--|------|----------------|
| Find min/max | O(1) | O(log n) |
| Insert | O(log n) | O(log n) |
| Delete arbitrary | O(log n) with index | O(log n) with key |
| Sorted traversal | O(n log n) via heap sort | O(n) in-order |
