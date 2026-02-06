package study.boj.binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj11722_dp {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] list = new int[N];
        int[] dp = new int[N];

        for (int i = 0; i < N; i++) {
            list[i] = Integer.parseInt(st.nextToken());
        }

        int maxLength = 0;

        System.out.println("Input list: " + java.util.Arrays.toString(list));

        for (int i = 0; i < N; i++) {
            dp[i] = 1; // 자기 자신의 길이.
            System.out.println("\n--- Processing index i=" + i + " (value: " + list[i] + ") ---");
            for (int j = 0; j < i; j++) {
                if (list[j] > list[i]) { // 앞의 숫자가 자기보다 클때.
                    int prevDp = dp[i];
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    System.out.println("  j=" + j + " (val: " + list[j] + "): list[j] > list[i] is true. dp[" + i + "] updated from " + prevDp + " to " + dp[i] + " (based on dp[" + j + "]=" + dp[j] + ")");
                } else {
                    System.out.println("  j=" + j + " (val: " + list[j] + "): list[j] > list[i] is false. No update.");
                }
            }
            maxLength = Math.max(maxLength, dp[i]);
            System.out.println("Current dp state: " + java.util.Arrays.toString(dp));
        }

        System.out.println("\nFinal Max Length: " + maxLength);
    }
}
