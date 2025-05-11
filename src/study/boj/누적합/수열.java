package study.boj.누적합;

import java.util.Scanner;

public class 수열 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); //전체 날짜
        int k = sc.nextInt(); //연속적인 날짜.
        int[] arr = new int[n];
        int[] preSum = new int[n + 1];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
            preSum[i + 1] = preSum[i] + arr[i];
        }

        int max = Integer.MIN_VALUE;
        for (int i = k; i <= n; i++) {
            max = Math.max(max, preSum[i] - preSum[i - k]);
        }

        System.out.println(max);
    }
}
