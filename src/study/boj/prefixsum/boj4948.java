package study.boj.prefixsum;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * 백준 4948번 - 베르트랑 공준
 *
 * 문제: n보다 크고 2n보다 작거나 같은 소수의 개수를 구하기
 * - 여러 개의 n이 주어지고 각각에 대해 답을 출력
 * - n은 최대 123,456까지 가능
 *
 * 알고리즘: 에라토스테네스의 체 + 누적합
 * - 에라토스테네스의 체로 2n까지의 모든 소수를 미리 구함
 * - 누적합 배열을 만들어 구간 내 소수 개수를 O(1)에 계산
 *
 * 핵심 아이디어:
 * 1. 에라토스테네스의 체로 모든 소수 판별
 *    - 2부터 시작해서 각 소수의 배수들을 모두 합성수로 표시
 *    - i*i부터 시작하는 이유: i*2, i*3, ... , i*(i-1)은 이미 처리됨
 *    - sqrt(N)까지만 확인하면 됨 (그 이상은 이미 처리됨)
 *
 * 2. 누적합 배열 생성
 *    - count_arr[i] = 0부터 i까지 소수의 개수
 *    - 이렇게 하면 특정 구간의 소수 개수를 빠르게 구할 수 있음
 *
 * 3. 구간 소수 개수 계산
 *    - n+1부터 2n까지의 소수 개수 = count_arr[2n] - count_arr[n]
 *    - 예: 4+1부터 8까지의 소수 개수 = count_arr[8] - count_arr[4]
 *         = (2,3,5,7 개수) - (2,3 개수) = 4 - 2 = 2개 (5,7)
 *
 * 시간복잡도: O(N log log N) + O(T)
 * - 에라토스테네스의 체: O(N log log N) - 최대 N=246,913
 * - 누적합 배열 생성: O(N)
 * - 각 쿼리 처리: O(1) - T개의 테스트케이스
 * - 전처리 후 각 쿼리를 상수 시간에 처리할 수 있어 매우 효율적
 *
 * 공간복잡도: O(N)
 * - prime 배열: 246,913개의 boolean (소수 판별)
 * - count_arr 배열: 246,913개의 int (누적 소수 개수)
 *
 * 왜 이 방법이 효율적인가?
 * - 매번 소수를 찾으면 시간초과 발생
 * - 미리 모든 소수를 구해두고 누적합으로 구간 개수를 O(1)에 계산
 * - 베르트랑 공준: n > 1에 대해 n과 2n 사이에는 항상 소수가 존재 (증명됨)
 */

public class boj4948 {
    /**
     * 소수 판별 배열 (false: 소수, true: 합성수)
     * - n <= 123,456이므로 2n은 최대 246,912
     * - 0부터 시작하므로 246,912 + 1 = 246,913 크기 필요
     * - 역논리를 사용하는 이유: 배열 초기값이 false이므로 소수가 기본값
     */
    public static boolean[] prime = new boolean[246913];

    /**
     * 누적 소수 개수 배열
     * - count_arr[i] = 0부터 i까지의 구간에 존재하는 소수의 총 개수
     * - 예: count_arr[10] = 4 (2, 3, 5, 7 총 4개)
     */
    public static int[] count_arr = new int[246913];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 1단계: 에라토스테네스의 체로 모든 소수를 미리 구함
        // - 프로그램 시작 시 한 번만 실행
        // - 이후 모든 쿼리에서 재사용
        get_prime();

        // 2단계: 누적 소수 개수 배열 생성
        // - 각 인덱스까지의 소수 개수를 저장
        // - O(1) 시간에 구간 소수 개수 계산 가능
        get_count();

        StringBuilder sb = new StringBuilder();

