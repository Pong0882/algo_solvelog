import java.util.*;

class Solution {

    static final int MOD = 1_000_000_007;

    static int[][] dp;
    static boolean[][] water;

    static int[] dr = {1, 0};
    static int[] dc = {0, 1};

    static int n, m;

    public int solution(int m, int n, int[][] puddles) {

        this.m = m;
        this.n = n;

        dp = new int[n + 1][m + 1];
        water = new boolean[n + 1][m + 1];

        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], -1);
        }

        // 웅덩이
        for (int[] p : puddles) {
            water[p[1]][p[0]] = true;
        }

        return dfs(1, 1, n, m);
    }

    static int dfs(int r, int c, int tr, int tc) {

        // 범위 밖
        if (r > n || c > m) {
            return 0;
        }

        // 웅덩이
        if (water[r][c]) {
            return 0;
        }

        // 도착
        if (r == tr && c == tc) {
            return 1;
        }

        // 이미 계산
        if (dp[r][c] != -1) {
            return dp[r][c];
        }

        int count = 0;

        for (int d = 0; d < 2; d++) {

            int nr = r + dr[d];
            int nc = c + dc[d];

            count += dfs(nr, nc, tr, tc);
            count %= MOD;
        }

        dp[r][c] = count;

        return count;
    }
}