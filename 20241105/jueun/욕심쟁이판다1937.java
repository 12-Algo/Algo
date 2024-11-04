import java.util.*;
import java.io.*;

class Main {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    
    static int N;
    static int[][] map;
    static int[][] dp; //dp[i][j] : i, j에서 더 갈 수 있는 칸 수

    static int answer = 0;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        dp = new int[N][N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int x=0; x<N; x++) {
            for(int y=0; y<N; y++) {
                answer = Math.max(answer, dfs(x, y, 1));
            }
        }
        
        System.out.println(answer);
    }

    private static int dfs(int x, int y, int cnt) {       
        if(dp[x][y] != 0) {
            return dp[x][y]+cnt-1;
        }
        
        int max = cnt;
        for(int i=0; i<4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx < 0 || nx >= N || ny < 0 || ny >= N)
                continue;

            if(map[x][y] < map[nx][ny])
                max = Math.max(max, dfs(nx, ny, cnt+1));
        }

        dp[x][y] = max - cnt + 1;
        return max;
    }
}
