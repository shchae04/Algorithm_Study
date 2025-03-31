package study.boj.two;

import java.util.Scanner;

public class 적어도대부분의배수 {

    public static void main(String[] args) {
        //3개로 나누어 지는 가장 작은 자연수
        Scanner sc = new Scanner(System.in);
        int[] arr = new int[5];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = sc.nextInt();
        }

        for (int result = 1; result <= Integer.MAX_VALUE; result++) {
            int count = 0;
            for (int i = 0; i < 5; i++) {
                if (result % arr[i] == 0) {
                    count++;
                }
            }
            if (count >= 3) {
                System.out.println(result);
                break;
            }
        }
    }
}
