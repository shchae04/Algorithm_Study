package study.boj.string;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 백준 17945번 - 통학의 신
 * 
 * 문제: 이차방정식 x² + 2Ax + B = 0의 정수해 구하기
 * 
 * 알고리즘: 완전탐색
 * - 근의 범위가 -1000 ≤ x ≤ 1000으로 제한되어 있음
 * - 모든 가능한 x값을 대입하여 방정식을 만족하는지 확인
 * - 근이 1개면 중근, 2개면 서로 다른 근
 * 
 * 시간복잡도: O(2001) → O(1)
 * 공간복잡도: O(1)
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
