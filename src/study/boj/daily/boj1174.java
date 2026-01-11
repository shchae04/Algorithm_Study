package study.boj.daily;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 백준 1174번 - 줄어드는 수
 *
 * 문제: N번째로 작은 '줄어드는 수'를 구하는 문제
 * - 줄어드는 수: 가장 높은 자리의 숫자부터 작은 자리의 숫자까지 순차적으로 감소하는 수 (예: 321, 950)
 * - 가장 작은 줄어드는 수는 0 (1번째)
 * - 만약 N번째 줄어드는 수가 없다면 -1 출력
 *
 * 알고리즘: 브루트포스(Brute Force) / 백트래킹(Backtracking)
 * - 가능한 모든 줄어드는 수의 개수가 매우 적음을 이용
 * - 최대 줄어드는 수는 9876543210 (10자리)
 * - 0~9까지 10개의 숫자를 조합(선택/미선택)하여 만들 수 있는 모든 경우의 수 = 2^10 = 1024개
 *
 * 핵심 아이디어:
 * 1. {9, 8, 7, 6, 5, 4, 3, 2, 1, 0} 배열을 사용
 * 2. 각 숫자를 '선택'하거나 '선택하지 않는' 방식으로 모든 부분집합을 생성 (DFS)
 *    - arr[idx]를 선택하면 현재 수의 뒤에 붙임 (num * 10 + arr[idx])
 *    - arr이 내림차순으로 정렬되어 있고, idx가 증가하며 순서대로 선택하므로
 *      자연스럽게 자릿수가 감소하는 형태가 됨
 * 3. 생성된 모든 수를 리스트에 저장하고 오름차순 정렬
 * 4. N번째 수(인덱스 N-1)를 출력, 존재하지 않으면 -1 출력
 *
 * 시간복잡도: O(1)
 * - 전체 경우의 수가 1024개로 고정되어 있어 상수 시간 내에 완료됨
 *
 * 공간복잡도: O(1)
 * - 최대 1024개의 Long 값을 저장하는 리스트 사용
 */
public class boj1174 {

    static int N;
    static int[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
    static List<Long> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        dfs(0, 0);
        list.sort(null);

        try {
            System.out.println(list.get(N - 1));
        } catch (Exception e) {
            System.out.println(-1);
        }

    }

    public static void dfs(long num, int idx) {
        if (!list.contains(num)) {
            list.add(num);
        }

        if (idx >= 10) {
            return;
        }

        dfs((num * 10 + arr[idx]), idx + 1);
        dfs(num, idx + 1);
    }
}
