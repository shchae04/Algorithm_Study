package study.boj.string;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;


/**
 * 백준 17144번 - 미세먼지 안녕!
 *
 * 문제: 시간 경과에 따른 미세먼지 확산과 공기청정기 동작을 시뮬레이션하는 문제
 *
 * 알고리즘: 시뮬레이션
 * 시간복잡도: O(R * C * T)
 * 공간복잡도: O(R * C)
 */

public class boj17144_my {

    static int R, C, T;
    static int[][] map;
    static int air1, air2;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // R행 C열, T초
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[R][C];

        // 배열 입력 완료
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 공기청정기 airPurify
        findAirPurify();

        // T 초 동안 dust가 spread, purify 됨.
        for (int i = 0; i < T; i++) {
            map = spread();
            purify();
        }

        count();

    }

    private static void count() {

        int result = 0;

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                result += map[i][j];
            }
        }

        System.out.println(result + 2);
    }

    // Spread Dust
    private static int[][] spread() {
        int[][] tMap = new int[R][C];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                // 공기 청정기 위치는 그대로 유지
                if (map[i][j] == -1) {
                    tMap[i][j] = -1;
                    continue;
                }

                // 현재 칸의 먼지 일단 더하기
                tMap[i][j] += map[i][j];

                // 먼지가 5보다 작으면 확산될 양이 0이므로 패스
                if (map[i][j] < 5) continue;

                int spreadAmount = map[i][j] / 5;

                // 4방향 확산
                for (int k = 0; k < 4; k++) {
                    int ny = i + dy[k];
                    int nx = j + dx[k];

                    // 경계 체크 및 공기청정기 체크
                    if (ny >= 0 && ny < R && nx >= 0 && nx < C && map[ny][nx] != -1) {
                        tMap[ny][nx] += spreadAmount;
                        tMap[i][j] -= spreadAmount;
                    }
                }
            }
        }
        return tMap;
    }

    private static void purify() {
        // 위쪽 공기청정기 (반시계)
        // 바람 방향: 우 -> 상 -> 좌 -> 하
        int[] topDy = {0, -1, 0, 1};
        int[] topDx = {1, 0, -1, 0};
        circulate(air1, topDy, topDx);

        // 아래쪽 공기청정기 (시계)
        // 바람 방향: 우 -> 하 -> 좌 -> 상
        int[] bottomDy = {0, 1, 0, -1};
        int[] bottomDx = {1, 0, -1, 0};
        circulate(air2, bottomDy, bottomDx);
    }

    private static void circulate(int airRow, int[] dy, int[] dx) {
        int y = airRow;
        int x = 1; // 공기청정기 바로 오른쪽에서 시작
        int preDust = 0; // 공기청정기에서 나오는 첫 바람은 0
        
        // 시작 위치(공기청정기 바로 옆) 처리:
        // 원래 있던 먼지는 밀려나야 하므로 preDust에 저장하고, 그 자리는 0으로 만듦
        // (단, 여기서는 어차피 preDust=0인 상태로 다음 칸에 덮어쓰면서 시작해야 하므로 로직 순서 주의)
        
        // 올바른 로직: 
        // 1. 공기청정기 바로 옆 칸(y, x)은 깨끗한 바람(0)이 들어와야 함.
        // 2. 하지만 그 자리에 있던 먼지는 '다음 칸'으로 밀려나야 함.
        // 따라서 현재 칸(y, x)의 먼지를 미리 preDust로 잡고, 현재 칸을 0으로 만든 뒤 루프 시작.
        preDust = map[y][x];
        map[y][x] = 0;

        // 4방향으로 돌면서 먼지를 밀어냄
        for (int k = 0; k < 4; k++) {
            while (true) {
                int ny = y + dy[k];
                int nx = x + dx[k];

                // 범위를 벗어나거나 공기청정기로 돌아오면 다음 방향으로
                if (ny < 0 || ny >= R || nx < 0 || nx >= C || map[ny][nx] == -1) {
                    break;
                }

                // 다음 칸의 먼지를 임시 저장(temp)
                int temp = map[ny][nx];
                // 다음 칸에 이전 먼지(preDust)를 넣음
                map[ny][nx] = preDust;
                // 밀려난 먼지(temp)를 다시 손에 듦
                preDust = temp;

                // 이동
                y = ny;
                x = nx;
            }
        }
    }

    private static void findAirPurify() {
        for (int i = 0; i < R; i++) {
            if (map[i][0] == -1) {
                air1 = i;
                air2 = i + 1; // 다음 칸.
                break;
            }
        }
    }

}
