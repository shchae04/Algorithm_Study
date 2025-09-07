package study.boj.daily;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 1010번 - 다리 놓기
 * 
 * 문제: 서쪽 N개 사이트와 동쪽 M개 사이트를 연결하는 다리 개수 구하기 (다리는 겹치면 안됨)
 * 
 * 알고리즘: 조합론 (Combination)
 * - N개 중에서 M개를 선택하는 조합 문제: C(M, N)
 * - 서쪽 N개를 동쪽 M개 중에서 선택하여 연결
 * - 다리가 겹치지 않으려면 순서대로 연결해야 함 → 조합
 * - 오버플로우 방지를 위해 곱하고 나누기를 반복
 * 
 * 시간복잡도: O(min(N, M-N))
 * 공간복잡도: O(1)
 */
public class boj1010 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine().trim());

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); // 서쪽
            int M = Integer.parseInt(st.nextToken()); // 동쪽

            System.out.println(comb(M, N));
        }
    }

    private static long comb(int n, int r) {
        if (r < 0 || r > n) return 0;
        r = Math.min(r, n - r);
        long result = 1;
        for (int i = 1; i <= r; i++) {
            result = result * (n - r + i) / i;
        }
        return result;
    }
}