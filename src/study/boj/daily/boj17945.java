package study.boj.daily;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 통학의신
 */
public class boj17945 {
    public static void main(String[] args) throws IOException {
        //x2  + 2Ax + B = 0 의 두 계수 A, B가 주어진다.
        //A, B는 정수이며, 이 방정식의 근은 항상 정수이다. (-1000 ≤ A, B ≤ 1000)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

//        int x1 = (int) Math.sqrt(-1 * b / (2 * a));
//        int x2 = (int) Math.sqrt(-1 * (b - a * x1 * x1) / (2 * a));

        int[] result = new int[2];
        int count = 0;
        for(int x = -1000; x <= 1000; x++) {
            if(x * x + 2 * a * x + b == 0) {
                result[count++] = x;
            }
        }
        if (count == 1) {
            System.out.println(result[0]);
        } else {
            Arrays.sort(result);
            System.out.println(result[0] + " " + result[1]);
        }
    }
}
