package study.boj.daily;

import java.util.*;
import java.io.*;

/**
 * 사탕 박사 고창영
 */
public class boj2508 {
    public static void main(String[] args) throws IOException {
        try (final BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int t = Integer.parseInt(br.readLine());

            for (int i = 0; i < t; i++) {
                int result = 0;
                br.readLine();
                StringTokenizer st = new StringTokenizer(br.readLine());
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                char[][] box = new char[r][c];

                for (int j = 0; j < r; j++) {
                    box[j] = br.readLine().toCharArray();
                }

                // 가로 사탕 찾기
                for (char[] chars : box) {
                    for (int k = 0; k < chars.length - 2; k++) {
                        if (chars[k] == '>' && chars[k + 1] == 'o' && chars[k + 2] == '<') {
                            result++;
                        }
                    }
                }

                // 세로 사탕 찾기
                for (int j = 0; j < c; j++) {
                    for (int k = 0; k < r - 2; k++) {
                        if (box[k][j] == 'v' && box[k + 1][j] == 'o' && box[k + 2][j] == '^') {
                            result++;
                        }
                    }
                }

                System.out.println(result);
            }

        }

    }

}