package study.boj.daily;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * 백준 1235번 - 학생 번호
 * 
 * 문제: N개의 학생번호에서 뒤에서부터 k자리로 모든 학생을 구별할 수 있는 최소 k값 찾기
 * 
 * 알고리즘: 완전탐색 + 해시셋
 * - 뒤에서부터 1자리, 2자리, ... 순서대로 확인
 * - 각 길이에 대해 모든 학생번호의 접미사를 해시셋에 저장
 * - 중복이 없으면(모든 접미사가 고유하면) 그 길이가 답
 * 
 * 시간복잡도: O(N * L²) (N: 학생 수, L: 학생번호 최대 길이)
 * 공간복잡도: O(N)
 */
public class boj1235 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String[] arr = new String[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.next();
        }
        int size = arr[0].length();

        // 뒤에서 부터 k개씩 가져와서 다른지 보면 된다.
        // 12345 -> substring(size - k)
        for (int k = 1; k <= size; k++) {
            Set<String> unique = new HashSet<>();
            boolean allUnique = true;
            for (int i = 0; i < n; i++) {
                String suffix = arr[i].substring(size - k);
                if (unique.contains(suffix)) {
                    allUnique = false;
                    break;
                }
                unique.add(suffix);
            }
            if (allUnique) {
                System.out.println(k);
                return;
            }
        }

    }
}
