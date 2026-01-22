package study.boj.binarysearch;
import java.io.*;

/**
 * 백준 1300번 - K번째 수
 *
 * 문제: N×N 크기의 배열 A의 각 원소 A[i][j] = i×j일 때,
 *       이를 일차원 배열 B로 만들어 오름차순 정렬했을 때 B[K]의 값 구하기
 *
 * 알고리즘: 이분 탐색 (Parametric Search)
 * - 직접 배열을 생성하면 메모리 초과 발생 (N ≤ 100,000)
 * - "K번째 수가 X 이하인가?"를 이분 탐색으로 결정
 * - X 이하인 수의 개수를 세어 K와 비교
 *
 * 핵심 아이디어:
 * 1. 답의 범위는 1부터 K까지 (K번째 수는 K를 넘을 수 없음)
 * 2. 특정 값 mid 이하인 수의 개수 계산 방법:
 *    - i번째 행에서 mid 이하인 수의 개수 = min(mid/i, N)
 *    - i×1, i×2, ..., i×N 중에서 mid 이하인 것의 개수
 * 3. 전체 개수가 K 이상이면 답은 mid 이하, 미만이면 mid 초과
 *
 * 시간복잡도: O(N log K)
 * - 이분 탐색: O(log K)
 * - 각 탐색마다 N개 행에 대해 개수 계산: O(N)
 *
 * 공간복잡도: O(1)
 * - 배열을 생성하지 않고 계산만으로 해결
 *
 * 매개 변수 탐색(Parametric Search)의 대표적인 문제로,
 * 직접 구하기 어려운 값을 "조건을 만족하는 최솟값/최댓값"으로 변환하여 이분 탐색으로 해결
 */

public class boj1300 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        long min = 1;
        long max = K;

        while (min < max) {
            long mid = (min + max) / 2;
            long count = 0;

            for (int i = 1; i <= N; i++) {
                count += Math.min(mid / i, N);
            }

            if (K <= count) {
                max = mid;
            } else {
                min = mid + 1;
            }
        }

        System.out.println(min);
        br.close();
    }
}
