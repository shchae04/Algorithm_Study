package study.boj.누적합;
import java.util.Scanner;

public class 나는정말휘파람을못불어 {
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