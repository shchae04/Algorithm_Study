package study.boj.numbertheory;

import java.io.*;
import java.util.*;

public class boj24620 {
    // Sleeping in class
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] arr = new int[N];
            int totalSum = 0;
            int maxVal = 0;

            // Step 1: 전체 합(totalSum)과 최댓값(maxVal) 구하기
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
                totalSum += arr[i];
                maxVal = Math.max(maxVal, arr[i]);
            }

            if (totalSum == 0) {
                sb.append(0).append('\n');
                continue;
            }

            // Step 2: "몇 덩어리(groups)"로 나눌지 결정 (N부터 1까지 역순 탐색)
            // 덩어리가 많을수록(groups가 클수록) 합치는 횟수(N - groups)는 줄어듦 -> 최소 횟수를 위해 큰 수부터 확인
            for (int groups = N; groups >= 1; groups--) {
                // Step 3: 전체 합이 덩어리 개수로 나누어 떨어지는지 확인
                if (totalSum % groups != 0) {
                    continue;
                }

                int targetSum = totalSum / groups; // 한 덩어리가 가져야 할 목표 크기

                // (최적화) 목표 크기가 배열의 원소 최댓값보다 작으면 불가능 (큰 놈은 쪼갤 수 없으므로)
                if (targetSum < maxVal) {
                    continue;
                }

                // Step 4: 실제 배열을 순회하며 목표 크기(targetSum)로 딱딱 나누어 떨어지는지 검증
                if (canPartition(arr, targetSum)) {
                    // 성공했다면 이때가 최소 연산 횟수임
                    sb.append(N - groups).append('\n');
                    break;
                }
            }
        }
        System.out.print(sb);
    }

    private static boolean canPartition(int[] arr, int targetSum) {
        int currentSum = 0;
        for (int val : arr) {
            currentSum += val;
            if (currentSum > targetSum) {
                return false;
            }
            if (currentSum == targetSum) {
                currentSum = 0;
            }
        }
        return currentSum == 0;
    }
}
