package study.boj.daily;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 19949번 - 영재의 시험
 *
 * 문제: 5지선다 10문제에서 정답이 주어질 때, 연속 3개를 같은 번호로 찍지 않으면서
 *       5개 이상 맞힐 수 있는 경우의 수를 구하기
 *
 * 알고리즘: 백트래킹(Backtracking)
 * - 가능한 모든 답안 조합을 생성하되, 제약 조건을 만족하지 않는 경우 가지치기
 * - 각 문제마다 1~5번 중 하나를 선택하며, 연속 3개가 같으면 해당 경로 배제
 * - 10문제를 모두 선택한 후 정답 개수를 세어 5개 이상이면 카운트
 *
 * 핵심 아이디어:
 * 1. 깊이 우선 탐색(DFS)으로 모든 가능한 답안 조합 생성 (depth 0~9)
 * 2. 각 depth에서 1~5번 답을 선택할 때 제약 조건 확인
 *    - depth >= 2일 때, 이전 2개와 현재 선택이 모두 같으면 skip
 * 3. depth == 10에 도달하면 정답과 비교하여 5개 이상 맞으면 카운트
 *
 * 시간복잡도: O(5^10) - 최악의 경우
 * - 각 문제마다 최대 5개의 선택지 존재
 * - 10문제이므로 이론적으로 5^10 = 9,765,625개의 조합
 * - 실제로는 연속 3개 같은 답 제약으로 많은 경우가 가지치기되어 더 적음
 *
 * 공간복잡도: O(10)
 * - 재귀 호출 스택의 최대 깊이: 10 (문제 개수)
 * - 배열 answer, youngjae 각각 크기 10
 *
 * 백트래킹의 전형적인 문제로, 제약 조건을 이용한 가지치기를 통해 탐색 공간 축소
 */

public class boj19949 {

    static int[] answer;
    static int[] youngjae;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        answer = new int[10];
        youngjae = new int[10];

        for (int i=0; i<10; i++) {
            answer[i] = Integer.parseInt(st.nextToken());
        }

        ans = 0;
        back(0);
        System.out.println(ans);
    }

    static void back(int depth) {
        if (depth == 10) {
            int cnt = 0;
            for (int i = 0; i < 10; i++) {
                if (answer[i] == youngjae[i]) {
                    cnt++;
                }
            }
            if (cnt >= 5) {
                ans++;
            }
            return;
        }

        for (int i = 1; i <= 5; i++) {
            if (depth >= 2) {
                if (youngjae[depth - 1] == i && youngjae[depth - 2] == i) continue;
            }
            youngjae[depth] = i;
            back(depth + 1);
        }
    }
}
