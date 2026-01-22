package study.boj.dp;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 백준 9095번 - 1, 2, 3 더하기
 * 
 * 문제: n을 1, 2, 3의 합으로 나타내는 방법의 수 구하기
 * 
 * 알고리즘: 동적 계획법
 * - dp[i] = dp[i-1] + dp[i-2] + dp[i-3]
 * - 마지막에 1을 더하는 경우: dp[i-1]
 * - 마지막에 2를 더하는 경우: dp[i-2]
 * - 마지막에 3을 더하는 경우: dp[i-3]
 * 
 * 시간복잡도: O(N)
 * 공간복잡도: O(N)
 */
public class boj9095 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        int[] dp = new int[11];

        dp[1] = 1; // 1 -> 1
        dp[2] = 2; // 11 2 -> 2
        dp[3] = 4; // 111 12 21 3 -> 4
        for (int i = 4; i <= 10; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }

        for (int i = 0; i < t; i++) {
            int num = Integer.parseInt(br.readLine());
            System.out.println(dp[num]);
        }
    }
}
