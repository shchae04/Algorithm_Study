package study.boj.binarysearch;
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 백준 10815번 - 숫자 카드
 *
 * 문제: 주어진 수가 상근이의 카드에 있는지 확인하는 문제
 *
 * 알고리즘: 정렬 + 이분 탐색
 * 시간복잡도: O((N + M) log N)
 * 공간복잡도: O(N)
 */

public class boj10815_alt {
    // 입출력을 위한 BufferedReader와 BufferedWriter 객체 생성
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int n, m;               // n: 상근이가 가진 카드 수, m: 확인할 수의 개수
    static int arr[];              // 상근이가 가진 카드 번호를 저장할 배열

    public static void main(String[] args) throws IOException {
        // 상근이가 가진 카드 수 입력
        n = Integer.parseInt(br.readLine());
        arr = new int[n];

        // 상근이가 가진 카드 번호들 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < n; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        // 이분 탐색을 위한 배열 정렬
        Arrays.sort(arr);

        // 확인할 수의 개수 입력
        m = Integer.parseInt(br.readLine());

        // 확인할 수들 입력 및 결과 출력
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < m; i++) {
            int num = Integer.parseInt(st.nextToken());
            // 이분 탐색으로 해당 숫자가 존재하는지 확인
            if(binarySearch(num)) bw.write("1 ");
            else bw.write("0 ");
        }

        // 입출력 스트림 닫기
        bw.close();
        br.close();
    }

    // 이분 탐색 메소드
    private static boolean binarySearch(int num) {
        int leftIndex = 0;                 // 시작 인덱스
        int rightIndex = n - 1;            // 끝 인덱스

        while(leftIndex <= rightIndex){
            int midIndex = (leftIndex + rightIndex) / 2;  // 중간 인덱스 계산
            int mid = arr[midIndex];                      // 중간 값

            if(num < mid) rightIndex = midIndex - 1;      // 찾는 값이 중간 값보다 작으면 왼쪽 부분 탐색
            else if(num > mid) leftIndex = midIndex + 1;  // 찾는 값이 중간 값보다 크면 오른쪽 부분 탐색
            else return true;                             // 찾는 값을 발견하면 true 반환
        }
        return false;                                     // 값을 찾지 못하면 false 반환
    }
}