        // 3단계: 각 테스트케이스 처리
        while (true) {
            int n = Integer.parseInt(br.readLine());
            if (n == 0) break;    // 입력이 0이면 종료

            // n+1부터 2n까지의 소수 개수 계산
            // count_arr[2n] = 0부터 2n까지의 소수 개수
            // count_arr[n] = 0부터 n까지의 소수 개수
            // 빼면 n+1부터 2n까지의 소수 개수가 나옴
            //
            // 예시: n=10일 때
            // - count_arr[20] = 8 (2,3,5,7,11,13,17,19)
            // - count_arr[10] = 4 (2,3,5,7)
            // - 답 = 8 - 4 = 4 (11,13,17,19)
            sb.append(count_arr[2 * n] - count_arr[n]).append('\n');
        }
        System.out.print(sb);
    }

    /**
     * 에라토스테네스의 체 알고리즘으로 소수 판별
     *
     * 동작 과정:
     * 1. 2부터 시작 (2는 가장 작은 소수)
     * 2. 현재 숫자가 소수면, 그 배수들을 모두 합성수로 표시
     * 3. sqrt(N)까지만 확인하면 됨
     *
     * 왜 sqrt(N)까지만?
     * - 합성수 N = a * b라면, a와 b 중 하나는 반드시 sqrt(N) 이하
     * - 예: 36 = 6 * 6, 만약 a > 6이면 b < 6이므로 b에서 이미 처리됨
     *
     * 왜 i*i부터 시작?
     * - i*2, i*3, ..., i*(i-1)은 이미 더 작은 수의 배수로 처리됨
     * - 예: i=5일 때, 5*2=10은 2의 배수로, 5*3=15는 3의 배수로 이미 처리됨
     * - 따라서 5*5=25부터 시작하면 됨
     */
    public static void get_prime() {
        // 0과 1은 소수가 아니므로 true로 표시 (합성수 취급)
        // prime[i] = true: i는 소수가 아님
        // prime[i] = false: i는 소수임
        prime[0] = prime[1] = true;

        // 2부터 sqrt(246913) ≈ 497까지 확인
        for (int i = 2; i <= Math.sqrt(prime.length); i++) {
            // 이미 합성수로 판별된 경우 건너뜀
            if (prime[i]) continue;

            // i는 소수이므로, i의 배수들을 모두 합성수로 표시
            // j는 i*i부터 시작 (i*2, i*3, ... , i*(i-1)은 이미 처리됨)
            // j += i: i의 배수들을 차례로 방문
            //
            // 예: i=2일 때
            // - j=4, 6, 8, 10, ... (2의 배수들을 모두 합성수로 표시)
            // 예: i=3일 때
            // - j=9, 12, 15, 18, ... (3의 배수들, 6은 이미 2에서 처리)
            for (int j = i * i; j < prime.length; j += i) {
                prime[j] = true;    // j는 i의 배수이므로 합성수
            }
        }
    }

    /**
     * 누적 소수 개수 배열 생성 (Prefix Sum)
     *
     * 목적: 구간 [a, b] 내의 소수 개수를 O(1)에 구하기 위한 전처리
     *
     * 누적합의 원리:
     * - count_arr[i] = 0부터 i까지의 소수 개수
     * - [a+1, b] 구간의 소수 개수 = count_arr[b] - count_arr[a]
     *
     * 예시로 이해하기:
     * - 숫자:    0  1  2  3  4  5  6  7  8  9  10
     * - 소수?:   X  X  O  O  X  O  X  O  X  X  X
     * - count_arr: 0  0  1  2  2  3  3  4  4  4  4
     *
     * 이제 4부터 10까지의 소수 개수를 구하려면?
     * - count_arr[10] - count_arr[4] = 4 - 2 = 2 (소수: 5, 7)
     * - 직접 세지 않고 빼기 한 번으로 계산 가능!
     */
    public static void get_count() {
        int count = 0;    // 현재까지 찾은 소수의 개수

        // 2부터 시작 (0과 1은 소수가 아님)
        for (int i = 2; i < prime.length; i++) {
            // prime[i] = false면 i는 소수
            if (!prime[i]) {
                count++;    // 소수를 발견했으므로 개수 증가
            }

            // count_arr[i]에 0부터 i까지의 소수 개수 저장
            // 이렇게 하면 나중에 구간의 소수 개수를 빠르게 계산 가능
            //
            // 예: i=7일 때
            // - 0~7까지 소수는 2, 3, 5, 7 총 4개
            // - count_arr[7] = 4
            //
            // 예: i=8일 때
            // - 8은 소수가 아니므로 count는 그대로 4
            // - count_arr[8] = 4 (0~8까지도 소수는 여전히 4개)
            count_arr[i] = count;
        }
    }


}