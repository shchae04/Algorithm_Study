package study.boj.daily;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 12761번 - 돌다리
 *
 * 문제: A와 B의 배수로 점프하거나 ±1씩 이동하여 위치 N에서 위치 M까지 최소 이동 횟수 구하기
 * - 0부터 100000 범위 내에서 이동 가능
 *
 * 알고리즘: BFS(너비 우선 탐색)를 이용한 최단거리 탐색
 * - 모든 간선의 가중치가 1이므로 BFS로 최단거리 보장
 * - 각 위치에서 8가지 이동 방식 고려:
 *   1. +1 (한 칸 앞으로)
 *   2. -1 (한 칸 뒤로)
 *   3. +A (A칸 앞으로)
 *   4. -A (A칸 뒤로)
 *   5. +B (B칸 앞으로)
 *   6. -B (B칸 뒤로)
 *   7. ×A (현재 위치에 A 배수)
 *   8. ×B (현재 위치에 B 배수)
 *
 * 핵심 아이디어:
 * 1. 시작점(N)에서 큐에 넣고 방문 표시
 * 2. 큐에서 현재 위치를 꺼내 8가지 이동 방식으로 다음 위치 탐색
 * 3. 방문하지 않은 유효한 위치(0~100000)는 거리값 업데이트 후 큐에 추가
 * 4. 도착점(M)에 도달하면 탐색 종료하고 거리값 출력
 *
 * 시간복잡도: O(N) - N은 100001 (모든 가능한 위치)
 * - 각 위치를 최대 한 번씩만 방문
 *
 * 공간복잡도: O(N) - 방문 배열과 거리 배열, 큐 공간
 */

public class boj12761 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken()); // Departure
        int M = Integer.parseInt(st.nextToken()); // Arrival

        boolean[] visited = new boolean[100001];
        int[] answer = new int[100001];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(N);
        visited[N] = true;

        while (!queue.isEmpty()) {
            int temp = queue.poll();
            int[] arr = {temp - 1, temp + 1, temp - A, temp + A, temp - B, temp + B, temp * A, temp * B};
            for (int i = 0; i < 8; i++) {
                if (arr[i] >= 0 && arr[i] <= 100000 && !visited[arr[i]]) {
                    visited[arr[i]] = true;
                    answer[arr[i]] = answer[temp] + 1;
                    queue.add(arr[i]);
                }
            }
            if (answer[M] > 0) break;
        }
        System.out.println(answer[M]);
    }
}