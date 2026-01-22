package study.boj.dp;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

/**
 * 백준 10826번 - 피보나치 수 4
 *
 * 문제: N번째 피보나치 수를 구하기 (N ≤ 10,000)
 *
 * 알고리즘: 동적 프로그래밍(DP) + BigInteger
 * - 피보나치 수는 F(n) = F(n-1) + F(n-2) 점화식으로 정의
 * - 큰 수를 다루기 위해 BigInteger 사용
 * - 메모이제이션을 통해 이미 계산된 값 재사용
 *
 * 핵심 아이디어:
 * 1. F(0) = 0, F(1) = 1을 초기값으로 설정
 * 2. F(2)부터 F(N)까지 이전 두 값을 더해가며 계산
 * 3. BigInteger.add()를 사용하여 큰 수의 덧셈 처리
 *
 * 시간복잡도: O(N)
 * - N번째 피보나치 수까지 순차적으로 계산
 * - 각 항은 상수 시간에 계산 (BigInteger 연산은 O(1)로 간주)
 *
 * 공간복잡도: O(N)
 * - dp 배열에 10001개의 BigInteger 저장
 * - 실제로는 O(1) 공간만으로도 가능하지만 배열 사용
 *
 * BigInteger 사용 이유:
 * - 10000번째 피보나치 수는 약 2000자리 이상의 큰 수
 * - long 타입으로는 표현 불가능 (long은 최대 19자리)
 */

public class boj10826 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        BigInteger[] dp = new BigInteger[10001];
        dp[0] = BigInteger.ZERO;
        dp[1] = BigInteger.ONE;

        for(int i =2; i<=N; i++) {
            dp[i] = dp[i-2].add(dp[i-1]);
        }

        System.out.println(dp[N]);
        br.close();
    }
}
