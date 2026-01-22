package study.boj.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj17945_re {
    /**
     * x^2 + 2Ax + B 의 계수 A, B 정수 (-1000 <=A,B <= 1000)
     * 첫번째 줄에 방정식의 근들을 모두 공백으로 분리해 오름차순으로 출력. 중근 -> 1개 출력
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        int[] result = new int[2];
        int count = 0;

        // -1000 ~ 1000 이면 x값만 돌려주면 되겠다.
        for (int x = -1000; x <= 1000; x++) {
            if (x * x + 2 * A * x + B == 0) {
                result[count++] = x;
            }
        }

        // 해를 뒤지기 때문에 result 배열에는 이미 작은것부터 담긴다.
        if (count == 1) {
            System.out.println(result[0]);
        } else {
            System.out.println(result[0] + " " + result[1]);
        }


    }
}
