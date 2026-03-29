import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;

    static boolean[][] map;
    static boolean[][][] visited;

    static class Player {
        int r, c, s, t;

        public Player(int r, int c, int s, int t) {
            this.r = r;
            this.c = c;
            this.s = s;
            this.t = t;
        }

    }

    static int[] dr = { 0, 0, -1, 1 };
    static int[] dc = { 1, -1, 0, 0 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new boolean[N][M];
        visited = new boolean[N][M][K + 1];

        for (int i = 0; i < N; i++) {
            char[] tmp = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if (tmp[j] == '1') {
                    map[i][j] = true; // true == 벽
                }
            }
        }

        ArrayDeque<Player> q = new ArrayDeque<>();
        q.add(new Player(0, 0, K, 1));
        visited[0][0][K] = true;
        while (!q.isEmpty()) {
            Player cur = q.poll();

            if (cur.r == N - 1 && cur.c == M - 1) {
                System.out.println(cur.t);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];

                if (isOut(nr, nc)) { // 나가면
                    continue;
                }
                if (map[nr][nc]) { // 벽이면
                    // 스킬 있으면 쓰기
                    if (cur.s > 0) {
                        // 그자리 간적있는지는 확인해야해
                        if (visited[nr][nc][cur.s - 1]) {
                            continue;
                        }
                        q.add(new Player(nr, nc, cur.s - 1, cur.t + 1));
                        visited[nr][nc][cur.s - 1] = true;
                    }
                    continue;
                }

                // 방문 체크
                if (visited[nr][nc][cur.s]) {
                    continue;
                }
                // 스킬 안쓰고 이동
                q.add(new Player(nr, nc, cur.s, cur.t + 1));
                visited[nr][nc][cur.s] = true;
            }
        }
        System.out.println(-1);
    }

    private static boolean isOut(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= M;
    }
}
