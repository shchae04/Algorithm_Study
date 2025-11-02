package study.boj.daily;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * 백준 1516번 - 게임 개발
 * 
 * 문제: 건물을 짓는 순서를 고려한 최소 시간 구하기 (위상 정렬)
 * 
 * 알고리즘: 위상 정렬 + 동적 계획법
 * - 각 건물의 선행 건물들을 모두 완료한 후 건설 시작
 * - 위상 정렬을 통해 의존성 해결
 * - dp[i] = max(dp[선행건물]) + 건설시간[i]
 * 
 * 시간복잡도: O(V + E) (V: 건물 수, E: 의존성 관계 수)
 * 공간복잡도: O(V + E)
 * 
 * 주의: 현재 코드는 미완성 상태
 */
public class boj1516 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new java.io.OutputStreamWriter(System.out));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        // 걸리는 시간, 건물 번호
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken()); // time
            arr[i][1] = Integer.parseInt(st.nextToken()); // no
        }


    }
}
