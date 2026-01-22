package study.boj.twopointer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 백준 16472번 - 고냥이
 *
 * 문제: 고양이가 인식할 수 있는 알파벳 종류가 최대 N개일 때,
 *      주어진 문자열에서 연속된 부분 문자열 중 가장 긴 길이를 구하는 문제
 *
 * 핵심 아이디어:
 * - 슬라이딩 윈도우(Sliding Window)와 투 포인터(Two Pointer) 기법 활용
 * - 현재 윈도우에서 사용된 알파벳 종류의 개수를 추적
 * - 알파벳 종류가 N개를 초과하면 윈도우 시작점을 이동시켜 조건을 만족시킴
 *
 * 알고리즘: 슬라이딩 윈도우 + 투 포인터
 * - start, end 포인터로 현재 윈도우의 구간을 관리
 * - alphabet 배열로 각 알파벳의 등장 횟수를 카운트
 * - count 변수로 현재 윈도우에서 사용된 서로 다른 알파벳의 개수를 추적
 *
 * 구현 세부사항:
 * 1. end 포인터를 오른쪽으로 이동하며 새 문자를 윈도우에 포함
 * 2. 새 문자가 처음 등장하는 경우(alphabet[char] == 0) count 증가
 * 3. count가 N을 초과하면 start 포인터를 이동하여 윈도우 크기 조정
 * 4. start 이동시 해당 문자의 개수를 감소시키고, 0이 되면 count 감소
 * 5. 각 단계에서 현재 윈도우 길이(end - start + 1)의 최댓값을 갱신
 *
 * 시간복잡도: O(|S|)
 * - 각 문자는 start와 end 포인터에 의해 최대 2번씩만 방문됨
 * - 문자열 길이에 비례하는 선형 시간 복잡도
 *
 * 공간복잡도: O(1)
 * - alphabet 배열 크기는 고정된 26 (영어 소문자)
 * - 추가 변수들은 상수 개
 */
public class boj16472 {
    // 각 알파벳 (a~z)의 현재 윈도우 내 등장 횟수를 저장하는 배열
    static int[] alphabet = new int[26];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력: N(인식 가능한 최대 알파벳 종류), 문자열
        int N = Integer.parseInt(br.readLine());
        String str = br.readLine();

        // count: 현재 윈도우에서 사용된 서로 다른 알파벳의 개수
        // answer: 조건을 만족하는 가장 긴 부분 문자열의 길이
        int count = 0, answer = 0;

        // 슬라이딩 윈도우: start는 윈도우 시작, end는 윈도우 끝
        for(int start = 0, end = 0; end < str.length(); end++) {
            // 새 문자를 윈도우에 추가
            // alphabet[문자]++의 이전 값이 0이면 새로운 종류의 알파벳
            if(alphabet[str.charAt(end) - 'a']++ == 0) {
                count++; // 새 알파벳 종류 추가
            }

            // 알파벳 종류가 N을 초과하면 윈도우 시작점을 이동하여 조건 만족
            while (N < count && start < end) {
                // start 문자를 윈도우에서 제거
                // --alphabet[문자]의 결과가 0이면 해당 알파벳 종류가 완전히 제거됨
                if (--alphabet[str.charAt(start++) - 'a'] == 0) {
                    count--; // 알파벳 종류 감소
                }
            }

            // 현재 윈도우 길이와 지금까지의 최댓값 비교하여 갱신
            answer = Math.max(answer, end - start + 1);
        }

        // 결과 출력: 조건을 만족하는 가장 긴 연속 부분 문자열의 길이
        System.out.println(answer);
    }
}