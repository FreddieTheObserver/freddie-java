# Library Book Management System — RandomAccessFile

> **Solution:** [../solutions/LibraryManagement/](../solutions/LibraryManagement/)

## Problem Description

A local library needs a system to store and retrieve book records from a binary file using `RandomAccessFile`. You will practice fixed-length record I/O, byte-level seeking, and direct record updates.

---

## Record Structure

Each record must be **exactly 86 bytes**:

| Field      | Type     | Size     | Write Method    |
|------------|----------|----------|-----------------|
| `id`       | `int`    | 4 bytes  | `writeInt`      |
| `title`    | `String` | 40 bytes | pad/trim to 40  |
| `author`   | `String` | 30 bytes | pad/trim to 30  |
| `price`    | `double` | 8 bytes  | `writeDouble`   |
| `quantity` | `int`    | 4 bytes  | `writeInt`      |

**Total: 4 + 40 + 30 + 8 + 4 = 86 bytes per record**

> **Hint:** To write a fixed-length String field, pad it with trailing spaces and then write only the first N bytes:
> ```java
> byte[] titleBytes = (title + " ".repeat(40)).getBytes();
> fpt.write(titleBytes, 0, 40);
> ```
> When reading back, call `.trim()` on the reconstructed String to strip padding.

---

## Part 1: `addBook()`

Write a method that appends one new book record to `library.data`.

- Open the file in `"rw"` mode.
- Seek to the **end** of the file before writing.
- Write each field in order: `id`, `title` (40 bytes), `author` (30 bytes), `price`, `quantity`.

---

## Part 2: `readBook()`

Write a method that reads and returns the **first record** in the file as a formatted String.

Return format:
```
id, title, author, price, quantity
```

---

## Part 3: `readBook(int recordNumber)`

Write a method that reads and returns the record at a given **1-based position**.

- Use `seek(86L * (recordNumber - 1))` to jump directly to the record.
- Return the same format as Part 2.

---

## Part 4: `searchByAuthor(String author)`

Scan **all records** in the file and return a multi-line String of every book whose author matches the given name (case-insensitive).

- Use `fpt.length() / 86` to find the total number of records.
- Loop with `fpt.seek((long) i * 86)` at the start of each iteration.
- If no match is found, return `"No books found for this author."`.

---

## Part 5: `searchByPriceRange(double min, double max)`

Scan all records and return every book whose `price` falls within `[min, max]` inclusive.

- Return format matches Part 2 (one book per line).
- If nothing is found, return `"No books found in this price range."`.

---

## Part 6: `borrowBook(int recordNumber)`

Decrease the `quantity` of the record at position `recordNumber` by 1.

- If `quantity` is already `0`, print `"Out of stock."` and do not modify the file.
- **Do not rewrite the whole record.** Seek directly to the `quantity` field within the record and overwrite only those 4 bytes.

> **Think:** What is the byte offset of `quantity` within a single record?
> `id`(4) + `title`(40) + `author`(30) + `price`(8) = 82 → `quantity` starts at byte **82** within the record.
> So its absolute position = `(recordNumber - 1) * 86 + 82`.

---

## Part 7: Test in `LibraryManagementMain.java`

Write a `main` method that tests all parts in sequence:

```
1. Add 4 books:
   - id=1, title="Clean Code",          author="Robert Martin", price=350.0, quantity=5
   - id=2, title="The Pragmatic Programmer", author="David Thomas", price=420.0, quantity=3
   - id=3, title="Effective Java",       author="Joshua Bloch",  price=280.0, quantity=8
   - id=4, title="Head First Java",      author="Kathy Sierra",  price=190.0, quantity=6

2. readBook()           → prints record 1
3. readBook(3)          → prints record 3 (Effective Java)
4. searchByAuthor("joshua bloch") → prints Effective Java
5. searchByPriceRange(200, 400)   → prints Clean Code and Effective Java
6. borrowBook(3)        → quantity of Effective Java becomes 7
7. readBook(3)          → confirm quantity is now 7
8. borrowBook(4) six times → after the 6th call prints "Out of stock."
```

---

## Expected Output

```
1, Clean Code, Robert Martin, 350.0, 5
3, Effective Java, Joshua Bloch, 280.0, 8
3, Effective Java, Joshua Bloch, 280.0
1, Clean Code, Robert Martin, 350.0, 5
3, Effective Java, Joshua Bloch, 280.0
3, Effective Java, Joshua Bloch, 280.0, 7
Out of stock.
```

---

## Things to Think About

- Why must every String field be **exactly** N bytes — not more, not less?
- When you read a byte array back and convert it with `new String(bytes)`, why must you call `.trim()`?
- In `borrowBook`, why is it better to seek to just the `quantity` field rather than reading and rewriting the full record?
- What does `fpt.length() / 86` tell you, and when could it give a wrong answer?

---

## Bonus Challenges (Optional)

1. **`returnBook(int recordNumber)`** — increment `quantity` by 1. What is the maximum stock level you'd want to enforce?

2. **`updatePrice(int recordNumber, double newPrice)`** — seek directly to the `price` field and overwrite only those 8 bytes. What is the byte offset of `price` within a record?

3. **`deleteBook(int recordNumber)`** — overwrite the record's `id` field with `-1` to mark it as deleted. Modify `searchByAuthor` and `searchByPriceRange` to skip records where `id == -1`.

4. **`listAll()`** — read and print every non-deleted record in the file.
