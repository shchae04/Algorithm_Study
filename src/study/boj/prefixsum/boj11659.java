package study.boj.prefixsum;
import java.util.Scanner;

/**
 * 백준 11659번: 구간 합 구하기 4
 * 
 * 문제 개요:
 * N개의 수가 주어졌을 때, M번의 구간 합 쿼리에 대해 i번째부터 j번째까지의 합을 빠르게 구하는 문제입니다.
 * 단순히 매번 구간을 순회하면 O(N*M)의 시간복잡도로 시간초과가 발생합니다.
 * 
 * 누적합(Prefix Sum) 알고리즘:
 * 누적합은 배열의 첫 번째 원소부터 현재 원소까지의 합을 미리 계산해두는 기법입니다.
 * prefixSum[i] = arr[0] + arr[1] + ... + arr[i-1]로 정의하면,
 * 구간 [i, j]의 합은 prefixSum[j+1] - prefixSum[i]로 O(1)에 구할 수 있습니다.
 * 
 * 핵심 아이디어:
 * - 전처리 단계에서 누적합 배열을 구성: O(N)
 * - 각 쿼리마다 두 누적합의 차이로 구간합 계산: O(1)
 * - 전체 시간복잡도: O(N + M)
 * 
 * 시간복잡도: O(N + M)
 * - 누적합 배열 구성: O(N)
 * - M번의 구간합 쿼리 처리: O(M)
 * 
 * 공간복잡도: O(N)
 * - 원본 배열: O(N)
 * - 누적합 배열: O(N)
 * 
 * @author Algorithm Study
 * @since 2024
 */
public class boj11659 {

    public static void main(String[] args) {
        /**
         *  i ~ j
         *  -> 1 ~ j 까지의 합에서 ~ 1 ~ i - 1 까지의 합을 빼면 된다
         */

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        int[] arr = new int[n + 1];
        int[] preSum = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            arr[i] = sc.nextInt();
            preSum[i] = preSum[i - 1] + arr[i];
        }

        for(int i = 0; i<m; i++) {
            int s = sc.nextInt();
            int e = sc.nextInt();
            System.out.println(preSum[e] - preSum[s - 1]);
        }

    }


}
