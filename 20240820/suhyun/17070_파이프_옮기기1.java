import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int N;
    static int[][] board;
    static int[][][] dp;
    static boolean[][][] check;

    // 파이프 머리 기준 이동할 delta 인덱스 dy와 dx, 방향
    static int[][] rightDelta = { { 0, 1, 0 }, { 1, 1, 1 } };
    static int[][] crossDelta = { { 0, 1, 0 }, { 1, 1, 1 }, { 1, 0, 2 } };
    static int[][] downDelta = { { 1, 0, 2 }, { 1, 1, 1 } };

    // 방향 w = [→: 0, ↘: 1, ↓: 2]
    static int explore(int y, int x, int w) {
        if (y == N - 1 && x == N - 1) {
            return 1;
        }

        // 해당 위치에 해당 방향으로 방문했던 경우 이전 값 사용
        if (check[y][x][w]) {
            return dp[y][x][w];
        }
        check[y][x][w] = true;

        int[][] delta;

        // 방향에 따른 델타값 지정
        switch (w) {
            case 0:
                delta = rightDelta;
                break;
            case 1:
                delta = crossDelta;
                break;
            default:
                delta = downDelta;
        }

        for (int i = 0; i < delta.length; i++) {
            int ny = y + delta[i][0];
            int nx = x + delta[i][1];
            int nw = delta[i][2];

            if (ny >= 0 && ny < N && nx >= 0 && nx < N && board[ny][nx] != 1) {
                // 대각선일 때 이동 가능한지 확인
                if (nw == 1 && (board[ny - 1][nx] == 1 || board[ny][nx - 1] == 1)) {
                    continue;
                }
                dp[y][x][w] += explore(ny, nx, nw);
            }
        }

        return dp[y][x][w];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        dp = new int[N][N][3];
        check = new boolean[N][N][3]; // 각각의 칸에 3개의 방향에 대한 방문리스트

        for (int i = 0; i < N; i++) {
            StringTokenizer line = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                if (line.nextToken().equals("1")) {
                    board[i][j] = 1;
                }
            }
        }

        System.out.println(explore(0, 1, 0));
    }
}