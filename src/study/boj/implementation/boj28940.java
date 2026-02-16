package study.boj.implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * 백준 28940
 * 한 페이지에 들어가는 글자 수를 계산한 뒤,
 * 전체 글자 수를 채우는 최소 페이지 수를 올림 나눗셈으로 구한다.
 */
public class boj28940 {

    public static void main(String[] args) throws IOException {
        // 빠른 입력을 위해 BufferedReader 사용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int w = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        // 1. 예외 케이스 처리 (글자가 종이보다 크면 불가능)
        if (a > w || b > h) {
            System.out.println(-1);
            return;
        }

        // 2. 한 페이지당 수용량 계산: (가로로 들어가는 개수) * (세로로 들어가는 개수)
        //    w / a, h / b 는 정수 나눗셈이므로 자동으로 floor 처리된다.
        int perPage = (w / a) * (h / b);

        // 3. 필요한 페이지 수 계산
        //    ceil(n / perPage) == (n + perPage - 1) / perPage
        int result = (n + perPage - 1) / perPage;

        System.out.println(result);
    }
}
