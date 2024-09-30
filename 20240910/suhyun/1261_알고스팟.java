import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int N, M;
    static int[][] board, dp;
    static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    private static int dijkstra() {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));
        pq.add(new int[] { 0, 0, 0 });
        dp[0][0] = 0;

        while (!pq.isEmpty()) {
            int[] now = pq.poll();

            if (now[2] > dp[now[0]][now[1]]) {
                continue;
            }

            for (int[] d : delta) {
                int ny = now[0] + d[0];
                int nx = now[1] + d[1];

                if (ny >= 0 && ny < N && nx >= 0 && nx < M) {
                    int wallCnt = now[2] + board[ny][nx];

                    if (wallCnt < dp[ny][nx]) {
                        dp[ny][nx] = wallCnt;
                        pq.add(new int[] { ny, nx, wallCnt });
                    }
                }
            }
        }

        return dp[N - 1][M - 1];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer size = new StringTokenizer(br.readLine());

        M = Integer.parseInt(size.nextToken());
        N = Integer.parseInt(size.nextToken());
        board = new int[N][M];
        dp = new int[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();

            for (int j = 0; j < M; j++) {
                if (line.charAt(j) == '1') {
                    board[i][j] = 1;
                } else {
                    board[i][j] = 0;
                }
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        System.out.println(dijkstra());
    }
}