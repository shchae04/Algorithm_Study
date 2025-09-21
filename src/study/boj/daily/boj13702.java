package study.boj.daily;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 백준 13702번 - 이상한 술집
 *
 * 문제: N개의 주전자에 들어있는 막걸리를 K명의 친구들에게 똑같은 양으로 나눠주는 문제
 * 최대한 많은 양의 막걸리를 나눠줄 수 있는 용량을 구해야 함
 *
 * 알고리즘: 이분탐색 (Binary Search)
 * - 답이 될 수 있는 범위: 1 ~ 가장 큰 주전자의 용량
 * - 특정 용량 x로 K명에게 나눠줄 수 있는지 판단
 * - 각 주전자에서 용량 x로 몇 잔을 만들 수 있는지 계산 (makgeolli / x)
 * - 총 잔의 개수가 K 이상이면 가능, 미만이면 불가능
 * - 가능한 경우 더 큰 값을 찾기 위해 left 증가
 * - 불가능한 경우 더 작은 값을 찾기 위해 right 감소
 *
 * 시간복잡도: O(N log M) - N: 주전자 개수, M: 최대 막걸리 용량
 * 공간복잡도: O(N)
 */
public class boj13702 {
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            String[] firstLine = br.readLine().split(" ");
            int N = Integer.parseInt(firstLine[0]); // 주전자 개수
            int K = Integer.parseInt(firstLine[1]); // 친구 수

            long[] makgeolliList = new long[N];
            long maxMakgeolli = 0;

            for (int i = 0; i < N; i++) {
                makgeolliList[i] = Long.parseLong(br.readLine());
                maxMakgeolli = Math.max(maxMakgeolli, makgeolliList[i]);
            }

            long left = 1;
            long right = maxMakgeolli;
            long result = 0;

            while (left <= right) {
                long mid = (left + right) / 2;
                int count = 0;

                for (long makgeolli : makgeolliList) {
                    count += makgeolli / mid;
                }

                if (count >= K) {
                    result = mid; // 가능하면 결과 갱신
                    left = mid + 1; // 더 큰 값을 찾기 위해 left 갱신
                } else {
                    right = mid - 1; // mid 값이 너무 크면 줄임
                }
            }

            System.out.println(result);
            br.close();
        }
    }