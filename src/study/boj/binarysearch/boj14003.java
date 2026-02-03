package study.boj.binarysearch;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Longest Increasing Subsequence 5 (LIS)
 * 백준 14003번
 * 
 * [최종 최적화 버전]
 * - FastInput 적용으로 입출력 속도 극대화 (약 500ms -> 더 단축)
 * - 직관적인 변수명 적용으로 가독성 확보
 */
/**
 * [문제 핵심 접근 방법]
 * 
 * 1. 이 알고리즘의 목표:
 *    - O(N log N) 시간 복잡도로 가장 긴 증가하는 부분 수열(LIS)의 길이와 그 수열을 구합니다.
 *    - N이 1,000,000이므로 일반적인 O(N^2) DP로는 해결이 불가능합니다.
 * 
 * 2. 1단계: 전진 (Forward Phase - Greedy + Binary Search)
 *    - lisCandidates 리스트를 유지하며, 현재까지 만든 수열의 "끝값"들을 저장합니다.
 *    - "같은 길이라면 끝값이 작을수록 유리하다"는 그리디(Greedy) 원칙을 따릅니다.
 *    - Binary Search의 역할: 
 *      새로운 숫자(currentNumber)가 왔을 때, lisCandidates에서 누구를 밀어내고 들어갈지 자리를 찾습니다.
 *      이미 있는 값보다 작은 값이 들어오면 그 자리를 교체하여 "진입 장벽"을 낮춥니다.
 *    - indexRecord 배열: 
 *      각 숫자가 리스트의 '몇 번째 칸'에 들어갔었는지 기록하는 '꼬리표'입니다. 수열 복원의 핵심입니다.
 * 
 * 3. 2단계: 후진 (Backward Phase - Backtracking)
 *    - lisCandidates는 중간에 값이 계속 바뀌므로 실제 정답 수열이 아닙니다.
 *    - 진짜 정답은 indexRecord를 뒤에서부터 훑으며 찾습니다.
 *    - 예를 들어 LIS 길이가 4라면, 뒤에서부터 꼬리표가 3, 2, 1, 0인 숫자를 차례로 뽑아냅니다.
 */
public class boj14003 extends FastInput {

    private void solution() throws Exception {
        // 1. 입력 크기
        int totalCount = nextInt();
        
        // 2. 입력 수열 저장
        int[] inputSequence = new int[totalCount];
        
        // 3. 인덱스 기록 (역추적용)
        int[] indexRecord = new int[totalCount];
        
        // 4. LIS 후보 리스트 (이분 탐색용)
        ArrayList<Integer> lisCandidates = new ArrayList<>();

        for (int i = 0; i < totalCount; i++) {
            int currentNumber = nextInt();
            inputSequence[i] = currentNumber;

            // [Case 1] 리스트가 비어있거나, 맨 뒤보다 큰 경우 (그냥 추가)
            if (lisCandidates.isEmpty() || lisCandidates.get(lisCandidates.size() - 1) < currentNumber) {
                indexRecord[i] = lisCandidates.size();
                lisCandidates.add(currentNumber);
            } 
            // [Case 2] 중간에 들어갈 위치를 찾아야 하는 경우 (이분 탐색 & 교체)
            else {
                int searchIndex = Collections.binarySearch(lisCandidates, currentNumber);
                // binarySearch: 찾으면 양수 인덱스, 못 찾으면 -(insertion point) - 1
                int insertionPoint = (searchIndex >= 0) ? searchIndex : -(searchIndex + 1);
                
                indexRecord[i] = insertionPoint;
                lisCandidates.set(insertionPoint, currentNumber);
            }
        }

        // 5. 역추적 (Backtracking)
        // lisCandidates의 크기가 곧 LIS의 길이입니다.
        int lisLength = lisCandidates.size();
        int[] resultSequence = new int[lisLength];
        
        int resultIdx = 0;
        int currentSearchIndex = totalCount; // 배열의 끝부터 탐색 시작
        int targetOrder = lisLength; // 찾아야 할 순서 (예: 길이 4면 -> 3, 2, 1, 0 순으로 찾음)

        // targetOrder를 하나씩 줄여가며 찾습니다 (예: 4 -> 3 -> 2 -> 1)
        while (targetOrder-- > 0) {
            // 배열을 뒤에서부터 훑으며 해당 순서(targetOrder)를 가진 원소를 찾습니다.
            while (--currentSearchIndex >= 0) {
                if (indexRecord[currentSearchIndex] == targetOrder) {
                    resultSequence[resultIdx++] = inputSequence[currentSearchIndex];
                    break; // 찾았으면 바로 다음 순서 찾으러 이동 (여기서 시간을 아낌)
                }
            }
        }

        // 6. 결과 출력
        StringBuilder sb = new StringBuilder();
        sb.append(lisLength).append('\n');
        
        // 역추적 결과는 거꾸로(뒤에서부터) 담겼으므로, 출력할 때 뒤집어서 출력하거나 뒤에서부터 읽습니다.
        // 위 로직은 큰 값부터 찾아서 순서대로 배열에 넣었으므로(사실상 역순 저장),
        // 출력할 때 배열의 뒤에서부터 출력하면 오름차순이 됩니다.
        for (int i = resultSequence.length - 1; i >= 0; i--) {
            sb.append(resultSequence[i]).append(' ');
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        initFI();
        new boj14003().solution();
    }
}

/**
 * 초고속 입력을 위한 클래스 (DataInputStream 활용)
 */
class FastInput {
    private static final int DEFAULT_BUFFER_SIZE = 1 << 16;
    private static DataInputStream inputStream;
    private static byte[] buffer;
    private static int curIdx, maxIdx;

    protected static void initFI() {
        inputStream = new DataInputStream(System.in);
        buffer = new byte[DEFAULT_BUFFER_SIZE];
        curIdx = maxIdx = 0;
    }

    protected static int nextInt() throws IOException {
        int ret = 0;
        byte c = read();
        while (c <= ' ') c = read(); // 공백 무시
        boolean neg = (c == '-');
        if (neg) c = read();
        do {
            ret = ret * 10 + c - '0';
        } while ((c = read()) >= '0' && c <= '9');
        if (neg) return -ret;
        return ret;
    }

    private static byte read() throws IOException {
        if (curIdx == maxIdx) {
            maxIdx = inputStream.read(buffer, curIdx = 0, DEFAULT_BUFFER_SIZE);
            if (maxIdx == -1) buffer[0] = -1;
        }
        return buffer[curIdx++];
    }
}
