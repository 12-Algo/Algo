import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer token;
    static int field[][], memo[][];
    static int dy[] = {-1, 1, 0, 0};
    static int dx[] = {0, 0, -1, 1};
    static int N;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        field = new int[N][N];
        memo = new int[N][N];

        for (int i=0; i<N; i++) {
            token = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++) {
                field[i][j] = Integer.parseInt(token.nextToken());
            }
        }

        for (int i=0; i<N; i++) {
            for (int j = 0; j < N; j++) {
                memo[i][j] = -1;
            }
        }

        int answer = 0;
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if (memo[i][j] == -1) answer = Math.max(dfs(i, j), answer);
            }
        }

//        for (int i=0; i<N; i++) {
//            for (int j=0; j<N; j++) {
//                System.out.print(memo[i][j] + " ");
//            }
//            System.out.println();
//        }
        System.out.println(answer);
    }

    private static int dfs(int y, int x) {

        if (memo[y][x] != -1) return memo[y][x];

        memo[y][x] = 1;
        for (int dir=0;dir<4;dir++) {
            int ny = y + dy[dir];
            int nx = x + dx[dir];

            if (ny >= 0 && ny < N && nx >= 0 && nx < N) {
//                System.out.println(field[ny][nx] + " " + field[y][x]);
                if (field[ny][nx] > field[y][x]) {
                    memo[y][x] = Math.max(memo[y][x], dfs(ny, nx) + 1);
                }
            }
        }
//        System.out.println(memo[y][x]);
        return memo[y][x];
    }
}