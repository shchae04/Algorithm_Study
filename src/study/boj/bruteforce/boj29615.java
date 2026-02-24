package study.boj.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * 백준 29615번 - 알파빌과 베타빌
 *
 * 문제 요약:
 * N명이 줄을 서 있고, 그 중 M명의 친구들이 있다.
 * 앞에서부터 M자리에 친구들이 모두 모이도록 하려면 최소 몇 번의 교환이 필요한가?
 *
 * 핵심 아이디어:
 * - 앞의 M자리에 친구가 아닌 사람이 몇 명 있는지 세면 된다.
 * - 그 수만큼 친구와 교환해야 하므로, 그것이 곧 최소 교환 횟수이다.
 *
 * 시간복잡도: O(N + M)
 */
public class boj29615 {
    public static void main(String[] args) throws IOException {
        // 빠른 입력을 위한 BufferedReader 사용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // N: 대기줄에 서 있는 사람 수
        // M: 친구 수 (앞의 M자리에 모두 모여야 함)
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // arr: 현재 대기줄의 순서 (1번부터 N번까지의 사람들)
        int[] arr = new int[N];

        // friends: 친구들의 번호를 저장하는 HashSet (빠른 검색을 위해)
        HashSet<Integer> friends = new HashSet<>();

        // 현재 대기줄의 순서 입력받기
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 친구들의 번호를 HashSet에 저장
        // O(1) 시간에 특정 번호가 친구인지 확인할 수 있음
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            friends.add(Integer.parseInt(st.nextToken()));
        }

        // 앞의 M자리(0번부터 M-1번까지) 중에서 친구가 아닌 사람의 수를 센다
        // 이 수가 곧 최소 교환 횟수이다
        // 왜냐하면 이 자리들을 친구로 채우기 위해 뒤쪽의 친구들과 교환해야 하기 때문
        int result = 0;
        for (int i = 0; i < M; i++) {
            // i번째 자리에 있는 사람이 친구가 아니면 카운트
            if (!friends.contains(arr[i])) {
                result++;
            }
        }

        // 최소 교환 횟수 출력
        System.out.println(result);
    }
}