package study.boj.graph;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 백준 7696번 - 반복하지 않는 수
 * 
 * 문제: 서로 다른 자리수로만 이루어진 수들을 오름차순으로 나열할 때 N번째 수를 구하는 문제
 * 예: 1, 2, 3, ..., 9, 10, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 23, ...
 * 
 * 알고리즘: DFS + 비트마스킹
 * - 자릿수별로 1자리부터 10자리까지 사전 순으로 생성
 * - 비트마스크로 사용된 숫자 추적 (0~9)
 * - 각 자리에서 아직 사용하지 않은 숫자만 사용
 * - DFS로 어릴 때부터 큰 숫자 순으로 생성
 * - 모든 쿼리의 최댓값까지만 미리 생성하여 효율성 향상
 * 
 * 시간복잡도: O(K) - K: 생성될 수의 개수 (최대 1,000,000)
 * 공간복잡돀: O(K)
 */
public class boj7696 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        // 입력을 모두 읽어 저장하고 최댓값을 구한다.
        List<Integer> queries = new ArrayList<>();
        int maxN = 0;
        while (true) {
            String line = br.readLine();
            if (line == null) break;
            line = line.trim();
            if (line.isEmpty()) continue;
            int n = Integer.parseInt(line);
            if (n == 0) break;
            queries.add(n);
            if (n > maxN) maxN = n;
        }
        if (maxN == 0) {
            bw.flush();
            return;
        }

        // n번째(1-indexed) 값을 담을 배열 생성
        int[] arr = new int[maxN + 1];
        Counter counter = new Counter();

        // 길이 1부터 10까지, 사전(숫자 오름차순) 순서대로 생성
        for (int len = 1; len <= 10 && counter.value < maxN; len++) {
            // 첫 자리는 1~9 (선두 0 금지)
            for (int d = 1; d <= 9 && counter.value < maxN; d++) {
                int used = 1 << d;
                dfs(len - 1, d, used, maxN, arr, counter);
            }
        }

        // 쿼리 출력
        for (int n : queries) {
            bw.write(Integer.toString(arr[n]));
            bw.newLine();
        }
        bw.flush();
    }

    // 남은 자릿수 remain을 채우는 깊이우선 탐색
    // 현재까지 만든 수 current, 사용된 숫자 비트마스크 used
    private static void dfs(int remain, int current, int used, int maxN, int[] arr, Counter counter) {
        if (counter.value >= maxN) return;

        if (remain == 0) {
            // 하나 완료: 1-indexed로 저장
            arr[++counter.value] = current;
            return;
        }

        // 다음 자리는 0~9 중에서 아직 사용하지 않은 숫자
        for (int nd = 0; nd <= 9 && counter.value < maxN; nd++) {
            if ((used & (1 << nd)) != 0) continue;
            dfs(remain - 1, current * 10 + nd, used | (1 << nd), maxN, arr, counter);
        }
    }

    // 카운터를 위한 래퍼 (참조로 공유하기 위함)
    private static class Counter {
        int value = 0;
    }
}