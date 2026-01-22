package study.boj.string;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * 백준 10813번 - 공 바꾸기
 * 
 * 문제: N개의 바구니에 각각 1번부터 N번 공이 들어있을 때, M번 공을 바꾼다
 * 
 * 알고리즘: 단순 배열 조작
 * - 초기 상태: 바구니[i] = i (i번 바구니에 i번 공)
 * - M번의 교환 연산 수행: (x, y) 입력 시 x번 바구니와 y번 바구니의 공 교환
 * - temp 변수를 사용한 swap 연산
 * 
 * 시간복잡도: O(M)
 * 공간복잡도: O(N)
 */
public class boj10813 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] budget = new int[N + 1]; // 1 + index
        for (int i = 1; i <= N; i++) {
            budget[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            int temp = budget[x];
            budget[x] = budget[y];
            budget[y] = temp;
        }

        for (int i = 1; i <= N; i++) {
            bw.write(budget[i] + " ");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}