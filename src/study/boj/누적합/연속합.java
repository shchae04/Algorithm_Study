package study.boj.누적합;

import java.util.Scanner;

public class 연속합 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] prefixSum = new int[n + 1]; // 0 ~ n
        int[] arr = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            arr[i] = sc.nextInt();
            prefixSum[i] = prefixSum[i - 1] + arr[i];
        }

        int minPrefix = 0;
        int maxSum = Integer.MIN_VALUE;

        for (int i = 1; i <= n; i++) {
            int currentSum = prefixSum[i] - minPrefix;
            maxSum = Math.max(maxSum, currentSum);
            minPrefix = Math.min(minPrefix, prefixSum[i]);
        }

        System.out.println(maxSum);
    }
}