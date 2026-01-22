package study.boj.dp;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 백준 2747번 - 피보나치 수
 * 
 * 문제: n번째 피보나치 수 구하기
 * 
 * 알고리즘: 동적 계획법 (Bottom-up)
 * - dp[0] = 0, dp[1] = 1
 * - dp[i] = dp[i-1] + dp[i-2] (i >= 2)
 * - 메모이제이션으로 중복 계산 방지
 * 
 * 시간복잡도: O(N)
 * 공간복잡도: O(N)
 */
public class boj2747 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] dp = new int[46];
        dp[1] = 1;

        for(int i=2; i<=n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        System.out.println(dp[n]);
    }
}
