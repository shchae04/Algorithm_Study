package study.boj.string;
import java.util.Scanner;

/**
 * 백준 3052번 - 나머지
 * 
 * 문제: 10개의 수를 입력받아 각각을 42로 나눋 나머지의 서로 다른 개수를 구하는 문제
 * 
 * 알고리즘: 빈도 카운팅
 * - 42개 크기의 배열을 만들어 나머지 빈도 기록
 * - 각 입력 수를 42로 나눋 나머지를 인덱스로 사용하여 카운트
 * - 최종적으로 0보다 큰 값을 가진 인덱스의 개수가 정답
 * 
 * 시간복잡도: O(1) - 입력이 10개로 고정
 * 공간복잡도: O(1) - 배열 크기가 42로 고정
 */
public class boj3052 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arr = new int[42];
        for (int i = 0; i < 10; i++) {
            int n = sc.nextInt();
            int remainder = n % 42;
            arr[remainder]++;
        }
        int distinct = 0;
        for (int i = 0; i < 42; i++) {
            if (arr[i] > 0) distinct++;
        }
        System.out.println(distinct);
        sc.close();
    }
}