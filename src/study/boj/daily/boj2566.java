package study.boj.daily;

import java.util.Scanner;

public class boj2566 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int[][] arr = new int[9][9];

        int max = 0;
        int maxI = 0;
        int maxJ = 0;
        for(int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                arr[i][j] = sc.nextInt();
                max = Math.max(arr[i][j], max);
                if(arr[i][j] == max) {
                    maxI = i;
                    maxJ = j;
                }
            }
        }
        System.out.println(max);
        System.out.println((maxI + 1) + " " + (maxJ + 1));
    }
}
