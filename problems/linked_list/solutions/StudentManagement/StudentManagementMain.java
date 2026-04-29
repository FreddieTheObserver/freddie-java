package linked_list.solutions.StudentManagement;

public class StudentManagementMain {
}

class Node {
    Student info;
    Node next;

    public Node(Student info, Node next) {
        this.info = info;
        this.next = next;
    }
}

class Student {
    int ID;
    String name;
    String gender;
    double gpa;
    String major;

    public Student(int iD, String name, String gender, double gpa, String major) {
        ID = iD;
        this.name = name;
        this.gender = gender;
        this.gpa = gpa;
        this.major = major;
    }

    public int getID() {
        return ID;
    }

    public void setID(int iD) {
        ID = iD;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        if (gpa >= 0 && gpa <= 4.0) {
            this.gpa = gpa;
        }
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}

class SLL {
    Node head, tail;

    public SLL() {
        head = tail = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void addToTail(int ID, String name, String gender, double gpa, String major) {
        Student stu = new Student(ID, name, gender, gpa, major);

        if (isEmpty()) {
            head = tail = new Node(stu, null);
        } else {
            tail.next = new Node(stu, null);
            tail = tail.next;
        }
    }

    public void addToHead(int ID, String name, String gender, double gpa, String major) {
        Student stu = new Student(ID, name, gender, gpa, major);

        head = new Node(stu, head);
        if (tail == null) {
            tail = head;
        }
    }

    public Student deleteFromHead() {
        Student st = null;

        if (isEmpty()) {
            return st;
        } else if (head == tail) {
            st = head.info;
            head = tail = null;
        } else {
            st = head.info;
            head = head.next;
        }
        return st;
    }

    public Student deleteFromTail() {
        Student st = null;

        if (isEmpty()) {
            return st;
        } else if (head == tail) {
            st = head.info;
            head = tail = null;
        } else {
            Node temp = head;

            while (temp.next != tail) {
                temp = temp.next;
            }
            st = tail.info;
            temp.next = null;
            tail = temp;
        }
        return st;
    }

    public Student searchByName(String name) {
        Node temp = head;
        while (temp != null) {
            if ((temp.info.name).equalsIgnoreCase(name)) {
                return temp.info;
            }
            temp = temp.next;
        }
        return null;
    }

    public int countByMajor(String major) {
        int count = 0;
        Node temp = head;

        while (temp != null) {
            if ((temp.info.major).equalsIgnoreCase(major)) {
                count++;
            }
            temp = temp.next;
        }
        return count;
    }
}

