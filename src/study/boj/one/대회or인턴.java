/*
백준 2875 - 대회 or 인턴 문제 :
여학생(n)과 남학생(m)으로 팀을 만들되, k명은 인턴십에 참여해야 함
- 팀당 여학생 2명, 남학생 1명 필요
- 최대한 많은 팀을 만들면서 k명의 인턴십 인원도 만족시켜야 함
*/
package study.boj.one;

import java.util.Scanner;

public class 대회or인턴 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();

        // 일단 최대한 많은 팀을 만들고
        int team = Math.min(n / 2, m);

        // 남은 인원에서 인턴쉽 인원 부족하면 팀에서 빼기
        int remain = (n - team * 2) + (m - team); // 남은 인원
        if (remain < k) { // 인턴쉽 인원이 부족하면
            int need = k - remain; // 추가로 필요한 인원
            team -= (need + 2) / 3; // 팀당 3명이므로 올림해서 나누기
        }

        System.out.println(team);
    }
}