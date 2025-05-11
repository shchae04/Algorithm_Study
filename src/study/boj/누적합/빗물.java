package study.boj.누적합;

import java.util.Scanner;

public class 빗물 {
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