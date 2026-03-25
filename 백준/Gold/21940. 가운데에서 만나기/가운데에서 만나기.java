import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static final int INF = 1_000_000_000;
    static int[][] map;
    static int N, M, K;
    static int min = INF;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            Arrays.fill(map[i], INF);
            map[i][i] = 0;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            map[s][e] = Math.min(map[s][e], w);
        }

        // 플로이드 워셜
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (map[i][k] == INF || map[k][j] == INF)
                        continue;
                    map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                }
            }
        }

        K = Integer.parseInt(br.readLine());
        int[] friends = new int[K];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            friends[i] = Integer.parseInt(st.nextToken());
        }

        int[] result = new int[N + 1];
        Arrays.fill(result, INF);

        for (int city = 1; city <= N; city++) {
            int maxTime = 0;
            boolean possible = true;

            for (int i = 0; i < K; i++) {
                int friend = friends[i];

                if (map[friend][city] == INF || map[city][friend] == INF) {
                    possible = false;
                    break;
                }

                maxTime = Math.max(maxTime, map[friend][city] + map[city][friend]);
            }

            if (possible) {
                result[city] = maxTime;
                min = Math.min(min, result[city]);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int city = 1; city <= N; city++) {
            if (result[city] == min) {
                sb.append(city).append(" ");
            }
        }

        System.out.println(sb);
    }
}