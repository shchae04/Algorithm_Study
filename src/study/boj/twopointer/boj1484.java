package study.boj.twopointer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 백준 1484번 - 다이어트
 *
 * 문제: 현재 몸무게의 제곱 - 기억하는 몸무게의 제곱 = G일 때,
 *      가능한 현재 몸무게를 모두 구하는 문제
 *
 * 핵심 아이디어:
 * - 현재 몸무게를 start, 기억하는 몸무게를 end라고 할 때
 * - start² - end² = G 를 만족하는 모든 start 값을 찾아야 함
 * - (start + end)(start - end) = G의 형태로 변형 가능
 * - 투 포인터 기법을 사용하여 효율적으로 탐색
 *
 * 알고리즘: 투 포인터 (Two Pointer)
 * - start와 end를 1부터 시작
 * - diff = start² - end² 계산
 * - diff == G이면 해당 start 값을 출력
 * - diff >= G이면 end 증가 (차이를 줄임)
 * - diff < G이면 start 증가 (차이를 늘림)
 * - start - end == 1이고 diff > G이면 더 이상 해가 없으므로 종료
 *
 * 구현 세부사항:
 * 1. start, end를 모두 1로 초기화
 * 2. 무한 루프에서 diff 계산
 * 3. 종료 조건: start - end == 1이고 diff > G
 * 4. diff에 따라 start 또는 end 증가
 * 5. diff == G일 때 start 출력 및 flag 설정
 * 6. 해가 없으면 -1 출력
 *
 * 시간복잡도: O(√G)
 * - start와 end가 최대 √G 정도까지 증가
 *
 * 공간복잡도: O(1)
 * - 추가 배열 없이 상수 공간만 사용
 */
public class boj1484 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력: 몸무게 차이의 제곱
        int G = Integer.parseInt(br.readLine());

        // 투 포인터 초기화
        int start = 1;  // 현재 몸무게
        int end = 1;    // 기억하는 몸무게
        boolean flag = false;  // 해가 존재하는지 확인하는 플래그

        // 투 포인터를 이용한 탐색
        while (true) {
            // 현재 차이 계산: start² - end²
            long diff = (long) (Math.pow(start, 2)) - (long) (Math.pow(end, 2));

            // 종료 조건: start - end == 1이고 diff > G이면 더 이상 해가 없음
            if (start - end == 1 && diff > G) break;

            if (diff >= G)
                end++;      // 차이가 크거나 같으면 end 증가 (차이 줄임)
            else
                start++;    // 차이가 작으면 start 증가 (차이 늘림)

            // 정답 발견시 출력
            if (diff == G) {
                System.out.println(start);
                flag = true;
            }
        }

        // 해가 없는 경우 -1 출력
        if (!flag)
            System.out.println(-1);
    }
}