package study.boj.누적합;

import java.util.Scanner;


public class HooFPaperScissors {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        char[] fj = new char[N];
        for (int i = 0; i < N; i++) {
            fj[i] = sc.next().charAt(0);
        }

        int[] h = new int[N+1]; // H > S
        int[] p = new int[N+1]; // P > H
        int[] s = new int[N+1]; // S > P

        for (int i = 0; i < N; i++) {
            h[i+1] = h[i] + (fj[i] == 'S' ? 1 : 0);
            p[i+1] = p[i] + (fj[i] == 'H' ? 1 : 0);
            s[i+1] = s[i] + (fj[i] == 'P' ? 1 : 0);
        }

        int max = 0;
        for (int i = 0; i <= N; i++) {
            max = Math.max(max, h[i] + Math.max(p[N] - p[i], s[N] - s[i]));
            max = Math.max(max, p[i] + Math.max(h[N] - h[i], s[N] - s[i]));
            max = Math.max(max, s[i] + Math.max(h[N] - h[i], p[N] - p[i]));
        }

        System.out.println(max);
    }
}