package data_structures.linked_lists.DoubleLinkedList;

public class DoublyLinkedListMain {

}

class Node {
    int val;
    Node next;
    Node prev;

    public Node(int val, Node next, Node prev) {
        this.val = val;
        this.next = next;
        this.prev = prev;
    }
}

class DoublyLinkedList {
    Node head;
    Node tail;
    int size;

    public DoublyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public int peekHead() {
        if (isEmpty()) throw new IllegalArgumentException("List is empty");
        return head.val;
    }

    public int peekTail() {
        if (isEmpty()) throw new IllegalArgumentException("List is empty");
        return tail.val;
    }

    public void addToHead(int val) {
        if (isEmpty()) {
            head = tail = new Node(val, null, null);
        } else {
            Node node = new Node(val, head, null);
            head.prev = node;
            head = node;
        }
        size++;
    }

    public void addToTail(int val) {
        if (isEmpty()) {
            head = tail = new Node(val, null, null);
        } else {
            Node node = new Node(val, null, tail);
            tail.next = node;
            tail = node;
        }
        size++;
    }

    public void addAt(int index, int val) {
        if (index < 0 || index > size) throw new IllegalArgumentException("Index out of bound");
        if (index == 0) {
            addToHead(val);
            return;
        }

        if (index == size) {
            addToTail(val);
            return;
        }

        Node cur = head;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }

        Node newNode = new Node(val, cur, cur.prev);
        cur.prev.next = newNode;
        cur.prev = newNode;
        size++;
    }

    public int deleteFromHead() {
        if (isEmpty()) throw new IllegalArgumentException("List is empty");
        int val = head.val;
        head = head.next;

        if (head == null) {
            tail = null;
        } else {
            head.prev = null;
        }
        size--;
        return val;
    }

    public int deleteFromTail() {
        if (isEmpty()) throw new IllegalArgumentException("List is empty");
        int val = tail.val;
        tail = tail.prev;

        if (tail == null) {
            head = null;
        } else {
            tail.next = null;
        }
        size--;
        return val;
    }

    public int deleteAt(int index) {
        if (index < 0 || index > size) throw new IllegalArgumentException("Out of bound");
        if (index == 0) {
            return deleteFromHead();
        }

        if (index == size - 1) {
            return deleteFromTail();
        }

        Node curr = head;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }

        curr.prev.next = curr.next;
        curr.next.prev = curr.prev;
        size--;
        return curr.val;
    }

    public boolean deleteVal(int val) {
        Node cur = head;
        while (cur != null) {
            if (cur.val == val) {
                if (cur == head) {
                    deleteFromHead();
                } else if (cur == tail) {
                    deleteFromTail();
                } else {
                    cur.prev.next = cur.next;
                    cur.next.prev = cur.prev;
                    size--;
                }
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    public int getAt(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Index out of bound");
        }
        Node curr;
        if (index < size / 2) {
            curr = head;
            for (int i = 0; i < index; i++) curr = curr.next;
        } else {
            curr = tail;
            for (int i = size - 1; i > index; i--) curr = curr.prev;
        }
        return curr.val;
    }

    public int find(int val) {
        Node curr = head;
        int index = 0;
        while (curr != null) {
            if (curr.val == val) {
                return index;
            }
            curr = curr.next;
            index++;
        }
        return -1;
    }

    public void reverse() {
        Node curr = head;
        while (curr != null) {
            Node temp = curr.next;
            curr.next = curr.prev;
            curr.prev = temp;
            curr = temp;
        }
        Node oldHead = head;
        head = tail;
        tail = oldHead;
    }
}
