package study.boj.daily;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj11650 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[][] arrays = new int[n][2];

        for (int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            arrays[i][0] = Integer.parseInt(st.nextToken());
            arrays[i][1] = Integer.parseInt(st.nextToken());
        }

        //x좌표가 다르면 x좌표 기준 오름차순 정렬
        //x좌표가 같으면 y좌표 기준 오름차순 정렬
        Arrays.sort(arrays, (num1, num2) -> num1[0] != num2[0] ? num1[0] - num2[0] : num1[1] - num2[1]);

        for (int i=0; i<n; i++) {
            bw.write(arrays[i][0] + " " + arrays[i][1] + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
