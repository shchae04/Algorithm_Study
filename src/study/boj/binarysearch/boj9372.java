package study.boj.binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 9372번: 상근이의 여행
 *
 * 문제 이해:
 * N개국을 여행하기 위해 타야 하는 비행기 종류의 최소 개수를 구하는 문제입니다.
 * 주어지는 그래프는 항상 연결 그래프임이 보장됩니다.
 *
 * 핵심 논리:
 * 1. N개의 국가(정점)를 모두 연결하면서 가장 적은 간선을 사용하는 구조는 '트리(Tree)' 형태입니다.
 *    이를 '신장 트리(Spanning Tree)'라고 합니다.
 * 2. 정점이 N개인 트리의 간선 개수는 항상 N-1개입니다.
 * 3. 따라서 비행기 경로가 어떻게 주어지든, 가중치가 없는 연결 그래프에서 모든 노드를 방문하는 최소 간선 수는 N-1입니다.
 * 4. 입력되는 간선 정보(M개)는 읽어만 두고 실제 계산에는 사용하지 않아도 됩니다.
 */
public class boj9372 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String first = br.readLine();
        if (first == null) {
            return;
        }

        int T = Integer.parseInt(first);
        while (T-- > 0) {
            String line = br.readLine();
            if (line == null) {
                return;
            }

            StringTokenizer st = new StringTokenizer(line);
            int N = Integer.parseInt(st.nextToken()); // 국가의 수
            int M = Integer.parseInt(st.nextToken()); // 비행기의 종류 (간선의 수)

            // M개의 줄에 비행기 경로(a b)가 주어집니다.
            // 하지만 답은 N-1로 정해져 있으므로, 입력 스트림을 비우기 위해 읽기만 하고 로직 처리는 하지 않습니다.
            for (int i = 0; i < M; i++) {
                br.readLine();
            }

            // 모든 국가를 여행하기 위한 최소 비행기 수 = N - 1
            sb.append(N - 1).append('\n');
        }
        System.out.println(sb);

    }
}
