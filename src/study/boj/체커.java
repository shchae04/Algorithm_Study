package study.boj;

import java.io.*;
import java.util.*;

public class 체커 {
    public static void main(String[] args) throws IOException {
        // 빠른 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        int[] x = new int[n];
        int[] y = new int[n];
        int[] result = new int[n];

        Arrays.fill(result, -1);

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            x[i] = Integer.parseInt(st.nextToken());
            y[i] = Integer.parseInt(st.nextToken());
        }

        // 가능한 모든 위치(x, y) 탐색
        for (int xIdx : x) {
            for (int yIdx : y) {
                int[] dist = new int[n];

                for (int k = 0; k < n; k++) {
                    dist[k] = Math.abs(x[k] - xIdx) + Math.abs(y[k] - yIdx);
                }

                Arrays.sort(dist);

                int tmp = 0;
                for (int m = 0; m < n; m++) {
                    tmp += dist[m];
                    if (result[m] == -1) {
                        result[m] = tmp;
                    } else {
                        result[m] = Math.min(result[m], tmp);
                    }
                }
            }
        }

        // 빠른 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(result[i]);
            if (i != n - 1) sb.append(" ");
        }

        System.out.println(sb);
    }
}