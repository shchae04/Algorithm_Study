package study.boj.twopointer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 백준 1253번 - 좋다
 *
 * 문제: N개의 수 중에서 어떤 수가 다른 수 두 개의 합으로 나타낼 수 있다면 그 수를 "좋다(GOOD)"고 한다.
 *       N개의 수 중 "좋다"인 수의 개수를 구하는 문제.
 *
 * 알고리즘: 정렬(Sorting) 및 투 포인터(Two Pointers)
 * - 주어진 수들을 오름차순으로 정렬하여 투 포인터 탐색을 용이하게 함
 * - 각 수에 대해 다른 두 수의 합으로 표현 가능한지 확인
 *
 * 핵심 아이디어:
 * 1. 정렬: 투 포인터를 사용하기 위해 배열을 오름차순으로 정렬
 * 2. 투 포인터 탐색:
 *    - 각 수(target)마다 배열의 양 끝(start, end)에서 시작하여 합을 확인
 *    - 합이 target보다 작으면 start를 증가시켜 합을 키움
 *    - 합이 target보다 크면 end를 감소시켜 합을 줄임
 *    - 합이 target과 같을 때, start와 end가 현재 target의 인덱스와 다른지 확인 (서로 다른 두 수여야 하므로)
 * 3. 예외 처리: 자기 자신을 포함하면 안 되므로 인덱스 확인 필수
 *
 * 시간복잡도: O(N^2)
 * - 정렬: O(N log N)
 * - 각 수에 대한 투 포인터 탐색: N * O(N) = O(N^2)
 * - N <= 2,000 이므로 O(N^2)은 약 4,000,000 연산으로 시간 제한(2초) 내 충분함
 *
 * 공간복잡도: O(N)
 * - 입력받은 수들을 저장할 배열 공간
 */
public class boj1253 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int count = 0;
        long[] arr = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i=0; i<N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(arr);

        for (int i = 0; i < N; i++) {
            int sIdx = 0;
            int eIdx = N - 1;
            long target = arr[i];

            while (sIdx < eIdx) {
                if (arr[sIdx] + arr[eIdx] == target) {
                    if (sIdx != i && eIdx != i) {
                        count++;
                        break;
                    } else if (sIdx == i) {
                        sIdx++;
                    } else if(eIdx == i) {
                        eIdx--;
                    }
                } else if (arr[sIdx] + arr[eIdx] < target) {
                    sIdx++;
                } else if (arr[sIdx] + arr[eIdx] > target) {
                    eIdx--;
                }
            }
        }
        System.out.println(count);
    }
}
