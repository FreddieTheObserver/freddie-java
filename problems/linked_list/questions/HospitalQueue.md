# Hospital Queue System — Singly Linked List

> **Solution:** [../solutions/HospitalQueue/](../solutions/HospitalQueue/)

## Problem Description

A hospital emergency room needs a queue management system to track patients waiting to be seen by a doctor. You will implement this system using a **Singly Linked List**.

---

## Part 1: Define the `Patient` class

Create a `Patient` class with the following fields:

| Field       | Type     | Description                              |
|-------------|----------|------------------------------------------|
| `id`        | `int`    | Unique patient ID                        |
| `name`      | `String` | Patient's full name                      |
| `age`       | `int`    | Patient's age                            |
| `condition` | `String` | e.g. "Fracture", "Fever", "Chest Pain"   |
| `priority`  | `int`    | 1 = Critical, 2 = Urgent, 3 = Normal    |
| `arrivalTime` | `String` | e.g. "08:30"                           |

- Write a constructor that accepts all fields.
- Write getters and setters for each field.
- Validate that `priority` is between 1 and 3 in the setter.

---

## Part 2: Define the `Node` class

Create a `Node` class that wraps a `Patient` object, just like your lab wrapped `Student`.

---

## Part 3: Implement `HospitalQueue`

Create a `HospitalQueue` class with `head` and `tail` pointers. Implement the following methods:

### Basic Operations

| Method | Description |
|--------|-------------|
| `isEmpty()` | Return `true` if the queue is empty |
| `addToTail(int id, String name, int age, String condition, int priority, String arrivalTime)` | A new patient joins the back of the queue |
| `addToHead(int id, String name, int age, String condition, int priority, String arrivalTime)` | An emergency patient is placed at the front |
| `deleteFromHead()` | The next patient is called in to see the doctor. Return the `Patient` |
| `deleteFromTail()` | The last patient in the queue decides to leave. Return the `Patient` |

### Hospital-Specific Operations

| Method | Description |
|--------|-------------|
| `searchByName(String name)` | Find and return a `Patient` by name (case-insensitive). Return `null` if not found |
| `countByPriority(int priority)` | Count how many patients have the given priority level |
| `removeById(int id)` | Remove a patient from anywhere in the queue by their ID. Return the removed `Patient`, or `null` if not found |
| `displayQueue()` | Print all patients in order from head to tail |

---

## Part 4: Test in `HospitalQueueLab.java`

Write a `main` method that does the following (in order):

```
1. Create a HospitalQueue

2. Add these patients to the TAIL:
   - (101, "John",    45, "Chest Pain", 1, "08:00")
   - (102, "Ann",     30, "Fever",      3, "08:15")
   - (103, "Saw",     22, "Fracture",   2, "08:20")

3. Print the head patient's name          → expected: John
4. Print the tail patient's name          → expected: Saw

5. Add to HEAD (emergency):
   - (104, "Peter",   60, "Stroke",     1, "08:25")
   Queue is now: Peter -> John -> Ann -> Saw

6. Print head patient's name              → expected: Peter

7. Doctor sees next patient (deleteFromHead)
   Print who was seen                     → expected: Peter
   Print new head                         → expected: John

8. Last patient leaves (deleteFromTail)
   Print who left                         → expected: Saw
   Print new tail                         → expected: Ann

9. Add more patients:
   - (105, "Mary",    28, "Fever",       3, "08:40")
   - (106, "David",   55, "Chest Pain",  1, "08:45")
   Queue: John -> Ann -> Mary -> David

10. Search for "ann" (case-insensitive)
    Print her ID                          → expected: 102

11. Search for "Nobody"
    Print result                          → expected: null (not found)

12. Count patients with priority 1
    Print count                           → expected: 2 (John, David)

13. Count patients with priority 3
    Print count                           → expected: 2 (Ann, Mary)

14. Remove patient with ID 102 (Ann)
    Print removed patient's name          → expected: Ann
    Queue: John -> Mary -> David

15. Display the final queue
```

---

## Expected Output

```
John
Saw
Peter
Peter
John
Saw
Ann
102
Not found
2
2
Ann
--- Final Queue ---
[101] John, 45, Chest Pain, Priority: 1, Arrived: 08:00
[105] Mary, 28, Fever, Priority: 3, Arrived: 08:40
[106] David, 55, Chest Pain, Priority: 1, Arrived: 08:45
```

---

## Bonus Challenges (Optional)

1. **`sortByPriority()`** — Rearrange the queue so critical patients (1) come first, then urgent (2), then normal (3). Do not create a new list — rearrange the existing nodes.

2. **`size()`** — Return the number of patients in the queue.

3. **`getPosition(String name)`** — Return the position (0-indexed) of a patient in the queue, or -1 if not found.
