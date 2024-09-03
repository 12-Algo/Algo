
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    static final int HORIZONTAL = 0;
    static final int VERTICAL = 1;
    static final int DIAGONAL = 2;

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int result = 0;

        int N = Integer.parseInt(st.nextToken());
        int[][] arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Deque<int[]> dq = new ArrayDeque<int[]>();
        dq.addLast(new int[]{0, 1, HORIZONTAL});

        while (dq.size() != 0) {
            int[] pipe = dq.pollFirst();

            int y = pipe[0];
            int x = pipe[1];
            int type = pipe[2];

            if (x == N - 1 && y == N - 1) {
                result++;
                continue;
            }

            if (type == 0) { //가로면!
                if (x + 1 < N && arr[y][x + 1] != 1) {
                    dq.addLast(new int[]{y, x + 1, 0});
                }

                if (x + 1 < N && y + 1 < N && arr[y][x + 1] != 1 && arr[y + 1][x] != 1 && arr[y + 1][x + 1] != 1) {
                    dq.addLast(new int[]{y + 1, x + 1, 2});
                }
            } else if (type == 1) { //세로면
                if (y + 1 < N && arr[y + 1][x] != 1) {
                    dq.addLast(new int[]{y + 1, x, 1});
                }
                if (x + 1 < N && y + 1 < N && arr[y][x + 1] != 1 && arr[y + 1][x] != 1 && arr[y + 1][x + 1] != 1) {
                    dq.addLast(new int[]{y + 1, x + 1, 2});
                }
            } else if (type == 2) { //대각선이면
                if (x + 1 < N && arr[y][x + 1] != 1) {
                    dq.addLast(new int[]{y, x + 1, 0});
                }
                if (y + 1 < N && arr[y + 1][x] != 1) {
                    dq.addLast(new int[]{y + 1, x, 1});
                }
                if (x + 1 < N && y + 1 < N && arr[y][x + 1] != 1 && arr[y + 1][x] != 1 && arr[y + 1][x + 1] != 1) {
                    dq.addLast(new int[]{y + 1, x + 1, 2});
                }
            }
        }
        System.out.println(result);
    }
}


/*
 * 가로면? 옆에 + 옆아래대각 확인 후 이동
 * 세로면? 아래 + 옆아래대각 확인 후 이동
 * 대각선? 옆 + 아래 + 옆아래대각 확인 후 이동
 * 
 * 끝점이 위치한 좌표랑 현재 상태(가로, 세로, 대각선)
 */
