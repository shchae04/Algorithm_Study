package study.boj.string;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 11728번 - 배열 합치기
 *
 * 문제: 정렬된 두 배열 A와 B를 합쳐서 정렬된 하나의 배열로 만들기
 *
 * 알고리즘: 투 포인터를 이용한 병합(Merge) 알고리즘
 * - 각 배열에 포인터(인덱스)를 두고 작은 값부터 차례로 선택
 * - 한 배열의 모든 원소가 선택되면 나머지 배열의 원소들을 순서대로 추가
 * - 이미 정렬된 배열들의 특성을 활용하여 효율적으로 병합
 *
 * 핵심 아이디어:
 * 1. 두 정렬된 배열의 첫 번째 원소부터 비교 시작
 * 2. 더 작은 값을 결과 배열에 추가하고 해당 배열의 포인터 증가
 * 3. 한 배열이 모두 처리되면 남은 배열의 원소들을 순서대로 복사
 *
 * 시간복잡도: O(N + M) - N과 M은 각각 배열 A와 B의 크기
 * - 각 배열의 모든 원소를 정확히 한 번씩만 검사
 * - 전체 원소 개수만큼의 비교 연산 수행
 *
 * 공간복잡도: O(N + M) - 결과 배열을 위한 추가 공간 필요
 * - 입력 배열들의 크기 합만큼의 공간 사용
 *
 * 병합 정렬(Merge Sort)의 핵심 연산으로, 분할 정복 알고리즘의 기본 구성 요소
 */

public class boj11728 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int sizeA = Integer.parseInt(st.nextToken());
        int sizeB = Integer.parseInt(st.nextToken());

        int[] A = new int[sizeA];
        int[] B = new int[sizeB];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < sizeA; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < sizeB; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        int[] conArr = new int[sizeA + sizeB];

        int i=0, j=0, k=0;
        while (i < sizeA && j < sizeB) {
            if(A[i] < B[j]) {
                conArr[k++] = A[i++];
            } else {
                conArr[k++] = B[j++];
            }
        }

        while (i < sizeA) {
            conArr[k++] = A[i++];
        }

        while (j < sizeB) {
            conArr[k++] = B[j++];
        }

        for(int num : conArr) {
            System.out.print(num + " ");
        }
    }
}
