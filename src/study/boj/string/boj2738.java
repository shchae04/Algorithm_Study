package study.boj.string;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 2738번 - 행렬 덧셈
 * 
 * 문제: 두 N×M 행렬 A와 B를 더한 결과 행렬 출력
 * 
 * 알고리즘: 단순 구현
 * - 두 행렬을 입력받아 같은 위치의 원소들을 더함
 * - A[i][j] + B[i][j]를 모든 위치에 대해 계산
 * 
 * 시간복잡도: O(N * M)
 * 공간복잡도: O(N * M)
 */
public class boj2738 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] A = new int[N][M];
        int[][] B = new int[N][M];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                B[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                System.out.print(A[i][j] + B[i][j] + " ");
            }
            System.out.println();
        }


    }
}