package study.boj.daily;

import java.io.*;

/**
 * 과제 안 내신 분?..
 */
public class boj5597 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        boolean[] arr = new boolean[31]; // 1 ~ 30

        for (int i = 1; i <= 28; i++) {
            int n = Integer.parseInt(br.readLine());
            arr[n] = true;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 30; i++) {
            if (!arr[i]) {
                sb.append(i).append("\n");
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
