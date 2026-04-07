package linked_list.solutions.ReverseLinkedList;
import java.util.ArrayList;

public class ReverseLinkedListMain {
    public static void main(String[] args) {
        // 1. Create and display
        LinkedList<Integer> list = new LinkedList<>();
        list.addToTail(1);
        list.addToTail(2);
        list.addToTail(3);
        list.addToTail(4);
        list.addToTail(5);
        System.out.print("1. Original:       ");
        list.display();
        System.out.println();

        // 2. Reverse and display
        list.reverse();
        System.out.print("2. Reversed:       ");
        list.display();
        System.out.println();

        // 3. Reverse again and display
        list.reverse();
        System.out.print("3. Reversed again: ");
        list.display();
        System.out.println();

        // 4. Single element
        LinkedList<Integer> single = new LinkedList<>();
        single.addToTail(42);
        single.reverse();
        System.out.print("4. Single element: ");
        single.display();
        System.out.println();

        // 5. Empty list
        LinkedList<Integer> empty = new LinkedList<>();
        empty.reverse();
        System.out.print("5. Empty list:     ");
        empty.display();
        System.out.println();
    }
}

class Node<T> {
    T data;
    Node<T> next;

    public Node (T data) {
        this.data = data;
        this.next = null;
    }
}

class LinkedList<T> {
    Node<T> head, tail;

    public LinkedList() {
        head = tail = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void addToTail(T data) {
        if (isEmpty()) {
            head = tail = new Node<T>(data);
        } else {
            tail.next = new Node<T>(data);
            tail = tail.next;
        }
    }

    public void display() {
        Node<T> temp = head;

        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.print("null");
    }

    public ArrayList<T> toArray() {
        ArrayList<T> list = new ArrayList<>();
        Node<T> temp = head;

        while (temp != null) {
            list.add(temp.data);
            temp = temp.next;
        }
        return list;
    }

    public void reverse() {
        Node<T> prev = null;
        Node<T> curr = head;
        tail = head;

        while (curr != null) {
            Node<T> temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }
        head = prev;
    }
}