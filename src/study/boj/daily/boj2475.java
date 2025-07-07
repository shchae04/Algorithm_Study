package study.boj.daily;

import java.util.Scanner;

public class boj2475 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int preSum = 0;

        for (int i = 0; i < 5; i++) {
            int t = sc.nextInt();
            preSum += t * t;
        }

        System.out.println(preSum % 10);


    }
}
