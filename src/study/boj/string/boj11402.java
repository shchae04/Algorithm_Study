package study.boj.string;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 백준 11402번 - 이항 계수 4
 *
 * 문제: 이항 계수 C(N, K)를 소수 M으로 나눈 나머지를 구하기
 *
 * 알고리즘: 뤼카의 정리(Lucas's Theorem)를 이용한 이항 계수 계산
 * - 큰 N, K에 대한 이항 계수를 소수로 나눈 나머지를 효율적으로 계산
 * - N과 K를 소수 p진법으로 표현하여 이항 계수를 작은 이항 계수들의 곱으로 분해
 * - 페르마의 소정리를 이용한 모듈러 역원 계산
 *
 * 핵심 아이디어:
 * 1. 뤼카의 정리: C(n, r) mod p = C(n/p, r/p) * C(n%p, r%p) mod p
 *    (p는 소수, n과 r을 p진법으로 표현했을 때 각 자리수의 이항 계수를 곱함)
 * 2. 작은 이항 계수 계산: C(n, r) = n! / (r! * (n-r)!)
 *    - 팩토리얼을 미리 계산하고 역원을 구해 나눗셈 대신 곱셈 사용
 * 3. 모듈러 역원: a^(-1) mod p = a^(p-2) mod p (페르마의 소정리)
 *    - 분할 정복을 이용한 거듭제곱으로 O(log p) 시간에 계산
 *
 * 시간복잡도: O(p + log_p(N) * log p)
 * - 팩토리얼 전처리: O(p) - p개의 팩토리얼 값 계산
 * - 역원 계산: O(p) - p개의 역원 값 계산 (페르마의 소정리 적용 시 O(log p))
 * - 뤼카의 정리 재귀: O(log_p(N)) - N을 p진법으로 표현했을 때의 자릿수
 *
 * 공간복잡도: O(p)
 * - 팩토리얼과 역팩토리얼 배열을 위한 공간
 *
 * 수학적 배경:
 * - 뤼카의 정리는 소수 모듈로에서 이항 계수를 효율적으로 계산하는 정리
 * - 페르마의 소정리: p가 소수이고 a가 p의 배수가 아니면 a^(p-1) ≡ 1 (mod p)
 * - 이를 이용하면 a^(-1) ≡ a^(p-2) (mod p)로 역원을 구할 수 있음
 */

public class boj11402 {
    static long[] fact, invFact;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] st = br.readLine().split(" ");
        long n = Long.parseLong(st[0]);
        long r = Long.parseLong(st[1]);
        int p = Integer.parseInt(st[2]);

        // p마다 factorial 테이블 새로 생성
        buildFactorials(p);

        long ans = lucas(n, r, p);
        System.out.println(ans);
    }

    static void buildFactorials(int p) {
        fact = new long[p];
        invFact = new long[p];
        fact[0] = 1;
        for (int i = 1; i < p; i++) fact[i] = fact[i - 1] * i % p;
        invFact[p - 1] = modPow(fact[p - 1], p - 2, p); // 페르마 소정리로 역원
        for (int i = p - 2; i >= 0; i--) invFact[i] = invFact[i + 1] * (i + 1) % p;
    }

    static long comb(int n, int r, int p) {
        if (r > n) return 0;
        return fact[n] * invFact[r] % p * invFact[n - r] % p;
    }

    static long lucas(long n, long r, int p) {
        if (r == 0) return 1;
        return lucas(n / p, r / p, p) * comb((int) (n % p), (int) (r % p), p) % p;
    }

    static long modPow(long a, long b, int mod) {
        long res = 1;
        while (b > 0) {
            if ((b & 1) == 1) res = res * a % mod;
            a = a * a % mod;
            b >>= 1;
        }
        return res;
    }
}
