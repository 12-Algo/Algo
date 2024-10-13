import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, map[][], room, maxArea;
    static boolean visited[][];
    static int dx[] = { 0, -1, 0, 1 };
    static int dy[] = { -1, 0, 1, 0 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[M][N];
        visited = new boolean[M][N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 값 초기화
        room = 0;
        maxArea = 0;

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    maxArea = Math.max(maxArea, bfs(i, j));
                    room++;
                }
            }
        }

        System.out.println(room);
        System.out.println(maxArea);

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                // 1, 2, 4, 8
                for (int k = 1; k <= 8; k <<= 1) {
                    // 만약 k에 해당하는 벽이 있다면
                    if ((map[i][j] & k) != 0) {
                        // 현재 위치 벽 제거
                        map[i][j] -= k;

                        // 새삥
                        visited = new boolean[M][N];
                        maxArea = Math.max(maxArea, bfs(i, j));
                        // 벽 원상복구
                        map[i][j] += k;
                    }
                }
            }
        }
        System.out.println(maxArea);
    }

    static int bfs(int startX, int startY) {
        Queue<int[]> q = new ArrayDeque<>();

        q.add(new int[] { startX, startY });
        visited[startX][startY] = true;

        int cnt = 1;

        while (!q.isEmpty()) {
            int node[] = q.poll();

            int wall = 1;

            for (int i = 0; i < 4; i++) {
                if ((map[node[0]][node[1]] & (1 << i)) == 0) {
                    // 만약 뚫려있으면
                    int nx = node[0] + dx[i];
                    int ny = node[1] + dy[i];

                    if (nx < 0 || nx >= M || ny < 0 || ny >= N)
                        continue;

                    if (!visited[nx][ny]) {
                        // 방문하지 않은 칸이면
                        visited[nx][ny] = true;
                        cnt++;
                        q.add(new int[] { nx, ny });
                    }
                }
                wall <<= 1;
            }
        }
        return cnt;
    }
}