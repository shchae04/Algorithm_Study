package study.boj.string;
import java.util.*;
import java.io.*;

/**
 * 백준 2508번 - 사탕 박사 고창영
 * 
 * 문제: 2차원 배열에서 사탕 모양을 찾는 문제
 * - 가로 사탕: '>o<' 패턴
 * - 세로 사탕: 'v' 위에 'o', 그 아래에 '^' 패턴
 * 
 * 알고리즘: 문자열 패턴 매칭
 * - 각 테스트 케이스에 대해 2차원 배열 입력 받기
 * - 가로 방향: 연속된 3개 문자가 '>o<'인지 확인
 * - 세로 방향: 연속된 3개 문자가 'vo^'인지 확인
 * - 찾은 사탕의 개수 누적
 * 
 * 시간복잡도: O(R*C) - R: 행 수, C: 열 수
 * 공간복잡도: O(R*C)
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