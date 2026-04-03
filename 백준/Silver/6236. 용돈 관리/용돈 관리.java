import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] paper;
    static int l, r;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        paper = new int[N];
        // st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {

            paper[i] = Integer.parseInt(br.readLine());
            l = Math.max(paper[i], l);
        }

        // for (int i = l; i < 100_000_000; i++) {
        // if (test(i)) {
        // System.out.println(i);
        // break;
        // }
        // }
        int result = 0;
        r = 1_000_000_000;

        while (l <= r) {
            int mid = (l + r) / 2;

            if (test(mid)) {
                result = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        System.out.println(result);

    }

    private static boolean test(int a) {
        int money = a;
        int count = 1;
        for (int i = 0; i < N; i++) {
            int need = paper[i];
            if (money < need) {
                money = a;
                count++;
            }

            money -= paper[i];
        }

        return count <= M ? true : false;
    }
}