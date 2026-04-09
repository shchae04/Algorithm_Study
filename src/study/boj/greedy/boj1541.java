package study.boj.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 백준 1541 - 잃어버린 괄호
 * 첫 번째 '-' 이후의 모든 수를 하나로 묶어 빼면 최솟값이 된다.
 */
public class boj1541 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String expression = br.readLine();

        String[] groups = expression.split("-");
        int answer = sumGroup(groups[0]);

        for (int i = 1; i < groups.length; i++) {
            answer -= sumGroup(groups[i]);
        }

        System.out.println(answer);
    }

    private static int sumGroup(String group) {
        String[] numbers = group.split("\\+");
        int sum = 0;

        for (String number : numbers) {
            sum += Integer.parseInt(number);
        }

        return sum;
    }
}
