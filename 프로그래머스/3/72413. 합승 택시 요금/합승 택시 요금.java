import java.util.*;

class Solution {
    static class Node implements Comparable<Node> {
        int to, cost;

        Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    static final int INF = 100_000_000;
    static ArrayList<Node>[] graph;
    // n: 노드수, s: 시작지점, a,b: 도착 노드 , fares[] : 길 개수, fares[][] : 시작, 도착, 요금 
    // 1. s, a, b 에서 다익스트라로 거리 검사 
    // 2. 지점 K 에서 s, a, b 까지의 요금 제일싼거 출력
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer;

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] f : fares) {
            int from = f[0];
            int to = f[1];
            int cost = f[2];

            graph[from].add(new Node(to, cost));
            graph[to].add(new Node(from, cost));
        }

        // s, a, b 에서 각각 최단거리 구하기
        int[] distS = dijkstra(n, s);
        int[] distA = dijkstra(n, a);
        int[] distB = dijkstra(n, b);

        answer = INF;

        // 모든 분기점 k 탐색
        for (int k = 1; k <= n; k++) {
            answer = Math.min(answer, distS[k] + distA[k] + distB[k]);
        }

        return answer;
    }

    static int[] dijkstra(int n, int start) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, INF);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[start] = 0;
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            
            int cost = cur.cost;
            int now = cur.to;
            if(dist[now] < cost){
                continue;
            }
            
            for(Node next : graph[now]){
                int nextCost = next.cost + cost;
                if(nextCost < dist[next.to]){
                    dist[next.to] = nextCost;
                    pq.add(new Node(next.to, nextCost));
                }
            }
        }

        return dist;
    }
}