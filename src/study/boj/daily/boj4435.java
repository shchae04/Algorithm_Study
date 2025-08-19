package study.boj.daily;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * 중간계 전쟁
 */
public class boj4435 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
        StringTokenizer st;

        BufferedWriter bw = new BufferedWriter(new java.io.OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine().trim());
        int[] scoreA = {1, 2, 3, 3, 4, 10};
        int[] scoreB = {1, 2, 2, 2, 3, 5, 10};

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= N; i++) {
            // 선(Good) 6종족
            st = new StringTokenizer(br.readLine());
            int sumA = 0;
            for (int j = 0; j < 6; j++) {
                sumA += Integer.parseInt(st.nextToken()) * scoreA[j];
            }

            // 악(Evil) 7종족
            st = new StringTokenizer(br.readLine());
            int sumB = 0;
            for (int j = 0; j < 7; j++) {
                sumB += Integer.parseInt(st.nextToken()) * scoreB[j];
            }

            sb.append("Battle ").append(i).append(": ");
            if (sumA > sumB) {
                sb.append("Good triumphs over Evil");
            } else if (sumA < sumB) {
                sb.append("Evil eradicates all trace of Good");
            } else {
                sb.append("No victor on this battle field");
            }
            sb.append('\n');
        }

        bw.write(sb.toString());
        bw.flush();
    }
}