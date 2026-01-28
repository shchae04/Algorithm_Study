package study.boj.binarysearch;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj12991 {
    public static void main(String[] args) throws IOException {
        // 1. 빠른 입출력을 위한 세팅 (Scanner보다 훨씬 빠름)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // N(배열 크기)과 K(찾으려는 순서)를 읽어옴
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        long K = Long.parseLong(st.nextToken()); // K는 N*N만큼 커질 수 있어 long 타입 필수

        int[] A = new int[N];
        int[] B = new int[N];

        // 배열 A 입력 받기
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) A[i] = Integer.parseInt(st.nextToken());

        // 배열 B 입력 받기
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) B[i] = Integer.parseInt(st.nextToken());

        // 2. 투 포인터와 이분 탐색을 쓰기 위한 전제 조건: 정렬
        Arrays.sort(A); // O(N log N)
        Arrays.sort(B); // O(N log N)

        // 3. '값의 범위'를 이용한 이분 탐색 (Parametric Search)
        // start: 발생할 수 있는 최소 곱셈값, end: 최대 곱셈값
        long start = (long) A[0] * B[0];
        long end = (long) A[N - 1] * B[N - 1];
        long result = end;

        while (start <= end) {
            // 중간값(mid)을 정해서 "이 값보다 작은 곱이 K개 이상인가?"를 테스트함
            long mid = start + (end - start) / 2;

            // getCount: A[i] * B[j] <= mid를 만족하는 쌍의 개수를 구함
            if (getCount(A, B, N, mid) >= K) {
                // K개 이상이면, 일단 답의 후보로 저장하고 더 작은 값에서도 가능한지 탐색 (범위 왼쪽으로)
                result = mid;
                end = mid - 1;
            } else {
                // K개 미만이면, 값이 너무 작다는 뜻이므로 범위를 오른쪽으로 키움
                start = mid + 1;
            }
        }

        // 최적의 결과값 출력
        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
        br.close();
    }

    /**
     * 특정 값(limit) 이하인 곱셈 쌍의 개수를 O(N)만에 찾는 투 포인터 함수
     */
    private static long getCount(int[] A, int[] B, int N, long limit) {
        long count = 0;
        int j = N - 1; // B 배열의 가장 끝(큰 값)에서 시작

        // i는 A의 첫 번째(작은 값)부터 순회
        for (int i = 0; i < N; i++) {
            // A[i]와 B[j]를 곱했을 때 limit를 초과하면, B[j]를 더 작은 값으로 바꿔야 함
            // B가 정렬되어 있으므로 j를 왼쪽으로 한 칸 이동(j--)
            while (j >= 0 && (long) A[i] * B[j] > limit) {
                j--;
            }
            // j가 결정되면, B[0]부터 B[j]까지는 모두 A[i]와 곱했을 때 limit 이하임
            // 따라서 그 개수(j + 1)를 한 번에 더함
            count += (j + 1);
        }
        return count;
    }
}