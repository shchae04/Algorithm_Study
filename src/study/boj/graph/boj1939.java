package study.boj.graph;
import java.io.*;
import java.util.*;

/**
 * 백준 1939번 - 중량제한
 *
 * 문제: N개의 섬이 M개의 다리로 연결되어 있고, 각 다리마다 중량 제한이 있다.
 *       한 공장에서 다른 공장으로 물품을 운반할 때, 한 번에 옮길 수 있는 물품의 최대 중량 구하기
 *
 * 알고리즘: 이분 탐색(Binary Search) + 그래프 탐색(BFS)
 *
 * 핵심 아이디어:
 * 1. "최대 중량"을 직접 찾는 것이 아니라 "특정 중량으로 운반 가능한지"를 판단
 * 2. 중량 제한은 1부터 최대 다리 중량까지 범위가 있음
 * 3. 이분 탐색으로 중량 값을 조절하면서 운반 가능 여부를 확인
 * 4. 특정 중량으로 운반 가능하면 더 큰 중량도 시도, 불가능하면 더 작은 중량 시도
 *
 * 이분 탐색을 사용하는 이유:
 * - 모든 중량(1~최대중량)을 하나씩 다 테스트하면 시간초과
 * - 중량이 X로 가능하면 X보다 작은 모든 값도 가능 (단조성이 있음)
 * - 따라서 이분 탐색으로 O(log(최대중량)) 번만에 최적값 찾기 가능
 *
 * BFS를 사용하는 이유:
 * - 출발 섬에서 도착 섬까지 "특정 중량 이상의 다리들만 사용해서" 갈 수 있는지 확인
 * - 최단 경로가 아니라 "도달 가능 여부"만 확인하면 되므로 BFS/DFS 모두 가능
 * - BFS는 큐를 사용해 같은 깊이의 노드들을 순서대로 탐색
 *
 * 동작 과정 예시:
 * 섬1 ---(10)--- 섬2
 *  |              |
 * (5)           (8)
 *  |              |
 * 섬3 ---(7)--- 섬4
 *
 * 섬1에서 섬4로 가는 경로:
 * - 경로1: 섬1 -> 섬2 -> 섬4 (최소 다리: 8)
 * - 경로2: 섬1 -> 섬3 -> 섬4 (최소 다리: 5)
 * => 정답은 8 (경로1이 더 큰 중량 운반 가능)
 *
 * 이분 탐색 과정:
 * 1. minCost=1, maxCost=10 (다리 중 최대값), mid=5
 *    -> 중량 5로 가능한가? YES (경로2 사용) -> 답 저장, minCost=6으로 더 큰 값 시도
 * 2. minCost=6, maxCost=10, mid=8
 *    -> 중량 8로 가능한가? YES (경로1 사용) -> 답 저장, minCost=9로 더 큰 값 시도
 * 3. minCost=9, maxCost=10, mid=9
 *    -> 중량 9로 가능한가? NO -> maxCost=8로 줄임
 * 4. minCost=9, maxCost=8 -> 종료, 답=8
 *
 * 시간복잡도: O(M × log(최대중량))
 * - 이분 탐색: O(log(최대중량)) - 최대 10^9이므로 약 30회
 * - 각 탐색마다 BFS: O(N + M) - N은 섬 개수, M은 다리 개수
 * - 전체: O((N + M) × log(최대중량))
 *
 * 공간복잡도: O(N + M)
 * - 인접 리스트로 그래프 저장: O(M) - 각 다리는 양방향이므로 2M개의 간선
 * - BFS 방문 배열: O(N)
 */

class Island {
    int to;        // 연결된 섬 번호
    int weight;    // 다리의 중량 제한

    public Island(int to, int weight){
        this.to = to;
        this.weight = weight;
    }
}

public class boj1939 {

    // 전역 변수 선언 (BFS에서 사용하기 위해 static으로 선언)
    public static int n;              // 섬의 개수 (정점의 수)
    public static int s;              // 출발 공장이 있는 섬
    public static int e;              // 도착 공장이 있는 섬
    public static List<Island>[] list; // 인접 리스트 - 각 섬에 연결된 다리 정보 저장

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 입력: 첫 번째 줄에서 섬의 개수 N과 다리 정보 M 읽기
        n = Integer.parseInt(st.nextToken());  // 섬의 개수 (1 ≤ N ≤ 10,000)
        int m = Integer.parseInt(st.nextToken()); // 다리의 개수 (1 ≤ M ≤ 100,000)

        // 인접 리스트 초기화 - 각 섬마다 연결된 다리들을 저장할 리스트
        list = new ArrayList[n + 1];  // 섬 번호가 1부터 시작하므로 크기를 n+1로

        for(int i = 1; i <= n; i++){
            list[i] = new ArrayList<>();
        }

        // 이분 탐색의 범위 설정
        int minCost = 1;      // 중량의 최소값 (문제 조건상 1 이상)
        int maxCost = 0;      // 중량의 최대값 (다리들 중 가장 큰 중량 제한)

