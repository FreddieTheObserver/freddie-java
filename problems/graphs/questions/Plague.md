# Plague — Day-by-Day BFS with Burnout

> **Solution:** [../solutions/Plague/](../solutions/Plague/)

## Problem Description

An `R x C` grid starts with `K` infected cells. Each day, infection spreads to all 4-directional neighbors — but any cell that has been infected for **exactly 3 days** "burns out" and becomes **immune** (it stops spreading and can never be re-infected).

Cells that are immune are still counted as "was infected" but no longer spread.

After `D` days, output two numbers:
1. The count of **currently-infected** cells (still spreading).
2. The count of **immune** cells (burnt out).

---

## Spread Rules (per day)

1. Every active infected cell spreads to its 4-directional neighbors that have **never been infected**.
2. A cell whose infection is exactly 3 days old burns out: it does **not** spread that day and stays immune forever.
3. Once a cell has been infected (active or immune), it cannot be re-infected.

### Lifecycle of a cell

| Day relative to infection | State          | Spreads? |
|---------------------------|----------------|----------|
| Day 0 (infected)          | active         | yes      |
| Day 1                     | active         | yes      |
| Day 2                     | active         | yes      |
| Day 3                     | immune (burnt) | no       |

---

## Input Format

```
R C D K
r_1 c_1
r_2 c_2
...
r_K c_K
```

| Token | Meaning |
|-------|---------|
| `R`   | number of rows |
| `C`   | number of columns |
| `D`   | number of days to simulate |
| `K`   | number of initial infections |
| `(r_i, c_i)` | coordinates of initial infection `i` (infected on day 0, 0-indexed) |

---

## Output Format

A single line with two space-separated integers:

```
<currently-infected> <immune>
```

---

## Constraints

- `1 <= R, C <= 1000`
- `0 <= D <= 1000`
- `1 <= K <= R * C`
- All coordinates are within bounds; no duplicate initial infections.

---

## Example

### Input
```
3 3 4 1
1 1
```

### Walkthrough

Initial: cell `(1,1)` infected on day 0.

- **Day 1:** `(1,1)` (age 1) spreads to `(0,1)`, `(2,1)`, `(1,0)`, `(1,2)`.
- **Day 2:** `(1,1)` (age 2) tries to spread — all neighbors already infected. Day-1 cells (age 1) spread to corners `(0,0)`, `(0,2)`, `(2,0)`, `(2,2)`.
- **Day 3:** `(1,1)` (age 3) burns out. Day-1 cells (age 2) try to spread — no untouched neighbors remain.
- **Day 4:** Day-1 cells (age 3) burn out. Day-2 cells (age 2) try to spread — none left.

After day 4: `(1,1)` and the four day-1 cells are immune (5). The four corners (day-2 cells) are still active (4).

### Output
```
4 5
```

---

## Hint — Track the Day of Infection

Store the day each cell got infected in an `int[R][C]` (use `-1` for "never infected"). Then on any day `d`, the **age** of a cell is `d - infectedDay[r][c]`.

```
if age == 3 → burns out (immune, stop processing)
else        → still active, spread to never-infected neighbors
```

### Two-Queue BFS Loop

Use the same level-by-level pattern as Frostbite, with one twist — **a cell stays in the queue across days while it's still active**, so re-enqueue it for tomorrow after it spreads:

```
for each day d in 1..D:
    while currentQ not empty:
        pop (r, c)
        age = d - infectedDay[r][c]
        if age >= 3:
            continue                        # burnt out: drop it
        for each neighbor (nr, nc):
            if in bounds AND infectedDay[nr][nc] == -1:
                infectedDay[nr][nc] = d
                push (nr, nc) to nextQ
        push (r, c) to nextQ                # still active tomorrow
    swap currentQ and nextQ
```

### Final Tally

After `D` days, scan the grid:
- `infectedDay[r][c] == -1` → never infected, skip.
- `D - infectedDay[r][c] >= 3` → immune.
- Otherwise → still active.

Total work: `O(R * C * 3)` since each cell sits in the queue for at most 3 days before burning out.
