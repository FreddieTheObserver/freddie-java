# Gym Management System — OOP

> **Solution:** [../solutions/GymManagement/](../solutions/GymManagement/)

## Problem Description

A fitness gym needs a billing system to manage trainers, members, workout plans, and training sessions. You will model this system using **inheritance**, **composition**, and **encapsulation**.

---

## Part 1: Define the `Person` class

Create a `Person` class with the following fields:

| Field    | Type     | Description            |
|----------|----------|------------------------|
| `id`     | `int`    | Unique person ID       |
| `name`   | `String` | Full name              |
| `age`    | `int`    | Age in years           |
| `gender` | `String` | "Male" / "Female"      |

- Write a **default constructor** that sets all fields to zero/empty.
- Write a **parameterized constructor**.
- Write getters for all fields.
- Override `toString()` to return: `Person{id=..., name=..., age=..., gender=...}`

---

## Part 2: Define the `Trainer` class (extends `Person`)

| Field           | Type             | Description                          |
|-----------------|------------------|--------------------------------------|
| `specializations` | `Vector<String>` | List of workout specializations     |
| `sessionFee`    | `double`         | Fee charged per session              |

- Constructor accepts `id`, `name`, `age`, `gender`.
- `addSpecialization(String s)` — adds to the list, returns the new size.
- Getters and setter for `sessionFee`.
- Override `toString()` to append specializations and sessionFee to `super.toString()`.

---

## Part 3: Define the `Member` class (extends `Person`)

| Field          | Type     | Description                                  |
|----------------|----------|----------------------------------------------|
| `membershipType` | `String` | e.g. `"Basic"`, `"Premium"`, `"VIP"`      |
| `unpaid`       | `double` | Running balance of unpaid charges            |

- Constructor accepts `membershipType`, `id`, `name`, `age`, `gender`.
- `charged(double amount)` — adds `amount` to `unpaid`.
- `pay(double payment)` — subtracts from `unpaid`; floor at 0.
- Getters/setters for `membershipType`.
- Getter for `unpaid`.
- Override `toString()` to append membershipType and unpaid to `super.toString()`.

---

## Part 4: Define the `WorkoutPlan` class

| Field           | Type             | Description                              |
|-----------------|------------------|------------------------------------------|
| `planName`      | `String`         | Name of the plan (e.g. "Weight Loss")   |
| `durationWeeks` | `int`            | How many weeks the plan lasts           |
| `planFee`       | `double`         | Base cost of the plan                   |
| `equipment`     | `Vector<String>` | List of required equipment names        |

- Constructor accepts `planName`, `durationWeeks`, `planFee`.
- `addEquipment(String item)` — adds to the list, returns the new size.
- Getter for `planFee`.
- Override `toString()` to show all fields.

---

## Part 5: Define the `Session` class

A `Session` combines a `Trainer`, a `Member`, and a `WorkoutPlan`.

| Field            | Type          | Description                          |
|------------------|---------------|--------------------------------------|
| `trainer`        | `Trainer`     | The trainer leading this session     |
| `member`         | `Member`      | The member attending                 |
| `plan`           | `WorkoutPlan` | The workout plan being followed      |
| `finalCharge`    | `double`      | Computed total cost                  |
| `chargedAlready` | `boolean`     | Prevents double-charging             |

### Constructor

Accepts a `Trainer`, `Member`, and `WorkoutPlan`. Immediately calls `computeTotalCharge()` to set `finalCharge`. Sets `chargedAlready = false`.

### `computeTotalCharge()`

```
total = trainer.sessionFee + plan.planFee
```

Then apply a **membership discount** based on `member.membershipType`:

| Membership | Multiplier |
|------------|-----------|
| `"VIP"`    | × 0.60    |
| `"Premium"`| × 0.75    |
| `"Basic"`  | × 1.00    |

Then apply an **age discount** on top:

| Age range  | Multiplier |
|------------|-----------|
| ≤ 18       | × 0.80    |
| ≥ 65       | × 0.80    |
| Otherwise  | × 1.00    |

Return the final value and store it in `finalCharge`.

### `chargeSession()`

If `chargedAlready` is `false`, call `member.charged(finalCharge)` and set `chargedAlready = true`. Otherwise do nothing.

### Getters: `getFinalCharge()`

### Override `toString()` to show `finalCharge` and `chargedAlready`.

---

## Part 6: Test in `GymManagementMain.java`

Write a `main` method that produces the following sequence:

```
1. Create trainer t1: id=201, name="Alex", age=35, gender="Male"
   Add specializations: "Strength Training", "HIIT"
   Set sessionFee = 5000

2. Print t1

3. Create plan p1: planName="Weight Loss", durationWeeks=8, planFee=3000
   Add equipment: "Treadmill", "Dumbbells"

4. Create members:
   m1: membershipType="Basic",   id=301, name="Jake",  age=17, gender="Male"
   m2: membershipType="Premium", id=302, name="Lisa",  age=30, gender="Female"
   m3: membershipType="VIP",     id=303, name="Granny",age=70, gender="Female"

5. Session 1 — t1, m1 (Basic, age 17), p1
   Print finalCharge   → expected: 6400.0
   chargeSession()
   Print m1.unpaid     → expected: 6400.0

6. Session 2 — t1, m1, p1 (charged again)
   Print finalCharge   → expected: 6400.0
   chargeSession()
   Print m1.unpaid     → expected: 12800.0

7. m1 pays 6400
   Print m1.unpaid     → expected: 6400.0

8. Session 3 — t1, m2 (Premium, age 30), p1
   Print finalCharge   → expected: 6000.0
   chargeSession()
   Print m2.unpaid     → expected: 6000.0

9. Session 4 — t1, m3 (VIP, age 70), p1
   Print finalCharge   → expected: 3840.0
   chargeSession()
   Print m3.unpaid     → expected: 3840.0
```

---

## Expected Output

```
Person{id=201, name=Alex, age=35, gender=Male}. Trainer specializations: [Strength Training, HIIT], sessionFee=5000.0
6400.0
6400.0
6400.0
12800.0
6400.0
6000.0
6000.0
3840.0
3840.0
```

---

## Charge Calculation Walkthrough

| Session | Trainer Fee | Plan Fee | Base  | Membership | After M | Age | Final  |
|---------|------------|---------|-------|------------|---------|-----|--------|
| s1 (Basic, 17) | 5000 | 3000 | 8000 | ×1.00 | 8000 | ×0.80 | **6400** |
| s3 (Premium, 30) | 5000 | 3000 | 8000 | ×0.75 | 6000 | ×1.00 | **6000** |
| s4 (VIP, 70)    | 5000 | 3000 | 8000 | ×0.60 | 4800 | ×0.80 | **3840** |

---

## Bonus Challenges (Optional)

1. **`refundSession(Session s)`** — Add a method to `Member` that reverses a charge from a specific session (subtract `s.getFinalCharge()` from `unpaid`; floor at 0).

2. **`applyLoyaltyDiscount(int sessionsCompleted)`** — After every 5 sessions a member completes, apply a flat 10% reduction to all future `finalCharge` calculations.

3. **`Gym` class** — Create a `Gym` class that holds a list of `Member` objects and a list of `Session` objects. Add:
   - `enrollMember(Member m)`
   - `bookSession(Trainer t, Member m, WorkoutPlan p)` — creates a `Session`, charges the member, and records the session
   - `getTotalRevenue()` — sum of all `finalCharge` values across all sessions
   - `getMemberSummary(int memberId)` — print how many sessions a member has had and their current `unpaid` balance
