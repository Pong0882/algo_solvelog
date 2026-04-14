import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, S, M;
    static boolean[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp = new boolean[M + 1][2];
        dp[S][0] = true;

        for (int i = 0; i < N; i++) {
            int curIdx = i % 2;
            int nextIdx = (i + 1) % 2;

            for (int j = 0; j <= M; j++) {
                dp[j][nextIdx] = false;
            }

            int volume = arr[i];

            for (int j = 0; j <= M; j++) {
                if (!dp[j][curIdx])
                    continue;

                if (j - volume >= 0) {
                    dp[j - volume][nextIdx] = true;
                }
                if (j + volume <= M) {
                    dp[j + volume][nextIdx] = true;
                }
            }
        }

        for (int i = M; i >= 0; i--) {
            if (dp[i][N % 2]) {
                System.out.println(i);
                return;
            }
        }

        System.out.println(-1);
    }
}