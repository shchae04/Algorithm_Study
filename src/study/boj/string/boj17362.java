package study.boj.string;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 백준 17362번 - 수학은 체육과목 입니다2
 * 
 * 문제: 1-2-3-4-5-4-3-2-1-2-3-4-5... 패턴으로 반복되는 수열에서 N번째 수를 구하는 문제
 * 
 * 알고리즘: 패턴 반복 + 모듈러 연산
 * - 패턴을 관찰하면 8개 주기로 반복: [1,2,3,4,5,4,3,2]
 * - N을 8로 나눋 나머지를 구해서 위치 결정
 * - 큰 수 N에 대해 문자열로 입력받아 모듈러 연산 최적화
 * - k = (N-1) % 8에 따라 switch문으로 결과 매핑
 * 
 * 시간복잡도: O(L) - L: 입력 수의 자릿수
 * 공간복잡도: O(1)
 */
public class boj17362 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine().trim();

        int rem = 0;
        for (int i = 0; i < s.length(); i++) {
            rem = (rem * 10 + (s.charAt(i) - '0')) % 8;
        }

        // k = (n - 1) % 8
        int k = (rem - 1 + 8) % 8;

        int ans;
        switch (k) {
            case 0:  ans = 1; break;
            case 1:
            case 7:  ans = 2; break;
            case 2:
            case 6:  ans = 3; break;
            case 3:
            case 5:  ans = 4; break;
            default: ans = 5; break; // k == 4
        }

        System.out.println(ans);
    }

}
