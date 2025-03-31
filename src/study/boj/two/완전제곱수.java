package study.boj.two;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 완전제곱수 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int count = 0;
        for(int A=1; A<= 500; A++){
            for(int B=1; B<= 500; B++){
                if (A >= B) {
                    if (A * A - B * B == N) {
                        count++;
                    }
                }
            }
        }
        System.out.println(count);

    }
}
