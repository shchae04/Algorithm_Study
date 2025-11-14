package study.boj.daily;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 백준 1138번 - 한 줄로 서기
 *
 * 문제: N명의 사람이 한 줄로 서 있을 때, 각 사람의 키 순서와 왼쪽에 자기보다 큰 사람의 수를 보고 원래 순서를 복원하기
 *
 * 알고리즘: 그리디(Greedy) + 리스트 삽입
 * - 키가 큰 사람부터 역순으로 처리하여 줄에 배치
 * - 각 사람을 "왼쪽에 있는 자기보다 큰 사람의 수" 위치에 삽입
 * - ArrayList의 add(index, element) 메서드를 활용한 효율적인 삽입
 *
 * 핵심 아이디어:
 * 1. 가장 큰 사람(N)부터 가장 작은 사람(1)까지 역순으로 처리
 * 2. 현재 처리 중인 사람보다 큰 사람은 이미 줄에 배치된 상태
 * 3. 입력된 위치(왼쪽에 있는 큰 사람의 수)에 현재 사람을 삽입
 * 4. 키가 작은 사람을 나중에 삽입하므로, 이미 배치된 큰 사람들의 위치가 자동으로 조정됨
 *
 * 예시 (N=4, 입력: [2, 1, 1, 0]):
 * - 4번(가장 큰 사람): 왼쪽에 큰 사람 0명 → [4]
 * - 3번: 왼쪽에 큰 사람 1명 → [4, 3]
 * - 2번: 왼쪽에 큰 사람 1명 → [4, 2, 3]
 * - 1번: 왼쪽에 큰 사람 2명 → [4, 2, 1, 3]
 *
 * 시간복잡도: O(N²)
 * - N명의 사람을 처리: O(N)
 * - 각 삽입 연산: 최악의 경우 O(N) (ArrayList의 중간 삽입)
 * - 전체: O(N) × O(N) = O(N²)
 *
 * 공간복잡도: O(N)
 * - 입력 배열: O(N)
 * - 결과 리스트: O(N)
 *
 * 왜 역순으로 처리하는가?
 * - 키가 큰 사람부터 배치하면, 작은 사람 삽입 시 큰 사람들의 상대적 위치만 고려하면 됨
 * - 정순으로 처리하면 작은 사람 배치 후 큰 사람이 삽입될 때 위치 계산이 복잡해짐
 */

public class boj1138 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] inputInfo = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            inputInfo[i] = Integer.parseInt(st.nextToken());
        }

        List<Integer> line = new ArrayList<>();

        for (int currentHeight = N; currentHeight >= 1; currentHeight--) {
            int position = inputInfo[currentHeight];

            line.add(position, currentHeight);
        }

        StringBuilder sb = new StringBuilder();
        for (int height : line) {
            sb.append(height).append(" ");
        }

        System.out.println(sb.toString().trim());
    }
}
