package study.boj.daily;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 윷놀이
 */
public class boj2490 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<String> answer = new ArrayList<>();

        Integer[][] values = new Integer[3][4];

        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < values[i].length; j++) {
                values[i][j] = sc.nextInt();
            }
        }

        for (int i = 0; i < values.length; i++) {
            int count = 0;
            for (int j = 0; j < values[i].length; j++) {
                if (values[i][j] == 0) {
                    count++;
                }
            }

            switch (count) {
                case 1:
                    answer.add("A");
                    break;
                case 2:
                    answer.add("B");
                    break;
                case 3:
                    answer.add("C");
                    break;
                case 4:
                    answer.add("D");
                    break;
                case 0:
                    answer.add("E");
            }
        }
        for (int i=0; i<answer.size(); i++) {
            System.out.println(answer.get(i));
        }
    }
}
