package study.boj.daily;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 백준 1519번 - 수 이동 게임
 *
 * 문제: 주어진 수 N에서 시작하여 수를 빼는 게임. 각 턴마다 현재 수의 부분 문자열로 나타낼 수 있는
 * 수들을 빼서 게임을 진행. 먼저 이기는 손을 찾아 최소로 빼야 할 수를 구하기.
 * (수를 0으로 만들 수 없으면 -1 출력)
 *
 * 알고리즘: 동적 프로그래밍을 이용한 게임 이론(Grundy Number)
 * - dp[n] = 0이면 패배 상태, 0이 아니면 승리 상태
 * - 각 상태에서 이동 가능한 모든 경우를 탐색하여 패배 상태로 갈 수 있는 최소값 저장
 * - 현재 상태에서 이동할 수 있는 상태 중 하나가 패배 상태면, 현재 상태는 승리 상태
 *
 * 핵심 아이디어:
 * 1. 부분 문자열: 현재 수의 연속된 숫자들로 이루어진 수
 * 2. 게임 상태 판정: 다음 상태가 패배이면 현재 상태는 승리
 * 3. 최소값 추적: 여러 승리 이동 중 최소값을 기록
 *
 * 시간복잡도: O(N * D^2) - N은 입력값, D는 N의 자릿수
 * - 각 수마다 부분 문자열 생성: O(D^2)
 * - 1부터 N까지 모든 수 처리: O(N)
 *
 * 공간복잡도: O(N) - dp 배열에 N만큼의 공간 필요
 */

public class boj1519 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());

        if (N < 10) {
            bw.write("-1");
        } else {
            // 0이면 패배, 0이 아니면 승리.
            // 승리일 경우, 가장 최소로 뺀 값을 저장.
            int[] dp = new int[N + 1];

            for (int n = 10; n <= N; n++) {
                String str = String.valueOf(n);
                Set<String> set = new HashSet<>(); // 진 부분 문자열 집합.

                for (int start = 0; start < str.length(); start++) {
                    // 0으로 시작하면 안 됨.
                    if (str.charAt(start) == '0') {
                        continue;
                    }

                    String res = "";
                    for (int i = start; i < str.length(); i++) {
                        res += str.charAt(i);

                        if (!res.equals(str)) {
                            set.add(res);
                        }
                    }
                }

                Iterator<String> it = set.iterator();

                int min = Integer.MAX_VALUE;
                while (it.hasNext()) {
                    int num = Integer.parseInt(str);
                    int temp = Integer.parseInt(it.next());

                    // 다음 게임의 상황이 패배하면,
                    // 현재 게임은 반드시 승리함.
                    if (dp[num - temp] == 0) {
                        min = Math.min(min, temp);
                    }
                }

                if (min != Integer.MAX_VALUE) {
                    dp[n] = min;
                }
            }
            bw.write(dp[N] == 0 ? "-1\n" : (dp[N] + "\n"));
        }
        bw.flush();
        bw.close();
        br.close();
    }

}