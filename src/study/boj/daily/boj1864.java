
package study.boj.daily;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * 문어 숫자
 */
public class boj1864 {

    public static Map<Character, Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        set();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while (!(line = br.readLine()).equals("#")) {
            long ans = 0;
            long power = 1;

            for (int i = line.length() - 1; i >= 0; i--) {
                int digit = map.get(line.charAt(i));
                ans += digit * power;
                power *= 8;
            }

            System.out.println(ans);
        }
    }

    private static void set() {
        map.put('-', 0);
        map.put('\\', 1);
        map.put('(', 2);
        map.put('@', 3);
        map.put('?', 4);
        map.put('>', 5);
        map.put('&', 6);
        map.put('%', 7);
        map.put('/', -1);
    }
}