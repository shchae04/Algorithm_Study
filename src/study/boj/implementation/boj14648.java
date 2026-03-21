package study.boj.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 14648 - 쿼리 맛보기
 * 펜윅 트리로 구간 합을 구하고, 1번 쿼리의 swap은 두 위치 값만 갱신한다.
 */
public class boj14648 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        long[] numbers = new long[n + 1];
        FenwickTree fenwickTree = new FenwickTree(n);

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            numbers[i] = Long.parseLong(st.nextToken());
            fenwickTree.add(i, numbers[i]);
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());

            if (type == 1) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                sb.append(fenwickTree.rangeSum(a, b)).append('\n');

                if (a != b) {
                    long firstValue = numbers[a];
                    long secondValue = numbers[b];

                    numbers[a] = secondValue;
                    numbers[b] = firstValue;

                    fenwickTree.add(a, secondValue - firstValue);
                    fenwickTree.add(b, firstValue - secondValue);
                }
            } else {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());

                long result = fenwickTree.rangeSum(a, b) - fenwickTree.rangeSum(c, d);
                sb.append(result).append('\n');
            }
        }

        System.out.print(sb);
    }

    private static class FenwickTree {
        private final long[] tree;

        private FenwickTree(int size) {
            this.tree = new long[size + 1];
        }

        private void add(int index, long value) {
            for (int i = index; i < tree.length; i += i & -i) {
                tree[i] += value;
            }
        }

        private long sum(int index) {
            long result = 0;
            for (int i = index; i > 0; i -= i & -i) {
                result += tree[i];
            }
            return result;
        }

        private long rangeSum(int left, int right) {
            return sum(right) - sum(left - 1);
        }
    }
}
