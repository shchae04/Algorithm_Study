package study.boj.Tree;

import java.io.*;
import java.util.*;

public class 트리와쿼리 {
    static ArrayList<Integer>[] tree;
    static int[] size;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 처리
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 정점 수
        int R = Integer.parseInt(st.nextToken()); // 루트
        int Q = Integer.parseInt(st.nextToken()); // 쿼리 수

        // 트리 초기화
        tree = new ArrayList[N + 1];
        size = new int[N + 1];
        visited = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }

        // 간선 입력
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            tree[u].add(v);
            tree[v].add(u);
        }

        // DFS로 서브트리 크기 계산
        dfs(R);

        // 쿼리 처리
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            int u = Integer.parseInt(br.readLine());
            sb.append(size[u]).append('\n');
        }

        System.out.print(sb.toString());
    }

    // DFS로 서브트리 크기 계산
    static void dfs(int node) {
        visited[node] = true;
        size[node] = 1; // 자기 자신 포함

        for (int child : tree[node]) {
            if (!visited[child]) {
                dfs(child);
                size[node] += size[child];
            }
        }
    }
}