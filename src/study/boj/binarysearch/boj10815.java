package study.boj.binarysearch;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 백준 10815번 - 숫자카드 문제 해결을 위한 이분탐색 알고리즘 구현
 * 
 * <h2>문제 설명</h2>
 * 상근이는 N개의 숫자카드를 가지고 있고, M개의 정수가 주어졌을 때
 * 각 정수가 상근이의 카드에 있는지 확인하는 문제입니다.
 * 
 * <h2>알고리즘 핵심 아이디어</h2>
 * 1. 주어진 카드 배열을 오름차순 정렬
 * 2. 각 질의에 대해 이분탐색을 수행하여 O(log N) 시간에 존재 여부 판단
 * 3. 정렬된 배열에서 중간값과 목표값을 비교하여 탐색 범위를 절반씩 줄임
 * 
 * <h2>이분탐색 구현 특징</h2>
 * - 표준적인 이분탐색 구현 방식 사용
 * - left <= right 조건으로 탐색 범위 제어
 * - 오버플로우 방지를 위해 mid = (left + right) / 2 계산
 * 
 * <h2>복잡도 분석</h2>
 * - 시간복잡도: O(N log N + M log N)
 *   - 정렬: O(N log N)
 *   - M번의 이분탐색: O(M log N)
 * - 공간복잡도: O(N) - 카드 배열 저장
 * 
 * <h2>에지 케이스 처리</h2>
 * - 빈 배열: N = 0인 경우
 * - 단일 원소: N = 1인 경우
 * - 중복 원소: 배열에 같은 값이 여러 개 있는 경우
 * - 범위 밖 값: 배열의 최솟값보다 작거나 최댓값보다 큰 경우
 * 
 * @author Algorithm Study
 * @version 1.0
 * @since 2024
 */
public class boj10815 {
    public static void main(String[] args) throws IOException {
        // 입력을 위한 BufferedReader 생성
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 상근이가 가지고 있는 카드의 개수 N 입력 받기
        int n = Integer.parseInt(br.readLine());

        // 상근이가 가지고 있는 카드의 번호를 저장할 배열 생성
        int[] cards = new int[n];

        // 상근이가 가지고 있는 카드의 번호 입력 받기
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
        }

        // 이분 탐색을 위해 카드 배열 정렬
        Arrays.sort(cards);

        // 확인할 카드 개수 M 입력 받기
        int m = Integer.parseInt(br.readLine());

        // 확인할 카드 번호 입력 받기
        st = new StringTokenizer(br.readLine());

        // 결과를 저장할 StringBuilder 생성
        StringBuilder sb = new StringBuilder();

        // 각 확인할 카드에 대해 이분 탐색 수행
        for (int i = 0; i < m; i++) {
            int target = Integer.parseInt(st.nextToken());

            // 이분 탐색으로 카드 존재 여부 확인 (존재하면 1, 없으면 0)
            if (binarySearch(cards, target)) {
                sb.append("1 ");
            } else {
                sb.append("0 ");
            }
        }

        // 결과 출력
        System.out.println(sb.toString().trim());
    }

    /**
     * 정렬된 배열에서 목표값의 존재 여부를 판단하는 표준 이분탐색 알고리즘
     * 
     * <h3>알고리즘 동작 원리</h3>
     * 1. 탐색 범위의 시작(left)과 끝(right) 인덱스를 설정
     * 2. 중간 인덱스(mid)를 계산하고 중간값과 목표값을 비교
     * 3. 비교 결과에 따라 탐색 범위를 절반으로 줄임:
     *    - arr[mid] == target: 값을 찾았으므로 true 반환
     *    - arr[mid] < target: 오른쪽 절반에서 탐색 (left = mid + 1)
     *    - arr[mid] > target: 왼쪽 절반에서 탐색 (right = mid - 1)
     * 4. left > right가 되면 값이 존재하지 않으므로 false 반환
     * 
     * <h3>구현 특징</h3>
     * - 닫힌 구간 [left, right] 방식 사용
     * - 종료 조건: left <= right
     * - 중간값 계산: (left + right) / 2 (오버플로우 위험 있음)
     * 
     * <h3>시간복잡도</h3>
     * - 최선의 경우: O(1) - 첫 번째 비교에서 값을 찾은 경우
     * - 평균 및 최악의 경우: O(log N) - 매번 탐색 범위가 절반으로 줄어듦
     * 
     * <h3>공간복잡도</h3>
     * - O(1) - 추가 메모리 사용량이 상수
     * 
     * @param arr 탐색할 정렬된 정수 배열 (오름차순 정렬 필수)
     * @param target 찾고자 하는 목표값
     * @return 목표값이 배열에 존재하면 true, 존재하지 않으면 false
     * 
     * @throws IllegalArgumentException arr가 null인 경우 (실제로는 체크하지 않음)
     * 
     * <h3>사용 예시</h3>
     * <pre>
     * int[] sortedArray = {1, 3, 5, 7, 9};
     * boolean found = binarySearch(sortedArray, 5); // true
     * boolean notFound = binarySearch(sortedArray, 4); // false
     * </pre>
     */
    private static boolean binarySearch(int[] arr, int target) {
        int left = 0;                  // 탐색 범위의 시작 인덱스
        int right = arr.length - 1;    // 탐색 범위의 끝 인덱스

        // 탐색 범위가 유효한 동안 반복
        while (left <= right) {
            // 중간 인덱스 계산
            int mid = (left + right) / 2;

            // 중간 값이 타겟과 같으면 찾음
            if (arr[mid] == target) {
                return true;
            }
            // 중간 값이 타겟보다 작으면 오른쪽 부분 배열에서 탐색
            else if (arr[mid] < target) {
                left = mid + 1;
            }
            // 중간 값이 타겟보다 크면 왼쪽 부분 배열에서 탐색
            else {
                right = mid - 1;
            }
        }

        // 값을 찾지 못한 경우
        return false;
    }
}