package study.boj.one;

import java.util.Scanner;

public class 암호키 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextLong();
        }

        for (int i = 0; i < n; i++) {
            if (valid(arr[i])) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }

        //주어진 수 10^18 -> 하지만 10^6 이하의 소인수가 있는지만 확인하면 됨. 2~10^6

    }

    private static boolean valid(long num) {

        long limit = 1000000;

        for (long i =2; i <= limit&& i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }

        if (num == 1) return false;

        return true;
    }
}
