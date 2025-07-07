package study.boj.daily;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class boj1235 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String[] arr = new String[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.next();
        }
        int size = arr[0].length();

        // 뒤에서 부터 k개씩 가져와서 다른지 보면 된다.
        // 12345 -> substring(size - k)
        for (int k = 1; k <= size; k++) {
            Set<String> unique = new HashSet<>();
            boolean allUnique = true;
            for (int i = 0; i < n; i++) {
                String suffix = arr[i].substring(size - k);
                if (unique.contains(suffix)) {
                    allUnique = false;
                    break;
                }
                unique.add(suffix);
            }
            if (allUnique) {
                System.out.println(k);
                return;
            }
        }

    }
}
