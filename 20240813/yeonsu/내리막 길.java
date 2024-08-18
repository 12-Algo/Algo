import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[][] map;
    static boolean[][] visit;
    static int[][] dp;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0 , -1, 0, 1};
    static int answer;
    public static void main(String[] args) throws IOException {
        getInput();
        visit[0][0] = true;
        answer = dfs(0, 0);
        System.out.println(answer);
    }    
    
    private static int dfs(int i, int j) {
        if (i == n-1 && j == m-1) {
            return 1;
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        dp[i][j] = 0;
        for (int dir=0;dir<4;dir++) {
            int ny = i + dy[dir];
            int nx = j + dx[dir];
            if (ny >= 0 && ny < n && nx >= 0 && nx < m && map[i][j] > map[ny][nx] && !visit[ny][nx]) {
                visit[ny][nx] = true;
                dp[i][j] += dfs(ny, nx);
                visit[ny][nx] =  false;
            }
        }
        return dp[i][j];
    }



    private static void getInput() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(in.readLine());
        n = Integer.parseInt(token.nextToken());
        m = Integer.parseInt(token.nextToken());
        map = new int[n][m];
        visit = new boolean[n][m];
        dp = new int[n][m];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        answer = 0;
        for (int i=0;i<n;i++) {
            token = new StringTokenizer(in.readLine());
            for (int j=0;j<m;j++) {
                map[i][j] = Integer.parseInt(token.nextToken());
            }
        }
    }
}
