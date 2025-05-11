package study.boj.누적합;

import java.util.Scanner;

public class 구간합구하기4 {

    public static void main(String[] args) {
        /**
         *  i ~ j
         *  -> 1 ~ j 까지의 합에서 ~ 1 ~ i - 1 까지의 합을 빼면 된다
         */

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        int[] arr = new int[n + 1];
        int[] preSum = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            arr[i] = sc.nextInt();
            preSum[i] = preSum[i - 1] + arr[i];
        }

        for(int i = 0; i<m; i++) {
            int s = sc.nextInt();
            int e = sc.nextInt();
            System.out.println(preSum[e] - preSum[s - 1]);
        }

    }


}
