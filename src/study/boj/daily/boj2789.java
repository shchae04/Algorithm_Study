package study.boj.daily;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 유학 금지 - 비트마스크 최적화 (대문자 보장)
 */
public class boj2789 {

    // 'A'~'Z' 26개에 대응하는 비트마스크
    // CAMBRIDGE에 해당하는 자리만 1로 세팅
    private static final int BAN_MASK;
    static {
        int m = 0;
        m |= 1 << ('C' - 'A');
        m |= 1 << ('A' - 'A');
        m |= 1 << ('M' - 'A');
        m |= 1 << ('B' - 'A');
        m |= 1 << ('R' - 'A');
        m |= 1 << ('I' - 'A');
        m |= 1 << ('D' - 'A');
        m |= 1 << ('G' - 'A');
        m |= 1 << ('E' - 'A');
        BAN_MASK = m;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        StringBuilder sb = new StringBuilder(s.length());
        int mask = BAN_MASK;
        for (int i = 0, n = s.length(); i < n; i++) {
            char c = s.charAt(i);            // 대문자 보장
            int bit = 1 << (c - 'A');        // 해당 문자의 비트
            if ((mask & bit) == 0) {         // 금지 문자 아님
                sb.append(c);
            }
        }

        System.out.print(sb);
    }
}