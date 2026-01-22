package study.boj.string;
/**
 * 백준 2661번 - 좋은수열
 *
 * 문제: 1, 2, 3으로만 이루어진 길이 N의 수열 중에서,
 *       나쁜 수열(인접한 두 부분 수열이 동일)이 아닌 좋은 수열 중
 *       가장 작은 수를 사전순으로 출력하기
 *
 * 알고리즘: 백트래킹 (Backtracking)
 * - 1, 2, 3을 순서대로 시도하면서 좋은 수열을 구성
 * - 각 단계에서 나쁜 수열 여부를 검사하여 가지치기
 * - 첫 번째로 발견되는 해가 최소값 (1부터 시도하므로)
 *
 * 핵심 아이디어:
 * 1. 백트래킹으로 수열을 한 자리씩 추가하며 구성
 * 2. 각 단계에서 1, 2, 3을 순서대로 추가 시도
 * 3. 새로운 숫자를 추가한 수열이 좋은 수열인지 검사
 * 4. 좋은 수열이면 다음 재귀 단계로 진행, 나쁜 수열이면 백트래킹
 * 5. 길이 N에 도달하면 출력 후 프로그램 종료
 *
 * 좋은 수열 검사 방법:
 * - 현재 수열의 마지막 부분부터 길이 1, 2, ..., length/2까지
 * - 각 길이에 대해 끝에서 i개와 그 직전 i개가 동일한지 비교
 * - 하나라도 동일하면 나쁜 수열
 *
 * 구현 세부사항:
 * - System.exit(0)로 첫 번째 해 발견 시 즉시 종료
 * - 문자열 연결로 수열 구성 (StringBuilder 사용 가능하나 단순성 우선)
 * - 부분 수열 비교는 substring으로 효율적으로 처리
 *
 * 시간복잡도: O(3^N) (이론적 최악) → 실제로는 O(N × 2^N) 이하
 * - 각 자리에서 최대 3가지 선택
 * - 가지치기로 실제 탐색 공간은 대폭 감소
 * - 좋은 수열 검사: O(N²) (각 단계마다 O(N) 검사)
 * - 실제로는 첫 번째 해를 빠르게 찾으므로 효율적
 *
 * 공간복잡도: O(N)
 * - 재귀 호출 스택: O(N) - 최대 깊이 N
 * - 문자열 저장: O(N) - 각 재귀 레벨에서 길이 증가
 *
 * 백트래킹 문제의 전형적인 예제로, 조기 종료와 가지치기를 통한 최적화 적용
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj2661 {
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        backtracking("");
    }

    private static void backtracking(String result) {
        if (result.length() == N) {
            System.out.println(result);
            System.exit(0); // 현재 실행하고 있는 프로세스를 강제 종료

        } else {
            for (int i = 1; i <= 3; i++) {
                if (isGoodSequence(result + i)) {
                    backtracking(result + i);
                }
            }
        }
    }

    private static boolean isGoodSequence(String s) {
        int length = s.length() / 2;

        for (int i = 1; i <= length; i++) {
            if (s.substring(s.length() - i).equals(s.substring(s.length() - 2 * i, s.length() - i))) {
                return false;
            }
        }

        return true;
    }
}