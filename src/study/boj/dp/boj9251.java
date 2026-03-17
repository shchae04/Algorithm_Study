package study.boj.dp;

import java.io.*;

/**
 * 백준 9251: LCS (Longest Common Subsequence)
 * 두 문자열의 최장 공통 부분 수열의 길이를 구하는 문제
 * DP를 활용한 O(n*m) 시간복잡도 해결
 *
 * 예시:
 * Input:
 *   ACAYKP
 *   CAPCAK
 *
 * DP 테이블 생성 과정:
 *       ""  C  A  P  C  A  K
 *   ""   0  0  0  0  0  0  0
 *   A    0  0  1  1  1  1  1
 *   C    0  1  1  1  2  2  2
 *   A    0  1  2  2  2  3  3
 *   Y    0  1  2  2  2  3  3
 *   K    0  1  2  2  2  3  4
 *   P    0  1  2  3  3  3  4
 *
 * 설명:
 * - dp[2][4] = 2: "AC"와 "CAPC"의 LCS는 "AC" (길이 2)
 * - dp[3][5] = 3: "ACA"와 "CAPCA"의 LCS는 "ACA" (길이 3)
 * - dp[6][6] = 4: "ACAYKP"와 "CAPCAK"의 LCS는 "ACAK" (길이 4)
 *
 * Output: 4
 */
public class boj9251 {

	static int[][] dp; // DP 테이블: dp[i][j] = str1의 i번째까지와 str2의 j번째까지의 LCS 길이

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 두 문자열 입력
		String str1 = br.readLine();
		String str2 = br.readLine();

		// LCS 길이 계산 및 출력
		System.out.println(LCS(str1, str2));

	}

	/**
	 * LCS 알고리즘: 두 문자열의 최장 공통 부분 수열 길이 계산
	 * @param str1 첫 번째 문자열
	 * @param str2 두 번째 문자열
	 * @return 최장 공통 부분 수열의 길이
	 */
	static int LCS(String str1, String str2) {
		int n1 = str1.length();
		int n2 = str2.length();

		// DP 테이블 초기화 (1-indexed 사용)
		dp = new int[n1+1][n2+1];

		// DP 테이블 채우기
		for(int i=1; i<n1+1; i++) {
			for(int j=1; j<n2+1; j++) {
				// 현재 문자가 같으면: 이전 대각선 값 + 1
				if(str1.charAt(i-1) == str2.charAt(j-1)) {
					dp[i][j] = dp[i-1][j-1]+1;
				}
				// 현재 문자가 다르면: 위쪽 또는 왼쪽 중 최댓값
				// dp[i-1][j]: str1의 i-1번째까지와 str2의 j번째까지의 LCS
				// dp[i][j-1]: str1의 i번째까지와 str2의 j-1번째까지의 LCS
				else {
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				}
			}
		}

		// 전체 문자열에 대한 LCS 길이 반환
		return dp[n1][n2];
	}
}
