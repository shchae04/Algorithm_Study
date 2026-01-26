package study.boj.binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj1981 {

    static final int[][] d = {{-1, 0, 1, 0}, {0, 1, 0, -1}};

    static class xy {
        int x;
        int y;

        public xy(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N;
    static int[][] board;
    static int[] arr;
    static StringTokenizer st;
    static int min = 200;
    static int max = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        board = new int[N][N];
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                min = Math.min(min, board[i][j]);
                max = Math.max(max, board[i][j]);
            }
        }
        System.out.println(binary());

    }

    // 1단계: 이분 탐색 (Binary Search)
    // "최대값과 최소값의 차이(gap)"를 기준으로 이분 탐색을 진행합니다.
    // mid는 우리가 허용할 "수치 차이"입니다.
    private static int binary() {
        int start = 0;
        int end = max - min; // 차이가 가장 클 때는 (전체 최대 - 전체 최소)
        int ret = 1_000_000_000;

        while (start <= end) {
            int mid = (start + end) / 2; // mid: 허용할 차이(gap)
            boolean flag = false;

            // 2단계: 가능한 모든 구간 확인 (Sliding Window 느낌)
            // 차이가 mid인 모든 구간(예: 0~mid, 1~1+mid, ...)을 검사합니다.
            for (int i=min; i<=max; i++) {
                // i: 구간의 하한값 (lower bound)
                // i + mid: 구간의 상한값 (upper bound)

                // 최적화: 시작점(0,0)의 값이 현재 구간(i ~ i+mid)에 포함되어야만 출발 가능
                if (i <= board[0][0] && board[0][0] <= i + mid) {
                    if (bfs(i, i + mid)) { // 해당 구간으로 도달 가능한지 확인
                        flag = true;
                        break;
                    }
                }
            }
            
            if (flag) {
                // 도달 가능하다면, 차이를 더 줄여본다 (더 빡빡한 조건 도전)
                end = mid - 1;
                ret = Math.min(ret, mid);
            } else {
                // 도달 불가능하다면, 차이를 늘려야 한다 (조건 완화)
                start = mid + 1;
            }
        }
        return ret;
    }

    // 3단계: BFS 탐색 (경로 확인)
    // 정해진 숫자 범위(lower ~ upper) 내의 숫자만 밟고 지나갈 수 있는지 확인합니다.
    private static boolean bfs(int lower, int upper) {
        boolean[][] visited = new boolean[N][N];
        Queue<xy> q = new ArrayDeque<>();
        q.add(new xy(0, 0));
        visited[0][0] = true;

        while (!q.isEmpty()) {
            xy cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + d[0][i];
                int ny = cur.y + d[1][i];

                if (IsOutBound(nx, ny) || visited[nx][ny]) {
                    continue;
                }

                // 핵심 로직: 밟으려는 땅의 숫자가 허용 범위 밖이면 이동 불가
                if (board[nx][ny] < lower || board[nx][ny] > upper) {
                    continue;
                }

                if (nx == N - 1 && ny == N - 1) {
                    return true;
                }

                q.add(new xy(nx, ny));
                visited[nx][ny] = true;
            }
        }

        return false;
    }

    private static boolean IsOutBound(int nx, int ny) {
        return nx < 0 || nx >= N || ny < 0 || ny >= N;
    }
}
