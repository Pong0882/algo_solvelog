import java.util.*;

class Solution {
    static int K; // 훔칠 물건의 개수
    static int N, M;
    static int[][] INFO;
    static final int INF = 1_000_000_000;
    static int result;
    static int[][] dp;
    
    public int solution(int[][] info, int n, int m) {
        // 1번 풀이
        // 일단 B를 먼저 다뽑아? 최소치로? subset 백트레킹, 
        // 프루닝 잘 걸어볼까?
        // dp 메모이제이션?
        
        K = info.length;
        N = n;
        M = m;
        INFO = info;
        result = INF;

        dp = new int[K + 1][M];
        for (int i = 0; i <= K; i++) {
            Arrays.fill(dp[i], INF);
        }
        
        dfs(0,0,0);
        // System.out.print(result);
        
        return result == INF ? -1 : result;
    }
     static void dfs(int depth, int a, int b) {

        if (a >= N || b >= M || a>=result || dp[depth][b] <= a) {
            return;
        }
        dp[depth][b] = a;

        if (depth == K) {
            result = Math.min(result, a);
            return;
        }
        // B가 훔침
        dfs(depth + 1, a, b + INFO[depth][1]);

        // A가 훔침
        dfs(depth + 1, a + INFO[depth][0], b);

    }
    
}