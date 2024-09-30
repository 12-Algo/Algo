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
    private static char[][][] field;
    private static int L, R, C;
    private static int[] dz = {-1, 1, 0, 0, 0, 0};
    private static int[] dy = {0, 0, -1, 0, 1, 0};
    private static int[] dx = {0, 0, 0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        while (true) {
            token = new StringTokenizer(br.readLine());
            L = Integer.parseInt(token.nextToken());
            R = Integer.parseInt(token.nextToken());
            C = Integer.parseInt(token.nextToken());
            if (L == 0 && R == 0 && C == 0) break;
            field = new char[L][R][C];
            int sz = 0, sy = 0, sx = 0;
            for (int i = 0; i < L; i++) {
                for (int j = 0; j < R; j++) {
                    String line = br.readLine();
                    for (int k = 0; k < C; k++) {
                        field[i][j][k] = line.charAt(k);
                        if (field[i][j][k] == 'S') {
                            sz = i;
                            sy = j;
                            sx = k;
                        }
                    }
                }
                br.readLine();
            }
            int minute = solve(sz, sy, sx);
            if (minute != -1) sb.append("Escaped in " + minute + " minute(s).\n");
            else sb.append("Trapped!\n");
        }
        System.out.println(sb.toString());
    }

    private static int solve(int sz, int sy, int sx) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{sz, sy, sx});
        int minute = 0;
        while (!queue.isEmpty()) {
            int length = queue.size();
            minute++;
//            System.out.println(queue.size());
            for (int i = 0; i < length; i++) {
                int[] cur = queue.poll();
                int z = cur[0], y = cur[1], x = cur[2];
                for (int dir=0;dir<6;dir++) {
                    int nz = z + dz[dir];
                    int ny = y + dy[dir];
                    int nx = x + dx[dir];
                    if (checkToMove(nz, ny, nx)) {
                        if (field[nz][ny][nx] == 'E') return minute;
                        field[nz][ny][nx] = 'v';
                        queue.offer(new int[]{nz, ny, nx});
                    }
                }
            }
        }
        return -1;
    }

    private static boolean checkToMove(int nz, int ny, int nx) {
        return nz >= 0 && nz < L && ny >= 0 && ny < R && nx >= 0 && nx < C && (field[nz][ny][nx] == '.' || field[nz][ny][nx] == 'E');
    }
}


