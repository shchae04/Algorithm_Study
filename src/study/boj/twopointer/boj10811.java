package study.boj.twopointer;
import java.util.Scanner;

/**
 * 백준 10811번 - 바구니 뒤집기
 * 
 * 문제: 바구니의 순서를 뒤집는 연산을 M번 수행하여 최종 배열 상태 구하기
 * 
 * 알고리즘: 배열 뒤집기 (Two Pointer)
 * - 각 연산마다 지정된 구간 [i, j]를 뒤집기
 * - 왼쪽 포인터와 오른쪽 포인터를 이용하여 swap하며 중앙으로 이동
 * - M번의 뒤집기 연산 후 최종 배열 출력
 * 
 * 시간복잡도: O(M * N) (M번의 연산, 각 연산당 최대 N개 원소)
 * 공간복잡도: O(N)
 */
public class boj10811 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();

        int[] arr = new int[N];
        for (int i=0; i<N; i++) {
            arr[i] = i + 1;
        }

        for (int i=0; i<M; i++) {
            int left = sc.nextInt() - 1;
            int right = sc.nextInt() - 1;

            while (left < right) {
                int temp = arr[left];
                arr[left++] = arr[right];
                arr[right--] = temp;
            }
        }
        for (int i=0; i<N; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
