package study.boj.numbertheory;
import java.util.Scanner;

/**
 * 백준 1978번 - 소수 찾기
 *
 * 문제: 주어진 수들 중 소수의 개수를 구하는 문제
 *
 * 알고리즘: 소수 판별(제곱근까지 나눗셈)
 * 시간복잡도: O(N * sqrt(M))
 * 공간복잡도: O(1)
 */

public class boj1978 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        int count = 0;
        for (int i : arr) {
            if (isPrime(i)) {
                count++;
            }
        }

        System.out.println(count);
    }

    private static boolean isPrime(int num) {
        if (num < 2) return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
}