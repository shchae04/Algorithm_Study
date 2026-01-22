package study.boj.dp;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 9184번 - 신나는 함수 실행
 *
 * 문제: 복잡한 재귀 함수 w(a, b, c)의 값을 효율적으로 계산하는 문제
 *
 * 알고리즘: 다이나믹 프로그래밍 (Dynamic Programming), 메모이제이션 (Memoization)
 * - 재귀 함수 호출 시 중복되는 연산을 줄이기 위해 이미 계산된 값을 저장하고 재사용
 * - 입력값 a, b, c가 특정 범위를 벗어나는 경우를 기저 사례로 처리하여 효율성 증대
 *
 * 핵심 아이디어:
 * 1. 기저 사례 및 범위 처리:
 *    - a, b, c 중 하나라도 0 이하이면 1을 반환
 *    - a, b, c 중 하나라도 20을 초과하면 w(20, 20, 20)을 호출하여 범위를 20 이하로 제한
 * 2. 메모이제이션 (Memoization):
 *    - 0 < a, b, c <= 20 범위의 결과값을 3차원 배열 dp[21][21][21]에 저장
 *    - dp 배열에 값이 존재하면(0이 아니면) 재귀 호출 없이 저장된 값을 반환하여 중복 계산 방지
 * 3. 점화식 구현:
 *    - 문제에서 제시된 조건(a < b < c 등)에 따라 다른 점화식을 적용
 *    - 재귀 호출 결과를 dp 배열에 저장하면서 진행
 *
 * 시간복잡도: O(1) (입력 제한 고려 시)
 * - 유효한 입력값 범위가 0~20으로 제한적이므로, 최대 21^3개의 상태만 계산하면 됨
 * - 실제로는 O(K^3) (K=20)
 *
 * 공간복잡도: O(1)
 * - 21 x 21 x 21 크기의 고정된 3차원 배열 사용
 */
public class boj9184 {
    static int[][][] dp = new int[21][21][21];

    public static void main(String[] args) throws Exception {
        int a, b, c;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine(), " ");
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            if (a == -1 && b == -1 && c == -1) break;
            long result = dynamicW(a, b, c);
            sb.append("w(").append(a).append(", ").append(b).append(", ").append(c).append(") = ").append(result).append("\n");
        }

        System.out.println(sb);
        br.close();
    }

    static int w(int a, int b, int c) {
        if (a <= 0 || b <= 0 || c <= 0) return 1;
        if (a > 20 || b > 20 || c > 20) return w(20, 20, 20);
        if (a < b && b < c) return w(a, b, c - 1) + w(a, b - 1, c - 1) - w(a, b - 1, c);
        return w(a - 1, b, c) + w(a - 1, b - 1, c) + w(a - 1, b, c - 1) - w(a - 1, b - 1, c - 1);
    }

    static int dynamicW(int a, int b, int c) {
        if (a <= 0 || b <= 0 || c <= 0) return 1;
        if (a > 20 || b > 20 || c > 20) return dynamicW(20, 20, 20);
        if (dp[a][b][c] > 0) return dp[a][b][c]; // 저장된 값이 있을 경우
        if (a < b && b < c) {
            dp[a][b][c] = dynamicW(a, b, c - 1) + dynamicW(a, b - 1, c - 1) - dynamicW(a, b - 1, c);
            return dp[a][b][c];
        }
        dp[a][b][c] = dynamicW(a - 1, b, c) + dynamicW(a - 1, b - 1, c) + dynamicW(a - 1, b, c - 1) - dynamicW(a - 1, b - 1, c - 1);
        return dp[a][b][c];
    }
}
