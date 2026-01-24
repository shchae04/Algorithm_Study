package study.boj.implementation;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class boj35164 {
    public static void main(String[] args) throws IOException {
        // 빠른 입출력을 위한 BufferedReader와 StringBuilder 사용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;

        StringTokenizer st = new StringTokenizer(line);
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] results = new int[M];
        for (int i = 0; i < M; i++) {
            results[i] = Integer.parseInt(br.readLine());
        }

        List<String> querySequence = new ArrayList<>();
        int intervalsCreated = 0;
        int connectedIdx = -1;    // 1번 구간과 연결된 구간의 인덱스
        int disconnectedIdx = -1; // 1번 구간과 떨어져 있는 구간의 인덱스

        // 1. 기준이 될 첫 번째 구간 생성 (범위: 0 ~ 1000)
        querySequence.add("1 0 1000");
        intervalsCreated = 1;

        boolean isPossible = true;
        for (int r : results) {
            if (r == 1) { // 연결된 상태여야 함
                if (connectedIdx != -1) {
                    // 이미 연결된 쌍이 있다면 그대로 사용
                    querySequence.add("2 1 " + connectedIdx);
                } else if (disconnectedIdx != -1) {
                    // 떨어진 구간이 있다면, 1번 쿼리를 하나 써서 '다리' 구간을 만들어 연결
                    if (intervalsCreated < N) {
                        intervalsCreated++;
                        // 다리 구간: 기준점(500)부터 떨어진 구간의 중간 지점까지 연결
                        long bridgeEnd = (long)disconnectedIdx * 10000L + 500;
                        querySequence.add("1 500 " + bridgeEnd);
                        connectedIdx = disconnectedIdx;
                        disconnectedIdx = -1;
                        querySequence.add("2 1 " + connectedIdx);
                    } else {
                        isPossible = false;
                        break;
                    }
                } else {
                    // 아무 구간도 없다면 새로 겹치는 구간 생성
                    if (intervalsCreated < N) {
                        intervalsCreated++;
                        querySequence.add("1 0 1000"); // 1번 구간과 동일한 범위
                        connectedIdx = intervalsCreated;
                        querySequence.add("2 1 " + connectedIdx);
                    } else {
                        isPossible = false;
                        break;
                    }
                }
            } else { // r == 0, 연결되지 않은 상태여야 함
                if (disconnectedIdx != -1) {
                    // 이미 떨어진 구간이 있다면 사용
                    querySequence.add("2 1 " + disconnectedIdx);
                } else {
                    // 새로운 멀리 떨어진 구간 생성
                    if (intervalsCreated < N) {
                        intervalsCreated++;
                        long start = (long)intervalsCreated * 10000L;
                        long end = start + 1000;
                        querySequence.add("1 " + start + " " + end);
                        disconnectedIdx = intervalsCreated;
                        querySequence.add("2 1 " + disconnectedIdx);
                    } else {
                        isPossible = false;
                        break;
                    }
                }
            }
        }

        // 결과 출력
        if (!isPossible) {
            System.out.println("-1");
        } else {
            // 아직 사용하지 않은 남은 1번 쿼리들을 채워줌
            while (intervalsCreated < N) {
                intervalsCreated++;
                long start = (long)intervalsCreated * 10000L;
                long end = start + 1000;
                querySequence.add("1 " + start + " " + end);
            }

            StringBuilder sb = new StringBuilder();
            sb.append("1\n"); // 가능함을 알리는 1 출력
            for (String q : querySequence) {
                sb.append(q).append("\n");
            }
            System.out.print(sb);
        }
    }
}