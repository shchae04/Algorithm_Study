package study.boj.binarysearch;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 1654번 - 랜선 자르기
 *
 * 문제: K개의 랜선을 잘라서 N개 이상의 같은 길이의 랜선을 만들 때, 만들 수 있는 최대 길이 구하기
 *
 * 알고리즘: 이분 탐색 (Binary Search) / 매개 변수 탐색 (Parametric Search)
 * - 결정 문제(decision problem)로 변환하여 해결: "길이가 x일 때 N개 이상의 랜선을 만들 수 있는가?"
 * - 가능한 랜선의 길이 범위(1 ~ max(lines))에서 이분 탐색을 수행하여 최적해를 찾음
 *
 * 핵심 아이디어:
 * 1. 탐색 범위 설정: 최소 길이 1, 최대 길이 max(lines)
 * 2. 중간값(mid) 설정: (start + end) / 2
 * 3. 랜선 개수 계산: 각 랜선을 mid로 나누었을 때 나오는 개수의 합을 구함
 * 4. 범위 조정:
 *    - 개수 >= N: 더 긴 길이도 가능한지 확인하기 위해 start = mid + 1 (최대 길이를 찾아야 하므로 현재 mid를 포함하여 탐색)
 *    - 개수 < N: 길이를 줄여야 하므로 end = mid - 1
 * 5. 자료형 주의: 랜선 길이는 2^31-1 범위의 정수이지만, start+end 과정에서 overflow 가능성이 있으므로 long 사용
 *
 * 시간복잡도: O(K * log(max_len))
 * - 탐색 범위가 1부터 랜선의 최대 길이(max_len)까지이므로 이분 탐색은 O(log(max_len))회 수행
 * - 각 단계마다 K개의 랜선을 순회하며 개수를 계산하므로 O(K) 소요
 *
 * 공간복잡도: O(K)
 * - K개의 랜선 길이를 저장할 배열 필요
 */

public class boj1654 {
    static int n, k;
    static int[] lines;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        k = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        lines = new int[k];
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < k; i++) {
            lines[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, lines[i]);
        }

        long s = 1;
        long e = max;
        long mid = (s + e) / 2;
        long cnt = 0;
        while (s <= e) {
            mid = (s + e) / 2;
            cnt = 0;
            for (int i=0; i<k; i++) {
                cnt += lines[i] / mid;
            }
            if (cnt < n) {
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }
        System.out.println(e);
        br.close();
    }
}