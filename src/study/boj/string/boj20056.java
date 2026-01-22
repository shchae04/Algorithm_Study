package study.boj.string;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 백준 20056번 - 마법사 상어와 파이어볼
 *
 * 문제: N x N 격자에서 파이어볼들이 이동하고 합쳐지고 나누어지는 시뮬레이션
 * - 격자의 1행과 N행, 1열과 N열은 연결되어 있음 (토로이드 구조)
 * - 이동 후 같은 칸에 2개 이상의 파이어볼이 있으면 합쳐진 후 4개로 나누어짐
 *
 * 알고리즘: 시뮬레이션
 * - 파이어볼 클래스 정의 및 리스트 관리
 * - 이동 단계: 모든 파이어볼을 방향과 속력에 맞춰 이동 (나머지 연산 활용)
 * - 병합 및 분할 단계: 2차원 리스트 배열을 활용하여 같은 칸의 파이어볼 처리
 *
 * 시간복잡도: O(K * N^2) 또는 O(K * M)
 * - M은 파이어볼 개수. 파이어볼이 쪼개지면서 늘어날 수 있지만 격자 크기 제한으로 상한 존재.
 */
public class boj20056 {

    static class Fireball {
        int r, c, m, s, d;

        public Fireball(int r, int c, int m, int s, int d) {
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }

    static int N, M, K;
    // 방향: 0부터 7까지 (상, 우상, 우, 우하, 하, 좌하, 좌, 좌상)
    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
    static List<Fireball> fireballs = new ArrayList<>();
    // 격자의 각 칸에 파이어볼들을 모아두기 위한 맵
    static List<Fireball>[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // 맵 초기화
        map = new ArrayList[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1; // 0-based indexing
            int c = Integer.parseInt(st.nextToken()) - 1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            fireballs.add(new Fireball(r, c, m, s, d));
        }

        while (K-- > 0) {
            move();
            mergeAndDivide();
        }

        int totalMass = 0;
        for (Fireball fb : fireballs) {
            totalMass += fb.m;
        }

        bw.write(String.valueOf(totalMass));
        bw.flush();
        bw.close();
        br.close();
    }

    // 1. 모든 파이어볼 이동
    static void move() {
        // 기존 맵 비우기 (새로운 위치를 담기 위해)
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j].clear();
            }
        }

        for (Fireball fb : fireballs) {
            // 속도를 N으로 나눈 나머지로 최적화 (N번 돌면 제자리니까)
            int speed = fb.s % N;
            
            // 이동 (음수 처리 포함)
            fb.r = (fb.r + dr[fb.d] * speed) % N;
            fb.c = (fb.c + dc[fb.d] * speed) % N;
            
            if (fb.r < 0) fb.r += N;
            if (fb.c < 0) fb.c += N;

            // 이동한 위치의 맵에 파이어볼 추가
            map[fb.r][fb.c].add(fb);
        }
    }

    // 2. 이동 후 2개 이상 있는 칸 처리
    static void mergeAndDivide() {
        List<Fireball> newFireballs = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j].isEmpty()) continue;

                // 파이어볼이 1개인 경우 -> 그냥 유지
                if (map[i][j].size() == 1) {
                    newFireballs.add(map[i][j].get(0));
                    continue;
                }

                // 파이어볼이 2개 이상인 경우 -> 합치고 나누기
                int sumM = 0;
                int sumS = 0;
                int count = map[i][j].size();
                
                // 방향의 홀짝성을 체크하기 위한 변수
                // 첫 번째 파이어볼의 방향 홀짝 여부
                boolean allEven = true; 
                boolean allOdd = true;

                for (Fireball fb : map[i][j]) {
                    sumM += fb.m;
                    sumS += fb.s;
                    if (fb.d % 2 == 0) allOdd = false;
                    else allEven = false;
                }

                int newM = sumM / 5;
                // 질량이 0이면 소멸
                if (newM == 0) continue;

                int newS = sumS / count;
                
                // 모두 홀수이거나 모두 짝수이면 -> 방향 0, 2, 4, 6
                // 그렇지 않으면 -> 방향 1, 3, 5, 7
                int startDir = (allEven || allOdd) ? 0 : 1;

                for (int k = 0; k < 4; k++) {
                    newFireballs.add(new Fireball(i, j, newM, newS, startDir + 2 * k));
                }
            }
        }

        // 다음 턴을 위해 파이어볼 리스트 갱신
        fireballs = newFireballs;
    }
}
