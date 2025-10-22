package study.boj.daily;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
