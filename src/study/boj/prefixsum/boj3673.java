package study.boj.prefixsum;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 3673 - 나눌 수 있는 부분 수열
 * 
 * 문제: 주어진 정수 수열에서 연속하는 부분 수열의 합이 특정 수 d로 나누어 떨어지는 부분 수열의 개수를 구하는 문제
 * 
 * 알고리즘: 누적합(Prefix Sum)과 나머지 연산의 성질 활용
 * - 두 누적합의 나머지가 같다면, 그 사이의 부분 수열의 합은 d로 나누어 떨어짐
 * - sum[i] % d == sum[j] % d 이면 (sum[i] - sum[j]) % d == 0
 * 
 * 시간복잡도: O(C × N) - C: 테스트 케이스 수, N: 수열 길이
 * 공간복잡도: O(N + D) - N: 수열 길이, D: 나누는 수
 */
public class boj3673 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int c = Integer.parseInt(br.readLine());
        
        for (int i = 0; i < c; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken()); // 나누는 수
            int n = Integer.parseInt(st.nextToken()); // 수열의 길이
            double[] arr = new double[n];
            st = new StringTokenizer(br.readLine());
            
            // 수열 입력
            for (int j = 0; j < n; j++) {
                arr[j] = Double.parseDouble(st.nextToken());
            }

            // 각 나머지 값의 출현 횟수를 저장하는 배열
            double[] modulo = new double[d];
            for (int j = 0; j < d; j++) {
                modulo[j] = 0;
            }
            
            int count = 0;  // 조건을 만족하는 부분 수열의 개수
            int sum = 0;    // 현재까지의 누적합
            
            // 핵심 알고리즘: 누적합을 계산하면서 나머지가 같은 이전 위치들의 개수를 카운트
            for (double num : arr) {
                sum += num;   // 누적합 계산
                sum %= d;     // 나머지 연산
                
                // 현재 나머지와 같은 이전 위치들의 개수만큼 부분 수열이 d로 나누어떨어짐
                count += modulo[sum];
                modulo[sum]++; // 현재 나머지 값의 출현 횟수 증가
            }
            
            // 처음부터 시작하는 부분 수열 중 합이 d로 나누어떨어지는 경우 추가
            count += modulo[0];
            System.out.println(count);
        }
    }
}
