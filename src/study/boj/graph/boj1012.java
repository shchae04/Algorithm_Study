package study.boj.graph;

import java.util.Scanner;

/**
 * 백준 1012 유기농 배추
 * 배추가 심어진 칸(1)들의 상하좌우 연결 요소 개수를 DFS로 계산한다.
 */
public class boj1012 {
    static int width, height, k, answer, t;
    static int[][] dis;
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0}; //우 좌 상 하
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        t = sc.nextInt();
        for (int v = 0; v < t; v++) {
            width = sc.nextInt();
            height = sc.nextInt();
            k = sc.nextInt();
            dis = new int[width][height];
            visited = new boolean[width][height];
            answer = 0;
            for (int i = 0; i < k; i++) {
                int a = sc.nextInt();
                int b = sc.nextInt();
                dis[a][b] = 1;
            }

            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    if (dis[i][j] == 1 && !visited[i][j]) {
                        answer++;
                        dfs(i, j);
                    }
                }
            }
            System.out.println(answer);
        }
    }

    public static void dfs(int curX, int curY) {
        visited[curX][curY] = true;
        for (int i = 0; i < 4; i++) {
            int nx = curX + dx[i];
            int ny = curY + dy[i];
            if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                if (dis[nx][ny] == 1 && !visited[nx][ny]) {
                    dfs(nx, ny);

                }
            }
        }
    }


}
