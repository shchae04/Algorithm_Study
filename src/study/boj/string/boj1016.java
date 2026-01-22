package study.boj.string;
import java.io.*;
import java.util.StringTokenizer;

/**
 * 백준 1016번 - 제곱 ㄴㄴ 수
 *
 * 문제: min과 max 사이의 정수 중, 어떤 제곱수(1보다 큰 제곱수)로도 나누어 떨어지지 않는 수(제곱 ㄴㄴ 수)의 개수를 구하는 문제
 * - min, max의 범위가 최대 1,000,000,000,000 (10^12) + 1,000,000 까지 가능
 * - max - min 은 최대 1,000,000
 *
 * 알고리즘: 에라토스테네스의 체 응용 (Sieve of Eratosthenes / Offset Sieve)
 * - 소수를 찾는 대신, 제곱수의 배수를 지워나가는 방식
 * - 범위가 크기 때문에 min ~ max 구간만 배열로 관리
 *
 * 핵심 아이디어:
 * 1. 범위 설정:
 *    - 어떤 수 X가 제곱수 S(>1)로 나누어 떨어지려면, S의 제곱근인 i는 sqrt(X) 이하여야 함.
 *    - 따라서 i는 2부터 sqrt(max)까지 확인하면 충분함.
 *
 * 2. 제곱수 배수 체크 (Offset Indexing):
 *    - i^2 (제곱수)의 배수들을 min ~ max 범위 내에서 찾아 지움.
 *    - min 이상의 첫 번째 i^2의 배수를 찾기 위한 시작점 계산:
 *      start = (min % i^2 == 0) ? min / i^2 : (min / i^2) + 1
 *    - 배열의 인덱스는 '값 - min' 으로 매핑하여 사용 (0 ~ max-min)
 *
 * 3. 자료형 주의:
 *    - i가 1,000,000에 근접할 때 i*i는 int 범위를 초과하므로 long 자료형 사용 필수.
 *
 * 시간복잡도: O((max-min) + sqrt(max))
 * - 제곱수들의 배수를 탐색하는 과정은 매우 빠르게 수렴함.
 *
 * 공간복잡도: O(max - min)
 * - 약 1,000,000개의 boolean 배열 사용
 */
public class boj1016 {

    // 제곱수로 나누어 떨어지는지 여부를 저장 (true: 제곱 ㄴㄴ 수가 아님, false: 제곱 ㄴㄴ 수)
    static boolean[] hasSquareFactor;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        long min = Long.parseLong(st.nextToken());
        long max = Long.parseLong(st.nextToken());

        // min ~ max 범위의 크기만큼 배열 생성 (최대 1,000,000)
        int size = (int) (max - min);
        hasSquareFactor = new boolean[size + 1];

        // 제곱수의 배수들을 체크
        checkSquareFree(min, max);

        // 체크되지 않은 수(제곱 ㄴㄴ 수)의 개수를 카운트
        int count = 0;
        for (int i = 0; i <= size; i++) {
            if (!hasSquareFactor[i]) {
                count++;
            }
        }

        bw.write(String.valueOf(count));
        bw.flush();
        bw.close();
        br.close();
    }

    static void checkSquareFree(long min, long max) {
        // 2의 제곱부터 시작해서 i^2이 max를 넘지 않을 때까지 반복
        for (long i = 2; i * i <= max; i++) {
            long square = i * i; 

            // min 값을 square로 나누어, 범위 내의 첫 번째 배수를 찾음
            // 예: min=10, square=4 -> 10/4 = 2. 배수는 4*2=8(범위 밖), 4*3=12(범위 안)
            // 따라서 나누어 떨어지지 않으면 몫에 1을 더해 첫 번째 배수의 곱(start)을 구함
            long start = min / square;
            if (min % square != 0) {
                start++;
            }

            // start * square 부터 시작하여 max 이하인 모든 배수를 지움
            for (long j = start; j * square <= max; j++) {
                // (실제 숫자 - min)을 인덱스로 사용하여 메모리 절약 (Offset Indexing)
                int index = (int) (j * square - min);
                hasSquareFactor[index] = true;
            }
        }
    }
}
