package study.boj.daily;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Strfry
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
