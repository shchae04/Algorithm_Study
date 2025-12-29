package study.boj.daily;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 2805번 - 나무 자르기
 *
 * 문제: 절단기에 높이 H를 지정해서 그보다 높은 나무를 잘랐을 때, 잘린 나무의 길이의 합이 M미터 이상이 되게 하는 절단기 높이의 최댓값 구하기
 *
 * 알고리즘: 이분 탐색 (Binary Search) / 매개 변수 탐색 (Parametric Search)
 * - 결정 문제(decision problem)로 변환: "높이가 h일 때, 자른 나무의 합이 M 이상인가?"
 * - 가능한 높이의 범위(0 ~ max_height)에서 이분 탐색을 수행하여 조건을 만족하는 최댓값을 찾음
 *
 * 핵심 아이디어:
 * 1. 탐색 범위 설정: 최소 높이 0, 최대 높이 max(나무들의 높이)
 * 2. 중간값(mid) 설정: (start + end) / 2
 * 3. 획득 나무 계산: 각 나무에 대해 (tree - mid)가 양수인 경우만 합산
 * 4. 범위 조정:
 *    - total >= M: 나무가 충분함 -> 더 높게 잘라도 되는지 확인 (start = mid + 1)
 *    - total < M: 나무가 부족함 -> 높이를 낮춰야 함 (end = mid)
 * 5. Upper Bound 방식:
 *    - start와 end가 같아질 때까지 반복하며, 반복 종료 시 start는 조건을 만족하지 않는 첫 번째 값이 됨 (또는 조건을 만족하는 최대값 + 1)
 *    - 따라서 정답은 start - 1
 *
 * 시간복잡도: O(N * log(max_H))
 * - 탐색 범위가 0부터 나무의 최대 높이(max_H, 최대 10억)까지이므로 이분 탐색은 O(log(max_H))회 수행
 * - 각 단계마다 N개의 나무를 순회하며 합을 계산하므로 O(N) 소요
 *
 * 공간복잡도: O(N)
 * - N개의 나무 높이를 저장할 배열 필요
 */

public class boj2805 {

    static int n, m;
    static int[] trees;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 나무의 수 (1 ≤ N ≤ 1,000,000)
        m = Integer.parseInt(st.nextToken()); // 집으로 가져가려는 나무의 길이 (1 ≤ M ≤ 2,000,000,000)
        trees = new int[n];
        st = new StringTokenizer(br.readLine());
        
        int max = 0;
        for (int i = 0; i < n; i++) {
            trees[i] = Integer.parseInt(st.nextToken());
            if (trees[i] > max) max = trees[i];
        }

        long start = 0; // 절단기 높이 0 이상
        long end = max;
        long mid;
        long total;

        while (start < end) {
            total = 0;
            mid = (start + end) / 2;
            
            for (int i = 0; i < n; i++) {
                if (trees[i] > mid) {
                    total += (trees[i] - mid);
                }
                // 최적화: 이미 목표량을 채웠다면 더 계산할 필요 없음
                if (total >= m) break;
            }

            if (total >= m) start = mid + 1;
            else end = mid;
        }
        System.out.println(start - 1);

        br.close();
    }
}
