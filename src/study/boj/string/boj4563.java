package study.boj.string;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 백준 4563번 - 피타고라스의 정리 응용
 *
 * 문제: 주어진 양의 정수 A에 대해, A² + B² = C²를 만족하면서 B > A인 정수 B의 개수 구하기
 *
 * 알고리즘: 약수 쌍 탐색 + 수학적 변형
 *
 * 핵심 원리:
 * 피타고라스 정리 A² + B² = C²를 다음과 같이 변형할 수 있다:
 * - C² - B² = A²
 * - (C - B)(C + B) = A²
 *
 * x = C - B, y = C + B라고 정의하면:
 * - x × y = A² (x와 y는 A²의 약수 쌍)
 * - x + y = 2C
 * - y - x = 2B → B = (y - x) / 2
 *
 * 따라서 A²의 모든 약수 쌍 (x, y)에서 B를 역산할 수 있다.
 *
 * 풀이 과정:
 * 1. A²의 모든 약수 쌍 (x, y)를 찾는다 (x ≤ √(A²), y = A²/x)
 * 2. 각 약수 쌍에 대해 B = (y - x) / 2를 계산한다
 *    - (x + y)가 짝수여야 B가 정수가 된다 (조건 체크 필요)
 * 3. B > A인 경우만 카운트한다
 *
 * 예시:
 * A = 5일 때, A² = 25
 * 약수 쌍: (1, 25), (5, 5)
 *
 * (1, 25): B = (25-1)/2 = 12, B > A (12 > 5) ✓ → 5² + 12² = 13² 성립
 * (5, 5):  B = (5-5)/2 = 0,   B > A (0 > 5)  ✗
 *
 * 답: 1개
 *
 * 시간복잡도: O(√(A²)) = O(A)
 * - A²의 약수를 찾기 위해 1부터 √(A²)까지 탐색
 * - 각 약수 쌍마다 O(1) 연산 수행
 *
 * 공간복잡도: O(1)
 * - 입력값 A와 카운터 변수만 사용
 *
 * 주의사항:
 * - A의 범위가 클 수 있으므로 long 타입 사용 (int 범위 초과 방지)
 * - x² ≤ A²인 x만 탐색하여 중복 카운트 방지
 * - (x + y)의 짝수 여부 확인 필수 (B가 정수인지 검증)
 */

public class boj4563 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            long A = Long.parseLong(br.readLine());
            if(A == 0) break;
            long A2 = A * A;
            int count = 0;

            for (long x =1; x * x <= A2; x++) {
                if (A2 % x != 0) {
                    continue;
                }
                long y = A2 / x;

                if ((x + y) % 2 != 0) {
                    continue;
                }
                long B = (y - x) / 2;
                if (B > A) {
                    count++;
                }

            }
            System.out.println(count);
        }
    }
}
