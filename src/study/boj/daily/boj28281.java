package study.boj.daily;

import java.util.Scanner;

/**
 * 백준 28281번 - 선물
 *
 * 문제:
 * - 상근이는 친구들에게 선물을 주기 위해 양말을 사려고 한다.
 * - 양말은 N일 동안 판매되며, 하루에 X개씩 이틀 연속으로 구매해야 한다.
 * - 즉, i일과 i+1일에 각각 X개씩 양말을 구매할 때 드는 총 비용을 최소화해야 한다.
 * - 각 날짜별 양말의 가격 A[i]가 주어질 때, 최소 비용을 구하는 문제.
 *
 * 알고리즘: 구현(Implementation) / 그리디(Greedy) / 슬라이딩 윈도우(Sliding Window)
 * - 연속된 두 날짜의 가격 합이 최소가 되는 구간을 찾는다.
 * - 전체 탐색을 통해 최솟값을 갱신해 나간다.
 *
 * 핵심 아이디어:
 * 1. 연속된 이틀(i, i+1) 동안 양말을 X개씩 구매하므로 비용은 (cost[i] + cost[i+1]) * X 이다.
 * 2. 모든 i (0 <= i < N-1)에 대해 위 값을 계산하고, 그 중 최솟값을 찾는다.
 * 3. X는 공통된 곱셈 인자이므로, (cost[i] + cost[i+1])의 최솟값을 찾은 뒤 마지막에 X를 곱해도 된다.
 *    (이 코드는 매번 곱해서 비교하는 방식을 사용함)
 *
 * 시간복잡도: O(N)
 * - N개의 가격 데이터를 입력받고, N-1번의 반복문을 통해 인접한 두 값의 합을 비교한다.
 *
 * 공간복잡도: O(N)
 * - 가격 데이터를 저장하기 위해 크기 N의 배열을 사용한다.
 */
public class boj28281 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int x = sc.nextInt();
        int total = 0;
        int[] cost = new int[n];

        for (int i = 0; i < n; i++) {
            cost[i] = sc.nextInt();
        }

        total = (cost[0] + cost[1]) * x;
        for (int i = 1; i < n - 1; i++) {
            if ((cost[i] + cost[i + 1]) * x < total) {
                total = (cost[i] + cost[i + 1]) * x;
            }
        }
        System.out.println(total);
        sc.close();
    }
}