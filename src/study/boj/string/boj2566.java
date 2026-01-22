package study.boj.string;
import java.util.Scanner;

/**
 * 백준 2566번 - 최댓값
 * 
 * 문제: 9×9 격자에서 최댓값과 그 위치(행, 열) 찾기
 * 
 * 알고리즘: 선형 탐색
 * - 9×9 격자를 순차적으로 탐색하며 최댓값과 위치 추적
 * - Math.max()를 사용하여 최댓값 갱신
 * - 최댓값이 갱신될 때마다 해당 위치 저장
 * 
 * 시간복잡도: O(1) (고정 크기 9×9 = 81)
 * 공간복잡도: O(1) (고정 크기 배열)
 */
public class boj2566 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int[][] arr = new int[9][9];

        int max = 0;
        int maxI = 0;
        int maxJ = 0;
        for(int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                arr[i][j] = sc.nextInt();
                max = Math.max(arr[i][j], max);
                if(arr[i][j] == max) {
                    maxI = i;
                    maxJ = j;
                }
            }
        }
        System.out.println(max);
        System.out.println((maxI + 1) + " " + (maxJ + 1));
    }
}
