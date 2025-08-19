package study.boj.daily;

import java.io.*;

public class boj5585 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());

        int remain = 1000 - N;

        int[] money = {500, 100, 50, 10, 5, 1};
        int count = 0;

        for (int i=0; i<money.length; i++) {
            count += remain / money[i];
            remain = remain % money[i];
        }

        bw.write(String.valueOf(count));
        bw.flush();
        bw.close();
        br.close();
    }
}
