package study.boj.daily;

import java.io.*;
import java.util.*;

public class boj30679 {
    static int[][] map = new int[101][101];
    static boolean[][][] isVisited = new boolean[101][101][4];
    static int N, M;
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};
    static List<Integer> answer = new ArrayList<>();

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static boolean dfs(int y, int x, int dir) {
        int dist = map[y][x];
        int ny = dy[dir] * dist + y;
        int nx = dx[dir] * dist + x;

        if (ny < 0 || nx < 0 || ny >= N || nx >= M) {
            return false;
        }

        if (isVisited[ny][nx][dir]) {
            return true;
        }

        isVisited[ny][nx][dir] = true;

        dir = (dir + 1) % 4;

        return dfs(ny, nx, dir);
    }

    static void solve() {
        for (int i = 0; i < N; i++) {
            // 3차원 배열 초기화
            for (int j = 0; j < 101; j++) {
                for (int k = 0; k < 101; k++) {
                    Arrays.fill(isVisited[j][k], false);
                }
            }

            if (dfs(i, 0, 0)) {
                answer.add(i + 1);
            }
        }

        System.out.println(answer.size());

        for (int i : answer) {
            System.out.print(i + " ");
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}