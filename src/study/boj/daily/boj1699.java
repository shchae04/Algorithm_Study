package study.boj.daily;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 백준 1699번 - 제곱수의 합
 *
 * 문제: 자연수 N을 제곱수의 합으로 나타낼 때, 그 항의 최소 개수 구하기
 *
 * 알고리즘: 동적 계획법(Dynamic Programming)
 * - 작은 수부터 차례대로 최소 항의 개수를 계산
 * - 각 수에 대해 가능한 모든 제곱수를 빼본 후 최솟값 선택
 * - 이전에 계산한 결과를 활용하여 중복 계산 방지
 *
 * 핵심 아이디어:
 * 1. dp[i] = i를 제곱수의 합으로 나타낼 때 필요한 최소 항의 개수
 * 2. 초기값: dp[i] = i (모두 1^2으로 표현하는 최악의 경우)
 * 3. 점화식: dp[i] = min(dp[i], dp[i - j^2] + 1) (j^2 <= i인 모든 j에 대해)
 * 4. i에서 어떤 제곱수 j^2를 빼고, 나머지 (i - j^2)의 최솟값에 1을 더함
 *
 * 시간복잡도: O(N * √N)
 * - 2부터 N까지의 각 수(N개)에 대해
 * - 1부터 √i까지의 제곱수(√N개)를 확인
 *
 * 공간복잡도: O(N)
 * - dp 배열에 N+1개의 원소 저장
 *
 * 예시: N = 12
 * - 12 = 4 + 4 + 4 (3개 항)
 * - 12 = 9 + 1 + 1 + 1 (4개 항)
 * - 답: 3 (최소 항의 개수)
 */

public class boj1699 {

    static Integer[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        dp = new Integer[N + 1];
        dp[0] = 0;
        dp[1] = 1;

        System.out.println(find(N));
    }

    private static int find(int N) {
        for (int i = 2; i <= N; i++) {
            dp[i] = i;
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);

            }
        }
        return dp[N];
    }
}
