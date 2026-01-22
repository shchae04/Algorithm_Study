package study.boj.string;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 백준 16955번 - 오목, 이길 수 있을까?
 * 
 * 문제: 10x10 오목판에서 'X' 말 하나만 더 놓으면 5목을 만들 수 있는지 판단
 * '.'(빈 자리), 'X'(내 말), 'O'(상대 말)로 구성된 판
 * 
 * 알고리즘: 브루트 포스 + 시뮬레이션
 * - 모든 빈 자리('.')에 'X'를 놓는 경우를 시도
 * - 각 위치에 대해 4방향(가로, 세로, 대각선 2개) 검사
 * - 연속된 'X' 개수가 4개 이상이면 5목 완성 가능
 * - 중앙에 놓으면 양쪽에서 X를 카운트하여 총 4개 이상이면 성공
 * 
 * 시간복잡도: O(N^2) - N=10이므로 상수 시간
 * 공간복잡도: O(N^2)
 */
public class boj16955 {
    char[][] arr;

    private boolean chk(int r, int c) {
        // horizon chk
        int cnt = 0;
        for (int i = c - 1; i >= 0 && arr[r][i] == 'X'; i--) cnt++;
        for (int i = c + 1; i < 10 && arr[r][i] == 'X'; i++) cnt++;
        if (cnt >= 4) return true;

        // vertical chk
        cnt = 0;
        for (int i = r - 1; i >= 0 && arr[i][c] == 'X'; i--) cnt++;
        for (int i = r + 1; i < 10 && arr[i][c] == 'X'; i++) cnt++;
        if (cnt >= 4) return true;

        // '/' diagonal chk
        cnt = 0;
        for (int i = r + 1, j = c - 1; i < 10 && j >= 0 && arr[i][j] == 'X'; i++, j--) cnt++;
        for (int i = r - 1, j = c + 1; i >= 0 && j < 10 && arr[i][j] == 'X'; i--, j++) cnt++;
        if (cnt >= 4) return true;

        // '\' diagonal chk
        cnt = 0;
        for (int i = r + 1, j = c + 1; i < 10 && j < 10 && arr[i][j] == 'X'; i++, j++) cnt++;
        for (int i = r - 1, j = c - 1; i >= 0 && j >= 0 && arr[i][j] == 'X'; i--, j--) cnt++;
        if (cnt >= 4) return true;

        return false;
    }

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        arr = new char[10][10];
        for (int i = 0; i < 10; i++) {
            String s = br.readLine();
            for (int j = 0; j < 10; j++) {
                arr[i][j] = s.charAt(j);
            }
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (arr[i][j] != '.') continue;
                if (chk(i, j)) {
                    System.out.println(1);
                    return;
                }
            }
        }
        System.out.println(0);
    }

    public static void main(String[] args) throws Exception {
        new boj16955().solution();
    }
}