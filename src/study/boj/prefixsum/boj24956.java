package study.boj.prefixsum;
import java.util.Scanner;

/**
 * 백준 24956번 - 나는 정말 휘파람을 못 불어
 *
 * 문제: 문자열에서 패턴의 부분수열 개수를 세는 문제
 *
 * 알고리즘: DP/누적 카운팅
 * 시간복잡도: O(N)
 * 공간복잡도: O(1)
 */

public class boj24956 {
    static final int MOD = 1_000_000_007;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String s = sc.next();

        long countW = 0;
        long countWH = 0;
        long countWHE = 0;
        long countWHEE = 0;

        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);

            if (ch == 'W') {
                countW = (countW + 1) % MOD;
            } else if (ch == 'H') {
                countWH = (countWH + countW) % MOD;
            } else if (ch == 'E') {
                countWHEE = (2 * countWHEE + countWHE) % MOD;
                countWHE = (countWHE + countWH) % MOD;
            }
        }

        System.out.println(countWHEE);
    }
}