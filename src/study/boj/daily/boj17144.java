package study.boj.daily;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 백준 17144번 - 미세먼지 안녕!
 *
 * 문제: 집안의 미세먼지가 확산되고 공기청정기가 작동하는 과정을 T초 동안 시뮬레이션하기
 * - 미세먼지는 1초마다 인접한 4방향(상하좌우)으로 확산됨
 * - 확산되는 양은 현재 위치 먼지의 1/5이고, 남은 먼지는 '원래 먼지 - (확산된 양 * 방향 개수)'가 됨
 * - 공기청정기는 1초마다 작동하며 위쪽은 반시계 방향, 아래쪽은 시계 방향으로 바람을 일으킴
 * - 바람이 불면 미세먼지가 바람 방향대로 한 칸씩 밀려남 (공기청정기로 들어간 먼지는 사라짐)
 *
 * 알고리즘: 시뮬레이션 (구현)
 * - 그냥 문제에서 시키는 대로 1초마다 [미세먼지 확산] -> [공기청정기 작동] 순서로 계속 돌리면 됨
 *
 * 핵심 아이디어:
 * 1. 동시 확산 처리:
 *    - 모든 칸에서 동시에 먼지가 퍼져야 함.
 *    - 하나씩 처리하면서 바로 원본 배열을 바꾸면, 옆 칸 계산할 때 이미 변한 값을 쓰게 돼서 꼬임.
 *    - 그래서 '임시 배열(tMap)'을 만들어서 확산 결과를 저장하고 나중에 한 번에 덮어씌워야 함.
 *
 * 2. 배열 돌리기 (공기청정기 바람):
 *    - 바람이 불면 먼지가 한 칸씩 이동함.
 *    - 이걸 코드로 짤 때는 '빈 칸을 채우는 느낌'이나 '역순으로 당겨오기'가 편함.
 *    - 예를 들어, 윗줄을 오른쪽으로 미는 건, 오른쪽 끝부터 시작해서 왼쪽 값을 가져오는 식으로 덮어씌우면 됨.
 *    - (주의) 공기청정기에서 나가는 바람은 먼지가 없는 깨끗한 바람(0)임.
 *
 * 시간복잡도: O(T * R * C)
 * - T초 동안 R*C 크기의 맵을 훑어야 하니까 (T * R * C)만큼 걸림.
 *
 * 공간복잡도: O(R * C)
 * - 맵 크기만큼 배열이 필요함.
 */
public class boj17144 {

    static int R, C, T;
    static int[][] map;
    // 상, 하, 좌, 우 방향을 나타내는 배열 (y축 변화량, x축 변화량)
    static int[] up = {1, -1, 0, 0};
    static int[] side = {0, 0, 1, -1};
    static int airPos1, airPos2; // airPos1: 공기청정기 위쪽, airPos2: 공기청정기 아래쪽

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        R = Integer.parseInt(s[0]); // 행 (세로 길이)
        C = Integer.parseInt(s[1]); // 열 (가로 길이)
        T = Integer.parseInt(s[2]); // 몇 초 동안 돌릴지

        map = new int[R][C];
        
