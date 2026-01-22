package study.boj.twopointer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 백준 22945번 - 팀 빌딩
 *
 * 문제: N명의 팀원이 있을 때, 두 명을 선택하여 팀을 만들 때 최대 팀 능력치를 구하는 문제
 * 팀 능력치 = min(선택한 두 팀원의 능력치) × (두 팀원 사이의 거리 - 1)
 *
 * 알고리즘: 투 포인터 (Two Pointers)
 * - 양 끝에서 시작하여 두 포인터를 이용해 최적해를 찾음
 * - 더 작은 능력치를 가진 쪽의 포인터를 안쪽으로 이동
 * - 거리가 멀수록, 최소 능력치가 클수록 팀 능력치가 높아짐
 * - 투 포인터를 사용하여 O(N) 시간에 해결
 *
 * 시간복잡도: O(N)
 * 공간복잡도: O(1)
 */
public class boj22945 {
    static int n;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n + 1];
        String[] str = br.readLine().split(" ");
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(str[i - 1]);
        }

        int result = calc();
        System.out.println(result);

    }

    private static int calc() {
        int left = 1;
        int right = n;
        int result = 0;

        while (left <= right) {
            int min = Math.min(arr[left], arr[right]);
            result = Math.max(min * (right - left - 1), result);
            if (arr[left] < arr[right]) {
                left++;
            } else {
                right--;
            }
        }
        return result;
    }
}
