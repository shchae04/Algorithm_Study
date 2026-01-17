package study.boj.daily;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 백준 2164번 - 카드2
 * <p>
 * 문제: N장의 카드가 1번부터 N번까지 번호가 붙어있으며, 1번이 제일 위, N번이 제일 아래인 상태로 순서대로 쌓여있다.
 * - 제일 위의 카드를 버린다.
 * - 그 다음, 제일 위의 카드를 제일 아래로 옮긴다.
 * - 카드가 한 장 남을 때까지 반복했을 때, 마지막에 남는 카드를 구하는 프로그램
 * <p>
 * 알고리즘: 큐(Queue) 자료구조
 * - 선입선출(FIFO) 특성을 가진 큐를 이용하여 카드 덱의 동작을 시뮬레이션
 * <p>
 * 핵심 아이디어:
 * 1. 1부터 N까지의 정수를 큐에 순서대로 삽입 (Add)
 * 2. 큐의 크기가 1이 될 때까지 반복:
 * - poll(): 맨 앞의 원소 제거 (버림)
 * - poll() -> add(): 그 다음 맨 앞의 원소를 꺼내서 다시 맨 뒤에 삽입 (아래로 옮김)
 * 3. 마지막에 남은 원소 출력 (peek)
 * <p>
 * 시간복잡도: O(N)
 * - 초기 큐 구성: O(N)
 * - 시뮬레이션: 매 반복마다 카드가 1장씩 줄어듦. (버림 1회 + 이동 1회). 총 N-1번 반복
 * - 총 O(N)
 * <p>
 * 공간복잡도: O(N)
 * - 큐에 최대 N개의 정수 저장
 * <p>
 * 특이사항:
 * - Java의 LinkedList를 Queue 구현체로 사용
 * - 입력 N (1 ≤ N ≤ 500,000)
 */
public class boj2164 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            queue.add(i);
        }

        while (queue.size() > 1) {
            queue.poll();
            int temp = queue.poll();
            queue.add(temp);
        }

        bw.write(queue.peek() + "\n");
        bw.close();
        br.close();
    }
}
