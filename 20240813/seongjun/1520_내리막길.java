package swea;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int[] dx = {0, 0, -1, 1 };
	static int[] dy = {1, -1, 0, 0 };

	static int checkPath(int[][] map, int x, int y, int[][] dp, boolean[][] visit) {
		if (visit[y][x]) {
			return dp[y][x];
		}
		if (x == map[0].length-1 && y == map.length-1) {
			return dp[y][x];
		}
		visit[y][x] = true;
		for (int i=0;i<4;i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			// 범위 체크
			if (nx < 0 || nx >= map[0].length || ny < 0 || ny >= map.length) {
				continue;
			}
			// 내리막 체크
			if (map[y][x] > map[ny][nx]) {
				dp[y][x] += checkPath(map, nx, ny, dp, visit);
			}
		}
		return dp[y][x];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());

		int[][] map = new int[m][n];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][] dp = new int[m][n];
		boolean[][] visit = new boolean[m][n];
		dp[m-1][n-1] = 1;

		int answer = 0;
		if (map[0][0] > map[m-1][n-1]) {
			answer = checkPath(map, 0, 0, dp, visit);	
		}
		System.out.println(answer);
	}
}
