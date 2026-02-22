package study.boj.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 백준 3448 - 문자 인식
 * 
 * 주어진 텍스트 인식 결과에서 '#' 문자를 제외한 실제 인식된 문자의 비율(인식률)을 계산하는 프로그램입니다.
 * - '#' 문자는 인식되지 않은 문자를 의미합니다.
 * - 인식률 = (인식된 문자 수 / 전체 문자 수) * 100
 * - 결과는 소수점 둘째 자리에서 반올림하여 첫째 자리까지 출력하며, 소수점 아래가 0이면 정수 부분만 출력합니다.
 */
public class boj3448 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 테스트 케이스 개수 N 읽기
        String firstLine = br.readLine();
        if (firstLine == null) return;
        int n = Integer.parseInt(firstLine);

        for (int tc = 0; tc < n; tc++) {
            long totalChars = 0;      // 전체 문자 수 (A)
            long recognizedChars = 0; // 인식된 문자 수 (R)

            String line;

            // 1. 테스트 케이스 시작 전의 빈 줄 건너뛰기 및 첫 줄 읽기
            while ((line = br.readLine()) != null && line.isEmpty()) {
                // skip empty lines
            }

            // 2. 테스트 케이스 내용 읽기 (빈 줄이 나오거나 입력이 끝날 때까지)
            while (line != null && !line.isEmpty()) {
                totalChars += line.length();
                for (int i = 0; i < line.length(); i++) {
                    // '#'이 아닌 문자가 인식된 문자임
                    if (line.charAt(i) != '#') {
                        recognizedChars++;
                    }
                }
                // 다음 줄 읽기
                line = br.readLine();
            }

            // 0으로 나누기 방지
            if (totalChars == 0) continue;

            // 3. 인식률 계산 및 반올림 처리
            // (recognizedChars / totalChars) * 100 * 10 을 정수로 계산하여 반올림 처리
            // 반올림을 위해 (R * 1000 + A / 2) / A 공식을 사용하여 소수점 첫째 자리까지의 값을 정수화(tenths) 함
            long tenths = (1000L * recognizedChars + totalChars / 2) / totalChars;

            // 4. 결과 출력 형식에 맞춰 출력 (소수점 아래가 0이면 정수만, 아니면 소수점 첫째 자리까지)
            if (tenths % 10 == 0) {
                System.out.printf("Efficiency ratio is %d%%.%n", tenths / 10);
            } else {
                System.out.printf("Efficiency ratio is %d.%d%%.%n", tenths / 10, tenths % 10);
            }
        }
    }
}
