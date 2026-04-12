# Depth-First Search (DFS) — Tree Traversal

DFS explores a tree by going as deep as possible along each branch before backtracking. There are three orderings depending on when you visit the current node relative to its children.

## Complexity

| | Time | Space |
|--|------|-------|
| All orders | O(n) | O(h) — h is tree height |

## Three Traversal Orders

Given this tree:
```
       1
      / \
     2   3
    / \    \
   4   5    6
```

### In-order (Left, Root, Right)
```
4, 2, 5, 1, 3, 6
```
Visits left subtree first, then current node, then right subtree. On a BST, this produces sorted order.

### Pre-order (Root, Left, Right)
```
1, 2, 4, 5, 3, 6
```
Visits current node first, then left, then right. Useful for copying/serializing a tree.

### Post-order (Left, Right, Root)
```
4, 5, 2, 6, 3, 1
```
Visits children first, then current node. Useful for deleting a tree or evaluating expressions.

## When to Use Which

| Order | Use case |
|-------|----------|
| In-order | Get sorted values from a BST |
| Pre-order | Serialize/copy a tree (captures structure) |
| Post-order | Delete a tree, evaluate expression trees |

## DFS vs BFS

| | DFS | BFS |
|--|-----|-----|
| Data structure | Stack (recursion) | Queue |
| Space | O(h) — tree height | O(w) — max width |
| Good for | Deep trees, path finding | Shortest path, level-order |
