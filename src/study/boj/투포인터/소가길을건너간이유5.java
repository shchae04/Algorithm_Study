package study.boj.투포인터;

import java.util.Scanner;

public class 소가길을건너간이유5 {

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
