package study.boj.dp;

import java.util.Scanner;

/**
 * 백준 1937번 - 욕심쟁이 판다
 *
 * 문제:
 * 대나무 숲에서 판다가 상하좌우로 이동할 때, 현재 칸보다 대나무 양이 많은 칸으로만 이동할 수 있다.
 * 판다가 가장 오래 살아남을 수 있는 최대 이동 칸 수를 구한다.
 *
 * 알고리즘:
 * DFS + 메모이제이션
 * - 각 칸에서 출발했을 때 이동할 수 있는 최대 칸 수를 dfs로 계산한다.
 * - 이미 계산한 칸은 dp 배열에 저장해 재사용하여 중복 탐색을 막는다.
 *
 * 시간복잡도: O(N^2)
 * 공간복잡도: O(N^2)
 */
public class boj1937 {
    static int n;
    static int[][] map;
    static int[][] dp;

    static final int[] DR = {0, 0, 1, -1};
    static final int[] DC = {-1, 1, 0, 0};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        map = new int[n][n];
        dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        int answer = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                answer = Math.max(answer, dfs(i, j));
            }
        }

        System.out.println(answer);
        sc.close();
    }

    static int dfs(int r, int c) {
        if (dp[r][c] != 0) {
            return dp[r][c];
        }

        // 현재 칸에서 시작하는 경우, 최소 1칸은 방문할 수 있다.
        dp[r][c] = 1;

        for (int d = 0; d < 4; d++) {
            int nr = r + DR[d];
            int nc = c + DC[d];

            if (nr < 0 || nr >= n || nc < 0 || nc >= n) {
                continue;
            }

            if (map[nr][nc] <= map[r][c]) {
                continue;
            }

            dp[r][c] = Math.max(dp[r][c], dfs(nr, nc) + 1);
        }

        return dp[r][c];
    }
}
