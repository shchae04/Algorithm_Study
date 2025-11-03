package study.boj.daily;

import java.util.*;

/**
 * 백준 14225번 - 부분수열의 합
 * <p>
 * 문제: N개의 수로 이루어진 수열에서 만들 수 없는 가장 작은 자연수를 구하기
 * - 부분수열을 선택하여 그 수들의 합을 만들 수 있음
 * - 공집합의 합은 0으로 정의
 * <p>
 * 알고리즘: 백트래킹(DFS)을 이용한 부분집합 생성
 * - 각 원소에 대해 "포함" 또는 "미포함" 두 가지 선택지를 재귀적으로 탐색
 * - 모든 가능한 부분수열의 합을 HashSet에 저장
 * - 1부터 순서대로 검사하여 만들 수 없는 첫 번째 자연수 찾기
 * <p>
 * 핵심 아이디어:
 * 1. DFS로 모든 부분수열 생성 (각 원소를 포함/제외하는 2가지 경우)
 * 2. 각 부분수열의 합을 HashSet에 저장 (중복 자동 제거)
 * 3. 1부터 순차적으로 증가하며 Set에 없는 첫 번째 수를 찾기
 * <p>
 * 시간복잡도: O(2^N + S)
 * - 2^N: 가능한 모든 부분집합 생성 (N ≤ 20이므로 최대 약 100만)
 * - S: 생성된 합의 개수 (최대 2^N개의 서로 다른 합)
 * <p>
 * 공간복잡도: O(2^N)
 * - HashSet에 저장되는 부분수열 합의 개수
 * - 재귀 호출 스택의 깊이 O(N)
 * <p>
 * 부분집합(Power Set) 문제의 전형적인 백트래킹 응용
 */

public class boj14225 {
    static HashSet<Integer> set = new HashSet<>();
    static int n;
    static int[] info;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        // 수열 입력 받기
        info = new int[n];
        for (int i = 0; i < n; i++) {
            info[i] = sc.nextInt();
        }

        // DFS로 모든 부분수열의 합 생성
        dfs(0, 0);

        // 1부터 시작하여 만들 수 없는 가장 작은 자연수 찾기
        // HashSet의 O(1) 조회를 활용하여 효율적으로 탐색
        int result = 1;
        while (set.contains(result)) {
            result++;
        }

        System.out.println(result);
    }

    /**
     * 백트래킹(DFS)으로 모든 가능한 부분수열의 합을 생성
     * <p>
     * @param index 현재 검사 중인 배열의 인덱스
     * @param currTotal 현재까지 선택한 원소들의 합
     * <p>
     * 동작 과정:
     * - 각 원소마다 2가지 선택: 포함 or 미포함
     * - 재귀적으로 모든 경우의 수 탐색
     * - 말단 노드(index == n)에 도달하면 현재 합을 Set에 저장
     * <p>
     * 예시) [5, 1, 2]인 경우:
     *       dfs(0, 0)
     *      /         \
     *   +5(0,5)    미포함(0,0)
     *   /    \       /      \
     * +1    미포함  +1      미포함
     * ...
     * 결과: {0, 1, 2, 3, 5, 6, 7, 8} → 4가 없으므로 답은 4
     */
    static void dfs(int index, int currTotal) {
        // 기저 조건: 모든 원소를 검사했으면 현재 합을 저장
        if (index == n) {
            set.add(currTotal);
            return;
        }

        // 현재 원소를 포함하는 경우
        dfs(index + 1, currTotal + info[index]);

        // 현재 원소를 포함하지 않는 경우
        dfs(index + 1, currTotal);
    }
}