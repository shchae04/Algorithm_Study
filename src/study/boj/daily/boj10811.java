package study.boj.daily;

import java.util.Scanner;

/**
 * 바구니 뒤집기
 */
public class boj10811 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();

        int[] arr = new int[N];
        for (int i=0; i<N; i++) {
            arr[i] = i + 1;
        }

        for (int i=0; i<M; i++) {
            int left = sc.nextInt() - 1;
            int right = sc.nextInt() - 1;

            while (left < right) {
                int temp = arr[left];
                arr[left++] = arr[right];
                arr[right--] = temp;
            }
        }
        for (int i=0; i<N; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
