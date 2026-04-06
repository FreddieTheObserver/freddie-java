package linked_list.solutions.ReverseLinkedList;
import java.util.ArrayList;

public class ReverseLinkedListMain {
    public static void main(String[] args) {

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
}