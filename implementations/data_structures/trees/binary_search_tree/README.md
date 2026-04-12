# Binary Search Tree (BST)

A binary tree where every node's left subtree contains only values less than the node, and every right subtree contains only values greater than (or equal to) the node.

## Key Properties

- **Search:** O(h) where h = height
- **Insert:** O(h)
- **Delete:** O(h)
- **In-order traversal** yields sorted values
- **Balanced:** h = O(log n), **Skewed:** h = O(n)

## Structure

```
        8
       / \
      3   10
     / \    \
    1   6   14
       / \  /
      4  7 13
```

In-order traversal: `1, 3, 4, 6, 7, 8, 10, 13, 14`

## Operations Implemented

| Method | Time (avg) | Description |
|--------|------------|-------------|
| `insert(root, val)` | O(log n) | Insert value, duplicates go right |
| `search(root, val)` | O(log n) | Return true if value exists |
| `remove(root, val)` | O(log n) | Remove value, handles all 3 cases |
| `minValueNode(root)` | O(log n) | Find the smallest value in subtree |

## Deletion Cases

1. **Leaf node** — simply remove it
2. **One child** — replace node with its child
3. **Two children** — replace value with in-order successor (smallest in right subtree), then delete the successor

## When to Use

- You need sorted data with dynamic insertions/deletions
- You need O(log n) search on average
- You want in-order traversal to yield sorted output

## Limitations

An unbalanced BST degrades to a linked list with O(n) operations. Self-balancing trees (AVL, Red-Black) solve this but add complexity.
