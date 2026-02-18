package study.boj.implementation;

import java.io.*;

/**
 * 백준 3943 - 헤일스톤 수열 (Hailstone Sequence)
 *
 * 이 클래스는 주어진 양의 정수 n에 대해 헤일스톤 수열의 규칙을 적용하여
 * 수열이 1이 될 때까지 생성되는 모든 수 중 가장 큰 값을 구합니다.
 *
 * [알고리즘 개요]
 * - n이 짝수라면 n = n / 2
 * - n이 홀수라면 n = n * 3 + 1
 * 위 과정을 n이 1이 될 때까지 반복합니다
 *
 * [복잡도 분석]
 * - 시간 복잡도: O(T * K), 여기서 T는 테스트 케이스의 수, K는 각 수열이 1에 도달하는 단계 수입니다.
 * - 공간 복잡도: O(1), 추가적인 배열이나 자료구조 없이 변수 몇 개만 사용합니다.
 */
public class boj3943 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for (int i=0; i<T; i++) {
            int n = Integer.parseInt(br.readLine());
            int max = n;

            // 헤일스톤 수열: n이 1이 될 때까지 반복하며 최대값을 갱신
            while (n != 1) {
                // 짝수면 2로 나누고, 홀수면 3을 곱하고 1을 더함
                n = (n % 2 == 0) ? n / 2 : 3 * n + 1;
                max = Math.max(n, max);
            }

            bw.write(max + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
