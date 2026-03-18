package study.boj.binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 백준 11663: 선분 위의 점
 *
 * 문제 요약:
 * - N개의 점이 주어지고, M개의 선분 구간이 주어졌을 때
 * - 각 선분 구간 [left, right] 안에 포함되는 점의 개수를 구하는 문제
 *
 * 핵심 알고리즘:
 * - 점들을 정렬한 후, 이분 탐색(Lower Bound, Upper Bound)을 활용
 * - Lower Bound: left 이상인 첫 번째 위치 찾기
 * - Upper Bound: right 초과인 첫 번째 위치 찾기
 * - 두 위치의 차이가 구간 내 점의 개수
 *
 * 시간 복잡도: O(N logN + M logN)
 * - N개 점 정렬: O(N logN)
 * - M개 쿼리마다 이분 탐색 2회: O(M logN)
 */
public class boj11663 {

    static int[] points;  // 주어진 N개의 점들을 저장할 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // N: 점의 개수, M: 선분의 개수
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // N개의 점 입력 받기
        points = new int[N];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            points[i] = Integer.parseInt(st.nextToken());
        }

        // 이분 탐색을 위해 점들을 오름차순 정렬
        Arrays.sort(points);

        // 결과를 한 번에 출력하기 위한 StringBuilder
        StringBuilder sb = new StringBuilder();

        // M개의 선분 구간에 대해 처리
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int left = Integer.parseInt(st.nextToken());   // 선분의 시작점
            int right = Integer.parseInt(st.nextToken());  // 선분의 끝점

            // left 이상인 값이 처음 나타나는 인덱스
            // 즉, 선분 구간에 포함될 수 있는 첫 번째 점의 위치
            int leftIdx = lowerBound(points, left);

            // right 초과인 값이 처음 나타나는 인덱스
            // 즉, 선분 구간을 벗어나는 첫 번째 점의 위치
            int rightIdx = upperBound(points, right);

            // [leftIdx, rightIdx) 구간의 점들이 [left, right] 범위에 속함
            // 따라서 rightIdx - leftIdx가 구간 내 점의 개수
            sb.append(rightIdx - leftIdx).append("\n");
        }

        System.out.println(sb);
    }

    /**
     * Lower Bound: target 이상인 값이 처음 나타나는 위치를 찾는 이분 탐색
     *
     * @param arr    정렬된 배열
     * @param target 찾고자 하는 기준 값
     * @return target 이상인 첫 번째 원소의 인덱스 (없으면 배열 길이 반환)
     *
     * 동작 원리:
     * - arr[mid] >= target이면 mid 이하에 정답이 있을 수 있으므로 right = mid
     * - arr[mid] < target이면 mid보다 큰 곳에 정답이 있으므로 left = mid + 1
     * - left와 right가 만나는 지점이 target 이상인 첫 위치
     */
    static int lowerBound(int[] arr, int target) {
        int left = 0;
        int right = arr.length;  // 배열 길이로 초기화 (모든 값이 target 미만일 경우 처리)

        while (left < right) {
            int mid = (left + right) / 2;

            if (arr[mid] >= target) {
                // mid 위치의 값이 target 이상이면
                // mid가 답이 될 수도 있고, 더 왼쪽에 있을 수도 있음
                right = mid;
            } else {
                // mid 위치의 값이 target 미만이면
                // 답은 mid보다 오른쪽에 있음
                left = mid + 1;
            }
        }

        return left;  // left == right인 지점이 lower bound
    }

    /**
     * Upper Bound: target 초과인 값이 처음 나타나는 위치를 찾는 이분 탐색
     *
     * @param arr    정렬된 배열
     * @param target 찾고자 하는 기준 값
     * @return target 초과인 첫 번째 원소의 인덱스 (없으면 배열 길이 반환)
     *
     * 동작 원리:
     * - arr[mid] > target이면 mid 이하에 정답이 있을 수 있으므로 right = mid
     * - arr[mid] <= target이면 mid보다 큰 곳에 정답이 있으므로 left = mid + 1
     * - left와 right가 만나는 지점이 target 초과인 첫 위치
     *
     * Lower Bound와의 차이점:
     * - Lower Bound는 >= 조건, Upper Bound는 > 조건
     * - 이 차이로 인해 target과 같은 값의 처리가 달라짐
     */
    static int upperBound(int[] arr, int target) {
        int left = 0;
        int right = arr.length;  // 배열 길이로 초기화 (모든 값이 target 이하일 경우 처리)

        while (left < right) {
            int mid = (left + right) / 2;

            if (arr[mid] > target) {
                // mid 위치의 값이 target 초과이면
                // mid가 답이 될 수도 있고, 더 왼쪽에 있을 수도 있음
                right = mid;
            } else {
                // mid 위치의 값이 target 이하이면
                // 답은 mid보다 오른쪽에 있음
                left = mid + 1;
            }
        }

        return left;  // left == right인 지점이 upper bound
    }
}
