package study.boj.daily;

import java.util.*;
import java.io.*;

/**
 * 2차원 그리드 상의 노드 정보를 담는 클래스
 */
class Node implements Comparable<Node> {
    int y, x, cost;

    public Node(int y, int x, int cost) {
        this.y = y;
        this.x = x;
        this.cost = cost;
    }

    // 우선순위 큐에서 비용(cost)이 적은 순으로 정렬되도록 설정
    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.cost, other.cost);
    }
}

/**
 * 백준 4485번 - 녹색 옷 입은 애가 젤다지?
 *
 * 문제: N x N 크기의 동굴에서 (0,0)에서 출발하여 (N-1, N-1)까지 이동할 때 잃는 루피(비용)의 최소합을 구하는 문제
 * - 각 칸마다 도둑루피가 있어 해당 칸을 지나면 비용이 발생함
 * - 상하좌우 4방향으로 이동 가능
 *
 * 알고리즘: 다익스트라(Dijkstra)
 * - 가중치가 있는 그래프에서의 최단 경로 문제 (음의 가중치 없음)
 * - 2차원 배열을 노드로 간주하여 우선순위 큐를 이용해 탐색
 *
 * 핵심 아이디어:
 * 1. 상태 정의: dist[y][x] = (0,0)에서 (y,x)까지 가는 최소 비용
 * 2. 초기화:
 *    - dist 배열을 무한대(INF)로 초기화
 *    - 시작점 (0,0)의 비용은 해당 칸의 도둑루피 크기로 설정
 * 3. 탐색 과정:
 *    - 현재 비용이 가장 적은 노드를 꺼냄 (PriorityQueue 사용)
 *    - 상하좌우로 이동하며 더 적은 비용으로 도달 가능한 경우 dist 갱신 및 큐에 추가
 *
 * 시간복잡도: O(N^2 log(N^2)) = O(N^2 log N)
 * - 노드의 수 V = N^2, 간선의 수 E ≈ 4N^2
 * - 다익스트라 시간복잡도 O(E log V)
 *
 * 공간복잡도: O(N^2)
 * - 지도 정보 board[N][N]
 * - 최단 거리 배열 dist[N][N]
 *
 * 특이사항:
 * - 입력이 0일 때까지 테스트 케이스가 반복됨
 * - 시작 지점 (0,0)의 비용도 포함해야 함
 */
public class boj4485 {
    // 상, 하, 좌, 우 순서의 방향 벡터
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static final int INF = (int) 1e9;

    public static void main(String[] args) throws IOException {
        // 빠른 입력을 위한 BufferedReader 사용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cnt = 1;

        while (true) {
            String input = br.readLine();
            if (input == null || input.equals("0")) break;

            int n = Integer.parseInt(input);
            int[][] board = new int[n][n];
            int[][] dist = new int[n][n];

            // 보드 데이터 입력 및 거리 테이블 초기화
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                    dist[i][j] = INF;
                }
            }

            // 다익스트라(Dijkstra) 알고리즘 수행
            int result = solve(n, board, dist);

            // 결과 출력
            System.out.println("Problem " + cnt + ": " + result);
            cnt++;
        }
    }

    private static int solve(int n, int[][] board, int[][] dist) {
        PriorityQueue<Node> pq = new PriorityQueue<>();

        // 시작점 설정 (0, 0)
        dist[0][0] = board[0][0];
        pq.offer(new Node(0, 0, board[0][0]));

        while (!pq.isEmpty()) {
            Node curr = pq.poll();

            // 현재 노드까지의 비용이 이미 저장된 최소 비용보다 크면 건너뜀
            if (dist[curr.y][curr.x] < curr.cost) continue;

            // 목표 지점에 도달한 경우 바로 해당 비용 반환
            if (curr.y == n - 1 && curr.x == n - 1) {
                return curr.cost;
            }

            // 상하좌우 4방향 탐색
            for (int i = 0; i < 4; i++) {
                int ny = curr.y + dy[i];
                int nx = curr.x + dx[i];

                // 경계 검사
                if (ny >= 0 && ny < n && nx >= 0 && nx < n) {
                    int nextCost = curr.cost + board[ny][nx];

                    // 더 짧은 경로를 찾은 경우 업데이트 후 큐에 삽입
                    if (dist[ny][nx] > nextCost) {
                        dist[ny][nx] = nextCost;
                        pq.offer(new Node(ny, nx, nextCost));
                    }
                }
            }
        }
        return -1; // 도달 불가능한 경우 (문제 특성상 발생하지 않음)
    }
}