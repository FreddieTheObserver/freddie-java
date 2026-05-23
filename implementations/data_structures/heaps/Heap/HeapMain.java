package data_structures.heaps.Heap;

import java.util.Arrays;

public class HeapMain {
    public static void main(String[] args) {
        System.out.println("=== MinHeap ===");
        MinHeap minHeap = new MinHeap();
        int[] minValues = {15, 10, 20, 8, 12, 25, 6};
        for (int v : minValues) {
            minHeap.insert(v);
        }
        minHeap.print(); // min at index 0

        System.out.println("peek: " + minHeap.peek());       // 6
        System.out.println("extractMin: " + minHeap.extractMin()); // 6
        minHeap.print();

        System.out.println("delete index 1: " + minHeap.delete(1));
        minHeap.print();

        System.out.println("=== MaxHeap ===");
        MaxHeap maxHeap = new MaxHeap();
        for (int v : minValues) {
            maxHeap.insert(v);
        }
        maxHeap.print();

        System.out.println("extractMax: " + maxHeap.extractMax()); // 25
        maxHeap.print();

        System.out.println("=== buildHeap (heapify) ===");
        int[] raw = {4, 10, 3, 5, 1};
        MinHeap built = MinHeap.buildHeap(raw.clone());
        System.out.println("input:  " + Arrays.toString(raw));
        System.out.println("heap:   " + Arrays.toString(built.toArray()));

        System.out.println("=== heapSort ===");
        int[] unsorted = {4, 10, 3, 5, 1, 7, 2};
        System.out.println("before: " + Arrays.toString(unsorted));
        System.out.println("after:  " + Arrays.toString(MaxHeap.heapSort(unsorted.clone())));

        System.out.println("=== in-place heapify + heapSort (array) ===");
        int[] inPlace = {9, 4, 7, 1, 3, 6, 2};
        System.out.println("before: " + Arrays.toString(inPlace));
        MaxHeap.heapSortInPlace(inPlace);
        System.out.println("after:  " + Arrays.toString(inPlace));

        System.out.println("=== PriorityQueue ===");
        PriorityQueue pq = new PriorityQueue();
        pq.offer(30);
        pq.offer(10);
        pq.offer(20);
        pq.offer(5);
        pq.print();

        System.out.println("peek: " + pq.peek());   // 5
        System.out.println("poll: " + pq.poll());     // 5
        System.out.println("poll: " + pq.poll());     // 10
        pq.print();
        System.out.println("size: " + pq.size());   // 2
    }
}

class MinHeap {
    int[] arr;
    int size;

    public MinHeap() {
        arr = new int[10];
        size = 0;
    }

