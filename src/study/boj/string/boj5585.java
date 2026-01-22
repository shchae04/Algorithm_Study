package study.boj.string;
import java.io.*;

/**
 * 백준 5585번 - 거스름돈
 * 
 * 문제: 1000원에서 물건 가격을 뺀 거스름돈을 최소 개수의 동전으로 돌려주는 문제
 * 사용 가능한 동전: 500원, 100원, 50원, 10원, 5원, 1원
 * 
 * 알고리즘: 그리디 알고리즘
 * - 가장 큰 단위의 동전부터 사용 (500원 → 1원)
 * - 각 단위에서 가능한 만큼 사용하고 나머지를 다음 단위로 전달
 * - 모든 동전이 서로의 배수 관계이미로 그리디로 최소 해 도출
 * 
 * 시간복잡도: O(1) - 동전 종류가 6개로 고정
 * 공간복잡도: O(1)
 */
public class boj5585 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());

        int remain = 1000 - N;

        int[] money = {500, 100, 50, 10, 5, 1};
        int count = 0;

        for (int i=0; i<money.length; i++) {
            count += remain / money[i];
            remain = remain % money[i];
        }

        bw.write(String.valueOf(count));
        bw.flush();
        bw.close();
        br.close();
    }
}
