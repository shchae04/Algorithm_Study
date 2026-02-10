package study.boj.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class boj10423 {
    static int atoi(String str) {
        return Integer.parseInt(str);
    }

    static int N, M, K;
    static int parent[];
    static ArrayList<Info> al = new ArrayList<Info>();
    static int ans;

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

    static void pro() {
        // 1. 간선들을 비용 기준으로 오름차순 정렬 (Kruskal 알고리즘 시작)
        Collections.sort(al, (o1, o2) -> {
            return o1.wei - o2.wei;
        });

        for (int i = 0; i < al.size(); i++) {
            Info info = al.get(i);
            // 두 노드가 아직 같은 전력망(또는 발전소)에 연결되지 않았다면 연결
            if (find(info.st) != find(info.ed)) {
                union(info.st, info.ed);
                ans += info.wei;

                // 모든 도시가 발전소에 연결되었다면 더 이상 탐색할 필요 없음
                if (check()) break;
            }
        }

        System.out.println(ans);
    }

    // 모든 도시가 발전소(-1)에 연결되었는지 확인하는 함수
    static boolean check() {
        for (int i = 1; i <= N; i++) {
            if (find(i) >= 0) { // 루트가 -1이 아니라면 아직 발전소에 연결 안 된 것
                return false;
            }
        }

        return true;
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = atoi(st.nextToken());
        M = atoi(st.nextToken());
        K = atoi(st.nextToken());

        parent = new int[N + 1];

        // 0은 아직 아무 곳에도 연결되지 않은 독립된 상태를 의미
        Arrays.fill(parent, 0);

        st = new StringTokenizer(br.readLine());

        // 발전소가 있는 도시는 부모를 -1로 설정하여 "슈퍼 발전소" 그룹으로 간주
        for (int i = 0; i < K; i++) {
            int num = atoi(st.nextToken());
            parent[num] = -1;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = atoi(st.nextToken());
            int to = atoi(st.nextToken());
            int wei = atoi(st.nextToken());

            al.add(new Info(from, to, wei));
        }
    }

    // Find: 도시가 속한 그룹의 루트를 찾음
    static int find(int v) {
        if (parent[v] < 0) return -1; // 발전소 그룹에 속해 있음
        if (parent[v] == 0) return v; // 자기 자신이 루트임

        // 경로 압축 (Path Compression)
        parent[v] = find(parent[v]);

        return parent[v];
    }

    // Union: 두 그룹을 하나로 합침
    static void union(int u, int v) {
        u = find(u);
        v = find(v);

        if (u == v) return;

        // 한쪽 그룹이라도 발전소를 포함하고 있다면(-1), 합쳐진 그룹 전체가 발전소 그룹이 됨
        if (u == -1) {
            parent[v] = -1;
        } else if (v == -1) {
            parent[u] = -1;
        } else {
            // 둘 다 일반 도시 그룹이라면 하나로 합침
            parent[v] = u;
        }
    }

    static class Info {
        int st, ed, wei;

        public Info(int st, int ed, int wei) {
            this.st = st;
            this.ed = ed;
            this.wei = wei;
        }
    }
}