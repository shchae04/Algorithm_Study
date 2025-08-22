package study.boj.daily;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 방 번호
 */
public class boj1475 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();

        int[] arr = new int[10];

        for (char c : str.toCharArray()) {
            int num = c - '0';

            if (num == 9) {
                num = 6;
            }

            arr[num]++;
        }

        arr[6] = arr[6] / 2 + arr[6] % 2;

        Arrays.sort(arr);

        System.out.println(arr[9]);
    }
}
