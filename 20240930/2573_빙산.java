import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] map;
	static boolean[][] visited;

	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	public static void main(String[] args) throws Exception {
		getInput();

		int answer = 0;
		while (true) {
			int cnt = 0;
			visited = new boolean[N][M];

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] > 0 && !visited[i][j]) {
						dfs(i, j);
						cnt++;
					}
				}
			}

			if (cnt == 0) { // 녹지 않은 빙하를 발견하지 못했으면
				System.out.println(0);
				break;
			} else if (cnt > 1) {// 빙하 덩어리가 2개 이상이면
				System.out.println(answer);
				break;
			}

			answer++;
		}
	}

	private static void dfs(int x, int y) {
		visited[x][y] = true;
		int melt = 0;

		// 내려가는 해수면
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx < 0 || nx >= N || ny < 0 || ny >= M)
				continue;
			if (map[nx][ny] <= 0)
				melt++;
		}

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx < 0 || nx >= N || ny < 0 || ny >= M)
				continue;
			if (map[nx][ny] <= 0 || visited[nx][ny])
				continue;

			dfs(nx, ny);
		}

		map[x][y] -= melt;
	}

	private static void getInput() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
}

