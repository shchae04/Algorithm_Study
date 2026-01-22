package study.boj.geometry;
import java.util.Scanner;

public class boj17553 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); // 구명요원 수
        int[][] shifts = new int[N][2]; // 근무 시간 저장

        for (int i = 0; i < N; i++) {
            shifts[i][0] = sc.nextInt(); // 시작 시간
            shifts[i][1] = sc.nextInt(); // 끝 시간
        }

        int maxCovered = 0;

        for (int i = 0; i < N; i++) { // i번째 요원을 해고해본다
            int[] time = new int[1001]; // 시간당 커버 인원 수

            for (int j = 0; j < N; j++) {
                if (i == j) continue; // i번은 해고했으므로 제외

                int start = shifts[j][0];
                int end = shifts[j][1];

                for (int t = start; t < end; t++) {
                    time[t] = 1; // 이 시간대는 커버됨
                }
            }

            int covered = 0;
            for (int t = 0; t <= 1000; t++) {
                if (time[t] == 1) covered++;
            }

            maxCovered = Math.max(maxCovered, covered);
        }

        System.out.println(maxCovered);
    }
}
