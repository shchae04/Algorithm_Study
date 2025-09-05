package study.boj.daily;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 백준 2143번: 두 배열의 합
 * 
 * 두 개의 정수 배열에서 각각 부배열을 선택하여, 
 * 두 부배열의 합이 주어진 목표값 T가 되는 경우의 수를 찾는 문제
 * 
 * 알고리즘:
 * 1. 각 배열의 모든 가능한 연속 부배열의 합을 계산
 * 2. 생성된 두 합 배열을 정렬
 * 3. 투 포인터 기법으로 목표값을 만족하는 쌍을 효율적으로 탐색
 * 
 * 시간 복잡도: O(n² + m² + (n²+m²)log(n²+m²))
 * 공간 복잡도: O(n² + m²)
 */
public class boj2143 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long T = Long.parseLong(br.readLine().trim());

        int n = Integer.parseInt(br.readLine().trim());
        int[] A = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int m = Integer.parseInt(br.readLine().trim());
        int[] B = new int[m];

        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            B[i] = Integer.parseInt(st2.nextToken());
        }

        long[] SA = allSubarraySums(A);
        long[] SB = allSubarraySums(B);

        Arrays.sort(SA);
        Arrays.sort(SB);

        long ans = countPairsEqualsT(SA, SB, T);
        System.out.println(ans);
    }

    /**
     * 주어진 배열의 모든 연속 부배열의 합을 계산
     * 시간 복잡도: O(n²), 공간 복잡도: O(n²)
     * 
     * @param arr 입력 배열
     * @return 모든 부배열 합을 담은 배열 (크기: n(n+1)/2)
     */
    private static long[] allSubarraySums(int[] arr) {
        int n = arr.length;
        int size = n * (n + 1) / 2;
        long[] sums = new long[size];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            long s = 0;
            for (int j = i; j < n; j++) {
                s += arr[j];
                sums[idx++] = s;
            }
        }
        return sums;
    }

    /**
     * 투 포인터 기법으로 합이 T가 되는 쌍의 개수 계산
     * 시간 복잡도: O(n² + m²), 공간 복잡도: O(1)
     * 
     * @param SA 배열 A의 정렬된 부배열 합
     * @param SB 배열 B의 정렬된 부배열 합  
     * @param T 목표값
     * @return 조건을 만족하는 쌍의 개수
     */
    private static long countPairsEqualsT(long[] SA, long[] SB, long T) {
        int i = 0;              // SA: 앞에서부터
        int j = SB.length - 1;  // SB: 뒤에서부터
        long result = 0;

        while (i < SA.length && j >= 0) {
            long sum = SA[i] + SB[j];
            if (sum == T) {
                long aVal = SA[i];
                long bVal = SB[j];
                long cntA = 0, cntB = 0;

                while (i < SA.length && SA[i] == aVal) {
                    cntA++;
                    i++;
                }
                while (j >= 0 && SB[j] == bVal) {
                    cntB++;
                    j--;
                }

                result += cntA * cntB;
            } else if (sum < T) {
                i++;
            } else {
                j--;
            }
        }
        return result;
    }
}