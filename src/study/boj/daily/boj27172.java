package study.boj.daily;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 27172번 - 수 나누기 게임
 *
 * 문제: N명의 플레이어가 각각 양의 정수 카드를 가지고 있을 때,
 * 자신의 수가 다른 사람의 수의 약수이면 점수가 -1, 배수이면 점수가 +1
 *
 * 알고리즘: 에라토스테네스의 체 응용
 * - 각 수에 대해 그 배수들을 확인하는 방식
 * - selected 배열로 입력된 수들을 빠르게 찾기 (O(1) 조회)
 * - 각 수의 배수들을 순회하며 점수 계산
 *
 * 핵심 아이디어:
 * 1. selected 배열에 각 수가 몇 번째 플레이어의 수인지 저장 (1-based 인덱스)
 * 2. 각 수(start)에 대해 배수들(start*2, start*3, ...)을 확인
 * 3. 배수가 다른 플레이어의 수라면:
 *    - 배수를 가진 플레이어: 점수 -1 (자신의 수가 약수이므로)
 *    - 현재 수를 가진 플레이어: 점수 +1 (자신의 수가 배수이므로)
 *
 * 시간복잡도: O(N * log(MAX))
 * - N개의 수 각각에 대해 배수들을 확인
 * - 배수의 개수는 조화급수의 합으로 O(log(MAX))에 수렴
 * - MAX/1 + MAX/2 + MAX/3 + ... ≈ O(MAX * log(MAX))
 * - 실제로는 N개의 수에 대해서만 수행하므로 O(N * MAX/N * log(MAX)) = O(MAX * log(MAX))
 *
 * 공간복잡도: O(MAX) = O(1,000,001)
 * - selected 배열이 가장 큰 공간 사용
 * - 카드 숫자의 범위가 1~1,000,000이므로 크기 1,000,001 배열 필요
 *
 * 에라토스테네스의 체와 유사한 방식으로 배수를 체크하는 기법 활용
 */

public class boj27172 {

    static int N;
    static int[] arr;
    static int[] selected;
    static int[] answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        arr = new int[N];
        selected = new int[1000001];
        answer = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            selected[arr[i]] = i + 1;
        }

        for (int i=0; i<N; i++) {
            int start = arr[i];
            for (int j = start * 2; j < 1000001; j += start) {
                if (selected[j] > 0) {
                    answer[selected[j]] = answer[selected[j]] - 1;
                    answer[selected[start]] = answer[selected[start]] + 1;
                }
            }
        }

        for (int i=1; i<=N; i++) {
            System.out.print(answer[i] + " ");
        }
    }
}
