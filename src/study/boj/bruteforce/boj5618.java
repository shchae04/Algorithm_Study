package study.boj.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 백준 5618번 - 공약수
 *
 * 입력으로 주어진 2개 또는 3개의 자연수의 최대공약수를 구한 뒤,
 * 그 최대공약수의 약수를 모두 오름차순으로 출력한다.
 */
public class boj5618 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] numbers = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        int gcdValue = numbers[0];
        for (int i = 1; i < n; i++) {
            gcdValue = gcd(gcdValue, numbers[i]);
        }

        StringBuilder answer = new StringBuilder();
        List<Integer> largerDivisors = new ArrayList<>();

        for (int divisor = 1; divisor * divisor <= gcdValue; divisor++) {
            if (gcdValue % divisor == 0) {
                answer.append(divisor).append('\n');

                int pairDivisor = gcdValue / divisor;
                if (pairDivisor != divisor) {
                    largerDivisors.add(pairDivisor);
                }
            }
        }

        for (int i = largerDivisors.size() - 1; i >= 0; i--) {
            answer.append(largerDivisors.get(i)).append('\n');
        }

        System.out.print(answer);
    }

    private static int gcd(int a, int b) {
        while (b != 0) {
            int remainder = a % b;
            a = b;
            b = remainder;
        }
        return a;
    }
}
