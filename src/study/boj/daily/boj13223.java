package study.boj.daily;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 백준 13223번 - 소금 폭탄
 * <p>
 * 문제: 현재 시각과 로봇이 소금을 투하할 시각이 주어졌을 때, 소금 투하까지 남은 시간을 구하기
 * - 시간은 HH:MM:SS 형식으로 주어짐
 * - 하루는 24시간이며, 투하 시간이 현재 시간보다 빠르다면 다음 날을 의미함
 * - 최소 1초 이상 기다려야 함 (현재 시간 == 투하 시간인 경우 24시간을 기다려야 함)
 * <p>
 * 알고리즘: 수학(Math), 문자열 파싱(String Parsing), 구현(Implementation)
 * - 시간을 "초(Second)" 단위로 변환하여 계산
 * <p>
 * 핵심 아이디어:
 * 1. 단위 통일: HH:MM:SS 형식을 초 단위의 정수로 변환
 * - totalSeconds = h * 3600 + m * 60 + s
 * 2. 시간 차이 계산:
 * - needSecond = dropSecond - currentSecond
 * 3. 예외 처리 (하루 경과):
 * - 만약 needSecond <= 0 이라면, 투하 시간이 다음 날이므로 24시간(86400초)을 더해줌
 * - 문제 조건상 "현재 시간 == 투하 시간"인 경우에도 24시간을 대기해야 하므로 <= 0 조건이 적절함
 * 4. 결과 변환:
 * - 남은 초를 다시 HH:MM:SS 형식으로 변환하여 출력
 * <p>
 * 시간복잡도: O(1)
 * - 입력 문자열의 길이가 고정적이고, 단순 사칙연산만 수행하므로 상수 시간 소요
 * <p>
 * 공간복잡도: O(1)
 * - 별도의 추가 공간 없이 몇 개의 정수 변수만 사용
 */
public class boj13223 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] t1 = br.readLine().split(":");
        int currentSecond = Integer.parseInt(t1[0]) * 3600 + Integer.parseInt(t1[1]) * 60 + Integer.parseInt(t1[2]);

        String[] t2 = br.readLine().split(":");
        int dropSecond = Integer.parseInt(t2[0]) * 3600 + Integer.parseInt(t2[1]) * 60 + Integer.parseInt(t2[2]);

        int needSecond = dropSecond - currentSecond;
        if (needSecond <= 0) {
            needSecond += 24 * 3600;
        }

        int h = needSecond / 3600;
        int m = (needSecond % 3600) / 60;
        int s = needSecond % 60;

        System.out.printf("%02d:%02d:%02d\n", h, m, s);
    }
}
