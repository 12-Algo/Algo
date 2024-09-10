import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int N;
    static char[][] picture;
    static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    private static void view(int y, int x, boolean[][] check, boolean isColorWeak) {
        Queue<int[]> q = new LinkedList<>();
        int[] start = { y, x };
        q.add(start);
        check[y][x] = true;
        // R과 G를 모두 확인해야 하는 경우를 위해 target을 담을 리스트
        ArrayList<Character> target = new ArrayList<>();

        if (isColorWeak && (picture[y][x] == 'R' || picture[y][x] == 'G')) {
            target.add('R');
            target.add('G');
        } else {
            target.add(picture[y][x]);
        }

        while (!q.isEmpty()) {
            int[] now = q.poll();

            for (int i = 0; i < 4; i++) {
                int ny = now[0] + delta[i][0];
                int nx = now[1] + delta[i][1];

                if (ny >= 0 && ny < N && nx >= 0 && nx < N && !check[ny][nx] && target.contains(picture[ny][nx])) {
                    check[ny][nx] = true;
                    int[] next = { ny, nx };
                    q.add(next);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        picture = new char[N][N];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();

            for (int j = 0; j < N; j++) {
                picture[i][j] = line.charAt(j);
            }
        }

        // 정상
        boolean[][] check = new boolean[N][N];
        int normal = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!check[i][j]) {
                    // bfs 탐색 + 구역 수 계산
                    view(i, j, check, false);
                    normal++;
                }
            }
        }

        // 적록색약
        check = new boolean[N][N];
        int colorWeak = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!check[i][j]) {
                    view(i, j, check, true);
                    colorWeak++;
                }
            }
        }

        System.out.println(normal + " " + colorWeak);
    }

}