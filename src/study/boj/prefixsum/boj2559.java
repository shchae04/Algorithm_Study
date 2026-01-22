package study.boj.prefixsum;
import java.util.Scanner;

/**
 * 백준 2559번 - 수열
 *
 * 문제: 연속 K개의 합 중 최대값을 구하는 문제
 *
 * 알고리즘: 누적합/슬라이딩 윈도우
 * 시간복잡도: O(N)
 * 공간복잡도: O(N)
 */

public class boj2559 {

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
