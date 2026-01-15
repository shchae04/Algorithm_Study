package study.boj.daily;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 2417번 - 정수 제곱근
 * <p>
 * 문제: 정수 n이 주어졌을 때, q^2 >= n인 가장 작은 음이 아닌 정수 q를 구하는 문제
 * - 입력 n은 2^63 미만의 양의 정수 (long 범위)
 * <p>
 * 알고리즘: 이분 탐색 (Binary Search)
 * - 0부터 n 사이의 범위에서 조건 q^2 >= n을 만족하는 최소값을 탐색
 * <p>
 * 핵심 아이디어:
 * 1. 이분 탐색 범위: start = 0, end = n
 * 2. 조건 확인: Math.pow(mid, 2) >= n 을 만족하면 결과값을 저장하고 더 작은 범위(왼쪽)를 탐색하여 최소값 탐색
 * 3. 자료형: n의 범위가 크므로 입력값 N과 중간값 mid, 결과값 result 모두 long 타입을 사용
 * <p>
 * 시간복잡도: O(log N)
 * - n의 크기에 대해 로그 시간 내에 결과 도출 가능
 * <p>
 * 공간복잡도: O(1)
 * - 상수 개의 변수만 사용
 * <p>
 * 특이사항:
 * - Math.pow()는 double을 반환하므로 매우 큰 수에 대해 정밀도 문제가 발생할 수 있음에 유의
 * - n이 0인 경우 등 경계값 조건 확인 필요
 */
public class boj2417 {
    public static long N;
    public static long result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Long.parseLong(st.nextToken());

        binarySearch();
        System.out.println(result);

    }

    private static void binarySearch() {
        long start = 0;
        long end = N;

        while (start <= end) {
            long mid = (start + end) / 2;

            if (Math.pow(mid, 2) >= N) {
                result = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
    }
}
