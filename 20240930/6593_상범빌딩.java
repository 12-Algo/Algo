import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();

	static int L, R, C;
	static int[][][] building;

	static Node startNode;
	static Node endNode;

	static int[] dl = { 1, -1, 0, 0, 0, 0 };
	static int[] dr = { 0, 0, 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 0, 0, 1, -1 };

	public static void main(String[] args) throws Exception {
		StringTokenizer st;

		while (true) {
			st = new StringTokenizer(br.readLine());
			L = Integer.parseInt(st.nextToken());

			if (L == 0)
				break;
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());

			building = new int[L][R][C];

			getInput();

			bfs();
		}
		System.out.println(sb);
	}

	private static void bfs() {
		Queue<Node> queue = new ArrayDeque<>();
		queue.add(startNode);

		while (!queue.isEmpty()) {
			Node now = queue.poll();

			if(endNode.sameLocation(now))
				break;
			
			for (int i = 0; i < 6; i++) {
				int nl = now.l + dl[i];
				int nr = now.r + dr[i];
				int nc = now.c + dc[i];

				if (nl < 0 || nl >= L || nr < 0 || nr >= R || nc < 0 || nc >= C)
					continue;
				if (building[nl][nr][nc] <= now.time + 1)
					continue;

				building[nl][nr][nc] = now.time + 1;
				Node next = new Node(nl, nr, nc, building[nl][nr][nc]);
				if(endNode.sameLocation(next)) {
					sb.append("Escaped in ").append(next.time).append(" minute(s).\n");
					return;
				}
				queue.add(next);
			}
		}
		
		sb.append("Trapped!\n");
	}

	private static void getInput() throws Exception {

		for (int l = 0; l < L; l++) {
			for (int r = 0; r < R; r++) {
				String line = br.readLine();
				for (int c = 0; c < C; c++) {
					char input = line.charAt(c);
					// ., E, S
					int value = Integer.MAX_VALUE;

					if (input == '#') {
						value = -1;
					} else if (input == 'S') {
						startNode = new Node(l, r, c, 0);
						value = 0;
					} else if (input == 'E') {
						endNode = new Node(l, r, c, Integer.MAX_VALUE);
					}

					building[l][r][c] = value;
				}
			}
			br.readLine();
		}
	}
	
	static class Node {
		int l, r, c;
		int time;

		public Node(int l, int r, int c, int time) {
			super();
			this.l = l;
			this.r = r;
			this.c = c;
			this.time = time;
		}

		public boolean sameLocation(Node other) {
			if (this.l != other.l)
				return false;
			if (this.r != other.r)
				return false;
			if (this.c != other.c)
				return false;

			return true;
		}
	}
}
