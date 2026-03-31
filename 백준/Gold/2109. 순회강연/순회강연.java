import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] paper;
    static int result;
    static int maxDay;

    // GPT 버전
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        paper = new int[N][2];
        maxDay = 0;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int money = Integer.parseInt(st.nextToken());
            int day = Integer.parseInt(st.nextToken());

            paper[i][0] = money;
            paper[i][1] = day;

            maxDay = Math.max(maxDay, day);
        }

        // day 기준 내림차순 정렬
        Arrays.sort(paper, (a, b) -> b[1] - a[1]);

        // 큰 값이 먼저 나오도록 최대 힙
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);

        int idx = 0;

        // 날짜를 뒤에서부터 내려오면서
        for (int d = maxDay; d >= 1; d--) {
            // 현재 날짜 d에 할 수 있는 강연들을 pq에 넣기
            while (idx < N && paper[idx][1] >= d) {
                pq.offer(paper[idx][0]);
                idx++;
            }

            // 그날 가능한 강연 중 가장 돈이 큰 강연 선택
            if (!pq.isEmpty()) {
                result += pq.poll();
            }
        }

        System.out.println(result);
    }
}