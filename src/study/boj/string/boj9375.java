package study.boj.string;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 백준 9375번 - 패션왕 신해빈
 *
 * 문제: 각 의상 종류별로 하나씩 입거나 입지 않을 수 있을 때, 가능한 의상 조합의 가짓수 구하기
 * (단, 알몸 상태는 제외)
 *
 * 알고리즘: 조합론 + 해시맵
 * - 각 의상 종류별로 개수를 카운팅
 * - 각 종류마다 (해당 종류 의상을 입지 않는 경우) 포함하여 경우의 수 계산
 * - 모든 종류를 곱한 뒤, 아무것도 입지 않은 경우 1가지를 제외
 *
 * 핵심 아이디어:
 * 1. 의상 종류별로 개수를 HashMap에 저장 (의상 이름은 불필요, 종류만 중요)
 * 2. 각 종류별로 (개수 + 1)을 계산 (해당 종류를 입지 않는 선택지 포함)
 * 3. 모든 종류의 (개수 + 1)을 곱하여 전체 경우의 수 계산
 * 4. 아무것도 입지 않은 경우 1가지를 빼기
 *
 * 예시: 모자 2개, 상의 3개가 있을 때
 * - 모자: 안입음, 모자1, 모자2 = 3가지
 * - 상의: 안입음, 상의1, 상의2, 상의3 = 4가지
 * - 전체: 3 × 4 = 12가지
 * - 알몸(모두 안입음) 제외: 12 - 1 = 11가지
 *
 * 시간복잡도: O(T × N) - T는 테스트 케이스 수, N은 각 케이스의 의상 개수
 * - 각 의상마다 HashMap에 카운팅: O(N)
 * - 각 종류마다 곱셈 연산: O(종류 수) ≤ O(N)
 *
 * 공간복잡도: O(K) - K는 의상 종류의 수
 * - HashMap에 의상 종류별 개수만 저장
 */

public class boj9375 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            int t = Integer.parseInt(br.readLine());
            Map<String, Integer> map = new HashMap<>();
            for (int j = 0; j < t; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                st.nextToken();
                String key = st.nextToken();
                map.put(key, map.getOrDefault(key, 0) + 1);
            }
            int count = 1;
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                count = count * (entry.getValue() + 1);
            }
            sb.append(count - 1).append("\n");
        }
        System.out.println(sb);
    }
}
