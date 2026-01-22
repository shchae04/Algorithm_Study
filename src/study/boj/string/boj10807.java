package study.boj.string;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * 백준 10807번 - 개수 세기
 * 
 * 문제: N개의 정수가 주어졌을 때, 정수 V가 목 번 나오는지 구하는 문제
 * 
 * 알고리즘: 선형 탐색
 * - N개의 정수를 순차적으로 입력받으며 타깃 값 V와 비교
 * - 동일한 값이 나오면 카운터 증가
 * - 모든 입력을 처리한 후 최종 카운트 출력
 * 
 * 시간복잡도: O(N)
 * 공간복잡도: O(1)
 */
public class boj10807 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(br.readLine());

        int count = 0;

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (num == V) {
                count++;
            }
        }

        System.out.println(count);

        br.close();
    }
}
