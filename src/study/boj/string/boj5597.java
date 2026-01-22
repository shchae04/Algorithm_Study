package study.boj.string;
import java.io.*;

/**
 * 백준 5597번 - 과제 안 내신 분?..
 * 
 * 문제: 1번부터 30번까지의 학생 중 28명이 과제를 내었을 때, 과제를 안 낸 2명을 찾는 문제
 * 
 * 알고리즘: 불리언 배열 체크
 * - 31개 크기의 boolean 배열 생성 (1번부터 30번 인덱스 사용)
 * - 28개의 제출자 번호를 입력받아 해당 인덱스를 true로 설정
 * - 1번부터 30번까지 순회하여 false인 인덱스 2개를 출력
 * 
 * 시간복잡도: O(1) - 고정된 28, 30 크기
 * 공간복잡도: O(1)
 */
public class boj5597 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        boolean[] arr = new boolean[31]; // 1 ~ 30

        for (int i = 1; i <= 28; i++) {
            int n = Integer.parseInt(br.readLine());
            arr[n] = true;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 30; i++) {
            if (!arr[i]) {
                sb.append(i).append("\n");
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
