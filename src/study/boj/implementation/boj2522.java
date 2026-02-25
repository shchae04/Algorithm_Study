package study.boj.implementation;

import java.io.*;

public class boj2522 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        for (int i = 1; i <= N; i++) {
            for (int j = N - i; j > 0; j--) {
                sb.append(" ");
            }
            for (int j = 1; j <= i; j++) {
                sb.append("*");
            }
            sb.append("\n");
        }

        for (int i = 1; i <= N - 1; i++) {
            for (int j = 1; j <= i; j++) {
                sb.append(" ");
            }
            for (int j = 1; j <= N - i; j++) {
                sb.append("*");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
