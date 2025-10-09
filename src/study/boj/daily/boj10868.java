package study.boj.daily;

import java.io.*;
import java.util.*;

/**
 * 백준 10868번 - 최솟값
 *
 * 문제: N개의 정수들이 주어질 때, a번째부터 b번째까지의 최솟값을 구하는 쿼리를 M번 처리
 *
 * 알고리즘: 세그먼트 트리(Segment Tree)를 이용한 구간 최솟값(Range Minimum Query, RMQ)
 * - 세그먼트 트리는 구간에 대한 질의를 효율적으로 처리하는 자료구조
 * - 완전 이진 트리 형태로 각 노드가 특정 구간의 최솟값을 저장
 * - 리프 노드는 개별 원소, 내부 노드는 자식 구간의 최솟값을 저장
 *
 * 핵심 아이디어:
 * 1. 트리 구축: 리프 노드부터 루트까지 상향식으로 최솟값 계산
 *    - 리프 노드 개수를 2의 거듭제곱으로 맞추기 위해 트리 크기 조정
 *    - 각 부모 노드는 두 자식 노드의 최솟값으로 설정
 *
 * 2. 구간 질의: 구간을 분할하여 트리를 탐색하며 최솟값 계산
 *    - 홀수 인덱스(좌측 경계)면 해당 노드를 포함하고 다음 노드로 이동
 *    - 짝수 인덱스(우측 경계)면 해당 노드를 포함하고 이전 노드로 이동
 *    - 부모 레벨로 올라가며 효율적으로 탐색
 *
 * 시간복잡도:
 * - 트리 구축: O(N) - 모든 노드를 한 번씩 계산
 * - 구간 쿼리: O(log N) - 트리의 높이만큼 탐색
 * - 전체: O(N + M log N) - N은 원소 개수, M은 쿼리 개수
 *
 * 공간복잡도: O(N) - 세그먼트 트리를 위한 배열 (크기 2^(k+1))
 * - k는 ceil(log2(N))으로, 리프 노드 개수를 2의 거듭제곱으로 맞추기 위한 값
 *
 * 세그먼트 트리는 구간 합, 최솟값, 최댓값 등 다양한 구간 질의에 활용 가능
 */

public class boj10868 {

    static long[] tree;
    static int N, leafStart;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int k = (int) Math.ceil(Math.log(N) / Math.log(2));
        leafStart = 1 << k; // 리프 노드의 시작 인덱스 (2^k)
        int size = 2 * leafStart; // 트리 배열의 전체 크기
        tree = new long[size]; // 트리 배열 초기화

        // 리프 노드에 입력 값 저장
        for (int i = 0; i < N; i++) {
            tree[leafStart + i] = Long.parseLong(br.readLine());
        }

        // 부모 노드들의 값을 자식 노드들의 비교로 최솟값 대입
        for (int i = leafStart - 1; i > 0; i--) {
            tree[i] = Math.min(tree[i * 2], tree[i * 2 + 1]);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            sb.append(min(a, b)).append("\n");
        }
        System.out.println(sb);
    }

    public static long min(int start, int end) {
        start = leafStart + start - 1;
        end = leafStart + end - 1;
        long minValue = Long.MAX_VALUE;

        while (start <= end) {
            if (start % 2 == 1) minValue = Math.min(tree[start++], minValue);
            if (end % 2 == 0) minValue = Math.min(tree[end--], minValue);
            start /= 2;
            end /= 2;
        }

        return minValue;
    }
}