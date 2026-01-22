package study.boj.prefixsum;
import java.util.Scanner;

/**
 * 백준 14719번 - 빗물
 *
 * 문제: 블록 사이에 고이는 빗물의 총량을 구하는 문제
 *
 * 알고리즘: 높이 기준 스캔
 * 시간복잡도: O(H * W)
 * 공간복잡도: O(1)
 */

public class boj14719 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int H = sc.nextInt();
        int W = sc.nextInt();
        int[] blocks = new int[W];

        int maxH = 0;
        for (int i = 0; i < W; i++) {
            blocks[i] = sc.nextInt();
            maxH = Math.max(maxH, blocks[i]);
        }

        int total = 0;

        for (int h = 1; h <= maxH; h++) {
            boolean started = false;
            int temp = 0;

            for (int i = 0; i < W; i++) {
                if (blocks[i] >= h) {
                    if (started) total += temp;
                    temp = 0;
                    started = true;
                } else {
                    if (started) {
                        temp++;
                    }
                }
            }
        }

        System.out.println(total);
    }
}