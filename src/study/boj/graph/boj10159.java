
package study.boj.graph;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 백준 10159번 - 비교 불가능한 쌍
 *
 * 문제: 각 물건에 대해 다른 모든 물건과의 대소 관계를 알 수 없는 경우의 수를 구하기
 *
 * 알고리즘: 플로이드-워셜(Floyd-Warshall) 알고리즘을 이용한 전이성 관계 파악
 * - 두 개의 그래프 유지: heavierThan(더 무거운), lighterThan(더 가벼운)
 * - 직접 측정한 관계뿐 아니라 전이성을 통해 도출 가능한 모든 관계 파악
 * - 각 물건에 대해 무게 대소를 알 수 없는 다른 물건의 개수 계산
 *
 * 핵심 아이디어:
 * 1. 입력된 측정 관계 A > B를 그래프에 저장
 *    - heavierThan[A][B] = true (A가 B보다 무겁다)
 *    - lighterThan[B][A] = true (B가 A보다 가볍다)
 * 2. 플로이드-워셜 알고리즘으로 전이성을 통한 모든 가능한 관계 도출
 *    - A > B이고 B > C이면 A > C (heavierThan)
 *    - C < B이고 B < A이면 C < A (lighterThan)
 * 3. 각 물건 i에 대해 heavierThan[i][j]도 false, lighterThan[i][j]도 false인 j의 개수 계산
 *    - 즉, i와 j의 대소 관계를 알 수 없는 경우
 *
 * 시간복잡도: O(N³) - 플로이드-워셜 알고리즘
 * - 세 개의 nested loop로 모든 경로 확인: for k, i, j
 * - 각 원소당 비교 불가 개수 계산: O(N²)
 *
 * 공간복잡도: O(N²) - 두 개의 인접 행렬
 * - heavierThan, lighterThan 각각 N×N 불린 배열
 */
public class boj10159 {
    private static boolean[][] heavierThan;
    private static boolean[][] lighterThan;
    private static int itemCount;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        itemCount = Integer.parseInt(br.readLine());
        int measurementCount = Integer.parseInt(br.readLine());

        initializeGraphs();
        readMeasurements(br, measurementCount);
        applyFloydWarshall();

        StringBuilder result = buildResult();
        System.out.print(result);
    }

    private static void initializeGraphs() {
        int size = itemCount + 1;
        heavierThan = new boolean[size][size];
        lighterThan = new boolean[size][size];
    }

    private static void readMeasurements(BufferedReader br, int measurementCount) throws IOException {
        StringTokenizer st;
        for (int i = 0; i < measurementCount; i++) {
            st = new StringTokenizer(br.readLine());
            int heavier = Integer.parseInt(st.nextToken());
            int lighter = Integer.parseInt(st.nextToken());

            heavierThan[heavier][lighter] = true;
            lighterThan[lighter][heavier] = true;
        }
    }

    private static void applyFloydWarshall() {
        for (int k = 1; k <= itemCount; k++) {
            for (int i = 1; i <= itemCount; i++) {
                for (int j = 1; j <= itemCount; j++) {
                    if (heavierThan[i][k] && heavierThan[k][j]) {
                        heavierThan[i][j] = true;
                    }
                    if (lighterThan[i][k] && lighterThan[k][j]) {
                        lighterThan[i][j] = true;
                    }
                }
            }
        }
    }

    private static StringBuilder buildResult() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= itemCount; i++) {
            int unknownRelationCount = countUnknownRelations(i);
            sb.append(unknownRelationCount).append("\n");
        }
        return sb;
    }

    private static int countUnknownRelations(int item) {
        int count = 0;
        for (int j = 1; j <= itemCount; j++) {
            if (item == j) continue;

            boolean cannotCompare = !heavierThan[item][j] && !lighterThan[item][j];
            if (cannotCompare) {
                count++;
            }
        }
        return count;
    }
}