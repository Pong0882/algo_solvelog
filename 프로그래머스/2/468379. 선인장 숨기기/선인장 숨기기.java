import java.util.*;

class Solution {
    static final int INF = 1_000_000_000;

    public int[] solution(int m, int n, int h, int w, int[][] drops) {
        int[][] time = new int[m][n];

        // 1. 각 칸에 비가 떨어지는 시간을 기록
        for (int i = 0; i < m; i++) {
            Arrays.fill(time[i], INF);
        }

        for (int i = 0; i < drops.length; i++) {
            int r = drops[i][0];
            int c = drops[i][1];

            time[r][c] = i + 1;
        }

        // 2. 각 행마다 가로 길이 w 구간의 최소값 구하기
        // rowMin[r][c] = r행에서 c ~ c+w-1 구간의 최소 비 시간
        int colSize = n - w + 1;
        int[][] rowMin = new int[m][colSize];

        for (int r = 0; r < m; r++) {
            ArrayDeque<Integer> deque = new ArrayDeque<>();

            for (int c = 0; c < n; c++) {
                // 현재 값보다 큰 값들은 뒤에서 제거
                while (!deque.isEmpty() && time[r][deque.peekLast()] >= time[r][c]) {
                    deque.pollLast();
                }

                deque.offerLast(c);

                // 윈도우 범위를 벗어난 인덱스 제거
                if (deque.peekFirst() <= c - w) {
                    deque.pollFirst();
                }

                // 길이 w가 완성된 시점부터 저장
                if (c >= w - 1) {
                    int startC = c - w + 1;
                    rowMin[r][startC] = time[r][deque.peekFirst()];
                }
            }
        }

        // 3. 세로 길이 h 구간의 최소값 구하기
        // 즉, 최종적으로 h x w 직사각형의 최소 비 시간 구하기
        int rowSize = m - h + 1;

        int bestTime = -1;
        int answerR = 0;
        int answerC = 0;

        for (int c = 0; c < colSize; c++) {
            ArrayDeque<Integer> deque = new ArrayDeque<>();

            for (int r = 0; r < m; r++) {
                while (!deque.isEmpty() && rowMin[deque.peekLast()][c] >= rowMin[r][c]) {
                    deque.pollLast();
                }

                deque.offerLast(r);

                if (deque.peekFirst() <= r - h) {
                    deque.pollFirst();
                }

                if (r >= h - 1) {
                    int startR = r - h + 1;
                    int startC = c;

                    int currentTime = rowMin[deque.peekFirst()][c];

                    // currentTime이 클수록 더 늦게 비 맞음
                    // 같은 경우는 위쪽, 왼쪽 우선이므로 처음 나온 값 유지
                    if (currentTime > bestTime) {
                        bestTime = currentTime;
                        answerR = startR;
                        answerC = startC;
                    }
                }
            }
        }

        return new int[]{answerR, answerC};
    }
}