package study.boj.graph;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 백준 15686번 - 치킨 배달
 *
 * 문제: 도시의 치킨 거리가 가장 작게 되도록 M개의 치킨집을 선택하는 문제
 * - N×N 도시가 있고, 0은 빈 칸, 1은 집, 2는 치킨집을 의미
 * - "치킨 거리"는 집과 가장 가까운 치킨집 사이의 거리 (|r1-r2| + |c1-c2|)
 * - "도시의 치킨 거리"는 모든 집의 치킨 거리의 합
 * - 전체 치킨집 중 최대 M개를 골랐을 때, 도시의 치킨 거리의 최솟값을 구해야 함
 *
 * 알고리즘: 백트래킹(Backtracking) / 브루트포스(Brute Force)
 * - 가능한 모든 치킨집 조합(Combination)을 생성하여 확인
 *
 * 핵심 아이디어:
 * 1. 좌표 저장:
 *    - 전체 맵을 매번 탐색하는 대신, 집(1)과 치킨집(2)의 좌표를 ArrayList에 별도로 저장
 *    - 입력 시 바로 리스트에 추가하므로 N*N 맵 배열은 불필요 (메모리 절약)
 * 2. 거리 미리 계산 (Pre-calculation):
 *    - DFS 탐색 전, 모든 집과 모든 치킨집 사이의 거리를 미리 계산하여 2D 배열(distanceTable)에 저장
 *    - 반복되는 거리 계산(Math.abs) 연산을 제거하여 속도 향상
 * 3. 조합(Combination) 구현:
 *    - DFS를 사용하여 전체 치킨집 중에서 M개를 선택하는 모든 경우의 수를 탐색
 *    - boolean 배열 open[]을 사용하여 선택된 치킨집을 표시
 * 4. 가지치기 (Pruning):
 *    - 도시의 치킨 거리를 합산하는 도중 이미 구한 최솟값(ans)을 초과하면 즉시 중단
 *
 * 시간복잡도: O(C_M × H × M)
 * - C: 전체 치킨집 수 (최대 13), H: 집의 수 (최대 2N, N<=50 -> 최대 100)
 * - 최대 13개 중 M개를 고르는 경우의 수는 13C6 = 1716 정도로 작음
 * - 각 조합마다 모든 집(H)에 대해 선택된 M개의 치킨집과의 거리를 계산
 * - 미리 계산된 거리 테이블을 사용하여 연산 속도 최적화
 *
 * 공간복잡도: O(H × C)
 * - 집과 치킨집 사이의 거리를 저장하는 2D 배열 사용
 * - 좌표 저장을 위한 리스트 사용
 *
 * 특이사항:
 * - 거리 계산 시 맨해튼 거리 공식(|x1-x2| + |y1-y2|) 사용
 */
class Point {
    int x;
    int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class boj15686 {
    static int N, M;
    static ArrayList<Point> person;
    static ArrayList<Point> chicken;
    static int[][] distanceTable; // 미리 계산된 거리 테이블
    static int ans;
    static boolean[] open;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        person = new ArrayList<>();
        chicken = new ArrayList<>();

        // 맵 정보를 읽으면서 집과 치킨집 좌표 저장 (맵 배열 불필요)
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int value = Integer.parseInt(st.nextToken());
                if (value == 1) {
                    person.add(new Point(i, j));
                } else if (value == 2) {
                    chicken.add(new Point(i, j));
                }
            }
        }

        // 모든 집과 모든 치킨집 사이의 거리 미리 계산
        distanceTable = new int[person.size()][chicken.size()];
        for (int i = 0; i < person.size(); i++) {
            for (int j = 0; j < chicken.size(); j++) {
                distanceTable[i][j] = Math.abs(person.get(i).x - chicken.get(j).x)
                                    + Math.abs(person.get(i).y - chicken.get(j).y);
            }
        }

        ans = Integer.MAX_VALUE;
        open = new boolean[chicken.size()];

        DFS(0, 0);
        bw.write(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    public static void DFS(int start, int cnt) {
        if (cnt == M) {
            int res = 0;

            for (int i = 0; i < person.size(); i++) {
                int temp = Integer.MAX_VALUE;

                // 미리 계산된 거리 테이블을 사용하여 거리 조회
                for (int j = 0; j < chicken.size(); j++) {
                    if (open[j]) {
                        temp = Math.min(temp, distanceTable[i][j]);
                    }
                }
                res += temp;

                // 가지치기: 현재 합이 이미 구한 최소값보다 크면 중단
                if (res >= ans) return;
            }
            ans = Math.min(ans, res);
            return;
        }

        // 백트래킹
        for (int i = start; i < chicken.size(); i++) {
            open[i] = true;
            DFS(i + 1, cnt + 1);
            open[i] = false;
        }
    }
}
