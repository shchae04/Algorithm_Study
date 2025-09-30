package study.boj.daily;

import java.util.*;
import java.io.*;

/**
 * 백준 1806번 - 부분합
 *
 * 문제: N개의 자연수로 이루어진 수열에서 연속된 부분합이 S 이상이 되는 가장 짧은 길이 구하기
 *
 * 알고리즘: 투 포인터(Two Pointer) / 슬라이딩 윈도우(Sliding Window)
 * - 구간의 시작점과 끝점을 나타내는 두 개의 포인터를 사용
 * - 조건에 따라 구간을 확장하거나 축소하며 최적해 탐색
 * - 브루트포스 O(N²) 접근법을 O(N)으로 최적화
 *
 * 핵심 아이디어:
 * 1. start, end 두 포인터로 구간 [start, end)를 관리
 * 2. 현재 구간합이 S보다 작으면 end를 증가시켜 구간 확장
 * 3. 현재 구간합이 S 이상이면 최소 길이 갱신 후 start를 증가시켜 구간 축소
 * 4. 모든 가능한 구간을 효율적으로 탐색하여 최소 길이 찾기
 *
 * 시간복잡도: O(N) - 각 포인터가 최대 N번 이동
 * - start와 end 포인터가 각각 배열을 한 번씩만 순회
 * - 전체 원소에 대해 상수 시간 연산만 수행
 *
 * 공간복잡도: O(1) - 입력 배열 외 추가 공간 사용 없음
 * - 포인터와 합계 변수 등 상수 개의 변수만 사용
 *
 * 투 포인터 기법의 대표적인 응용 문제로, 구간 관련 최적화 문제의 핵심 패턴
 */

public class boj1806 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        int[] arr = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int end = 0;
        int len = Integer.MAX_VALUE;
        int sum = 0;
        while (start <= end && end <= n) {
            if (sum < s) {
                sum += arr[end++];
            } else if (sum >= s) {
                len = Math.min(len, end - start);
                sum -= arr[start++];
            }
        }
        System.out.println(len == Integer.MAX_VALUE ? 0 : len);
    }
}