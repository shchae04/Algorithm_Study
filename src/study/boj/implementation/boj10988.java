package study.boj.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj10988 {
    /**
     * BOJ 10988 - 팰린드롬인지 확인하기
     * 문자열의 앞/뒤 문자를 절반까지만 비교해 팰린드롬 여부를 판단한다.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();

        int length = line.length();

        // 기본값은 팰린드롬(true)로 두고, 불일치가 나오면 false로 변경한다.
        boolean flag = true;
        // i번째 문자와 뒤에서 i번째 문자를 비교한다. (length - i - 1)
        for (int i = 0; i < length / 2; i++) {
            if (line.charAt(i) != line.charAt(length - i - 1)) {
                flag = false;
                break;
            }
        }

        // 팰린드롬이면 1, 아니면 0 출력
        if (flag) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }
}
