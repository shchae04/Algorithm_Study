package study.boj.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj27494 {

    private static boolean isWinningTicket(int x) {
        // "2023" 부분수열 체크를 정수 자릿수로 수행
        int[] target = {2, 0, 2, 3};
        int[] digits = new int[10]; // N <= 10,000,000 이면 최대 8자리, 여유로 10
        int len = 0;

        int cnt2 = 0, cnt0 = 0, cnt3 = 0;
        int t = x;
        while (t > 0) {
            int d = t % 10;
            digits[len++] = d;
            if (d == 2) cnt2++;
            else if (d == 0) cnt0++;
            else if (d == 3) cnt3++;
            t /= 10;
        }

        // 빠른 가지치기(선택): 필요한 숫자 개수가 부족하면 불가능
        if (cnt2 < 2 || cnt0 < 1 || cnt3 < 1) return false;

        int p = 0;
        for (int i = len - 1; i >= 0 && p < 4; i--) { // 가장 큰 자리부터
            if (digits[i] == target[p]) p++;
        }
        return p == 4;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        if (n < 2023) {
            System.out.println(0);
            return;
        }

        int ans = 0;
        for (int i = 2023; i <= n; i++) {
            if (isWinningTicket(i)) ans++;
        }
        System.out.println(ans);
    }
}