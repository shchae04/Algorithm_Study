package study.boj.daily;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 3020번 - 개똥벌레
 * 
 * 문제: 동굴에서 개똥벌레가 일직선으로 날아가면서 만나는 장애물의 최소 개수와 
 *      그러한 높이 구간의 개수를 구하는 문제
 * 
 * 핵심 아이디어:
 * - 석순(아래에서 올라오는 장애물)과 종유석(위에서 내려오는 장애물)이 번갈아 배치
 * - 각 높이에서 파괴되는 장애물 수를 효율적으로 계산해야 함
 * - IMOS 기법을 사용하여 구간 업데이트를 O(1)에 처리하고, 누적합으로 실제 값 계산
 * 
 * 알고리즘: IMOS (Interval Manipulation Operations and Sums) 기법
 * - 구간 [start, end)에 value를 더하는 연산을 차분 배열로 처리
 * - imos[start] += value, imos[end] -= value로 구간 업데이트
 * - 최종적으로 누적합을 통해 실제 각 위치의 값을 계산
 * 
 * 구현 세부사항:
 * 1. 석순(짝수 인덱스): 높이 0부터 num-1까지 영향 → [0, num) 구간 +1
 * 2. 종유석(홀수 인덱스): 높이 H-num부터 H-1까지 영향 → [H-num, H) 구간 +1
 * 3. IMOS 배열로 각 구간 업데이트를 O(1)에 처리
 * 4. 누적합으로 각 높이에서의 실제 장애물 개수 계산
 * 5. 최솟값과 그 개수를 찾아 출력
 * 
 * 시간복잡도: O(N + H)
 * - 장애물 입력 및 IMOS 업데이트: O(N)
 * - 누적합 계산: O(H)
 * - 최솟값 탐색: O(H)
 * 
 * 공간복잡도: O(H)
 * - IMOS 배열 크기: H+1
 */
public class boj3020 {
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            // 입력: N(장애물 개수), H(동굴 높이)
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int N = Integer.parseInt(st.nextToken()); // 장애물 개수 (항상 짝수)
            int H = Integer.parseInt(st.nextToken()); // 동굴 높이

            // IMOS 배열 초기화 (차분 배열)
            int[] imos = new int[H+1];
            
            // 각 장애물에 대해 IMOS 기법으로 구간 업데이트
            for(int i = 0; i < N; i++) {
                int num = Integer.parseInt(br.readLine()); // 장애물 높이
                
                if (i % 2 == 0) {
                    // 석순 (아래에서 위로): 높이 0~(num-1)까지 영향
                    // 구간 [0, num)에 +1 적용
                    imos[0] += 1;      // 시작점에 +1
                    imos[num] -= 1;    // 끝점에 -1
                } else {
                    // 종유석 (위에서 아래로): 높이 (H-num)~(H-1)까지 영향  
                    // 구간 [H-num, H)에 +1 적용
                    imos[H-num] += 1;  // 시작점에 +1
                    imos[H] -= 1;      // 끝점에 -1
                }
            }

            // IMOS 배열을 누적합으로 변환하여 실제 값 계산
            int now = 0;
            for(int i = 0; i <= H; i++){
                now += imos[i];    // 차분값을 누적
                imos[i] = now;     // 실제 해당 높이에서의 장애물 개수
            }

            // 최솟값과 그 개수 탐색 (높이 0~H-1만 확인)
            int min = N;    // 최솟값 (최대값으로 초기화)
            int cnt = 0;    // 최솟값을 가지는 높이 개수
            
            for(int i = 0; i < H; i++){
                if(min > imos[i]){
                    min = imos[i];  // 더 작은 값 발견시 업데이트
                    cnt = 1;        // 개수 초기화
                } else if(min == imos[i]){
                    cnt++;          // 같은 최솟값 발견시 개수 증가
                }
            }
            
            // 결과 출력: 최소 장애물 개수와 그러한 구간 수
            System.out.println(min + " " + cnt);

        }
    }