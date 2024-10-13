import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static int N, M;

	static int[][] map; // 이동정보를 담고 있는 배열
	static int[][] room; // 방이름을 담고 있는 배열

	static final int WEST = 1;
	static final int NORTH = 2;
	static final int EAST = 4;
	static final int SOUTH = 8;

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int[] wall = { NORTH, SOUTH, WEST, EAST };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		room = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int num = 1, max = 1;
		Map<Integer, Integer> area = new HashMap<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (room[i][j] == 0) {
					int result = dfs(i, j, num, 1);
					max = Math.max(max, result);
					area.put(num, result);

					num++;
				}
			}
		}
		sb.append(num - 1).append('\n');
		sb.append(max).append('\n');

		for (int x = 0; x < N; x++) {
			for (int y = 0; y < M; y++) {

				for (int i = 0; i < 4; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];

					if (nx < 0 || nx >= N || ny < 0 || ny >= M || room[x][y] == room[nx][ny]
							|| (map[x][y] & wall[i]) == 0)
						continue;

					int result = area.get(room[x][y]) + area.get(room[nx][ny]);
					max = Math.max(max, result);
				}
			}
		}
		sb.append(max);

		System.out.println(sb);
	}

	private static int dfs(int x, int y, int num, int cnt) {
		room[x][y] = num;

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx < 0 || nx >= N || ny < 0 || ny >= M)
				continue;
			if ((map[x][y] & wall[i]) != 0 || room[nx][ny] != 0)
				continue;

			cnt = dfs(nx, ny, num, cnt + 1);
		}

		return cnt;
	}
}
