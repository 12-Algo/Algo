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
    private static int N;
    private static int[][] field;
    private static boolean[][] visited;
    private static int[] dy = {-1, 1, 0, 0};
    private static int[] dx = {0, 0, -1, 1};
    private static int answer;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        field = new int[N][N];
        answer = Integer.MAX_VALUE;
        for (int i=0;i<N;i++) {
            token = new StringTokenizer(br.readLine());
            for (int j=0;j<N;j++) {
                field[i][j] = Integer.parseInt(token.nextToken());
            }
        }

        int mark = 2;
        for (int i=0;i<N;i++) {
            for (int j=0;j<N;j++) {
                if (field[i][j] == 1) {
                    field[i][j] = mark;
                    MarkWithNum(i, j, mark++);
                }
            }
        }

//        for (int[] f : field) {
//            for (int ff : f) {
//                System.out.print(ff + " ");
//            }
//            System.out.println();
//        }

        for (int i=0;i<N;i++) {
            for (int j=0;j<N;j++) {
                if (field[i][j] != 0) {
                    int temp = getMinPathWithOnotherIsland(i, j, field[i][j]);
                    if (temp == -1) continue;
                    answer = Math.min(answer, temp);
                }
            }
        }
        System.out.println(answer);
    }

    private static int getMinPathWithOnotherIsland(int i, int j, int mark) {
        Queue<int[]> q = new ArrayDeque<>();

        q.offer(new int[]{i, j, mark});
        visited = new boolean[N][N];
        visited[i][j] = true;
        int move = 0;
        while (!q.isEmpty()) {
            int length = q.size();
            while (length-- > 0) {
                int[] cur = q.poll();
                int y = cur[0];
                int x = cur[1];

                for (int dir=0;dir<4;dir++) {
                    int ny = y + dy[dir];
                    int nx = x + dx[dir];
                    if (nx < 0 || ny < 0 || nx >= N || ny >= N || field[ny][nx] == mark || visited[ny][nx]) continue;
                    visited[ny][nx] = true;
                    if (field[ny][nx] == 0) {
                        q.offer(new int[]{ny, nx, mark});
                    } else return move;
                }
            }
            move++;
        }
        return -1;
    }


    private static void MarkWithNum(int y, int x, int mark) {

        for (int dir=0;dir<4;dir++) {
            int ny = y + dy[dir];
            int nx = x + dx[dir];
            if (nx < 0 || ny < 0 || nx >= N || ny >= N || field[ny][nx] != 1) continue;
            field[ny][nx] = mark;
            MarkWithNum(ny, nx, mark);
        }
    }

}