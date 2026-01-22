package study.boj.twopointer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class boj22988 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        long x = Long.parseLong(st.nextToken());

        long min = (x + 1) / 2;

        List<Long> containers = new ArrayList<>();
        int fullContainers = 0;

        st = new StringTokenizer(br.readLine());
        for (int i=0; i<n; i++) {
            long capa = Long.parseLong(st.nextToken());
            if (capa >= x) {
                fullContainers++;
            } else {
                containers.add(capa);
            }
        }
        Collections.sort(containers);

        int left = 0;
        int right = containers.size() - 1;
        int result = fullContainers;
        int unPaired = 0;

        while (left <= right) {
            if (left < right && containers.get(left) + containers.get(right) >= min) {
                result++;
                left++;
                right--;
            } else {
                unPaired++;
                left++;
            }
        }

        result += unPaired / 3;
        System.out.println(result);
    }
}
