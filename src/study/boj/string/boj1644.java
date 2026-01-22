package study.boj.string;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 백준 1644번 - 소수의 연속합
 *
 * 문제: 하나 이상의 연속된 소수의 합으로 나타낼 수 있는 자연수 N을 만드는 경우의 수 구하기
 * 예시: N=41의 경우 다음 3가지 방법이 있음
 * - 41 (소수 하나)
 * - 2 + 3 + 5 + 7 + 11 + 13 (연속된 소수 6개)
 * - 11 + 13 + 17 (연속된 소수 3개)
 *
 * 알고리즘: 에라토스테네스의 체 + 투 포인터 (슬라이딩 윈도우)
 * 1. 에라토스테네스의 체로 N 이하의 모든 소수 생성
 *    - 최적화: 홀수만 확인하고, 2는 별도 처리
 *    - 합성수 배제를 위한 조기 종료 조건 적용
 *
 * 2. 투 포인터 기법으로 연속 소수합 탐색
 *    - 앞 포인터: 구간 시작점 (for문의 primeNumber)
 *    - 뒤 포인터: 구간 끝점 (tailIndex)
 *    - 현재 합이 목표값보다 작으면 뒤 포인터 증가
 *    - 현재 합이 목표값과 같으면 경우의 수 증가
 *    - 앞 포인터 이동 시 해당 소수 제거
 *
 * 핵심 구현 포인트:
 * - 소수 생성: 2를 먼저 저장하고 홀수만 순회
 * - 최적화: prevPrimeNumber + i > number 조건으로 불필요한 연산 방지
 * - 마지막 홀수 처리: N이 홀수 소수인 경우 별도 추가
 * - 투 포인터: 구간합이 작을 때만 확장, 같을 때 카운트 후 축소
 *
 * 시간복잡도: O(N log log N + P) - N: 입력값, P: N 이하 소수 개수
 *            에라토스테네스의 체 O(N log log N) + 투 포인터 O(P)
 * 공간복잡도: O(N) - 소수 판별 배열과 소수 저장 배열
 */
public class boj1644 {
    static int[] primeNumbers = new int[400000];
    static int numOfPrimeNumbers = 0;
    static int number;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        number = Integer.parseInt(reader.readLine());
        findPrimeNumbers();
        System.out.print(calcNumOfCases());
    }

    public static int calcNumOfCases() {
        int numOfCases = 0;
        int tailIndex = 0;
        int sumOfPrimeNumbers = 0;

        loop:
        for (int primeNumber : primeNumbers) {
            while (sumOfPrimeNumbers < number) {
                if (tailIndex == numOfPrimeNumbers)
                    break loop;
                sumOfPrimeNumbers += primeNumbers[tailIndex++];
            }
            if (sumOfPrimeNumbers == number)
                numOfCases++;
            sumOfPrimeNumbers -= primeNumber;
        }
        return numOfCases;
    }

    public static void findPrimeNumbers() {
        boolean[] isNoPrime = new boolean[number + 1];
        int prevPrimeNumber = primeNumbers[numOfPrimeNumbers++] = 2;

        for (int i = 3; i <= number; i += 2) {
            if (isNoPrime[i])
                continue;
            if (prevPrimeNumber + i > number)
                break;
            primeNumbers[numOfPrimeNumbers++] = prevPrimeNumber = i;
            for (int j = i * 2; j <= number; j += i)
                isNoPrime[j] = true;
        }
        if (number > 2 && number % 2 != 0 && !isNoPrime[number])
            primeNumbers[numOfPrimeNumbers++] = number;
    }
}
