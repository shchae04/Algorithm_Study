package study.boj.daily;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 23825번 - SASA 모형을 만들어보자
 *
 * 문제: 'S' 블록과 'A' 블록을 이용해서 SASA 모형을 최대한 많이 만들기
 * - SASA 모형 하나를 만들려면 'S' 블록 2개와 'A' 블록 2개가 필요함
 * - 주어진 S개의 'S' 블록과 A개의 'A' 블록으로 만들 수 있는 최대 SASA 모형 개수 구하기
 *
 * 알고리즘: 수학 (간단한 계산)
 * - S 블록으로 만들 수 있는 최대 개수: S/2
 * - A 블록으로 만들 수 있는 최대 개수: A/2
 * - 둘 중 작은 값이 실제로 만들 수 있는 SASA 모형의 최대 개수
 *
 * 핵심 아이디어:
 * 1. 병목 현상 (Bottleneck):
 *    - SASA 모형은 S 2개, A 2개가 모두 있어야 만들 수 있음
 *    - 즉, S가 100개 있어도 A가 2개밖에 없으면 SASA는 1개밖에 못 만듦
 *    - 따라서 S/2와 A/2 중 더 작은 값이 답이 됨
 *
 * 2. 정수 나눗셈 활용:
 *    - S/2는 S 블록으로 만들 수 있는 'S 쌍'의 개수
 *    - A/2는 A 블록으로 만들 수 있는 'A 쌍'의 개수
 *    - 정수 나눗셈이므로 홀수일 때 나머지는 자동으로 버려짐 (예: 5/2 = 2)
 *
 * 시간복잡도: O(1)
 * - 단순 계산 문제라서 상수 시간에 해결
 *
 * 공간복잡도: O(1)
 * - 추가 공간 사용 없음
 */
public class boj23825 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();

        if (line == null)
            return;

        StringTokenizer st = new StringTokenizer(line);

        // S 블록의 개수
        int S = Integer.parseInt(st.nextToken());
        // A 블록의 개수
        int A = Integer.parseInt(st.nextToken());

        // S 블록 2개로 만들 수 있는 쌍의 개수
        int SS = S / 2;
        // A 블록 2개로 만들 수 있는 쌍의 개수
        int AA = A / 2;

        // SASA 모형은 S쌍과 A쌍이 모두 있어야 하므로, 둘 중 작은 값이 답
        System.out.println(Math.min(SS, AA));
    }

}
