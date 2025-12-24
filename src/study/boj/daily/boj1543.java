package study.boj.daily;

import java.util.Scanner;

/**
 * 백준 1543번 - 문서 검색
 *
 * 문제: 어떤 문서와 검색하려는 단어가 주어졌을 때, 그 단어가 문서에 중복되지 않게 최대 몇 번 등장하는지 구하는 문제
 *
 * 알고리즘: 그리디 알고리즘 (Greedy), 문자열 검색 (Brute Force String Search)
 * - 문서의 앞에서부터 검색 단어를 찾고, 찾으면 해당 단어 길이만큼 건너뛰어 중복을 방지함
 *
 * 핵심 아이디어:
 * 1. 문서의 인덱스 0부터 시작하여 검색 단어와 일치하는 부분 문자열이 있는지 확인
 * 2. 일치하는 경우:
 *    - 카운트(cnt) 증가
 *    - 검색 단어의 길이만큼 인덱스를 이동하여 겹치지 않게 처리
 *      (for문의 i++와 합쳐져서 다음 검색 위치가 올바르게 설정되도록 조정)
 * 3. 일치하지 않는 경우:
 *    - 인덱스를 1 증가시켜 다음 위치에서 확인
 *
 * 시간복잡도: O(N * M)
 * - N: 문서의 길이, M: 검색 단어의 길이
 * - 최악의 경우 각 위치마다 부분 문자열 생성 및 비교 수행
 * - N <= 2500, M <= 50이므로 충분히 시간 내에 통과 가능
 *
 * 공간복잡도: O(1)
 * - 추가적인 메모리 공간을 거의 사용하지 않음 (입력 문자열 제외)
 */

public class boj1543 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        String doc = s.nextLine();
        String str = s.nextLine();

        int cnt = 0;

        for (int i = 0; i < doc.length() - str.length() + 1; i++) {

            if (doc.substring(i, i + str.length()).equals(str)) {
                cnt++;
                i += str.length(); //for문을 빠져나갈때 i++되므로 +1 생략
                i--;
            } else continue;
        }


        System.out.println(cnt);
    }
}