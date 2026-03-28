import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int J; // 진서집 번호
    static int K; // 종류별 동물 수
    static int[] paper; // 0: 일반집, 1: A형집, 2: B형집
    static int[] dist;
    static final int INF = 100_000_000;

    static class Node implements Comparable<Node> {
        int e, w;

        Node(int e, int w) {
            this.e = e;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.w, o.w);
        }
    }

    static ArrayList<Node>[] line;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        J = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        paper = new int[N + 1];
        dist = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int tmp = Integer.parseInt(st.nextToken());
            paper[tmp] = 1; // A형집
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            paper[Integer.parseInt(st.nextToken())] = 2; // B형집
        }

        line = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            line[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            line[s].add(new Node(e, w));
            line[e].add(new Node(s, w));
        }

        dijkstra();

        // System.out.println(Arrays.toString(dist));

        int minA = INF;
        int minB = INF;

        for (int i = 1; i <= N; i++) {
            if (paper[i] == 1) {
                minA = Math.min(minA, dist[i]);
            } else if (paper[i] == 2) {
                minB = Math.min(minB, dist[i]);
            }
        }

        StringBuilder sb = new StringBuilder();
        if (minA == INF && minB == INF) {
            sb.append(-1);
        } else {
            if (minA > minB) {
                sb.append('B');
                sb.append("\n");
                sb.append(minB);
            } else {
                sb.append('A');
                sb.append("\n");
                sb.append(minA);
            }
        }

        System.out.println(sb);
    }

    static void dijkstra() {
        Arrays.fill(dist, INF);
        PriorityQueue<Node> pq = new PriorityQueue<>();

        dist[J] = 0;
        pq.add(new Node(J, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cur.w > dist[cur.e]) {
                continue;
            }

            for (Node next : line[cur.e]) {
                if (dist[next.e] > cur.w + next.w) {
                    dist[next.e] = cur.w + next.w;
                    pq.add(new Node(next.e, dist[next.e]));
                }
            }
        }
    }
}