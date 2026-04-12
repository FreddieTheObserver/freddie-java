# LRU Cache — Doubly Linked List + HashMap

## Difficulty: Hard

## Problem Description

Design and implement a **Least Recently Used (LRU) Cache**. This is one of the most common hard interview questions involving linked lists.

An LRU Cache supports two operations in **O(1)** time:

- `get(key)` — Return the value if the key exists, otherwise return `-1`. Marks the key as recently used.
- `put(key, value)` — Insert or update the key-value pair. If the cache exceeds its capacity, **evict the least recently used** item before inserting.

The trick: you need a **Doubly Linked List** (to move/remove nodes in O(1)) combined with a **HashMap** (to look up nodes in O(1)).

---

## Part 1: Define the `CacheNode` class

Create a `CacheNode` class with the following fields:

| Field   | Type        | Description                    |
|---------|-------------|--------------------------------|
| `key`   | `int`       | The cache key                  |
| `value` | `int`       | The cached value               |
| `prev`  | `CacheNode` | Pointer to the previous node   |
| `next`  | `CacheNode` | Pointer to the next node       |

- Write a constructor that accepts `key` and `value`.

---

## Part 2: Implement `LRUCache`

Create an `LRUCache` class with:

- A **capacity** (set at construction time)
- A **HashMap<Integer, CacheNode>** for O(1) lookups
- A **doubly linked list** with dummy `head` and `tail` sentinel nodes (this simplifies edge cases enormously)

The doubly linked list maintains **usage order**: the node right after `head` is the **most recently used**, and the node right before `tail` is the **least recently used**.

### Methods to Implement

| Method | Description |
|--------|-------------|
| `LRUCache(int capacity)` | Constructor. Initialize the cache with a positive capacity. Create sentinel head/tail nodes and link them together. |
| `int get(int key)` | If key exists: move the node to the front (most recent), return its value. If not: return `-1`. |
| `void put(int key, int value)` | If key exists: update its value and move it to the front. If key is new: create a node, add it to the front, put it in the map. If size exceeds capacity: remove the node before `tail` (least recent), remove it from the map. |

### Internal Helper Methods (recommended)

| Method | Description |
|--------|-------------|
| `void addToFront(CacheNode node)` | Insert a node right after the `head` sentinel |
| `void removeNode(CacheNode node)` | Detach a node from wherever it currently sits in the list |
| `void moveToFront(CacheNode node)` | Remove from current position, then add to front |
| `CacheNode evictLast()` | Remove and return the node right before `tail` (the LRU item) |

---

## Part 3: Test in `LRUCacheLab.java`

Write a `main` method that performs the following tests:

```
1. Create an LRUCache with capacity 3

2. put(1, 10)
   put(2, 20)
   put(3, 30)
   Cache state (most recent -> least recent): 3:30, 2:20, 1:10

3. get(1)          -> expected: 10
   Cache state: 1:10, 3:30, 2:20   (key 1 moved to front)

4. get(99)         -> expected: -1  (not found)

5. put(4, 40)      -> evicts key 2 (it's the least recently used)
   Cache state: 4:40, 1:10, 3:30

6. get(2)          -> expected: -1  (was evicted)

7. get(3)          -> expected: 30
   Cache state: 3:30, 4:40, 1:10

8. put(5, 50)      -> evicts key 1
   Cache state: 5:50, 3:30, 4:40

9. get(1)          -> expected: -1  (was evicted)
   get(3)          -> expected: 30
   get(4)          -> expected: 40
   get(5)          -> expected: 50

10. put(3, 300)    -> update existing key 3
    get(3)         -> expected: 300
    Cache state: 3:300, 5:50, 4:40

11. Create a new LRUCache with capacity 1
    put(1, 1)
    put(2, 2)      -> evicts key 1 immediately
    get(1)         -> expected: -1
    get(2)         -> expected: 2
```

---

## Expected Output

```
10
-1
-1
30
-1
30
40
50
300
-1
2
```

---

## Key Concepts to Understand

- **Why a doubly linked list?** You need to remove a node from the middle in O(1). With a singly linked list, removal requires traversal to find the previous node.
- **Why sentinel nodes?** They eliminate null checks for head/tail edge cases. Every real node always has a valid `prev` and `next`.
- **Why a HashMap?** Without it, finding a node by key requires O(n) traversal. The HashMap gives you O(1) access to any node, and the list gives you O(1) reordering.

---

## Bonus Challenges (Optional)

1. **`display()`** — Print the cache contents from most recently used to least recently used.

2. **`size()`** — Return the current number of items in the cache.

3. **TTL (Time-To-Live):** Modify the cache so each entry has an expiration time. `get()` should return `-1` for expired entries and remove them.

4. **Thread Safety:** Think about (or implement) what would need to change to make this cache thread-safe.
