package study.boj.string;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 백준 2490번 - 윷놀이
 * 
 * 문제: 3번의 윷놀이 결과를 입력받아 각각에 대해 결과를 출력하는 문제
 * 0(등)과 1(별)의 수에 따라 다른 결과
 * - 0이 1개: 도(도), A
 * - 0이 2개: 개(개), B  
 * - 0이 3개: 걸(걸), C
 * - 0이 4개: 윷(윷), D
 * - 0이 0개: 모(모), E
 * 
 * 알고리즘: 단순 조건문 처리
 * - 각 회에 대해 0의 개수를 세어서 switch문으로 결과 결정
 * 
 * 시간복잡도: O(1)
 * 공간복잡도: O(1)
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
