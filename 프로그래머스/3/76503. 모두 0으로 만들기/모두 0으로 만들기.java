import java.util.*;

class Solution {
    static int[] indeg;
    static ArrayList<Integer>[] adj;
    static int N;
    static boolean[] visited;
    
    public long solution(int[] a, int[][] edges) {
        long answer = 0;
        
        N = a.length;
        indeg = new int[N];
        adj = new ArrayList[N];
        visited = new boolean[N];
        
        for (int i = 0; i < N; i++) {
            adj[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < N - 1; i++) {
            int s = edges[i][0];
            int e = edges[i][1];
            
            adj[s].add(e);
            adj[e].add(s);
            indeg[s]++;
            indeg[e]++;
        }
        
        ArrayDeque<Integer> q = new ArrayDeque<>();
        
        long[] weight = new long[N];
        long sum = 0;
        
        for (int i = 0; i < N; i++) {
            weight[i] = a[i];
            sum += weight[i];
            
            if (indeg[i] == 1) {
                q.add(i);
            }
        }
        
        if (sum != 0) {
            return -1;
        }
        
        while (!q.isEmpty()) {
            int cur = q.poll();
            visited[cur] = true;
            
            for (int next : adj[cur]) {
                if (visited[next]) continue;
                
                answer += Math.abs(weight[cur]);
                weight[next] += weight[cur];
                weight[cur] = 0;
                
                indeg[next]--;
                if (indeg[next] == 1) {
                    q.add(next);
                }
                
                break;
            }
        }
        
        return answer;
    }
}