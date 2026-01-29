package study.boj.binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj1300_re {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        long min = 1;
        long max = K;

        while (min < max) {
            long mid = (min + max) / 2;
            long count = 0;

            for (int i = 1; i <= N; i++) {
                count += Math.min(mid / i, N);
            }

            if (K <= count) {
                max = mid;
            } else {
                min = mid + 1;
            }
        }

        System.out.println(min);
        br.close();
    }
}
