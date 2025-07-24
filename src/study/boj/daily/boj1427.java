package study.boj.daily;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj1427 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        int[] count = new int[10];

        for (char c : str.toCharArray()) {
            count[c - '0']++;
        }

        StringBuilder sb = new StringBuilder(str.length());
        for (int i = 9; i >= 0; i--) {
            while (count[i] > 0) {
                sb.append(i);
                count[i]--;
            }
        }

        System.out.println(sb);
    }
}