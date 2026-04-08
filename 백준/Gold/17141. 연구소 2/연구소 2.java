import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] map;

    static class Position {
        int r, c;

        Position(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static ArrayList<Position> positionList;
    static boolean[] select;
    static int result = 1_000_000;

    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // N : 50 , M : 10
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        // 연구실 크기 50 * 50;
        // 이거진짜 완탐? 그러면 2500 C 10 의 경우의수로 BFS 를 돌려야하는데?

        // 0 빈칸, 1 벽, 2 바이러스
        // 못퍼트리면 -1

        // 일단 해봐? 뭐 좋은방법이있는지모르겠네..?

        // 아니 뭔데 이거 2번이 놓을수 있는칸이네?
        // map 에 2 의 경우의수는 10 이하 >> 10 C 5 정도네 완탐 가능
        // 10 C 5 * 2500 정도네
        positionList = new ArrayList<>();

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 2) {
                    positionList.add(new Position(i, j));
                }
            }
        }
        select = new boolean[positionList.size()];

        brute(0, 0);

        System.out.println(result == 1_000_000 ? -1 : result);
    }

    private static void brute(int depth, int start) {
        if (depth == M) {
            check();
            return;
        }

        for (int i = start; i < positionList.size(); i++) {
            select[i] = true;
            brute(depth + 1, i + 1);
            select[i] = false;
        }
    }

    private static void check() {
        ArrayDeque<Position> q = new ArrayDeque<>();
        int[][] dist = new int[N][N];

        for (int i = 0; i < N; i++) {
            Arrays.fill(dist[i], -1);
        }

        for (int i = 0; i < positionList.size(); i++) {
            if (select[i]) {
                Position p = positionList.get(i);
                q.add(p);
                dist[p.r][p.c] = 0;
            }
        }

        while (!q.isEmpty()) {
            Position cur = q.poll();

            for (int d = 0; d < 4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];

                if (nr < 0 || nc < 0 || nr >= N || nc >= N) {
                    continue;
                }
                if (map[nr][nc] == 1) {
                    continue;
                }
                if (dist[nr][nc] != -1) {
                    continue;
                }

                dist[nr][nc] = dist[cur.r][cur.c] + 1;
                q.add(new Position(nr, nc));
            }
        }

        int max = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] != 1) {
                    if (dist[i][j] == -1) {
                        return;
                    }
                    max = Math.max(max, dist[i][j]);
                }
            }
        }

        result = Math.min(result, max);
    }
}
