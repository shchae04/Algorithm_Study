package study.boj.daily;

import java.io.*;
import java.util.StringTokenizer;

/**
 * 킹 퀸 룩 비숍 나이트 폰
 */
public class boj3003 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = {1, 1, 2, 2, 2, 8};

        for (int i = 0; i < arr.length; i++) {
            bw.write(arr[i] - Integer.parseInt(st.nextToken()) + " ");
        }
        bw.flush();
        bw.close();
        
    }
}
