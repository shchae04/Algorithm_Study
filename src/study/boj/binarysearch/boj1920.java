package study.boj.binarysearch;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 백준 1920번 - 수 찾기
 *
 * 문제: N개의 정수 A[1], A[2], ..., A[N]이 주어져 있을 때, 이 안에 X라는 정수가 존재하는지 알아내는 프로그램
 * - N개의 정수가 주어짐
 * - M개의 정수가 주어지며, 이 수들이 A안에 존재하는지 확인
 * - 존재하면 1, 존재하지 않으면 0 출력
 *
 * 알고리즘: 정렬(Sorting) + 이분 탐색(Binary Search) + 최적화된 입출력(Fast I/O)
 * - N개의 정수를 오름차순으로 정렬
 * - M개의 각 정수에 대해 이분 탐색을 수행하여 존재 여부 확인
 * - StringBuilder와 사용자 정의 nextInt()를 사용하여 성능 극대화
 *
 * 핵심 아이디어:
 * 1. Fast I/O: System.in.read()를 직접 호출하여 정수를 파싱함으로써 입력 성능 향상
 * 2. 정렬: Arrays.sort()를 사용하여 탐색 전 정렬 수행
 * 3. 탐색: Arrays.binarySearch()로 존재 여부 확인
 * 4. 출력 최적화: StringBuilder를 통해 결과값을 모아 한 번에 출력
 *
 * 시간복잡도: O((N + M) log N)
 * - 정렬: O(N log N)
 * - 이분 탐색: M * O(log N)
 * - 최적화된 입출력을 통해 상수 시간(Constant Factor) 대폭 감소
 *
 * 공간복잡도: O(N)
 * - N개의 정수를 저장할 배열 필요
 *
 * 특이사항:
 * - nextInt() 함수는 공백과 음수 처리를 포함한 빠른 정수 입력 함수임
 * - 많은 양의 데이터를 처리할 때 StringBuilder를 사용하지 않으면 시간 초과가 날 수 있음
 */
public class boj1920 {
    public static void main(String[] args) throws Exception {
        int N = nextInt();
        int[] arrN = new int[N];
        for (int i = 0; i < N; i++) {
            arrN[i] = nextInt();
        }

        Arrays.sort(arrN);

        int M = nextInt();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            int key = nextInt();

            if (Arrays.binarySearch(arrN, key) >= 0) {
                sb.append(1).append('\n');
            } else {
                sb.append(0).append('\n');
            }
        }

        System.out.println(sb);
    }

    /**
     * 빠른 정수 입력을 위한 사용자 정의 함수
     */
    static int nextInt() throws Exception {
        int sign = 0;
        int n = 0;
        int c;
        while ((c = System.in.read()) <= 32) ; // 공백 무시
        if (c == 45) { // '-' 처리
            sign = 1;
            c = System.in.read();
        }
        do {
            n = (n << 3) + (n << 1) + (c & 15);
        } while ((c = System.in.read()) > 47 && c < 58);
        return sign == 0 ? n : ~n + 1;
    }
}
