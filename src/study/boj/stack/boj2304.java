package study.boj.stack;
import java.util.Arrays;
import java.util.Scanner;

public class boj2304 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[][] pillars = new int[n][2];
        
        // 기둥 정보 입력
        for (int i = 0; i < n; i++) {
            pillars[i][0] = sc.nextInt(); // 위치
            pillars[i][1] = sc.nextInt(); // 높이
        }
        
        // 위치 기준 정렬
        Arrays.sort(pillars, (a, b) -> Integer.compare(a[0], b[0]));
        
        // 최대 높이 찾기
        int maxHeight = 0;
        for (int i = 0; i < n; i++) {
            maxHeight = Math.max(maxHeight, pillars[i][1]);
        }
        
        // 최소, 최대 x 좌표 찾기
        int minX = pillars[0][0];
        int maxX = pillars[n-1][0];
        
        // 높이별 왼쪽 끝, 오른쪽 끝 좌표 저장
        int[] leftEnd = new int[maxHeight + 1];
        int[] rightEnd = new int[maxHeight + 1];
        
        // 초기화: 각 높이의 왼쪽 끝은 최대값으로, 오른쪽 끝은 최소값으로 설정
        Arrays.fill(leftEnd, Integer.MAX_VALUE);
        Arrays.fill(rightEnd, Integer.MIN_VALUE);
        
        // 각 기둥에 대해 모든 높이별 왼쪽/오른쪽 끝 좌표 갱신
        for (int i = 0; i < n; i++) {
            int x = pillars[i][0];
            int h = pillars[i][1];
            
            // 해당 기둥의 높이부터 1까지 모든 높이에 대해 갱신
            for (int height = 1; height <= h; height++) {
                leftEnd[height] = Math.min(leftEnd[height], x);
                rightEnd[height] = Math.max(rightEnd[height], x);
            }
        }
        
        // 면적 계산
        int area = 0;
        for (int height = 1; height <= maxHeight; height++) {
            // 해당 높이에 기둥이 존재하는 경우에만 계산
            if (leftEnd[height] != Integer.MAX_VALUE) {
                // 너비 * 높이 1 계산
                area += (rightEnd[height] - leftEnd[height] + 1); // +1은 기둥 자체의 너비
            }
        }
        
        System.out.println(area);
    }
}