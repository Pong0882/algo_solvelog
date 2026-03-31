import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] paper; 
    static boolean[] visited;
    static int result;
    static int maxDay;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        paper = new int[N][2];
        visited = new boolean[N];
        maxDay = 0;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int money = Integer.parseInt(st.nextToken());
            int day = Integer.parseInt(st.nextToken());

            paper[i][0] = money;
            paper[i][1] = day;

            maxDay = Math.max(maxDay, day);
        }

        // 뒤 날짜부터 하나씩
        for (int d = maxDay; d >= 1; d--) {
            int maxMoney = 0;
            int idx = -1;

            // 가능한 강연 중 최대값 찾기
            for (int i = 0; i < N; i++) {
                if (!visited[i] && paper[i][1] >= d) {
                    if (paper[i][0] > maxMoney) {
                        maxMoney = paper[i][0];
                        idx = i;
                    }
                }
            }

            if (idx != -1) {
                visited[idx] = true;
                result += paper[idx][0];
            }
        }

        System.out.println(result);
    }
}