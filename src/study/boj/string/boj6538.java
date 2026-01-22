package study.boj.string;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * 백준 6538번 - Run Length Encoding (RLE)
 *
 * 문제: 주어진 규칙에 따라 런 길이 인코딩을 수행하는 프로그램 작성
 *
 * 알고리즘: 문자열 처리 / 시뮬레이션
 * 1. 반복 시퀀스(2-9개): [개수][문자] (9개 초과 시 9개 단위로 분할)
 * 2. 비반복 시퀀스: 1[문자열]1 (문자열 내 '1'은 '11'로 출력)
 *
 * 핵심 아이디어:
 * - 포인터를 이용해 현재 문자가 다음 문자와 같은지 확인하여 반복/비반복 여부 결정
 * - 비반복 구간을 최대한 길게 수집하여 1...1로 감싸 출력
 * - 반복 구간은 9개씩 끊어서 처리
 * - I/O 최적화를 위해 BufferedWriter 사용
 */
public class boj6538 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String line;

        while ((line = br.readLine()) != null) {
            bw.write(encode(line));
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static String encode(String s) {
        StringBuilder res = new StringBuilder();
        int n = s.length();
        int i = 0;

        while (i < n) {
            // 1. 반복되는 시퀀스 확인 (2개 이상 동일한 문자가 연속될 때)
            if (i + 1 < n && s.charAt(i) == s.charAt(i + 1)) {
                char c = s.charAt(i);
                int count = 0;
                while (i < n && s.charAt(i) == c) {
                    count++;
                    i++;
                }
                
                // 9개 단위로 끊어서 출력
                while (count > 9) {
                    res.append('9').append(c);
                    count -= 9;
                }
                // 남은 개수가 2개 이상이면 [개수][문자]
                if (count >= 2) {
                    res.append(count).append(c);
                } else if (count == 1) {
                    // 9개씩 끊고 마지막에 1개 남은 경우, 비반복 규칙 적용
                    res.append("1").append(c == '1' ? "11" : c).append("1");
                }
            } 
            // 2. 반복되지 않는 시퀀스 (단일 문자들)
            else {
                res.append('1');
                while (i < n) {
                    // 다음 문자가 현재 문자와 같아지면 반복 시퀀스 시작이므로 중단
                    if (i + 1 < n && s.charAt(i) == s.charAt(i + 1)) {
                        break;
                    }
                    char c = s.charAt(i);
                    if (c == '1') {
                        res.append("11"); // '1' 이스케이프
                    } else {
                        res.append(c);
                    }
                    i++;
                }
                res.append('1');
            }
        }
        return res.toString();
    }
}