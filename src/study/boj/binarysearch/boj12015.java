package study.boj.binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj12015 {
    public static void main(String[] args) throws IOException {
        // LIS (Longest Increasing Subsequence)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        ArrayList<Integer> list = new ArrayList<>();
        list.add(arr[0]);

        for (int i=1; i<N; i++) {
            int key = arr[i];
            // 현재 원소가 리스트의 마지막 원소보다 크면 LIS 길이를 확장
            if (key > list.get(list.size() - 1)) {
                list.add(key);
            } else {
                // 현재 원소가 들어갈 수 있는 위치를 이분 탐색(Lower Bound)으로 찾음
                // list[i]는 길이가 i+1인 증가 부분 수열 중 가장 작은 마지막 원소 값을 유지함
                int low = 0;
                int high = list.size() - 1;
                while (low < high) {
                    int mid = (low + high) / 2;
                    if (list.get(mid) < key) {
                        low = mid + 1;
                    } else {
                        high = mid;
                    }
                }
                list.set(low, key);
            }
        }
        System.out.println(list.size());

    }
}
