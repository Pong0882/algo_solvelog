import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;
    static int[] dr = { -1, 1, 0, 0 }; // 상 하 좌 우
    static int[] dc = { 0, 0, -1, 1 };
    static int R, C;
    static boolean[][] map, visited;
    static int[] move;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());

        map = new boolean[N][M];
        visited = new boolean[N][M];
        move = new int[4];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map[a][b] = true; // 장애물
        }

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            move[i] = Integer.parseInt(st.nextToken()) - 1;
        }

        int r = R;
        int c = C;
        int point = 0; 
        visited[r][c] = true;

        while (true) {
            int dir = move[point];
            int nr = r + dr[dir];
            int nc = c + dc[dir];

            // 현재 방향으로 이동 가능하면 이동
            if (canGo(nr, nc)) {
                r = nr;
                c = nc;
                visited[r][c] = true;
                continue;
            }

            // 이동 불가능하면 최대 4번 방향 전환 시도
            boolean moved = false;
            for (int i = 1; i <= 4; i++) {
                int nextPoint = (point + i) % 4;
                dir = move[nextPoint];
                nr = r + dr[dir];
                nc = c + dc[dir];

                if (canGo(nr, nc)) {
                    point = nextPoint;
                    r = nr;
                    c = nc;
                    visited[r][c] = true;
                    moved = true;
                    break;
                }
            }

            // 4방향 다 못 가면 종료
            if (!moved) {
                break;
            }
        }

        System.out.println(r + " " + c);
    }

    private static boolean canGo(int nr, int nc) {
        if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
            return false;
        }
        if (map[nr][nc]) {
            return false;
        }
        if (visited[nr][nc]) {
            return false;
        }
        return true;
    }
}
