package study.boj.daily;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 백준 1758번 - 암호 만들기
 *
 * 문제: C개의 문자로 길이 L인 증가하는 암호를 만들기
 * 조건: 최소 한 개의 모음, 최소 두 개의 자음 포함
 *
 * 알고리즘: 백트래킹을 이용한 조합 생성
 * - 주어진 문자들을 정렬한 후 사전순으로 가능한 모든 조합 탐색
 * - 각 조합이 완성될 때마다 모음/자음 개수 조건 검사
 * - 조건을 만족하는 조합만 출력
 *
 * 핵심 아이디어:
 * 1. 입력받은 문자들을 오름차순 정렬
 * 2. 백트래킹으로 길이 L인 모든 조합 생성 (중복 없이, 사전순)
 * 3. 각 조합에서 모음(a,e,i,o,u) 개수와 자음 개수 확인
 * 4. 조건을 만족하면 출력
 *
 * 시간복잡도: O(C^L * L) - C개 중 L개를 선택하는 조합의 수 × 각 조합의 유효성 검사
 * - 실제로는 가지치기로 인해 더 적은 연산 수행
 *
 * 공간복잡도: O(L) - 재귀 호출 스택과 현재 조합을 저장하는 배열
 *
 * 조합론과 백트래킹의 대표적인 문제로, 제약 조건이 있는 조합 생성 문제
 */

public class boj1758 {

    public static int L, C;
    public static char[] list;
    public static char[] code;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        list = new char[C];
        code = new char[L];

        st = new StringTokenizer(br.readLine());

        for (int x = 0; x < C; x++) {
            list[x] = st.nextToken().charAt(0);
        }


        // 정렬
        Arrays.sort(list);

        makeCode(0,0);

    }

    public static void makeCode(int x,int idx) {


        if (idx == L) {
            // 최소 한개의 모음, 최소 2개의 자음인지 확인
            if (isValid()) {
                System.out.println(code);
            }
            return;
        }

        // 아직 길이 L의 코드를 만들지 않았고 글자도 아직 남았다.

        for (int i = x; i < C; i++) {
            code[idx] = list[i];
            makeCode(i+1, idx + 1);
        }
    }

    // 최소 모음 1개, 최소 자음 2개인지 확인
    public static boolean isValid() {
        int mo = 0;
        int ja = 0;

        for (char x : code) {
            if (x == 'a' || x == 'e' || x == 'i' || x == 'o' || x == 'u') {
                mo++;
            } else {
                ja++;
            }
        }

        return mo >= 1 && ja >= 2;
    }

}

