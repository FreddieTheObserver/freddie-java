
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

//    public int size() {
//        return 0;
//    }

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
        int cur = 0;

        if (isEmpty()) {
            return cur;
        } else if (head == tail) {
            cur = head.val;
            head = tail = null;
        } else {
            cur = head.val;
            head = head.next;
        }
        return cur;
    }

    public int deleteFromTail() {
        int cur = 0;

        if (isEmpty()) {
            return cur;
        } else if (head == tail) {
            cur = head.val;
            head = tail = null;
        } else {
            ListNode temp = head;

            while (temp.next != tail) {
                temp = temp.next;
            }

            cur = tail.val;
            temp.next = null;
            temp = tail;
        }

        return cur;
    }
}