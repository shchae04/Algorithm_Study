package study.boj.daily;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Tawla
 */
public class boj11800 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "Yakk");
        map.put(2, "Doh");
        map.put(3, "Seh");
        map.put(4, "Ghar");
        map.put(5, "Bang");
        map.put(6, "Sheesh");

        List<String> list = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (a == b) {
                switch (a) {
                    case 1: list.add("Habb Yakk"); break;
                    case 2: list.add("Dobara"); break;
                    case 3: list.add("Dousa"); break;
                    case 4: list.add("Dorgy"); break;
                    case 5: list.add("Dabash"); break;
                    case 6: list.add("Dosh"); break;
                }
            } else if ((a == 6 && b == 5) || (a == 5 && b == 6)) {
                list.add("Sheesh Beesh");
            } else {
                if (a > b) {
                    list.add(map.get(a) + " " + map.get(b));
                } else {
                    list.add(map.get(b) + " " + map.get(a));
                }
            }
        }

        for (int i=0; i<list.size(); i++) {
            bw.write("Case " + (i + 1) + ": " + list.get(i));
            bw.newLine();
        };

        bw.flush();
        bw.close();

    }
}