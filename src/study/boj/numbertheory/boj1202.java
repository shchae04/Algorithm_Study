package study.boj.numbertheory;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 백준 1202번 - 보석 도둑
 *
 * 문제: 가방에 담을 보석을 선택해 최대 가치를 만드는 문제
 *
 * 알고리즘: 정렬 + 우선순위 큐(그리디)
 * 시간복잡도: O(N log N)
 * 공간복잡도: O(N)
 */

public class boj1202 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        long k = sc.nextLong(); //무게

        // 보석의 무게 > 1

        // 15
        // 보석의 개수 2
        // 보석의 무게 3 5

        //15 -> 3 * 5  2개 이면서 3*5

        int prd = 2;
        int cnt = 0;
        //2부터.. k와 나누어 떨어지는 수를 출력. 제곱근까지..
        List<String> list = new ArrayList<>();
        while (k != 1) {
            if (prd >= 1000000) { //12/2 6개
                list.add(k + "");
                cnt++;
                break;
            }
            if (k % prd == 0) {
                k = k / prd;
                cnt++;
                list.add(prd + "");
            } else {
                prd++;
            }
        }

        System.out.println(cnt);
        for (String s : list) {
            System.out.print(s + " ");
        }
    }
}
