# Singly Linked List

A linear data structure where each node stores a value and a pointer to the next node. The list maintains `head` and `tail` references.

## Key Properties

- **Access:** O(n) — must traverse from head
- **Insert/Delete at head:** O(1)
- **Insert/Delete at tail:** O(1) with tail pointer (insert), O(n) without prev pointer (delete)
- **Search:** O(n)

## Structure

```
head                          tail
 |                             |
 v                             v
[10] -> [20] -> [30] -> [40] -> null
```

Each node holds:
- `val` — the stored value
- `next` — pointer to the next node (or `null`)

## Operations Implemented

| Method | Time | Description |
|--------|------|-------------|
| `addToHead(val)` | O(1) | Insert at front |
| `addToTail(val)` | O(1) | Insert at end |
| `addAt(index, val)` | O(n) | Insert at position |
| `deleteFromHead()` | O(1) | Remove front node |
| `deleteFromTail()` | O(n) | Remove last node (no prev pointer, must traverse) |
| `deleteAt(index)` | O(n) | Remove at position |
| `getAt(index)` | O(n) | Get value at position |
| `contains(val)` | O(n) | Check if value exists |
| `find(val)` | O(n) | Return index of value |
| `size()` | O(n) | Count all nodes |
| `peekHead()` | O(1) | Read head value |
| `peekTail()` | O(1) | Read tail value |

## When to Use

- You need frequent insertions/deletions at the front
- You don't need random access by index
- Memory efficiency matters (no wasted capacity like arrays)

## Trade-offs vs Array

| | Singly Linked List | Array |
|--|---|---|
| Access by index | O(n) | O(1) |
| Insert at front | O(1) | O(n) |
| Insert at end | O(1) | O(1) amortized |
| Delete from middle | O(n) search + O(1) remove | O(n) shift |
| Memory | Extra pointer per node | Contiguous, may over-allocate |
