package study.boj.정수론;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class 최대GCD {
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