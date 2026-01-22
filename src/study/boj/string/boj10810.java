package study.boj.string;
import java.util.Scanner;

/**
 * 백준 10810번 - 공 넣기
 * 
 * 문제: N개의 비어있는 바구니에 M번의 공 넣기 연산을 수행
 * 각 연산은 i번부터 j번 바구니에 k번 공을 넣는다
 * 
 * 알고리즘: 배열 조작 시뮬레이션
 * - N개 크기의 배열을 생성 (초기값 모두 0)
 * - M번의 연산에 대해:
 *   - i번부터 j번 바구니 범위에 k번 공을 넣기
 *   - 인덱스는 1부터 시작하므로 배열 접근시 -1 처리
 * - 최종 배열 상태 출력
 * 
 * 시간복잡도: O(M * K) - M: 연산 횟수, K: 평균 범위 크기
 * 공간복잡도: O(N)
 */
public class boj10810 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int [] arr = new int[N];

        for (int i=0; i<M; i++) {
            int I = sc.nextInt();
            int J = sc.nextInt();
            int K = sc.nextInt();

            for (int j=I - 1; j<J; j++) {
                arr[j] = K;
            }
        }

        for (int k=0; k<arr.length; k++) {
            System.out.print(arr[k] + " ");
        }

    }
}
