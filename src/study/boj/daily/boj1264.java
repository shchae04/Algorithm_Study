package study.boj.daily;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * 모음의 개수
 */
public class boj1264 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        char[] arr = new char[]{'a', 'e', 'i', 'o', 'u'};

        while (true) {
            String input = br.readLine();
            if (input.equals("#")) {
                break;
            }

            int count = 0;
            char[] charArray = input.toLowerCase().toCharArray();
            for (char c : charArray) {
                for (char vowel : arr) {
                    if (c == vowel) {
                        count++;
                    }
                }
            }
            bw.write(count + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}