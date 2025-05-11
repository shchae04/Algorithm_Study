package study.boj.누적합;

import java.util.Scanner;

public class 점수따먹기 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); // 행
        int M = sc.nextInt(); // 열
        int[][] prefixSum = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                int value = sc.nextInt();
                prefixSum[i][j] = value
                        + prefixSum[i - 1][j]
                        + prefixSum[i][j - 1]
                        - prefixSum[i - 1][j - 1];
            }
        }

        int maxSum = Integer.MIN_VALUE;

        for (int x1 = 1; x1 <= N; x1++) {
            for (int y1 = 1; y1 <= M; y1++) {
                for (int x2 = x1; x2 <= N; x2++) {
                    for (int y2 = y1; y2 <= M; y2++) {
                        // * * * *
                        // * * * *
                        // * * * *
                        int subSum = prefixSum[x2][y2]
                                - prefixSum[x1 - 1][y2]
                                - prefixSum[x2][y1  - 1]
                                + prefixSum[x1 - 1][y1 - 1]; //중복

                        maxSum = Math.max(maxSum, subSum);
                    }
                }
            }
        }

        System.out.println(maxSum);
    }
}