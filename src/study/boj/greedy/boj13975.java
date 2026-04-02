package study.boj.greedy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 백준 13975 - 파일 합치기 3
 * 가장 작은 두 파일을 먼저 합쳐야 전체 비용이 최소가 되므로
 * 우선순위 큐(최소 힙)에서 두 개씩 꺼내 합치는 과정을 반복한다.
 */
public class boj13975 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCaseCount = Integer.parseInt(br.readLine());

        for (int testCase = 0; testCase < testCaseCount; testCase++) {
            int fileCount = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            PriorityQueue<Long> files = new PriorityQueue<>();

            for (int i = 0; i < fileCount; i++) {
                files.offer(Long.parseLong(st.nextToken()));
            }

            bw.write(calculateMinimumMergeCost(files) + "\n");
        }

        bw.flush();
    }

    /**
     * 가장 작은 두 파일을 계속 합치면 전체 비용이 최소가 된다.
     * 합쳐진 파일도 다시 비교 대상이 되므로 우선순위 큐에 재삽입한다.
     */
    private static long calculateMinimumMergeCost(PriorityQueue<Long> files) {
        long totalCost = 0;

        while (files.size() > 1) {
            long firstSmallestFile = files.poll();
            long secondSmallestFile = files.poll();
            long mergedFileSize = firstSmallestFile + secondSmallestFile;

            totalCost += mergedFileSize;
            files.offer(mergedFileSize);
        }

        return totalCost;
    }
}
