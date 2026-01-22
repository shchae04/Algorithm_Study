package study.boj.string;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 13300번 - 방 배정
 * 
 * 문제: 수학여행에서 학생들을 성별과 학년을 고려하여 방에 배정할 때 필요한 최소 방 개수
 * 한 방에 최대 K명까진 배정 가능, 같은 성별과 같은 학년만 배정 가능
 * 
 * 알고리즘: 그룹별 카운팅 + 수학
 * - 2D 배열로 성별(2개) x 학년(1~6학년) 학생 수 저장
 * - 각 그룹별로 학생 수를 K로 나눠 올림 처리하여 방 수 계산
 * - 예: 5명이고 K=3이면 2개 방 필요 (3명, 2명)
 * - 모든 그룹의 필요 방 수를 합산
 * 
 * 시간복잡도: O(N) - N: 학생 수
 * 공간복잡도: O(1) - 고정 크기 2x7 배열
 */
public class boj13300 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] arr = new int[2][7];
        int count = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());

            arr[S][Y]++;
        }

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j <= 6; j++) {
                if (arr[i][j] == 0) {
                    continue;
                }
                if (arr[i][j] % K != 0) {
                    count++;
                }
                count += arr[i][j] / K;
            }
        }
        System.out.println(count);
    }
}
