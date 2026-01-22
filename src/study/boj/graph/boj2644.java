package study.boj.graph;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 2644번 - 촌수계산
 *
 * 문제: 주어진 두 사람 간의 촌수를 계산
 *
 * 알고리즘: BFS(너비 우선 탐색)를 이용한 최단 거리 탐색
 * - 친척 관계를 무방향 그래프로 모델링
 * - 시작 정점에서 목표 정점까지의 최단 경로를 탐색
 * - 각 정점까지의 거리(촌수)를 dist 배열에 기록
 *
 * 핵심 아이디어:
 * 1. 부모-자식 관계를 양방향 간선으로 표현한 그래프 구성
 * 2. 출발 정점에서 BFS를 수행하며 각 정점까지의 거리 계산
 * 3. 목표 정점에 도달하면 해당 거리가 촌수
 * 4. 목표 정점에 도달할 수 없으면 -1 반환
 *
 * 시간복잡도: O(N + M) - N은 전체 사람 수, M은 부모-자식 관계 수
 * - BFS 탐색: 각 정점과 간선을 최대 한 번씩 방문
 * - 인접 행렬 사용으로 인한 최악의 경우 O(N²)
 *
 * 공간복잡도: O(N²) - 인접 행렬 graph[N+1][N+1] 사용
 * - dist 배열: O(N)
 * - 큐: O(N)
 *
 * 최적화 가능:
 * - 인접 리스트 사용 시 공간복잡도 O(N + M)으로 개선 가능
 * - 양방향 BFS로 시간 단축 가능
 */

public class boj2644 {
    static int n,m,start,end;
    static int[][] graph;
    static int[] dist;

    public static void bfs(int idx){
        Queue<Integer> q = new LinkedList<Integer>();
        q.add(idx);

        while(!q.isEmpty()){
            int temp = q.poll();

            if(temp == end) break;

            for(int i=1; i<=n; i++){
                if(graph[temp][i] == 1 && dist[i] == 0){
                    q.add(i);
                    dist[i] = dist[temp] + 1;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        graph = new int[n+1][n+1];
        dist = new int[n+1];

        StringTokenizer st = new StringTokenizer(br.readLine());

        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(br.readLine());

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            graph[x][y] = graph[y][x] = 1;
        }
        bfs(start);

        System.out.println(dist[end] == 0 ? -1 : dist[end]);
    }
}