package study.boj.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj2293 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] coin = new int[n];
        // dp[j]는 합계 j를 만드는 경우의 수를 저장하는 배열
        int[] dp = new int[k+1];

        for (int i = 0; i < n; i++) {
            coin[i] = Integer.parseInt(br.readLine());
        }

        // 0원을 만드는 방법은 '아무 동전도 사용하지 않는 것' 1가지이므로 초기값을 1로 설정
        // 이 1은 새로운 동전이 추가될 때 dp[j-coin[i]]가 0원일 경우 1을 더해주는 기준점이 됨
        dp[0] = 1;

        // 1. 사용할 수 있는 동전의 종류를 하나씩 늘려가며 확인
        for (int i = 0; i < n; i++) {
            // 2. 1원부터 목표 금액 k원까지 순차적으로 경우의 수 계산
            for (int j=1; j<=k; j++) {
                // 3. 현재 목표 금액(j)이 현재 확인 중인 동전의 가치(coin[i])보다 크거나 같아야 사용 가능
                if (j >= coin[i]) {
                    // dp[j]: 기존 동전들로 j원을 만들었던 경우의 수
                    // dp[j - coin[i]]: 현재 동전(coin[i])을 하나 썼을 때, 남은 금액을 만드는 경우의 수
                    // 이 두 값을 더해줌으로써 현재 동전을 포함한 새로운 경우의 수를 누적함
                    dp[j] += dp[j-coin[i]];
                }
            }
        }
        System.out.println(dp[k] + "\n");

    }
}
