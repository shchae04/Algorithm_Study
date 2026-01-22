package study.boj.string;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 백준 10808번 - 알파벳 개수
 * 
 * 문제: 소문자로만 이루어진 단어에서 각 알파벳(a~z)의 출현 횟수를 구하는 문제
 * 
 * 알고리즘: 빈도 카운팅
 * - 26개 크기의 배열을 만들어 각 알파벳 빈도 저장
 * - 문자열의 각 문자에 대해 (c - 'a')로 인덱스 계산
 * - 해당 인덱스의 카운트를 1 증가
 * - a부터 z까지 순서대로 빈도 출력
 * 
 * 시간복잡도: O(L) - L: 문자열 길이
 * 공간복잡도: O(1) - 26개 고정 크기 배열
 */
public class boj10808 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = new int[26];
        String s = br.readLine();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            arr[c - 'a']++;
        }

        for (int i = 0; i < 26; i++) {
            System.out.print(arr[i] + " ");

        }
    }
}
