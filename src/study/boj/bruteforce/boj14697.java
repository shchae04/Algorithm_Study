package study.boj.bruteforce;
import java.util.Scanner;

public class boj14697 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt(); // 방 정원 A
        int b = sc.nextInt(); // 방 정원 B
        int c = sc.nextInt(); // 방 정원 C
        int n = sc.nextInt(); // 전체 학생 수

        // 모든 가능한 조합 탐색
        for (int i = 0; i <= n / a; i++) {
            for (int j = 0; j <= n / b; j++) {
                for (int k = 0; k <= n / c; k++) {
                    if (a * i + b * j + c * k == n) {
                        System.out.println(1);
                        return;
                    }
                }
            }
        }

        System.out.println(0);
    }
}