package study.boj.string;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 백준 20366번 - 같이 눈사람 만들래?
 *
 * 문제: N개의 눈덩이로 두 개의 눈사람을 만들 때, 두 눈사람의 키 차이의 최솟값을 구하기
 * - 각 눈사람은 2개의 눈덩이로 만들어짐 (총 4개의 서로 다른 눈덩이 사용)
 * - 눈사람의 키는 사용된 두 눈덩이의 높이의 합
 *
 * 알고리즘: 투 포인터 + 이중 반복문
 * - 첫 번째 눈사람: 이중 반복문으로 모든 두 눈덩이 조합 선택
 * - 두 번째 눈사람: 투 포인터로 남은 눈덩이들 중에서 최적의 조합 탐색
 *
 * 핵심 아이디어:
 * 1. 눈덩이 배열을 정렬하여 투 포인터 활용 가능하게 함
 * 2. 첫 번째 눈사람의 모든 가능한 조합에 대해 반복
 * 3. 각 조합마다 투 포인터로 두 번째 눈사람의 최적 조합 탐색
 * 4. 두 눈사람의 키 차이가 최소가 되는 경우를 찾음
 *
 * 시간복잡도: O(N²)
 * - 첫 번째 눈사람 선택: O(N²)
 * - 각 선택마다 투 포인터로 두 번째 눈사람 탐색: O(N)
 * - 전체: O(N² × N) = O(N³) → 최적화하면 O(N²)
 *
 * 공간복잡도: O(N) - 눈덩이 배열 저장 공간
 *
 * 주의사항:
 * - 4개의 서로 다른 눈덩이를 사용해야 함 (인덱스 중복 방지)
 * - 키 차이가 0이면 즉시 종료 가능
 */

public class boj20366 {

    static int N;
    static int snow[];
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        snow = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            snow[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(snow);

        for (int i = 0; i < N; i++) {
            for(int j = i + 1; j < N; j++) {
                int snow1 = snow[i] + snow[j];

                int start = 0;
                int end = N - 1;

                while (start < end) {
                    if (start == i || start == j) {
                        start++;
                        continue;
                    }

                    if (end == i || end == j) {
                        end--;
                        continue;
                    }

                    int snow2 = snow[start] + snow[end];
                    min = Math.min(min, Math.abs(snow1 - snow2));

                    if (snow1 > snow2) {
                        start++;
                    } else if (snow1 < snow2) {
                        end--;
                    } else {
                        System.out.println(0);
                        return;
                    }
                }
            }
        }
        System.out.println(min);
    }
}
