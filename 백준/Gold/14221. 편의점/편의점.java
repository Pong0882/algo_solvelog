import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;

    static class Node implements Comparable<Node> {
        int e, w;

        public Node(int e, int w) {
            this.e = e;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.w, o.w);
        }
    }

    static ArrayList<Node>[] edge;
    static int[] shop, home;
    static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        edge = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            edge[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            edge[a].add(new Node(b, w));
            edge[b].add(new Node(a, w));
        }

        st = new StringTokenizer(br.readLine());
        int homeN = Integer.parseInt(st.nextToken());
        int shopN = Integer.parseInt(st.nextToken());

        home = new int[homeN];
        shop = new int[shopN];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < homeN; i++) {
            home[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < shopN; i++) {
            shop[i] = Integer.parseInt(st.nextToken());
        }

        dist = new int[N + 1];
        Arrays.fill(dist, 100_000_000);

        dijkstra(shopN);

        int min = 100_000_000;
        int result = 0;

        for (int i = 0; i < homeN; i++) {
            int h = home[i];

            if (dist[h] < min) {
                min = dist[h];
                result = h;
            } else if (dist[h] == min && h < result) {
                result = h;
            }
        }

        System.out.println(result);
    }

    private static void dijkstra(int startN) {
        PriorityQueue<Node> pq = new PriorityQueue<>();

        for (int i = 0; i < startN; i++) {
            dist[shop[i]] = 0;
            pq.add(new Node(shop[i], 0));
        }

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (dist[cur.e] < cur.w) {
                continue;
            }

            for (Node next : edge[cur.e]) {
                int nw = cur.w + next.w;

                if (dist[next.e] > nw) {
                    dist[next.e] = nw;
                    pq.add(new Node(next.e, nw));
                }
            }
        }
    }
}