package study.boj.daily;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * 백준 1620번 - 나는야 포켓몬 마스터 이다솜
 *
 * 문제:
 * - 포켓몬 도감에 수록된 포켓몬의 개수 N과 맞춰야 하는 문제의 개수 M이 주어짐.
 * - N개의 포켓몬 이름이 번호 순서대로 주어짐 (1번부터 N번까지).
 * - 그 다음 M개의 줄에 문제가 주어지는데,
 *   1. 문제가 숫자로 들어오면 -> 해당 번호의 포켓몬 이름을 출력.
 *   2. 문제가 문자로 들어오면 -> 해당 포켓몬의 번호를 출력.
 *
 * 알고리즘: 자료구조 (해시맵, 배열)
 * - 이름으로 번호를 찾을 때와 번호로 이름을 찾을 때 모두 빠른 속도가 필요함.
 * - 따라서 두 가지 자료구조를 동시에 사용함.
 *
 * 핵심 아이디어:
 * 1. 번호 -> 이름 검색:
 *    - 배열(String[])을 사용하여 인덱스로 바로 접근 (O(1)).
 *    - 입력이 1번부터 시작하므로 크기를 N+1로 잡으면 편함.
 *
 * 2. 이름 -> 번호 검색:
 *    - 해시맵(HashMap<String, Integer>)을 사용하여 이름(Key)으로 번호(Value)를 바로 찾음 (평균 O(1)).
 *
 * 시간복잡도: O(N + M)
 * - 도감 입력받는데 O(N) (배열 저장 + 해시맵 put).
 * - 문제 M개를 처리하는데 각 O(1) (배열 접근 or 해시맵 get)이므로 총 O(M).
 * - 전체적으로 O(N + M). (입출력 비용 제외 시)
 *
 * 공간복잡도: O(N)
 * - 이름 저장용 배열 O(N) + 해시맵 O(N).
 */
public class boj1620 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 도감에 수록된 포켓몬 개수
        int M = Integer.parseInt(st.nextToken()); // 맞춰야 하는 문제 개수

        // 번호로 이름을 찾기 위한 배열 (1번부터 시작하므로 N+1 크기 할당)
        String[] idxToName = new String[N + 1];
        // 이름으로 번호를 찾기 위한 해시맵
        HashMap<String, Integer> nameToIdx = new HashMap<>();

        // 도감 정보 입력
        for (int i=1; i<=N; i++) {
            String name = br.readLine();
            idxToName[i] = name;      // 배열에 저장 (번호 -> 이름)
            nameToIdx.put(name, i);   // 맵에 저장 (이름 -> 번호)
        }

        StringBuilder sb = new StringBuilder();
        // 문제 풀이
        for (int i=0; i<M; i++) {
            String query = br.readLine();

            // 입력받은 문자열의 첫 글자가 숫자인지 확인
            if (Character.isDigit(query.charAt(0))) {
                // 숫자라면: 인덱스로 이름을 찾아서 출력
                int idx = Integer.parseInt(query);
                sb.append(idxToName[idx]).append("\n");
            } else {
                // 문자라면: 맵에서 이름으로 번호를 찾아서 출력
                sb.append(nameToIdx.get(query)).append("\n");
            }
        }

        System.out.println(sb);
    }
}