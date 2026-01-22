package study.boj.string;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 백준 18310번 - 안테나
 * 
 * 문제: N개의 집이 수직선 위에 있을 때, 모든 집과의 거리 합이 최소가 되는 안테나 위치를 구하는 문제
 * 
 * 알고리즘: 수학적 최적화 (중앙값 활용)
 * - 모든 집과의 거리 합이 최소가 되는 점은 중앙값(median)
 * - 집의 개수가 홀수일 때: 정 가운데 위치 
 * - 집의 개수가 짝수일 때: 가운데 두 수 중 작은 값 (여러 개 있을 때 가장 작은 값을 출력하기 위함)
 * - 정렬 후 인덱스를 통해 중앙값을 구함
 * 
 * 시간복잡도: O(N log N) - 정렬
 * 공간복잡도: O(1)
 */
public class boj18310 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int idx;

        if(n % 2 == 0) {
            idx = (n - 1) / 2;
        } else {
            idx = n / 2;
        }

        System.out.println(arr[idx]);
    }
}
