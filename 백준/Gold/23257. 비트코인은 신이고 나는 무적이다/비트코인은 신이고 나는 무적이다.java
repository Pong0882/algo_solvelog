import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static boolean[][] dp;
    static int[] paper;
    static final int MAX = 1024;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dp = new boolean[M + 1][MAX];
        paper = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            paper[i] = Math.abs(Integer.parseInt(st.nextToken()));

        }
        dp[0][0] = true;

        for (int i = 1; i <= M; i++) {
            // 일단 ture 찾아
            for (int j = 0; j < MAX; j++) {
                if (dp[i - 1][j]) {
                    // System.out.println("Hello");
                    for (int jj = 0; jj < N; jj++) {
                        int next = paper[jj] ^ j;
                        dp[i][next] = true;
                        // System.out.println("Hello? " + i + " " + next);
                    }
                }
            }
        }
        int result = 0;

        // for (int i = 0; i < 10; i++) {
        // System.out.println(dp[M][i]);
        // }
        for (int i = MAX - 1; i >= 0; i--) {
            if (dp[M][i]) {
                result = i;
                break;
            }
        }
        System.out.println(result);
    }
}