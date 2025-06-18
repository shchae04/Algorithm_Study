package study.boj.이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 숫자카드 {
    public static void main(String[] args) throws IOException {
        // 입력을 위한 BufferedReader 생성
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 상근이가 가지고 있는 카드의 개수 N 입력 받기
        int n = Integer.parseInt(br.readLine());

        // 상근이가 가지고 있는 카드의 번호를 저장할 배열 생성
        int[] cards = new int[n];

        // 상근이가 가지고 있는 카드의 번호 입력 받기
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
        }

        // 이분 탐색을 위해 카드 배열 정렬
        Arrays.sort(cards);

        // 확인할 카드 개수 M 입력 받기
        int m = Integer.parseInt(br.readLine());

        // 확인할 카드 번호 입력 받기
        st = new StringTokenizer(br.readLine());

        // 결과를 저장할 StringBuilder 생성
        StringBuilder sb = new StringBuilder();

        // 각 확인할 카드에 대해 이분 탐색 수행
        for (int i = 0; i < m; i++) {
            int target = Integer.parseInt(st.nextToken());

            // 이분 탐색으로 카드 존재 여부 확인 (존재하면 1, 없으면 0)
            if (binarySearch(cards, target)) {
                sb.append("1 ");
            } else {
                sb.append("0 ");
            }
        }

        // 결과 출력
        System.out.println(sb.toString().trim());
    }

    /**
     * 이분 탐색을 수행하는 메소드
     * @param arr 탐색할 정렬된 배열
     * @param target 찾을 값
     * @return 값이 존재하면 true, 없으면 false
     */
    private static boolean binarySearch(int[] arr, int target) {
        int left = 0;                  // 탐색 범위의 시작 인덱스
        int right = arr.length - 1;    // 탐색 범위의 끝 인덱스

        // 탐색 범위가 유효한 동안 반복
        while (left <= right) {
            // 중간 인덱스 계산
            int mid = (left + right) / 2;

            // 중간 값이 타겟과 같으면 찾음
            if (arr[mid] == target) {
                return true;
            }
            // 중간 값이 타겟보다 작으면 오른쪽 부분 배열에서 탐색
            else if (arr[mid] < target) {
                left = mid + 1;
            }
            // 중간 값이 타겟보다 크면 왼쪽 부분 배열에서 탐색
            else {
                right = mid - 1;
            }
        }

        // 값을 찾지 못한 경우
        return false;
    }
}