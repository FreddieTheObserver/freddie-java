# Caesar Variant — Progressive Shift Cipher

> **Solution:** [../solutions/CaesarVariant/](../solutions/CaesarVariant/)

## Problem Description

Encrypt a string using a **progressive Caesar cipher**.

A standard Caesar cipher shifts every letter by the same amount `k`. In this variant, the shift **grows by 1 for each letter encountered**:

- The 1st letter is shifted by `k`.
- The 2nd letter is shifted by `k + 1`.
- The 3rd letter is shifted by `k + 2`.
- …and so on.

### Rules

1. **Letters only**: the shift applies to A-Z and a-z. Case is preserved.
2. **Non-letters pass through**: spaces, digits, punctuation, etc., are appended unchanged and **do not advance the shift counter**.
3. **Wrap around**: the alphabet wraps modulo 26 (e.g., `'z'` shifted by 1 becomes `'a'`).

---

## Input Format

```
<string>
<k>
```

- Line 1: the plaintext string (may contain spaces and punctuation).
- Line 2: integer `k >= 0`, the initial shift.

---

## Output Format

The encrypted string on a single line.

---

## Examples

### Example 1
**Input**
```
abc
1
```
**Output**
```
bdf
```
**Trace**
| char | shift | result |
|------|-------|--------|
| `a`  | 1     | `b`    |
| `b`  | 2     | `d`    |
| `c`  | 3     | `f`    |

### Example 2
**Input**
```
Hello, World!
3
```
**Output**
```
Kiqrv, Exbwp!
```
**Trace** — note that `,`, ` `, and `!` are appended unchanged and do **not** increment the shift.

| char | shift | result |
|------|-------|--------|
| `H`  | 3     | `K`    |
| `e`  | 4     | `i`    |
| `l`  | 5     | `q`    |
| `l`  | 6     | `r`    |
| `o`  | 7     | `v`    |
| `,`  | —     | `,`    |
| ` `  | —     | ` `    |
| `W`  | 8     | `E`    |
| `o`  | 9     | `x`    |
| `r`  | 10    | `b`    |
| `l`  | 11    | `w`    |
| `d`  | 12    | `p`    |
| `!`  | —     | `!`    |

---

## Constraints

- `0 <= k <= 1000`
- `1 <= input length <= 10^5`
- Input contains only printable ASCII characters.

---

## Hint

Use modular arithmetic to wrap around the alphabet:

```
shifted = (c - base + shift) % 26 + base
```

where `base` is `'A'` for uppercase letters and `'a'` for lowercase.

Track a running `shift` counter, starting at `k`. Increment it **only** when you actually shift a letter — non-letter characters must not bump it.
