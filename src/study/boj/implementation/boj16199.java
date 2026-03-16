package study.boj.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj16199 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 생년월일 입력
        int y1= Integer.parseInt(st.nextToken());
        int m1= Integer.parseInt(st.nextToken());
        int d1= Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        // 기준 날짜 입력
        int y2= Integer.parseInt(st.nextToken());
        int m2= Integer.parseInt(st.nextToken());
        int d2= Integer.parseInt(st.nextToken());

        // 기본 나이 = 연도 차이
        int age = y2 - y1;

        // 만 나이 계산: 생일이 지나지 않았으면 -1
        if (m2 < m1 || (m2 == m1 && d2 < d1)) {
            System.out.println(age - 1);
        } else {
            System.out.println(age);
        }

        // 세는 나이: 연 나이 + 1
        System.out.println(age + 1);

        // 연 나이: 연도 차이
        System.out.println(age);

    }
}
