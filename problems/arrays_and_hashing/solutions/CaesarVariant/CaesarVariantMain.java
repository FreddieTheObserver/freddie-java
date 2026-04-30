package arrays_and_hashing.solutions.CaesarVariant;

import java.util.Scanner;

public class CaesarVariantMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        int k = scanner.nextInt();

        StringBuilder result = new StringBuilder();
        int shift = k;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                char shifted = (char) ((c - base + shift) % 26 + base);
                result.append(shifted);
                shift++;
            } else {
                result.append(c);
            }
        }

        System.out.println(result);
        scanner.close();
    }
}
