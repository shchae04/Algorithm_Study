package study.boj.투포인터;

import java.util.Arrays;
import java.util.Scanner;

public class 일곱난쟁이 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] dwarfs = new int[9];
        int totalSum = 0;

        for (int i = 0; i < 9; i++) {
            dwarfs[i] = sc.nextInt();
            totalSum += dwarfs[i];
        }

        Arrays.sort(dwarfs);

        int targetSum = totalSum - 100;
        int s=0, e=8;

        int remove1 = -1, remove2 = -1;
        while(s < e) {
            int sum = dwarfs[s] + dwarfs[e];
            if(sum == targetSum) {
                remove1 = s;
                remove2 = e;
                break;
            } else if(sum < targetSum) {
                s++;
            } else {
                e--;
            }
        }
        for (int i=0; i<9; i++) {
        if (i != remove1 && i != remove2)
            System.out.println(dwarfs[i]);
        }
    }
}
