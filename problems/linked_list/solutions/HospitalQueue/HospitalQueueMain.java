package linked_list.solutions.HospitalQueue;

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
        this.priority = priority;
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
                    deleteFromHead();
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
}