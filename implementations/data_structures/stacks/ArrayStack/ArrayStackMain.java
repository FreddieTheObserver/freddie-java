package data_structures.stacks.ArrayStack;

public class ArrayStackMain {
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack();

        stack.push(1);
        stack.push(2);
        stack.push(3);

        stack.print();                          // [3, 2, 1] ← top is leftmost

        System.out.println("peek: " + stack.peek());  // 3
        System.out.println("pop:  " + stack.pop());   // 3
        stack.print();                          // [2, 1] ← top is leftmost

        System.out.println("size: " + stack.size());  // 2
        System.out.println("pop:  " + stack.pop());   // 2
        System.out.println("pop:  " + stack.pop());   // 1
        System.out.println("isEmpty: " + stack.isEmpty()); // true
    }
}

class ArrayStack {
    int[] arr;
    int size;

    public ArrayStack() {
        arr = new int[10];
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void push(int val) {
        if (size == arr.length) resize();
        arr[size] = val;
        size++;
    }

    public int pop() {
        if (isEmpty()) throw new IllegalArgumentException("Stack is empty");
        size--;
        int val = arr[size];
        arr[size] = 0;
        return val;
    }

    public int peek() {
        if (isEmpty()) throw new IllegalArgumentException("Stack is empty");
        return arr[size - 1];
    }

    private void resize() {
        int[] bigger = new int[arr.length * 2];
        for (int i = 0; i < arr.length; i++) {
            bigger[i] = arr[i];
        }
        arr = bigger;
    }

    public void print() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = size - 1; i >= 0; i--) {
            sb.append(arr[i]);
            if (i > 0) sb.append(", ");
        }
        sb.append("] <- top is leftmost");
        System.out.println(sb.toString());
    }
}
