import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
	static final int INF = Integer.MAX_VALUE;

	static int K, W, H;
	static int[][] map;
	static boolean[][][] visited;

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	static int[] hdx = { -1, -2, -2, -1, 1, 2, 2, 1 };
	static int[] hdy = { -2, -1, 1, 2, -2, -1, 1, 2 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		K = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		map = new int[H][W];
		visited = new boolean[H][W][K + 1];

		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		System.out.println(bfs());
	}

	private static int bfs() {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] { 0, 0, 0, 0 }); // r, c, 이동횟수, 말점프를 했는지?
		visited[0][0][0] = true;

		while (!queue.isEmpty()) {
			int[] curr = queue.poll();

//            System.out.printf("[%d, %d] %d %d\n", curr[0], curr[1], curr[2],curr[3]);

			int x = curr[0];
			int y = curr[1];
			int d = curr[2] + 1;
			int jump = curr[3];

			if (x == H - 1 && y == W - 1) {
				return curr[2];
			}

//            System.out.printf("[%d, %d] %d %d\n", x, y, d, jump);

			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				if (nx < 0 || nx >= H || ny < 0 || ny >= W || map[nx][ny] == 1)
					continue;

				if (visited[nx][ny][jump])
					continue;

				visited[nx][ny][jump] = true;
				queue.add(new int[] { nx, ny, d, jump });
			}

			// 말점프 다 했으면 넘어감.
			if (jump == K)
				continue;

			jump++;
			for (int i = 0; i < 8; i++) {
				int nx = x + hdx[i];
				int ny = y + hdy[i];

				if (nx < 0 || nx >= H || ny < 0 || ny >= W || map[nx][ny] == 1)
					continue;
//                System.out.printf("\t[%d, %d]\n", nx,ny);

				if (visited[nx][ny][jump])
					continue;

				visited[nx][ny][jump] = true;
				queue.add(new int[] { nx, ny, d, jump });
			}
		}

		return -1;
	}
}
