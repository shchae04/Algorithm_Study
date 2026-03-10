package study.boj.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 1158번 - 요세푸스 문제
 *
 * N명의 사람이 원을 이루며 앉아 있다고 가정한다.
 * 1번부터 N번까지 순서대로 존재하며, K번째 사람을 반복해서 제거했을 때의 제거 순서를 출력하는 문제이다.
 *
 * 이 코드는 큐(Queue)를 사용해 원형 구조를 단순하게 흉내 낸다.
 * - 맨 앞 사람을 꺼내 다시 뒤에 넣으면, 원형으로 한 칸 이동한 것과 같은 효과가 난다.
 * - 이를 K - 1번 반복하면 K번째 사람이 큐의 맨 앞에 오게 된다.
 * - 그 사람을 poll() 하여 제거 순서에 기록한다.
 *
 * 입력이 7 3인 경우의 진행 예시:
 * 초기 큐: [1, 2, 3, 4, 5, 6, 7]
 *
 * 1회전
 * - 1을 뒤로 이동 -> [2, 3, 4, 5, 6, 7, 1]
 * - 2를 뒤로 이동 -> [3, 4, 5, 6, 7, 1, 2]
 * - 3 제거
 *
 * 2회전
 * - 현재 큐: [4, 5, 6, 7, 1, 2]
 * - 4를 뒤로 이동 -> [5, 6, 7, 1, 2, 4]
 * - 5를 뒤로 이동 -> [6, 7, 1, 2, 4, 5]
 * - 6 제거
 *
 * 같은 방식으로 계속 반복하면 제거 순서는
 * 3 -> 6 -> 2 -> 7 -> 5 -> 1 -> 4
 * 이고, 최종 출력은 <3, 6, 2, 7, 5, 1, 4> 이다.
 *
 * 시간 복잡도는 대략 O(NK)이다.
 * 문제의 입력 범위에서는 이 방식으로 충분히 통과할 수 있다.
 */
public class boj1158 {
    public static void main(String[] args) throws IOException {
        // 입력 예시: "7 3"
        // N = 사람 수, K = 제거할 간격
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        // 1번부터 N번까지 사람을 순서대로 큐에 넣는다.
        // 큐의 front가 현재 기준 위치라고 생각하면 된다.
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            queue.offer(i);
        }

        // 정답은 문제에서 요구한 형식 그대로 "<3, 6, 2, ...>" 형태로 만들어야 한다.
        StringBuilder sb = new StringBuilder();
        sb.append("<");

        while (!queue.isEmpty()) {
            // K번째 사람을 제거하기 위해, 앞의 K-1명을 뒤로 보낸다.
            // 예를 들어 K=3이면:
            // 1) 첫 번째 사람을 뒤로 이동
            // 2) 두 번째 사람을 뒤로 이동
            // 3) 이제 세 번째 사람이 맨 앞에 오므로 제거 가능
            //
            // 입력이 7 3일 때 첫 반복은 다음과 같다.
            // [1, 2, 3, 4, 5, 6, 7]
            // -> [2, 3, 4, 5, 6, 7, 1]
            // -> [3, 4, 5, 6, 7, 1, 2]
            // -> 3 제거
            for (int i = 0; i < k - 1; i++) {
                queue.offer(queue.poll());
            }

            // 현재 맨 앞의 사람이 이번 차례에 제거되는 K번째 사람이다.
            sb.append(queue.poll());

            // 아직 제거할 사람이 남아 있으면 쉼표와 공백을 붙여 형식을 맞춘다.
            if (!queue.isEmpty()) {
                sb.append(", ");
            }
        }

        // 요세푸스 순열 출력 형식의 마무리
        sb.append(">");
        System.out.println(sb);
    }
}
