package study.boj.greedy;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * 백준 1417번 - 국회의원 선거
 * 
 * 문제: N명의 후보 중 다솔이 가장 많은 표를 얻기 위해 매수해야 할 최소 인원수를 구하는 문제
 * 
 * 알고리즘: 그리디 + 우선순위 큐 (Max Heap)
 * - 다솔보다 표가 많은 후보들을 우선순위 큐에 저장
 * - 다솔보다 표가 많거나 같은 후보가 있는 한 반복:
 *   1) 가장 많은 표를 받은 후보에서 1표 가져오기
 *   2) 다솔 표수 1 증가
 *   3) 매수 횟수 1 증가
 * - 후보가 1명인 경우 매수 불필요 (0 출력)
 * 
 * 시간복잡도: O(M log N) - M: 매수 횟수, N: 후보 수
 * 공간복잡도: O(N)
 */
public class boj1417 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());
        int dasom = Integer.parseInt(br.readLine().trim());

        if (N == 1) {
            System.out.println(0);
            return;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 2; i <= N; i++) {
            pq.offer(Integer.parseInt(br.readLine().trim()));
        }

        int bribes = 0;
        while (!pq.isEmpty() && dasom <= pq.peek()) {
            int top = pq.poll();
            top -= 1;
            dasom += 1;
            bribes += 1;
            pq.offer(top);
        }

        System.out.println(bribes);
    }
}
