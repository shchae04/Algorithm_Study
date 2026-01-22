package study.boj.string;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 15961번 - 회전 초밥
 *
 * 문제 설명:
 * - 회전 초밥집에서 연속으로 k개의 접시를 먹을 때 가능한 최대 초밥 종류의 수를 구하는 문제
 * - N개의 접시가 원형으로 배치되어 있고, 각 접시에는 d종류 중 하나의 초밥이 있음
 * - 연속으로 k개의 접시를 선택했을 때, 쿠폰으로 c번 초밥을 추가로 무료로 받을 수 있음
 * - 목표: 먹을 수 있는 서로 다른 초밥 종류의 최대 개수 구하기
 *
 * 핵심 알고리즘: 슬라이딩 윈도우 (Sliding Window) + 해시 테이블
 *
 * 알고리즘 접근법:
 * 1. 원형 배열 처리: 배열 끝에 처음 k-1개 원소를 복사하여 선형 배열로 변환
 * 2. 초기 윈도우 설정: 첫 k개 접시에 대해 초밥 종류 개수 계산 (쿠폰 초밥 포함)
 * 3. 슬라이딩 윈도우 적용: 윈도우를 한 칸씩 이동하며 최대값 갱신
 *    - 윈도우에서 제거되는 초밥 처리
 *    - 윈도우에 추가되는 초밥 처리
 *    - 각 단계에서 현재 초밥 종류 개수와 최대값 비교
 *
 * 주요 최적화 기법:
 * - 해시 테이블(배열)을 사용하여 각 초밥 종류의 개수 추적 → O(1) 삽입/삭제
 * - 슬라이딩 윈도우로 전체 배열을 한 번만 순회 → O(N) 시간복잡도
 * - 원형 배열을 선형으로 변환하여 모듈러 연산 없이 처리
 *
 * 시간복잡도: O(N + k)
 * - 배열 확장: O(k)
 * - 초기 윈도우 설정: O(k)
 * - 슬라이딩 윈도우 이동: O(N)
 * - 총 시간복잡도: O(N + k), 일반적으로 k ≤ N이므로 O(N)
 *
 * 공간복잡도: O(N + d)
 * - 확장된 배열: O(N + k) ≈ O(N)
 * - 초밥 개수 추적 배열: O(d) (d는 초밥 종류 수)
 * - 총 공간복잡도: O(N + d)
 *
 * 핵심 데이터 구조:
 * 1. list[]: 원형 회전 초밥 배열을 선형으로 확장한 배열
 * 2. eaten[]: 각 초밥 종류별 현재 윈도우 내 개수를 저장하는 해시 테이블
 *
 */
public class boj15961 {
    static StringTokenizer st;
    static StringBuilder sb=new StringBuilder();
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st=new StringTokenizer(br.readLine());

        // 입력 파라미터
        int N = Integer.parseInt(st.nextToken()); // 회전 초밥의 개수
        int d = Integer.parseInt(st.nextToken()); // 초밥의 가짓수
        int k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시의 수
        int c = Integer.parseInt(st.nextToken()); // 쿠폰 번호 (무료로 먹을 수 있는 초밥)

        // 원형 배열을 선형으로 변환하기 위해 크기 N+k-1로 배열 생성
        // 마지막 k-1개 위치에 처음 k-1개 원소를 복사하여 원형 구조 구현
        int[] list=new int[N+k-1];
        for (int i = 0; i < N; i++) {
            list[i]=Integer.parseInt(br.readLine());
        } // 기본 N개 초밥 입력 완료

        // 원형 배열 처리: 처음 k-1개 원소를 배열 끝에 복사
        // 이렇게 하면 모든 가능한 연속 k개 구간을 선형 배열로 처리 가능
        for (int i = 0; i < k-1; i++) {
            list[N++]=list[i];
        }

        // 각 초밥 종류별 현재 윈도우 내 개수를 추적하는 해시 테이블
        int[] eaten=new int[d+1]; // 초밥 번호는 1~d, 인덱스 0은 사용하지 않음

        // 쿠폰 초밥을 미리 추가 (항상 무료로 먹을 수 있음)
        int max=1; // 쿠폰 초밥 1개로 시작
        eaten[c]+=1;

