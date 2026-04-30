# Frostbite — Multi-Source BFS on a Grid

> **Solution:** [../solutions/Frostbite/](../solutions/Frostbite/)

## Problem Description

A frozen lake is represented as an `R x C` grid. Each cell is initially **water** (`0`). Over `D` days, ice spreads across the lake.

### Spread Rules (per day)

1. **Existing ice expands** — every ice cell spreads to its 4-directional neighbors (up, down, left, right), turning them to ice.
2. **Heat sources block ice** — some cells contain heat sources (`H`). A heat source cell can never become ice, **AND** its 4 neighbors are also protected — ice cannot spread into any cell adjacent to a heat source.
3. **Daily freeze point** — at the end of each day, a new freeze point is dropped at `(r, c)`. If that cell is water and not protected by a heat source, it becomes ice.
4. **Newly frozen cells spread the next day**, not the day they were frozen (standard two-queue rule).

After `D` days, output the number of ice cells on the grid.

---

## Input Format

```
R C D K
hr_1 hc_1
hr_2 hc_2
...
hr_K hc_K
fr_1 fc_1
fr_2 fc_2
...
fr_D fc_D
```

| Token | Meaning |
|-------|---------|
| `R`   | number of rows |
| `C`   | number of columns |
| `D`   | number of days |
| `K`   | number of heat sources |
| `(hr_i, hc_i)` | coordinates of heat source `i` |
| `(fr_j, fc_j)` | coordinates of the daily freeze point on day `j` |

All coordinates are 0-indexed.

---

## Output Format

A single integer: the total number of ice cells after day `D`.

---

## Constraints

- `1 <= R, C <= 1000`
- `1 <= D <= 1000`
- `0 <= K <= R * C`
- All coordinates are within bounds.

---

## Example

### Input
```
4 4 3 1
1 1
2 2
3 3
0 0
```

### Walkthrough

Heat source at `(1,1)` protects itself and `(0,1)`, `(2,1)`, `(1,0)`, `(1,2)`.

- **Day 1:** No ice yet to spread. Freeze point `(2,2)` → becomes ice.
- **Day 2:** Ice at `(2,2)` spreads to `(1,2)`❌protected, `(3,2)`✓, `(2,1)`❌protected, `(2,3)`✓. Then freeze point `(3,3)` → becomes ice.
- **Day 3:** Ice spreads from `(3,2)`, `(2,3)`, `(3,3)`. Freeze point `(0,0)` → becomes ice.

### Output
```
8
```

---

## Hint — Two-Queue BFS

This is a level-by-level BFS. Use two queues: `currentQ` (cells that spread today) and `nextQ` (cells that just became ice and will spread tomorrow).

```
for each day d in 1..D:
    while currentQ not empty:
        pop cell (r, c)
        for each neighbor (nr, nc):
            if in bounds AND not ice AND not protected:
                mark ice
                push to nextQ
    process the day's freeze point → if valid, mark ice and push to nextQ
    swap: currentQ = nextQ, nextQ = empty
```

Each cell is pushed to the queue at most once, so total work is `O(R * C + D)`.
