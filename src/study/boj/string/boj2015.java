package study.boj.string;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 백준 2015번 - 수들의 합 4
 * 
 * 문제: 배열 A의 부분합 중에서 합이 K인 것의 개수를 구하는 문제
 * 
 * 알고리즘: 누적합 + 해시맵
 * - 누적합 배열을 만들어서 부분합을 O(1)에 계산
 * - 해시맵을 사용하여 각 누적합의 출현 횟수를 저장
 * - 부분합 A[j]~A[i] = K 조건을 누적합[i] - 누적합[j-1] = K로 변환
 * - 즉, 누적합[j-1] = 누적합[i] - K인 j-1의 개수를 찾음
 * 
 * 시간복잡도: O(N)
 * 공간복잡도: O(N)
 */
public class boj2015 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        long k = Long.parseLong(st.nextToken());
        long[] arr = new long[n + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = arr[i-1] + Long.parseLong(st.nextToken());
        }

        long answer = 0L;
        Map<Long, Long> count = new HashMap<>();
        count.put(0L, 1L);
        for (int i = 1; i <= n; i++) {
            answer += count.getOrDefault(arr[i] - k, 0L);
            count.put(arr[i], count.getOrDefault(arr[i], 0L) + 1);
        }
        System.out.println(answer);
    }
}
