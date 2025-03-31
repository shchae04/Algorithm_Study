package study.boj.two;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 슈퍼마리오 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = new int[10];

        for (int i = 0; i < 10; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int sum = 0;
        int result = 0;

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