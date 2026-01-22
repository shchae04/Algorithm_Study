package study.boj.twopointer;
import java.io.*;
import java.util.*;

/**
 * 백준 13423번 - Three Dots
 *
 * 문제: 직선 위의 점들이 주어질 때, 등차수열을 이루는 세 점의 개수 구하기
 *
 * 알고리즘: 정렬 + 투 포인터
 * - 점들을 정렬한 후 중간 점을 기준으로 양쪽을 탐색
 * - 세 점 a, b, c가 등차수열을 이루려면: b - a = c - b
 * - 중간 점 b를 고정하고 양쪽 포인터를 이동하며 조건을 만족하는 쌍 탐색
 *
 * 핵심 아이디어:
 * 1. 점들을 오름차순으로 정렬
 * 2. 중간 점 b를 하나씩 선택 (1번째부터 N-2번째까지)
 * 3. 왼쪽(left)과 오른쪽(right) 포인터를 설정
 * 4. leftDiff(b - a)와 rightDiff(c - b)를 비교:
 *    - 같으면: 등차수열 발견, 카운트 증가 후 양쪽 포인터 이동
 *    - leftDiff < rightDiff: 왼쪽 간격이 작으므로 left 감소
 *    - leftDiff > rightDiff: 오른쪽 간격이 작으므로 right 증가
 *
 * 시간복잡도: O(N^2)
 * - 정렬: O(N log N)
 * - 각 중간점 b에 대해 투 포인터 탐색: O(N)
 * - 총 N-2개의 중간점에 대해 수행: O(N^2)
 *
 * 공간복잡도: O(N) - 점 배열 저장을 위한 공간
 *
 * 정렬된 데이터에서 투 포인터를 활용하여 조건을 만족하는 쌍을 효율적으로 탐색하는 문제
 */

public class boj13423 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            String[] input = br.readLine().split(" ");

            int[] points = new int[N];
            for (int i = 0; i < N; i++) {
                points[i] = Integer.parseInt(input[i]);
            }

            // 점들을 정렬
            Arrays.sort(points);

            int count = 0;

            // 중간 점 b를 기준으로 탐색
            for (int b = 1; b < N - 1; b++) {
                int left = b - 1;
                int right = b + 1;

                // 투 포인터로 양쪽 탐색
                while (left >= 0 && right < N) {
                    int leftDiff = points[b] - points[left];
                    int rightDiff = points[right] - points[b];

                    if (leftDiff == rightDiff) {
                        // 간격이 같은 경우 발견
                        count++;
                        left--;
                        right++;
                    } else if (leftDiff < rightDiff) {
                        // 왼쪽 간격이 더 작으면 왼쪽 포인터 이동
                        left--;
                    } else {
                        // 오른쪽 간격이 더 작으면 오른쪽 포인터 이동
                        right++;
                    }
                }
            }

            sb.append(count).append("\n");
        }

        System.out.print(sb);
    }
}