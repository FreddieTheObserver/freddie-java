# Mountain Array — Find the Peak in O(log n)

> **Solution:** [../solutions/MountainArray/](../solutions/MountainArray/)

## Problem Description

An array is a **mountain** if it strictly increases up to a peak, then strictly decreases. Formally, there exists an index `p` (`0 < p < n - 1`) such that:

```
arr[0] < arr[1] < ... < arr[p-1] < arr[p] > arr[p+1] > ... > arr[n-1]
```

Given an array of `n` distinct integers that is **guaranteed** to be a mountain, return the index of the peak.

**Required time complexity:** `O(log n)`.

---

## Input Format

```
<n>
<arr[0]> <arr[1]> ... <arr[n-1]>
```

- Line 1: integer `n`, the length of the array.
- Line 2: `n` space-separated distinct integers forming a valid mountain.

---

## Output Format

A single integer: the 0-indexed position of the peak.

---

## Examples

### Example 1
**Input**
```
5
1 3 5 4 2
```
**Output**
```
2
```
**Why:** `arr[2] = 5` is the peak — values strictly increase before it and strictly decrease after it.

### Example 2
**Input**
```
3
0 10 5
```
**Output**
```
1
```

### Example 3
**Input**
```
6
1 2 3 4 5 0
```
**Output**
```
4
```
**Why:** the peak is `arr[4] = 5`.

---

## Constraints

- `3 <= n <= 10^5`
- All `arr[i]` are distinct.
- The input is guaranteed to be a valid mountain (peak is strictly inside, not at index `0` or `n-1`).

---

## Hint — Binary Search on a Monotonic Condition

The key observation: for any index `mid` not at the peak, the comparison `arr[mid] < arr[mid + 1]` tells you which side of the peak you're on.

| `arr[mid] < arr[mid + 1]` | Where you are        | Move           |
|---------------------------|----------------------|----------------|
| `true`                    | ascending side       | `l = mid + 1`  |
| `false`                   | at peak or descending| `r = mid`      |

That condition is monotonic across the array (`true...true, false...false`), so binary-searching the boundary lands exactly on the peak.

```
l = 0, r = n - 1
while l < r:
    mid = (l + r) / 2
    if arr[mid] < arr[mid + 1]:
        l = mid + 1
    else:
        r = mid
return l
```

When the loop exits, `l == r` and that index is the peak. Because the input is guaranteed to be a mountain, `mid + 1` is always in bounds when we read it (we never compute `mid` at `r`).
