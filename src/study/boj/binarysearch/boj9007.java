package study.boj.binarysearch;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 백준 9007번 - 카누선수
 *
 * 문제: 4개 반에서 각각 1명씩 총 4명을 선발하여 카누팀을 구성할 때,
 *      4명의 몸무게 합이 목표 몸무게 k에 가장 가까운 조합 찾기
 *
 * 알고리즘: Meet-in-the-Middle (중간에서 만나기) + 이분탐색
 * - 4개 반을 2개 그룹으로 나누어 각각의 가능한 조합을 미리 계산
 * - 첫 번째 그룹(1반+2반)의 모든 조합에 대해 두 번째 그룹(3반+4반)에서 최적해 탐색
 * - 정렬된 배열에서 이분탐색을 통해 목표값에 가장 가까운 값 찾기
 *
 * 핵심 아이디어:
 * 1. 4중 반복문 O(n⁴) 대신 2단계로 분할하여 O(n²log n²)로 최적화
 * 2. 1반+2반의 모든 조합(n²개)과 3반+4반의 모든 조합(n²개)을 각각 계산
 * 3. 3반+4반 조합을 정렬하여 이분탐색으로 최적 매칭 찾기
 * 4. 목표값과의 차이가 같을 때는 더 작은 값을 우선 선택
 *
 * Meet-in-the-Middle 기법:
 * - 지수적 복잡도 문제를 √N 크기의 두 부분으로 나누어 해결
 * - 전체 탐색 공간을 반으로 나누어 각각 독립적으로 처리
 * - 두 부분의 결과를 조합하여 최종 답 도출
 * - 4-SUM 문제의 최적 해법 중 하나
 *
 * 시간복잡도: O(n²log n²) = O(n²log n)
 * - 1반+2반 조합 생성: O(n²)
 * - 3반+4반 조합 생성: O(n²)
 * - 3반+4반 조합 정렬: O(n²log n²) = O(n²log n)
 * - 이분탐색 n²회 수행: O(n² × log n²) = O(n²log n)
 * - 전체: O(n²log n)
 *
 * 공간복잡도: O(n²)
 * - arr12 배열: n²개 원소 저장 (1반+2반 조합)
 * - arr34 배열: n²개 원소 저장 (3반+4반 조합)
 * - 추가 변수들: O(1)
 *
 * 브루트포스 O(n⁴) 대비 대폭적인 성능 향상으로 대규모 입력 처리 가능
 * n=1000일 때: O(n⁴) = 10¹² vs O(n²log n) ≈ 2×10⁷ (약 50,000배 빠름)
 */
public class boj9007 {
    static int k, n;           // k: 목표 몸무게, n: 각 반의 학생 수
    static long[] arr34;       // 3반+4반의 모든 조합 저장 (정렬됨)
    static long abs, result;   // abs: 현재 최소 차이값, result: 현재 최적해

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder answer = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        long[] arr;
        long[] arr12;
        long tmp;
        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            k = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());

            // 1반 학생들의 몸무게 입력 (임시로 arr 사용)
            arr = new long[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                arr[i] = Long.parseLong(st.nextToken());
            }

            // 2반 학생들의 몸무게 입력하면서 1반과의 모든 조합 계산
            // Meet-in-the-Middle: 첫 번째 그룹 (1반 + 2반) 처리
            arr12 = new long[n * n];  // 1반과 2반의 모든 조합 n² 개
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                tmp = Long.parseLong(st.nextToken());  // 2반 i번째 학생 몸무게
                for (int j = 0; j < n; j++) {
                    arr12[n * i + j] = tmp + arr[j];    // 2반 i번째 + 1반 j번째
                }
            }

            // 3반 학생들의 몸무게 입력 (arr 재사용)
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                arr[i] = Long.parseLong(st.nextToken());
            }
            // 4반 학생들의 몸무게 입력하면서 3반과의 모든 조합 계산
            // Meet-in-the-Middle: 두 번째 그룹 (3반 + 4반) 처리
            arr34 = new long[n * n];  // 3반과 4반의 모든 조합 n² 개
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                tmp = Long.parseLong(st.nextToken());  // 4반 i번째 학생 몸무게
                for (int j = 0; j < n; j++) {
                    arr34[n * i + j] = tmp + arr[j];    // 4반 i번째 + 3반 j번째
                }
            }

            // 3반+4반 조합을 정렬 (이분탐색을 위해 필수)
            Arrays.sort(arr34);  // O(n²log n²) = O(n²log n)

            // 최대 차이값으로 초기화 (최적화를 위한 초기값 설정)
            abs = k - 3 > 40_000_001 - k ? k - 3 : 40_000_001 - k;

            // 1반+2반의 각 조합에 대해 3반+4반에서 최적 매칭 탐색
            for (int i = 0; i < n * n; i++) {
                binarySearch(arr12[i]);  // arr12[i] + arr34[?] 가 k에 가장 가까운 값 찾기
                if(result == k) break;   // 정확히 k가 나오면 더 이상 탐색 불필요
            }

            answer.append(result + "\n");
        }

        System.out.print(answer);
    }

    /**
     * 이분탐색을 통해 num + arr34[?]가 k에 가장 가까운 값 찾기
     *
     * @param num 1반+2반 조합의 몸무게 합
     *
     * 핵심 로직:
     * 1. 정렬된 arr34에서 k - num에 가장 가까운 값 탐색
     * 2. 차이가 더 작으면 갱신, 같으면 더 작은 값 우선 선택
     * 3. 이분탐색으로 O(log n²) = O(log n) 시간에 최적해 탐색
     */
    private static void binarySearch(long num) {
        int s = 0, e = n * n - 1, m;  // 이분탐색 범위: [0, n²-1]
        long tmp;  // 현재 조합과 목표값의 차이

        while(s <= e) {
            m = (s + e) / 2;              // 중간 인덱스
            tmp = arr34[m] + num - k;     // 현재 조합의 목표값과의 차이

            // 더 좋은 해를 발견한 경우
            if(abs > Math.abs(tmp)) {
                abs = Math.abs(tmp);      // 최소 차이값 갱신
                result = arr34[m] + num;  // 최적해 갱신
            }
            // 차이가 같지만 현재 조합이 목표값보다 작은 경우 (문제 조건: 더 작은 값 우선)
            else if(abs == Math.abs(tmp) && tmp < 0) {
                result = arr34[m] + num;
            }

            // 이분탐색 진행
            if(arr34[m] + num < k) s = m + 1;      // 합이 목표값보다 작으면 오른쪽 탐색
            else if(arr34[m] + num == k) return;   // 정확히 일치하면 종료
            else e = m - 1;                        // 합이 목표값보다 크면 왼쪽 탐색
        }
    }
}