package study.boj.implementation;
import java.util.Scanner;

/**
 * 백준 2875번: 대회 or 인턴
 * 
 * 문제 설명:
 * 남학생 N명, 여학생 M명이 있고, K명이 인턴십으로 대회에 참여하지 못한다.
 * 대회는 남학생 2명, 여학생 1명으로 한 팀을 구성하며,
 * 최대 몇 팀을 만들 수 있는지 구하는 문제
 * 
 * 사용된 알고리즘:
 * - 완전탐색 (Brute Force)
 * - 그리디 (Greedy)
 * 
 * 핵심 아이디어:
 * 1. t개의 팀을 만들면 남학생 2t명, 여학생 t명 필요
 * 2. 남은 학생 수가 K명 이상이어야 인턴십 보내기 가능
 * 3. 가능한 모든 팀 수를 시도하여 최대값 찾기
 * 
 * 시간복잡도: O(min(N/2, M))
 * 공간복잡도: O(1)
 * 
 * Algorithm Classification: Brute Force, Greedy, Mathematics
 */
public class boj2875 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); //남
        int m = sc.nextInt(); //여
        int k = sc.nextInt(); //k명 인턴쉽으로 대회에 못나가는 인원.

        // 6 3 2
        // 2 - 1 팀이 2개, 2명이 인턴쉽.

        int count = 0; // 팀 수
        for (int t = 0; t <= Math.min(n / 2, m); t++) {
            int girl = n - 2 * t;
            int men = m - t;

            if (girl + men >= k) {
                count = t;
            }
        }
        System.out.println(count);

    }
}
