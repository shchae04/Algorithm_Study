package study.boj.string;
import java.util.Scanner;

/**
 * 백준 17618번 - 신기한 수
 *
 * 문제: 자연수 N이 주어졌을 때, 1부터 N까지의 자연수 중에서 '신기한 수'의 개수를 구하는 프로그램
 *      (신기한 수: 양의 정수 x가 x의 각 자릿수의 합으로 나누어 떨어지는 수)
 *
 * 알고리즘: 브루트포스(Brute Force), 구현
 * - 1부터 N까지 모든 수에 대해 신기한 수인지 확인
 *
 * 핵심 아이디어:
 * 1. 각 자릿수의 합 구하기: 10으로 나눈 나머지를 더하고 몫을 갱신하며 반복
 * 2. 나누어 떨어지는지 확인: (원래 수) % (자릿수의 합) == 0 인지 검사
 *
 * 시간복잡도: O(N * log N)
 * - 1부터 N까지 반복 (N)
 * - 각 수의 자릿수 합 구하기: 자릿수 개수만큼 반복 (log_10 N)
 *
 * 공간복잡도: O(1)
 * - 별도의 배열이나 자료구조를 사용하지 않고 정수형 변수 몇 개만 사용
 */
public class boj17618 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // N이 매우 클 경우를 대비해 int 범위를 확인하세요. (최대 21억)
        if (!sc.hasNextInt()) return;
        int N = sc.nextInt();

        int ans = 0;
        for (int i = 1; i <= N; i++) {
            if (isHarshad(i)) {
                ans++;
            }
        }

        System.out.println(ans);
        sc.close();
    }

    // 하샤드 수 여부를 판별하는 함수
    public static boolean isHarshad(int n) {
        int sum = 0;
        int temp = n;

        // 숫자를 문자열로 바꾸지 않고 각 자릿수의 합 구하기
        while (temp > 0) {
            sum += temp % 10; // 마지막 자릿수를 더함
            temp /= 10;       // 마지막 자릿수를 제거
        }

        // 원래의 수가 자릿수 합으로 나누어떨어지는지 확인
        return n % sum == 0;
    }
}