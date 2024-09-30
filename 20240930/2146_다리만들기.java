import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
	static int N;
	static int answer = Integer.MAX_VALUE;
	static int[][] dist;
	static int[][] cities;
	static Queue<int[]> queue = new ArrayDeque<>();

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		getInput();
		markCity();
		bfs();

		System.out.println(answer);
	}

	public static void bfs() {

		while (!queue.isEmpty()) {
			int[] curr = queue.poll();

			for (int k = 0; k < 4; k++) {
				int nr = curr[0] + dr[k];
				int nc = curr[1] + dc[k];

				if (nr < 0 || nr >= N || nc < 0 || nc >= N)
					continue;

				// 바다의 경우 영역 확장
				if (dist[nr][nc] == -1) {
					queue.add(new int[] { nr, nc, curr[2] });

					cities[nr][nc] = curr[2];
					dist[nr][nc] = dist[curr[0]][curr[1]] + 1;
				}
				// 다른 섬을 만났을 경우
				else if (cities[nr][nc] != curr[2]) {
					answer = Math.min(answer, dist[nr][nc] + dist[curr[0]][curr[1]]);
				}
			}
		}
	}

	public static void markCity() {
		Queue<int[]> q = new ArrayDeque<>();
		int city = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (dist[i][j] == 0 && cities[i][j] == 0) {
					q.add(new int[] { i, j, ++city });
					cities[i][j] = city;
				}

				// flood fill
				while (!q.isEmpty()) {
					int[] curr = q.poll();
					
					// 섬을 저장
					queue.add(curr);

					for (int k = 0; k < 4; k++) {
						int nr = curr[0] + dr[k];
						int nc = curr[1] + dc[k];

						if (nr < 0 || nr >= N || nc < 0 || nc >= N)
							continue;
						if (dist[nr][nc] == 0 && cities[nr][nc] == 0) {
							cities[nr][nc] = city;
							q.add(new int[] { nr, nc, city });
						}
					}
				}
			}
		}
	}

	public static void getInput() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		dist = new int[N][N];
		cities = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				if (st.nextToken().equals("0"))
					dist[i][j] = -1;
			}
		}

		// 완료 시, dist[i][j] == 0 -> 땅 / dist[i][j] == -1 -> 바다
	}
}