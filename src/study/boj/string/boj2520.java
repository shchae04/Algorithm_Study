package study.boj.string;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 2520번 - 팬케이크 사랑
 * 
 * 문제: 주어진 재료로 만들 수 있는 팬케이크의 최대 개수를 구하는 문제
 * - 팬케이크 1개 만들기 위해 필요한 재료:
 *   반죽: 생크림 8ml, 우유 8ml, 달가루 4g, 밀가루 1g, 버터 9g
 *   토핑: 바나나, 딸기, 체리, 호두 중 한 가지
 * 
 * 알고리즘: 수학적 최적화
 * - 반죽 가능 개수 = min(각 재료 / 필요량) * 16
 * - 토핑 가능 개수 = 노말바나나 + (딸기/30) + (체리/25) + (호두/10)
 * - 최종 개수 = min(반죽 개수, 토핑 개수)
 * 
 * 시간복잡도: O(1)
 * 공간복잡도: O(1)
 */
public class boj2520 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫 줄: 테스트 케이스 수
        int T = Integer.parseInt(readNonEmptyLine(br));
        String line;

        StringBuilder sb = new StringBuilder();
        for (int tc = 0; tc < T; tc++) {
            // 각 테스트케이스는 빈 줄로 구분될 수 있으므로 비지 않은 줄을 읽는다.
            String first = readNonEmptyLine(br);
            String second = readNonEmptyLine(br);

            // 첫 줄: cm, y, ssu, ssa, f
            StringTokenizer st1 = new StringTokenizer(first);
            long cm = Long.parseLong(st1.nextToken());
            long y = Long.parseLong(st1.nextToken());
            long ssu = Long.parseLong(st1.nextToken());
            long ssa = Long.parseLong(st1.nextToken());
            long f = Long.parseLong(st1.nextToken());

            // 두 번째 줄: b, gs, gc, w
            StringTokenizer st2 = new StringTokenizer(second);
            long b = Long.parseLong(st2.nextToken());
            long gs = Long.parseLong(st2.nextToken());
            long gc = Long.parseLong(st2.nextToken());
            long w = Long.parseLong(st2.nextToken());

            // 1) 반죽 수 B = floor(16 * min(cm/8, y/8, ssu/4, ssa/1, f/9))
            double x = Math.min(
                    Math.min(cm / 8.0, y / 8.0),
                    Math.min(ssu / 4.0, Math.min(ssa / 1.0, f / 9.0))
            );
            long B = (long) Math.floor(16.0 * x);

            // 2) 토핑으로 만들 수 있는 최대 수 T
            long Toppings = b + (gs / 30) + (gc / 25) + (w / 10);

            // 3) 결과 = min(B, Toppings)
            long ans = Math.min(B, Toppings);

            sb.append(ans).append('\n');
        }

        System.out.print(sb.toString());
    }

    // 빈 줄을 건너뛰고 실제 내용이 있는 줄을 반환
    private static String readNonEmptyLine(BufferedReader br) throws IOException {
        String line;
        while ((line = br.readLine()) != null) {
            if (line.trim().length() > 0) return line.trim();
        }
        return ""; // 입력이 끝나는 경우 (BOJ 환경에서는 보통 도달하지 않음)
    }
}