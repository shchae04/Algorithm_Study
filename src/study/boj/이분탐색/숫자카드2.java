
package study.boj.이분탐색;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 숫자카드2 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int n, m;
    static int arr[];

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < n; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(arr);

        m = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int i = 0 ; i < m; i++) {
            int num = Integer.parseInt(st.nextToken());
            int count = upperBound(arr, num) - lowerBound(arr, num);
            sb.append(count).append(" ");
        }

        bw.write(sb.toString());
        bw.flush();

    }

    public static int upperBound(int[] arr, int target){
        int left = 0;
        int right = arr.length;
        while(left<right){
            int mid = (left+right)/2;
            if(target < arr[mid]){
                right = mid;
            }else left=mid + 1;
        }
        return left;
    }
    public static int lowerBound(int[] arr, int target){
        int left = 0;
        int right = arr.length;
        while(left<right){
            int mid = (left+right)/2;
            if(arr[mid]>=target){
                right = mid;
            }else left = mid + 1;
        }
        return left;
    }
}