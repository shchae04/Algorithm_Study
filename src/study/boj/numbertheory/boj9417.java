package study.boj.numbertheory;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * 백준 9417번 - 최대 GCD
 *
 * 문제: 주어진 수들 중 두 수의 최대공약수의 최댓값을 구하는 문제
 *
 * 알고리즘: 완전탐색 + 유클리드 호제법
 * 시간복잡도: O(K^2 * log V)
 * 공간복잡도: O(1)
 */

public class boj9417 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        while (n-- > 0) {
            String line;
            do {
                line = br.readLine().trim();
            } while (line.isEmpty());

            String[] token = line.split(" ");
            int[] nums = new int[token.length];
            for (int i = 0; i < token.length; i++) {
                nums[i] = Integer.parseInt(token[i]);
            }

            int max = 0;
            for (int i = 0; i < nums.length; i++) {
                for (int j = i + 1; j < nums.length; j++) {
                    max = Math.max(max, gcd(nums[i], nums[j]));
                }
            }
            System.out.println(max);
        }
    }

    public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}