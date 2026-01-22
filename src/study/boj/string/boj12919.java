package study.boj.string;
import java.util.Scanner;

public class boj12919 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String S = sc.nextLine().trim();
        String T = sc.nextLine().trim();

        System.out.println(solve(S, T) ? 1 : 0);
    }

    public static boolean solve(String S, String T) {
        return canMake(S, T);
    }

    public static boolean canMake(String S, String T) {
        // T에서 S로 역추적
        if (S.length() == T.length()) {
            return S.equals(T);
        }

        if (S.length() > T.length()) {
            return false;
        }

        // T의 마지막 문자가 'A'인 경우: 연산 1의 역 (A 제거)
        if (T.charAt(T.length() - 1) == 'A') {
            String newT = T.substring(0, T.length() - 1);
            if (canMake(S, newT)) {
                return true;
            }
        }

        // T의 첫 문자가 'B'인 경우: 연산 2의 역 (뒤집고 B 제거)
        if (T.charAt(0) == 'B') {
            String reversed = new StringBuilder(T).reverse().toString();
            String newT = reversed.substring(0, reversed.length() - 1);
            if (canMake(S, newT)) {
                return true;
            }
        }

        return false;
    }
}