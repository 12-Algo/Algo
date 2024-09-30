import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] board;
    static int[] root;
    static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    static int find(int v) {
        if (v == root[v]) {
            return v;
        }

        return root[v] = find(root[v]);
    }

    static void union(int y1, int x1, int y2, int x2) {
        int aRoot = find(y1 * M + x1);
        int bRoot = find(y2 * M + x2);

        if (aRoot != bRoot) {
            if (aRoot < bRoot) {
                root[bRoot] = aRoot;
            } else {
                root[aRoot] = bRoot;
            }
        }
    }

    static void explore(int y, int x) {
        while (true) {
            int ny = y + delta[board[y][x]][0];
            int nx = x + delta[board[y][x]][1];

            if (root[y * M + x] == root[ny * M + nx]) {
                return;
            }

            union(y, x, ny, nx);
            y = ny;
            x = nx;
        }
    }

    static int findSet() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (i * M + j == root[i * M + j]) {
                    explore(i, j);
                }
            }
        }

        int cnt = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (i * M + j == root[i * M + j]) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer size = new StringTokenizer(br.readLine());
        N = Integer.parseInt(size.nextToken());
        M = Integer.parseInt(size.nextToken());
        board = new int[N][M];
        root = new int[N * M];
        int r = 0;

        for (int i = 0; i < N; i++) {
            String line = br.readLine();

            for (int j = 0; j < M; j++) {
                int deltaIdx;

                switch (line.charAt(j)) {
                    case 'U':
                        deltaIdx = 0;
                        break;
                    case 'D':
                        deltaIdx = 1;
                        break;
                    case 'L':
                        deltaIdx = 2;
                        break;
                    default:
                        deltaIdx = 3;
                }

                board[i][j] = deltaIdx;
                root[r] = r++;
            }
        }

        System.out.println(findSet());
    }
}