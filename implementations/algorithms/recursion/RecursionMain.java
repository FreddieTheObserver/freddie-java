package algorithms.recursion;

public class RecursionMain {
    public static void main (String[] args) {
        System.out.println("2^3 : " + exponential(2, 3));

        System.out.print("backwards 5 : ");
        printBackwards(5);
        System.out.println();

        System.out.print("print 5 : ");
        print(5);
        System.out.println();

        System.out.print("print odd ascending 11 : ");
        printOddAscending(11);
        System.out.println();

        System.out.println("fac 16 : " + factorial(16));
        System.out.println("fibo 10 : " + fibonacci(10));
        System.out.println("fibo 5 (iterative) : " + fibonacciIterative(5));
        System.out.println("divide 10 by 3 : " + divide(10, 3));
        System.out.println("multiply 5 by 3 : " + multiply(5, 3));
        System.out.println("average of 1..67 : " + average(67));
        System.out.println("cotot is palindrome : " + isPalindrome("cotot"));
        System.out.println("replace pi in 'api' : " + replacePi("api"));
        System.out.println("sum of digits of 1729 : " + sumOfDigits(1729));
    }

    public static int exponential(int a, int b) {
        if (b == 0) return 1; // a^0 = 1
        if (a == 0) return 0; // 0^b = 0
        return a * exponential(a, b - 1);
    }

    public static void printBackwards(int n) {
        if (n <= 0) return;
        System.out.print(n + " ");
        printBackwards(n - 1);
    }

    public static void print(int n) {
        if (n <= 0) return;
        print(n - 1);
        System.out.print(n + " ");
    }

    public static void printOddAscending(int n) {
        if (n <= 0) return;
        printOddAscending(n - 1);
        if (n % 2 == 1) {
            System.out.print(n + " ");
        }
    }

    public static long factorial(long n) {
        if (n < 0) throw new IllegalArgumentException("factorial undefined for negative");
        if (n <= 1) return 1;

        return n * factorial(n - 1);
    }

    public static long fibonacci(long n) {
        if (n < 0) throw new IllegalArgumentException("fibonacci undefined for negative");
        if (n <= 1) return n;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public static long fibonacciIterative(long n) {
        if (n < 0) throw new IllegalArgumentException("fibonacci undefined for negative");
        if (n <= 1) return n;

        long a = 0, b = 1;
        for (long i = 2; i <= n; i++) {
            long next = a + b;
            a = b;
            b = next;
        }
        return b;
    }

    public static int divide(int a, int b) {
        if (b == 0) throw new ArithmeticException("divided by zero");
        if (a < b) return 0;
        return 1 + divide(a - b, b);
    }

    public static int multiply(int a, int b) {
        if (a == 0 || b == 0) return 0;
        return a + multiply(a, b - 1);
    }

    public static double average(int n) {
        if (n <= 0) throw new IllegalArgumentException("n must be positive");
        if (n == 1) return 1;
        return ((n - 1) * average(n - 1) + n) / n;
    }

    public static boolean isPalindrome(String s) {
        if (s.length() <= 1) return true;
        if (s.charAt(0) != s.charAt(s.length() - 1)) return false;
        return isPalindrome(s.substring(1, s.length() - 1));
    }

    public static String replacePi(String s) {
        if (s.length() <= 1) return s;
        if (s.startsWith("pi")) {
            return "3.14" + replacePi(s.substring(2));
        }
        return s.charAt(0) + replacePi(s.substring(1));
    }

    public static int  sumOfDigits(int n) {
        if (n < 0) throw new IllegalArgumentException("n must be non-negative");
        if (n < 10) return n;
        return (n % 10) + sumOfDigits(n / 10);
    }
}
