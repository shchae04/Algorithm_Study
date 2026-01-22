package study.boj.string;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 25628번 - 햄버거 만들기
 *
 * 문제: 햄버거 빵 A개와 패티 B개가 있을 때, 만들 수 있는 햄버거의 최대 개수 구하기
 * - 햄버거 1개 구성: 빵 2개 + 패티 1개
 *
 * 알고리즘: 단순 수학 (사칙연산)
 * - 가지고 있는 재료로 최대한 만들 수 있는 개수를 비교해서 정답을 찾음
 *
 * 핵심 아이디어:
 * 1. 햄버거 하나를 만드는데 빵은 2개가 필요함 -> 가진 빵(A)으로 만들 수 있는 햄버거 수 = A / 2
 * 2. 햄버거 하나를 만드는데 패티는 1개가 필요함 -> 가진 패티(B)로 만들 수 있는 햄버거 수 = B
 * 3. 빵으로 만들 수 있는 개수와 패티로 만들 수 있는 개수 중, '더 적은 쪽'이 실제로 만들 수 있는 햄버거의 최대 개수임.
 *    (왜냐하면 재료가 남더라도 짝이 안 맞으면 못 만드니까!)
 *
 * 시간복잡도: O(1)
 * - 그냥 나누기랑 비교만 하면 끝남. 입력 크기와 상관없이 즉시 계산.
 *
 * 공간복잡도: O(1)
 * - 변수 몇 개만 쓰면 됨.
 */
public class boj25628 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // A: 햄버거 빵의 개수 (입력 받기)
        int A = Integer.parseInt(st.nextToken());
        // B: 햄버거 패티의 개수 (입력 받기)
        int B = Integer.parseInt(st.nextToken());

        // 1. 빵으로 만들 수 있는 햄버거 개수 계산
        // 햄버거 1개당 빵 2개가 필요하니까, 가진 빵을 2로 나누면 됨. (자바에서 정수 나눗셈은 몫만 가짐)
        int burgerFromBread = A / 2;

        // 2. 패티로 만들 수 있는 햄버거 개수 계산
        // 햄버거 1개당 패티 1개가 필요하니까, 패티 개수 그대로가 만들 수 있는 개수임.
        int burgerFromPatty = B;

        // 3. 둘 중 더 작은 값이 정답!
        // 예를 들어 빵으로는 100개 만들 수 있어도, 패티가 1개밖에 없으면 햄버거는 1개밖에 못 만듦.
        // 반대로 패티가 100개여도 빵으로 1개밖에 못 만들면 1개뿐임.
        // 그래서 둘 중 작은 값(Math.min)을 선택해야 함.
        System.out.println(Math.min(burgerFromBread, burgerFromPatty));
    }
}
