package study.boj.daily;

import java.io.*;
import java.util.StringTokenizer;

/**
 * 백준 2600번 - 구슬게임
 *
 * 문제: 두 개의 구슬 통이 있고, 세 가지 종류의 꺼낼 수 있는 구슬 개수(b1, b2, b3)가 주어진다.
 * - 두 사람이 번갈아가며 구슬을 꺼낸다.
 * - 한 번에 한 통에서만 구슬을 꺼낼 수 있다.
 * - 주어진 3가지 개수 중 하나만큼만 꺼낼 수 있다.
 * - 더 이상 구슬을 꺼낼 수 없는 사람이 패배한다. (Normal Play Convention)
 * - 두 사람이 최적의 전략으로 게임을 할 때 승자를 구하라.
 *
 * 알고리즘: 스프라그-그런디 정리 (Sprague-Grundy Theorem)
 * - 이 게임은 임파셜 게임(Impartial Game)으로, 님 게임(Nim Game)과 동치로 변환 가능.
 * - 각 상태(구슬 개수)를 그런디 수(Grundy Number) 또는 님 값(Nim-value)으로 매핑.
 * - G(state) = MEX({G(next_state_1), G(next_state_2), ...})
 *   * MEX (Minimum Excluded value): 포함되지 않은 가장 작은 0 이상의 정수
 * - 전체 게임의 그런디 수 = (1번 통의 그런디 수) XOR (2번 통의 그런디 수)
 * - 결과가 0이면 후공(B) 승리, 0이 아니면 선공(A) 승리.
 */
public class boj2600 {

    static int[] moves = new int[3];
    static int[] grundy = new int[501]; // 각 구슬 개수별 그런디 값을 저장하는 DP 테이블

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        // 1. 꺼낼 수 있는 구슬의 개수(b1, b2, b3) 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 3; i++) {
            moves[i] = Integer.parseInt(st.nextToken());
        }

        // 2. 0부터 500개까지 각 상태의 그런디 수를 미리 계산 (Bottom-up DP)
        calculateGrundy();

        // 3. 5번의 독립적인 게임 시나리오 테스트
        for (int t = 0; t < 5; t++) {
            st = new StringTokenizer(br.readLine());
            int k1 = Integer.parseInt(st.nextToken());
            int k2 = Integer.parseInt(st.nextToken());

            // 두 통의 구슬 게임은 독립적이므로, 전체 게임의 그런디 값은 각 통의 XOR 합임
            int resultXor = grundy[k1] ^ grundy[k2];

            // 그런디 값이 0이 아니면 선공(A)이 승리할 수 있는 상태
            if (resultXor != 0) {
                sb.append("A\n");
            } else {
                sb.append("B\n");
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static void calculateGrundy() {
        // base case: 구슬이 0개일 때는 이동이 불가능하므로 그런디 값은 0 (패배 상태)
        grundy[0] = 0;

        for (int i = 1; i <= 500; i++) {
            // MEX(Minimum Excluded value)를 구하기 위한 체크 배열
            // 가능한 이동이 3가지이므로 다음 상태의 그런디 값은 최대 3가지만 존재할 수 있음.
            // 따라서 MEX 결과값은 0, 1, 2, 3 중 하나가 됨.
            boolean[] used = new boolean[4];

            for (int move : moves) {
                if (i >= move) {
                    // 현재 i개에서 move개를 뺐을 때 도달하는 상태의 그런디 값을 확인
                    int nextStateGrundy = grundy[i - move];
                    if (nextStateGrundy < 4) {
                        used[nextStateGrundy] = true;
                    }
                }
            }

            // used 배열에 체크되지 않은 가장 작은 인덱스가 현재 상태의 그런디 수(MEX)
            int res = 0;
            while (res < 4 && used[res]) {
                res++;
            }
            grundy[i] = res;
        }
    }
}
