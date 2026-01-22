package study.boj.math;
import java.util.Scanner;

public class boj1977 {

    public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();

    //15 -> 2
        int count = 0;

        for (int i = 1; i <= 500; i++) {
            for(int j =1; j<=500; j++) {
                if(i>=j && i*i - j*j == n) {
                    count++;
                }
            }
        }

        System.out.println(count);

    }
}
