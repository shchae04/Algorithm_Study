package study.boj.daily;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 백준 1234번 - 크리스마스 트리
 *
 * 문제: N개의 레벨로 구성된 트리에 빨강(r), 초록(g), 파랑(b) 색 사탕을 배치하는 경우의 수 구하기
 * - 각 레벨 i에는 정확히 i개의 사탕을 배치해야 함
 * - 각 색상별로 사용 가능한 사탕의 개수가 제한됨
 * - 같은 색 사탕은 구분하지 않음 (조합 문제)
 *
 * 알고리즘: 동적 프로그래밍(DP) + 조합론(Combinatorics)
 * - 메모이제이션을 활용한 Top-down DP
 * - 각 레벨에서 사탕을 1가지, 2가지, 3가지 색으로 나눠 배치
 * - 조합 공식을 미리 계산하여 중복 계산 방지
 *
 * 핵심 아이디어:
 * 1. 상태 정의: solve(N, r, g, b) = N번째 레벨부터 1번째 레벨까지 배치하는 경우의 수
 * 2. 각 레벨에서 가능한 색상 조합 패턴:
 *    - 1가지 색: 모든 N개를 한 색으로 칠함
 *    - 2가지 색: N이 짝수일 때, N/2개씩 두 색으로 칠함 (조합 C(N, N/2) 적용)
 *    - 3가지 색: N이 3의 배수일 때, N/3개씩 세 색으로 칠함 (조합 C(N, N/3) × C(N-N/3, N/3) 적용)
 * 3. Base Case:
 *    - 음수 사탕: 불가능하므로 0 반환
 *    - N=0: 모든 레벨을 배치했으므로 1 반환 (성공)
 * 4. 조합 계산: 파스칼의 삼각형을 이용해 미리 계산 (C(n,k) = C(n-1,k-1) + C(n-1,k))
 *
 * 시간복잡도: O(N × R × G × B)
 * - 4차원 DP 배열의 모든 상태를 최대 한 번씩만 계산
 * - N ≤ 10, R,G,B ≤ 100이므로 약 10^7 연산
 *
 * 공간복잡도: O(N × R × G × B)
 * - 4차원 메모이제이션 배열: color[11][101][101][101]
 * - 조합 배열: dp_combination[11][11]
 *
 * 특이사항:
 * - 정적 초기화 블록을 사용하여 팩토리얼과 조합 값을 프로그램 시작 시 미리 계산
 * - 메모이제이션 배열을 -1로 초기화하여 계산 여부 판별
 */

public class boj1234 {
    // color 배열을 -1로 초기화하기 위해 long으로 선언
    static long[][][][] color = new long[11][101][101][101];
    static long[] dp_factorial = new long[11];
    static long[][] dp_combination = new long[11][11];

    // 정적 초기화 블록: 팩토리얼, 조합, DP 테이블을 미리 초기화
    static {
        // 1. color 배열을 -1로 초기화 (계산되지 않은 상태)
        for (int n = 0; n < 11; n++) {
            for (int r = 0; r < 101; r++) {
                for (int g = 0; g < 101; g++) {
                    Arrays.fill(color[n][r][g], -1);
                }
            }
        }

        // 2. 팩토리얼 계산
        dp_factorial[0] = 1;
        for (int i = 1; i <= 10; i++) {
            dp_factorial[i] = dp_factorial[i - 1] * i;
        }

        // 3. 조합 계산: 파스칼의 삼각형 C(n, k) = C(n-1, k-1) + C(n-1, k)
        for (int n = 0; n <= 10; n++) {
            for (int k = 0; k <= n; k++) {
                if (k == 0 || k == n) {
                    dp_combination[n][k] = 1;
                } else {
                    dp_combination[n][k] = dp_combination[n - 1][k - 1] + dp_combination[n - 1][k];
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int g = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        System.out.println(solve(N, r, g, b));
    }

    static long solve(int N, int r, int g, int b) {
        // 1. Base Case
        if (r < 0 || g < 0 || b < 0) {
            return 0;
        }
        if (N <= 0) {
            return 1;
        }

        // 2. Memoization: -1이 아니면 이미 계산된 값
        if (color[N][r][g][b] != -1) {
            return color[N][r][g][b];
        }

        // 3. Recursive Case
        long result = 0;

        // 1가지 색상을 사용할 경우 (모든 N개를 한 색상에 사용)
        result += solve(N - 1, r - N, g, b);
        result += solve(N - 1, r, g - N, b);
        result += solve(N - 1, r, g, b - N);

        // 2가지 색상을 사용할 경우 (각 count = N/2개씩 사용) -> N이 2의 배수인 경우만
        if (N % 2 == 0) {
            int count = N / 2;
            long comb = combination(N, count);
            result += solve(N - 1, r - count, g - count, b) * comb;
            result += solve(N - 1, r, g - count, b - count) * comb;
            result += solve(N - 1, r - count, g, b - count) * comb;
        }

        // 3가지 색상을 사용할 경우 (각 count = N/3개씩 사용) -> N이 3의 배수인 경우만
        if (N % 3 == 0) {
            int count = N / 3;
            // N개 중에서 count개를 선택하고, 남은 N-count개 중에서 count개를 선택
            long comb = combination(N, count) * combination(N - count, count);
            result += solve(N - 1, r - count, g - count, b - count) * comb;
        }

        // 결과 저장 및 반환
        color[N][r][g][b] = result;
        return result;
    }

    // 미리 계산된 배열에서 값을 조회하는 함수
    static long combination(int n, int r) {
        if (r < 0 || r > n) return 0;
        return dp_combination[n][r];
    }
}
