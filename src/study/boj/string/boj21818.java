package study.boj.string;
import java.util.*;

/**
 * 백준 21818번 - Do You Know Your ABCs?
 * <p>
 * 문제:
 * 서로 다른 양의 정수 A, B, C (A ≤ B ≤ C)가 있을 때,
 * 7개의 수 {A, B, C, A+B, B+C, C+A, A+B+C} 중 N개가 주어짐.
 * 주어진 N개의 수를 포함할 수 있는 올바른 (A, B, C) 조합의 개수를 구하는 문제.
 * <p>
 * 알고리즘: 브루트포스 (후보군 생성 후 검증)
 * <p>
 * 핵심 아이디어:
 * 1. 후보군 생성 (Candidates Generation):
 * - A, B, C는 주어진 수들 자체이거나, 주어진 수들의 차이로 만들어질 수 있음.
 * - 예: (A+B+C)와 (B+C)가 주어지면 두 수의 차이인 A를 알 수 있음.
 * - 따라서 입력값들(input)과 그들의 차이(diff)를 모두 후보군에 넣음.
 * <p>
 * 2. 3중 반복문 (Triple Loop):
 * - 후보군 중에서 3개의 수 (a, b, c)를 뽑음.
 * - 중복 허용, 순서를 고려하여 a ≤ b ≤ c 조건을 만족하도록 탐색.
 * <p>
 * 3. 검증 (Validation):
 * - 뽑은 a, b, c로 만들 수 있는 7개의 모든 합을 생성.
 * - 입력으로 주어진 모든 수가 이 7개의 합 안에 포함되는지 확인.
 * - 포함된다면 유효한 (A, B, C) 조합으로 카운트.
 * <p>
 * 시간복잡도: O(T * M^3)
 * - M은 후보군의 개수. N이 작으므로(보통 N<=7) 후보군 크기도 작아 충분히 수행 가능.
 * <p>
 * 공간복잡도: O(M)
 * - 후보군 저장을 위한 공간.
 */
public class boj21818 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt(); // 테스트 케이스 개수

        while (T-- > 0) {
            int N = sc.nextInt(); // 주어진 수의 개수
            int[] input = new int[N];

            Set<Integer> inputSet = new HashSet<>();
            for (int i = 0; i < N; i++) {
                input[i] = sc.nextInt();
                inputSet.add(input[i]);
            }

            // A, B, C가 될 수 있는 후보 숫자들 찾기
            Set<Integer> candidates = new HashSet<>();
            for (int x : input) {
                candidates.add(x); // 입력값 자체도 후보
                for (int y : input) {
                    if (x > y) {
                        candidates.add(x - y); // 입력값끼리의 차이도 후보 (예: A+B - B = A)
                    }
                }
            }

            // 정렬된 리스트로 변환 (순서대로 조합을 만들기 위해)
            List<Integer> candiList = new ArrayList<>(candidates);
            Collections.sort(candiList);

            int count = 0;
            int size = candiList.size();

            // 후보군 중에서 3개(a, b, c)를 선택하는 모든 경우의 수 탐색
            // i <= j <= k 조건을 통해 a <= b <= c 유지
            for (int i = 0; i < size; i++) {
                for (int j = i; j < size; j++) {
                    for (int k = j; k < size; k++) {
                        int a = candiList.get(i);
                        int b = candiList.get(j);
                        int c = candiList.get(k);

                        // A, B, C로 만들 수 있는 7개의 수 생성
                        Set<Integer> set = new HashSet<>();
                        set.add(a);
                        set.add(b);
                        set.add(c);
                        set.add(a + b);
                        set.add(a + c);
                        set.add(b + c);
                        set.add(a + b + c);

                        // 입력된 모든 숫자가 생성된 집합(7개의 수)에 포함되는지 확인
                        boolean flag = true;
                        for (int n : input) {
                            if (!set.contains(n)) {
                                flag = false;
                                break;
                            }
                        }

                        // 조건을 만족하면 카운트 증가
                        if (flag) {
                            count++;
                        }
                    }
                }
            }
            System.out.println(count);
        }
    }
}