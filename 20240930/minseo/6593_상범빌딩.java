import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int L, R, C;
    static char[][][] map;
    static int dx[] = { -1, 1, 0, 0, 0, 0 };
    static int dy[] = { 0, 0, -1, 1, 0, 0 };
    static int dz[] = { 0, 0, 0, 0, -1, 1 };

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            if (L + R + C == 0) {
                break;
            }
            map = new char[L][R][C];

            int startX = 0;
            int startY = 0;
            int startZ = 0;

            for (int i = 0; i < L; i++) {
                for (int j = 0; j < R; j++) {
                    String s = br.readLine();
                    for (int k = 0; k < C; k++) {
                        map[i][j][k] = s.charAt(k);
                        if (map[i][j][k] == 'S') {
                            startX = k;
                            startY = j;
                            startZ = i;
                            map[i][j][k] = '.';
                        }
                    }
                }
                br.readLine();
            }

            System.out.println(bfs(startX, startY, startZ));
        }

    }

    static String bfs(int x, int y, int z) {

        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[] { x, y, z, 0 });

        boolean[][][] visited = new boolean[L][R][C];
        visited[z][y][x] = true;

        while (!q.isEmpty()) {
            int[] node = q.poll();

            int nx = node[0];
            int ny = node[1];
            int nz = node[2];

            int move = node[3];

            if (map[nz][ny][nx] == 'E') {
                return "Escaped in " + move + " minute(s).";
            }

            for (int i = 0; i < 6; i++) {
                int nx2 = nx + dx[i];
                int ny2 = ny + dy[i];
                int nz2 = nz + dz[i];

                if (nx2 < 0 || ny2 < 0 || nz2 < 0 || nx2 >= C || ny2 >= R || nz2 >= L)
                    continue;

                if (visited[nz2][ny2][nx2])
                    continue;

                if (map[nz2][ny2][nx2] == '.' || map[nz2][ny2][nx2] == 'E') {
                    visited[nz2][ny2][nx2] = true;
                    q.add(new int[] { nx2, ny2, nz2, move + 1 });
                }
            }
        }
        return "Trapped!";
    }
}
