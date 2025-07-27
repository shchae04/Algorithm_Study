package study.boj.daily;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class boj1516 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new java.io.OutputStreamWriter(System.out));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        // 걸리는 시간, 건물 번호
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken()); // time
            arr[i][1] = Integer.parseInt(st.nextToken()); // no
        }


    }
}
