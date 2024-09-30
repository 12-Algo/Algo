import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer token;
    private static int K, W, H;
    private static int[][] field;
    private static boolean[][][] visited;
    private static int[] dy = {-1, 1, 0, 0, -2, -2, 2, 2, -1, -1, 1, 1};
    private static int[] dx = {0, 0, -1, 1, -1, 1, -1, 1, -2, 2, -2, 2};
    public static void main(String[] args) throws IOException {
        K = Integer.parseInt(br.readLine());
        token = new StringTokenizer(br.readLine());
        W = Integer.parseInt(token.nextToken());
        H = Integer.parseInt(token.nextToken());
        field = new int[H][W];
        visited = new boolean[H][W][K+1];
        for (int i=0;i<H;i++) {
            token = new StringTokenizer(br.readLine());
            for (int j=0;j<W;j++) {
                field[i][j] = Integer.parseInt(token.nextToken());
            }
        }

        int answer = solve();
        System.out.println(answer);
    }

    private static int solve() {
        if (field[0][0] == 1) return -1;
        if (H == 1 && W == 1) return 0;

        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0, 0, K, 0}); // row, col, remaining horse moves, total moves
        visited[0][0][K] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int row = cur[0];
            int col = cur[1];
            int k = cur[2];
            int move = cur[3];

            if (row == H - 1 && col == W - 1) return move;

            for (int dir = 0; dir < 12; dir++) {
                if (k == 0 && dir > 3) break;
                int ny = row + dy[dir];
                int nx = col + dx[dir];
                int nk = dir > 3 ? k - 1 : k;

                if (nx < 0 || ny < 0 || nx >= W || ny >= H || field[ny][nx] == 1) {
                    continue;
                }
                if (visited[ny][nx][nk]) {
                    continue;
                }
                visited[ny][nx][nk] = true;
                q.offer(new int[]{ny, nx, nk, move + 1});
            }
        }
        return -1;
    }
}