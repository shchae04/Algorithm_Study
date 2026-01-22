package study.boj.twopointer;
import java.util.Scanner;

/**
 * 백준 14465번 - 소가 길을 건너간 이유 5
 *
 * 문제: 연속 K개의 신호등이 모두 켜지도록 고칠 최소 개수를 구하는 문제
 *
 * 알고리즘: 슬라이딩 윈도우
 * 시간복잡도: O(N)
 * 공간복잡도: O(N)
 */

public class boj14465 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        boolean[] broken = new boolean[n + 1];

        int k = sc.nextInt();
        int b = sc.nextInt();

        for(int i=0; i<b; i++) {
            int xx = sc.nextInt();
            broken[xx] = true;
        }

        // o o x o x o o 인경우 4개가 연속되게 하려면 1개만 고치면 된다.

        int count = 0;
        int s = 1;
        int e = 1;
        int min = Integer.MAX_VALUE;

        while (e <= n) {
            if (e-s + 1 <= k) {
                if (broken[e]) count++;

                if (e-s + 1 == k) {
                    min = Math.min(min, count);
                }
                e++;
            } else { //e-s+1 >k
                if (broken[s]) count--;
                s++;
            }
        }

        System.out.println(min);

    }
}
