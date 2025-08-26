package study.boj.daily;

import java.util.Scanner;

/**
 * 공 넣기
 */
public class boj10810 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int [] arr = new int[N];

        for (int i=0; i<M; i++) {
            int I = sc.nextInt();
            int J = sc.nextInt();
            int K = sc.nextInt();

            for (int j=I - 1; j<J; j++) {
                arr[j] = K;
            }
        }

        for (int k=0; k<arr.length; k++) {
            System.out.print(arr[k] + " ");
        }

    }
}
