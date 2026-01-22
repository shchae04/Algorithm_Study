package study.boj.string;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 백준 11800번 - Tawla
 * 
 * 문제: 중동의 주사위 게임 Tawla의 규칙에 따라 두 주사위 결과를 영어로 출력
 * - 주사위 눈: 1(Yakk), 2(Doh), 3(Seh), 4(Ghar), 5(Bang), 6(Sheesh)
 * - 두 눈이 같을 때 특별한 이름 사용
 * - (5,6) 또는 (6,5)일 때 특별히 "Sheesh Beesh"
 * - 일반적인 경우 큰 수부터 먼저 출력
 * 
 * 알고리즘: 조건분기 + 해시맵
 * - 주사위 눈에 대응하는 영어 이름을 HashMap으로 매핑
 * - 동일한 눈인지, 특수 조합인지, 일반 조합인지 순차적으로 검사
 * - 일반 조합은 큰 수가 먼저 오도록 정렬
 * 
 * 시간복잡도: O(N) - N: 테스트 케이스 수
 * 공간복잡도: O(1)
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