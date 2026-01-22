package study.boj.string;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 1107번 - 리모컨
 * 
 * 문제: 일부 숫자 버튼이 고장난 리모컨으로 목표 채널로 이동하는 최소 버튼 입력 횟수 구하기
 * 
 * 알고리즘: 완전탐색 + 그리디
 * - 방법 1: +/- 버튼만 사용 (현재 채널 100에서 목표까지)
 * - 방법 2: 고장나지 않은 숫자 버튼으로 만들 수 있는 모든 수 + +/- 버튼 조합
 * - 0~6자리까지 모든 숫자 조합을 재귀로 탐색하여 최적해 찾기
 * - 두 방법 중 최솟값 선택
 * 
 * 시간복잡도: O(10^6) (최대 6자리, 각 자리당 최대 10개 숫자)
 * 공간복잡도: O(1) (재귀 스택 제외)
 */
public class boj1107 {

    // 0~9 숫자 버튼의 고장 여부를 저장하는 배열 (true = 고장)
    static boolean[] broken = new boolean[10];
    // 목표 채널 번호
    static int channel;
    // 최소 버튼 입력 횟수를 저장하는 변수
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫 번째 줄: 목표 채널 (문제에서는 사용하지 않지만 입력으로 주어짐)
        String str = br.readLine();
        // 두 번째 줄: 실제 목표 채널 번호 입력
        channel = Integer.parseInt(br.readLine());

        // 세 번째 줄: 고장난 버튼의 개수
        int brokenNum = Integer.parseInt(br.readLine());

        // 고장난 버튼이 있는 경우에만 처리
        if (brokenNum > 0) {
            // 네 번째 줄: 고장난 버튼 번호들을 공백으로 구분하여 입력
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < brokenNum; i++) {
                // 고장난 버튼 번호를 true로 표시
                broken[Integer.parseInt(st.nextToken())] = true;
            }
        }

        // 특수 케이스: 현재 채널(100)이 목표 채널과 같은 경우
        // 버튼을 누를 필요가 없으므로 0 출력 후 종료
        if (channel == 100) {
            System.out.println(0);
            return;
        }

        // 최솟값을 찾기 위해 최댓값으로 초기화
        ans = Integer.MAX_VALUE;

        // 방법 1: +/- 버튼만 사용하는 경우
        // 현재 채널(100)에서 목표 채널까지 +/- 버튼만으로 이동
        // 절댓값을 사용하여 거리(버튼 입력 횟수) 계산
        ans = Math.abs(channel - 100);

        // 방법 2: 숫자 버튼 조합을 사용하는 경우
        // 최대 6자리까지 가능 (문제 조건: 채널 범위가 500,000 이하)
        // 0자리(빈 입력)부터 6자리까지 모든 경우를 시도
        for (int i = 0; i <= 6; i++) {
            // i자리 숫자로 만들 수 있는 모든 조합을 재귀적으로 탐색
            check(0, i, 0);
        }

        // 최종 답 출력
        System.out.println(ans);
    }

    /**
     * 재귀 함수: 고장나지 않은 숫자 버튼으로 만들 수 있는 모든 숫자 조합을 탐색
     * @param pick 현재까지 선택한 자릿수
     * @param len 만들려는 숫자의 총 자릿수
     * @param num 현재까지 만든 숫자
     */
    private static void check(int pick, int len, int num) {
        // 기저 조건: 목표 자릿수만큼 숫자를 모두 선택한 경우
        if (pick == len) {
            // 현재 만든 숫자(num)에서 목표 채널까지의 총 버튼 입력 횟수 계산
            // len: 숫자 버튼을 누른 횟수
            // Math.abs(channel - num): +/- 버튼으로 목표 채널까지 이동하는 횟수
            int count = Math.abs(channel - num) + len;

            // 현재까지 찾은 최솟값과 비교하여 더 작은 값으로 갱신
            ans = Math.min(ans, count);
            return;
        }

        // 0~9까지 모든 숫자에 대해 시도
        for (int i = 0; i < 10; i++) {
            // 고장나지 않은 버튼인 경우에만 사용
            if (!broken[i]) {
                // 재귀 호출: 다음 자리 선택
                // pick + 1: 선택한 자릿수 증가
                // len: 목표 자릿수 유지
                // num * 10 + i: 현재 숫자에 새로운 자릿수 추가
                check(pick + 1, len, num * 10 + i);
            }
        }
    }
}
