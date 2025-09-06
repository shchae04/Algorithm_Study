package study.boj.daily;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 백준 24525 - SKK 문자열
 * 
 * 문제 설명:
 * 문자열에서 'S', 'K', 다른 문자들로 구성된 문자열이 주어진다.
 * 'S'는 가중치 +2, 'K'는 가중치 -1을 가지며, 다른 문자는 가중치 0이다.
 * 부분 문자열의 가중치 합이 0이면서 'S'와 'K'가 모두 포함된 가장 긴 부분 문자열의 길이를 구하는 문제.
 * 
 * 알고리즘 접근법:
 * 1. 누적합(Prefix Sum)을 이용한 효율적 해법
 * 2. 같은 누적합 값을 가진 두 위치 사이의 구간 합은 0이다
 * 3. 각 누적합 값의 최초 등장 위치를 기록하여 최대 길이 구간을 찾는다
 * 
 * 시간 복잡도: O(n) - 문자열을 두 번 순회
 * 공간 복잡도: O(n + 600002) - 누적합 배열과 인덱스 배열
 * 
 * 핵심 아이디어:
 * - 누적합이 같은 두 지점 사이의 구간 합은 0이다
 * - 해시맵 대신 배열을 사용하여 빠른 접근 (오프셋으로 음수 인덱스 처리)
 * 
 * 예시:
 * 문자열 "SSKK" -> 가중치 [2, 2, -1, -1]
 * 누적합: [0, 2, 4, 3, 2]
 * 인덱스 0과 4에서 누적합이 2로 같음 -> 구간 [1,4] "SSKK"의 합은 0
 * S 개수: 2, K 개수: 2 -> 조건 만족, 길이 4
 * 
 * 주요 제약 조건:
 * - 구간에 S와 K가 모두 포함되어야 함 (단순히 합이 0인 것만으로는 부족)
 * - 가중치 비율: S 하나당 K 두 개가 필요 (2 * 1 = 1 * 2)
 * 
 * 엣지 케이스:
 * - S만 있는 경우: 불가능 (K가 없음)
 * - K만 있는 경우: 불가능 (S가 없음)
 * - 가중치 합이 0이지만 S나 K가 없는 경우: 불가능
 */
public class boj24525 {
    /**
     * 메인 알고리즘 구현
     * 
     * 단계별 해결 과정:
     * 1. 누적합 계산: 각 위치까지의 가중치 합 계산
     * 2. 인덱스 매핑: 각 누적합 값의 최초 등장 위치 기록
     * 3. 구간 탐색: 같은 누적합을 가진 구간에서 최대 길이 찾기
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine().trim();

        int len = s.length();
        
        // 데이터 구조 초기화
        int[] psum = new int[len + 1];  // 누적합 배열 (가중치 합)
        int[] idx = new int[600002];    // 각 누적합 값의 최초 등장 인덱스 (오프셋 300000)
        int[] kcnt = new int[len + 1];  // K 문자 개수 누적합
        int[] scnt = new int[len + 1];  // S 문자 개수 누적합

        // idx 배열을 무한대로 초기화 (등장하지 않은 누적합 값 표시)
        Arrays.fill(idx, Integer.MAX_VALUE);

        /*
         * 1단계: 누적합과 문자 개수 계산 - O(n)
         * 
         * 각 위치 i에서:
         * - psum[i]: 0번째부터 i-1번째까지의 가중치 합
         * - kcnt[i]: 0번째부터 i-1번째까지의 K 문자 개수
         * - scnt[i]: 0번째부터 i-1번째까지의 S 문자 개수
         * 
         * 가중치 규칙:
         * - 'S': +2 (가중치가 높아 균형을 위해 K가 더 필요)
         * - 'K': -1
         * - 기타 문자: 0
         */
        for (int i = 1; i <= len; i++) {
            psum[i] = psum[i - 1];
            kcnt[i] = kcnt[i - 1];
            scnt[i] = scnt[i - 1];

            if (s.charAt(i - 1) == 'S') {
                psum[i] += 2;  // S가 나오면 +2
                scnt[i] += 1;
            } else if (s.charAt(i - 1) == 'K') {
                psum[i] -= 1;  // K가 나오면 -1
                kcnt[i] += 1;
            }
            // 다른 문자는 가중치 0이므로 변경 없음
        }

        int ans = -1;  // 조건을 만족하는 최대 부분 문자열 길이 (-1은 불가능한 경우)

        /*
         * 2단계: 각 누적합 값의 최초 등장 위치 기록 - O(n)
         * 
         * 핵심 아이디어: psum[i] == psum[j]이면 구간 [i+1, j]의 가중치 합은 0
         * 
         * 오프셋 300000을 사용하는 이유:
         * - 누적합이 음수가 될 수 있어 배열 인덱스로 사용 불가
         * - 최대 문자열 길이 300000 × 가중치 범위 [-1, 2] 고려
         * - 안전한 범위: [-300000, 600000] → [0, 900000] (실제로는 600002 사용)
         */
        for (int i = 0; i <= len; i++) {
            int index = psum[i] + 300000;  // 음수 인덱스 방지를 위한 오프셋
            idx[index] = Math.min(idx[index], i);  // 가장 빠른 등장 위치만 저장
        }

        /*
         * 3단계: 최대 길이 구간 탐색 - O(n)
         * 
         * 각 위치 i에 대해 같은 누적합을 가진 가장 이른 위치 left 탐색
         * 구간 [left+1, i]가 조건을 만족하는지 확인:
         * 1. 가중치 합이 0 (psum[i] == psum[left])
         * 2. S와 K가 모두 포함 (s_cnt > 0 && k_cnt > 0)
         */
        for (int i = 1; i <= len; i++) {
            int index = psum[i] + 300000;
            int left = idx[index];

            if (left < Integer.MAX_VALUE && left < i) {
                // 구간 [left+1, i]에서의 문자 개수 계산
                int k_cnt = kcnt[i] - kcnt[left];  // 구간 내 K 개수
                int s_cnt = scnt[i] - scnt[left];  // 구간 내 S 개수

                // 조건 확인: S와 K가 모두 존재해야 함
                if (k_cnt != 0 && s_cnt != 0) {
                    ans = Math.max(ans, i - left);  // 구간 길이 업데이트
                }
            }
        }

        System.out.println(ans);
        scanner.close();
    }
}