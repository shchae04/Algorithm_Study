package study.boj.dp;
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

    /**
     * 바텀업(Bottom-Up) 방식: 반복문을 이용한 DP
     *
     * 동작 방식:
     * - 작은 문제(dp[0], dp[1])부터 시작하여 큰 문제(dp[N])까지 순차적으로 해결
     * - 모든 부분 문제를 미리 계산하여 테이블을 채움
     * - 이전에 계산한 값을 바로 참조 (dp[i-j*j])
     *
     * 실행 순서 예시 (N=12):
     * dp[0]=0 → dp[1]=1 → dp[2]=2 → dp[3]=3 → dp[4]=1 → ... → dp[12]=3
     *
     * 장점:
     * - 반복문 사용으로 스택 오버플로우 위험 없음
     * - 함수 호출 오버헤드가 없어 일반적으로 더 빠름
     * - 실행 흐름이 명확하고 디버깅이 쉬움
     *
     * 단점:
     * - 필요하지 않은 모든 부분 문제도 계산
     * - 메모리를 처음부터 N+1 크기만큼 모두 사용
     */
//    private static int find(int N) {
//        // 2부터 N까지 순차적으로 계산
//        for (int i = 2; i <= N; i++) {
//            // 최악의 경우로 초기화 (모두 1²로 표현)
//            dp[i] = i;
//            // i보다 작거나 같은 모든 제곱수 시도
//            for (int j = 1; j * j <= i; j++) {
//                // 이미 계산된 dp[i - j*j] 값을 활용
//                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
//            }
//        }
//        return dp[N];
//    }

    /**
     * 탑다운(Top-Down) 방식: 재귀 + 메모이제이션
     *
     * 동작 방식:
     * - 큰 문제(dp[N])부터 시작하여 필요한 작은 문제들을 재귀적으로 호출
     * - 계산된 값은 메모이제이션하여 중복 계산 방지
     * - 필요한 부분 문제만 계산 (Lazy Evaluation)
     *
     * 실행 순서 예시 (N=12):
     * find(12) → find(11), find(8), find(3) → find(10), find(7), ... → find(0)
     * (실제로는 중복 호출을 메모이제이션으로 방지)
     *
     * 장점:
     * - 필요한 부분 문제만 계산하므로 효율적일 수 있음
     * - 점화식을 그대로 코드로 옮긴 형태라 직관적
     * - 문제를 자연스럽게 분할하여 해결
     *
     * 단점:
     * - 재귀 호출로 인한 함수 호출 오버헤드
     * - 깊은 재귀 시 스택 오버플로우 위험 (N이 매우 큰 경우)
     * - 디버깅이 상대적으로 어려움
     *
     * 시간복잡도: O(N * √N) - 바텀업과 동일
     * 공간복잡도: O(N) - dp 배열 + 재귀 스택 공간
     */
    private static int find(int N) {
        // 메모이제이션: 이미 계산된 값이 있으면 바로 반환
        if (dp[N] != null) {
            return dp[N];
        }

        // 최악의 경우로 초기화: 모두 1²로 표현하는 경우
        // 예: 12 = 1+1+1+1+1+1+1+1+1+1+1+1 (12개)
        dp[N] = N;

        // N보다 작거나 같은 모든 제곱수를 시도
        for (int j = 1; j * j <= N; j++) {
            // 재귀 호출: N에서 j²를 뺀 나머지의 최솟값을 구함
            // dp[N] = min(dp[N-1²]+1, dp[N-2²]+1, dp[N-3²]+1, ...)
            dp[N] = Math.min(dp[N], find(N - j * j) + 1);
        }

        return dp[N];
    }
}
