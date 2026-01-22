package study.boj.string;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * 백준 4435번 - 중간계 전쟁
 * 
 * 문제: 선(강들프)과 악(사우론) 군대의 전투력을 계산하여 승부 결정
 * 선의 종족: 호비트, 인간, 엘프, 드워프, 독수리, 엔트 (6종족)
 * 악의 종족: 오크, 인간, 워그, 드라고스, 낪즐, 스학에다, 사우론 (7종족)
 * 
 * 알고리즘: 점수 계산 및 비교
 * - 선의 전투력: [1,2,3,3,4,10] 가중치 적용
 * - 악의 전투력: [1,2,2,2,3,5,10] 가중치 적용
 * - 각 종족의 수 * 해당 전투력을 모두 더해서 총점 계산
 * - 총점 비교하여 승부 결정 (동점 처리 포함)
 * 
 * 시간복잡도: O(N) - N: 테스트 케이스 수
 * 공간복잡도: O(1)
 */
public class boj4435 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
        StringTokenizer st;

        BufferedWriter bw = new BufferedWriter(new java.io.OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine().trim());
        int[] scoreA = {1, 2, 3, 3, 4, 10};
        int[] scoreB = {1, 2, 2, 2, 3, 5, 10};

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= N; i++) {
            // 선(Good) 6종족
            st = new StringTokenizer(br.readLine());
            int sumA = 0;
            for (int j = 0; j < 6; j++) {
                sumA += Integer.parseInt(st.nextToken()) * scoreA[j];
            }

            // 악(Evil) 7종족
            st = new StringTokenizer(br.readLine());
            int sumB = 0;
            for (int j = 0; j < 7; j++) {
                sumB += Integer.parseInt(st.nextToken()) * scoreB[j];
            }

            sb.append("Battle ").append(i).append(": ");
            if (sumA > sumB) {
                sb.append("Good triumphs over Evil");
            } else if (sumA < sumB) {
                sb.append("Evil eradicates all trace of Good");
            } else {
                sb.append("No victor on this battle field");
            }
            sb.append('\n');
        }

        bw.write(sb.toString());
        bw.flush();
    }
}