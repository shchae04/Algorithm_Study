package study.boj.daily;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 *  개수 세기
 */
public class boj10807 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(br.readLine());

        int count = 0;

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (num == V) {
                count++;
            }
        }

        System.out.println(count);

        br.close();
    }
}
