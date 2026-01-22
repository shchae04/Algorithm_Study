package study.boj.string;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 백준 1475번 - 방 번호
 * 
 * 문제: 0~9의 숫자 스티커 세트를 사용해 방 번호를 만들 때 필요한 최소 세트 수
 * 6과 9는 뒤집어서 사용 가능 (한 세트에 6과 9가 각각 1개씩)
 * 
 * 알고리즘: 빈도 카운팅 + 특수 처리
 * - 각 숫자의 출현 빈도를 카운트
 * - 6과 9는 동일하게 처리: 9가 나오면 6에 카운트
 * - 6과 9의 총 사용량을 2로 나눠 올림 처리 (한 세트에 각각 1개씩)
 * - 모든 숫자 중 최댓값이 필요한 세트 수
 * 
 * 시간복잡도: O(L) - L: 방 번호의 자릿수
 * 공간복잡도: O(1)
 */
public class boj1475 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();

        int[] arr = new int[10];

        for (char c : str.toCharArray()) {
            int num = c - '0';

            if (num == 9) {
                num = 6;
            }

            arr[num]++;
        }

        arr[6] = arr[6] / 2 + arr[6] % 2;

        Arrays.sort(arr);

        System.out.println(arr[9]);
    }
}
