package study.boj.daily;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 백준 1427번 - 소트인사이드
 * 
 * 문제: 수를 이루는 각 숫자를 내림차순으로 정렬하기
 * 
 * 알고리즘: 계수 정렬(Counting Sort)
 * - 0~9 숫자의 개수를 카운팅 배열로 저장
 * - 9부터 0까지 역순으로 개수만큼 출력하여 내림차순 정렬 완성
 * 
 * 시간복잡도: O(N) (N: 숫자의 자릿수)
 * 공간복잡도: O(1) (고정 크기 10의 카운팅 배열)
 */
public class boj1427 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        int[] count = new int[10];

        for (char c : str.toCharArray()) {
            count[c - '0']++;
        }

        StringBuilder sb = new StringBuilder(str.length());
        for (int i = 9; i >= 0; i--) {
            while (count[i] > 0) {
                sb.append(i);
                count[i]--;
            }
        }

        System.out.println(sb);
    }
}