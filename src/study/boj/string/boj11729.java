package study.boj.string;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 백준 11729번 - 하노이 탑 이동 순서
 *
 * 문제: 세 개의 기둥과 N개의 원판이 있을 때, 모든 원판을 첫 번째 기둥에서 세 번째 기둥으로 옮기는 과정 출력
 * - 규칙 1: 한 번에 하나의 원판만 이동 가능
 * - 규칙 2: 큰 원판이 작은 원판 위에 올라갈 수 없음
 * - 규칙 3: 가장 적은 이동 횟수로 옮겨야 함
 *
 * 알고리즘: 재귀(Recursion)를 이용한 하노이 탑
 * - 분할 정복(Divide and Conquer) 방식으로 문제를 작은 단위로 나눔
 * - 큰 문제를 동일한 구조의 작은 문제들로 분해하여 해결
 *
 * 핵심 아이디어:
 * N개의 원판을 A 기둥에서 C 기둥으로 옮기는 과정 (B는 보조 기둥)
 * 1. N-1개의 원판을 A에서 B로 옮김 (C를 보조로 사용)
 *    - 이렇게 하면 가장 큰 원판이 A에 홀로 남게 됨
 * 2. 가장 큰 원판(N번째)을 A에서 C로 옮김
 *    - 이제 가장 큰 원판은 목적지에 도달
 * 3. N-1개의 원판을 B에서 C로 옮김 (A를 보조로 사용)
 *    - 작은 원판들을 가장 큰 원판 위로 옮김
 *
 * 예시: N=3일 때
 * 초기 상태: A[1,2,3], B[], C[] (1이 가장 작은 원판, 3이 가장 큰 원판)
 * 1. 원판 2개를 A→B: A[3], B[1,2], C[]
 * 2. 원판 1개를 A→C: A[], B[1,2], C[3]
 * 3. 원판 2개를 B→C: A[], B[], C[1,2,3]
 *
 * 재귀 호출 과정 상세 설명:
 * hanoi(from, middle, to, N)은 N개 원판을 from에서 to로 옮김 (middle은 보조)
 * - N=0: 옮길 원판이 없으므로 종료 (기저 조건)
 * - N≥1:
 *   1) hanoi(from, to, middle, N-1): N-1개를 from→middle로 (to를 보조로)
 *   2) from→to 출력: 가장 큰 원판을 목적지로
 *   3) hanoi(middle, from, to, N-1): N-1개를 middle→to로 (from을 보조로)
 *
 * 이동 횟수 공식: 2^N - 1
 * - N=1: 2^1-1 = 1번
 * - N=2: 2^2-1 = 3번
 * - N=3: 2^3-1 = 7번
 * - N=4: 2^4-1 = 15번
 * 증명: T(N) = T(N-1) + 1 + T(N-1) = 2*T(N-1) + 1
 *      T(1) = 1이므로, T(N) = 2^N - 1
 *
 * 시간복잡도: O(2^N)
 * - 재귀 호출이 지수적으로 증가
 * - N=20이면 약 100만 번의 이동
 * - 매우 비효율적이지만 하노이 탑의 최소 이동 횟수가 2^N-1이므로 최적
 *
 * 공간복잡도: O(N)
 * - 재귀 호출 스택의 깊이가 N
 * - 각 재귀 단계마다 스택 프레임 생성
 *
 * 주의사항:
 * - StringBuilder를 사용하여 출력 성능 최적화 (System.out.println 반복 호출 방지)
 * - N이 클수록 출력량이 기하급수적으로 증가 (N=20이면 약 100만 줄)
 */

public class boj11729 {
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 최소 이동 횟수 출력: 2^N - 1
        sb.append((int)Math.pow(2, N) - 1).append('\n');

        // 하노이 탑 이동 과정 수행
        // 1번 기둥(from)에서 3번 기둥(to)으로, 2번 기둥(middle)을 보조로 사용
        hanoi(1, 2, 3, N);

        System.out.println(sb);
    }

    /**
     * 하노이 탑 재귀 함수
     *
     * @param from   출발 기둥 번호
     * @param middle 중간(보조) 기둥 번호
     * @param to     목적지 기둥 번호
     * @param N      옮길 원판 개수
     *
     * 동작 과정:
     * 1. N-1개를 from→middle로 옮김 (to가 보조 역할)
     * 2. 가장 큰 원판(N번째)을 from→to로 옮김
     * 3. N-1개를 middle→to로 옮김 (from이 보조 역할)
     */
    static void hanoi(int from, int middle, int to, int N) {
        // 기저 조건: 옮길 원판이 없으면 종료
        if (N == 0) return;

        // 1단계: N-1개의 원판을 from에서 middle로 이동 (to를 보조로)
        // 이렇게 하면 가장 큰 원판(N번째)이 from에 홀로 남음
        hanoi(from, to, middle, N - 1);

        // 2단계: 가장 큰 원판을 from에서 to로 이동
        sb.append(from + " " + to + '\n');

        // 3단계: N-1개의 원판을 middle에서 to로 이동 (from을 보조로)
        // 작은 원판들을 가장 큰 원판 위로 옮김
        hanoi(middle, from, to, N - 1);
    }
}