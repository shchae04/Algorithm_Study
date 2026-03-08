package study.boj.dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class boj17268 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());

        // 문제 조건상 N은 짝수이지만, 예외 상황에 대비한 방어 코드
        // 사람 수가 홀수면 모두 짝지어 악수할 수 없으므로 경우의 수는 0
        if (n % 2 != 0) {
            System.out.println(0);
            return;
        }

        // 사람 2명이 1쌍이므로, 실제로는 "쌍의 개수" 기준으로 카탈란 수를 계산
        int pairs = n / 2;

        // dp[i] = i쌍이 서로 교차하지 않게 악수하는 경우의 수
        long[] dp = new long[pairs + 1];

        // 문제에서 요구하는 나머지 연산 값
        final long mod = 987654321L;

        // 기저 조건:
        // 0쌍일 때는 "아무것도 하지 않는 경우" 1가지로 본다
        dp[0] = 1;

        // 카탈란 수 점화식:
        // dp[i] = sum(dp[j] * dp[i - 1 - j])   (j = 0 ~ i - 1)
        //
        // 의미:
        // 한 쌍을 기준으로 전체를 왼쪽 구간 / 오른쪽 구간으로 나누면
        // 왼쪽 경우의 수 * 오른쪽 경우의 수를 모두 더한 값이 dp[i]
        //
        // [표로 보는 예시]
        // i = 1:
        // j=0 -> dp[0] * dp[0] = 1
        // dp[1] = 1
        //
        // i = 2:
        // j=0 -> dp[0] * dp[1] = 1
        // j=1 -> dp[1] * dp[0] = 1
        // dp[2] = 2
        //
        // i = 3:
        // j=0 -> dp[0] * dp[2] = 2
        // j=1 -> dp[1] * dp[1] = 1
        // j=2 -> dp[2] * dp[0] = 2
        // dp[3] = 5
        //
        // i = 4:
        // j=0 -> dp[0] * dp[3] = 5
        // j=1 -> dp[1] * dp[2] = 2
        // j=2 -> dp[2] * dp[1] = 2
        // j=3 -> dp[3] * dp[0] = 5
        // dp[4] = 14
        //
        // 즉, dp 값은 1, 1, 2, 5, 14, ... 형태로 커지며
        // 이는 대표적인 카탈란 수(Catalan Number) 점화식이다
        for (int i = 1; i <= pairs; i++) {
            long sum = 0;

            for (int j = 0; j < i; j++) {
                // 한 쌍을 기준으로 왼쪽 구간과 오른쪽 구간으로 나누었을 때
                // 각각의 경우의 수를 곱해서 전체 경우의 수에 더한다
                sum += (dp[j] * dp[i - 1 - j]) % mod;

                // 누적합이 너무 커지지 않도록 매 단계마다 모듈러 연산
                sum %= mod;
            }

            dp[i] = sum;
        }

        // n명(= pairs쌍)의 최종 경우의 수 출력
        System.out.println(dp[pairs]);
    }
}