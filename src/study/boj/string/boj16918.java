package study.boj.string;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 백준 16918번 - 봄버맨
 * 
 * 문제: 봄버맨이 폭탄을 설치하고 폭발시키는 시뮬레이션에서 N초 후 상태 구하기
 * 
 * 알고리즘: 시뮬레이션 + 패턴 분석 + 비트마스크 최적화
 * - 1초: 초기 상태 유지
 * - 2초: 모든 빈 자리에 폭탄 설치 (대칭적 패턴)
 * - 3초 이후: 주기적 패턴 (N % 4로 판별)
 * - 비트마스크로 폭발 영역 표시하여 메모리 최적화
 * 
 * 시간복잡도: O(R × C)
 * 공간복잡도: O(R × C)
 */
public class boj16918 {
    private static final int[] DR = {0, 0, 1, -1};
    private static final int[] DC = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        int R = Integer.parseInt(input[0]);
        int C = Integer.parseInt(input[1]);
        int N = Integer.parseInt(input[2]);

        char[][] grid = new char[R][];
        for (int i = 0; i < R; i++) {
            grid[i] = br.readLine().toCharArray();
        }

        // N이 1 이하면 초기 상태 출력
        if (N <= 1) {
            printGrid(grid);
            return;
        }

        // N이 짝수면 모든 칸이 폭탄
        if ((N & 1) == 0) {
            printFullGrid(R, C);
            return;
        }

        // 홀수인 경우 패턴 분석: 3초 후와 5초 후가 반복
        char[][] state3 = explodeGrid(grid, R, C);

        if ((N & 3) == 3) {  // N % 4 == 3
            printGrid(state3);
        } else {  // N % 4 == 1
            printGrid(explodeGrid(state3, R, C));
        }
    }

    private static char[][] explodeGrid(char[][] original, int R, int C) {
        // 폭발 후 상태를 비트마스크로 관리
        long[] exploded = new long[(R * C + 63) / 64];

        // 폭발 위치 마킹
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (original[i][j] == 'O') {
                    markExploded(exploded, i, j, R, C);
                }
            }
        }

        // 결과 그리드 생성
        char[][] result = new char[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                int pos = i * C + j;
                int wordIdx = pos / 64;
                int bitIdx = pos % 64;
                result[i][j] = (exploded[wordIdx] & (1L << bitIdx)) != 0 ? '.' : 'O';
            }
        }

        return result;
    }

    private static void markExploded(long[] exploded, int row, int col, int R, int C) {
        // 중심 폭발
        setBit(exploded, row * C + col);

        // 4방향 폭발
        for (int d = 0; d < 4; d++) {
            int nr = row + DR[d];
            int nc = col + DC[d];
            if (nr >= 0 && nr < R && nc >= 0 && nc < C) {
                setBit(exploded, nr * C + nc);
            }
        }
    }

    private static void setBit(long[] arr, int pos) {
        int wordIdx = pos >>> 6;  // pos / 64
        int bitIdx = pos & 63;    // pos % 64
        arr[wordIdx] |= 1L << bitIdx;
    }

    private static void printGrid(char[][] grid) {
        StringBuilder sb = new StringBuilder(grid.length * (grid[0].length + 1));
        for (char[] row : grid) {
            sb.append(row).append('\n');
        }
        System.out.print(sb);
    }

    private static void printFullGrid(int R, int C) {
        StringBuilder sb = new StringBuilder(R * (C + 1));
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                sb.append('O');
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }
}