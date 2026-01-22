package study.boj.string;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 13458번 - 시험 감독
 * 
 * 문제: N개의 시험장에 좋각 인원을 배치하여 모든 응시자를 감독할 때 필요한 최소 감독관 수
 * 각 시험장에는 총감독관 1명이 반드시 있어야 하고, 추가로 부감독관을 배치 가능
 * 
 * 알고리즘: 수학적 계산
 * - 각 시험장마다 총감독관 1명 배치 (감독 가능 인원 B명)
 * - 남은 인원에 대해 부감독관 배치 (각각 C명 감독 가능)
 * - 남은 인원을 C로 나눠 올림 처리하여 부감독관 수 계산
 * - 전체 감독관 수 = 총감독관 수 + 부감독관 수
 * 
 * 주의사항: 응시자 수가 많을 수 있으므로 long 타입 사용
 * 
 * 시간복잡도: O(N)
 * 공간복잡도: O(N)
 */
public class boj13458 {

    static int n, B, C;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());
        arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        B = Integer.parseInt(st.nextToken()); // 총 감독관 관리가능 수(한 명)
        C = Integer.parseInt(st.nextToken()); // 부 감독관 관리가능 수(여러 명)

        System.out.println(solve());
    }

    private static long solve() {
        long result = 0;
        for (int i = 0; i < n; i++) {
            result++; // 총감독 1명
            int t = arr[i] - B;
            if (t > 0) {
                result += (t + C - 1) / C; // 부감독 필요한 최소 수
            }
        }
        return result;
    }
}