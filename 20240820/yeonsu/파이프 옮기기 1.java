import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    
    static int[] dy = { 0, 1, 1 };
    static int[] dx = { 1, 1, 0 };
    static int n;
    static int[][] pipe;
    static int[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token;
        token = new StringTokenizer(br.readLine());
        n = Integer.parseInt(token.nextToken());
        pipe = new int[n][n];
        dp = new int[3][n][n];
        for (int i = 0; i < 3; i++) {
            for (int[] row : dp[i]) {
                Arrays.fill(row, -1);
            }
        }

        for (int i = 0; i < n; i++) {
            token = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                pipe[i][j] = Integer.parseInt(token.nextToken());
            }
        }
        System.out.println(findRoot(0, 1, 0));

    }

    private static int findRoot(int y, int x, int prevDir) {
        if (y == n - 1 && x == n - 1) {
            return 1;
        }
        int ret = 0;
        for (int dir=0;dir<3;dir++) {
            int ny = y + dy[dir];
            int nx = x + dx[dir];
            if (Math.abs(prevDir - dir) == 2) continue;
            if (dp[dir][y][x] != -1) ret += dp[dir][y][x];
            else {
                if (check(ny, nx, dir)) {
                    dp[dir][y][x] = findRoot(ny, nx, dir);
                    ret += dp[dir][y][x];
                }
            }
        }
        return ret;
    }

    private static boolean check(int y, int x, int dir) {
        if (dir == 1) {
            if (!check(y - 1, x, 0))
                return false;
            if (!check(y, x - 1, 2))
                return false;
        }
        return y >= 0 && y < n && x >= 0 && x < n && (pipe[y][x] != 1);
    }
}