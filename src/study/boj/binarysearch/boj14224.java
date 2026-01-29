package study.boj.binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ 14224: 작은 정사각형 2
 *
 * 정수 좌표 점 중 적어도 K개를 내부(경계 제외)에 포함하는
 * 축 평행 정사각형의 최소 넓이를 구한다.
 *
 * 내부는 정수 격자 기준 [x0+1, x0+L-1]로 바뀌므로,
 * 포함 조건을 닫힌 정사각형 [x1, x1+W]로 변환하고(W = L-2),
 * W에 대해 이분 탐색 + 좌표 후보(점 좌표) 전수 조사로 판정한다.
 */
public class boj14224 {
    static int N;
    static int K;
    static long[] xs;
    static long[] ys;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        xs = new long[N];
        ys = new long[N];
        long minX = Long.MAX_VALUE;
        long maxX = Long.MIN_VALUE;
        long minY = Long.MAX_VALUE;
        long maxY = Long.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());
            xs[i] = x;
            ys[i] = y;
            minX = Math.min(minX, x);
            maxX = Math.max(maxX, x);
            minY = Math.min(minY, y);
            maxY = Math.max(maxY, y);
        }

        long left = 0;
        long right = Math.max(maxX - minX, maxY - minY);
        long answer = right;

        while (left <= right) {
            long mid = (left + right) / 2;
            if (canContain(mid)) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        long side = answer + 2;
        long area = side * side;
        System.out.println(area);
    }

    private static boolean canContain(long width) {
        for (int i = 0; i < N; i++) {
            long baseX = xs[i];
            long maxX = baseX + width;
            for (int j = 0; j < N; j++) {
                long baseY = ys[j];
                long maxY = baseY + width;
                int count = 0;
                for (int k = 0; k < N; k++) {
                    long x = xs[k];
                    long y = ys[k];
                    if (x < baseX || x > maxX || y < baseY || y > maxY) {
                        continue;
                    }
                    count++;
                    if (count >= K) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
