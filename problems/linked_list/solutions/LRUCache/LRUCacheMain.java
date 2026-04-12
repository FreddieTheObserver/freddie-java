package linked_list.solutions.LRUCache;

import java.util.HashMap;

public class LRUCacheMain {
    public static void main(String[] args) {
        LRUCache cache = new LRUCache(3);

        cache.put(1, 10);
        cache.put(2, 20);
        cache.put(3, 30);

        System.out.println(cache.get(1));
        System.out.println(cache.get(99));

        cache.put(4, 40);
        System.out.println(cache.get(2));

        System.out.println(cache.get(3));

        cache.put(5, 50);
        System.out.println(cache.get(1));
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));
        System.out.println(cache.get(5));

        cache.put(3, 300);
        System.out.println(cache.get(3));

        LRUCache singleItemCache = new LRUCache(1);
        singleItemCache.put(1, 1);
        singleItemCache.put(2, 2);
        System.out.println(singleItemCache.get(1));
        System.out.println(singleItemCache.get(2));
    }
}

class CacheNode {
    int key;
    int value;
    CacheNode prev;
    CacheNode next;

    public CacheNode(int key, int value) {
        this.key = key;
        this.value = value;
        this.prev = null;
        this.next = null;
    }
}

class LRUCache {
    int capacity;
    HashMap<Integer, CacheNode> cache;
    CacheNode head, tail;

    public LRUCache(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than 0");
        }

        this.capacity = capacity;
        this.cache = new HashMap<>();
        // Sentinel nodes simplify insert/remove by avoiding null checks at boundaries.
        this.head = new CacheNode(0, 0);
        this.tail = new CacheNode(0, 0);

        this.head.next = this.tail;
        this.tail.prev = this.head;
    }

    public int get(int key) {
        if (cache.containsKey(key)) {
            CacheNode node = cache.get(key);
            // Access makes this key most recently used.
            moveToFront(node);
            return node.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            CacheNode node = cache.get(key);
            node.value = value;
            // Updating an existing key also refreshes its recency.
            moveToFront(node);
        } else {
            CacheNode newNode = new CacheNode(key, value);
            cache.put(key, newNode);
            // New keys are inserted as most recently used.
            addToFront(newNode);

            if (cache.size() > capacity) {
                // Remove least recently used node (right before tail sentinel).
                CacheNode lru = evictLast();
                cache.remove(lru.key);
            }
        }
    }

    public void addToFront(CacheNode node) {
        CacheNode first = head.next;

        // Insert node between head and current first real node.
        node.prev = head;
        node.next = first;

        head.next = node;
        first.prev = node;
    }

    public void removeNode(CacheNode node) {
        CacheNode prevNode = node.prev;
        CacheNode nextNode = node.next;

        // Bypass node to unlink it from the doubly linked list.
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
    }

    public void moveToFront(CacheNode node) {
        // Keep map entry, only reposition node in recency order.
        removeNode(node);
        addToFront(node);
    }

    public CacheNode evictLast() {
        CacheNode last = tail.prev;
        removeNode(last);
        return last;
    }
}
