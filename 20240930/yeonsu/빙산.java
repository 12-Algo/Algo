import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer token;
    private static int[][] field;
    private static int[][] melt;
    private static int N;
    private static int M;
    private static int[] dy = {-1, 1, 0, 0};
    private static int[] dx = {0, 0, -1, 1};
    private static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        field = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            token = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                field[i][j] = Integer.parseInt(token.nextToken());
            }
        }
        int answer = 0;
        int cnt;
        while(true) {
            cnt = 0;
            visited = new boolean[N][M];
            melt = new int[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (field[i][j] > 0 && !visited[i][j]) {
                        visited[i][j] = true;
                        dfs(i, j);
                        cnt++;
                    }
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (melt[i][j] > 0 && visited[i][j]) {
                        field[i][j] -= melt[i][j];
                    }
                }
            }
            if (cnt > 1) break;
            else if (cnt == 0) {
                answer = 0;
                break;
            }
            answer++;
        };
        System.out.println(answer);
    }

    private static void dfs(int y, int x) {
        for (int i=0;i<4;i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (check(ny, nx)) {
                if (field[ny][nx] <= 0) {
                    melt[y][x]++;
                } else if (field[ny][nx] > 0 && !visited[ny][nx]) {
                    visited[ny][nx] = true;
                    dfs(ny, nx);
                }
            }
        }
    }

    private static boolean check(int y, int x) {
        return y >= 0 && y < N && x >= 0 && x < M;
    }
}


