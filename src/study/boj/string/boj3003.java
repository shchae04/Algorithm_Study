package study.boj.string;
import java.io.*;
import java.util.StringTokenizer;

/**
 * 백준 3003번 - 킹, 퀸, 룩, 비숍, 나이트, 폰
 * 
 * 문제: 체스에서 현재 가지고 있는 피스를 입력받아 완전한 체스 세트를 만들기 위해 필요한 피스 수
 * 완전한 체스 세트: 킹 1개, 퀸 1개, 룩 2개, 비숍 2개, 나이트 2개, 폰 8개
 * 
 * 알고리즘: 단순 수학 계산
 * - 필요한 개수에서 현재 가진 개수를 뺄셈
 * - 양수면 더 필요, 음수면 초과, 0이면 완벽
 * 
 * 시간복잡도: O(1)
 * 공간복잡도: O(1)
 */
public class boj3003 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = {1, 1, 2, 2, 2, 8};

        for (int i = 0; i < arr.length; i++) {
            bw.write(arr[i] - Integer.parseInt(st.nextToken()) + " ");
        }
        bw.flush();
        bw.close();
        
    }
}