        // 초기 윈도우 설정: 첫 k개 접시에 대해 초밥 종류 개수 계산
        int start=0;
        for (int i = start; i < k; i++) {
            // 해당 초밥이 현재 윈도우에 없었다면 새로운 종류 추가
            if(eaten[list[i]]==0) {
                max++;
            }
            eaten[list[i]]+=1; // 해당 초밥 개수 증가
        }

        // 슬라이딩 윈도우 적용: 윈도우를 한 칸씩 오른쪽으로 이동
        start=0;
        int end=k;

        int result=max; // 현재 윈도우의 초밥 종류 개수
        for (int i = end; i < list.length; i++) {
            // 1단계: 윈도우에서 제거되는 초밥 처리 (왼쪽 끝 원소)
            eaten[list[start]]-=1;
            // 중요: 해당 초밥의 개수가 0이 되었을 때만 종류 개수에서 제외
            // 같은 종류의 초밥이 윈도우 내에 여러 개 있을 수 있기 때문
            if(eaten[list[start]]==0) {
                result-=1;
            }

            // 2단계: 윈도우에 추가되는 초밥 처리 (오른쪽 끝 원소)
            // 해당 초밥이 윈도우에 처음 추가되는 경우에만 종류 개수 증가
            if(eaten[list[i]]==0) result+=1;
            eaten[list[i]]+=1;

            // 3단계: 현재까지의 최대값과 비교하여 갱신
            max=Math.max(max, result);

            start++; // 윈도우의 시작 위치를 오른쪽으로 한 칸 이동
        }

        System.out.println(max); // 최대 초밥 종류 개수 출력
    }

    /*
     * 알고리즘 동작 예시:
     *
     * 입력 예제: N=8, d=30, k=4, c=30 (쿠폰 초밥)
     * 원형 초밥 배열: [7, 9, 7, 30, 2, 7, 9, 25]
     *
     * 1단계: 배열 확장 (원형 → 선형 변환)
     *    원래 배열: [7, 9, 7, 30, 2, 7, 9, 25]
     *    확장 배열: [7, 9, 7, 30, 2, 7, 9, 25, 7, 9, 7] (마지막 k-1=3개 추가)
     *
     * 2단계: 쿠폰 초밥 추가 및 초기 윈도우 설정
     *    쿠폰 초밥 30: eaten[30] = 1, max = 1
     *    첫 번째 윈도우 [7, 9, 7, 30] 처리:
     *    - 7 추가: eaten[7] = 1, max = 2 (새로운 종류)
     *    - 9 추가: eaten[9] = 1, max = 3 (새로운 종류)
     *    - 7 추가: eaten[7] = 2, max = 3 (이미 있는 종류)
     *    - 30 추가: eaten[30] = 2, max = 3 (이미 있는 종류)
     *    초기 result = 3
     *
     * 3단계: 슬라이딩 윈도우 이동
     *
     *    윈도우 [9, 7, 30, 2]:
     *    - 7 제거: eaten[7] = 1 (≠0), result = 3
     *    - 2 추가: eaten[2] = 1, result = 4, max = 4
     *
     *    윈도우 [7, 30, 2, 7]:
     *    - 9 제거: eaten[9] = 0, result = 3
     *    - 7 추가: eaten[7] = 2, result = 3, max = 4
     *
     *    윈도우 [30, 2, 7, 9]:
     *    - 7 제거: eaten[7] = 1 (≠0), result = 3
     *    - 9 추가: eaten[9] = 1, result = 4, max = 4
     *
     *    윈도우 [2, 7, 9, 25]:
     *    - 30 제거: eaten[30] = 1 (≠0, 쿠폰으로 여전히 있음), result = 4
     *    - 25 추가: eaten[25] = 1, result = 5, max = 5
     *
     * 최종 답: 5 (초밥 종류 2, 7, 9, 25, 30)
     *
     * 핵심 통찰:
     * - 쿠폰 초밥은 항상 무료로 추가되어 최적해에 기여
     * - 해시 테이블로 각 종류별 개수를 추적하여 O(1) 업데이트 달성
     * - 슬라이딩 윈도우로 모든 가능한 구간을 효율적으로 탐색
     * - 원형 배열의 선형 변환으로 경계 조건 처리 간소화
     */
}

