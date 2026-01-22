package study.boj.string;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * 백준 25332번 - 수들의 합 8
 * 
 * 문제: 두 배열 A, B가 주어졌을 때, 구간 [i, j]에서 A의 합과 B의 합이 같은 경우의 수 구하기
 * 
 * 알고리즘: 누적합 + 해시맵
 * - 차이 배열 diff[i] = A[i] - B[i] 생성
 * - 누적합 배열로 구간 합을 O(1)에 구하기
 * - 구간 [i, j]에서 A의 합 == B의 합 ⟺ diff 배열의 구간 합 == 0
 * - 누적합이 같은 두 지점이 있으면, 그 사이 구간의 합은 0
 * - 해시맵으로 같은 누적합 값의 개수를 세어 조합 계산
 * 
 * 시간복잡도: O(N)
 * 공간복잡도: O(N)
 */
public class boj25332 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        String[] arrA = br.readLine().split(" ");
        String[] arrB = br.readLine().split(" ");

        int[] A = new int[N];
        int[] B = new int[N];

        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(arrA[i]);
            B[i] = Integer.parseInt(arrB[i]);
        }

        int[] diff = new int[N];
        for (int i = 0; i < N; i++) {
            diff[i] = A[i] - B[i];
        }

        long[] preSum = new long[N + 1]; // 0-indexed
        for (int i = 0; i < N; i++) {
            preSum[i + 1] = preSum[i] + diff[i];
        }

        Map<Long, Integer> countMap = new HashMap<>();

        long result = 0;

        for (int i = 0; i <= N; i++) {
            long sum = preSum[i];
            result += countMap.getOrDefault(sum, 0);
            countMap.put(sum, countMap.getOrDefault(sum, 0) + 1);
        }
        System.out.println(result);

    }
}
