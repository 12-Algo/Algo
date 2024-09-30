import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static int N, M, D;
    static int[][] castle;
    static int ans = 0;
    static int[][] delta = { { 0, -1 }, { -1, 0 }, { 0, 1 } };

    // BFS 탐색으로 가장 가깝고 왼쪽에 있는 적의 위치 반환
    // 거리가 넘어가면 {-1, -1} return
    static int[] bfs(int i, int j, int[][] copyCastle) {
        boolean[][] check = new boolean[N + 1][M];
        check[i][j] = true;

        Deque<int[]> q = new ArrayDeque<>();
        int[] start = { i, j, 0 };
        q.add(start);

        while (!q.isEmpty()) {
            int[] now = q.poll();
            int y = now[0];
            int x = now[1];
            int d = now[2];

            if (d > D) {
                int[] res = { -1, -1 };
                return res;
            }

            if (copyCastle[y][x] == 1) {
                int[] res = { y, x };
                return res;
            }

            for (int k = 0; k < 3; k++) {
                int ny = y + delta[k][0];
                int nx = x + delta[k][1];

                if (ny >= 0 && ny < N + 1 && nx >= 0 && nx < M && !check[ny][nx]) {
                    check[ny][nx] = true;
                    int[] next = { ny, nx, d + 1 };
                    q.add(next);
                }
            }
        }

        int[] res = { -1, -1 };
        return res;
    }

    static void calc(int[] archer) {
        int cnt = 0;
        int[][] enemy = new int[3][2];
        int[][] copyCastle = new int[N + 1][M];

        // 캐슬 배열 복사
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                copyCastle[i][j] = castle[i][j];
            }
        }

        // 궁수가 있는 줄부터 시작해서 한 칸씩 올라가기
        for (int i = N; i > 0; i--) {
            // 궁수가 있는 줄 0으로 초기화
            for (int j = 0; j < M; j++) {
                copyCastle[i][j] = 0;
            }

            // 각 궁수가 쏘는 적의 위치 배열에 담기
            for (int j = 0; j < 3; j++) {
                enemy[j] = bfs(i, archer[j], copyCastle);
            }

            // 같이 쏘는 경우를 고려해서 카운트
            for (int[] p : enemy) {
                if (p[0] != -1 && copyCastle[p[0]][p[1]] == 1) {
                    copyCastle[p[0]][p[1]] = 0;
                    cnt++;
                }
            }
        }

        ans = Math.max(ans, cnt);
    }

    // 궁수의 x좌표 저장하고 3명 조합 생성 시 계산
    static void combi(int depth, int[] archer, int start, int check) {
        if (depth == 3) {
            calc(archer);
            return;
        }

        for (int i = start; i < M; i++) {
            archer[depth] = i;
            combi(depth + 1, archer, i + 1, check | (1 << i)); // 비트마스킹
        }
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        System.setIn(new FileInputStream("src/algorithm/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer numbers = new StringTokenizer(br.readLine());
        N = Integer.parseInt(numbers.nextToken());
        M = Integer.parseInt(numbers.nextToken());
        D = Integer.parseInt(numbers.nextToken());
        // 궁수가 있는 줄도 필요해서 N+1행
        castle = new int[N + 1][M];

        for (int i = 0; i < N; i++) {
            StringTokenizer line = new StringTokenizer(br.readLine());

            for (int j = 0; j < M; j++) {
                castle[i][j] = Integer.parseInt(line.nextToken());
            }
        }

        // 조합 생성
        combi(0, new int[3], 0, 0);
        System.out.println(ans);
    }
}
