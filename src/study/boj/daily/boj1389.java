package study.boj.daily;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * 백준 1389번 - 케빈 베이컨의 6단계 법칙
 *
 * 문제: N명의 사람들 사이의 친구 관계가 주어질 때, 케빈 베이컨의 수가 가장 작은 사람을 구하기
 *       케빈 베이컨의 수 = 한 사람에서 다른 모든 사람까지의 최단 경로의 합
 *
 * 알고리즘: 플로이드-와샬(Floyd-Warshall) 최단 경로 알고리즘
 * - 모든 정점 쌍 간의 최단 거리를 구하는 동적 계획법 알고리즘
 * - 그래프에서 음의 가중치를 허용하며, 모든 정점에서 모든 정점으로의 최단 경로를 계산
 * - 3중 반복문을 통해 경유점 k를 거쳐가는 경로가 더 짧은지 확인
 *
 * 핵심 아이디어:
 * 1. 인접 행렬로 그래프를 표현 (친구 관계는 가중치 1인 양방향 간선)
 * 2. 모든 정점 쌍 간의 최단 거리를 플로이드-와샬로 계산
 * 3. 각 사람의 케빈 베이컨 수(다른 모든 사람까지의 거리 합)를 계산
 * 4. 케빈 베이컨 수가 가장 작은 사람의 번호를 출력
 *
 * 시간복잡도: O(N³) - N은 사람의 수
 * - 플로이드-와샬 알고리즘의 3중 반복문: O(N³)
 * - 각 사람의 케빈 베이컨 수 계산: O(N²)
 * - 전체 시간복잡도: O(N³)
 *
 * 공간복잡도: O(N²) - 인접 행렬을 위한 2차원 배열
 * - N×N 크기의 거리 행렬 저장
 *
 * 플로이드-와샬 알고리즘의 점화식:
 * dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j])
 * - k를 경유점으로 하여 i에서 j로 가는 경로가 더 짧은지 확인
 */

public class boj1389 {
    static final int INF = 987654321;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] arr = new int[N + 1][N + 1];

        // 초기값 설정
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                arr[i][j] = INF;

                if (i == j) {
                    arr[i][j] = 0;
                }
            }
        }

        // 간선의 방향이 양방향이어야 함.
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            arr[x][y] = arr[y][x] = 1;
        }

        // 플로이드 와샬 알고리즘
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    // 최단경로 초기화
                    if (arr[i][j] > arr[i][k] + arr[k][j]) {
                        arr[i][j] = arr[i][k] + arr[k][j];
                    }
                }
            }
        }

        int res = INF;
        int idx = -1;

        // 케빈 베이컨의 수가 가장 작은 인덱스를 탐색
        for (int i = 1; i <= N; i++) {
            int total = 0;
            for (int j = 1; j <= N; j++) {
                total += arr[i][j];
            }

            if (res > total) {
                res = total;
                idx = i;
            }
        }

        bw.write(idx + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

}