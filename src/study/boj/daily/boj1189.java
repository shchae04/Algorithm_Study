package study.boj.daily;

import java.util.Scanner;

/**
 * 백준 1189번 - 컴백홈
 * 
 * 문제: R×C 격자에서 왼쪽 하단에서 오른쪽 상단으로 정확히 K번만에 도착하는 경로의 개수
 * 
 * 알고리즘: DFS + 백트래킹
 * - (R-1, 0)에서 시작하여 (0, C-1)에 도착
 * - 장애물 'T'는 지나갈 수 없음
 * - 방문한 곳은 다시 방문할 수 없음 (백트래킹 필요)
 * - 정확히 K번 이동해서 도착하는 경우의 수 카운트
 * 
 * 시간복잡도: O(4^K) (최악의 경우 모든 경로 탐색)
 * 공간복잡도: O(R × C) (방문 배열 + 맵 저장)
 */
public class boj1189 {

    static int R, C, K;
    static char[][] map;
    static int[][] visited;
    static int answer;

    static int[] moveR = {1, -1, 0, 0};
    static int[] moveC = {0, 0, 1, -1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        R = sc.nextInt();
        C = sc.nextInt();
        K = sc.nextInt();
        map = new char[R][C];
        visited = new int[R][C];

        for (int i = 0; i < R; i++) {
            String s = sc.next();
            for (int j = 0; j < C; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        visited[R - 1][0] = 1;
        dfs(R - 1, 0, 1);

        System.out.println(answer);

    }

    static void dfs(int r, int c, int moved) {
        //도착
        if (r == 0 && c == C - 1) {
            if (moved == K)
                answer++;
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nextR = r + moveR[i];
            int nextC = c + moveC[i];
            if (nextR < 0 || nextR >= R || nextC < 0 || nextC >= C)
                continue;
            if (visited[nextR][nextC] == 1 || map[nextR][nextC] == 'T')
                continue;
            visited[nextR][nextC] = 1;
            dfs(nextR, nextC, moved + 1);
            visited[nextR][nextC] = 0;

        }

    }

}
