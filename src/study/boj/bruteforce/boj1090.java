package study.boj.bruteforce;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 백준 1090번 - 체커 문제 해결
 * 
 * 문제 설명:
 * N개의 체커가 주어진 좌표에 있고, 한 위치에 모든 체커를 모으려고 한다.
 * i번째부터 N번째까지의 체커를 모두 한 점으로 모으는데 필요한 최소 거리의 합을 구하는 문제
 * 
 * 핵심 아이디어:
 * 1. 최적의 집합점은 항상 주어진 체커들 중 하나의 좌표 조합에 위치한다
 * 2. 모든 가능한 (x, y) 위치에서 각 체커까지의 맨하탄 거리를 계산
 * 3. i번째부터 N번째까지 가장 가까운 체커들의 거리 합을 구함
 * 
 * 알고리즘 분류: Brute Force (완전탐색)
 * 시간복잡도: O(N^3 log N)
 * - N^2개의 가능한 집합점 위치 탐색
 * - 각 위치마다 N개 체커의 거리 계산 및 정렬 (N log N)
 * 공간복잡도: O(N) - 좌표 저장과 거리 배열을 위한 공간
 * 
 * @author Algorithm Study
 */
public class boj1090 {
    /**
     * 백준 1090번 체커 문제 해결 메인 메소드
     * 
     * 알고리즘 접근:
     * 1. 입력된 모든 체커의 x좌표와 y좌표를 가능한 집합점 후보로 설정
     * 2. 각 (x, y) 조합에서 모든 체커까지의 맨하탄 거리 계산
     * 3. 거리를 오름차순 정렬하여 i번째부터 N번째까지의 최소 거리 합 갱신
     * 
     * 핵심 통찰:
     * - 최적해는 항상 기존 체커 좌표들의 x, y 조합에서 발생
     * - 맨하탄 거리의 특성상 각 축을 독립적으로 최적화 가능
     */
    public static void main(String[] args) throws IOException {
        // 빠른 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        int[] x = new int[n];
        int[] y = new int[n];
        int[] result = new int[n];

        Arrays.fill(result, -1);

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            x[i] = Integer.parseInt(st.nextToken());
            y[i] = Integer.parseInt(st.nextToken());
        }

        // 가능한 모든 위치(x, y) 탐색
        for (int xIdx : x) {
            for (int yIdx : y) {
                int[] dist = new int[n];

                for (int k = 0; k < n; k++) {
                    dist[k] = Math.abs(x[k] - xIdx) + Math.abs(y[k] - yIdx);
                }

                Arrays.sort(dist);

                int tmp = 0;
                for (int m = 0; m < n; m++) {
                    tmp += dist[m];
                    if (result[m] == -1) {
                        result[m] = tmp;
                    } else {
                        result[m] = Math.min(result[m], tmp);
                    }
                }
            }
        }

        // 빠른 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(result[i]);
            if (i != n - 1) sb.append(" ");
        }

        System.out.println(sb);
    }
}