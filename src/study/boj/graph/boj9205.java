package study.boj.graph;
import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 9205번 - 맥주 마시면서 걸어가기
 *
 * 문제: 상근이가 맥주 20병을 박스에 담아 락 페스티벌에 가려고 한다.
 * - 맥주 한 병을 마시면 50미터를 갈 수 있다. (최대 이동 거리 50미터 * 20병 = 1000미터)
 * - 편의점에 들르면 빈 병을 버리고 새 맥주 병을 채워 다시 20병을 만들 수 있다.
 * - 집(출발지) -> 편의점들 -> 페스티벌(도착지)로 이동할 수 있는지 판별하라.
 *
 * 알고리즘: 그래프 탐색 (BFS)
 * - 각 지점(집, 편의점들, 페스티벌)을 그래프의 '노드'로 본다.
 * - 두 지점 사이의 맨해튼 거리(|x1-x2| + |y1-y2|)가 1000미터 이하라면 이동 가능하다.
 * - 즉, 거리가 1000 이하인 지점들끼리 '간선'이 연결된 그래프라고 볼 수 있다.
 * - 집에서 시작하여 BFS를 통해 페스티벌에 도달할 수 있는지 탐색한다.
 *
 * 시간복잡도: O(N^2)
 * - 노드(편의점)의 개수 N은 최대 100. (총 노드 수 V = N + 2)
 * - 각 노드에서 다른 모든 노드까지의 거리를 확인하여 간선 연결 여부를 판단.
 * - 간선(E)은 최대 V^2개까지 가능하므로, BFS 탐색의 시간복잡도는 O(V + E) = O(N^2).
 * - N이 작으므로 플로이드-워셜 O(N^3)로도 해결 가능하지만, BFS가 더 효율적.
 *
 * 공간복잡도: O(N)
 * - 방문 체크 배열 visited[N] 및 큐 사용.
 */
public class boj9205 {

    static class Position {
        int r, c; // r: x좌표(가로), c: y좌표(세로)로 문제 맥락상 생각해도 무방 (맨해튼 거리라 순서 상관 없음)
        Position(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static Position[] stores; // 편의점 위치 배열
    static int N; // 편의점 개수
    static int sr, sc, er, ec; // 시작점(집), 도착점(페스티벌)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        // 테스트 케이스 반복
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            
            // 1. 집(Start) 위치 입력
            st = new StringTokenizer(br.readLine(), " ");
            sr = Integer.parseInt(st.nextToken());
            sc = Integer.parseInt(st.nextToken());

            // 2. 편의점 위치 입력
            stores = new Position[N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                stores[i] = new Position(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }

            // 3. 페스티벌(End) 위치 입력
            st = new StringTokenizer(br.readLine(), " ");
            er = Integer.parseInt(st.nextToken());
            ec = Integer.parseInt(st.nextToken());

            // BFS 탐색 수행
            if (bfs()) {
                sb.append("happy\n");
            } else {
                sb.append("sad\n");
            }
        }
        System.out.print(sb);
    }

    static boolean bfs() {
        Queue<Position> q = new ArrayDeque<>();
        boolean[] visited = new boolean[N]; // 편의점 방문 여부 체크

        q.offer(new Position(sr, sc)); // 집에서 출발

        while (!q.isEmpty()) {
            Position current = q.poll();

            // 현재 위치에서 페스티벌(도착점)까지 바로 갈 수 있으면 성공
            if (canGo(current.r, current.c, er, ec)) {
                return true;
            }

            // 방문하지 않은 편의점 중 이동 가능한 곳 탐색
            for (int i = 0; i < N; i++) {
                if (!visited[i]) {
                    Position next = stores[i];
                    if (canGo(current.r, current.c, next.r, next.c)) {
                        visited[i] = true;
                        q.offer(next);
                    }
                }
            }
        }
        return false; // 큐가 빌 때까지 페스티벌에 도달 못하면 실패
    }

    // 두 좌표 사이의 거리가 1000m 이하인지 확인 (맨해튼 거리)
    static boolean canGo(int r1, int c1, int r2, int c2) {
        int distance = Math.abs(r1 - r2) + Math.abs(c1 - c2);
        return distance <= 1000;
    }
}