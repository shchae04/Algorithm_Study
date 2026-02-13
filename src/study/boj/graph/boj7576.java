package study.boj.graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

// 좌표를 저장하기 위한 클래스
class Pair2 {
    int x, y;

    Pair2(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class boj7576 {
    // 상하좌우 이동을 위한 방향 배열
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int m = Integer.parseInt(st.nextToken()); // 가로 칸 수
        int n = Integer.parseInt(st.nextToken()); // 세로 칸 수

        int[][] arr = new int[n][m];    // 토마토 상자 상태
        int[][] dist = new int[n][m];   // 각 토마토가 익기까지 걸린 일수 저장
        
        // dist 배열을 -1로 초기화 (방문하지 않음을 의미)
        for (int i = 0; i < n; i++) Arrays.fill(dist[i], -1);

        Deque<Pair2> q = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                // 이미 익어있는 토마토(1)를 모두 큐에 삽입 (동시 탐색의 시작점들)
                if (arr[i][j] == 1) {
                    q.add(new Pair2(i, j));
                    dist[i][j] = 0; // 익어있는 상태이므로 0일차
                }
            }
        }

        // BFS 시작
        while (!q.isEmpty()) {
            Pair2 p = q.poll();
            int x = p.x, y = p.y;

            // 현재 위치에서 상하좌우 확인
            for (int dir = 0; dir < 4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                // 범위를 벗어나면 무시
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                // 익지 않은 토마토(0)가 아니거나 이미 방문(dist != -1)했다면 무시
                if (arr[nx][ny] != 0) continue;
                if (dist[nx][ny] != -1) continue;

                // 다음 토마토가 익는 일수는 현재 일수 + 1
                dist[nx][ny] = dist[x][y] + 1;
                q.add(new Pair2(nx, ny));
            }
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 탐색이 끝났는데도 익지 않은 토마토(0)가 남아있다면 -1 출력
                if (arr[i][j] == 0 && dist[i][j] == -1) {
                    System.out.println(-1);
                    return;
                }
                // 전체 토마토가 익는 데 걸린 최대 일수 갱신
                ans = Math.max(ans, dist[i][j]);
            }
        }

        System.out.println(ans); // 결과 출력
    }
}
