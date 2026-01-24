package study.boj.bruteforce;

import java.util.Scanner;

public class boj2851 {
    public static void main(String[] args) {
        // 버섯 10개.
        // 버섯의 점수의 합 100에 가깝게 만들려고 함.
        Scanner sc = new Scanner(System.in);
        int[] arr = new int[10];

        int sum = 0;
        int result = 0;

        for (int i = 0; i < 10; i++) {
            arr[i] = sc.nextInt();
        }
        for (int i = 0; i < 10; i++) {
            sum += arr[i];
            if (Math.abs(100 - sum) <= Math.abs(100 - result)) {
                result = sum;
            } else {
                break;
            }
        }
        System.out.println(result);
    }
}
