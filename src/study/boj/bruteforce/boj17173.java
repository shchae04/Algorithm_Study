package study.boj.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

/**
 * 백준 17173번 - 배수들의 합
 *
 * 문제:
 * 1부터 N까지의 수 중에서, 주어진 K개의 수 중 하나 이상의 배수인 수들을 모두 더한다.
 *
 * 알고리즘:
 * 완전 탐색 + HashSet
 * - 각 수의 배수를 N 이하까지 모두 탐색한다.
 * - 여러 수의 공배수는 중복해서 더하면 안 되므로 HashSet에 저장한다.
 * - 마지막에 HashSet에 담긴 값들의 합을 구한다.
 *
 * 시간복잡도:
 * 각 수 x에 대해 N / x개의 배수를 확인하므로 O(Σ(N / x))
 *
 * 공간복잡도: O(N)
 */
public class boj17173 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] strA = br.readLine().split(" ");
        int numA = Integer.parseInt(strA[0]);

        String[] strB = br.readLine().split(" ");
        HashSet<Integer> set = new HashSet<>();

        for (int i = 0; i < strB.length; i++) {
            int numB = Integer.parseInt(strB[i]);

            // numB의 배수를 numA 이하까지 모두 집합에 저장한다.
            for (int j = 1; j * numB <= numA; j++) {
                set.add(numB * j);
            }
        }

        int answer = 0;
        for (Integer number : set) {
            answer += number;
        }

        System.out.println(answer);
    }
}
