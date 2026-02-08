package study.boj.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 11055번: 가장 큰 증가하는 부분 수열
 * 알고리즘: 다이나믹 프로그래밍 (DP)
 * 시간 복잡도: O(N^2)
 */
public class boj11055 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 수열의 크기 N

        int[] arr = new int[N]; // 수열 데이터를 저장할 배열
        int[] dp = new int[N];  // dp[i]: i번째 원소를 마지막으로 포함하는 증가하는 부분 수열의 최대 합

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            dp[i] = arr[i]; // 초기값은 자기 자신의 값 (길이가 1인 수열의 합)
        }

        int max = dp[0];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < i; j++) {
                // 1. 현재 원소(arr[i])가 이전 원소(arr[j])보다 커야 '증가' 조건 만족
                if (arr[j] < arr[i]) {
                    // 2. 기존 dp[i]와 'j를 마지막으로 하는 합 + 현재 원소' 중 큰 값으로 갱신
                    dp[i] = Math.max(dp[i], dp[j] + arr[i]);
                }
            }
            // 모든 원소를 돌며 전체 DP 테이블 중 최댓값을 찾음
            max = Math.max(max, dp[i]);
        }
        System.out.println(max); // 가장 큰 합 출력
    }
}
