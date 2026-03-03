package study.boj.implementation;

import java.io.*;
import java.util.*;

public class boj34336 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int m = Integer.parseInt(br.readLine().trim());

        int globalMax = Integer.MIN_VALUE;
        int globalMin = Integer.MAX_VALUE;

        long monthMaxSum = Long.MIN_VALUE;
        long monthMinSum = Long.MAX_VALUE;

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());

            long sum = 0;
            for (int j = 0; j < k; j++) {
                // 한 줄에 k개가 다 안 들어올 수도 있으니, 토큰이 부족하면 다음 줄을 계속 읽음
                while (!st.hasMoreTokens()) {
                    st = new StringTokenizer(br.readLine());
                }
                int s = Integer.parseInt(st.nextToken());

                sum += s;
                if (s > globalMax) globalMax = s;
                if (s < globalMin) globalMin = s;
            }

            if (sum > monthMaxSum) monthMaxSum = sum;
            if (sum < monthMinSum) monthMinSum = sum;
        }

        String sb = String.valueOf(globalMax) + '\n' +
                globalMin + '\n' +
                monthMaxSum + '\n' +
                monthMinSum + '\n';

        System.out.print(sb);
    }
}