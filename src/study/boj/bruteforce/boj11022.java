package study.boj.bruteforce;

import java.util.Scanner;

public class boj11022 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = scanner.nextInt();

        for (int i = 1; i <= count; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            System.out.println("Case #" + i + ": " + a + " + " + b + " = " + (a + b));
        }

    }
}