package study.boj.string;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 백준 1339번 - 단어 수학
 *
 * 문제: 알파벳 대문자를 0부터 9까지의 숫자로 바꿔서 단어들의 합을 최대로 만들기
 *
 * 알고리즘: 그리디 알고리즘 + 정렬
 * - 각 알파벳이 전체 합에 기여하는 가중치를 계산
 * - 가중치가 큰 알파벳에 큰 숫자를 할당
 * - 자릿수에 따른 가중치를 반영하여 최대합 산출
 *
 * 핵심 아이디어:
 * 1. 각 알파벳이 나타나는 자릿수의 자리값(10^n)을 모두 더하여 가중치 계산
 *    예: ABC에서 A는 100의 자리 -> 가중치 100 추가
 * 2. 가중치가 높은 알파벳부터 9, 8, 7... 순으로 숫자 할당
 * 3. 각 알파벳의 가중치 × 할당된 숫자의 합이 최대값
 *
 * 예제:
 * GCF + ACDEB
 * - G: 100, C: 100+1000, F: 1, A: 10000, D: 100+10, E: 1, B: 1
 * - 정렬 후 큰 순서: A(10000), C(1100), D(110), G(100), ...
 * - A=9, C=8, D=7, G=6 등으로 할당하여 최대합 계산
 *
 * 시간복잡도: O(N × L + 26log26) ≈ O(N × L)
 * - N: 단어의 개수, L: 단어의 평균 길이
 * - 각 단어를 순회하며 가중치 계산: O(N × L)
 * - 알파벳 26개 정렬: O(26log26) = 상수
 *
 * 공간복잡도: O(1)
 * - 알파벳 26개의 가중치를 저장하는 고정 크기 배열
 *
 * 그리디 알고리즘이 최적해를 보장하는 이유:
 * - 가중치가 큰 알파벳에 큰 숫자를 할당할 때마다 전체 합이 최대로 증가
 * - 각 선택이 독립적이며 이전 선택을 번복할 필요가 없음
 */

public class boj1339 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] alpha = new int[26];

        for(int i = 0; i < N; i++) {
            String str = br.readLine();
            int length = str.length();

            for (int j=0; j<length; j++) {
                char ch = str.charAt(j);
                alpha[ch - 'A'] += (int) Math.pow(10, length - 1 - j); // A -> alpha[0]
            }
        }

        Arrays.sort(alpha);

        int mNum = 9;
        int idx = 25;
        int sum = 0;

        while (alpha[idx] > 0) {
            sum += alpha[idx] * mNum;
            idx--;
            mNum--;
        }

        System.out.println(sum);
    }
}
