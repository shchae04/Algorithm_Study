package study.boj.string;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 5363번 - 요다
 *
 * 문제:
 * - 스타워즈의 요다처럼 문장의 앞 두 단어를 문장의 맨 뒤로 옮겨서 출력하는 문제
 * - 예: "I will go now" -> "go now I will"
 *
 * 알고리즘: 문자열 처리(String Manipulation) / 구현(Implementation)
 * - 입력받은 문장을 단어 단위로 분리(Tokenizing)
 * - 앞의 두 단어를 따로 저장해두고, 나머지 단어들을 먼저 출력
 * - 마지막에 따로 저장해둔 두 단어를 이어 붙임
 *
 * 핵심 아이디어:
 * 1. StringTokenizer를 사용하여 공백을 기준으로 단어를 분리한다.
 * 2. 첫 번째(first)와 두 번째(second) 토큰을 변수에 저장한다.
 * 3. 세 번째 토큰부터 끝까지 순서대로 StringBuilder에 추가한다.
 * 4. 마지막으로 저장해둔 first와 second를 StringBuilder에 추가한다.
 *
 * 시간복잡도: O(N)
 * - 각 문장의 단어 수만큼 순회하므로 전체 문자의 길이에 비례한다.
 *
 * 공간복잡도: O(N)
 * - 결과를 저장할 StringBuilder와 각 단어를 저장할 공간이 필요하다.
 */
public class boj5363 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int count = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < count; i++) {
            String input = br.readLine();

            StringTokenizer st = new StringTokenizer(input, " ");
            int cnt = st.countTokens();

            if (cnt < 2) {
                sb.append(input).append("\n");
                continue;
            }

            String first = st.nextToken();
            String second = st.nextToken();

            while (st.hasMoreTokens()) {
                sb.append(st.nextToken()).append(" ");
            }

            sb.append(first).append(" ").append(second).append("\n");
        }

        if (sb.length() > 0) {
            System.out.println(sb.toString().trim());
        }

    }
}
