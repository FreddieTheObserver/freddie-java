package oop.solutions.GymManagement;
import java.util.*;

public class GymManagementMain {
    public static void main(String[] args) {
        Trainer t1 = new Trainer(201, "Alex", 35, "Male");
        t1.addSpecialization("Strength Training");
        t1.addSpecialization("HIIT");
        t1.setSessionFee(5000);
        System.out.println(t1);

        WorkoutPlan p1 = new WorkoutPlan("Weight Loss", 8, 3000);
        p1.addEquipment("Treadmill");
        p1.addEquipment("Dumbbells");

        Member m1 = new Member("Basic",   301, "Jake",  17, "Male");
        Member m2 = new Member("Premium", 302, "Lisa",  30, "Female");
        Member m3 = new Member("VIP",     303, "Granny",70, "Female");

        // Session 1 — Basic, age 17
        Session s1 = new Session(t1, m1, p1);
        System.out.println(s1.getFinalCharge());
        s1.chargeSession();
        System.out.println(m1.getUnPaid());

        m1.refundSession(s1);
        System.out.println(m1.getUnPaid());

        m1.refundSession(s1);
        System.out.println(m1.getUnPaid());

        // Session 2 — same member, charged again
        Session s2 = new Session(t1, m1, p1);
        System.out.println(s2.getFinalCharge());
        s2.chargeSession();
        System.out.println(m1.getUnPaid());

        // m1 pays 6400
        m1.pay(6400);
        System.out.println(m1.getUnPaid());

        // Session 3 — Premium, age 30
        Session s3 = new Session(t1, m2, p1);
        System.out.println(s3.getFinalCharge());
        s3.chargeSession();
        System.out.println(m2.getUnPaid());

        // Session 4 — VIP, age 70
        Session s4 = new Session(t1, m3, p1);
        System.out.println(s4.getFinalCharge());
        s4.chargeSession();
        System.out.println(m3.getUnPaid());

        // Loyalty discount test — charge m2 (Premium) 5 times, 6th should be cheaper
        Member m4 = new Member("Basic", 304, "Tom", 25, "Male");
        double normalCharge = new Session(t1, m4, p1).getFinalCharge();
        System.out.println("Normal charge: " + normalCharge);

        for (int i = 1; i <= 5; i++) {
            Session sx = new Session(t1, m4, p1);
            sx.chargeSession();
            System.out.println("After session " + i + ", unpaid: " + m4.getUnPaid() + ", charge was: " + sx.getFinalCharge());
        }

        // Session 6 — should be 10% cheaper
        Session s6 = new Session(t1, m4, p1);
        System.out.println("Session 6 charge (10% less = " + (normalCharge * 0.90) + "): " + s6.getFinalCharge());
    }
}

class Person {
    private int id;
    private String name;
    private int age;
    private String gender;

    public Person() {
        this.id = 0;
        this.name = "";
        this.age = 0;
        this.gender = "";
    }

    public Person(int id, String name, int age, String gender) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Person{id=" + id + ", name=" + name + ", age=" + age + ", gender=" + gender + "}";
    }
}

class Trainer extends Person {
    private Vector<String> specializations;
    private double sessionFee;

    public Trainer(int id, String name, int age, String gender) {
        super(id, name, age, gender);
        specializations = new Vector<>();
        sessionFee = 0;
    }

    public int addSpecialization(String s) {
        specializations.add(s);
        return specializations.size();
    }

    public double getSessionFee() {
        return sessionFee;
    }

    public void setSessionFee(double sessionFee) {
        this.sessionFee = sessionFee;
    }

    @Override
    public String toString() {
        return super.toString() + ". Trainer specializations: " + specializations + ", sessionFee=" + sessionFee;
    }
}

class Member extends Person {
    private String membershipType;
    private double unpaid;
    private int sessionCount;
    private double discountMultiplier = 1.0;

    public Member(String membershipType, int id, String name, int age, String gender) {
        super(id, name, age, gender);
        this.membershipType = membershipType;
        this.unpaid = 0;
        this.sessionCount = 0;
    }

    public String getMemberShipType() {
        return membershipType;
    }

    public void setMemberShipType(String membershipType) {
        this.membershipType = membershipType;
    }

    public double getUnPaid() {
        return unpaid;
    }

    public int getSessionCount() { return sessionCount; }

    public void setSessionCount(int sessionCount) {
        this.sessionCount = sessionCount;
    }

    public void charged(double amount) {
        unpaid += amount;
    }

    public void pay(double payment) {
        unpaid -= payment;
        if (unpaid < 0) unpaid = 0;
    }

    public void applyLoyaltyDiscount(int sessionsCompleted) {
        if (sessionsCompleted % 5 == 0) {
            discountMultiplier *= 0.90;
        }
    }

    public double getDiscountMultiplier() {
        return discountMultiplier;
    }

    public void refundSession(Session s) {
        unpaid -= s.getFinalCharge();
        if (unpaid < 0) unpaid = 0;
    }

    @Override
    public String toString() {
        return super.toString() + ". MembershipType=" + membershipType + ", unpaid=" + unpaid;
    }
}

class WorkoutPlan {
    private String planName;
    private int durationWeeks;
    private double planFee;
    private Vector<String> equipments;

    public WorkoutPlan (String planName, int durationWeeks, double planFee) {
        this.planName = planName;
        this.durationWeeks = durationWeeks;
        this.planFee = planFee;
        equipments = new Vector<>();
    }

    public int addEquipment(String item) {
        equipments.add(item);
        return equipments.size();
    }

    public double getPlanFee() {
        return planFee;
    }

    @Override
    public String toString() {
        return "WorkoutPlan{planName=" + planName + ", durationWeeks=" + durationWeeks + ", planFee=" + planFee + ", equipment=" + equipments + "}";
    }
}

class Session {
    private Trainer trainer;
    private Member member;
    private WorkoutPlan plan;
    private double finalCharge;
    private boolean chargedAlready;

    public Session(Trainer trainer, Member member, WorkoutPlan plan) {
        this.trainer = trainer;
        this.member = member;
        this.plan = plan;
        this.finalCharge = computeTotalCharge();
        this.chargedAlready = false;
    }

    public double computeTotalCharge() {
        double total = trainer.getSessionFee() + plan.getPlanFee();

        if (member.getMemberShipType().equals("VIP")) {
            total *= 0.60;
        } else if (member.getMemberShipType().equals("Premium")) {
            total *= 0.75;
        }

        if (member.getAge() <= 18 || member.getAge() >= 65) {
            total *= 0.80;
        }

        total *= member.getDiscountMultiplier();
        return total;
    }

    public void chargeSession() {
        if (!chargedAlready) {
            member.charged(finalCharge);
            member.setSessionCount(member.getSessionCount() + 1);
            member.applyLoyaltyDiscount(member.getSessionCount());
            chargedAlready = true;
        }
    }

    public double getFinalCharge() {
        return finalCharge;
    }

    @Override
    public String toString() {
        return "Session{finalCharge=" + finalCharge + ", chargedAlready=" + chargedAlready + "}";
    }
}

