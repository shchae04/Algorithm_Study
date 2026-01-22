
package study.boj.bruteforce;
import java.util.Scanner;

public class boj14658 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();
        int L = sc.nextInt();
        int K = sc.nextInt();

        /**
         * 문제 해결 전략:
         * 1. 트램펄린의 왼쪽 하단 모서리 위치를 (startX, startY)라고 할 때
         * 2. 트램펄린이 덮는 영역: startX ≤ x ≤ startX+L, startY ≤ y ≤ startY+L
         * 3. 최적해는 반드시 어떤 별똥별의 x좌표나 y좌표를 트램펄린의 경계로 하는 경우에 존재
         * 4. 따라서 모든 별똥별의 좌표를 트램펄린의 시작점 후보로 고려
         */

        int[][] stars = new int[K][2]; // 별똥별의 좌표 저장

        // 별똥별 좌표 입력
        for (int i = 0; i < K; i++) {
            stars[i][0] = sc.nextInt(); // x좌표
            stars[i][1] = sc.nextInt(); // y좌표
        }

        int maxCovered = 0; // 트램펄린으로 막을 수 있는 최대 별똥별 수

        // 모든 별똥별의 x좌표를 트램펄린의 시작 x좌표로 고려
        for (int i = 0; i < K; i++) {
            // 모든 별똥별의 y좌표를 트램펄린의 시작 y좌표로 고려
            for (int j = 0; j < K; j++) {
                int startX = stars[i][0];
                int startY = stars[j][1];

                // 트램펄린이 구역을 벗어나지 않도록 조정
                if (startX + L > N) startX = N - L;
                if (startY + L > M) startY = M - L;
                if (startX < 0) startX = 0;
                if (startY < 0) startY = 0;

                // 현재 트램펄린 위치에서 막을 수 있는 별똥별 수 계산
                int covered = countCoveredStars(stars, startX, startY, L);
                maxCovered = Math.max(maxCovered, covered);
            }
        }

        // 지구에 떨어지는 별똥별 수 = 전체 별똥별 수 - 막은 별똥별 수
        System.out.println(K - maxCovered);
    }

    /**
     * 주어진 트램펄린 위치에서 막을 수 있는 별똥별의 수를 계산
     * @param stars 별똥별 좌표 배열
     * @param startX 트램펄린 시작 x좌표
     * @param startY 트램펄린 시작 y좌표
     * @param L 트램펄린 한 변의 길이
     * @return 막을 수 있는 별똥별 수
     */
    private static int countCoveredStars(int[][] stars, int startX, int startY, int L) {
        int count = 0;

        for (int[] star : stars) {
            int x = star[0];
            int y = star[1];

            // 별똥별이 트램펄린 영역 내에 있는지 확인
            // 트램펄린 영역: [startX, startX+L] × [startY, startY+L]
            if (x >= startX && x <= startX + L && y >= startY && y <= startY + L) {
                count++;
            }
        }

        return count;
    }
}