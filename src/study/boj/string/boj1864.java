package study.boj.string;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * 백준 1864번 - 문어 숫자
 *
 * 문제: 문어가 사용하는 8진법 숫자 체계를 10진법으로 변환
 *
 * 알고리즘: HashMap + 진법 변환
 * - 문어 숫자 기호와 값을 HashMap에 매핑
 * - 8진법 숫자를 10진법으로 변환 (자릿수 × 8^위치)
 * - '#'이 입력될 때까지 반복
 *
 * 시간복잡도: O(N * M) (N: 입력 개수, M: 각 숫자 길이)
 * 공간복잡도: O(1) (HashMap 크기 고정)
 */
public class boj1864 {

    // 문어 숫자 기호와 10진수 값을 매핑하는 Map
    public static Map<Character, Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        // 문어 숫자 기호 초기화
        set();

        // 입력을 받기 위한 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;

        // '#'이 입력될 때까지 반복
        while (!(line = br.readLine()).equals("#")) {
            // 변환된 10진수 값을 저장할 변수
            long ans = 0;
            // 현재 자릿수의 가중치 (8의 거듭제곱)
            long power = 1;

            // 문자열을 뒤에서부터 처리 (낮은 자릿수부터)
            for (int i = line.length() - 1; i >= 0; i--) {
                // 현재 문어 기호에 해당하는 숫자 값 가져오기
                int digit = map.get(line.charAt(i));
                // 8진법 변환: 현재 자릿수 값 × 8^위치
                ans += digit * power;
                // 다음 자릿수를 위해 가중치를 8배 증가
                power *= 8;
            }

            // 변환된 10진수 출력
            System.out.println(ans);
        }

        // BufferedReader 닫기
        br.close();
    }

    // 문어 숫자 기호와 10진수 값을 매핑하는 메서드
    private static void set() {
        map.put('-', 0);   // '-'는 0을 의미
        map.put('\\', 1);  // '\'는 1을 의미
        map.put('(', 2);   // '('는 2를 의미
        map.put('@', 3);   // '@'는 3을 의미
        map.put('?', 4);   // '?'는 4를 의미
        map.put('>', 5);   // '>'는 5를 의미
        map.put('&', 6);   // '&'는 6을 의미
        map.put('%', 7);   // '%'는 7을 의미
        map.put('/', -1);  // '/'는 -1을 의미 (특수 케이스)
    }
}