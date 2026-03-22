package study.boj.graph;

import java.io.*;
import java.util.*;

/**
 * 백준 1707번: 이분 그래프
 *
 * 문제 설명:
 * - 그래프의 정점을 두 그룹으로 나눴을 때, 같은 그룹 내의 정점끼리는 간선이 없어야 함
 * - BFS를 이용하여 인접한 정점들을 서로 다른 색으로 칠하면서 이분 그래프 여부를 판별
 *
 * 알고리즘:
 * 1. 각 정점을 1 또는 -1로 색칠 (두 그룹을 의미)
 * 2. BFS로 탐색하면서 인접한 정점은 반대 색으로 칠함
 * 3. 이미 칠해진 정점이 현재 정점과 같은 색이면 이분 그래프가 아님
 *
 * 시간복잡도: O(V + E) - 각 테스트케이스마다
 */
public class boj1707 {
    static int K;           // 테스트 케이스 개수
    static int V, E;        // V: 정점 개수, E: 간선 개수
    static int u, v;        // 간선의 양 끝 정점
    static int color[];     // 각 정점의 색상 (0: 미방문, 1: 그룹1, -1: 그룹2)
    static ArrayList<ArrayList<Integer>> connect;  // 인접 리스트
    static String answer;   // 각 테스트케이스의 결과

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        K = Integer.parseInt(br.readLine());

        // 각 테스트 케이스 처리
        for (int i = 0; i < K; i++) {
            answer = "YES";  // 기본값: 이분 그래프라고 가정

            // 정점 개수(V)와 간선 개수(E) 입력
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            // 인접 리스트 초기화
            connect = new ArrayList<>();
            for (int j = 0; j <= V; j++) {
                connect.add(new ArrayList<>());  // 각 정점마다 빈 리스트 생성
            }

            // 간선 정보 입력 (양방향 그래프)
            for (int j = 0; j < E; j++) {
                st = new StringTokenizer(br.readLine());
                u = Integer.parseInt(st.nextToken());
                v = Integer.parseInt(st.nextToken());

                // 양방향 간선 추가
                connect.get(u).add(v);
                connect.get(v).add(u);
            }

            // 색상 배열 초기화 (0: 아직 방문하지 않음)
            color = new int[V + 1];

            // 모든 정점에 대해 BFS 수행 (비연결 그래프 대응)
            for (int j = 1; j <= V; j++) {
                // 아직 방문하지 않은 정점이면 BFS 시작
                if (color[j] == 0) {
                    if (!bfs(j))  // 이분 그래프가 아니면 즉시 중단
                        break;
                }
            }

            System.out.println(answer);
        }
    }

    /**
     * BFS를 이용한 이분 그래프 판별
     *
     * @param node 시작 정점
     * @return 이분 그래프이면 true, 아니면 false
     *
     * 동작 원리:
     * 1. 시작 정점을 1로 칠하고 큐에 넣음
     * 2. 큐에서 정점을 꺼내 인접한 정점들을 확인
     * 3. 인접 정점이 같은 색이면 이분 그래프 아님 (즉시 false 반환)
     * 4. 인접 정점이 미방문이면 현재 정점과 반대 색으로 칠함
     */
    public static boolean bfs(int node) {
        Queue<Integer> q = new LinkedList<>();
        q.add(node);
        color[node] = 1;  // 시작 정점을 그룹1로 설정

        while (!q.isEmpty()) {
            int now = q.poll();  // 현재 정점

            // 현재 정점과 인접한 모든 정점 확인
            for (Integer i : connect.get(now)) {
                // 인접한 정점이 현재 정점과 같은 색이면 이분 그래프 아님
                if (color[now] == color[i]) {
                    answer = "NO";
                    return false;
                }

                // 인접 정점이 아직 방문하지 않은 정점이면
                if (color[i] == 0) {
                    color[i] = color[now] * -1;  // 반대 색으로 칠함 (1 -> -1, -1 -> 1)
                    q.add(i);  // 큐에 추가하여 다음에 탐색
                }
            }
        }

        return true;  // 모든 정점을 문제없이 칠했으면 이분 그래프
    }
}
