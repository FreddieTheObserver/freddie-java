package data_structures.linked_lists;

import java.util.Arrays;

class ListNode {
    int val;
    ListNode next;

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

public class SinglyLinkedListMain {
    ListNode head;
    ListNode tail;

    public SinglyLinkedListMain() {
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

    public static void main(String[] args) {
        SinglyLinkedListMain list = new SinglyLinkedListMain();
        int[] values = {10, 20, 30, 40};

        for (int value : values) {
            list.addToTail(value);
        }

        System.out.println("Inserted values: " + Arrays.toString(values));
        System.out.println("List: " + Arrays.toString(toArray(list)));

        list.addToHead(5);
        list.addAt(1, 15);
        System.out.println("After addToHead(5) and addAt(1, 15): " + Arrays.toString(toArray(list)));

        System.out.println("Size: " + list.size());
        System.out.println("Head: " + list.peekHead());
        System.out.println("Tail: " + list.peekTail());
        System.out.println("Contains 20: " + list.contains(20));
        System.out.println("Contains 99: " + list.contains(99));
        System.out.println("Index of 30: " + list.find(30));
        System.out.println("Value at index 2: " + list.getAt(2));

        System.out.println("deleteFromHead(): " + list.deleteFromHead());
        System.out.println("deleteAt(1): " + list.deleteAt(1));
        System.out.println("deleteFromTail(): " + list.deleteFromTail());
        System.out.println("Final list: " + Arrays.toString(toArray(list)));
    }

    private static int[] toArray(SinglyLinkedListMain list) {
        int[] values = new int[list.size()];
        ListNode current = list.head;
        int index = 0;

        while (current != null) {
            values[index++] = current.val;
            current = current.next;
        }

        return values;
    }
}
