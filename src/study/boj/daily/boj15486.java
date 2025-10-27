package study.boj.daily;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 15486번 - 퇴사 2
 *
 * 문제: N일 동안 진행할 수 있는 상담 중에서 겹치지 않는 상담들을 선택하여 최대 수익을 얻기
 * - 각 상담은 시작일(i)에 소요 기간(t[i])과 수익(p[i])이 주어짐
 * - 상담이 이전 상담과 겹치면 두 상담을 모두 할 수 없음
 *
 * 알고리즘: 동적계획법(Dynamic Programming)
 * - 뒤에서 앞으로 진행하는 역방향 DP
 * - dp[i] = i번째 상담부터 시작하여 N일까지 얻을 수 있는 최대 수익
 * - 각 상담마다 두 가지 선택: 상담을 하거나 하지 않거나
 *
 * 핵심 아이디어:
 * 1. i번째 상담을 하는 경우: i번째 상담의 수익 + dp[endDay] (endDay = i + t[i])
 * 2. i번째 상담을 하지 않는 경우: dp[i+1]
 * 3. 두 경우 중 최댓값을 선택: dp[i] = max(dp[i+1], dp[endDay] + p[i])
 * 4. 상담이 N일을 초과하면 할 수 없으므로 dp[i] = dp[i+1]
 *
 * 시간복잡도: O(N) - N개의 상담에 대해 한 번씩만 순회
 * - 각 상담마다 상수 시간의 연산 수행
 *
 * 공간복잡도: O(N)
 * - 상담 정보와 DP 배열을 위한 공간 필요
 * - 입력 크기에 비례한 메모리 사용
 *
 * 상담 일정 최적화 문제의 전형적인 동적계획법 응용 사례
 */

public class boj15486 {
    // Bottom-Up 방식 (현재 사용)
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] t = new int[N + 2];
        int[] p = new int[N + 2];
        int[] dp = new int[N + 2];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            t[i] = Integer.parseInt(st.nextToken());
            p[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = N; i >= 1; i--) {
            int endDay = i + t[i];
            if (endDay > N + 1) {
                dp[i] = dp[i + 1];
            } else {
                dp[i] = Math.max(dp[i + 1], dp[endDay] + p[i]);
            }
        }
        System.out.println(dp[1]);
    }

    /*
    // Top-Down 방식 (재귀 + 메모이제이션)
    static int[] t, p, memo;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        t = new int[N + 2];
        p = new int[N + 2];
        memo = new int[N + 2];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            t[i] = Integer.parseInt(st.nextToken());
            p[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i <= N + 1; i++) {
            memo[i] = -1;
        }

        System.out.println(solve(1));
    }

    static int solve(int day) {
        if (day > N) return 0;
        if (memo[day] != -1) return memo[day];

        int endDay = day + t[day];
        int result;
        if (endDay > N + 1) {
            result = solve(day + 1);
        } else {
            result = Math.max(solve(day + 1), solve(endDay) + p[day]);
        }

        return memo[day] = result;
    }
    */
}