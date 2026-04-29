package data_structures.queues.ArrayQueue;

public class ArrayQueueMain {
    public static void main(String[] args) {
        ArrayQueue q = new ArrayQueue();

        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.print();                              // [1, 2, 3] <- front is leftmost

        System.out.println("peek: " + q.peek());      // 1
        System.out.println("dequeue: " + q.dequeue()); // 1
        q.print();                              // [2, 3] <- front is leftmost

        System.out.println("size: " + q.size());       // 2
        System.out.println("dequeue: " + q.dequeue()); // 2
        System.out.println("dequeue: " + q.dequeue()); // 3
        System.out.println("isEmpty: " + q.isEmpty()); // true
    }
}

class ArrayQueue {
    int[] arr;
    int front;
    int rear;
    int size;

    public ArrayQueue() {
        arr = new int [10];
        front = 0;
        rear = 0;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void resize() {
        int[] bigger = new int[arr.length * 2];
        for (int i = 0; i < size; i++) {
            bigger[i] = arr[(front + i) % arr.length];
        }
        arr = bigger;
        front = 0;
        rear = size;
    }

    public void enqueue(int val) {
        if (size == arr.length) resize();
        arr[rear] = val;
        rear = (rear + 1) % arr.length;
        size++;
    }

    public int dequeue() {
        if (isEmpty()) throw new IllegalArgumentException("Queue is empty");
        int val = arr[front];
        arr[front] = 0;
        front = (front + 1) % arr.length;
        size--;
        return val;
    }


    public int peek() {
        if (isEmpty()) throw new IllegalArgumentException("Queue is empty");
        return arr[front];
    }

    public void print() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(arr[(front + i) % arr.length]);
            if (i < size - 1) sb.append(", ");
        }
        sb.append("] <- front is leftmost");
        System.out.println(sb.toString());
    }
}
