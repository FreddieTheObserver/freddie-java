package data_structures.linked_lists;

class ListNode {
    int val;
    ListNode next;

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

public class SLL {
    ListNode head;
    ListNode tail;

    public SLL() {
        head = tail = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        int count = 0;
        ListNode cur = head;

        while (cur != null) {
            count++;
            cur = cur.next;
        }

        return count;
    }

    public int peekHead() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Cannot peek head of an empty list.");
        }

        return head.val;
    }

    public int peekTail() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Cannot peek tail of an empty list.");
        }

        return tail.val;
    }

    public boolean contains(int val) {
        ListNode cur = head;

        while (cur != null) {
            if (cur.val == val) {
                return true;
            }
            cur = cur.next;
        }

        return false;
    }

    public int find(int val) {
        ListNode cur = head;
        int index = 0;

        while (cur != null && index < size()) {
            if (cur.val == val) {
                return index;
            }
            cur = cur.next;
            index++;
        }

        return -1;
    }

    public void addToHead(int val) {
        head = new ListNode(val, head);
        if (tail == null) {
            tail = head;
        }
    }

    public void addToTail(int val) {
        if (isEmpty()) {
            head = tail = new ListNode(val, null);
        } else {
            tail.next = new ListNode(val, null);
            tail = tail.next;
        }
    }

    public int deleteFromHead() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Cannot delete head of an empty list.");
        } else if (head == tail) {
            int cur = head.val;
            head = tail = null;
            return cur;
        } else {
            int cur = head.val;
            head = head.next;
            return cur;
        }
    }

    public int deleteFromTail() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Cannot delete tail of an empty list.");
        } else if (head == tail) {
            int cur = head.val;
            head = tail = null;
            return cur;
        } else {
            ListNode temp = head;

            while (temp.next != tail) {
                temp = temp.next;
            }

            int cur = tail.val;
            temp.next = null;
            tail = temp;
            return cur;
        }
    }

    public int getAt(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Index cannot be negative.");
        }

        ListNode cur = head;
        int i = 0;

        while (cur != null) {
            if (i == index) {
                return cur.val;
            }
            cur = cur.next;
            i++;
        }

        throw new IndexOutOfBoundsException("Index out of range: " + index);
    }

    public void addAt(int index, int val) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Index cannot be negative.");
        }

        int n = size();
        if (index > n) {
            throw new IndexOutOfBoundsException("Index out of range: " + index);
        }

        if (index == 0) {
            addToHead(val);
            return;
        }

        if (index == n) {
            addToTail(val);
            return;
        }

        ListNode prev = head;
        for (int i = 0; i < index - 1; i++) {
            prev = prev.next;
        }

        prev.next = new ListNode(val, prev.next);
    }

    public int deleteAt(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Index cannot be negative.");
        }

        int n = size();
        if (index >= n) {
            throw new IndexOutOfBoundsException("Index out of range: " + index);
        }

        if (index == 0) {
            return deleteFromHead();
        }

        if (index == n - 1) {
            return deleteFromTail();
        }

        ListNode prev = head;
        for (int i = 0; i < index - 1; i++) {
            prev = prev.next;
        }

        int removed = prev.next.val;
        prev.next = prev.next.next;
        return removed;
    }
}