        // 맵 정보 입력받기
        for (int i = 0; i < R; i++) {
            String[] s1 = br.readLine().split(" ");
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(s1[j]);
            }
        }

        findAir(); // 공기청정기가 어디 있는지 찾기

        // T초 동안 시뮬레이션 반복
        for (int i = 0; i < T; i++) {
            solve();
        }

        // 남은 미세먼지 양 계산
        int result = count();

        // count() 함수는 배열의 모든 값을 더함.
        // 공기청정기 위치(-1)가 두 곳 있으니까 합계에서 -2가 됨.
        // 실제 먼지 양을 구하려면 다시 +2를 해줘야 함.
        System.out.println(result + 2); 
    }

    // 공기청정기 위치 찾는 함수
    public static void findAir() {
        for (int i = 0; i < R; i++) {
            // -1인 곳이 공기청정기임
            if (map[i][0] == -1) {
                airPos1 = i;      // 위쪽 칸
                airPos2 = i + 1;  // 아래쪽 칸 (바로 밑에 붙어있음)
                break; // 찾았으면 끝
            }
        }
    }

    // 1초 동안 일어나는 일 (확산 -> 공기청정)
    public static void solve() {
        map = dustSimulation(); // 1. 미세먼지가 확 퍼짐
        airSimulation();        // 2. 공기청정기가 위잉 돌아감
    }

    // 공기청정기 작동 함수 (바람 불어서 먼지 이동)
    public static void airSimulation() {
        // === 위쪽 공기청정기 (반시계 방향 순환) ===
        int top = airPos1; 

        // 1. 왼쪽 세로줄: 위에서 아래로 당기기 (▼)
        // 공기청정기 바로 위부터 꼭대기까지의 먼지를 아래로 한 칸씩 내림 (공기청정기로 빨려들어감)
        for (int x = top - 1; x > 0; x--) {
            map[x][0] = map[x - 1][0];
        }

        // 2. 맨 윗줄: 오른쪽에서 왼쪽으로 당기기 (◀)
        for (int y = 0; y < C - 1; y++) {
            map[0][y] = map[0][y + 1];
        }

        // 3. 오른쪽 세로줄: 아래에서 위로 당기기 (▲)
        for (int x = 0; x < top; x++) {
            map[x][C - 1] = map[x + 1][C - 1];
        }

        // 4. 공기청정기 있는 줄: 왼쪽에서 오른쪽으로 당기기 (▶)
        // 공기청정기에서 깨끗한 바람이 나오는 방향
        for (int y = C - 1; y > 1; y--) {
            map[top][y] = map[top][y - 1];
        }

        // 공기청정기 바로 옆칸은 깨끗한 바람(0)이 나옴
        map[top][1] = 0; 


        // === 아래쪽 공기청정기 (시계 방향 순환) ===
        int bottom = airPos2; 

        // 1. 왼쪽 세로줄: 아래에서 위로 당기기 (▲)
        // 공기청정기 바로 아래부터 바닥까지의 먼지를 위로 한 칸씩 올림 (공기청정기로 빨려들어감)
        for (int x = bottom + 1; x < R - 1; x++) {
            map[x][0] = map[x + 1][0];
        }

        // 2. 맨 아랫줄: 오른쪽에서 왼쪽으로 당기기 (◀)
        for (int y = 0; y < C - 1; y++) {
            map[R - 1][y] = map[R - 1][y + 1];
        }

        // 3. 오른쪽 세로줄: 위에서 아래로 당기기 (▼)
        for (int x = R - 1; x > bottom; x--) {
            map[x][C - 1] = map[x - 1][C - 1];
        }

        // 4. 공기청정기 있는 줄: 왼쪽에서 오른쪽으로 당기기 (▶)
        for (int y = C - 1; y > 1; y--) {
            map[bottom][y] = map[bottom][y - 1];
        }

        // 공기청정기 바로 옆칸은 깨끗한 바람(0)이 나옴
        map[bottom][1] = 0; 
    }

    // 미세먼지 확산 함수
    public static int[][] dustSimulation() {
        int[][] tMap = new int[R][C]; // 임시 배열 (확산 결과를 저장할 곳)

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                // 공기청정기는 그대로 둠
                if (map[i][j] == -1) {
                    tMap[i][j] = -1;
                    continue;
                }
                
                // 일단 현재 칸의 먼지 양을 임시 배열에 더함
                tMap[i][j] += map[i][j];
                
                // 4방향으로 확산 시도
                for (int k = 0; k < 4; k++) {
                    int nx = j + side[k]; // 옆으로 이동
                    int ny = i + up[k];   // 위아래로 이동

                    // 맵 밖으로 나가면 패스
                    if (ny < 0 || ny >= R || nx < 0 || nx >= C) continue;
                    // 공기청정기 있는 곳으로는 확산 안 됨
                    if (map[ny][nx] == -1) continue;

                    // 확산될 양 = 현재 먼지 / 5
                    int spreadAmount = map[i][j] / 5;
                    
                    // 옆 칸에 확산된 먼지 더해주고
                    tMap[ny][nx] += spreadAmount;
                    // 내 칸에서 확산된 만큼 뺌
                    tMap[i][j] -= spreadAmount;
                }
            }
        }
        return tMap; // 확산이 완료된 맵을 반환
    }

    // 전체 미세먼지 양 계산하는 함수
    public static int count() {
        int temp = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                temp += map[i][j]; // 모든 칸의 값을 더함
            }
        }
        return temp;
    }
}
