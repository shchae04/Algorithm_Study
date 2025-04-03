package study.boj.two;

import java.util.Scanner;

public class 암호키 {

    static final int MAX = 1000000;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine(); // 버퍼 제거용 (줄바꿈 처리)

        for (int i = 0; i < n; i++) {
            String s = sc.nextLine().trim(); // 큰 수 입력을 문자열로 처리
            boolean isValid = true;

            for (int j = 2; j <= MAX; j++) {
                if (isPrime(j)) {
                    if (isDivisible(s, j)) {
                        isValid = false;
                        break;
                    }
                }
            }

            System.out.println(isValid ? "YES" : "NO");
        }

        sc.close();
    }

    // 문자열로 된 수 s가 정수 p로 나누어떨어지는지 확인
    public static boolean isDivisible(String s, int p) {
        long rem = 0;
        for (int i = 0; i < s.length(); i++) {
            rem = (rem * 10 + (s.charAt(i) - '0')) % p;
        }
        return rem == 0;
    }

    // 소수 판별 (완전 탐색 방식)
    public static boolean isPrime(int num) {
        if (num < 2) return false;
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
}