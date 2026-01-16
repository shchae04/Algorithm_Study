package study.boj.daily;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 백준 15683번 - 감시
 *
 * 문제: 사무실의 정보를 바탕으로 CCTV의 방향을 설정하여 사각지대의 최소 크기를 구하는 문제
 * - N×M 크기의 직사각형 사무실에 총 K개의 CCTV가 설치되어 있음
 * - CCTV는 5가지 종류가 있으며, 회전시킬 수 있음
 * - CCTV는 벽(6)을 통과할 수 없으나 다른 CCTV는 통과 가능
 *
 * 알고리즘: 완전 탐색(Brute Force) + 시뮬레이션(Simulation) + 백트래킹(Backtracking)
 * - 모든 CCTV의 가능한 회전 방향 조합을 확인
 * - DFS를 이용해 각 CCTV의 방향을 결정하고, 모든 방향이 결정되면 사각지대 크기 계산
 *
 * 핵심 아이디어:
 * 1. CCTV 방향 정의:
 *    - 3차원 배열 `modes`를 사용하여 각 CCTV 종류별, 회전 상태별 감시 방향을 미리 매핑
 *    - 예: modes[2][0] = {1, 3} (2번 CCTV가 가로 방향일 때 우, 좌 감시)
 * 2. 백트래킹 (DFS):
 *    - 각 깊이(depth)마다 해당 순서 CCTV의 회전 방향을 선택
 *    - 모든 CCTV의 방향이 선택되면(`depth == cctvs.size()`) `simulate` 함수 호출
 * 3. 시뮬레이션:
 *    - 선택된 방향 정보를 바탕으로 감시 영역(`visited`) 표시
 *    - 감시 영역 표시 후 사각지대(0이면서 방문하지 않은 곳) 개수 세기
 *    - 최소 사각지대 값 갱신
 *
 * 시간복잡도: O(4^K × max(N, M))
 * - K는 CCTV의 개수 (최대 8개)
 * - 각 CCTV는 최대 4방향 회전 가능하므로 경우의 수는 최대 4^8 = 65,536
 * - 각 경우마다 맵 전체가 아닌 CCTV 사거리를 탐색하므로 대략 O(max(N, M)) 비례
 * - 전체 연산 횟수는 제한 시간 내 충분함
 *
 * 공간복잡도: O(N × M)
 * - 맵 정보 저장을 위한 2차원 배열
 * - 시뮬레이션을 위한 방문 배열
 *
 * 특이사항:
 * - CCTV 종류별로 가능한 회전 방향을 미리 하드코딩하여 구현 복잡도를 줄임
 * - 전체 맵을 매번 복사하지 않고 `visited` 배열을 매 시뮬레이션마다 생성하여 사용
 */
public class boj15683 {
    static int N, M;
    static int[][] map;
    static ArrayList<CCTV> cctvs = new ArrayList<>();
    static int minBlindSpot = Integer.MAX_VALUE;

    // 상, 우, 하, 좌 (시계 방향)
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    // [CCTV 종류][회전 형태][해당 형태가 보는 방향들의 인덱스]
    // 예: mode[2][0] = {1, 3} -> 2번 CCTV의 0번 회전은 우(1), 좌(3)를 본다.
    static int[][][] modes = {
            {}, // 0번 인덱스 더미
            {{0}, {1}, {2}, {3}}, // 1번 CCTV (4방향)
            {{1, 3}, {0, 2}},     // 2번 CCTV (2방향: 가로, 세로)
            {{0, 1}, {1, 2}, {2, 3}, {3, 0}}, // 3번 CCTV (4방향: 직각)
            {{0, 1, 3}, {0, 1, 2}, {1, 2, 3}, {2, 3, 0}}, // 4번 CCTV (4방향: ㅗ, ㅏ, ㅜ, ㅓ)
            {{0, 1, 2, 3}}        // 5번 CCTV (1방향)
    };

    static class CCTV {
        int x, y, type;

        CCTV(int x, int y, int type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 세로 (Y)
        M = Integer.parseInt(st.nextToken()); // 가로 (X)

        map = new int[N][M];
        int emptySpace = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] != 0 && map[i][j] != 6) {
                    cctvs.add(new CCTV(j, i, map[i][j]));
                }
                if (map[i][j] == 0) emptySpace++;
            }
        }

        // 초기 사각지대 개수가 0개면 바로 종료 (최적화)
        if (emptySpace == 0) {
            System.out.println(0);
            return;
        }

        // 각 CCTV가 어떤 방향(회전)을 보고 있는지 저장하는 배열
        // directionIdx[i] = i번째 CCTV가 선택한 modes의 인덱스
        backTracking(0, new int[cctvs.size()]);

        System.out.println(minBlindSpot);
    }

    // DFS로 각 CCTV의 회전 방향을 결정
    public static void backTracking(int depth, int[] dirIdx) {
        if (depth == cctvs.size()) {
            // 모든 CCTV의 방향이 결정되었으므로 사각지대 계산
            simulate(dirIdx);
            return;
        }

        int type = cctvs.get(depth).type;

        // 해당 CCTV 종류가 가질 수 있는 회전 가짓수만큼 반복
        // 예: 2번 CCTV는 modes[2].length == 2 (가로, 세로)
        for (int i = 0; i < modes[type].length; i++) {
            dirIdx[depth] = i;
            backTracking(depth + 1, dirIdx);
        }
    }

    // 결정된 방향(dirIdx)으로 감시 영역 체크
    public static void simulate(int[] dirIdx) {
        // 원본 맵을 건드리지 않기 위해 방문 배열 사용 (또는 맵 복사)
        boolean[][] visited = new boolean[N][M];
        int checkCount = 0;

        for (int i = 0; i < cctvs.size(); i++) {
            CCTV cctv = cctvs.get(i);
            int rotation = dirIdx[i];

            // 해당 CCTV의 현재 회전 상태에서 뻗어나가는 방향들 반복
            for (int d : modes[cctv.type][rotation]) {
                int nx = cctv.x;
                int ny = cctv.y;

                while (true) {
                    nx += dx[d];
                    ny += dy[d];

                    // 범위 벗어나거나 벽이면 중단
                    if (nx < 0 || ny < 0 || nx >= M || ny >= N || map[ny][nx] == 6) break;

                    // 빈 공간이고 아직 체크 안했으면 카운트
                    if (map[ny][nx] == 0 && !visited[ny][nx]) {
                        visited[ny][nx] = true;
                        checkCount++;
                    }
                }
            }
        }

        // 전체 빈칸 개수 계산 (매번 세지 않고 초기 전체 0 개수에서 visited만 빼도 됨)
        // 여기서는 안전하게 전체 0의 개수를 구한 뒤 checkCount를 빼는 대신,
        // 현재 로직상 '초기 0 개수'를 전역변수로 관리하고 거기서 checkCount를 빼는 것이 더 빠름.
        // 하지만 직관성을 위해 현재 맵 전체를 다시 세는 방식으로 구현 (N이 작아서 무방)
        int currentBlindSpot = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0 && !visited[i][j]) {
                    currentBlindSpot++;
                }
            }
        }

        minBlindSpot = Math.min(minBlindSpot, currentBlindSpot);
    }
}