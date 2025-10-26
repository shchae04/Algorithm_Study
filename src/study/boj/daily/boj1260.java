package study.boj.daily;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 1260번 - DFS와 BFS
 *
 * 문제: 주어진 그래프에서 DFS와 BFS를 모두 수행하여 결과 출력
 *
 * 알고리즘:
 * 1. DFS(깊이 우선 탐색) - 재귀를 이용한 깊이 우선 탐색
 *    - 시작 노드에서 출발하여 한 방향으로 최대한 깊게 탐색
 *    - 더 이상 방문할 노드가 없으면 되돌아가 다른 경로 탐색
 *
 * 2. BFS(너비 우선 탐색) - 큐를 이용한 너비 우선 탐색
 *    - 시작 노드에서 출발하여 인접한 모든 노드를 먼저 방문
 *    - 계층별로 노드를 방문하여 최단 경로 탐색 가능
 *
 * 핵심 아이디어:
 * 1. 그래프를 인접 행렬로 표현 (arr[i][j] = 1이면 i-j 간선 존재)
 * 2. DFS: 재귀 함수를 이용하여 깊이 우선으로 탐색
 * 3. BFS: 큐를 이용하여 너비 우선으로 탐색
 * 4. 각 탐색마다 visited 배열(check)을 초기화하여 독립적으로 실행
 *
 * 시간복잡도: O(N + M) - N은 노드 수, M은 간선 수
 * - DFS: 각 노드와 간선을 한 번씩 방문
 * - BFS: 각 노드와 간선을 한 번씩 방문
 *
 * 공간복잡도: O(N^2)
 * - 인접 행렬을 이용하므로 N^2 공간 필요
 * - DFS 재귀 스택: O(N)
 * - BFS 큐: O(N)
 */

public class boj1260 {

    static StringBuilder sb = new StringBuilder();
    static boolean[] check;
    static int[][] arr;

    static int node, line, start;

    static Queue<Integer> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        node = Integer.parseInt(st.nextToken());
        line = Integer.parseInt(st.nextToken());
        start= Integer.parseInt(st.nextToken());

        arr = new int[node+1][node+1];
        check = new boolean[node+1];

        for(int i = 0 ; i < line ; i ++) {
            StringTokenizer str = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(str.nextToken());
            int b = Integer.parseInt(str.nextToken());

            arr[a][b] = arr[b][a] =  1;
        }
        //sb.append("\n");
        dfs(start);
        sb.append("\n");
        check = new boolean[node+1];

        bfs(start);

        System.out.println(sb);

    }
    public static void dfs(int start) {

        check[start] = true;
        sb.append(start).append(" ");

        for(int i = 0 ; i <= node ; i++) {
            if(arr[start][i] == 1 && !check[i])
                dfs(i);
        }

    }

    public static void bfs(int start) {
        q.add(start);
        check[start] = true;

        while(!q.isEmpty()) {

            start = q.poll();
            sb.append(start).append(" ");

            for(int i = 1 ; i <= node ; i++) {
                if(arr[start][i] == 1 && !check[i]) {
                    q.add(i);
                    check[i] = true;
                }
            }
        }


    }

}