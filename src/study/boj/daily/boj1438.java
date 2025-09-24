package study.boj.daily;

/**
 * 백준 1438번 - 가장 작은 직사각형
 *
 * 문제: n개의 2차원 점 중에서 정확히 n/2개의 점을 선택하여,
 *       이 점들을 모두 포함하는 가장 작은 직사각형의 넓이를 구하기
 *
 * 알고리즘: 정렬 기반 최적화 탐색 (Sorting-based Optimization Search)
 * - X좌표 기준 정렬 후 가능한 X 범위를 체계적으로 탐색
 * - 각 X 범위 내에서 Y좌표 기준 정렬을 통한 효율적인 점 선택
 * - 슬라이딩 윈도우 방식으로 연속된 n/2개 점들의 최소 직사각형 계산
 *
 * 핵심 아이디어:
 * 1. 먼저 모든 점을 X좌표 기준으로 정렬
 * 2. 가능한 모든 X 범위 [i, j]에 대해 탐색 (최소 n/2개 점 포함)
 * 3. 각 X 범위 내 점들을 Y좌표 기준으로 재정렬
 * 4. 슬라이딩 윈도우로 연속된 n/2개 점의 최소 직사각형 넓이 계산
 * 5. 모든 경우 중 최소 넓이 선택
 *
 * 구현 세부사항:
 * - 직사각형 넓이: (maxX - minX + 2) * (maxY - minY + 2)
 * - +2는 점을 포함하는 최소 격자 직사각형을 위한 여백
 *
 * 시간복잡도: O(n³ log n)
 * - 외부 이중 반복문: O(n²) - 가능한 모든 X 범위 조합
 * - 각 범위에서 Y좌표 정렬: O(n log n)
 * - 슬라이딩 윈도우 처리: O(n²) - 최악의 경우 n개 점에서 n개 윈도우
 * - 전체: O(n² × n log n × n) = O(n³ log n)
 *
 * 공간복잡도: O(n)
 * - 입력 점들을 위한 배열: O(n)
 * - 각 반복에서 후보 점들을 위한 임시 리스트: O(n)
 * - 정렬을 위한 추가 공간: O(log n)
 *
 * 기하 알고리즘의 최적화 문제로, 정렬과 윈도우 탐색을 결합한 효율적 해법
 * 완전 탐색의 복잡도를 정렬을 통해 체계적으로 줄인 대표적 사례
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class boj1438 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] points = new int[n][2];

        for (int i = 0; i < n; i++) {
            points[i][0] = sc.nextInt();
            points[i][1] = sc.nextInt();
        }

        int need = n / 2;
        int minArea = Integer.MAX_VALUE;

        // 모든 점의 조합을 n/2개씩 선택하되, 더 효율적인 방법 사용
        // X 좌표 기준으로 정렬
        Arrays.sort(points, (a, b) -> a[0] - b[0]);

        // 가능한 모든 X 범위에 대해
        for (int i = 0; i <= n - need; i++) {
            for (int j = i + need - 1; j < n; j++) {
                // points[i] ~ points[j] 범위에서 need개 선택
                List<int[]> candidates = new ArrayList<>();
                for (int k = i; k <= j; k++) {
                    candidates.add(points[k]);
                }

                // Y 좌표 기준으로 정렬
                candidates.sort((a, b) -> a[1] - b[1]);

                // 연속된 need개 선택해서 최소 직사각형
                for (int start = 0; start <= candidates.size() - need; start++) {
                    int minX = candidates.get(start)[0];
                    int maxX = candidates.get(start)[0];
                    int minY = candidates.get(start)[1];
                    int maxY = candidates.get(start)[1];

                    for (int k = start; k < start + need; k++) {
                        minX = Math.min(minX, candidates.get(k)[0]);
                        maxX = Math.max(maxX, candidates.get(k)[0]);
                        minY = Math.min(minY, candidates.get(k)[1]);
                        maxY = Math.max(maxY, candidates.get(k)[1]);
                    }

                    int area = (maxX - minX + 2) * (maxY - minY + 2);
                    minArea = Math.min(minArea, area);
                }
            }
        }

        System.out.println(minArea);
    }
}