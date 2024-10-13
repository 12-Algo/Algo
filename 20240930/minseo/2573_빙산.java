import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class test {
    static int N, M, map[][], map2[][];
    static int dx[] = { -1, 1, 0, 0 };
    static int dy[] = { 0, 0, -1, 1 };
    static boolean visited[][];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        List<int[]> notMeltedYet = new ArrayList<>();
        int time = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] != 0) {
                    notMeltedYet.add(new int[] { i, j });
                }
            }
        }

        if (notMeltedYet.size() <= 1) {
            System.out.println(0);
            return;
        }

        while (true) {
            time++;

            List<int[]> melted = new ArrayList<>();

            for (int i = 0; i < notMeltedYet.size(); i++) {
                int node[] = notMeltedYet.get(i);

                int x = node[0];
                int y = node[1];

                int cnt = countZeroAroundMe(x, y);

                if (Math.max(map[x][y] - cnt, 0) == 0) {
                    melted.add(new int[] { x, y });
                    notMeltedYet.remove(i);
                    i--; // 중요!
                } else {
                    map[x][y] = map[x][y] - cnt;
                }
            }

            for (int i = 0; i < melted.size(); i++) {
                int x = melted.get(i)[0];
                int y = melted.get(i)[1];
                map[x][y] = 0;
            }

            if (notMeltedYet.size() <= 1) {
                System.out.println(0);
                return;
            }

            if (isSeperated()) {
                System.out.println(time);
                return;
            }
        }

    }

    static boolean isSeperated() {
        int cnt = 0;
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != 0 && bfs(i, j, cnt)) {
                    cnt++;
                }
                if (cnt >= 2)
                    return true;
            }
        }
        return false;
    }

    static boolean bfs(int startX, int startY, int cnt) {
        if (visited[startX][startY])
            return false;
        if (cnt >= 1)
            return true;
        Deque<int[]> q = new ArrayDeque<>();
        q.add(new int[] { startX, startY });
        visited[startX][startY] = true;

        while (!q.isEmpty()) {
            int node[] = q.pollLast();

            for (int i = 0; i < 4; i++) {
                int nx = node[0] + dx[i];
                int ny = node[1] + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                    continue;
                }

                if (map[nx][ny] != 0 && !visited[nx][ny]) {
                    q.add(new int[] { nx, ny });
                    visited[nx][ny] = true;
                }
            }
        }
        return true;
    }

    static int countZeroAroundMe(int x, int y) {
        int cnt = 0;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                continue;
            }
            if (map[nx][ny] == 0) {
                cnt++;
            }
        }

        return cnt;
    }
}
