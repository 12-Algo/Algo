import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer token;
    private static int[][] field;
    private static boolean[][] visited;
    private static int[] dy = {0, -1, 0, 1};
    private static int[] dx = {-1, 0, 1, 0};
    private static int roomCnt;
    private static int roomSize;
    private static int roomSizeAfterDeleteWall;

    public static void main(String[] args) throws IOException {
        token = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(token.nextToken());
        int m = Integer.parseInt(token.nextToken());
        field = new int[m][n];
        visited = new boolean[m][n];
        roomCnt = 0;
        roomSize = 0;
        roomSizeAfterDeleteWall = 0;
        //m개의 줄에 n개의 정수로 벽에 대한 정보.
        for (int i=0;i<m;i++) {
            token = new StringTokenizer(br.readLine());
            for (int j=0;j<n;j++) {
                field[i][j] = Integer.parseInt(token.nextToken());
            }
        }

        //1,2번 구하기
        for (int i=0;i<m;i++) {
            for (int j=0;j<n;j++) {
                if (!visited[i][j]) {
                    visited[i][j] = true;
                    roomSize = Math.max(roomSize, dfs(i, j));
                    roomCnt++;
                }
            }
        }
        visited = new boolean[m][n];
        //3번 구하기
        for (int i=0;i<m;i++) {
            for (int j=0;j<n;j++) {
                if (field[i][j] > 0 && !visited[i][j]) {
                    for (int dir=0;dir<4;dir++) {
                        //벽이 있을 때 벽 너머의 방의 크기를 계산해서 더해줌.
                        if ((field[i][j] & (1 << dir)) != 0) {
                            int ny = i + dy[dir];
                            int nx = j + dx[dir];
                            if (!checkInField(ny, nx)) continue;
                            visited = new boolean[m][n];
                            visited[i][j] = true;
                            visited[ny][nx] = true;
                            roomSizeAfterDeleteWall = Math.max(roomSizeAfterDeleteWall, dfs(i, j) + dfs(ny, nx));
                        }
                    }

                }
            }
        }

        System.out.println(roomCnt);
        System.out.println(roomSize);
        System.out.println(roomSizeAfterDeleteWall);
    }

    private static boolean checkInField(int ny, int nx) {
        return ny >= 0 && ny < field.length && nx >= 0 && nx < field[0].length;
    }

    public static int dfs(int y, int x) {
        int cnt = 1;
        for (int i=0;i<4;i++) {
            if ((field[y][x] & (1 << i)) == 0) {
                int ny = y + dy[i];
                int nx = x + dx[i];
                if (visited[ny][nx]) continue;
                visited[ny][nx] = true;
                cnt += dfs(ny, nx);
            }
        }
        return cnt;
    }
}


