package study.boj.binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 12015 - 가장 긴 증가하는 부분 수열 2 (LIS)
 * 
 * [문제 개요]
 * 수열 A가 주어졌을 때, 가장 긴 증가하는 부분 수열의 길이를 구하는 프로그램.
 * N의 범위가 최대 1,000,000이므로 O(N^2) 알고리즘은 시간 초과가 발생하며,
 * 이분 탐색을 활용한 O(N log N) 방식으로 해결해야 합니다.
 * 
 * [입력 예시]
 * 6
 * 10 20 10 30 20 50
 * 
 * [출력 예시]
 * 4
 * 
 * [동작 과정 설명 - 예시 기반]
 * 입력: 10 20 10 30 20 50
 * 1. 10 입력: LIS 배열이 비어있으므로 추가. [10], 길이=1
 * 2. 20 입력: 20 > 10 이므로 뒤에 추가. [10, 20], 길이=2
 * 3. 10 입력: 10 < 20 이므로 이분 탐색 수행. 10 이상의 가장 작은 값 위치인 index 0의 10을 10으로 대체. [10, 20], 길이=2
 * 4. 30 입력: 30 > 20 이므로 뒤에 추가. [10, 20, 30], 길이=3
 * 5. 20 입력: 20 < 30 이므로 이분 탐색 수행. 20 이상의 가장 작은 값 위치인 index 1의 20을 20으로 대체. [10, 20, 30], 길이=3
 * 6. 50 입력: 50 > 30 이므로 뒤에 추가. [10, 20, 30, 50], 길이=4
 * 최종 결과: 4
 */
public class boj12015_re {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // N: 수열의 크기
        int N = Integer.parseInt(br.readLine());

        // lis: 가장 긴 증가하는 부분 수열의 형태를 유지할 배열
        // len: 현재까지 완성된 LIS의 길이
        int[] lis = new int[N];
        
        // 백준 입력은 한 줄에 공백으로 구분되어 들어오므로 StringTokenizer 사용 필수
        StringTokenizer st = new StringTokenizer(br.readLine());

        int len = 0;
        for (int i = 0; i < N; i++) {
            int key = Integer.parseInt(st.nextToken());

            // 1. 현재 숫자가 LIS의 마지막 값보다 크면 뒤에 붙임
            if (len == 0 || key > lis[len - 1]) {
                lis[len++] = key;
            } 
            // 2. 현재 숫자가 LIS의 마지막 값보다 작거나 같으면, 
            //    LIS 배열 내에서 key가 들어갈 적절한 위치를 이분 탐색(Lower Bound)으로 찾음
            else {
                int low = 0;
                int high = len - 1;

                while (low < high) {
                    int mid = (low + high) / 2;
                    
                    // key보다 작은 값이면 오른쪽 탐색
                    if (lis[mid] < key) {
                        low = mid + 1;
                    } 
                    // key보다 크거나 같은 값이면 왼쪽 탐색 (범위 좁히기)
                    else {
                        high = mid;
                    }
                }
                
                // 찾은 위치(low)의 값을 현재 값(key)으로 대체
                // 이는 더 작은 숫자로 대체함으로써 뒤에 더 긴 수열이 올 가능성을 높여줌
                lis[low] = key;
            }
        }

        // 최종적인 LIS의 길이 출력
        System.out.println(len);
    }
}