package study.boj.daily;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 백준 2744번 - 대소문자 바꾸기
 *
 * 문제: 영어 소문자와 대문자로 이루어진 단어를 입력받아 대소문자를 바꿔서 출력하기
 *
 * 알고리즘: 문자열 순회 및 ASCII 코드 변환
 * - 문자열을 문자 배열로 변환하여 각 문자를 순회
 * - 대문자는 소문자로, 소문자는 대문자로 변환
 * - ASCII 코드 차이를 이용한 변환 수행
 *
 * 핵심 아이디어:
 * 1. 문자열을 char 배열로 변환하여 각 문자에 접근
 * 2. 대문자(A-Z)인 경우: 'A'와의 차이를 계산하여 'a'에 더함
 * 3. 소문자(a-z)인 경우: 'a'와의 차이를 계산하여 'A'에 더함
 * 4. ASCII 코드에서 대문자와 소문자의 차이는 32
 *
 * 시간복잡도: O(N) - N은 문자열의 길이
 * - 문자열의 모든 문자를 정확히 한 번씩만 검사
 *
 * 공간복잡도: O(N) - 문자 배열을 위한 추가 공간 필요
 * - 입력 문자열 길이만큼의 char 배열 사용
 *
 * 문자열 처리의 기본 문제로, ASCII 코드와 문자 변환의 개념을 익히기 좋음
 */

public class boj2744 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        char[] arr = str.toCharArray(); // str.toCharArray()로 문자열을 문자 배열로 변환

        for (int i = 0; i < str.length(); i++) {
            char ch = arr[i]; // arr[i]를 ch에 할당
            if ('A' <= ch && ch <= 'Z') {
                arr[i] = (char) (ch - 'A' + 'a');
            } else if ('a' <= ch && ch <= 'z') { // else 조건에 소문자 범위 검사 추가
                arr[i] = (char) (ch - 'a' + 'A');
            }
        }

        System.out.println(new String(arr)); // println으로 한번에 출력
    }
}