import java.io.*;
import java.util.*;

public class Main {

	static int K, W, H, map[][];
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };
	static int[] hr = { -2, -2, 2, 2, -1, 1, -1, 1 };
	static int[] hc = { -1, 1, -1, 1, -2, -2, 2, 2 };
	static int[][][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		K = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new int[H][W];
		visited = new int[K + 1][H][W]; // 0~k번 말을 사용한 map, 총 k+1개

		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}

		int[] start = new int[] { 0, 0, 0 }; // (0,0)까지 말을 0번 씀
		Queue<int[]> q = new ArrayDeque<int[]>();
		q.offer(start);

		// 시작점 넣고 BFS 시작
		while (!q.isEmpty()) {
			int current[] = q.poll();
			int horse = current[0];
			int r = current[1];
			int c = current[2];

			// 목적지 좌표면 해당하는 말 사용횟수에 해당하는 visited map을 참조해 경로 출력
			if (r == H - 1 && c == W - 1) {
				System.out.println(visited[horse][r][c]);
				return;
			}

			// 4방 탐색
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];

				if (nr < 0 || nc < 0 || nr >= H || nc >= W || map[nr][nc] == 1 || visited[horse][nr][nc] != 0)
					continue;

				// 현재 좌표의 말 사용횟수에 해당하는 visited[][]를 업데이트
				// 말을 사용하지 않았으므로 현재 좌표와 같은 visited[][]임
				visited[horse][nr][nc] = visited[horse][r][c] + 1;
				q.offer(new int[] { horse, nr, nc });
			}

			// 아직 말 사용 횟수가 남았으면 8방탐색
			if (horse < K)
				for (int d = 0; d < 8; d++) {
					int nr = r + hr[d];
					int nc = c + hc[d];

					if (nr < 0 || nc < 0 || nr >= H || nc >= W || map[nr][nc] == 1 || visited[horse + 1][nr][nc] != 0)
						continue;

					visited[horse + 1][nr][nc] = visited[horse][r][c] + 1;
					q.offer(new int[] { horse + 1, nr, nc });
				}
		}
		
		// 목적지 좌표에 도달하지 못했다면
		System.out.println(-1);
	}
}