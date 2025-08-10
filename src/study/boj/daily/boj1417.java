package study.boj.daily;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * 국회의원 선거
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
