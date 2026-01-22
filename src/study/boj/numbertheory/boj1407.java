package study.boj.numbertheory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * 백준 1407번 - 2로 몇 번 나누어질까
 *
 * 문제: 구간 [A, B]에서 각 수를 2로 나눌 수 있는 횟수의 합을 구하는 문제
 *
 * 알고리즘: 수학적 누적 계산
 * 시간복잡도: O(log N)
 * 공간복잡도: O(1)
 */

public class boj1407 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());

        System.out.println(howManyTwo(b) - howManyTwo(a-1));
    }

    private static long howManyTwo(long n) {
        long result = 0;
        long divisor = 1;  //2^0
        long beforeDivisor = 0; //이전의 divisor 담아두기


        while(true){
            long val = n / divisor;
            // 추가분만 더 해줌
            if(val > 0)result += val * (divisor - beforeDivisor);
            else break;
            beforeDivisor = divisor;    //이전 divisor
            divisor *= 2; //2의 제곱수
        }


        return result;
    }
}
