package study.boj.투포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 용액 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long[] arr = new long[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        int s = 0, e = n - 1;
        int idx1 = 0, idx2 = 0;
        long min = Long.MAX_VALUE;

        while (s < e) {
            long sum = arr[s] + arr[e];

            if (min > Math.abs(sum)) {
                min = Math.abs(sum);
                idx1 = s;
                idx2 = e;
            }

            if (sum >= 0) e--;
            else s++;
        }

        System.out.println(arr[idx1] + " " + arr[idx2]);
    }
}