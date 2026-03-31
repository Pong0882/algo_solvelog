import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, res;
    static int[][] paper;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        paper = new int[N][2];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            paper[i][0] = Integer.parseInt(st.nextToken()); // money
            paper[i][1] = Integer.parseInt(st.nextToken()); // day
        }

        Arrays.sort(paper, (a, b) -> a[1] - b[1]);

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            pq.offer(paper[i][0]);

            if (pq.size() > paper[i][1]) {
                pq.poll();
            }
        }

        while (!pq.isEmpty()) {
            res += pq.poll();
        }

        System.out.println(res);
    }
}