import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer token;
    static int N, M;    //N은 세로 크기 M은 가로 크기
    static int[][] field;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        token = new StringTokenizer(br.readLine());
        M = Integer.parseInt(token.nextToken());
        N = Integer.parseInt(token.nextToken());
        field = new int[N][M];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                field[i][j] = input.charAt(j) - '0';
            }
        }

        System.out.println(findMinCrush());
    }

    private static int findMinCrush() {
        Queue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        int[][] count = new int[N][M];
        for (int[] row : count) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        pq.offer(new int[]{0, 0, 0});

        while (!pq.isEmpty()) {
            //가장 벽을 적게 벽을 부순 칸을 먼저 뽑아주세요.
            int[] cur = pq.poll();

            //방문하지 않았다면, 방문 처리를 하고 진행해주세요.
            if (count[cur[0]][cur[1]] < cur[2]) continue;

            count[cur[0]][cur[1]] = cur[2];
            if (cur[0] == N-1 && cur[1] == M-1) break;

            for (int dir=0;dir<4;dir++) {
                int y = cur[0] + dy[dir];
                int x = cur[1] + dx[dir];
                //범위 내에 있는 지 체크.
                if (y >= 0 && y < N && x >= 0 && x < M) {
                    //0이면서 그 노드의 방 부신 개수가 기존보다 더 작으면 그 값을 넣어줌.
//                    System.out.println(y + " " + x);
                    if (field[y][x] == 0 && count[y][x] > cur[2]) {
                        count[y][x] = cur[2];
                        pq.offer(new int[]{y, x, cur[2]});
                    }
                    //1이면서 다음 경로까지 방 부신개수 + 1이 기존 값보다 작으면 그 값을 넣어줌.
                    else if (field[y][x] == 1 && count[y][x] > cur[2] + 1) {
                        count[y][x] = cur[2] + 1;
                        pq.offer(new int[]{y, x, cur[2] + 1});
                    }
                }
            }
        }
        return count[N-1][M-1];
    }
}