    public MinHeap(int capacity) {
        arr = new int[capacity];
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public int peek() {
        if (isEmpty()) throw new IllegalArgumentException("Heap is empty");
        return arr[0];
    }

    public void insert(int val) {
        if (size == arr.length) resize();
        arr[size] = val;
        size++;
        heapifyUp(size - 1);
    }

    public int extractMin() {
        if (isEmpty()) throw new IllegalArgumentException("Heap is empty");
        int min = arr[0];
        arr[0] = arr[size - 1];
        arr[size - 1] = 0;
        size--;
        if (size > 0) heapifyDown(0);
        return min;
    }

    public int delete(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        int removed = arr[index];
        arr[index] = arr[size - 1];
        arr[size - 1] = 0;
        size--;
        if (index < size) {
            int parent = (index - 1) / 2;
            if (index > 0 && arr[index] < arr[parent]) {
                heapifyUp(index);
            } else {
                heapifyDown(index);
            }
        }
        return removed;
    }

    public static MinHeap buildHeap(int[] values) {
        MinHeap heap = new MinHeap(Math.max(values.length, 1));
        System.arraycopy(values, 0, heap.arr, 0, values.length);
        heap.size = values.length;
        for (int i = heap.size / 2 - 1; i >= 0; i--) {
            heap.heapifyDown(i);
        }
        return heap;
    }

    public int[] toArray() {
        return Arrays.copyOf(arr, size);
    }

    private void heapifyUp(int i) {
        while (i > 0) {
            int parent = (i - 1) / 2;
            if (arr[i] >= arr[parent]) break;
            swap(i, parent);
            i = parent;
        }
    }

    private void heapifyDown(int i) {
        while (true) {
            int smallest = i;
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if (left < size && arr[left] < arr[smallest]) smallest = left;
            if (right < size && arr[right] < arr[smallest]) smallest = right;
            if (smallest == i) break;

            swap(i, smallest);
            i = smallest;
        }
    }

    private void resize() {
        arr = Arrays.copyOf(arr, arr.length * 2);
    }

    private void swap(int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public void print() {
        System.out.println(Arrays.toString(toArray()) + " (index 0 = min)");
    }
}

class MaxHeap {
    int[] arr;
    int size;

    public MaxHeap() {
        arr = new int[10];
        size = 0;
    }

    public MaxHeap(int capacity) {
        arr = new int[capacity];
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public int peek() {
        if (isEmpty()) throw new IllegalArgumentException("Heap is empty");
        return arr[0];
    }

    public void insert(int val) {
        if (size == arr.length) resize();
        arr[size] = val;
        size++;
        heapifyUp(size - 1);
    }

    public int extractMax() {
        if (isEmpty()) throw new IllegalArgumentException("Heap is empty");
        int max = arr[0];
        arr[0] = arr[size - 1];
        arr[size - 1] = 0;
        size--;
        if (size > 0) heapifyDown(0);
        return max;
    }

    public int delete(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        int removed = arr[index];
        arr[index] = arr[size - 1];
        arr[size - 1] = 0;
        size--;
        if (index < size) {
            int parent = (index - 1) / 2;
            if (index > 0 && arr[index] > arr[parent]) {
                heapifyUp(index);
            } else {
                heapifyDown(index);
            }
        }
        return removed;
    }

    public static MaxHeap buildHeap(int[] values) {
        MaxHeap heap = new MaxHeap(Math.max(values.length, 1));
        System.arraycopy(values, 0, heap.arr, 0, values.length);
        heap.size = values.length;
        for (int i = heap.size / 2 - 1; i >= 0; i--) {
            heap.heapifyDown(i);
        }
        return heap;
    }

    public static int[] heapSort(int[] arr) {
        int[] copy = arr.clone();
        heapSortInPlace(copy);
        return copy;
    }

    public static void heapSortInPlace(int[] arr) {
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
        for (int i = n - 1; i > 0; i--) {
            swap(arr, 0, i);
            heapify(arr, i, 0);
        }
    }

    public static void heapify(int[] arr, int heapSize, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < heapSize && arr[left] > arr[largest]) largest = left;
        if (right < heapSize && arr[right] > arr[largest]) largest = right;

        if (largest != i) {
            swap(arr, i, largest);
            heapify(arr, heapSize, largest);
        }
    }

    public int[] toArray() {
        return Arrays.copyOf(arr, size);
    }

    private void heapifyUp(int i) {
        while (i > 0) {
            int parent = (i - 1) / 2;
            if (arr[i] <= arr[parent]) break;
            swap(i, parent);
            i = parent;
        }
    }

    private void heapifyDown(int i) {
        while (true) {
            int largest = i;
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if (left < size && arr[left] > arr[largest]) largest = left;
            if (right < size && arr[right] > arr[largest]) largest = right;
            if (largest == i) break;

            swap(i, largest);
            i = largest;
        }
    }

    private void resize() {
        arr = Arrays.copyOf(arr, arr.length * 2);
    }

    private void swap(int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public void print() {
        System.out.println(Arrays.toString(toArray()) + " (index 0 = max)");
    }
}

class PriorityQueue {
    private final MinHeap heap;

    public PriorityQueue() {
        heap = new MinHeap();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public int size() {
        return heap.size();
    }

    public void offer(int val) {
        heap.insert(val);
    }

    public int poll() {
        return heap.extractMin();
    }

    public int peek() {
        return heap.peek();
    }

    public void print() {
        heap.print();
    }
}
