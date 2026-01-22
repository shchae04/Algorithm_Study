package study.boj.graph;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 백준 2668번 - 숫자고르기
 *
 * 문제: 1부터 N까지의 수가 각각 적혀있는 N장의 카드가 있고, i번째 카드에는 pick[i]가 적혀있음.
 * 각 숫자에 대해 그 숫자가 적힌 카드를 골라서, 그 카드에 적힌 숫자를 따라가는 과정에서
 * 자기 자신으로 돌아올 수 있는 숫자들을 찾기
 *
 * 알고리즘: DFS를 이용한 사이클(Cycle) 찾기
 * - 각 숫자를 시작점으로 하여 DFS 수행
 * - pick 배열을 따라가면서 방문한 노드들을 추적
 * - 자신의 사이클로 돌아오면 결과에 추가
 *
 * 핵심 아이디어:
 * 1. 각 숫자 i를 시작점으로 pick[i]를 따라가며 DFS 수행
 * 2. visited로 현재 DFS 경로의 방문 여부를, finished로 완전히 탐색 완료 여부 구분
 * 3. 아직 방문하지 않은 노드면 계속 따라가고, 방문했으면:
 *    - finished가 아니면 사이클을 만남 (현재 경로 내에서)
 *    - 그 사이클의 시작점이 자신이면 결과에 추가
 * 4. 탐색 완료 후 사이클에 속한 모든 숫자들이 결과에 포함됨
 *
 * 시간복잡도: O(N²) - 각 숫자마다 DFS 수행 (worst case)
 * - N개의 시작점에서 각각 최대 N개의 노드를 방문할 수 있음
 *
 * 공간복잡도: O(N) - visited, finished 배열과 재귀 스택
 * - 각 배열이 N+1의 크기를 가짐
 *
 * 사이클 탐지의 핵심: visited와 finished를 구분하여 현재 경로와 완료된 경로를 구별
 */

public class boj2668 {

    static int N;
    static int[] pick;
    static boolean[] visited;
    static boolean[] finished;
    static List<Integer> result = new ArrayList<>();

    static void dfs(int start, int current) {
        visited[current] = true;
        int next = pick[current];

        if (!visited[next]) {
            dfs(start, next);
        } else if(!finished[next]) {
            if (next == start) {
                result.add(start); // start가 cycle에 포함된다.
            }
        }

        finished[current] = true; // 탐색 끝.
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        pick = new int[N + 1];
        visited = new boolean[N + 1];
        finished = new boolean[N + 1];


        for(int i = 1; i <=N; i++) {
            pick[i] = Integer.parseInt(br.readLine());
        }

        for(int i = 1; i <= N; i++) {
            visited = new boolean[N + 1];
            finished = new boolean[N + 1];
            dfs(i, i);
        }

        Collections.sort(result);

        System.out.println(result.size());
        for (int j : result) {
            System.out.println(j);
        }
    }
}
