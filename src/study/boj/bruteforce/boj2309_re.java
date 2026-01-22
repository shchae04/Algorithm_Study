package study.boj.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj2309_re {
    /**
     * 난쟁이는 9명이고, 그중 7명의 키 합이 100이 되는 조합을 찾는다.
     * <p>
     * 각 난쟁이의 키는 100을 넘지 않는 자연수. 키가 모두 다르다.
     * 정답이 여러개이면 아무거나 출력
     * <p>
     * 1. 완전탐색(9C2)으로 제외할 2명을 찾는다.
     * 2. total에서 2명을 뺀 값이 100이면 해당 2명을 제외한다.
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = new int[9];
        int total = 0;

        for (int i = 0; i < 9; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            total += arr[i];
        }
        int idx1 = -1;
        int idx2 = -1;

        outer:
        for (int i = 0; i < 9; i++) {
            for (int j = i + 1; j < 9; j++) {
                if (total - arr[i] - arr[j] == 100) {
                    idx1 = i;
                    idx2 = j;
                    break outer;
                }
            }
        }

        int[] result = new int[7];
        int idx = 0;
        for (int i = 0; i < 9; i++) {
            if (i == idx1 || i == idx2) {
                continue;
            }
            result[idx++] = arr[i];
        }
        Arrays.sort(result);
        for (int x : result) System.out.println(x);
    }
}
