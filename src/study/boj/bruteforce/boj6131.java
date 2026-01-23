package study.boj.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj6131 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 두 양의 정수 A, B (1<=A, B<=500)
        // A^2 = B^2 + N

        int count = 0;
        for (int A = 1; A <= 500; A++) {
            for (int B = 1; B <= 500; B++) {
                if (A * A == B * B + N) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }
}
