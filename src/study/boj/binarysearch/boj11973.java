package study.boj.binarysearch;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 문제: Angry Cows (Silver) (백준 12289)
 * 풀이: 정답(최소 반경 R)에 대한 이분 탐색
 *
 * 1. 특정 반경 R이 주어졌을 때 K마리의 소로 모든 건초더미를 덮을 수 있는지 판별하는 함수 canCover(R)을 정의한다.
 * 2. canCover(R)이 참이면 더 작은 R을 시도하고, 거짓이면 더 큰 R을 시도하는 이분 탐색을 통해 최소 R을 찾는다.
 * 3. canCover(R) 함수는 그리디 방식으로 구현한다.
 * a. 건초더미 위치를 정렬한다.
 * b. 첫 번째 소로 가장 왼쪽 건초더미를 덮으면서 최대한 멀리 폭발이 닿도록 배치한다.
 * (폭발 범위: [x_i, x_i + 2R])
 * c. 폭발 범위에 닿지 않는 다음 건초더미가 나타나면 새로운 소를 사용한다.
 * d. 사용한 소의 총 개수가 K 이하인지 확인한다.
 */
public class boj11973 {
    static int N, K;
    static int[] locations;

    public static void main(String[] args) throws IOException {
        // --- 입력 처리 ---
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        locations = new int[N];
        for (int i = 0; i < N; i++) {
            locations[i] = Integer.parseInt(br.readLine());
        }

        // --- 핵심 로직 ---

        // 1. 그리디 판별을 위해 건초더미 위치를 정렬한다.
        Arrays.sort(locations);

        // 2. 최소 반경 R을 이분 탐색으로 찾는다.
        int low = 0;
        int high = 500_000_000; // 최대 가능한 R의 상한값 (10^9 / 2)
        int ans = high;

        while (low <= high) {
            int midR = low + (high - low) / 2;

            if (canCover(midR)) {
                // midR 반경으로 가능하다면, 더 작은 반경을 시도해본다.
                ans = midR;
                high = midR - 1;
            } else {
                // midR 반경으로 불가능하다면, 더 큰 반경이 필요하다.
                low = midR + 1;
            }
        }

        // --- 출력 ---
        System.out.println(ans);
    }

    /**
     * 주어진 반경 R과 K마리의 소로 모든 건초더미를 덮을 수 있는지 판별하는 함수
     * @param R 테스트할 폭발 반경
     * @return 덮을 수 있으면 true, 없으면 false
     */
    private static boolean canCover(int R) {
        if (N == 0) {
            return true;
        }

        int cowsUsed = 1;
        // 현재 소가 덮는 가장 오른쪽 좌표 (long 타입으로 오버플로우 방지)
        long coverageEnd = (long) locations[0] + 2 * R;

        // 정렬된 건초더미를 순회하며 확인
        for (int i = 1; i < N; i++) {
            // 현재 건초더미가 이전 소의 폭발 범위를 벗어난 경우
            if (locations[i] > coverageEnd) {
                // 새로운 소가 필요함
                cowsUsed++;
                // 새로운 소를 현재 건초더미를 덮도록 배치
                coverageEnd = (long) locations[i] + 2 * R;
            }
        }

        // 사용한 소의 수가 K마리 이하면 성공
        return cowsUsed <= K;
    }
}
