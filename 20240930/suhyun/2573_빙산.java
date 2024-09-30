import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static int N, M;
	private static int[][] board;
	private static boolean[][] check;
	private static int[][] delta = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	
	private static boolean isExist() {
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < M; x++) {
				if (board[y][x] > 0) {
					return true;
				}
			}
		}
		
		return false;
	}
	
  // 인접한 바다 카운트해서 얼마나 녹을지 저장 후 한번에 녹이기
	private static void melt() {
		ArrayList<int[]> toMelt = new ArrayList<>();
		
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < M; x++) {
				if (board[y][x] > 0) {
					int cnt = 0;
					
					for (int[] d : delta) {
						int ny = y + d[0];
						int nx = x + d[1];
						
						if (ny >= 0 && ny < N && nx >= 0 && nx < M && board[ny][nx] == 0) {
							cnt++;
						}
					}
					
					toMelt.add(new int[] {y, x, cnt});
				}
			}
		}
		
		for (int[] p : toMelt) {
			board[p[0]][p[1]] -= board[p[0]][p[1]] < p[2] ? board[p[0]][p[1]] : p[2];
		}
	}
	
	private static boolean isDevided() {
		check = new boolean[N][M];
		int cnt = 0;
		
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < M; x++) {
				if (board[y][x] > 0 && !check[y][x]) {
					if (cnt == 1) {
						return true;
					}
					
					dfs(y, x);
					cnt++;
				}
			}
		}
		
		return false;
	}
	
	private static void dfs(int i, int j) {
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] {i, j});
		check[i][j] = true;
		
		while (!q.isEmpty()) {
			int[] now = q.poll();
			
			for (int[] d : delta) {
				int ny = now[0] + d[0];
				int nx = now[1] + d[1];
				
				if (ny >= 0 && ny < N && nx >= 0 && nx < M && board[ny][nx] > 0 && !check[ny][nx]) {
					check[ny][nx] = true;
					q.add(new int[] {ny, nx});
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer size = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(size.nextToken());
		M = Integer.parseInt(size.nextToken());
		board = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer line = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(line.nextToken());
			}
		}
		
		int res = 0;
		int temp = 0;
		
		while (isExist()) {
			melt();
			temp++;
			
			if (isDevided()) {
				res = temp;
				break;
			}
		}
		
		System.out.println(res);
	}
}