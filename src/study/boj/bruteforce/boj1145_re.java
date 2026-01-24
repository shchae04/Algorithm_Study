package study.boj.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj1145_re {
    public static void main(String[] args) throws IOException {
        // 다섯개의 자연수. 적어도 대부분의 배수는 위의 수 중 적어도 3개로 나누어지는 가장 작은 자연수
        // 적어도 대부분의 배수를 출력하는 프로그램.

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] nums = new int[5];
        for (int i = 0; i < 5; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums);
        // 정답은 적어도 3번째로 작은 수(nums[2]) 이상이어야 하므로 시작점 최적화
        int start = nums[2];

        for (int i = start; i <= Integer.MAX_VALUE; i++) {
            int count = 0;
            for (int j = 0; j < 5; j++) {
                if (i % nums[j] == 0) {
                    count++;
                    if (count >= 3) {
                        System.out.println(i);
                        return;
                    }
                }
            }
        }
    }
}