        // M개의 다리 정보 입력받기
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());  // 다리가 연결하는 섬 A
            int b = Integer.parseInt(st.nextToken());  // 다리가 연결하는 섬 B
            int c = Integer.parseInt(st.nextToken());  // 다리의 중량 제한 (1 ≤ C ≤ 1,000,000,000)

            // 이분 탐색의 상한선 결정 - 모든 다리 중 최대 중량
            // 이유: 이보다 큰 중량으로는 절대 운반할 수 없음
            maxCost = Math.max(maxCost, c);

            // 양방향 그래프로 연결 (A->B, B->A 모두 가능)
            list[a].add(new Island(b, c));
            list[b].add(new Island(a, c));
        }

        // 출발 섬과 도착 섬 입력받기
        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());  // 출발 공장이 있는 섬
        e = Integer.parseInt(st.nextToken());  // 도착 공장이 있는 섬

        int answer = 0;  // 최종 답: 한 번에 옮길 수 있는 최대 중량

        // 이분 탐색으로 최대 중량 찾기
        // 핵심: "X 중량으로 운반 가능한가?"를 반복적으로 질문
        while(minCost <= maxCost){
            int mid = (minCost + maxCost) / 2;  // 중간값 계산 (테스트할 중량)

            // BFS로 mid 중량으로 출발지에서 도착지까지 갈 수 있는지 확인
            if(bfs(mid)){
                // 가능하다면? => 더 큰 중량도 가능할 수 있음
                answer = mid;           // 현재 가능한 최대값 저장
                minCost = mid + 1;      // 더 큰 중량을 시도해봄
            } else {
                // 불가능하다면? => 중량이 너무 무거움
                maxCost = mid - 1;      // 더 작은 중량으로 시도해봄
            }
        }
        // 반복문이 끝나면 answer에는 가능한 최대 중량이 저장되어 있음

        bw.write(String.valueOf(answer));

        br.close();
        bw.flush();
        bw.close();
    }

    /**
     * BFS로 특정 중량(cost)으로 출발지(s)에서 도착지(e)까지 이동 가능한지 확인
     *
     * @param cost 테스트하려는 중량 (이 중량으로 운반이 가능한지 확인)
     * @return 출발지에서 도착지까지 도달 가능하면 true, 불가능하면 false
     *
     * 동작 원리:
     * 1. 출발 섬(s)부터 시작해서 BFS로 탐색
     * 2. 다음 섬으로 이동할 때, 다리의 중량 제한이 cost 이상인 다리만 사용
     * 3. 도착 섬(e)에 도달하면 true 반환
     * 4. 더 이상 갈 곳이 없으면 false 반환
     *
     * BFS 과정 예시 (cost=8로 테스트):
     * 시작: 큐=[섬1], 방문=[섬1]
     * 1단계: 섬1 꺼냄 -> 섬1에서 갈 수 있는 섬 찾기
     *        - 섬2로 가는 다리 중량=10 (10>=8 OK) -> 큐=[섬2]
     *        - 섬3로 가는 다리 중량=5 (5>=8 NO) -> 갈 수 없음
     * 2단계: 섬2 꺼냄 -> 섬2에서 갈 수 있는 섬 찾기
     *        - 섬4로 가는 다리 중량=8 (8>=8 OK) -> 큐=[섬4]
     * 3단계: 섬4 꺼냄 -> 도착지 발견! return true
     */
    public static boolean bfs(int cost){
        // 방문 체크 배열 - 이미 방문한 섬은 다시 방문하지 않음
        boolean[] visited = new boolean[n + 1];

        // BFS를 위한 큐 - 탐색할 섬 번호를 저장
        Queue<Integer> q = new LinkedList<>();

        // 시작: 출발 섬을 큐에 넣고 방문 표시
        q.add(s);
        visited[s] = true;

        // 큐가 빌 때까지 반복 (더 이상 갈 곳이 없을 때까지)
        while(!q.isEmpty()){
            int now = q.poll();  // 현재 탐색 중인 섬

            // 목적지에 도착했다면 성공!
            if(now == e){
                return true;
            }

            // 현재 섬(now)에서 연결된 모든 다리 확인
            for(Island next : list[now]){
                // 다음 섬으로 갈 수 있는 조건:
                // 1. 아직 방문하지 않은 섬이어야 함 (!visited[next.to])
                // 2. 다리의 중량 제한이 우리가 옮기려는 짐(cost)보다 크거나 같아야 함
                //    예: cost=100이면, 중량 제한이 100 이상인 다리만 사용 가능
                if(!visited[next.to] && next.weight >= cost){
                    visited[next.to] = true;  // 방문 표시
                    q.add(next.to);           // 큐에 추가해서 나중에 탐색
                }
            }
        }

        // 큐가 비었는데도 도착지에 도달하지 못했다면 실패
        return false;
    }
}