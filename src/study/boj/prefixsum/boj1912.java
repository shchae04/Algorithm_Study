package study.boj.prefixsum;
import java.util.Scanner;

/**
 * 백준 1912번 - 연속합
 *
 * 문제: 연속 부분 배열의 최대 합을 구하는 문제
 *
 * 알고리즘: 누적합/카데인 아이디어
 * 시간복잡도: O(N)
 * 공간복잡도: O(N)
 */

public class boj1912 {
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