package study.boj.daily;

import java.util.Scanner;

/**
 * 백준 2475번 - 검증수
 * 
 * 문제: 5개 고유번호 각각을 제곱한 수의 합을 10으로 나눈 나머지 구하기
 * 
 * 알고리즘: 단순 구현
 * - 5개 숫자를 입력받아 각각 제곱하여 합계 구하기
 * - 합계를 10으로 나눈 나머지 출력
 * 
 * 시간복잡도: O(1)
 * 공간복잡도: O(1)
 */
public class boj2475 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int preSum = 0;

        for (int i = 0; i < 5; i++) {
            int t = sc.nextInt();
            preSum += t * t;
        }

        System.out.println(preSum % 10);


    }
}
