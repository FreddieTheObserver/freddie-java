package linked_list.solutions.HospitalQueue;

public class HospitalQueueMain {
    public static void main (String[] args) {
        HospitalQueue queue = new HospitalQueue();

        // Add to Tail
        queue.addToTail(101, "John", 45, "Chest Pain", 1, "08:00");
        queue.addToTail(102, "Ann", 30, "Fever", 3, "08:15");
        queue.addToTail(103, "Saw", 22, "Fracture", 2, "08:20");

        System.out.println(queue.head.data.name);
        System.out.println(queue.tail.data.name);

        // Add to Head
        queue.addToHead(104, "Peter", 60, "Stroke", 1, "08:25");
        System.out.println(queue.head.data.name);

        // Delete from Head
        Patient seen = queue.deleteFromHead();
        System.out.println(seen.name);
        System.out.println(queue.head.data.name);

        // Delete from tail
        Patient left = queue.deleteFromTail();
        System.out.println(left.name);
        System.out.println(queue.tail.data.name);

        // Add more
        queue.addToTail(105, "Mary", 28, "Fever", 3, "08:40");
        queue.addToTail(106, "David", 55, "Chest Pain", 1, "08:45");

        // Search "ann"
        Patient foundAnn = queue.searchByName("ann");
        System.out.println(foundAnn != null ? foundAnn.id : "Not found");

        // Search "Nobody"
        Patient nobody = queue.searchByName("Nobody");
        System.out.println(nobody != null ? nobody.id : "Not found");

        // Counting Priority
        System.out.println(queue.countByPriority(1));
        System.out.println(queue.countByPriority(3));

        // Remove by ID
        Patient removed = queue.removeById(102);
        System.out.println(removed != null ? removed.name : "Not found");

        System.out.println("--- Final Queue ---");
        queue.displayQueue();
    }
}

class Patient {
    int id;
    String name;
    int age;
    String condition;
    int priority;
    String arrivalTime;

    public Patient(int iD, String name, int age, String condition, int priority, String arrivalTime) {
        id = iD;
        this.name = name;
        this.age = age;
        this.condition = condition;
        setPriority(priority);
        this.arrivalTime = arrivalTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int iD) {
        id = iD;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        if (priority >= 1 && priority <= 3) {
            this.priority = priority;
        }
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    @Override
    public String toString() {
        return String.format(
                "[%d] %s, %d, %s, Priority: %d, Arrived: %s",
                id, name, age, condition, priority, arrivalTime
        );
    }
}

class Node {
    Patient data;
    Node next;

    public Node(Patient data, Node next) {
        this.data = data;
        this.next = next;
    }
}

class HospitalQueue {
    Node head, tail;

    public HospitalQueue() {
        head = tail = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        int count = 0;

        Node temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }

        return count;
    }

    public void addToTail(int id, String name, int age, String condition, int priority, String arrivalTime) {
        Patient patient = new Patient(id, name, age, condition, priority, arrivalTime);

        if (isEmpty()) {
            head = tail = new Node(patient, null);
        } else {
            tail.next = new Node(patient, null);
            tail = tail.next;
        }
    }

    public void addToHead(int id, String name, int age, String condition, int priority, String arrivalTime) {
        Patient patient = new Patient(id, name, age, condition, priority, arrivalTime);

        head = new Node(patient, head);
        if (tail == null) {
            tail = head;
        }
    }

    public Patient deleteFromHead() {
        Patient patient = null;

        if (isEmpty()) {
            return patient;
        } else if (head == tail) {
            patient = head.data;
            head = tail = null;
        } else {
            patient = head.data;
            head = head.next;
        }
        return patient;
    }

    public Patient deleteFromTail() {
        Patient patient = null;

        if (isEmpty()) {
            return patient;
        } else if (head == tail) {
            patient = head.data;
            head = tail = null;
        } else {
            Node temp = head;

            while (temp.next != tail) {
                temp = temp.next;
            }

            patient = tail.data;
            temp.next = null;
            tail = temp;
        }
        return patient;
    }

    public Patient searchByName(String name) {
        if (isEmpty()) {
            return null;
        }

        Node temp = head;

        while (temp != null) {
            if ((temp.data.name).equalsIgnoreCase(name)) {
                return temp.data;
            }
            temp = temp.next;
        }
        return null;
    }

    public int getPosition(String name) {
        int index = 0;
        Node temp = head;

        while (temp != null) {
            if (temp.data.name.equalsIgnoreCase(name)) {
                return index;
            }
            temp = temp.next;
            index++;
        }

        return -1;
    }

    public int countByPriority(int priority) {
        int count = 0;
        Node temp = head;

        while (temp != null) {
            if (temp.data.priority == priority) {
                count++;
            }
            temp = temp.next;
        }
        return count;
    }

    public Patient removeById(int id) {
        if (isEmpty()) return null;

        Node prev = null;
        Node temp = head;

        while (temp != null) {
            if (temp.data.id == id) {
                if (prev == null) {
                    return deleteFromHead();
                } else {
                    prev.next = temp.next;

                    if (temp == tail) {
                        tail = prev;
                    }
                }
                return temp.data;
            }
            prev = temp;
            temp = temp.next;
        }
        return null;
    }

    private Node[] appendNode(Node bucketHead, Node bucketTail, Node node) {
        if (bucketHead == null) {
            bucketHead = bucketTail = node;
        } else {
            bucketTail.next = node;
            bucketTail = node;
        }
        return new Node[]{bucketHead, bucketTail};
    }

    public void sortByPriority() {
        if (head == null || head.next == null) return;

        Node p1Head = null, p1Tail = null;
        Node p2Head = null, p2Tail = null;
        Node p3Head = null, p3Tail = null;

        Node temp = head;
        while (temp != null) {
            Node next = temp.next;
            temp.next = null;

            int priority = temp.data.priority;
            Node[] updated;

            if (priority == 1) {
                updated = appendNode(p1Head, p1Tail, temp);
                p1Head = updated[0];
                p1Tail = updated[1];
            } else if (priority == 2) {
                updated = appendNode(p2Head, p2Tail, temp);
                p2Head = updated[0];
                p2Tail = updated[1];
            } else {
                updated = appendNode(p3Head, p3Tail, temp);
                p3Head = updated[0];
                p3Tail = updated[1];
            }
            temp = next;
        }

        head = (p1Head != null) ? p1Head : (p2Head != null) ? p2Head : p3Head;

        Node lastTail = null;
        if (p1Head != null) {
            lastTail = p1Tail;

            if (p2Head != null) {
                lastTail.next = p2Head;
                lastTail = p2Tail;
            }

            if (p3Head != null) {
                lastTail.next = p3Head;
                lastTail = p3Tail;
            }
        } else if (p2Head != null) {
            lastTail = p2Tail;
            if (p3Head != null) {
                lastTail.next = p3Head;
                lastTail = p3Tail;
            }
        } else {
            lastTail = p3Tail;
        }

        tail = lastTail;
    }

    public void displayQueue() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return;
        }

        Node temp = head;

        while (temp != null) {
            System.out.println(temp.data);
            temp = temp.next;
        }
    }
}