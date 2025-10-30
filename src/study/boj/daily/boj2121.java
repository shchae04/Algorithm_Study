package study.boj.daily;

import java.io.*;
import java.util.*;

/**
 * 백준 2121번 - 넷이 놀기
 *
 * 문제: N개의 점 중 4개를 선택하여 축에 평행한 직사각형을 만들 때,
 *       가로 길이 A, 세로 길이 B인 직사각형을 만들 수 있는 경우의 수 구하기
 *
 * 알고리즘: HashSet을 이용한 좌표 존재 여부 확인
 * 1. 직사각형의 4개 꼭짓점 관계:
 *    - 왼쪽 아래: (x, y)
 *    - 오른쪽 아래: (x+A, y)
 *    - 왼쪽 위: (x, y+B)
 *    - 오른쪽 위: (x+A, y+B)
 *
 * 2. 모든 점을 HashSet에 저장하여 O(1)로 존재 여부 확인
 * 3. 각 점을 왼쪽 아래 꼭짓점으로 가정하고, 나머지 3개 점이 존재하는지 확인
 * 4. 4개 점이 모두 존재하면 카운트 증가
 *
 * 핵심 아이디어:
 * - 좌표를 "x,y" 문자열 형태로 HashSet에 저장
 * - java.awt.Point 대신 String 사용 (백준 환경 호환성)
 * - 각 점마다 O(1) 확인으로 전체 O(N) 시간복잡도 달성
 *
 * 시간복잡도: O(N) - N개 점에 대해 각각 O(1) 확인
 * 공간복잡도: O(N) - HashSet에 N개의 좌표 문자열 저장
 *
 * 예제:
 * 입력: 6개 점, 가로 2 세로 3
 * (0,0), (2,0), (0,3), (2,3), (4,0), (4,3)
 *
 * 경우 1: (0,0) 기준 -> (2,0), (0,3), (2,3) 존재 ✓
 * 경우 2: (2,0) 기준 -> (4,0), (2,3), (4,3) 존재 ✓
 * 출력: 2
 */
public class boj2121 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력: 점의 개수
        final int N = Integer.parseInt(br.readLine());

        // 입력: 직사각형의 가로(A), 세로(B) 길이
        String[] input = br.readLine().split(" ");
        final int A = Integer.parseInt(input[0]);
        final int B = Integer.parseInt(input[1]);

        // 점들을 "x,y" 문자열 형태로 HashSet에 저장
        Set<String> set = new HashSet<>();
        int[][] points = new int[N][2];

        for (int i = 0; i < N; ++i) {
            input = br.readLine().split(" ");
            final int x = Integer.parseInt(input[0]);
            final int y = Integer.parseInt(input[1]);

            points[i][0] = x;
            points[i][1] = y;
            set.add(x + "," + y);  // O(1) 조회를 위한 HashSet 저장
        }

        long cnt = 0;

        // 각 점을 직사각형의 왼쪽 아래 꼭짓점으로 가정
        for (int i = 0; i < N; i++) {
            int x = points[i][0];
            int y = points[i][1];

            // 나머지 3개 꼭짓점이 모두 존재하는지 확인
            // - 오른쪽 아래: (x+A, y)
            // - 왼쪽 위: (x, y+B)
            // - 오른쪽 위: (x+A, y+B)
            if (set.contains((x + A) + "," + y) &&
                set.contains(x + "," + (y + B)) &&
                set.contains((x + A) + "," + (y + B))) {
                ++cnt;
            }
        }

        System.out.println(cnt);
    }
}
