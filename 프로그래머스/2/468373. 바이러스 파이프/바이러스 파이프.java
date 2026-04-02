import java.util.*;

class Solution {
    int maxInfected = 0;
    List<Edge>[] adj;
    
    class Edge {
        int to, type;
        Edge(int to, int type) {
            this.to = to;
            this.type = type;
        }
    }

    public int solution(int n, int infection, int[][] edges, int k) {
        adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) adj[i] = new ArrayList<>();
        
        for (int[] e : edges) {
            adj[e[0]].add(new Edge(e[1], e[2]));
            adj[e[1]].add(new Edge(e[0], e[2]));
        }

        // 비트마스킹을 사용하여 감염된 노드 상태 관리 (n <= 100이므로 BitSet 사용)
        BitSet initialInfected = new BitSet(n + 1);
        initialInfected.set(infection);
        
        dfs(0, k, initialInfected, n);

        return maxInfected;
    }

    private void dfs(int count, int k, BitSet currentInfected, int n) {
        // 현재까지 감염된 수 업데이트
        maxInfected = Math.max(maxInfected, currentInfected.cardinality());

        // k번 모두 수행했거나 모든 노드가 감염되었으면 종료
        if (count == k || maxInfected == n) return;

        // 3가지 타입의 파이프를 시도
        for (int type = 1; type <= 3; type++) {
            BitSet nextInfected = (BitSet) currentInfected.clone();
            
            // 현재 감염된 노드들로부터 해당 타입 파이프로 도달 가능한 노드 탐색 (BFS)
            Queue<Integer> queue = new LinkedList<>();
            for (int i = currentInfected.nextSetBit(1); i >= 0; i = currentInfected.nextSetBit(i + 1)) {
                queue.add(i);
            }

            boolean changed = false;
            while (!queue.isEmpty()) {
                int curr = queue.poll();
                for (Edge edge : adj[curr]) {
                    if (edge.type == type && !nextInfected.get(edge.to)) {
                        nextInfected.set(edge.to);
                        queue.add(edge.to);
                        changed = true;
                    }
                }
            }

            // 만약 이번 타입 선택으로 새로 감염된 노드가 있다면 다음 단계로 진행
            if (changed) {
                dfs(count + 1, k, nextInfected, n);
            }
        }
    }
}