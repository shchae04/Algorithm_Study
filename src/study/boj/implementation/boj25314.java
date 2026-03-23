package study.boj.implementation;

import java.util.Scanner;

/**
 * 백준 25314: 코딩은 체육과목 입니다
 * N바이트 정수를 표현하기 위해 필요한 자료형 출력
 * 4바이트마다 "long"을 하나씩 추가
 */
public class boj25314 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();  // N바이트 입력
        sc.close();

        int loop = n / 4;  // 4바이트당 "long" 1개
        StringBuilder sb = new StringBuilder();

        // "long"을 필요한 만큼 추가
        for (int i = 0; i < loop; i++) {
            sb.append("long ");
        }
        sb.append("int");  // 마지막에 "int" 추가

        System.out.println(sb);
    }
}
