package study.boj.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj16283 {
    public static void main(String[] args) throws IOException {
        /**
         * 양, 염소 각 1마리 이상,  양은 사료를 a 만큼 먹고, 염소는 b 만큼 먹는다.
         *  양과 염소의 전체 n 마리. 하루동안 소비한 전체 사료가 w. 양 염소?
         *  x, y >= 1 , x+y = n,  ax + by = w 일때. x와 y를 구해라
         *  1<=a<=1000, 1<=b<=1000, 2<=n<=1002, 2<=w<=1,000,000
         *  가짓수가 작아서 완탐 돌려도 되것다.
         */

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());

//        int count = 0;
//        int ansX = 0;
//        int ansY = 0;
//
//        for (int x = 1; x <= 1000; x++) {
//            int y = n - x;
//            if (y > 1000 || y < 1)
//                continue;
//            if (a * x + b * y == w) {
//                count++;
//                ansX = x;
//                ansY = y;
//            }
//        }
//
//        if (count == 1) {
//            System.out.println(ansX + " " + ansY);
//        } else {
//            System.out.println(-1);
//        }

        if (a == b) {
            // 양 염소가 동일하게 먹음 -> 해가 여러개거나 없음
            System.out.println(-1);
            return;
        }

        int numerator = w - b * n;
        int denominator = a - b;

        if (numerator % denominator != 0) {
            // 나누어 떨어지지 않는다면 해가 없음
            System.out.println(-1);
            return;
        }

        int x = numerator / denominator;
        int y = n - x;

        if (x < 1 || x > 1000 || y < 1 || y > 1000) {
            System.out.println(-1);
        } else {
            System.out.println(x + " " + y);
        }


    }

}
