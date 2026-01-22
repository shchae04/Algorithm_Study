package study.boj.bruteforce;
import java.util.Arrays;
import java.util.Scanner;

public class boj2309 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] arr = new int[9];
        int total = 0;

        for (int i = 0; i < arr.length; i++) {
            arr[i] = sc.nextInt();
            total += arr[i];
        }

        for(int i=0; i<arr.length; i++){
            for(int j=i+1; j<arr.length; j++){
                if (total - arr[i] - arr[j] == 100) {
                    arr[i] = arr[j] = -999;
                    break;
                }
            }
        }
        Arrays.sort(arr);
        for(int i=2; i<arr.length; i++){
            System.out.println(arr[i]);
        }
    }
}
