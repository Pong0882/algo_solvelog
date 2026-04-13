import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, K;

    static int[] paper;
    // perm
    static boolean[] visited;
    static int[] select;
    static int[] note;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        note = new int[N];
        select = new int[N];
        paper = new int[N];
        visited = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            paper[i] = Integer.parseInt(st.nextToken());
            note[i] = K * (i + 1);
        }

        perm(0);

        System.out.println(result);
    }

    private static void perm(int depth) {
        if (depth == N) {
            check();
            return;
        }
        for (int i = 0; i < N; i++) {
            if (visited[i]) {
                continue;
            }
            select[depth] = i;
            visited[i] = true;
            perm(depth + 1);
            visited[i] = false;
        }
    }

    private static void check() {
        // System.out.println(Arrays.toString(paper));
        int sum = 0;
        for (int i = 0; i < N; i++) {
            sum += paper[select[i]];
            if (sum - note[i] < 0) {
                return;
            }
        }
        result++;
    }
}