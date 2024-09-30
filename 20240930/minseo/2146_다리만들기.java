import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, map[][], visited[][], cnt, visited2[][], length;
    static int dx[] = { 1, -1, 0, 0 };
    static int dy[] = { 0, 0, -1, 1 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine()); // 지도의 크기

        map = new int[N][N];
        visited = new int[N][N];
        visited2 = new int[N][N];

        cnt = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1)
                    fill(i, j);
            }
        }
        length = Integer.MAX_VALUE;

        // for(int i = 0; i < N; i++) {
        // for(int j = 0; j < N; j++) {
        // System.out.print(visited[i][j]+" ");
        // }
        // System.out.println();
        // }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1)
                    bfs(i, j);
            }
        }
        System.out.println(length - 1);
    }

    static void fill(int startX, int startY) {
        if (visited[startX][startY] != 0)
            return;

        cnt++;

        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[] { startX, startY });
        visited[startX][startY] = cnt;

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N)
                    continue;

                if (visited[nx][ny] != 0)
                    continue;

                if (map[nx][ny] == 1) {
                    visited[nx][ny] = cnt;
                    q.offer(new int[] { nx, ny });
                }

            }
        }
    }

    static int bfs(int startX, int startY) {
        if (visited[startX][startY] == 0)
            return 0;
        int continent = visited[startX][startY];
        visited2 = new int[N][N];

        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[] { startX, startY, 0 });

        while (!q.isEmpty()) {
            int node[] = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = node[0] + dx[i];
                int ny = node[1] + dy[i];
                int time = node[2] + 1;

                if (nx < 0 || ny < 0 || nx >= N || ny >= N)
                    continue;

                if (visited[nx][ny] != continent && visited[nx][ny] != 0) {
                    // 신대륙 발견
                    length = Math.min(time, length);
                }

                if (visited[nx][ny] == 0 && visited2[nx][ny] == 0) {
                    visited2[nx][ny] = 1;
                    q.offer(new int[] { nx, ny, time });
                }
            }

        }
        return length;
    }
}