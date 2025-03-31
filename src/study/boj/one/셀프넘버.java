package study.boj.one;

public class 셀프넘버 {
    // d(n) 함수 정의: n + n의 각 자리수
    public static int d(int n) {
        int sum = n;
        while (n != 0) {
            sum += n % 10; // 일의 자리 더함
            n /= 10;       // 다음 자리
        }
        return sum;
    }

    public static void main(String[] args) {
        boolean[] isGenerated = new boolean[10001]; // 인덱스 1~10000까지 사용

        // 1부터 10000까지의 수로 생성 가능한 수를 표시
        for (int i = 1; i <= 10000; i++) {
            int generated = d(i);
            if (generated <= 10000) {
                isGenerated[generated] = true;
            }
        }

        // 생성자가 없는 수(셀프 넘버)를 출력
        for (int i = 1; i <= 10000; i++) {
            if (!isGenerated[i]) {
                System.out.println(i);
            }
        }
    }
}