package study.boj.daily;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 백준 1918번 - 후위 표기식
 * 
 * 문제: 중위표기식을 후위표기식으로 변환
 * 
 * 알고리즘: 스택 + 연산자 우선순위
 * - 피연산자는 바로 출력
 * - 연산자는 스택의 top보다 우선순위가 높을 때까지 pop하고 push
 * - 여는 괄호는 스택에 push, 닫는 괄호는 여는 괄호까지 모든 연산자 pop
 * - 마지막에 스택의 모든 연산자 출력
 * 
 * 시간복잡도: O(N)
 * 공간복잡도: O(N)
 */
public class boj1918 {
    public static void main(String[] args) throws IOException {
        // 입력을 받기 위한 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 결과를 저장할 StringBuilder
        StringBuilder sb = new StringBuilder();

        // 중위표기식 입력받기
        String str = br.readLine();

        // 연산자를 저장할 스택
        Stack<Character> stack = new Stack<>();

        // 입력받은 문자열을 한 글자씩 처리
        for (int i = 0; i < str.length(); i++) {
            char n = str.charAt(i);

            switch (n) {
                // 연산자일 경우
                case '+':
                case '-':
                case '*':
                case '/':
                    // 스택이 비어있지 않고, 스택 맨 위의 연산자 우선순위가 현재 연산자보다 크거나 같으면
                    while (!stack.isEmpty() && priority(stack.peek()) >= priority(n)) {
                        sb.append(stack.pop()); // 스택에서 꺼내서 출력
                    }
                    stack.add(n); // 현재 연산자를 스택에 추가
                    break;
                case '(': // 여는 괄호는 스택에 추가
                    stack.add(n);
                    break;
                case ')': // 닫는 괄호를 만나면
                    while (!stack.isEmpty() && stack.peek() != '(') { // 여는 괄호를 만날 때까지
                        sb.append(stack.pop()); // 스택에서 꺼내서 출력
                    }
                    stack.pop(); // 여는 괄호 제거
                    break;
                default: // 피연산자는 바로 출력
                    sb.append(n);
            }
        }
        // 스택에 남아있는 연산자들을 모두 출력
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        // 결과 출력
        System.out.println(sb);
    }

    // 연산자의 우선순위를 반환하는 메서드
    public static int priority(char operator) {
        if (operator == '(' || operator == ')') {
            return 0; // 괄호의 우선순위는 가장 낮음
        } else if (operator == '+' || operator == '-') {
            return 1; // 덧셈과 뺄셈의 우선순위
        } else if (operator == '*' || operator == '/') {
            return 2; // 곱셈과 나눗셈의 우선순위
        }
        return -1; // 해당하지 않는 경우
    }
}