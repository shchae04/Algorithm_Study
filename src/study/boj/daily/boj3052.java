package study.boj.daily;

import java.util.Scanner;

/**
 * 나머지
 */
public class boj3052 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arr = new int[42];
        for (int i = 0; i < 10; i++) {
            int n = sc.nextInt();
            int remainder = n % 42;
            arr[remainder]++;
        }
        int distinct = 0;
        for (int i = 0; i < 42; i++) {
            if (arr[i] > 0) distinct++;
        }
        System.out.println(distinct);
        sc.close();
    }
}