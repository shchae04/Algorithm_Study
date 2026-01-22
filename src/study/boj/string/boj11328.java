package study.boj.string;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 백준 11328번 - Strfry
 * 
 * 문제: 첫 번째 문자열을 재배열하여 두 번째 문자열을 만들 수 있는지 판별
 * 
 * 알고리즘: 문자 빈도수 카운팅
 * - 각 문자열의 알파벳별 출현 횟수를 배열에 저장
 * - 두 배열이 완전히 같으면 재배열 가능 (Possible)
 * - 다르면 재배열 불가능 (Impossible)
 * 
 * 시간복잡도: O(N) (N: 문자열 길이)
 * 공간복잡도: O(1) (고정 크기 26의 카운팅 배열)
 */
public class boj11328 {

    static int[] origin = new int[26];
    static int[] parsed = new int[26];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            String s1 = st.nextToken();
            String s2 = st.nextToken();
            calc(s1, s2);
        }
    }

    private static void calc(String s1, String s2) {
        Arrays.fill(origin, 0);
        Arrays.fill(parsed, 0);

        int length = s1.length();
        for (int i=0; i<length; i++) {
            origin[s1.charAt(i) - 'a']++;
            parsed[s2.charAt(i) - 'a']++;
        }
        System.out.println(Arrays.equals(origin, parsed) ? "Possible" : "Impossible");
    }
}
