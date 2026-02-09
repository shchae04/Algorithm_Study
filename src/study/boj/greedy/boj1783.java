package study.boj.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 1783번: 병든 나이트 (Greedy)
 *
 * <p>체스판의 크기가 N x M일 때, 병든 나이트가 방문할 수 있는 칸의 최대 개수를 구하는 문제.</p>
 * <p>이동 규칙:</p>
 * <ul>
 *   <li>1. 2칸 위로, 1칸 오른쪽</li>
 *   <li>2. 1칸 위로, 2칸 오른쪽</li>
 *   <li>3. 1칸 아래로, 2칸 오른쪽</li>
 *   <li>4. 2칸 아래로, 1칸 오른쪽</li>
 * </ul>
 * <p>제약 사항:</p>
 * <ul>
 *   <li>이동 횟수가 4번 이상이면, 4가지 이동 방법을 모두 적어도 한 번씩 사용해야 함.</li>
 *   <li>이동 횟수가 4번 미만이면 이동 방법에 제약이 없음 (단, 최대 방문 칸 수는 4개로 제한됨).</li>
 * </ul>
 */
public class boj1783 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long N = Long.parseLong(st.nextToken());
        long M = Long.parseLong(st.nextToken());

        System.out.println(solve(N, M));
    }

    /**
     * N(높이)과 M(너비)에 따른 최대 방문 가능 칸 수 계산
     *
     * @param n 체스판의 세로 길이
     * @param m 체스판의 가로 길이
     * @return 방문할 수 있는 칸의 최대 개수
     */
    private static long solve(long n, long m) {
        // 세로가 1이면 이동 불가 (시작 칸만 방문)
        if (n == 1) {
            return 1;
        }

        // 세로가 2이면 2번, 3번 이동만 가능
        // 이동 횟수가 4번 이상이 될 수 없으므로(모든 방향 사용 불가), 최대 4칸까지만 방문 가능
        if (n == 2) {
            return Math.min(4, (m + 1) / 2);
        }

        // 세로가 3 이상일 때
        if (n >= 3) {
            // 가로가 7 미만이면 4가지 이동 방법을 모두 사용할 수 없음
            // 따라서 최대 4칸까지만 방문 가능하거나, 가로 길이만큼 방문 가능
            if (m < 7) {
                return Math.min(4, m);
            }
            // 가로가 7 이상이면 4가지 방법을 모두 사용하여 5칸을 확보한 뒤,
            // 남은 가로 길이는 1번, 4번 이동을 통해 매 칸마다 방문 가능
            // (M - 7) + 5 = M - 2
            else {
                return m - 2;
            }
        }

        return 0;
    }
}
