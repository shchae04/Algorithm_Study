package study.boj.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj2875_re {
    public static void main(String[] args) throws IOException {
        // 2명의 여 + 1명의 남 -> 팀.
        // N명의 여자와 M명의 남자. 팀원 참가.
        // K명은 반드시 인턴쉽 프로그램에 참가해야함. 인턴쉽 참가 인원은. 팀 X
        // 많은 팀


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int team = Math.min(N / 2, M);

        int remain  = (N - team * 2) + (M - team); //rest people
        if (remain < K) {
            int need = K - remain;
            team = team - (need + 2);
        }

        System.out.println(team);
    }
}
