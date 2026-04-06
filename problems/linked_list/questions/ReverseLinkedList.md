# Reverse a Linked List — Singly Linked List

## Problem Description

Given a singly linked list, reverse it **in-place** — without allocating a new list.

For example:

```
Input:  1 -> 2 -> 3 -> 4 -> 5 -> null
Output: 5 -> 4 -> 3 -> 2 -> 1 -> null
```

---

## Part 1: Define the `Node` class

Create a generic `Node<T>` class with the following fields:

| Field  | Type   | Description                       |
|--------|--------|-----------------------------------|
| `data` | `T`    | The value stored in this node     |
| `next` | `Node<T>` | Pointer to the next node       |

- Write a constructor that accepts `data` and sets `next` to `null`.

---

## Part 2: Implement `LinkedList<T>`

Create a `LinkedList<T>` class with a `head` pointer. Implement the following methods:

| Method | Description |
|--------|-------------|
| `addToTail(T data)` | Append a new node at the end of the list |
| `display()` | Print all values from head to tail, separated by `" -> "`, ending with `"null"` |
| `toArray()` | Return an `ArrayList<T>` of all values from head to tail (used for testing) |

---

## Part 3: Implement `reverse()`

Add a `reverse()` method to your `LinkedList<T>` class that reverses the list **in-place**.

**Constraints:**
- Do **not** create a new linked list or copy values into an array.
- Rearrange the `next` pointers of existing nodes.
- After calling `reverse()`, `head` must point to what was previously the tail.

---

## Part 4: Test in `ReverseLinkedListLab.java`

Write a `main` method that performs the following tests:

```
1. Create a LinkedList and add: 1, 2, 3, 4, 5
   Display the list             → expected: 1 -> 2 -> 3 -> 4 -> 5 -> null

2. Reverse the list
   Display the list             → expected: 5 -> 4 -> 3 -> 2 -> 1 -> null

3. Reverse again (should restore original order)
   Display the list             → expected: 1 -> 2 -> 3 -> 4 -> 5 -> null

4. Create a new list with a single element: 42
   Reverse it
   Display the list             → expected: 42 -> null

5. Create an empty list
   Reverse it (should not crash)
   Display the list             → expected: null
```

---

## Expected Output

```
1 -> 2 -> 3 -> 4 -> 5 -> null
5 -> 4 -> 3 -> 2 -> 1 -> null
1 -> 2 -> 3 -> 4 -> 5 -> null
42 -> null
null
```

---

## Bonus Challenges (Optional)

1. **`reverseRecursive()`** — Implement a second version of reverse using recursion instead of iteration.

2. **`reverseSubList(int left, int right)`** — Reverse only the nodes between positions `left` and `right` (1-indexed, inclusive).
   ```
   Input:  1 -> 2 -> 3 -> 4 -> 5, left=2, right=4
   Output: 1 -> 4 -> 3 -> 2 -> 5
   ```

3. **`isPalindrome()`** — Return `true` if the list reads the same forwards and backwards. Use your `reverse()` logic to help.
