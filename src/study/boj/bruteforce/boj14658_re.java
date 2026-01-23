package study.boj.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj14658_re {

    public static void main(String[] args) throws IOException {
        // 남규 영훈 택희
        // i j k
        // i >= j + 2
        // k % 2 == 0

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int count = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                for (int k = 1; k <= N; k++) {
                    if (i + j + k == N) {
                        if (i >= j + 2) {
                            if (k % 2 == 0) count++;
                        }
                    }
                }
            }
        }
        System.out.println(count);
    }
}
