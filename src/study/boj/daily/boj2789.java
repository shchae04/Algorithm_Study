package study.boj.daily;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 백준 2789번 - 유학 금지
 * 
 * 문제: 'CAMBRIDGE' 문자가 포함된 문자열에서 해당 문자들을 모두 제거한 결과를 출력
 * 대문자로만 이루어진 문자열 입력
 * 
 * 알고리즘: 비트마스킹 최적화
 * - 'CAMBRIDGE'에 해당하는 문자들을 비트마스크로 사전 설정
 * - 각 문자마다 해당 비트와 AND 연산으로 빠른 판단
 * - 금지 문자가 아닌 경우만 StringBuilder에 추가
 * - O(1) 시간에 문자 필터링 가능
 * 
 * 시간복잡도: O(N) - N: 문자열 길이
 * 공간복잡도: O(N)
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