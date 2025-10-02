package study.boj.daily;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 백준 13719번 - 정렬된 연속한 부분수열의 합
 *
 * 문제: N개의 정수가 주어질 때, 각 단계마다 누적 XOR 값을 구하고 최종 결과를 출력
 *
 * 알고리즘: 비트 연산을 이용한 XOR 누적 계산
 * - 각 숫자를 비트 단위로 분해하여 처리
 * - XOR 연산의 특성을 활용하여 각 비트 위치별로 1이 등장한 횟수를 추적
 * - 누적된 XOR 값을 효율적으로 계산
 *
 * 핵심 아이디어:
 * 1. 각 숫자를 이진수로 변환하여 비트 배열로 저장
 * 2. 이전 단계의 XOR 결과와 현재 숫자를 비트별로 XOR 연산
 * 3. 각 비트 위치에서 1이 등장한 횟수를 추적하여 최종 결과 계산
 * 4. XOR 연산 특성: a XOR a = 0, a XOR 0 = a
 *
 * 시간복잡도: O(N * log(MAX)) - N은 숫자 개수, MAX는 숫자의 최댓값
 * - 각 숫자를 비트로 변환: O(log(MAX))
 * - N개의 숫자를 처리: O(N)
 * - 각 비트별 연산: O(log(MAX))
 *
 * 공간복잡도: O(log(MAX)) - 비트 배열 저장 공간
 * - 최대 비트 길이만큼의 리스트 사용
 */

public class boj13719 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int first = Integer.parseInt(st.nextToken());
        long result = first;
        List<Long> pre = getBit(first);

        for (int i = 2; i <= n; i++) {
            int num = Integer.parseInt(st.nextToken());
            List<Long> binary = getBit(num);
            List<Long> nxtPre = getNxtPre(pre, binary, i);

            long mul = 1L;
            for (Long count : nxtPre) {
                result += count * mul;
                mul <<= 1;
            }
            pre = nxtPre;
        }

        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
        br.close();
    }

    private static List<Long> getNxtPre(List<Long> pre, List<Long> binary, int idx) {
        List<Long> nxtPre = new ArrayList<>();
        int preSize = pre.size();
        int binarySize = binary.size();
        int minSize = Math.min(preSize, binarySize);

        for (int j = 0; j < minSize; j++) {
            nxtPre.add(binary.get(j) == 1 ? idx - pre.get(j) : pre.get(j));
        }

        if (preSize > binarySize) {
            nxtPre.addAll(pre.subList(binarySize, preSize));
        } else {
            for (int j = preSize; j < binarySize; j++) {
                nxtPre.add(binary.get(j) == 1 ? (long) idx : 0L);
            }
        }

        return nxtPre;
    }

    private static List<Long> getBit(long num) {
        List<Long> bit = new ArrayList<>();
        while (num > 0) {
            bit.add(num & 1);
            num >>= 1;
        }
        return bit;
    }
}