package study.one;

import java.util.Scanner;

public class 통학의신 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int A = sc.nextInt();
        int B = sc.nextInt();

        int first = 0; int second = 0;
        for (int x = -1000; x <= 1000; x++) {
            if (x * x + 2 * A * x + B == 0) {
                second = first;
                first = x;
            }
        }

        if (second != 0) {
            System.out.println(second + " " + first);
        }
        else{
            System.out.println(first);
        }
        
    }
}
