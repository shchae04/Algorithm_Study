package study.boj.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj34362 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        double n  = Double.parseDouble(br.readLine());

        // 입력값 n에서 0.3을 차감한 결과 계산
        double result = n - 0.3;
        System.out.println(result);
    }
}
