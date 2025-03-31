package study.boj.two;

import java.util.Scanner;

public class 대회or인턴 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); //남
        int m = sc.nextInt(); //여
        int k = sc.nextInt(); //k명 인턴쉽으로 대회에 못나가는 인원.

        // 6 3 2
        // 2 - 1 팀이 2개, 2명이 인턴쉽.

        int count = 0; // 팀 수
        for (int t = 0; t <= Math.min(n / 2, m); t++) {
            int girl = n - 2 * t;
            int men = m - t;

            if (girl + men >= k) {
                count = t;
            }
        }
        System.out.println(count);

    }
}
