package study.boj.daily;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 수학은 체육과목 입니다2
 *
 * Key : 관찰로 규칙 파악
 */
public class boj17362 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine().trim();

        int rem = 0;
        for (int i = 0; i < s.length(); i++) {
            rem = (rem * 10 + (s.charAt(i) - '0')) % 8;
        }

        // k = (n - 1) % 8
        int k = (rem - 1 + 8) % 8;

        int ans;
        switch (k) {
            case 0:  ans = 1; break;
            case 1:
            case 7:  ans = 2; break;
            case 2:
            case 6:  ans = 3; break;
            case 3:
            case 5:  ans = 4; break;
            default: ans = 5; break; // k == 4
        }

        System.out.println(ans);
    }

}
