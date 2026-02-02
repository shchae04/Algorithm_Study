package study.boj.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class boj28086 {
    // 어려웠다.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String exp = br.readLine().trim();

        int opIdx = -1; // 연산자가 위치한 인덱스를 저장할 변수
        char op = ' ';  // 연산자 기호(+, -, *, /)를 저장할 변수

        // 문자열을 순회하며 연산자 위치 찾기
        // *중요*: i를 0이 아닌 1부터 시작하는 이유
        // 만약 입력이 "-5+3"처럼 첫 번째 숫자가 음수일 경우, 
        // 0번 인덱스의 '-'는 연산자가 아니라 음수 부호입니다. 이를 건너뛰기 위함
        for (int i = 1; i < exp.length(); i++) {
            char c = exp.charAt(i);
            // 현재 문자가 사칙연산 기호인지 확인
            if (c == '+' || c == '-' || c == '*' || c == '/') {
                opIdx = i; // 연산자 위치 기록
                op = c;    // 연산자 종류 기록
                break;     // 연산자를 찾았으므로 더 이상 반복할 필요 없음
            }
        }

        // 연산자 인덱스를 기준으로 문자열을 두 부분(A, B)으로 나눔
        String aStr = exp.substring(0, opIdx);    // 처음부터 연산자 바로 앞까지 (첫 번째 숫자)
        String bStr = exp.substring(opIdx + 1);   // 연산자 바로 다음부터 끝까지 (두 번째 숫자)
        
        // 문자열을 8진수(Radix 8) 정수로 변환하여 BigInteger 객체로 생성
        // (int나 long 범위를 넘어갈 수 있으므로 BigInteger 사용)
        BigInteger a = new BigInteger(aStr, 8);
        BigInteger b = new BigInteger(bStr, 8);

        // 예외 처리: 0으로 나누는 경우
        // 연산자가 '/'이고 나누는 수(b)가 0이면 계산할 수 없으므로 "invalid" 출력
        if (op == '/' && b.equals(BigInteger.ZERO)) {
            System.out.println("invalid");
            return; // 프로그램 종료
        }

        BigInteger result;
        // 연산자에 따라 계산 수행
        switch (op) {
            case '+': result = a.add(b); break;      // 덧셈
            case '-': result = a.subtract(b); break; // 뺄셈
            case '*': result = a.multiply(b); break; // 곱셈
            case '/': result = floorDiv(a, b); break; // 나눗셈 (직접 구현한 바닥 나눗셈 사용)
            default: result = BigInteger.ZERO; // 도달할 일 없는 기본값
        }

        // 계산된 결과(BigInteger)를 다시 8진수 문자열로 변환하여 출력
        System.out.println(result.toString(8));
    }

    /**
     * 바닥 나눗셈(Floor Division)을 구현한 메소드
     * 일반적인 정수 나눗셈은 0 방향으로 소수점을 버리지만(Truncate),
     * 바닥 나눗셈은 무조건 더 작은 수(수직선상에서 왼쪽) 방향으로 내림을 합니다.
     * 예: -5 / 2 -> 일반 나눗셈은 -2, 바닥 나눗셈은 -3
     */
    private static BigInteger floorDiv(BigInteger a, BigInteger b) {
        // divideAndRemainder: 몫[0]과 나머지[1]를 배열로 반환하는 메소드
        BigInteger[] dr = a.divideAndRemainder(b);
        BigInteger q = dr[0]; // 몫
        BigInteger r = dr[1]; // 나머지

        // 몫을 1 줄여야 하는 조건 확인 (내림 보정):
        // 1. 결과가 음수여야 함 (a와 b의 부호가 서로 다를 때: a.signum() * b.signum() < 0)
        // 2. 나누어 떨어지지 않음 (나머지가 0이 아닐 때: !r.equals(BigInteger.ZERO))
        // 이 두 조건이 맞다면 일반 나눗셈 결과보다 1 더 작은 수가 되어야 함
        if (a.signum() * b.signum() < 0 && !r.equals(BigInteger.ZERO)) {
            q = q.subtract(BigInteger.ONE);
        }
        return q;
    }
}