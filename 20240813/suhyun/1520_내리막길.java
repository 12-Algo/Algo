import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static int[][] board;
    static int[][] dp;
    static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    static int dfs(int y, int x) {
        if (y == N - 1 && x == M - 1) {
            return 1;
        }

        int cnt = 0;

        for (int i = 0; i < 4; i++) {
            int ny = y + delta[i][0];
            int nx = x + delta[i][1];

            if (ny >= 0 && ny < N && nx >= 0 && nx < M && board[y][x] > board[ny][nx]) {

                // 이미 탐색한 경우 저장한 값을 사용
                if (dp[ny][nx] > -1) {
                    cnt += dp[ny][nx];

                    // 아닌 경우 탐색하여 더하기
                } else {
                    cnt += dfs(ny, nx);
                }
            }
        }

        dp[y][x] = cnt;
        return cnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer numbers = new StringTokenizer(br.readLine());
        N = Integer.parseInt(numbers.nextToken());
        M = Integer.parseInt(numbers.nextToken());

        board = new int[N][M];

        for (int i = 0; i < N; i++) {
            StringTokenizer input = new StringTokenizer(br.readLine());

            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(input.nextToken());
            }
        }

        dp = new int[N][M];

        // 방문리스트와 메모이제이션을 함께 하기 위해 -1로 초기화
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dp[i][j] = -1;
            }
        }

        System.out.println(dfs(0, 0));
    }
}