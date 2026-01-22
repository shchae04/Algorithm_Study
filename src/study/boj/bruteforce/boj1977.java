package study.boj.bruteforce;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * A² - B² = N을 만족하는 자연수 쌍 (A, B) 개수를 구하는 알고리즘
 * 
 * 문제 유형: 수학, 완전탐색
 * 
 * 알고리즘 개요:
 * 주어진 자연수 N에 대해 A² - B² = N을 만족하는 자연수 쌍 (A, B)의 개수를 구합니다.
 * 여기서 A >= B > 0이어야 합니다.
 * 
 * 핵심 수학적 개념:
 * - 두 제곱수의 차 공식: A² - B² = (A+B)(A-B) = N
 * - 이는 N을 두 자연수의 곱으로 분해하는 문제와 연결됩니다
 * - A+B = p, A-B = q라 하면, N = p×q이고 A = (p+q)/2, B = (p-q)/2
 * 
 * 구현 방식:
 * - 완전탐색을 통해 1 ≤ B ≤ A ≤ 500 범위에서 모든 (A, B) 쌍을 확인
 * - A² - B² = N인 쌍의 개수를 카운트
 * 
 * 시간복잡도: O(500²) = O(250,000) - 두 중첩 반복문
 * 공간복잡도: O(1) - 상수 공간 사용
 * 
 * 최적화 여지:
 * - 수학적 접근: N의 약수를 이용한 O(√N) 해법 가능
 * - 현재 구현은 단순하지만 이해하기 쉬운 완전탐색 방식
 * 
 * @author Algorithm Study
 * @since 2024
 */
public class boj1977 {

    /**
     * A² - B² = N을 만족하는 자연수 쌍 (A, B)의 개수를 구하는 메인 메서드
     * 
     * 알고리즘 단계:
     * 1. 입력: 자연수 N 읽기
     * 2. 완전탐색: A, B ∈ [1, 500] 범위에서 모든 쌍 검사
     * 3. 조건 검사: A >= B이고 A² - B² = N인 경우 카운트 증가
     * 4. 출력: 조건을 만족하는 쌍의 총 개수
     * 
     * 수학적 분석:
     * - A² - B² = (A+B)(A-B) = N
     * - A >= B 조건으로 중복 제거 (순서쌍이 아닌 조합)
     * - 최대 탐색 범위: 500² = 250,000번의 연산
     * 
     * 시간복잡도: O(MAX_RANGE²) where MAX_RANGE = 500
     * 공간복잡도: O(1)
     * 
     * @param args 명령줄 인수 (사용되지 않음)
     * @throws IOException 입력 스트림 읽기 오류 시 발생
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 목표값 N 입력
        int N = Integer.parseInt(br.readLine());

        int count = 0;
        
        // 완전탐색: 모든 가능한 A, B 쌍 검사
        for(int A = 1; A <= 500; A++) {
            for(int B = 1; B <= 500; B++) {
                // A >= B 조건: 중복 방지 및 수학적 조건
                if (A >= B) {
                    // A² - B² = N 조건 검사
                    if (A * A - B * B == N) {
                        count++;
                    }
                }
            }
        }
        
        // 결과 출력: 조건을 만족하는 쌍의 개수
        System.out.println(count);
    }
}
