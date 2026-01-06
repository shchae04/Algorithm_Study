package study.boj.daily;

import java.io.*;
import java.util.*;

/**
 * 백준 1141번 - 접두사
 *
 * 문제: 단어 N개로 이루어진 집합에서, 어떤 단어도 다른 단어의 접두사가 되지 않는 부분 집합을 만들 때
 *       그 부분 집합의 최대 크기를 구하는 문제.
 *       (예: "run", "running"이 있으면 둘 다 포함할 수 없음)
 *
 * 알고리즘: 정렬, 문자열 처리, 그리디
 *
 * 핵심 아이디어:
 * 1. 사전순 정렬:
 *    - 단어를 사전순으로 정렬하면 접두사 관계인 단어들이 인접하게 배치됨.
 *
 * 2. 접두사 관계 확인:
 *    - 어떤 단어 A가 B의 접두사라면, A를 집합에서 제외해야 부분 집합의 크기를 최대로 유지하면서 조건을 만족하기 쉬움.
 *    - (A를 포함하면 B를 포함할 수 없지만, B를 포함하면 A만 포기하면 됨. 보통 긴 단어를 남기는 게 유리하거나 동등함)
 *    - 정렬된 상태에서 현재 단어(words[i])가 바로 다음 단어(words[i+1])의 접두사인지 확인하면 됨.
 *    - 만약 현재 단어가 다음 단어의 접두사라면, 현재 단어는 제외함.
 *    - 접두사가 아니라면, 결과 집합에 포함시킴.
 *
 * 시간복잡도: O(N^2 * L) 또는 O(N * L)
 * - 코드에서는 2중 루프를 돌지만, 정렬 후에는 바로 다음 단어만 비교해도 충분하므로 O(N * L)로 최적화 가능.
 * - N <= 50이므로 현재 로직으로도 충분함.
 *
 * 공간복잡도: O(N * L)
 * - 입력을 저장할 배열 필요.
 */
public class boj1141 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String[] words = new String[N];

        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
        }

        // 1. 사전순으로 정렬 (사전순 정렬 시, 접두사 관계인 단어들은 바로 옆에 위치함)
        Arrays.sort(words);

        int count = 0;
        for (int i = 0; i < N; i++) {
            boolean isPrefix = false;

            // 2. 현재 단어가 다음 단어의 접두사인지 확인
            // (이미 정렬되었으므로 i+1번째 이후의 모든 단어를 볼 필요 없이 바로 다음 단어만 체크해도 됨)
            for (int j = i + 1; j < N; j++) {
                if (words[j].startsWith(words[i])) {
                    isPrefix = true;
                    break;
                }
            }

            // 어떤 단어의 접두사도 아니라면 카운트 증가
            if (!isPrefix) {
                count++;
            }
        }

        System.out.println(count);
    }
}