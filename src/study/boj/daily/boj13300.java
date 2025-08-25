package study.boj.daily;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 방 배정
 */
public class boj13300 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] arr = new int[2][7];
        int count = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());

            arr[S][Y]++;
        }

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j <= 6; j++) {
                if (arr[i][j] == 0) {
                    continue;
                }
                if (arr[i][j] % K != 0) {
                    count++;
                }
                count += arr[i][j] / K;
            }
        }
        System.out.println(count);
    }
}
